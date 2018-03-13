package com.example.aakash.firapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;




/*package com.example.aakash.firapp;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.aakash.firapp.R;

import com.example.aakash.firapp.activity_sign_in;
import com.example.aakash.firapp.activity_sign_up;

import org.w3c.dom.Text;*/


/*import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.aakash.firapp.activity_sign_in;
import com.example.aakash.firapp.activity_sign_up;*/

public class MainActivity extends AppCompatActivity {
   // TextView testView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
       // testView =( TextView )findViewById(R.id.app);
    }

    public void OnClick( View view)
    {
        if(R.id.btnSignIn== view.getId()){
            Intent intent1= new Intent(this,activity_sign_in.class);
            startActivity(intent1 );
        }
        else{
            Intent intent= new Intent(this,activity_sign_up.class);
            startActivity(intent );
        }




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}


