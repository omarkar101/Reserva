package lb.edu.aub.cmps297.reserva.ui.restauarant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import lb.edu.aub.cmps297.reserva.R;
import lb.edu.aub.cmps297.reserva.StaticStorage;
import lb.edu.aub.cmps297.reserva.models.Menu;
import lb.edu.aub.cmps297.reserva.models.Restaurant;
import lb.edu.aub.cmps297.reserva.views.MenuAdapter;
import lb.edu.aub.cmps297.reserva.views.RestaurantAdapter;

public class RestaurantDetails extends AppCompatActivity {
    private Restaurant restaurant;
    private ImageView restaurantImg;
    private TextView restaurantName;
//    private Button restaurantViewMenu;
    private TextView restaurantDescriptionTitle;
    private TextView restaurantDescriptionText;
    private TextView restaurantPhoneNumberTitle;
    private TextView restaurantPhoneNumberText;
    private TextView restaurantLocationTitle;
    private TextView restaurantLocationText;
    private TextView restaurantSeatsTitle;
    private TextView restaurantSeatsNumber;
    private ImageButton restaurantArrowUp;
    private ImageButton restaurantArrowDown;
    private Button restaurantReserve;
    private RecyclerView restaurantDetailsMenuRV;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_details);
        restaurant = StaticStorage.restaurants.get(StaticStorage.restaurantChosen);
        restaurantImg = findViewById(R.id.idRestarauntDetialsImg);
        restaurantName = findViewById(R.id.idRestaurantDetailsName);
//        restaurantViewMenu = findViewById(R.id.idRestaurantDetialsViewMenu);
        restaurantDescriptionTitle = findViewById(R.id.idRestaurantDetialsDescriptionTitle);
        restaurantDescriptionText = findViewById(R.id.idRestaurantDetailsDescriptionText);
        restaurantPhoneNumberTitle = findViewById(R.id.idRestaurantDetailPhoneNumberTitle);
        restaurantPhoneNumberText = findViewById(R.id.idRestaurantDetialsPhoneNumberText);
        restaurantLocationTitle = findViewById(R.id.idRestaurantDetailsLocationTitle);
        restaurantLocationText = findViewById(R.id.idRestaurantDetailsLocationText);
        restaurantSeatsTitle = findViewById(R.id.idRestaurantDetailsSeatsTitle);
        restaurantSeatsNumber = findViewById(R.id.idRestaurantDetailsSeatsNumber);
        restaurantArrowUp = findViewById(R.id.idRestaurantDetailsArrowUpBtn);
        restaurantArrowDown = findViewById(R.id.idRestaurantDetailsArrowDownBtn);
        restaurantReserve = findViewById(R.id.idRestaurantDetailsReserveBtn);

        context = this;
        restaurantImg.setImageResource(restaurant.getImg());
        restaurantName.setText(restaurant.getName());
        restaurantDescriptionText.setText(restaurant.getDescription());
        restaurantPhoneNumberText.setText(restaurant.getCellPhone());
        restaurantLocationText.setText(restaurant.getLocation());
        restaurantSeatsNumber.setText("0");


        restaurantDetailsMenuRV = findViewById(R.id.idRVRestaurantDetailsMenuRV);

        MenuAdapter menuAdapter = new MenuAdapter(this, StaticStorage.restaurants.get(StaticStorage.restaurantChosen).getMenu().getImgList());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        restaurantDetailsMenuRV.setLayoutManager(linearLayoutManager);
        restaurantDetailsMenuRV.setAdapter(menuAdapter);

        restaurantArrowUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer count = Integer.parseInt(restaurantSeatsNumber.getText().toString());
                if (Integer.parseInt(restaurantSeatsNumber.getText().toString()) < restaurant.getNumSeats()){
                    count++;
                    restaurantSeatsNumber.setText(count.toString());
                }
                restaurantReserve.setEnabled(true);
            }
        });
        restaurantArrowDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer count = Integer.parseInt(restaurantSeatsNumber.getText().toString());
                if (Integer.parseInt(restaurantSeatsNumber.getText().toString()) > 0){
                    count--;
                    restaurantSeatsNumber.setText(count.toString());
                }
                if (Integer.parseInt(restaurantSeatsNumber.getText().toString()) == 0) {
                    restaurantReserve.setEnabled(false);
                }
            }
        });
        restaurantReserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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