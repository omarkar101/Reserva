package lb.edu.aub.cmps297.reserva;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import lb.edu.aub.cmps297.reserva.database.Entities.LoggedInUser;
import lb.edu.aub.cmps297.reserva.database.Entities.Restaurant;
import lb.edu.aub.cmps297.reserva.database.ViewModels.LoggedInUserViewModel;
import lb.edu.aub.cmps297.reserva.database.ViewModels.RestaurantViewModel;

public class RestaurantEditInfo extends AppCompatActivity {
    private Button SaveChangesBtn;
    private Button CancelBtn;
//    private Restaurant restaurant;
//    private LoggedInUserViewModel loggedInUserViewModel;
//    private RestaurantViewModel restaurantViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_edit_info);
        ImageButton restaurantImg = findViewById(R.id.idRestaurantEditInfoRestaurantImgEdit);
        restaurantImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 3);
            }
        });

//        loggedInUserViewModel = new ViewModelProvider(this).get(LoggedInUserViewModel.class);
//        LoggedInUser loggedInUser = loggedInUserViewModel.getUser();
//        restaurant = restaurantViewModel.getRestaurant(loggedInUser.email);

        SaveChangesBtn = findViewById(R.id.idRestaurantEditInfoSaveChangesBtn);
        SaveChangesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        CancelBtn = findViewById(R.id.idRestaurantEditInfoCancelBtn);
        CancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            ImageView imageView = findViewById(R.id.idRestaurantEditInfoRestaurantImgEdit);
            imageView.setImageURI(selectedImage);
        }
    }
}