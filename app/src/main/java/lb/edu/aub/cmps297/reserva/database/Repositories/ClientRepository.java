package lb.edu.aub.cmps297.reserva.database.Repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;

import java.util.concurrent.ExecutionException;

import lb.edu.aub.cmps297.reserva.database.AppDatabase;
import lb.edu.aub.cmps297.reserva.database.DAO.ClientDao;
import lb.edu.aub.cmps297.reserva.database.DAO.RestaurantDao;
import lb.edu.aub.cmps297.reserva.database.Entities.Client;

public class ClientRepository {
    private ClientDao mClientDao;

    public ClientRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        mClientDao = db.userDao();
    }

    public boolean insert(@NonNull Client client) {
        try {
            Client clientFromDb = new getClientAsyncTask(mClientDao).execute(client.email).get();
            if(clientFromDb != null) {
                return false;
            }
            new insertAsyncTask(mClientDao).execute(client).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }
    private static class insertAsyncTask extends AsyncTask<Client, Void, Void> {

        private ClientDao mAsyncTaskDao;

        insertAsyncTask(ClientDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Client... params) {
            mAsyncTaskDao.insertAll(params[0]);
            return null;
        }
    }

    public Client getClient(String email) {
        try {
            return new getClientAsyncTask(mClientDao).execute(email).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
    private static class getClientAsyncTask extends AsyncTask<String, Void, Client> {

        private final ClientDao mAsyncTaskDao;

        getClientAsyncTask(ClientDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Client doInBackground(final String... params) {
            return mAsyncTaskDao.findByEmail(params[0]);
        }
    }

    public void updateClientInfo(String name, String email, String phoneNumber) {
        try {
            new updateClientInfoAsyncTask(mClientDao).execute(name,email,phoneNumber).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private static class updateClientInfoAsyncTask extends AsyncTask<String, Void, Void> {

        private ClientDao mAsyncTaskDao;

        updateClientInfoAsyncTask(ClientDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final String... params) {
            mAsyncTaskDao.updateClientInfo(params[0],params[1],params[2]);
            return null;
        }
    }
}
