package com.example.myapplication;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;



public class ConfigPage extends AppCompatActivity {
    



    private Button game;
    private EditText name;
    private RadioGroup difficultyGroup;
    private RadioGroup characterGroup;



    public static final String NAME_ID = "name1";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_page);
        setUpConfigPage(savedInstanceState);
    }

    public void setUpConfigPage(Bundle savedInstanceState) {

        game = (Button) findViewById(R.id.goToGame);

        name = (EditText) findViewById(R.id.personName);

        difficultyGroup = (RadioGroup) findViewById(R.id.ChooseDifficulty);

        characterGroup = (RadioGroup) findViewById(R.id.ChooseCharacter);



        game.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                if (name.getText().toString().trim().equals("")
                        || (difficultyGroup.getCheckedRadioButtonId() == -1)
                        || (characterGroup.getCheckedRadioButtonId() == -1)) {

                    //System.out.println("Null name");
                } else {
                    Intent intent = new Intent(ConfigPage.this, GameScreen.class);
                    intent.putExtra(NAME_ID, name.getText().toString());

                    if (characterGroup.getCheckedRadioButtonId() == R.id.CharacterOne) {
                        intent.putExtra("image", R.drawable.character_one);
                    } else if (characterGroup.getCheckedRadioButtonId() == R.id.CharacterTwo) {
                        intent.putExtra("image", R.drawable.character_two);
                    } else if (characterGroup.getCheckedRadioButtonId() == R.id.CharacterThree) {
                        intent.putExtra("image", R.drawable.character_three);
                    }

                    if (difficultyGroup.getCheckedRadioButtonId() == R.id.DifficultyOne) {
                        intent.putExtra("difficulty", "Easy");
                        intent.putExtra("numLives", 10);
                    } else if (difficultyGroup.getCheckedRadioButtonId() == R.id.DifficultyTwo) {
                        intent.putExtra("difficulty", "Intermediate");
                        intent.putExtra("numLives", 7);
                    } else if (difficultyGroup.getCheckedRadioButtonId() == R.id.DifficultyThree) {
                        intent.putExtra("difficulty", "Difficult");
                        intent.putExtra("numLives", 5);
                    }

                    startActivity(intent);

                }
            }
        });
    }
}