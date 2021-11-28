package lb.edu.aub.cmps297.reserva.database.ViewModels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import lb.edu.aub.cmps297.reserva.database.Entities.Client;
import lb.edu.aub.cmps297.reserva.database.Repositories.ClientRepository;

public class ClientViewModel extends AndroidViewModel {
    private ClientRepository mClientRepository;

    public ClientViewModel(Application application) {
        super(application);
        mClientRepository = new ClientRepository(application);
    }
    public boolean insert(Client client) {
        return mClientRepository.insert(client);
    }
    public Client getClient(String email) {
        return mClientRepository.getClient(email);
    }
}
