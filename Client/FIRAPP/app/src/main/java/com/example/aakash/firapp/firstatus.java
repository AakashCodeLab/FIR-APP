package com.example.aakash.firapp;

/**
 * Created by hp on 3/30/2016.
 */

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class firstatus extends Activity {

    EditText txtName;
    EditText txtMo;
    EditText txtFirid;
    EditText txtCrType;
    EditText txtDate;
    EditText txtCrDesc;
    EditText txtadd;
    EditText txtStn;
    EditText txtPname;
    EditText txtStatus;
    EditText txtComment;
    Button btnStatus;
    String pid;

    private ProgressDialog pDialog;

    // JSON parser class
    JSONParser jsonParser = new JSONParser();

    // single fir url
    private static final String url_fir_detials = "http://10.0.2.2/FIRAPP/library/status.php";

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_fir = "fir";
    private static final String TAG_FID = "fid";
    private static final String TAG_NAME = "name";
    private static final String TAG_MO = "mobile";
    private static final String TAG_ADD = "add";
    private static final String TAG_STN = "stn";
    private static final String TAG_PNAME = "Pname";
    private static final String TAG_STATUS = "status";
    private static final String TAG_COMMENT = "comment";
    private static final String TAG_DESCRIPTION = "description";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firstatus);
        loginValidate();
    }

    private void loginValidate() {


        // save button
        btnStatus = (Button) findViewById(R.id.btnstatus);

        btnStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                login(v);


                //check connectivity
                //   if (!isOnline(activity_sign_in.this)) {
                //Toast.makeText(activity_sign_in.this, "No network connection", Toast.LENGTH_LONG).show();
                // return;
                // }

                // new loginAccess().execute();
            }

            //code to check online details
            //  private boolean isOnline(Context mContext) {
            //  ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
            //  NetworkInfo netInfo = cm.getActiveNetworkInfo();
            // if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            // return true;
            // }
            // return false;
            //  }
            //Close code that check online details
        });
        //Close log in

    }




        public void login(View view){
        txtName = (EditText) findViewById(R.id.etUserName);
        txtFirid = (EditText) findViewById(R.id.etFirId);
        String name = txtName.getText().toString();
        String fid = txtFirid.getText().toString();

        new GetFirDetails().execute(name,fid);
    }

    class GetFirDetails extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(firstatus.this);
            pDialog.setMessage("Loading Fir Details. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }




        /**
         * Getting product details in background thread
         * */
        protected String doInBackground(String... params) {
            String paramname = params[0];
            String paramfid = params[1];

           final String name = paramname;
          final   String fid = paramfid;


            // updating UI from Background Thread
            runOnUiThread(new Runnable() {
                public void run() {
                    // Check for success tag
                    int success;
                    try {
                        // Building Parameters
                        List<NameValuePair> params = new ArrayList<NameValuePair>();
                        params.add(new BasicNameValuePair("fid", fid));
                        params.add(new BasicNameValuePair("name", name));

                        // getting product details by making HTTP request
                        // Note that product details url will use GET request
                        JSONObject json = jsonParser.makeHttpRequest(
                                url_fir_detials, "GET", params);

                        // check your log for json response
                        Log.d("Single fir Details", json.toString());

                        // json success tag
                        success = json.getInt(TAG_SUCCESS);
                        if (success == 1) {

                            // successfully received product details
                            JSONArray statusObj = json
                                    .getJSONArray(TAG_fir); // JSON Array

                            // get first product object from JSON Array
                            JSONObject status = statusObj.getJSONObject(0);

                            // product with this pid found
                            // Edit Text
                        //   txtName = (EditText) findViewById(R.id.inputName);
                          //  txtPrice = (EditText) findViewById(R.id.inputPrice);
                          //  txtDesc = (EditText) findViewById(R.id.inputDesc);

                            // display product data in EditText
                            txtName.setText(status.getString(TAG_NAME));
                           // txtPrice.setText(product.getString(TAG_PRICE));
                           // txtDesc.setText(product.getString(TAG_DESCRIPTION));

                        }else{
                            // product with pid not found
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });

            return null;
        }


        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once got all details
            pDialog.dismiss();
        }
    }









}
