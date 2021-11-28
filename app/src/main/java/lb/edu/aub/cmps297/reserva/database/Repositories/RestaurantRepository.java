package lb.edu.aub.cmps297.reserva.database.Repositories;

import android.app.Application;
import android.os.AsyncTask;

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
}
