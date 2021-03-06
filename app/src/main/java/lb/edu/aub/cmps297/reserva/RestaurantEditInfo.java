package lb.edu.aub.cmps297.reserva;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.FileUtils;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import lb.edu.aub.cmps297.reserva.database.AppDatabase;
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
    private ImageButton menuImg;



    private Restaurant restaurant;
    private LoggedInUserViewModel loggedInUserViewModel;
    private RestaurantViewModel restaurantViewModel;
    String selectedImageNew;
    String selectedMenuImageNew;
    boolean isMenuImg;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_edit_info);

        menuImg = findViewById(R.id.idRestaurantEditInfoRestaurantMenuEdit);
        restaurantImg = findViewById(R.id.idRestaurantEditInfoRestaurantImgEdit);
        restaurantName = findViewById(R.id.idRestaurantEditInfoRestaurantNameEditText);
        restaurantDescription = findViewById(R.id.idRestaurantEditInfoDescriptionText);
        restaurantLocation = findViewById(R.id.idRestaurantEditInfoLocationText);
        restaurantPhoneNumber = findViewById(R.id.idRestaurantEditInfoPhoneNumberText);

        restaurantImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isMenuImg = false;
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 3);
            }
        });

        menuImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isMenuImg = true;
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

        if(restaurant.profileUri != null){
            File finalFile = new File(restaurant.profileUri);
            restaurantImg.setImageURI(Uri.fromFile(finalFile));
        }
        else{
            restaurantImg.setImageResource(R.drawable.profile);
        }

        if(restaurant.menuUri != null){
            File finalFile = new File(restaurant.menuUri);
            menuImg.setImageURI(Uri.fromFile(finalFile));
        }
        else{
            menuImg.setImageResource(R.drawable.profile);
        }



        SaveChangesBtn = findViewById(R.id.idRestaurantEditInfoSaveChangesBtn);
        SaveChangesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restaurantViewModel.updateRestaurantInfo(restaurantName.getText().toString(),
                        restaurant.email,restaurantPhoneNumber.getText().toString(),
                        restaurantLocation.getText().toString(),restaurantDescription.getText().toString());


                if(selectedImageNew != null){
                    restaurantViewModel.updateRestaurantProfileImageUsingUri(restaurant.email, selectedImageNew);
                }

                if (selectedMenuImageNew !=null){
                    restaurantViewModel.updateRestaurantMenuImageUsingUri(restaurant.email, selectedMenuImageNew);
                }
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

            if (isMenuImg){
                Uri selectedImage = data.getData();
                ImageView imageView = findViewById(R.id.idRestaurantEditInfoRestaurantMenuEdit);
                imageView.setImageURI(selectedImage);


                File finalFile = new File(getRealPathFromURI(selectedImage));

                selectedMenuImageNew = finalFile.getAbsolutePath();
            }
            else{
                Uri selectedImage = data.getData();
                ImageView imageView = findViewById(R.id.idRestaurantEditInfoRestaurantImgEdit);
                imageView.setImageURI(selectedImage);


                File finalFile = new File(getRealPathFromURI(selectedImage));

                selectedImageNew = finalFile.getAbsolutePath();
            }



        }
    }

    public String getRealPathFromURI(Uri uri) {
        String path = "";
        if (getContentResolver() != null) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
                int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                path = cursor.getString(idx);
                cursor.close();
            }
        }
        return path;
    }

}