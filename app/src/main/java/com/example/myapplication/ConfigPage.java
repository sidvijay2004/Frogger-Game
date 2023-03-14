package com.example.myapplication;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;


public class ConfigPage extends AppCompatActivity {
    private Button game;
    private EditText name;
    private RadioGroup difficultyGroup;
    private RadioGroup characterGroup;
    private ImageButton charSelectLeft;
    private ImageButton charSelectRight;
    private ImageView charImageDisplay;
    private int charSelectionId;

    private TextView difficultySelector;
    private ImageButton diffSelectLeft;
    private ImageButton diffSelectRight;
    private int diffId;
    public static final String NAME_ID = "name1";

//    public static final String NAME_ID = "RESR";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_page);
        setUpConfigPage(savedInstanceState);

        Player mainPlayer = new Player();
    }

    public void updateCharDisplay() {
        switch (charSelectionId) {
        case 0:
            charImageDisplay.setImageResource(R.drawable.character_one);
            break;
        case 1:
            charImageDisplay.setImageResource(R.drawable.character_two);
            break;
        default:
            charImageDisplay.setImageResource(R.drawable.character_three);
            break;
        }
    }

    public void updateDiffId() {
        diffId = diffId % 3;
        String diffText = "";
        switch (diffId) {
        case 0:
            diffText = "EASY";
            break;
        case 1:
            diffText = "MEDIUM";
            break;
        default:
            diffText = "HARD";
            break;
        }
        difficultySelector.setText(diffText);
    }


    public void setUpConfigPage(Bundle savedInstanceState) {

        game = (Button) findViewById(R.id.goToGame);

        name = (EditText) findViewById(R.id.personName);


        charImageDisplay = (ImageView) findViewById(R.id.characterDisplay);
        charSelectLeft = (ImageButton) findViewById(R.id.charLeft);
        charSelectRight = (ImageButton) findViewById(R.id.charRight);

        difficultySelector = (TextView) findViewById(R.id.difficultySelector);
        diffSelectLeft = (ImageButton) findViewById(R.id.diffLeft);
        diffSelectRight = (ImageButton) findViewById(R.id.diffRight);
        diffId = 0;
        charSelectionId = 0;
        diffSelectLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                diffId--;
                updateDiffId();
            }
        });

        diffSelectRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                diffId++;
                updateDiffId();
            }
        });


        charSelectLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                charSelectionId--;
                charSelectionId %= 3;
                updateCharDisplay();
            }
        });

        charSelectRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                charSelectionId++;
                charSelectionId %= 3;
                updateCharDisplay();
            }
        });

        game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!name.getText().toString().trim().equals("")) {

                    String diffString = difficultySelector.getText().toString();

                    Intent intent = new Intent(ConfigPage.this, GameScreen.class);
                    intent.putExtra(NAME_ID, name.getText().toString());

                    Player mainPlayer = new Player();

                    switch (charSelectionId) {
                    case 0:
                        mainPlayer.setImageResourceId(R.drawable.character_one);
                        break;
                    case 1:
                        mainPlayer.setImageResourceId(R.drawable.character_two);

                        break;
                    default:
                        mainPlayer.setImageResourceId(R.drawable.character_three);
                        break;
                    }

                    switch (diffString) {
                    case "MEDIUM":
                        intent.putExtra("difficulty", "Medium");
                        intent.putExtra("numLives", 7);
                        mainPlayer.setDifficulty("Medium", 7);
                        break;
                    case "HARD":
                        intent.putExtra("difficulty", "Hard");
                        intent.putExtra("numLives", 5);
                        mainPlayer.setDifficulty("Hard", 5);
                        break;
                    default:
                        intent.putExtra("difficulty", "Easy");
                        intent.putExtra("numLives", 10);
                        mainPlayer.setDifficulty("Easy", 10);
                    }


                    intent.putExtra("playerObject", mainPlayer);
                    startActivity(intent);

                }
            }
        });
    }
}