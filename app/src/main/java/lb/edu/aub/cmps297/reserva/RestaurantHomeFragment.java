package lb.edu.aub.cmps297.reserva;

import static android.provider.CalendarContract.CalendarCache.URI;

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Objects;

import lb.edu.aub.cmps297.reserva.database.AppDatabase;
import lb.edu.aub.cmps297.reserva.database.Entities.LoggedInUser;
import lb.edu.aub.cmps297.reserva.database.ViewModels.LoggedInUserViewModel;
import lb.edu.aub.cmps297.reserva.database.ViewModels.RestaurantViewModel;
import lb.edu.aub.cmps297.reserva.databinding.FragmentRestaurantHomeBinding;
import lb.edu.aub.cmps297.reserva.models.Menu;
import lb.edu.aub.cmps297.reserva.database.Entities.Restaurant;


public class RestaurantHomeFragment extends Fragment {
    private FragmentRestaurantHomeBinding binding;
    private Restaurant restaurant;
    private ImageView restaurantImg;
    private TextView restaurantName;
    private TextView restaurantDescriptionText;
    private TextView restaurantPhoneNumberText;
    private TextView restaurantLocationText;
    private TextView restaurantSeatsNumber;
    private ImageButton restaurantArrowUp;
    private ImageButton restaurantArrowDown;
    private Button restaurantSaveChanges;


    private LoggedInUserViewModel loggedInUserViewModel;

    private RestaurantViewModel restaurantViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRestaurantHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        ArrayList<Integer> menuImgs = new ArrayList<Integer>();
        menuImgs.add(R.drawable.menu1);
        menuImgs.add(R.drawable.menu2);

        Menu menu = new Menu(menuImgs);
        loggedInUserViewModel = new ViewModelProvider(this).get(LoggedInUserViewModel.class);
        restaurantViewModel = new ViewModelProvider(this).get(RestaurantViewModel.class);
        LoggedInUser loggedInUser = loggedInUserViewModel.getUser();


        restaurant = restaurantViewModel.getRestaurant(loggedInUser.email);
        if(restaurant == null) return root;


        restaurantImg = root.findViewById(R.id.idRestarauntHomeImg);
        restaurantName = root.findViewById(R.id.idRestaurantHomeName);
        restaurantDescriptionText = root.findViewById(R.id.idRestaurantHomeDescriptionText);
        restaurantPhoneNumberText = root.findViewById(R.id.idRestaurantHomePhoneNumberText);
        restaurantLocationText = root.findViewById(R.id.idRestaurantHomeLocationText);
        restaurantSeatsNumber = root.findViewById(R.id.idRestaurantHomeSeatsNumber);
        restaurantArrowUp = root.findViewById(R.id.idRestaurantHomeArrowUpBtn);
        restaurantArrowDown = root.findViewById(R.id.idRestaurantHomeArrowDownBtn);
        restaurantSaveChanges = root.findViewById(R.id.idRestaurantHomeSaveChangesBtn);


//        restaurantImg.setImageResource(R.drawable.ic_dashboard_black_24dp);

