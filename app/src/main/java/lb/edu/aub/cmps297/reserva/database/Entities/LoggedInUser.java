package lb.edu.aub.cmps297.reserva.database.Entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "logged_in_user")
public class LoggedInUser {

    @ColumnInfo(name = "user_type")
    @NonNull
    public String userType;

    @PrimaryKey
    @ColumnInfo(name = "email")
    @NonNull
    public String email;

    public LoggedInUser(@NonNull String email, @NonNull String userType) {
        this.userType = userType;
        this.email = email;
    }
}
