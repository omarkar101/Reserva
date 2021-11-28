package lb.edu.aub.cmps297.reserva.database.Entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "clients")
public class Client {
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

    public Client(@NonNull String email) {
        this.email = email;
    }
}
