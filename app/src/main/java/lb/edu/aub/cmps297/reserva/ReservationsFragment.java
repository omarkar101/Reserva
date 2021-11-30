package lb.edu.aub.cmps297.reserva;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import lb.edu.aub.cmps297.reserva.adapters.RestaurantAdapter;
import lb.edu.aub.cmps297.reserva.databinding.FragmentReservationsBinding;
import lb.edu.aub.cmps297.reserva.adapters.RestaurantCurrentReservationAdapter;
import lb.edu.aub.cmps297.reserva.adapters.RestaurantIncomingRequestsAdapter;

public class ReservationsFragment extends Fragment {
    private FragmentReservationsBinding binding;
    private RecyclerView incomingRequestsRV;
    private RecyclerView currentReservationsRV;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentReservationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        incomingRequestsRV = root.findViewById(R.id.idRVRestaurantIncomingRequests);
        currentReservationsRV = root.findViewById(R.id.idRVRestaurantCurrentReservations);


        RestaurantIncomingRequestsAdapter incomingRequestsAdapter = new RestaurantIncomingRequestsAdapter(this.getContext(), StaticStorage.restaurants);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
        incomingRequestsRV.setLayoutManager(linearLayoutManager1);
        incomingRequestsRV.setAdapter(incomingRequestsAdapter);


        RestaurantCurrentReservationAdapter currentReservationAdapter = new RestaurantCurrentReservationAdapter(this.getContext(),StaticStorage.restaurants);
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
