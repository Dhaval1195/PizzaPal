package com.SAURABH.pizzapal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.SAURABH.pizza.R;

public class loginActivity extends Activity {
    EditText username,pass;
    Button login;
    TextView signu;
    String eusername;
    boolean allfield = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username=(EditText) findViewById(R.id.username);
        pass=(EditText) findViewById(R.id.password);
        login=(Button) findViewById(R.id.login1);
        signu=(TextView) findViewById(R.id.signin);
        Intent i = getIntent();
        String a = " ";
        String b = " ";
        try {
            a = i.getStringExtra("number1");
            b = i.getStringExtra("number2");
        } catch (NumberFormatException e) {
            Log.d("error1", "user not give input");

        }
        username.setText(a);
        pass.setText(b);

        signu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(loginActivity.this,registerActivity.class);
                startActivity(intent);
            }
        });
         login.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 allfield = login();
                 if (allfield)
                 {
                     Intent i = new Intent(loginActivity.this,Pizza.class);
                     i.putExtra("username",eusername);
                     startActivity(i);
                 }



             }
         });


    }

    private boolean login()
    {

       eusername = username.getText().toString().trim();
        String epass = pass.getText().toString().trim();
        if(checkUsername(eusername)&& checkPassword(epass))
        {
            if(eusername.equals("Dhavaluable") && epass.equals("Dhaval1195")){
                Toast.makeText(this,"You Have Login Successfully!",Toast.LENGTH_SHORT).show();
                return true;
            }
           else {
                Toast.makeText(this,"Wrong Credentials",Toast.LENGTH_SHORT).show();

            }
        }
        return false;
    }
    private boolean checkUsername(String eusername) {
        if (TextUtils.isEmpty(eusername)) {
            username.setError("Please Enter UserName");
            return false;
        }
        return true;
    }
    private  boolean checkPassword(String epass) {
        if (TextUtils.isEmpty(epass)) {
            pass.setError("Please Enter Password");
            return false;
        }
        else if (pass.length()<8 || pass.length()>10){
            pass.setError("Password Should be Atleast 8 to 10 characters");
            return false;
        }
        return true;
    }


}


