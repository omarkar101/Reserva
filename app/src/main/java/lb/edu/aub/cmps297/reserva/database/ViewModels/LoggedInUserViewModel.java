package lb.edu.aub.cmps297.reserva.database.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import lb.edu.aub.cmps297.reserva.database.Entities.LoggedInUser;
import lb.edu.aub.cmps297.reserva.database.Repositories.LoggedInUserRepository;

public class LoggedInUserViewModel extends AndroidViewModel {

    private LoggedInUserRepository loggedInUserRepository;

    public LoggedInUserViewModel(@NonNull Application application) {
        super(application);
        loggedInUserRepository = new LoggedInUserRepository(application);
    }

    public boolean insert(@NonNull LoggedInUser loggedInUser) {
        return loggedInUserRepository.insert(loggedInUser);
    }

    public LoggedInUser getUser() {
        return loggedInUserRepository.getUser();
    }

    public void deleteAll() {
        loggedInUserRepository.deleteAll();
    }
}