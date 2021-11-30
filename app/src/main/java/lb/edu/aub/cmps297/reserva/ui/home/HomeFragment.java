package lb.edu.aub.cmps297.reserva.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

import lb.edu.aub.cmps297.reserva.R;
import lb.edu.aub.cmps297.reserva.StaticStorage;
import lb.edu.aub.cmps297.reserva.database.Entities.Restaurant;
import lb.edu.aub.cmps297.reserva.database.ViewModels.FavoriteRestaurantsByClientsViewModel;
import lb.edu.aub.cmps297.reserva.database.ViewModels.LoggedInUserViewModel;
import lb.edu.aub.cmps297.reserva.database.ViewModels.RestaurantViewModel;
import lb.edu.aub.cmps297.reserva.databinding.FragmentHomeBinding;
import lb.edu.aub.cmps297.reserva.ui.favorites.FavoritesFragment;
import lb.edu.aub.cmps297.reserva.views.RestaurantAdapter;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private RecyclerView restaurantRV;
    private RestaurantViewModel restaurantViewModel;

    private FavoriteRestaurantsByClientsViewModel favoriteRestaurantsByClientsViewModel;
    private LoggedInUserViewModel loggedInUserViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        restaurantRV = root.findViewById(R.id.idRVRestaurant);
        restaurantViewModel = new ViewModelProvider(this).get(RestaurantViewModel.class);

        List<Restaurant> restaurants = restaurantViewModel.getAll();

        loggedInUserViewModel = new ViewModelProvider(this).get(LoggedInUserViewModel.class);
        String client_email = loggedInUserViewModel.getUser().email;

        favoriteRestaurantsByClientsViewModel = new ViewModelProvider(this).get(FavoriteRestaurantsByClientsViewModel.class);

        RestaurantAdapter restaurantAdapter = new RestaurantAdapter(this.getContext(), (ArrayList<Restaurant>) restaurants,
                favoriteRestaurantsByClientsViewModel, client_email);
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
