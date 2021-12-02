package lb.edu.aub.cmps297.reserva.database.Entities;

import android.content.Intent;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "reservations",foreignKeys = {@ForeignKey(entity = Restaurant.class,
        parentColumns = "email",
        childColumns = "restaurant_email",
        onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = Client.class,
                parentColumns = "email",
                childColumns = "client_email",
                onDelete = ForeignKey.CASCADE)})
public class Reservation {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public Integer id;

    @ColumnInfo(name = "restaurant_email")
    public String restaurantEmail;

    @ColumnInfo(name = "client_email")
    public String clientEmail;

    @ColumnInfo(name = "seats_requested")
    public String seatsRequested;

    @ColumnInfo(name = "status")
    public String status;

    public Reservation(String restaurantEmail, String clientEmail, String seatsRequested, String status) {
        this.restaurantEmail = restaurantEmail;
        this.clientEmail = clientEmail;
        this.status = status;
        this.seatsRequested = seatsRequested;
    }
}
