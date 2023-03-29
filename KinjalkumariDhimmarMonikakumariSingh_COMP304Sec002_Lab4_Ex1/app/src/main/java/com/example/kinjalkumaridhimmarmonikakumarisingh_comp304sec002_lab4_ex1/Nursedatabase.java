package com.example.kinjalkumaridhimmarmonikakumarisingh_comp304sec002_lab4_ex1;
import android.content.Context;
import android.os.AsyncTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
@Database(entities = {Nurse.class}, version = 1, exportSchema = false)
public abstract class Nursedatabase extends RoomDatabase {
    public abstract NurseDao nurseDao();

    private static volatile Nursedatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static Nursedatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (Nursedatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    Nursedatabase.class, "customer_database")
                            .addCallback(roomCallBack)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            // If you want to keep data through app restarts, comment out the following block
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more customer, just add them.
                NurseDao dao = INSTANCE.nurseDao();
                dao.deleteAll();
                new PopulateDbAsyncTasks(INSTANCE).execute();
            });

        }
    };

    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            super.onCreate(db);
            new PopulateDbAsyncTasks(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsyncTasks extends AsyncTask<Void, Void, Void>{
        private NurseDao nurseDao;

        private PopulateDbAsyncTasks(Nursedatabase db){
            nurseDao = db.nurseDao();
        }

        @Override
        protected Void doInBackground(Void... voids){
            Nurse nurse = new Nurse("Dhavalbhai", "Patel", "Dhaval", "12345");
            nurse.setNurseID(301240436);
            nurseDao.insert(nurse);
            return null;
        }
    }

}
