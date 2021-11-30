package lb.edu.aub.cmps297.reserva.database.DAO;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

import lb.edu.aub.cmps297.reserva.database.Entities.Reservation;

@Dao
public interface ReservationDao {
    @Query("SELECT * from reservations WHERE restaurant_email = :restaurant_email")
    List<Reservation> getRestaurantReservations(String restaurant_email);

    @Query("SELECT * from reservations WHERE client_email = :client_email")
    List<Reservation> getClientReservations(String client_email);

    @Query("UPDATE reservations SET status=:status " +
            "where client_email=:client_email and restaurant_email=:restaurant_email")
    void updateReservation(String client_email, String restaurant_email, String status);

    @Query("INSERT INTO reservations (client_email, restaurant_email, status)" +
            "VALUES (:client_email, :restaurant_email, :status)")
    void insert(String client_email, String restaurant_email, String status);
}
