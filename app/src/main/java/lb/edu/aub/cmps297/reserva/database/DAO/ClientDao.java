package lb.edu.aub.cmps297.reserva.database.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import lb.edu.aub.cmps297.reserva.database.Entities.Client;

@Dao
public interface ClientDao {
    @Query("SELECT * FROM clients")
    List<Client> getAll();

    @Query("SELECT * FROM clients WHERE email = :email")
    Client findByEmail(String email);

    @Insert
    void insertAll(Client... clients);

    @Delete
    void delete(Client client);
}
