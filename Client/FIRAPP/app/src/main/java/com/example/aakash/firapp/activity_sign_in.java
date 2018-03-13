package com.example.aakash.firapp;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class activity_sign_in extends AppCompatActivity {

   private Button signin;
    private EditText etUserName, etPass;
    Spinner s9;
    private ProgressDialog pDialog;
    int flag = 0;
    JSONParser jsonParser = new JSONParser();
    public  String url = "https://firsite.000webhostapp.com/FIRAPP/functions.php";
    //public  String url = "http://10.0.2.2/FIRAPP/library/functions.php";
    public  final String TAG_SUCCESS = "success";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads().detectDiskWrites().detectNetwork()
                .penaltyLog().build());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_screen);
        loginValidate();
    }

    private void loginValidate(){

        etUserName = (EditText) findViewById(R.id.etUserName);
        // TextWatcher would let us check validation error on the fly
        etUserName.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                Validation.hasText(etUserName);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });


        etPass= (EditText) findViewById(R.id.etPass);
        etPass.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                Validation.isPass(etPass, true);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });



        //Get all data and log in

        etUserName = (EditText) findViewById(R.id.etUserName);
        s9=(Spinner)findViewById(R.id.UserType);


        etPass = (EditText) findViewById(R.id.etPass);
        signin = (Button) findViewById(R.id.btnSignIn);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final  String user=s9.getSelectedItem().toString();
                if (checkValidation()) {
                    if (s9.getSelectedItem().toString().equals("Complainer"))
                        login(v);
                    else if (s9.getSelectedItem().toString().equals("Police Inspector")) {
                        Intent is = new Intent(getApplicationContext(), police.class);
                        startActivity(is);
                    } else if(s9.getSelectedItem().toString().equals("Superintendent")) {
                        Intent is = new Intent(getApplicationContext(), superintendent.class);
                        startActivity(is);
                    }
                    else
                        Toast.makeText(activity_sign_in.this, " Wrong", Toast.LENGTH_LONG).show();

                }else
                    Toast.makeText(activity_sign_in.this, "Something is Wrong", Toast.LENGTH_LONG).show();


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


    public void login(View view) {

        String name = etUserName.getText().toString();
        String pass = etPass.getText().toString();
        new loginAccess().execute(name,pass);

    }




   //private void Sign(String name, String pass) {
       class loginAccess extends AsyncTask<String, String,String> {


           protected void onPreExecute() {

               super.onPreExecute();
               pDialog = new ProgressDialog(activity_sign_in.this);
               pDialog.setMessage("Sig in...");
               pDialog.setIndeterminate(false);
               pDialog.setCancelable(true);
               pDialog.show();
           }

           @Override
           protected String doInBackground(String... args) {
               // Toast.makeText(activity_sign_in.this, "Running", Toast.LENGTH_LONG).show();
               String paramUsername = args[0];
               String paramPass = args[1];

               String name = paramUsername;
               String pass = paramPass;


               List<NameValuePair> params = new ArrayList<NameValuePair>();
              // String user=etUserName.getText().toString();
              //
              // String pwd=etPass.getText().toString();

               params.add(new BasicNameValuePair("name", name));
               params.add(new BasicNameValuePair("pass", pass));


               JSONObject json = jsonParser.makeHttpRequest(url, "POST", params);

              Log.d("Create Response", json.toString());



               try {
                   int success = json.getInt(TAG_SUCCESS);
                   if (success == 1) {
                       flag = 0;
                     //  Toast.makeText(activity_sign_in.this, "Success", Toast.LENGTH_LONG).show();
                       Intent i = new Intent(getApplicationContext(), complainer.class);
                     //  Toast.makeText(activity_sign_in.this, "Login Success", Toast.LENGTH_LONG).show();
                      i.putExtra("name", name);
                       i.putExtra("pass", pass);
                      startActivity(i);
                       finish();
                   } else {
                       // failed to Sign in
                       flag = 1;
                   }
               } catch (JSONException e) {
                   e.printStackTrace();
               }
               return null;
           }


           protected void onPostExecute(String file_url) {
               pDialog.dismiss();
               if (flag == 1)
                   Toast.makeText(activity_sign_in.this, "Please Enter Correct Information", Toast.LENGTH_LONG).show();

           }

       }

   // }




    private boolean checkValidation() {
        boolean ret = true;

        if (!Validation.hasText(etUserName)) ret = false;
        if (!Validation.isPass(etPass, true)) ret = false;

        return ret;
    }
}






































