package home.test2;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StoryGame extends AppCompatActivity {

    ArrayList<String> sections;
    List<Question> data = Data.getInstance().getData();
    int[] storynums = new int[10];
    Typeface custom_font;
    private Button submitButton;
    ProgressBar healthbar;
    TextView questionView;
    TextView hpText;
    int safe;
    int wrongjump;
    int correctjump;
    int HP = 50;
    int right;
    int j = 0;
    Intent quizIntent;


    int currentQuestion;
    RadioGroup group;
    RadioButton button1;
    RadioButton button2;
    RadioButton button3;
    RadioButton button4;
    Random r = new Random();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_game);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        questionView = (TextView) findViewById(R.id.questionView);
         custom_font = Typeface.createFromAsset(getAssets(), "fonts/MODES___.ttf");
        questionView.setTypeface(custom_font);
        hpText = (TextView) findViewById(R.id.HPtext);
        hpText.setTypeface(custom_font);

         group = (RadioGroup) findViewById(R.id.radioGroup1);
        button1 = (RadioButton) findViewById(R.id.button1);
         button2 = (RadioButton) findViewById(R.id.button2);
         button3 = (RadioButton) findViewById(R.id.button3);
         button4 = (RadioButton) findViewById(R.id.button4);
        button1.setTypeface(custom_font);
        button2.setTypeface(custom_font);
        button3.setTypeface(custom_font);
        button4.setTypeface(custom_font);

       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
*/

         quizIntent = getIntent();
        //sections = quizIntent.getStringArrayListExtra("sectionArray");
        sections = quizIntent.getStringArrayListExtra("sectionArray");

        if (sections.contains("Sequences and Series")) {
            for (int i = 0; i <= 9; i++) {
                storynums[i] = 25 + i;
            }

        }
        else
        {
            for (int i = 0; i <= 9; i++) {
                storynums[i] = 46 + i;
            }        }
        generate(j);
    }

         //questionView.setText("You begin your Journey to the castle with 50 HP and a mediocre knowledge of math." +
           //      "You go to cross the bridge in front of your house and encounter a troll who demmands you answer" +
             //    "a question to pass: "+ data.get(storynums[1]).getProblem());
            public void generate(int j) {
                submitButton = (Button) findViewById(R.id.submitButton);
                healthbar = (ProgressBar) findViewById(R.id.healthBar);
                healthbar.setProgress(HP*2);
                hpText.setText("HP Remaining: " + HP);


                group.clearCheck();
                    if(HP == 0)
                    {

                        questionView.setText("Sorry you have died.");
                        submitButton.setText("FINISH");
                    }
                    else if (j == 10)
                    {
                        questionView.setText("You win");
                        submitButton.setText("FINISH");
                    }

                else{



                        questionView.setText("Question: " + data.get(storynums[j]).getProblem());

                //a.setText(""+(data.get(storynums[1]).getAnswer()) * 2));
                /*
                        for (int i = 0; i < 4; i++) {

                    int mid = r.nextInt(34);
                    button = new RadioButton(this);
                    button.setText("" + data.get(mid).getAnswer());
                    group.addView(button);
                }

                 right = r.nextInt(3);
                button = new RadioButton(this);
                button.setText(data.get(storynums[j]).getAnswer());

                group.removeViewAt(right);
                group.addView(button, right);
                 */

                        int mid = r.nextInt(34);
                        button1.setText(data.get(mid).getAnswer());
                        mid = r.nextInt(34);
                        button2.setText(data.get(mid).getAnswer());
                        mid = r.nextInt(34);
                        button3.setText(data.get(mid).getAnswer());
                        mid = r.nextInt(34);
                        button4.setText(data.get(mid).getAnswer());

                        right = r.nextInt(4);
                        if(right == 3)
                        {button4.setText(data.get(storynums[j]).getAnswer());}
                        else if(right == 2)
                        {button3.setText(data.get(storynums[j]).getAnswer());}
                        else if(right == 1)
                        {button2.setText(data.get(storynums[j]).getAnswer());}
                        else
                        {button1.setText(data.get(storynums[j]).getAnswer());}

                submitButton.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        if (submitButton.getText().toString().equalsIgnoreCase("FINISH")) {
                            //navigate to quiz selection page
                            Intent i = new Intent(StoryGame.this, LessonMenu.class);
                            startActivity(i);
                            return;
                        }
                        else
                        {
                            check();
                        }
                    }
                    })
                    ;}
            }

    public void showNextQuestion(int j){


        generate(j);
    }
    public void check(){

        if (button4.isChecked() &&  right == 3)
        {
            j++;
            showNextQuestion(j);
        }
        else if (button3.isChecked() &&  right == 2)
        {
            j++;
            showNextQuestion(j);
        }
        else if (button2.isChecked() &&  right == 1)
        {
            j++;
            showNextQuestion(j);
        }
        else if (button1.isChecked() &&  right == 0)
        {
            j++;
            showNextQuestion(j);
        }

        else{
            HP = HP - 10;
            j++;
            showNextQuestion(j);
        }
    }





    }




