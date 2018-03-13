package com.example.aakash.firapp;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
/**
 * Created by hp on 4/1/2016.
 */
public class closefir extends Fragment {

    public closefir() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.close, container, false);
    }
    /*public void btnClose(View v){
        Supritendent will close corresponding FIR after having successfull work on it by the department.
        It will update the status of FIR.

    }*/
}

