package lb.edu.aub.cmps297.reserva.database.DAO;

import android.net.Uri;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import lb.edu.aub.cmps297.reserva.database.Entities.Client;
import lb.edu.aub.cmps297.reserva.database.Entities.Restaurant;

@Dao
public interface RestaurantDao {
    @Query("SELECT * FROM restaurants")
    List<Restaurant> getAll();

    @Query("SELECT * FROM restaurants WHERE email = :email")
    Restaurant findByEmail(String email);

    @Insert
    void insertAll(Restaurant... restaurants);

    @Delete
    void delete(Restaurant restaurant);

    @Query("UPDATE restaurants SET name=:name,phone_number=:phoneNumber,location=:Location,description=:Description WHERE email=:email")
    void updateRestaurantInfo(String name, String email, String phoneNumber, String Location, String Description);

    @Query("UPDATE restaurants SET seats_max_capacity=:seatsMaxCapacity WHERE email=:email")
    void updateRestaurantSeatsNumber(String email,int seatsMaxCapacity);

    @Query("UPDATE restaurants SET seats_reserved=:seats_reserved WHERE email=:email")
    void updateRestaurantSeatsReserved(String email, int seats_reserved);
    @Query("UPDATE restaurants SET profile_uri=:profileImagePath where email=:email")
    void updateRestaurantProfileImageUsingUri(String email, String profileImagePath);

    @Query("UPDATE restaurants SET menu_uri=:menuImagePath where email=:email")
    void updateRestaurantMenuImageUsingUri(String email, String menuImagePath);
}
