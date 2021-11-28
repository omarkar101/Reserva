package lb.edu.aub.cmps297.reserva.database.Repositories;

import android.app.Application;

import androidx.annotation.NonNull;

import lb.edu.aub.cmps297.reserva.database.AppDatabase;
import lb.edu.aub.cmps297.reserva.database.DAO.LoggedInUserDao;
import lb.edu.aub.cmps297.reserva.database.DAO.RestaurantDao;
import lb.edu.aub.cmps297.reserva.database.Entities.Client;
import lb.edu.aub.cmps297.reserva.database.Entities.LoggedInUser;

public class LoggedInUserRepository {
    private LoggedInUserDao mLoggedInUserDao;

    public LoggedInUserRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        mLoggedInUserDao = db.loggedInUserDao();
    }

    public boolean insert(@NonNull LoggedInUser loggedInUser) {
        LoggedInUser loggedInUserFromDb = mLoggedInUserDao.findByEmail(loggedInUser.email);
        if(loggedInUserFromDb != null) {
            return false;
        }
        mLoggedInUserDao.deleteAll();
        mLoggedInUserDao.insertAll(loggedInUser);
        return true;
    }

    public LoggedInUser getUser(String email) {
        return mLoggedInUserDao.findByEmail(email);
    }
    public void deleteAll() {
        mLoggedInUserDao.deleteAll();
    }
}
