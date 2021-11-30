package lb.edu.aub.cmps297.reserva;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import lb.edu.aub.cmps297.reserva.database.Entities.LoggedInUser;
import lb.edu.aub.cmps297.reserva.database.Entities.Restaurant;
import lb.edu.aub.cmps297.reserva.database.ViewModels.LoggedInUserViewModel;
import lb.edu.aub.cmps297.reserva.database.ViewModels.RestaurantViewModel;

public class RestaurantEditInfo extends AppCompatActivity {
    private Button SaveChangesBtn;
    private Button CancelBtn;
    private EditText restaurantName;
    private EditText restaurantDescription;
    private EditText restaurantLocation;
    private EditText restaurantPhoneNumber;

    private ImageButton restaurantImg;

    private Restaurant restaurant;
    private LoggedInUserViewModel loggedInUserViewModel;
    private RestaurantViewModel restaurantViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_edit_info);

        restaurantImg = findViewById(R.id.idRestaurantEditInfoRestaurantImgEdit);
        restaurantName = findViewById(R.id.idRestaurantEditInfoRestaurantNameEditText);
        restaurantDescription = findViewById(R.id.idRestaurantEditInfoDescriptionText);
        restaurantLocation = findViewById(R.id.idRestaurantEditInfoLocationText);
        restaurantPhoneNumber = findViewById(R.id.idRestaurantEditInfoPhoneNumberText);

        restaurantImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 3);
            }
        });

        loggedInUserViewModel = new ViewModelProvider(this).get(LoggedInUserViewModel.class);
        restaurantViewModel = new ViewModelProvider(this).get(RestaurantViewModel.class);
        LoggedInUser loggedInUser = loggedInUserViewModel.getUser();
        restaurant = restaurantViewModel.getRestaurant(loggedInUser.email);
        restaurantName.setText(restaurant.name);
        restaurantDescription.setText(restaurant.description);
        restaurantPhoneNumber.setText(restaurant.phoneNumber);
        restaurantLocation.setText(restaurant.location);

//        Bitmap bitmap = ((BitmapDrawable)restaurantImg.getDrawable()).getBitmap();


        SaveChangesBtn = findViewById(R.id.idRestaurantEditInfoSaveChangesBtn);
        SaveChangesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restaurantViewModel.updateRestaurantInfo(restaurantName.getText().toString(),
                        restaurant.email,restaurantPhoneNumber.getText().toString(),
                        restaurantLocation.getText().toString(),restaurantDescription.getText().toString());
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

            // hon bede hot el sura bel database

            // hay mnaamila bel profile activity hek men jeeb el sura men database
            // imageView.setImageBitmap(BitmapFactory.decodeStream(is)); // is hiye el blob

//            OutputStream out = new ByteArrayOutputStream(imageView);
        }
    }
}