package home.test2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class QuizActivity extends AppCompatActivity {

    private Button submitButton;
    private Button quitButton;
    //The Num variables keep track of how many times a question from this category has been placed in the quiz list
    //The Max variables switch to false and prevent a question from being selected for the quiz when this category has a uneven majority
    //of questions in the quiz
    int leNum, afNum, ssNum, caNum, mpavNum, endNum = 0;
    boolean leMax = true;
    boolean afMax = true;
    boolean ssMax = true;
    boolean caMax = true;
    boolean mpavMax = true;
    boolean endMax = true;

    TextView questionView;
    TextView answerbox;
    TextView correctprefix;
    ArrayList<String> sections;
    int correct = 0;
    private int currentQuestion = 0;
    List<Question> data = Data.getInstance().getData();
    ArrayList<Question> questions = new ArrayList<>();
    ArrayList<String> responses = new ArrayList<>();
    int[] quiznums = new int[10];

    //A class-name tag for any Log calls
    private String tag = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*retrieves arraylist of sections from QuizMenu class*/
        Intent quizIntent = getIntent();
        sections = quizIntent.getStringArrayListExtra("sectionArray");
        Log.v(tag + " sections", sections.toString());
        Random r = new Random();
        int[] alreadyChosen = new int[10];
            int hold;
            int i = 0;
            boolean safe = true;
        while(i<=9) {
            hold = r.nextInt(65);
            safe = true;

            for (int j = 0; j <= 9; j++) {
                if (hold == alreadyChosen[j]) {
                    safe = false;
                }
            }

            if (safe) {

                    Log.v(tag + " total", ""+leMax+" "+ afMax+ " "+ ssMax + " " +caMax+" "+ mpavMax+ " "+ endMax);

                if (sections.contains("Linear Equations") && hold >= 0 && hold <= 14 && leMax) {
                    quiznums[i] = hold; alreadyChosen[i] = hold;

                    Log.v(tag + " LE", ""+hold);

                    i++;

                    leNum++;
                    if(sections.size() >=5 && leNum >= 2)
                    {leMax = false;}
                    if(sections.size() >=4 && leNum >= 3)
                    {leMax = false;}
                    if(sections.size() >=3 && leNum >= 4)
                    {leMax = false;}
                    if(sections.size() >=2 && leNum >= 5)
                    {leMax = false;}

                    Log.v(tag + " lemax", ""+leMax);

                }
                
                else if (sections.contains("Algebraic Functions") && hold >= 15 && hold <= 24 && afMax) {
                    quiznums[i] = hold; alreadyChosen[i] = hold;
                    Log.v(tag + " AF", ""+hold);

                    i++;

                    afNum++;
                    if(sections.size() >=5 && afNum >= 2)
                    {afMax = false;}
                    if(sections.size() >=4 && afNum >= 3)
                    {afMax = false;}
                    if(sections.size() >=3 && afNum >= 4)
                    {afMax = false;}
                    if(sections.size() >=2 && afNum >= 5)
                    {afMax = false;}
                    Log.v(tag + " afmax", ""+afMax);

                }

                else if (sections.contains("Sequences and Series") && hold >= 25 && hold <= 34 && ssMax) {
                    quiznums[i] = hold; alreadyChosen[i] = hold;

                    Log.v(tag + " SS", ""+hold);

                    i++;

                    ssNum++;
                    if(sections.size() >=5 && ssNum >= 2)
                    {ssMax = false;}
                    if(sections.size() >=4 && ssNum >= 3)
                    {ssMax = false;}
                    if(sections.size() >=3 && ssNum >= 4)
                    {ssMax = false;}
                    if(sections.size() >=2 && ssNum >= 5)
                    {ssMax = false;}
                    
                    Log.v(tag + " ssmax", ""+ssMax);

                }

                else if (sections.contains("Classification of Angles") && hold >= 35 && hold <= 44 && caMax) {
                    quiznums[i] = hold; alreadyChosen[i] = hold;
                    Log.v(tag + " CA", ""+hold);

                    i++;

                    caNum++;
                    if(sections.size() >=5 && caNum >= 2)
                    {caMax = false;}
                    if(sections.size() >=4 && caNum >= 3)
                    {caMax = false;}
                    if(sections.size() >=3 && caNum >= 4)
                    {caMax = false;}
                    if(sections.size() >=2 && caNum >= 5)
                    {caMax = false;}
                    Log.v(tag + " camax", ""+caMax);

                }

                else if (sections.contains("Measurements of Perimeters, Area, and Volume") && hold >= 45 && hold <= 54 &&mpavMax) {
                    quiznums[i] = hold; alreadyChosen[i] = hold;
                    Log.v(tag + " MPAV", ""+hold);
                    i++;

                    mpavNum++;
                    if(sections.size() >=5 && mpavNum >= 2)
                    {mpavMax = false;}
                    if(sections.size() >=4 && mpavNum >= 3)
                    {mpavMax = false;}
                    if(sections.size() >=3 && mpavNum >= 4)
                    {mpavMax = false;}
                    if(sections.size() >=2 && mpavNum >= 5)
                    {mpavMax = false;}
                    Log.v(tag + " mpavmax", ""+mpavMax);

                }

                else if  (sections.contains("Right Triangles and Trigonometry") && hold >= 55 && hold <= 64 && endMax) {
                    quiznums[i] = hold; alreadyChosen[i] = hold;
                    Log.v(tag + " RTT", ""+hold);
                    i++;

                    endNum++;
                    if(sections.size() >=5 && endNum >= 2)
                    {endMax = false;}
                    if(sections.size() >=4 && endNum >= 3)
                    {endMax = false;}
                    if(sections.size() >=3 && endNum >= 4)
                    {endMax = false;}
                    if(sections.size() >=2 && endNum >= 5)
                    {endMax = false;}

                    Log.v(tag + " endmax", ""+endMax);

                }
            }
        }
        initialize();
    }

    /**
     * Starts quiz by setting up view
     */
    public void initialize(){
        submitButton = (Button) findViewById(R.id.submitButton);
        quitButton = (Button) findViewById(R.id.quitButton);
        Log.v(tag + " submit button text", "" + submitButton.getText());
        questionView = (TextView) findViewById(R.id.questionView);
        correctprefix = (TextView) findViewById(R.id.correcttext);
        answerbox = (TextView) findViewById(R.id.Answerbox);
        showQuestion();
        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //navigate to Lesson selection page
                Intent i = new Intent(QuizActivity.this, QuizMenu.class);
                startActivity(i);
                return;

            }
        });
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (submitButton.getText().toString().equalsIgnoreCase("CHECK")) {
                    checkAnswer();
                    submitButton.setText("SUBMIT");
                    if (check())
                        Toast.makeText(getApplicationContext(), "Correct!", Toast.LENGTH_SHORT).show();
                    else if (!check())
                    {
                        Toast.makeText(getApplicationContext(), "Sorry, incorrect!", Toast.LENGTH_SHORT).show();
                        correctprefix.setText("Correct Answer: ");
                        answerbox.setText(data.get(quiznums[currentQuestion]).getAnswer());

                    }

                    EditText answerText = (EditText) findViewById(R.id.answerText);
                    answerText.setEnabled(false);
                    return;
                } else if (submitButton.getText().toString().equalsIgnoreCase("SUBMIT")) {
                    showNextQuestion();
                    EditText answerText = (EditText) findViewById(R.id.answerText);
                    answerText.setEnabled(true);
                    if (currentQuestion != 10) {
                        submitButton.setText("CHECK");
                    }
                } else if (submitButton.getText().toString().equalsIgnoreCase("RESULTS")) {
                    //open results
                    displayResults();

                    //navigate to quiz selection page
                    //Intent i = new Intent(QuizActivity.this, QuizMenu.class);
                    //startActivity(i);
                    return;
                }
                // check(); add check() method to keep track of answers
                //showNextQuestion ?
            }
        });
    }

    /**
     * Clears the text field and shows the next question
     */
    public void showNextQuestion(){
        EditText d = (EditText)findViewById(R.id.answerText);
        responses.add(currentQuestion, d.getText().toString());
        questions.add(currentQuestion, data.get(quiznums[currentQuestion]));
        correctprefix.setText("");
        answerbox.setText("");
        d.setText("");
        currentQuestion++;

        Log.v(tag + " currentQuestion", "" + currentQuestion);
        if(currentQuestion == 10){
            int leNum, afNum, ssNum, caNum, mpavNum, endNum = 0;
            boolean leMax = true;
            boolean afMax = true;
            boolean ssMax = true;
            boolean caMax = true;
            boolean mpavMax = true;
            boolean endMax = true;
            questionView.setText("Congrats you got " + correct + " questions correct.");
            submitButton.setText("Results");
            d.setVisibility(View.GONE);
        }
        else
        {showQuestion();}
    }
    public boolean check(){
        EditText d = (EditText)findViewById(R.id.answerText);
        return d.getText().toString().equalsIgnoreCase(data.get(quiznums[currentQuestion]).getAnswer());
    }
    public void checkAnswer(){
        EditText d = (EditText)findViewById(R.id.answerText);
        if (d.getText().toString().equalsIgnoreCase(data.get(quiznums[currentQuestion]).getAnswer()))
        {
            correct++;
        }
    }
    /**
     * Displays questions
     */
    public void showQuestion(){

        TextView questionToolBarTitle = (TextView) findViewById(R.id.questionToolBarTitle);
        questionToolBarTitle.setText("Question " + (currentQuestion+1) + " / 10");
        questionView.setText((currentQuestion+1) + ". " + data.get(quiznums[currentQuestion]).getProblem());
    }

    public void displayResults(){
        Intent i = new Intent(this, ResultsActivity.class);
        i.putExtra("questions", questions);
        i.putStringArrayListExtra("responses", responses);
        startActivity(i);
    }

}
