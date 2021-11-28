package lb.edu.aub.cmps297.reserva.database.Repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;

import java.util.concurrent.ExecutionException;

import lb.edu.aub.cmps297.reserva.database.AppDatabase;
import lb.edu.aub.cmps297.reserva.database.DAO.LoggedInUserDao;
import lb.edu.aub.cmps297.reserva.database.Entities.LoggedInUser;

public class LoggedInUserRepository {
    private LoggedInUserDao mLoggedInUserDao;

    public LoggedInUserRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        mLoggedInUserDao = db.loggedInUserDao();
    }

    public boolean insert(@NonNull LoggedInUser loggedInUser) {

        try {
            new deleteAllAsyncTask(mLoggedInUserDao).execute().get();
            new insertAsyncTask(mLoggedInUserDao).execute(loggedInUser).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }
    private static class insertAsyncTask extends AsyncTask<LoggedInUser, Void, Void> {

        private LoggedInUserDao mAsyncTaskDao;

        insertAsyncTask(LoggedInUserDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final LoggedInUser... params) {
            mAsyncTaskDao.insertAll(params[0]);
            return null;
        }
    }

    public LoggedInUser getUser() {
        try {
            return new getUserAsyncTask(mLoggedInUserDao).execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static class getUserAsyncTask extends AsyncTask<Void, Void, LoggedInUser> {

        private LoggedInUserDao mAsyncTaskDao;

        getUserAsyncTask(LoggedInUserDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected LoggedInUser doInBackground(final Void... params) {
            LoggedInUser[] loggedInUsers = mAsyncTaskDao.getUser();
            if (loggedInUsers.length == 0){
                return null;
            }
            return loggedInUsers[0];
        }
    }

    public void deleteAll() {
        try {
            new deleteAllAsyncTask(mLoggedInUserDao).execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static class deleteAllAsyncTask extends AsyncTask<Void, Void, Void> {

        private final LoggedInUserDao mAsyncTaskDao;

        deleteAllAsyncTask(LoggedInUserDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Void ...voids) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }



}
