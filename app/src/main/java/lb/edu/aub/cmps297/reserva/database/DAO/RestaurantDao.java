package lb.edu.aub.cmps297.reserva.database.DAO;

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
}
