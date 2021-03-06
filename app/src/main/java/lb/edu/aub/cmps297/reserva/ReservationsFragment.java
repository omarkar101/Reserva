package lb.edu.aub.cmps297.reserva;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

import lb.edu.aub.cmps297.reserva.Enums.ReservationStatus;
import lb.edu.aub.cmps297.reserva.Enums.UserType;
import lb.edu.aub.cmps297.reserva.database.Entities.LoggedInUser;
import lb.edu.aub.cmps297.reserva.database.Entities.Reservation;
import lb.edu.aub.cmps297.reserva.database.Entities.Restaurant;
import lb.edu.aub.cmps297.reserva.database.ViewModels.ClientViewModel;
import lb.edu.aub.cmps297.reserva.database.ViewModels.LoggedInUserViewModel;
import lb.edu.aub.cmps297.reserva.database.ViewModels.ReservationViewModel;
import lb.edu.aub.cmps297.reserva.database.ViewModels.RestaurantViewModel;
import lb.edu.aub.cmps297.reserva.databinding.FragmentReservationsBinding;
import lb.edu.aub.cmps297.reserva.adapters.RestaurantCurrentReservationAdapter;
import lb.edu.aub.cmps297.reserva.adapters.RestaurantIncomingRequestsAdapter;

public class ReservationsFragment extends Fragment {
    private FragmentReservationsBinding binding;
    private RecyclerView incomingRequestsRV;
    private RecyclerView currentReservationsRV;
    private Button seatsRemaining;

    private Restaurant restaurant;

    private LoggedInUserViewModel loggedInUserViewModel;
    private RestaurantViewModel restaurantViewModel;
    private ReservationViewModel reservationViewModel;
    private ClientViewModel clientViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentReservationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        incomingRequestsRV = root.findViewById(R.id.idRVRestaurantIncomingRequests);
        currentReservationsRV = root.findViewById(R.id.idRVRestaurantCurrentReservations);
        seatsRemaining = root.findViewById(R.id.idRestaurantReservationsSeatsRemainingText);

        loggedInUserViewModel = new ViewModelProvider(this).get(LoggedInUserViewModel.class);
        restaurantViewModel = new ViewModelProvider(this).get(RestaurantViewModel.class);
        LoggedInUser loggedInUser = loggedInUserViewModel.getUser();

        restaurant = restaurantViewModel.getRestaurant(loggedInUser.email);

        reservationViewModel = new ViewModelProvider(this).get(ReservationViewModel.class);
        seatsRemaining.setText(("Seats Remaining: "+Integer.valueOf(restaurant.seatsMaxCapacity - restaurant.seatsReserved).toString()));

        ArrayList<Reservation> incomingReservations = reservationViewModel.getRestaurantReservations(restaurant.email);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            incomingReservations.removeIf(x -> !x.status.equals(ReservationStatus.PENDING.name()));
        }

        clientViewModel = new ViewModelProvider(this).get(ClientViewModel.class);
        RestaurantIncomingRequestsAdapter incomingRequestsAdapter =
                new RestaurantIncomingRequestsAdapter(this.getContext(), incomingReservations, reservationViewModel, clientViewModel, restaurantViewModel);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
        incomingRequestsRV.setLayoutManager(linearLayoutManager1);
        incomingRequestsRV.setAdapter(incomingRequestsAdapter);

        ArrayList<Reservation> currentReservations = reservationViewModel.getRestaurantReservations(restaurant.email);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            currentReservations.removeIf(x -> x.status.equals(ReservationStatus.PENDING.name()));
        }

        RestaurantCurrentReservationAdapter currentReservationAdapter =
                new RestaurantCurrentReservationAdapter(this.getContext(), currentReservations,
                        restaurant.phoneNumber, UserType.RESTAURANT, reservationViewModel, restaurantViewModel);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
        currentReservationsRV.setLayoutManager(linearLayoutManager2);
        currentReservationsRV.setAdapter(currentReservationAdapter);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
