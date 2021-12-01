package lb.edu.aub.cmps297.reserva.database.Entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
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
    public String name;

    @ColumnInfo(name = "phone_number")
    public String phoneNumber;

    @ColumnInfo(typeAffinity = ColumnInfo.BLOB,name = "profile_image")
    public byte[] profileImage;

    public Client(@NonNull String email) {
        this.email = email;
    }

    @Ignore
    public Client(String name, @NonNull String email, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.phoneNumber = "Your phone number goes here.";
    }
}
