package lb.edu.aub.cmps297.reserva.database.Entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "restaurants")
public class Restaurant {
    @PrimaryKey
    @ColumnInfo(name = "email")
    @NonNull
    public String email;

    @ColumnInfo(name = "password")
    public String password;

    @ColumnInfo(name = "name")
    public int name;

    @ColumnInfo(name = "phone_number")
    public String phoneNumber;

    @ColumnInfo(name = "description")
    public String description;

    @ColumnInfo(name = "seats_available")
    public int seatsAvailable;

    @ColumnInfo(name = "seats_max_capacity")
    public int seatsMaxCapacity;

    public Restaurant(@NonNull String email) {
        this.email = email;
    }
}