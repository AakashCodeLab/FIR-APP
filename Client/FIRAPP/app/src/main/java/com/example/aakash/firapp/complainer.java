package com.example.aakash.firapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by hp on 3/8/2016.
 */


public class complainer extends AppCompatActivity {
    TextView txtName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.complainer);

        Intent in = getIntent();
        String username = in.getStringExtra("name");
        String pass = in.getStringExtra("pass");

        txtName = (TextView) findViewById(R.id.textView7);
        txtName.setText(getIntent().getStringExtra("name"));
    }

    public void OnClick(View view) {
        if (R.id.btnLodge == view.getId()) {
            Intent intent1 = new Intent(this, addfir.class);
            startActivity(intent1);
        } else {
            if (R.id.btnstatus == view.getId()) {
                Intent intent2 = new Intent(this, firstatus.class);
                startActivity(intent2);
            }

            else {
                if (R.id.btnFeedback == view.getId()) {
                    Intent intent3 = new Intent(this, feedback.class);
                    startActivity(intent3);
                }


            else {
                Intent intent = new Intent(this, complainer.class);
                startActivity(intent);
                txtName = (TextView) findViewById(R.id.textView7);
                txtName.setText(getIntent().getStringExtra("name"));
            }

        }

    }
    }
}
