package lb.edu.aub.cmps297.reserva.database.ViewModels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import lb.edu.aub.cmps297.reserva.database.Entities.Client;
import lb.edu.aub.cmps297.reserva.database.Entities.Restaurant;
import lb.edu.aub.cmps297.reserva.database.Repositories.RestaurantRepository;

public class RestaurantViewModel extends AndroidViewModel {
    private RestaurantRepository mRestaurantRepository;

    public RestaurantViewModel(Application application) {
        super(application);
        mRestaurantRepository = new RestaurantRepository(application);
    }
    public boolean insert(Restaurant restaurant) {
        return mRestaurantRepository.insert(restaurant);
    }
    public Restaurant getRestaurant(String email) {
        return mRestaurantRepository.getRestaurant(email);
    }
}