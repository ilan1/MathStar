package home.test2;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import java.util.List;

import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class StoryGame extends AppCompatActivity {

    ArrayList<String> sections;
    String[] deathArray = new String[5];
    String[] scenarios = new String[10];
    List<Question> data = Data.getInstance().getData();
    int[] storynums = new int[10];
    Typeface custom_font;
    private Button submitButton;
    ProgressBar healthbar;
    TextView questionView;
    TextView hpText;
    int safe;
    private Button quitButton;
    int HP = 50;
    int right;
    int j = 0;
    boolean noDuplicates;
    Intent quizIntent;


    String currentAnswer;
    RadioGroup group;
    RadioButton button1;
    RadioButton button2;
    RadioButton button3;
    RadioButton button4;
    Random r = new Random();

    int but1,but2,but3,but4;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_game);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        questionView = (TextView) findViewById(R.id.questionView);
         custom_font = Typeface.createFromAsset(getAssets(), "fonts/MODES___.TTF");
        questionView.setTypeface(custom_font);
        hpText = (TextView) findViewById(R.id.HPtext);
        hpText.setTypeface(custom_font);

        deathArray[0] = "Wow you're terrible!";
        deathArray[1] = "Better go study some more...";
        deathArray[2] = "Tip: Don't die next time...";
        deathArray[3] = "Time to get a tutor?";
        deathArray[4] = "You completely and utterly failed.";

        scenarios[0] = "You begin your journey by crossing the stream in front of your house, to keep your focus you ask yourself a math problem ";
        scenarios[1] = "You continue walking and run into a troll who asks you ";
        scenarios[2] = "You reach your first town but before you can enter you must answer a riddle ";
        scenarios[3] = "You begin to get hungry and look around for food. You enter a math contest where 1st place gets pie and last place gets poison ";
        scenarios[4] = "You set off for the next town but must answer the trolls question to cross the bridge ";
        scenarios[5] = "A thunderstorm is overhead, a shopkeep offers you refuge if you can answer his sons homework problem ";
        scenarios[6] = "To reward yourself for making it so far you decide to take a break, to decide " +
                "whether you sleep in the snake pit or under a tree you ask yourself a question ";
        scenarios[7] = "You reach the castle, but it has a code on the drawbridge.  The keypad asks you ";
        scenarios[8] = "  You've made it inside the castle and encounter two dragons, to let you pass they each ask you a question.  The first one asks ";
        scenarios[9] = "For a final test the second dragon asks you ";




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
                quitButton = (Button) findViewById(R.id.quitButton);
                healthbar = (ProgressBar) findViewById(R.id.healthBar);
                healthbar.setProgress(HP*2);
                hpText.setText("HP Remaining: " + HP);


                group.clearCheck();
                    if(HP == 0)
                    {
                        safe = r.nextInt(5);
                        questionView.setText("" + deathArray[safe]);
                        submitButton.setText("FINISH");
                    }
                    else if (j == 10)
                    {
                        questionView.setText("You win");
                        submitButton.setText("FINISH");
                    }

                else{



                        questionView.setText(""+scenarios[j] + data.get(storynums[j]).getProblem());

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
                        /*

                        int mid = r.nextInt(34);
                        button1.setText(data.get(mid).getAnswer());
                        mid = r.nextInt(34);
                        button2.setText(data.get(mid).getAnswer());
                        mid = r.nextInt(34);
                        button3.setText(data.get(mid).getAnswer());
                        mid = r.nextInt(34);
                        button4.setText(data.get(mid).getAnswer());
*/
                        currentAnswer = data.get(storynums[j]).getAnswer();

                        noDuplicates = false;





                        int mid = r.nextInt(18);
                        button1.setText(""+mid);
                        mid = r.nextInt(18);
                        button2.setText(""+mid);
                        mid = r.nextInt(18);
                        button3.setText(""+mid);
                        mid = r.nextInt(18);
                        button4.setText(""+mid);





                        right = r.nextInt(4);
                        if(right == 3)
                        {button4.setText(""+currentAnswer);}
                        else if(right == 2)
                        {button3.setText(""+currentAnswer);}
                        else if(right == 1)
                        {button2.setText(""+currentAnswer);}
                        else
                        {button1.setText(""+currentAnswer);}

                        quitButton.setOnClickListener(new View.OnClickListener()
                        {
                            @Override
                            public void onClick(View v)
                            {

                                    //navigate to Lesson selection page
                                    Intent i = new Intent(StoryGame.this, LessonMenu.class);
                                    startActivity(i);
                                    return;

                            }
                        });

                submitButton.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        if (submitButton.getText().toString().equalsIgnoreCase("FINISH")) {
                            //navigate to Lesson selection page
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




