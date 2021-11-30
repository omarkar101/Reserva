package lb.edu.aub.cmps297.reserva;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import lb.edu.aub.cmps297.reserva.adapters.RestaurantCurrentReservationAdapter;

public class UserReservations extends AppCompatActivity {

    private RecyclerView currentReservationsRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_reservations);

        currentReservationsRV = findViewById(R.id.idRVUserCurrentReservations);

        RestaurantCurrentReservationAdapter restaurantCurrentReservationAdapter = new RestaurantCurrentReservationAdapter(this,StaticStorage.restaurants);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        currentReservationsRV.setLayoutManager(linearLayoutManager);
        currentReservationsRV.setAdapter(restaurantCurrentReservationAdapter);

    }
}