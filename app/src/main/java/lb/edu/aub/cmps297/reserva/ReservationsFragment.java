package lb.edu.aub.cmps297.reserva;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import lb.edu.aub.cmps297.reserva.databinding.FragmentProfileBinding;
import lb.edu.aub.cmps297.reserva.databinding.FragmentReservationsBinding;
import lb.edu.aub.cmps297.reserva.views.RestaurantAdapter;
import lb.edu.aub.cmps297.reserva.views.RestaurantIncomingRequestsAdapter;

public class ReservationsFragment extends Fragment {
    private FragmentReservationsBinding binding;
    private RecyclerView incomingRequestsRV;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentReservationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        incomingRequestsRV = root.findViewById(R.id.idRVRestaurantIncomingRequests);

        RestaurantIncomingRequestsAdapter incomingRequestsAdapter = new RestaurantIncomingRequestsAdapter(this.getContext(), StaticStorage.restaurants);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
        incomingRequestsRV.setLayoutManager(linearLayoutManager);
        incomingRequestsRV.setAdapter(incomingRequestsAdapter);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
