package lb.edu.aub.cmps297.reserva.database.Repositories;

import android.app.Application;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.ExecutionException;

import lb.edu.aub.cmps297.reserva.database.AppDatabase;
import lb.edu.aub.cmps297.reserva.database.DAO.ClientDao;
import lb.edu.aub.cmps297.reserva.database.DAO.RestaurantDao;
import lb.edu.aub.cmps297.reserva.database.Entities.Client;
import lb.edu.aub.cmps297.reserva.database.Entities.Restaurant;

public class RestaurantRepository {
    private RestaurantDao mRestaurantDao;

    public RestaurantRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        mRestaurantDao = db.restaurantDao();
    }

    public List<Restaurant> getAll() {
        try {
            return new getAllAsyncTask(mRestaurantDao).execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
    private static class getAllAsyncTask extends AsyncTask<Void, Void, List<Restaurant>> {

        private RestaurantDao mAsyncTaskDao;

        getAllAsyncTask(RestaurantDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected List<Restaurant> doInBackground(final Void... params) {
            return mAsyncTaskDao.getAll();
        }
    }


    public boolean insert(Restaurant restaurant) {
        Restaurant restaurantFromDb = null;
        try {
            restaurantFromDb = new getRestaurantAsyncTask(mRestaurantDao).execute(restaurant.email).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(restaurantFromDb != null) {
            return false;
        }
        try {
            new insertAsyncTask(mRestaurantDao).execute(restaurant).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }
    private static class insertAsyncTask extends AsyncTask<Restaurant, Void, Void> {

        private RestaurantDao mAsyncTaskDao;

        insertAsyncTask(RestaurantDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Restaurant... params) {
            mAsyncTaskDao.insertAll(params[0]);
            return null;
        }
    }

    public Restaurant getRestaurant(String email) {
        try {
            return new getRestaurantAsyncTask(mRestaurantDao).execute(email).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
    private static class getRestaurantAsyncTask extends AsyncTask<String, Void, Restaurant> {

        private RestaurantDao mAsyncTaskDao;

        getRestaurantAsyncTask(RestaurantDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Restaurant doInBackground(final String... params) {
            return mAsyncTaskDao.findByEmail(params[0]);
        }
    }

    public void updateRestaurantInfo(String name, String email, String phoneNumber, String Location, String Description) {
        try {
            new updateRestaurantInfoAsyncTask(mRestaurantDao).execute(name,email,phoneNumber,Location,Description).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private static class updateRestaurantInfoAsyncTask extends AsyncTask<String, Void, Void> {

        private RestaurantDao mAsyncTaskDao;

        updateRestaurantInfoAsyncTask(RestaurantDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final String... params) {
            mAsyncTaskDao.updateRestaurantInfo(params[0],params[1],params[2],params[3],params[4]);
            return null;
        }
    }

    public void updateRestaurantSeatsNumber(String email,int seatsMaxCapacity) {
        try {
            new updateRestaurantSeatsNumberAsyncTask(mRestaurantDao).execute(email, Integer.valueOf(seatsMaxCapacity).toString()).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private static class updateRestaurantSeatsNumberAsyncTask extends AsyncTask<String, Void, Void> {

        private RestaurantDao mAsyncTaskDao;

        updateRestaurantSeatsNumberAsyncTask(RestaurantDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final String... params) {
            mAsyncTaskDao.updateRestaurantSeatsNumber(params[0],Integer.parseInt(params[1]));
            return null;
        }
    }

    public void updateRestaurantSeatsReserved(String email, String seats_reserved) {
        try {
            new updateRestaurantSeatsReservedAsyncTask(mRestaurantDao).execute(email, seats_reserved).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void updateRestaurantProfileImageUsingUri(String email,String profileImagePath) {
        try {
            new updateRestaurantProfileImageUsingUriAsyncTask(mRestaurantDao).execute(email, profileImagePath).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private static class updateRestaurantProfileImageUsingUriAsyncTask extends AsyncTask<String, Void, Void> {

        private RestaurantDao mAsyncTaskDao;

        updateRestaurantProfileImageUsingUriAsyncTask(RestaurantDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final String... params) {
            mAsyncTaskDao.updateRestaurantProfileImageUsingUri(params[0], params[1]);
            return null;
        }
    }

    public void updateRestaurantMenuImageUsingUri(String email,String menuImagePath) {
        try {
            new updateRestaurantMenuImageUsingUriAsyncTask(mRestaurantDao).execute(email, menuImagePath).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static class updateRestaurantSeatsReservedAsyncTask extends AsyncTask<String, Void, Void> {

        private RestaurantDao mAsyncTaskDao;

        updateRestaurantSeatsReservedAsyncTask(RestaurantDao dao) {
            mAsyncTaskDao = dao;
        }
        @Override
        protected Void doInBackground(final String... params) {
            mAsyncTaskDao.updateRestaurantSeatsReserved(params[0], Integer.parseInt(params[1]));
            return null;
        }
    }

    private static class updateRestaurantMenuImageUsingUriAsyncTask extends AsyncTask<String, Void, Void> {

        private RestaurantDao mAsyncTaskDao;

        updateRestaurantMenuImageUsingUriAsyncTask(RestaurantDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final String... params) {
            mAsyncTaskDao.updateRestaurantMenuImageUsingUri(params[0], params[1]);
            return null;
        }
    }
}
