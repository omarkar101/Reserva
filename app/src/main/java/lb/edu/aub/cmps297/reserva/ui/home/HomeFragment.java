package lb.edu.aub.cmps297.reserva.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import lb.edu.aub.cmps297.reserva.R;
import lb.edu.aub.cmps297.reserva.StaticStorage;
import lb.edu.aub.cmps297.reserva.databinding.FragmentHomeBinding;
import lb.edu.aub.cmps297.reserva.ui.favorites.FavoritesFragment;
import lb.edu.aub.cmps297.reserva.views.RestaurantAdapter;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private RecyclerView restaurantRV;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        restaurantRV = root.findViewById(R.id.idRVRestaurant);
        RestaurantAdapter restaurantAdapter = new RestaurantAdapter(this.getContext(), StaticStorage.restaurants);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
        restaurantRV.setLayoutManager(linearLayoutManager);
        restaurantRV.setAdapter(restaurantAdapter);


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}