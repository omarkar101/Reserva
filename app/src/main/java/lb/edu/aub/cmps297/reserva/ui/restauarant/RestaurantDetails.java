package lb.edu.aub.cmps297.reserva.ui.restauarant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
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
import lb.edu.aub.cmps297.reserva.database.Entities.Restaurant;
import lb.edu.aub.cmps297.reserva.database.ViewModels.RestaurantViewModel;
import lb.edu.aub.cmps297.reserva.models.Menu;
import lb.edu.aub.cmps297.reserva.views.MenuAdapter;
import lb.edu.aub.cmps297.reserva.views.RestaurantAdapter;

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
    private RecyclerView restaurantDetailsMenuRV;
    Context context;

    private RestaurantViewModel restaurantViewModel;
    private Restaurant chosenRestaurant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_details);

        restaurantViewModel = new ViewModelProvider(this).get(RestaurantViewModel.class);

//        restaurant = StaticStorage.restaurants.get(StaticStorage.restaurantChosen);
        restaurantImg = findViewById(R.id.idRestarauntDetialsImg);
        restaurantName = findViewById(R.id.idRestaurantDetailsName);
//        restaurantViewMenu = findViewById(R.id.idRestaurantDetialsViewMenu);
        restaurantDescriptionText = findViewById(R.id.idRestaurantDetailsDescriptionText);
        restaurantPhoneNumberText = findViewById(R.id.idRestaurantDetialsPhoneNumberText);
        restaurantLocationText = findViewById(R.id.idRestaurantDetailsLocationText);
        restaurantSeatsNumber = findViewById(R.id.idRestaurantDetailsSeatsNumber);
        restaurantArrowUp = findViewById(R.id.idRestaurantDetailsArrowUpBtn);
        restaurantArrowDown = findViewById(R.id.idRestaurantDetailsArrowDownBtn);
        restaurantReserve = findViewById(R.id.idRestaurantDetailsReserveBtn);

        context = this;
        restaurantDetailsMenuRV = findViewById(R.id.idRVRestaurantDetailsMenuRV);

        chosenRestaurant = restaurantViewModel.getRestaurant(StaticStorage.restaurantChosenEmail);
        restaurantImg.setImageResource(R.drawable.ic_dashboard_black_24dp);
        restaurantName.setText(chosenRestaurant.name);
        restaurantDescriptionText.setText(chosenRestaurant.description);
        restaurantPhoneNumberText.setText(chosenRestaurant.phoneNumber);
        restaurantLocationText.setText(chosenRestaurant.location);
        restaurantSeatsNumber.setText("0");



        MenuAdapter menuAdapter = new MenuAdapter(this, StaticStorage.restaurants.get(StaticStorage.restaurantChosen).getMenu().getImgList());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        restaurantDetailsMenuRV.setLayoutManager(linearLayoutManager);
        restaurantDetailsMenuRV.setAdapter(menuAdapter);

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