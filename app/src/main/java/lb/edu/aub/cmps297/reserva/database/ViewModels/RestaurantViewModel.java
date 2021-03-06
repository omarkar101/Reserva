package lb.edu.aub.cmps297.reserva.database.ViewModels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import java.util.List;

import lb.edu.aub.cmps297.reserva.database.Entities.Client;
import lb.edu.aub.cmps297.reserva.database.Entities.Restaurant;
import lb.edu.aub.cmps297.reserva.database.Repositories.RestaurantRepository;

public class RestaurantViewModel extends AndroidViewModel {
    private RestaurantRepository mRestaurantRepository;

    public RestaurantViewModel(Application application) {
        super(application);
        mRestaurantRepository = new RestaurantRepository(application);
    }
    public List<Restaurant> getAll() {
        return mRestaurantRepository.getAll();
    }
    public boolean insert(Restaurant restaurant) {
        return mRestaurantRepository.insert(restaurant);
    }
    public Restaurant getRestaurant(String email) {
        return mRestaurantRepository.getRestaurant(email);
    }
    public void updateRestaurantInfo(String name, String email, String phoneNumber, String Location, String Description){
        mRestaurantRepository.updateRestaurantInfo(name, email, phoneNumber, Location, Description);
    }
    public void updateRestaurantMaxSeatsNumber(String email, int seatsMaxCapacity){
        mRestaurantRepository.updateRestaurantSeatsNumber(email, seatsMaxCapacity);
    }
    public void updateRestaurantSeatsReserved(String email, int seatsReserved) {
        mRestaurantRepository.updateRestaurantSeatsReserved(email, Integer.valueOf(seatsReserved).toString());
    }
    public void updateRestaurantProfileImageUsingUri(String email,String profileImagePath){
        mRestaurantRepository.updateRestaurantProfileImageUsingUri(email, profileImagePath);
    }

    public void updateRestaurantMenuImageUsingUri(String email,String menuImagePath){
        mRestaurantRepository.updateRestaurantMenuImageUsingUri(email, menuImagePath);
    }
}
