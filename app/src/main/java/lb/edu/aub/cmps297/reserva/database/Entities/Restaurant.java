package lb.edu.aub.cmps297.reserva.database.Entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
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
    public String name;

    @ColumnInfo(name = "phone_number")
    public String phoneNumber;

    @ColumnInfo(name = "description")
    public String description;

    @ColumnInfo(name = "location")
    public String location;

    @ColumnInfo(name = "seats_reserved")
    public int seatsReserved;

    @ColumnInfo(name = "seats_max_capacity")
    public int seatsMaxCapacity;

    @ColumnInfo(name = "profile_uri")
    public String profileUri;

    @ColumnInfo(name = "menu_uri")
    public String menuUri;


    public Restaurant(@NonNull String email) {
        this.email = email;
    }

    @Ignore
    public Restaurant(String name, @NonNull String email, String password) {
        this.name = name;
        this.password = password;
        this.email = email;
        seatsMaxCapacity = 0;
        seatsReserved = 0;
        description = "Description of your restuarant goes here. To edit, please press the Edit Info button";
        location = "Location of your restuarant goes here. To edit, please press the Edit Info button";
        phoneNumber = "Phone number of your restuarant goes here. To edit, please press the Edit Info button";
    }
}
