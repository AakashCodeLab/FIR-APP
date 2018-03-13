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

public class activity_sign_up extends AppCompatActivity {
    private EditText etUserName;
    private EditText etEmail;
    private EditText etPass;
    private EditText mo;
    private EditText add;
    private Button signup;
            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_sign_up_screen);
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

        etEmail = (EditText) findViewById(R.id.etEmail);
        etEmail.addTextChangedListener(new TextWatcher() {
            // after every change has been made to this editText, we would like to check validity
            public void afterTextChanged(Editable s) {
                Validation.isEmailAddress(etEmail, true);
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });

        mo = (EditText) findViewById(R.id.mo);
        mo.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                Validation.isPhoneNumber(mo, true);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });
        etPass= (EditText) findViewById(R.id.etPass);
        etPass.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                Validation.isPass(etPass,true);
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




        etUserName = (EditText) findViewById(R.id.etUserName);
        etEmail = (EditText) findViewById(R.id.etEmail);
        add=(EditText) findViewById(R.id.add);
        etPass=(EditText) findViewById(R.id.etPass);
        mo=(EditText) findViewById(R.id.mo);
        signup = (Button) findViewById(R.id.btnSingIn);

        signup.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (checkValidation())
                            insert(v);
                        else
                            Toast.makeText(activity_sign_up.this, "Form contains error", Toast.LENGTH_LONG).show();


                        Log.d("success", "working button");


                    }
                });
            }

                    public void insert(View view){
                    String name = etUserName.getText().toString();
                     String mail = etEmail.getText().toString();
                    String pass = etPass.getText().toString();
                    String mobile = mo.getText().toString();
                    String address = add.getText().toString();
                    insertToDatabase(name,mail,pass,mobile,address);
               }





            private void insertToDatabase(String name,String mail,String pass,String mobile, String address){
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {

            protected String doInBackground(String... params) {

                String paramUsername = params[0];
                String paramMail = params[1];
                String paramPass = params[2];
                String paramMo = params[3];
                String paramAdd = params[4];


                String name = paramUsername;
                String mail = paramMail;
                String pass = paramPass;
                String mo = paramMo;
                String add = paramAdd;
               // String mail = etEmail.getText().toString();
                //String pass = etPass.getText().toString();
                //String mo = mo.getText().toString();
                //String add = add.getText().toString();

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("name", name));
                nameValuePairs.add(new BasicNameValuePair("mail", mail));
                nameValuePairs.add(new BasicNameValuePair("pass", pass));
                nameValuePairs.add(new BasicNameValuePair("mo", mo));
                nameValuePairs.add(new BasicNameValuePair("add", add));
                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost("http://10.0.2.2/FIRAPP/library/functionsf.php");
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    HttpResponse response = httpClient.execute(httpPost);
                    HttpEntity entity = response.getEntity();

                    } catch (ClientProtocolException e) {

                    } catch (IOException e) {

                    }
                Intent i = new Intent(getApplicationContext(), activity_sign_in.class);
                startActivity(i);

                return "You Have Successfuly registred.";
                }
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                //TextView textViewResult = (TextView) findViewById(R.id.textViewResult);

               // textViewResult.setText("Inserted");

                }
            }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(name, mail, pass, mobile, address);

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
        if (!Validation.isEmailAddress(etEmail, true)) ret = false;
        if (!Validation.isPhoneNumber(mo,false)) ret = false;

        return ret;
    }

}