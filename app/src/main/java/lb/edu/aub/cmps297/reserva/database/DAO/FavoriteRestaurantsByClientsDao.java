package lb.edu.aub.cmps297.reserva.database.DAO;

import androidx.room.Dao;
import androidx.room.Query;

import lb.edu.aub.cmps297.reserva.database.Entities.Restaurant;

@Dao
public interface FavoriteRestaurantsByClientsDao {

    @Query("SELECT * FROM restaurants where email in " +
            "(SELECT email from favorite_restaurants_by_clients where client_email = :client_email)")
    public Restaurant[] getAllFavoriteRestaurants(String client_email);

    @Query("DELETE FROM favorite_restaurants_by_clients where client_email=:client_email and restaurant_email=:restaurant_email")
    public void removeFavoriteRestaurant(String client_email, String restaurant_email);

    @Query("INSERT INTO favorite_restaurants_by_clients (client_email, restaurant_email) VALUES(:client_email, :restaurant_email)")
    public void addFavoriteRestaurant(String client_email, String restaurant_email);
}
