package com.example.aakash.firapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hp on 4/3/2016.
 */
public class feedback extends AppCompatActivity {

    private EditText Feedback;
    private EditText firid;
    private Button btnfeedback;
    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback);
        feedbackview();
    }
    private void feedbackview() {
        firid = (EditText) findViewById(R.id.fid);
        firid.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                Validation.isFirid(firid, true);
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });
        Feedback = (EditText) findViewById(R.id.feedback);
        // TextWatcher would let us check validation error on the fly
        Feedback.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                Validation.hasText(Feedback);
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });



        btnfeedback = (Button) findViewById(R.id.btnFeedback);

         Feedback= (EditText) findViewById(R.id.feedback);
        firid = (EditText) findViewById(R.id.fid);

        btnfeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (checkValidation())
                    insert(v);
                else
                    Toast.makeText(feedback.this, "Form contains error", Toast.LENGTH_LONG).show();


                Log.d("success", "working button");


            }
        });
        }


    public void insert(View view) {
        String feedback = Feedback.getText().toString();
        String fid = firid.getText().toString();
        insertToDatabase(feedback, fid);
    }
    private void insertToDatabase(String feedback,String fid) {
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {

            protected String doInBackground(String... params) {


                String paramFeedback = params[0];
                String paramfirno = params[1];


                String feedback = paramFeedback;
                String fid = paramfirno;

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("feedback", feedback));
                nameValuePairs.add(new BasicNameValuePair("firNo", fid));


                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost("http://10.0.2.2/FIRAPP/library/feedback.php");
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    HttpResponse response = httpClient.execute(httpPost);
                    HttpEntity entity = response.getEntity();

                } catch (ClientProtocolException e) {

                } catch (IOException e) {

                }
                Intent i = new Intent(getApplicationContext(), feedback.class);
                startActivity(i);

                return "Thanks For Your Support.";
            }


            protected void onPostExecute(String result) {
                super.onPostExecute(result);

                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                //TextView textViewResult = (TextView) findViewById(R.id.textViewResult);

                // textViewResult.setText("Inserted");

            }
        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(feedback, fid);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private boolean checkValidation() {
        boolean ret = true;

        if (!Validation.hasText(Feedback)) ret = false;
        if (!Validation.isFirid(firid, true)) ret = false;

        return ret;
    }

}
