package com.example.kinjalkumaridhimmar_comp304sec004_test01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.Toast;

public class BrainChallenge extends AppCompatActivity {
CheckBox lose,names,quickly,things;
Button next;
    String selected="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brain_challenge);

        lose=(CheckBox) findViewById(R.id.checkBoxLose);
        names=(CheckBox) findViewById(R.id.checkBoxNames);
        quickly=(CheckBox) findViewById(R.id.checkBoxQuickly);
        things=(CheckBox) findViewById(R.id.checkBoxThings);
        next=(Button) findViewById(R.id.nextButton);


        next.setOnClickListener(new View.OnClickListener() {
            @Override


            public void onClick(View view) {
                if (lose.isChecked()){
                    selected+="Lose things";
                     //Toast.makeText(BrainChallenge.this, "Lose things" + selected, Toast.LENGTH_SHORT).show();

                }
                else if (names.isChecked())
                {
                    selected+="Remember Names";
                    //Toast.makeText(BrainChallenge.this, "Remember Names" + selected, Toast.LENGTH_SHORT).show();

                }
                else if(quickly.isChecked())
                {
                    selected+="Learn Things Quickly";
                    //Toast.makeText(BrainChallenge.this, "Learn Things Quickly" + selected, Toast.LENGTH_SHORT).show();

                }
                else if(things.isChecked())
                {
                   selected=selected+"Keep Track of multiple things";
                    //Toast.makeText(BrainChallenge.this, "Keep Track of multiple things" + selected, Toast.LENGTH_SHORT).show();

                }
                else {
                    Toast.makeText(BrainChallenge.this, "Please Select an Item", Toast.LENGTH_SHORT).show();
                }

                Toast.makeText(BrainChallenge.this, "" + selected, Toast.LENGTH_SHORT).show();
            }
        });

    }
}