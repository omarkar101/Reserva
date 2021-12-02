package lb.edu.aub.cmps297.reserva.ui.restauarant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import lb.edu.aub.cmps297.reserva.Enums.ReservationStatus;
import lb.edu.aub.cmps297.reserva.R;
import lb.edu.aub.cmps297.reserva.StaticStorage;
import lb.edu.aub.cmps297.reserva.database.Entities.LoggedInUser;
import lb.edu.aub.cmps297.reserva.database.Entities.Restaurant;
import lb.edu.aub.cmps297.reserva.database.ViewModels.LoggedInUserViewModel;
import lb.edu.aub.cmps297.reserva.database.ViewModels.ReservationViewModel;
import lb.edu.aub.cmps297.reserva.database.ViewModels.RestaurantViewModel;
import lb.edu.aub.cmps297.reserva.adapters.MenuAdapter;

public class RestaurantDetails extends AppCompatActivity {
    private ImageView restaurantImg;
    private TextView restaurantName;
    private TextView restaurantDescriptionText;
    private TextView restaurantPhoneNumberText;
    private TextView restaurantLocationText;
    private TextView restaurantSeatsToRequestNumber;
    private ImageButton restaurantArrowUp;
    private ImageButton restaurantArrowDown;
    private Button restaurantReserve;
    private RecyclerView restaurantDetailsMenuRV;
    Context context;

    private RestaurantViewModel restaurantViewModel;
    private ReservationViewModel reservationViewModel;
    private LoggedInUserViewModel loggedInUserViewModel;

    private Restaurant chosenRestaurant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_details);

        restaurantViewModel = new ViewModelProvider(this).get(RestaurantViewModel.class);
        reservationViewModel = new ViewModelProvider(this).get(ReservationViewModel.class);
        loggedInUserViewModel = new ViewModelProvider(this).get(LoggedInUserViewModel.class);

        LoggedInUser loggedInUser = loggedInUserViewModel.getUser();

        restaurantImg = findViewById(R.id.idRestarauntDetialsImg);
        restaurantName = findViewById(R.id.idRestaurantDetailsName);
        restaurantDescriptionText = findViewById(R.id.idRestaurantDetailsDescriptionText);
        restaurantPhoneNumberText = findViewById(R.id.idRestaurantDetialsPhoneNumberText);
        restaurantLocationText = findViewById(R.id.idRestaurantDetailsLocationText);
        restaurantSeatsToRequestNumber = findViewById(R.id.idRestaurantDetailsSeatsToRequestNumber);
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

        restaurantSeatsToRequestNumber.setText("0");



        MenuAdapter menuAdapter = new MenuAdapter(this, StaticStorage.restaurants.get(StaticStorage.restaurantChosen).getMenu().getImgList());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        restaurantDetailsMenuRV.setLayoutManager(linearLayoutManager);
        restaurantDetailsMenuRV.setAdapter(menuAdapter);

        restaurantArrowUp.setOnClickListener(view -> {
            Integer count = Integer.parseInt(restaurantSeatsToRequestNumber.getText().toString());
            count++;
            restaurantSeatsToRequestNumber.setText(count.toString());
            restaurantReserve.setEnabled(true);
        });
        restaurantArrowDown.setOnClickListener(view -> {
            Integer count = Integer.parseInt(restaurantSeatsToRequestNumber.getText().toString());
            if (Integer.parseInt(restaurantSeatsToRequestNumber.getText().toString()) > 0){
                count--;
                restaurantSeatsToRequestNumber.setText(count.toString());
                restaurantReserve.setEnabled(true);
                restaurantReserve.setClickable(true);
            }
            if (Integer.parseInt(restaurantSeatsToRequestNumber.getText().toString()) == 0) {
                restaurantReserve.setEnabled(false);
                restaurantReserve.setClickable(false);
            }
        });
        restaurantReserve.setOnClickListener(view -> {
                restaurantReserve.setText("Reserve");
                restaurantArrowDown.setClickable(true);
                restaurantArrowUp.setEnabled(true);
                reservationViewModel.insert(loggedInUser.email, chosenRestaurant.email,
                        restaurantSeatsToRequestNumber.getText().toString(), ReservationStatus.PENDING.name());
            Toast.makeText(RestaurantDetails.this,
                    "Successfully reserved, please check you reservations page.", Toast.LENGTH_LONG).show();
        });
    }
}