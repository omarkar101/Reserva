package lb.edu.aub.cmps297.reserva.database.Repositories;

import android.app.Application;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import lb.edu.aub.cmps297.reserva.database.AppDatabase;
import lb.edu.aub.cmps297.reserva.database.DAO.LoggedInUserDao;
import lb.edu.aub.cmps297.reserva.database.DAO.ReservationDao;
import lb.edu.aub.cmps297.reserva.database.Entities.LoggedInUser;
import lb.edu.aub.cmps297.reserva.database.Entities.Reservation;

public class ReservationRepository {

    private ReservationDao mReservationDao;

    public ReservationRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        mReservationDao = db.reservationDao();
    }

    public ArrayList<Reservation> getRestaurantReservations(String restaurant_email) {
        try {
            return new getRestaurantReservationsAsyncTask(mReservationDao).execute(restaurant_email).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
    private static class getRestaurantReservationsAsyncTask extends AsyncTask<String, Void, ArrayList<Reservation>> {

        private ReservationDao mAsyncTaskDao;

        getRestaurantReservationsAsyncTask(ReservationDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected ArrayList<Reservation> doInBackground(final String... params) {
            return new ArrayList<>(mAsyncTaskDao.getRestaurantReservations(params[0]));
        }
    }

    public ArrayList<Reservation> getClientReservations(String client_email) {
        try {
            return new getClientReservationsAsyncTask(mReservationDao).execute(client_email).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    private static class getClientReservationsAsyncTask extends AsyncTask<String, Void, ArrayList<Reservation>> {

        private ReservationDao mAsyncTaskDao;

        getClientReservationsAsyncTask(ReservationDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected ArrayList<Reservation> doInBackground(final String... params) {
            return new ArrayList<>(mAsyncTaskDao.getClientReservations(params[0]));
        }
    }

    public void updateReservation(String client_email, String restaurant_email, String status) {
        new updateReservationAsyncTask(mReservationDao).execute(client_email, restaurant_email, status);
    }

    private static class updateReservationAsyncTask extends AsyncTask<String, Void, Void> {

        private ReservationDao mAsyncTaskDao;

        updateReservationAsyncTask(ReservationDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final String... params) {
            mAsyncTaskDao.updateReservation(params[0], params[1], params[2]);
            return null;
        }
    }

    public void insert(String client_email, String restaurant_email, String seats_requested, String status) {
        try {
            new insertAsyncTask(mReservationDao).execute(client_email, restaurant_email, seats_requested, status).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static class insertAsyncTask extends AsyncTask<String, Void, Void> {

        private ReservationDao mAsyncTaskDao;

        insertAsyncTask(ReservationDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final String... params) {
            mAsyncTaskDao.insert(params[0], params[1], params[2], params[3]);
            return null;
        }
    }

}
