package com.example.aakash.firapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
/**
 * Created by hp on 4/4/2016.
 */
public class history extends AppCompatActivity {

    private Button create;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);
        historyView();
    }
    private void historyView() {

     /*
      It will give the history of registred FIR to complainer like date of Lodge FIR, FIR ID of Specific Lodged FIR,
      and Crime Type of Lodged FIR.
    */
    }
}
