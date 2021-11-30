package lb.edu.aub.cmps297.reserva.database.Entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "favorite_restaurants_by_clients",
        foreignKeys = {@ForeignKey(entity = Restaurant.class,
                        parentColumns = "email",
                        childColumns = "restaurant_email",
                        onDelete = ForeignKey.CASCADE),
                       @ForeignKey(entity = Client.class,
                        parentColumns = "email",
                        childColumns = "client_email",
                        onDelete = ForeignKey.CASCADE)})
public class FavoriteRestaurantsByClients {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "restaurant_email")
    public String restaurantEmail;

    @ColumnInfo(name = "client_email")
    public String clientEmail;

    public FavoriteRestaurantsByClients(String restaurantEmail, String clientEmail) {
        this.restaurantEmail = restaurantEmail;
        this.clientEmail = clientEmail;
    }

}
