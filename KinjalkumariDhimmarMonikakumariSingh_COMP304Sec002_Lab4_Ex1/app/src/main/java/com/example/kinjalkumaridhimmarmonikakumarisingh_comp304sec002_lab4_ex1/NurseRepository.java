package com.example.kinjalkumaridhimmarmonikakumarisingh_comp304sec002_lab4_ex1;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
public class NurseRepository {
    public NurseDao nurseDao;
    private MutableLiveData<Integer> insertResult = new MutableLiveData<>();
    private LiveData<List<Nurse>> allNurse;
    public NurseRepository(Application application) {
        Nursedatabase db = Nursedatabase.getDatabase(application);
        nurseDao = db.nurseDao();
        allNurse = nurseDao.getAllNurse();
    }

    public LiveData<List<Nurse>> getAllNurse() {
        return allNurse;
    }

    public void update(Nurse nurse){
        new UpdateTestAsyncTask(nurseDao).execute(nurse);
    }

    public void insert(Nurse nurse) {
        insertAsync(nurse);
    }

    private void insertAsync(final Nurse nurse) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    nurseDao.insert(nurse);
                    insertResult.postValue(1);
                } catch (Exception e) {
                    insertResult.postValue(0);
                }
            }
        }).start();
    }
    public LiveData<Integer> getInsertResult() {
        return insertResult;
    }

    public LiveData<Nurse> getByNurseID(int nurseID) {
        return nurseDao.getByNurseID(nurseID);
    }
    private static class UpdateTestAsyncTask extends AsyncTask<Nurse, Void, Void> {
        private NurseDao nurseDao;

        private UpdateTestAsyncTask(NurseDao testDao){
            this.nurseDao = testDao;
        }

        @Override
        protected Void doInBackground(Nurse... tests){
            nurseDao.update(tests[0]);
            return null;
        }

        public void execute(Nurse nurse) {
        }

    }


}