/*package com.example.aakash.firapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;








public class activity_sign_in extends AppCompatActivity {


    private Button signin;
    private String UserType;
    private EditText UserName,password,Type;
    private ProgressDialog pDialog;
    int flag=0;
    JSONParser jsonParser = new JSONParser();
    private static String url = "http://10.0.2.2/FIRSITE/library/functions.php";
    private static final String TAG_SUCCESS = "success";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads().detectDiskWrites().detectNetwork()
                .penaltyLog().build());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_screen);

        signin=(Button)findViewById(R.id.btnSingIn);
        password=(EditText)findViewById(R.id.etPass);
        UserName=(EditText)findViewById(R.id.etUserName);
       final Spinner utype = (Spinner) findViewById(R.id.UserType);
      String UserType = utype.getSelectedItem().toString();




        signin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {

                //Check all fields
                if(UserName.equals(""))
                {
                    Toast.makeText(activity_sign_in.this,"Please Enter UserName", Toast.LENGTH_LONG).show();
                    return;
                }
                if(password.equals(""))
                {
                    Toast.makeText(activity_sign_in.this,"Please Enter minimum 4 letters in password", Toast.LENGTH_LONG).show();
                    return;
                }

                /*if(utype.)
                {
                    Toast.makeText(activity_sign_in.this,"Please Select User Type", Toast.LENGTH_LONG).show();
                    return;
                }*/
                //check connectivity
        /*      if(!isOnline(activity_sign_in.this))
                {
                    Toast.makeText(activity_sign_in.this,"No network connection", Toast.LENGTH_LONG).show();
                    return;
                }

              //from login.java
                new loginAccess().execute();
            }

            //code to check online details
          private boolean isOnline(Context mContext) {
                ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo netInfo = cm.getActiveNetworkInfo();
                if (netInfo != null && netInfo.isConnectedOrConnecting())
                {
                    return true;
                }
                return false;
            }
    //       //Close code that check online details
        });
        //Close log in
    }


  /* class loginAccess extends AsyncTask<String, String, String> {

        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(activity_sign_in.this);
            pDialog.setMessage("Sig in...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
        @Override
        protected String doInBackground(String... params) {

            String paramUsername = params[0];
            String paramPass = params[1];
            String paramType = params[2];


            String UserName = paramUsername;
            String password = paramPass;
            String UserType = paramType;






        /*    List<NameValuePair>param = new ArrayList<NameValuePair>();
           /* String user=UserName.getText().toString();
            String pwd=password.getText().toString();
            String h=hint.getText().toString();
            String id=email_id.getText().toString();  */

      /*      param.add(new BasicNameValuePair("UserName", UserName));
            param.add(new BasicNameValuePair("password", password));
                    param.add(new BasicNameValuePair("UserType", UserType));

            JSONObject json = jsonParser.makeHttpRequest(url,"POST", param);

           Log.d("Create Response", json.toString());

           try {
                int success = json.getInt(TAG_SUCCESS);
                if (success == 1)
                {
                    flag=0;
                    Intent i = new Intent(getApplicationContext(),complainer.class);
                    i.putExtra("UserName",UserName);
                    i.putExtra("password",password);
                    startActivity(i);
                    finish();
                }
                else
                {
                    // failed to Sign in
                    flag=1;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
      /*  protected void onPostExecute(String file_url) {
            pDialog.dismiss();
            if(flag==1)
                Toast.makeText(activity_sign_in.this, "Please Enter Correct informations", Toast.LENGTH_LONG).show();

        }





   }
}*/
