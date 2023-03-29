package com.example.kinjalkumaridhimmarmonikakumarisingh_comp304sec002_lab4_ex1;

import android.app.Application;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class NurseViewModel extends AndroidViewModel {
    private NurseRepository nurseRepository;
    private LiveData<Integer> insertResult;
    private LiveData<List<Nurse>> allnurse;
    public NurseViewModel(Application application) {
        super((application));
        nurseRepository = new NurseRepository(application);
        insertResult = nurseRepository.getInsertResult();
        allnurse = nurseRepository.getAllNurse();
    }

    public LiveData<Nurse> getByCustomerID(int customerID) {
        return nurseRepository.getByNurseID(customerID);
    }

    public void insert(Nurse nurse) {nurseRepository.insert(nurse);
    }



    public LiveData<Integer> getInsertResult() {
        return insertResult;
    }


    public void update(Nurse nurse){nurseRepository.update(nurse);
    }
    public LiveData<List<Nurse>> getAllCustomers() {
        return allnurse;
    }

}
