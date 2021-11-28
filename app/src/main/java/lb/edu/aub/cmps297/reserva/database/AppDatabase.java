package lb.edu.aub.cmps297.reserva.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import lb.edu.aub.cmps297.reserva.database.DAO.LoggedInUserDao;
import lb.edu.aub.cmps297.reserva.database.DAO.RestaurantDao;
import lb.edu.aub.cmps297.reserva.database.DAO.ClientDao;
import lb.edu.aub.cmps297.reserva.database.Entities.LoggedInUser;
import lb.edu.aub.cmps297.reserva.database.Entities.Restaurant;
import lb.edu.aub.cmps297.reserva.database.Entities.Client;

@Database(entities = {Client.class, Restaurant.class, LoggedInUser.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ClientDao userDao();
    public abstract RestaurantDao restaurantDao();
    public abstract LoggedInUserDao loggedInUserDao();

    private static AppDatabase INSTANCE;

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    // Create database here.
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "reserva_db")
                            // Wipes and rebuilds instead of migrating if no Migration object.
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}