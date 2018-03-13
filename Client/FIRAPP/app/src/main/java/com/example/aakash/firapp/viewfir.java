package com.example.aakash.firapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
/**
 * Created by hp on 4/2/2016.
 */
public class viewfir extends Fragment {
    public viewfir() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.viewfir, container, false);
    }
/*
    public void btnViewFir(View v){
    Police Inspector will get to know about how many  complainer have registered FIR through this app
     along with the FIR details through list view on android screen.
}
    }
    */
}


