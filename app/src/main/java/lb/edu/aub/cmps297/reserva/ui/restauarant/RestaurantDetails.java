package lb.edu.aub.cmps297.reserva.ui.restauarant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

import lb.edu.aub.cmps297.reserva.R;
import lb.edu.aub.cmps297.reserva.StaticStorage;
import lb.edu.aub.cmps297.reserva.database.Entities.Restaurant;
import lb.edu.aub.cmps297.reserva.database.ViewModels.RestaurantViewModel;

public class RestaurantDetails extends AppCompatActivity {
    private ImageView restaurantImg;
    private TextView restaurantName;
    private TextView restaurantDescriptionText;
    private TextView restaurantPhoneNumberText;
    private TextView restaurantLocationText;
    private TextView restaurantSeatsNumber;
    private ImageButton restaurantArrowUp;
    private ImageButton restaurantArrowDown;
    private Button restaurantReserve;

    private ImageView menuImg;
    Context context;

    private RestaurantViewModel restaurantViewModel;
    private Restaurant chosenRestaurant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_details);

        restaurantViewModel = new ViewModelProvider(this).get(RestaurantViewModel.class);


        restaurantImg = findViewById(R.id.idRestarauntDetialsImg);
        restaurantName = findViewById(R.id.idRestaurantDetailsName);

        restaurantDescriptionText = findViewById(R.id.idRestaurantDetailsDescriptionText);
        restaurantPhoneNumberText = findViewById(R.id.idRestaurantDetialsPhoneNumberText);
        restaurantLocationText = findViewById(R.id.idRestaurantDetailsLocationText);
        restaurantSeatsNumber = findViewById(R.id.idRestaurantDetailsSeatsNumber);
        restaurantArrowUp = findViewById(R.id.idRestaurantDetailsArrowUpBtn);
        restaurantArrowDown = findViewById(R.id.idRestaurantDetailsArrowDownBtn);
        restaurantReserve = findViewById(R.id.idRestaurantDetailsReserveBtn);

        menuImg = findViewById(R.id.idRestaurantDetailsMenuImage);

        context = this;


        chosenRestaurant = restaurantViewModel.getRestaurant(StaticStorage.restaurantChosenEmail);


        if(chosenRestaurant.profileUri != null){
            File finalFile = new File(chosenRestaurant.profileUri);
            restaurantImg.setImageURI(Uri.fromFile(finalFile));
        }
        else{
            restaurantImg.setImageResource(R.drawable.profile);
        }
        if(chosenRestaurant.menuUri != null){
            File finalFile = new File(chosenRestaurant.menuUri);
            menuImg.setImageURI(Uri.fromFile(finalFile));
        }
        else{
            menuImg.setImageResource(R.drawable.profile);
        }
        restaurantName.setText(chosenRestaurant.name);
        restaurantDescriptionText.setText(chosenRestaurant.description);
        restaurantPhoneNumberText.setText(chosenRestaurant.phoneNumber);
        restaurantLocationText.setText(chosenRestaurant.location);
        restaurantSeatsNumber.setText("0");





        restaurantArrowUp.setOnClickListener(view -> {
            Integer count = Integer.parseInt(restaurantSeatsNumber.getText().toString());
            count++;
            restaurantSeatsNumber.setText(count.toString());
            restaurantReserve.setEnabled(true);
        });
        restaurantArrowDown.setOnClickListener(view -> {
            Integer count = Integer.parseInt(restaurantSeatsNumber.getText().toString());
            if (Integer.parseInt(restaurantSeatsNumber.getText().toString()) > 0){
                count--;
                restaurantSeatsNumber.setText(count.toString());
            }
            if (Integer.parseInt(restaurantSeatsNumber.getText().toString()) == 0) {
                restaurantReserve.setEnabled(false);
            }
        });
        restaurantReserve.setOnClickListener(view -> {
            if (restaurantReserve.getText() == "Cancel Reservation"){
                restaurantReserve.setText("Reserve");
                restaurantArrowDown.setClickable(true);
                restaurantArrowUp.setClickable(true);
                restaurantArrowDown.setEnabled(true);
                restaurantArrowUp.setEnabled(true);
            }
            else {
                restaurantReserve.setText("Cancel Reservation");
                restaurantArrowDown.setClickable(false);
                restaurantArrowUp.setClickable(false);
                restaurantArrowDown.setEnabled(false);
                restaurantArrowUp.setEnabled(false);
            }

        });


//        restaurantViewMenu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(context, RestaurantMenu.class);
//                context.startActivity(i);
//            }
//        });

    }



}