        restaurantName.setText(restaurant.name);
        restaurantDescriptionText.setText(restaurant.description);
        restaurantPhoneNumberText.setText(restaurant.phoneNumber);
        restaurantLocationText.setText(restaurant.location);
        restaurantSeatsNumber.setText(Integer.valueOf(restaurant.seatsMaxCapacity).toString());


//        Log.d("kaka","from home "+ restaurant.profileUri);

//        1 way
//        File finalFile = new File(restaurant.profileUri);
//        restaurantImg.setImageURI(Uri.fromFile(finalFile));

//        2 way
//        Picasso.with(getContext()).load(finalFile).into(restaurantImg);

//        3 way
//        if(finalFile.exists()){
//
//            Bitmap myBitmap = BitmapFactory.decodeFile(restaurant.profileUri);
//            restaurantImg.setImageBitmap(myBitmap);
//        }


//        4 way
//        restaurantImg.setImageBitmap(decodeSampledBitmapFromResource(restaurant.profileUri,10,10));


//        5 way
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//            byte[] decodedBytes = Base64.getDecoder().decode(restaurant.profileUri);
//            Bitmap bmp = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
//            restaurantImg.setImageBitmap(Bitmap.createScaledBitmap(bmp, restaurantImg.getWidth(), restaurantImg.getHeight(), false));
//        }



//        6 way
//        if (restaurant.profileImage != null && restaurant.profileImage.length > 0){
//
//            Bitmap bmp = BitmapFactory.decodeByteArray(restaurant.profileImage, 0, restaurant.profileImage.length);
//            restaurantImg.setImageBitmap(Bitmap.createScaledBitmap(bmp, restaurantImg.getWidth(), restaurantImg.getHeight(), false));
//
//            if (bmp != null){
//
//            }
//        }

//        7 way
//        java.net.URI uri = null;
//        try {
//            uri = new URI(restaurant.profileUri);
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        }
//        File finalFile = new File(restaurant.profileUri);
//        InputStream in = null;
//        try {
//            in = uri.toURL().openStream();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        BufferedInputStream bis = new BufferedInputStream(in,1024*8);
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//
//        int len=0;
//        byte[] buffer = new byte[1024];
//        while(true){
//            try {
//                if (!((len = bis.read(buffer)) != -1)) break;
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            out.write(buffer, 0, len);
//        }
//        try {
//            out.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        try {
//            bis.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        byte[] data = out.toByteArray();
//        Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
//        restaurantImg.setImageBitmap(bitmap);

//        8 way
//        File finalFile = new File(restaurant.profileUri);
//        Bitmap bitmap = null;
//
//        final Cursor cursor = this.getContext().getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
//                null, null, null, null);
//        if (cursor.moveToFirst()) {
//
//            if (Build.VERSION.SDK_INT >= 29) {
//                // You can replace '0' by 'cursor.getColumnIndex(MediaStore.Images.ImageColumns._ID)'
//                // Note that now, you read the column '_ID' and not the column 'DATA'
//                Uri imageUri= ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, cursor.getInt(0));
//
//                // now that you have the media URI, you can decode it to a bitmap
//                try (ParcelFileDescriptor pfd = this.getContext().getContentResolver().openFileDescriptor(Uri.fromFile(finalFile), "r")) {
//                    if (pfd != null) {
//                        bitmap = BitmapFactory.decodeFileDescriptor(pfd.getFileDescriptor());
//                    }
//                } catch (IOException ex) {
//
//                }
//            } else {
//                // Repeat the code you already are using
//            }
//        }
//        restaurantImg.setImageBitmap(bitmap);

        restaurantSaveChanges.setOnClickListener(view -> {
            restaurantViewModel.updateRestaurantSeatsNumber(restaurant.email, Integer.parseInt(restaurantSeatsNumber.getText().toString()));

        });
        restaurantArrowUp.setOnClickListener(view -> {
            Integer count = Integer.parseInt(restaurantSeatsNumber.getText().toString());
            count++;
            restaurantSeatsNumber.setText(count.toString());
            restaurantSaveChanges.setEnabled(true);

        });
        restaurantArrowDown.setOnClickListener(view -> {
            Integer count = Integer.parseInt(restaurantSeatsNumber.getText().toString());
            if (Integer.parseInt(restaurantSeatsNumber.getText().toString()) > 0) {
                count--;
                restaurantSeatsNumber.setText(count.toString());
            }
            if (Integer.parseInt(restaurantSeatsNumber.getText().toString()) == 0) {
                restaurantSaveChanges.setEnabled(false);
            }
        });

        return root;
    }

    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    public static Bitmap decodeSampledBitmapFromResource(String path,
                                                         int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(path, options);
    }


//    @Override
//    public void onClick(View v) {
//        Intent intent = new Intent(this.getContext(), RestaurantEditInfo.class);
//        startActivity(intent);
//    }
}