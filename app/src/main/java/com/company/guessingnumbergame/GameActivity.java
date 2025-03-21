package com.company.guessingnumbergame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private TextView textViewLast, textViewRight, textViewHint;
    private EditText editTextGuess;
    private Button buttonConfirm;

    boolean twoDigits,threeDigits,fourDigits;

    Random r = new Random();
    int random;
    int remainingRight = 10;

    //keeping the user's guesses in arrayList
    ArrayList<Integer> guessesList = new ArrayList<>();
    int userAttempts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        textViewLast = findViewById(R.id.textViewLast);
        textViewRight = findViewById(R.id.textViewRight);
        textViewHint = findViewById(R.id.textViewHint);
        editTextGuess = findViewById(R.id.editTextGuess);
        buttonConfirm = findViewById(R.id.buttonConfirm);

        //we take radioButton(number of digits) chosen by the user on MainActivity page from this activity(GameActivity)
        twoDigits = getIntent().getBooleanExtra("two",false);
        threeDigits = getIntent().getBooleanExtra("three",false);
        fourDigits = getIntent().getBooleanExtra("four",false);

        if (twoDigits)
        {
            random = r.nextInt(90)+10;
        }
        if (threeDigits)
        {
            random = r.nextInt(900)+100;
        }
        if (fourDigits)
        {
            random = r.nextInt(9000)+1000;
        }

        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String guess = editTextGuess.getText().toString();
                if (guess.equals(""))
                {
                    Toast.makeText(GameActivity.this,"Please enter a guess",Toast.LENGTH_LONG).show();
                }
                else
                {
                    textViewLast.setVisibility(View.VISIBLE);
                    textViewRight.setVisibility(View.VISIBLE);
                    textViewHint.setVisibility(View.VISIBLE);

                    remainingRight--;
                    userAttempts++;

                    //I convert the guess value to integer type
                    int userGuess = Integer.parseInt(guess);
                    guessesList.add(userGuess);

                    textViewLast.setText(guess);
                    textViewRight.setText(remainingRight);

                    if (userGuess == random)
                    {
                        AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
                        builder.setTitle("Number Guessing Game");
                        builder.setCancelable(false);//if the user click out of the dialog and it'll not be close
                        builder.setMessage("Congratulations. My guess was "+random
                                +"\n\n You Know my number in :"+userAttempts
                                +" attempts. \n\n Your guesses : "+guessesList
                                +"\n\n Would you like to play again?");
                        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(GameActivity.this,MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });
                        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                               //this code is just standard, these codes are used to terminate the app
                                moveTaskToBack(true);
                                android.os.Process.killProcess(android.os.Process.myPid());
                                System.exit(1);
                            }
                        });
                        builder.create().show();//that'll make the dialog appear
                    }
                    if (userGuess > random)
                    {
                        textViewHint.setText("Decrease your guess ");
                    }
                    if (userGuess < random)
                    {
                        textViewHint.setText("Increase your guess ");
                    }
                    if (remainingRight == 0)
                    {

                    }

                    editTextGuess.setText("");

                }
            }
        });
    }
}