package com.example.aakash.firapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class addfir extends AppCompatActivity {
   // private EditText etUserName;
//    private EditText etEmail;
  //  private EditText etPass;
   // private EditText mo;
   // private EditText add;
    private EditText etUserName;
    private EditText crType;
    private EditText firno;
    private EditText PoliceStn;
    private EditText mo;
    private EditText add;
    private EditText Desc;

    private Button lodge;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fir);
        registerViews();
    }
    private void registerViews(){

        etUserName = (EditText) findViewById(R.id.etUserName);
        // TextWatcher would let us check validation error on the fly
        etUserName.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                Validation.hasText(etUserName);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });

        crType = (EditText) findViewById(R.id.crType);
        // TextWatcher would let us check validation error on the fly
        crType.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                Validation.hasText(crType);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });



        firno= (EditText) findViewById(R.id.firid);
        firno.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                Validation.isFirid(firno, true);
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });





        mo = (EditText) findViewById(R.id.mo);
        mo.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                Validation.isPhoneNumber(mo, true);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });


        add= (EditText) findViewById(R.id.add);
        add.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                Validation.hasText(add);
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        PoliceStn= (EditText) findViewById(R.id.PoliceStn);
        PoliceStn.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                Validation.hasText(PoliceStn);
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });



        Desc= (EditText) findViewById(R.id.Desc);
        Desc.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                Validation.hasText(Desc);
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });





       lodge = (Button) findViewById(R.id.btnLodge);

        etUserName = (EditText) findViewById(R.id.etUserName);
        crType = (EditText) findViewById(R.id.crType);
        firno = (EditText) findViewById(R.id.firid);
        add=(EditText) findViewById(R.id.add);
        PoliceStn=(EditText) findViewById(R.id.PoliceStn);
        Desc=(EditText) findViewById(R.id.Desc);

        mo=(EditText) findViewById(R.id.mo);
        //lodge = (Button) findViewById(R.id.btnLodge);




        lodge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (checkValidation())
                    insert(v);
                else
                    Toast.makeText(addfir.this, "Form contains error", Toast.LENGTH_LONG).show();


                Log.d("success", "working button");


            }
        });
    }

    public void insert(View view){
        String name = etUserName.getText().toString();
       // String mail = etEmail.getText().toString();
       // String pass = etPass.getText().toString();
       // String mobile = mo.getText().toString();
       // String name = etUserName.getText().toString();
        String crime = crType.getText().toString();
        String firid = firno.getText().toString();
        String description = Desc.getText().toString();
        String mobile = mo.getText().toString();
        String address = add.getText().toString();
        String Stn = PoliceStn.getText().toString();
        insertToDatabase(name,crime,firid,description,mobile,address,Stn);

    }





    private void insertToDatabase(String name,String crime,String firid,String description,String mobile, String address,String Stn){
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {

            protected String doInBackground(String... params) {


                String paramUsername = params[0];
                String paramCrime = params[1];
                String paramfirno = params[6];
                String paramDesc = params[2];
                String paramMo = params[3];
                String paramAdd = params[4];
                String paramStn = params[5];


                String name = paramUsername;
                String crime = paramCrime;
                String firid = paramfirno;
                String description = paramDesc;
                String mo = paramMo;
                String add = paramAdd;
                String Stn = paramStn;



                // String mail = etEmail.getText().toString();
                //String pass = etPass.getText().toString();
                //String mo = mo.getText().toString();
                //String add = add.getText().toString();

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("name", name));
                nameValuePairs.add(new BasicNameValuePair("crime", crime));
                nameValuePairs.add(new BasicNameValuePair("firid", firid));
                nameValuePairs.add(new BasicNameValuePair("description", description));
                nameValuePairs.add(new BasicNameValuePair("mo", mo));
                nameValuePairs.add(new BasicNameValuePair("add", add));
                nameValuePairs.add(new BasicNameValuePair("Stn", Stn));
                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost("http://10.0.2.2/FIRAPP/library/lodge.php");
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    HttpResponse response = httpClient.execute(httpPost);
                    HttpEntity entity = response.getEntity();

                } catch (ClientProtocolException e) {

                } catch (IOException e) {

                }
                Intent i = new Intent(getApplicationContext(), addfir.class);
                startActivity(i);

                return "Your FIR Is Successfuly registred.";
            }
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                //TextView textViewResult = (TextView) findViewById(R.id.textViewResult);

                // textViewResult.setText("Inserted");

            }
        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(name, crime,firid, description, mobile, address,Stn);
        //sendPostReqAsyncTask.execute(name, crime, description, mo, add,Stn);

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

        if (!Validation.hasText(etUserName)) ret = false;
        if (!Validation.hasText(crType)) ret = false;
        if (!Validation.hasText(Desc)) ret = false;
        if (!Validation.hasText(PoliceStn)) ret = false;
       // if (!Validation.isEmailAddress(etEmail, true)) ret = false;
        if (!Validation.isPhoneNumber(mo, false)) ret = false;

        return ret;
    }

}