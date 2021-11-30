package lb.edu.aub.cmps297.reserva.database.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import java.util.ArrayList;
import java.util.List;

import lb.edu.aub.cmps297.reserva.database.Entities.Restaurant;
import lb.edu.aub.cmps297.reserva.database.Repositories.FavoriteRestaurantsByClientsRepository;

public class FavoriteRestaurantsByClientsViewModel extends AndroidViewModel {
    private final FavoriteRestaurantsByClientsRepository favoriteRestaurantsByClientsRepository;
    public FavoriteRestaurantsByClientsViewModel(@NonNull Application application) {
        super(application);
        favoriteRestaurantsByClientsRepository = new FavoriteRestaurantsByClientsRepository(application);
    }

    public ArrayList<Restaurant> getAllFavoriteRestaurants(String client_email) {
        return favoriteRestaurantsByClientsRepository.getAllFavoriteRestaurants(client_email);
    }

    public void removeFavoriteRestaurant(String client_email, String restaurant_email) {
        favoriteRestaurantsByClientsRepository.removeFavoriteRestaurant(client_email, restaurant_email);
    }

    public void addFavoriteRestaurant(String client_email, String restaurant_email) {
        favoriteRestaurantsByClientsRepository.addFavoriteRestaurant(client_email, restaurant_email);
    }
}
