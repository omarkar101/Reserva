package lb.edu.aub.cmps297.reserva.database.Repositories;

import android.app.Application;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import lb.edu.aub.cmps297.reserva.database.AppDatabase;
import lb.edu.aub.cmps297.reserva.database.DAO.FavoriteRestaurantsByClientsDao;
import lb.edu.aub.cmps297.reserva.database.Entities.Restaurant;

public class FavoriteRestaurantsByClientsRepository {
    private FavoriteRestaurantsByClientsDao mFavoriteRestaurantsByClientsDao;

    public FavoriteRestaurantsByClientsRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        mFavoriteRestaurantsByClientsDao = db.favoriteRestaurantsByClientsDao();
    }

    public ArrayList<Restaurant> getAllFavoriteRestaurants(String client_email) {
        try {
            return new ArrayList<>(Arrays.asList(new getAllFavoriteRestaurantsAsyncTask(mFavoriteRestaurantsByClientsDao).execute(client_email).get()));
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    private static class getAllFavoriteRestaurantsAsyncTask extends AsyncTask<String, Void, Restaurant[]> {

        private FavoriteRestaurantsByClientsDao mAsyncTaskDao;

        getAllFavoriteRestaurantsAsyncTask(FavoriteRestaurantsByClientsDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Restaurant[] doInBackground(final String... params) {
            return mAsyncTaskDao.getAllFavoriteRestaurants(params[0]);
        }
    }

    public void removeFavoriteRestaurant(String client_email, String restaurant_email) {
        try {
            new removeFavoriteRestaurantAsyncTask(mFavoriteRestaurantsByClientsDao).execute(client_email, restaurant_email).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static class removeFavoriteRestaurantAsyncTask extends AsyncTask<String, Void, Void> {

        private FavoriteRestaurantsByClientsDao mAsyncTaskDao;

        removeFavoriteRestaurantAsyncTask(FavoriteRestaurantsByClientsDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final String... params) {
            mAsyncTaskDao.removeFavoriteRestaurant(params[0], params[1]);
            return null;
        }
    }


    public void addFavoriteRestaurant(String client_email, String restaurant_email) {
        try {
            new addFavoriteRestaurantAsyncTask(mFavoriteRestaurantsByClientsDao).execute(client_email, restaurant_email).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static class addFavoriteRestaurantAsyncTask extends AsyncTask<String, Void, Void> {

        private FavoriteRestaurantsByClientsDao mAsyncTaskDao;

        addFavoriteRestaurantAsyncTask(FavoriteRestaurantsByClientsDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final String... params) {
            mAsyncTaskDao.addFavoriteRestaurant(params[0], params[1]);
            return null;
        }
    }
}
