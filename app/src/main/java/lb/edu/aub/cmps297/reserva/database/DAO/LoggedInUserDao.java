package lb.edu.aub.cmps297.reserva.database.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import lb.edu.aub.cmps297.reserva.database.Entities.LoggedInUser;

@Dao
public interface LoggedInUserDao {
    @Query("SELECT * FROM logged_in_user")
    List<LoggedInUser> getAll();

    @Query("SELECT * FROM logged_in_user WHERE email = :email")
    LoggedInUser findByEmail(String email);

    @Insert
    void insertAll(LoggedInUser... loggedInUser);

    @Delete
    void delete(LoggedInUser loggedInUser);

    @Query("DELETE from logged_in_user")
    void deleteAll();
}
