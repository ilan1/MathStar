package home.test2;

import android.content.Intent;
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
    private Button submitButton;
    TextView questionView;
    int safe;
    int wrongjump;
    int correctjump;
    int HP = 50;
    int right;
    int j = 0;

    int currentQuestion;
     RadioGroup group = (RadioGroup) findViewById(R.id.radioGroup1);
    RadioButton button;
    Random r = new Random();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_game);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);


       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
*/

        Intent quizIntent = getIntent();
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
                    if(HP == 0)
                    {
                        questionView.setText("Sorry you have died.");
                        submitButton.setText("FINISH");
                    }
                else{



                        questionView.setText("Your HP is: " + HP + "" +
                                "Question: " + data.get(storynums[j]).getProblem());

                //a.setText(""+(data.get(storynums[1]).getAnswer()) * 2));
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

        if (group.getCheckedRadioButtonId() == right)
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




