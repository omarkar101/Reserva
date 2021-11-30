package lb.edu.aub.cmps297.reserva;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

import lb.edu.aub.cmps297.reserva.adapters.RestaurantCurrentReservationAdapter;
import lb.edu.aub.cmps297.reserva.database.Entities.Client;
import lb.edu.aub.cmps297.reserva.database.Entities.LoggedInUser;
import lb.edu.aub.cmps297.reserva.database.Entities.Reservation;
import lb.edu.aub.cmps297.reserva.database.ViewModels.LoggedInUserViewModel;
import lb.edu.aub.cmps297.reserva.database.ViewModels.ReservationViewModel;
import lb.edu.aub.cmps297.reserva.database.ViewModels.RestaurantViewModel;

public class UserReservations extends AppCompatActivity {

    private RecyclerView currentReservationsRV;
    private RestaurantViewModel restaurantViewModel;
    private ReservationViewModel reservationViewModel;
    private LoggedInUserViewModel loggedInUserViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_reservations);

        currentReservationsRV = findViewById(R.id.idRVUserCurrentReservations);
        restaurantViewModel = new ViewModelProvider(this).get(RestaurantViewModel.class);
        loggedInUserViewModel = new ViewModelProvider(this).get(LoggedInUserViewModel.class);
        reservationViewModel = new ViewModelProvider(this).get(ReservationViewModel.class);

        LoggedInUser loggedInUser = loggedInUserViewModel.getUser();

        ArrayList<Reservation> reservations = reservationViewModel.getClientReservations(loggedInUser.email);

        RestaurantCurrentReservationAdapter restaurantCurrentReservationAdapter = new RestaurantCurrentReservationAdapter(this, reservations);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        currentReservationsRV.setLayoutManager(linearLayoutManager);
        currentReservationsRV.setAdapter(restaurantCurrentReservationAdapter);

    }
}