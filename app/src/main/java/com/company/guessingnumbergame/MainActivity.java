package com.company.guessingnumbergame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private Button buttonStart;
    private RadioButton radio2,radio3,radio4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonStart = findViewById(R.id.buttonStart);
        radio2 = findViewById(R.id.radio2);
        radio3 = findViewById(R.id.radio3);
        radio4 = findViewById(R.id.radio4);

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,GameActivity.class);

                if (!radio2.isChecked() && !radio3.isChecked() && !radio4.isChecked())
                {
                    Snackbar.make(view,"Please Select a number of digits",Snackbar.LENGTH_LONG).show();
                }
                else
                {
                    if (radio2.isChecked())
                    {
                        //convey this selection to the game activity using putExtra() method
                        intent.putExtra("two",true);
                    }
                    if (radio3.isChecked())
                    {
                        //convey this selection to the game activity using putExtra() method
                        intent.putExtra("three",true);
                    }
                    if (radio4.isChecked())
                    {
                        //convey this selection to the game activity using putExtra() method
                        intent.putExtra("four",true);
                    }
                    startActivity(intent);//switch to the GameActivity using startActivity() method
                }
            }
        });
    }
}