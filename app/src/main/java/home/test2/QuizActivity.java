package home.test2;

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
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuizActivity extends AppCompatActivity {

    private Button submitButton;
    TextView questionView;
    ArrayList<String> sections;
    int correct = 0;
    private int currentQuestion = 0;
    List<Question> data = Data.getInstance().getData();
    int[] quiznums = new int[10];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*retrieves arraylist of sections from QuizMenu class*/
        Intent quizIntent = getIntent();
        sections = quizIntent.getStringArrayListExtra("sectionArray");
        Log.v("sections", sections.toString());
        Random r = new Random();
        int[] alreadyChosen = new int[10];
            int hold;
            int i = 0;
            boolean safe = true;
        while(i<=9) {
            hold = r.nextInt(64);
            safe = true;

            for (int j = 0; j <= 9; j++) {
                if (hold == alreadyChosen[j]) {
                    safe = false;
                }


            }

            if (safe) {



                if (sections.contains("Linear Equations") && hold >= 0 && hold <= 14) {
                    quiznums[i] = hold; alreadyChosen[i] = hold;
                    Log.v("LE",""+hold);
                    i++;
                }
                
                else if (sections.contains("Algebraic Functions") && hold >= 15 && hold <= 24) {
                    quiznums[i] = hold; alreadyChosen[i] = hold;
                    Log.v("AF",""+hold);
                    i++;
                }

                else if (sections.contains("Sequences and Series") && hold >= 25 && hold <= 34) {
                    quiznums[i] = hold; alreadyChosen[i] = hold;
                    Log.v("SS",""+hold);
                    i++;
                }

                else if (sections.contains("Classification of Angles") && hold >= 35 && hold <= 44) {
                    quiznums[i] = hold; alreadyChosen[i] = hold;
                    Log.v("CA",""+hold);
                    i++;
                }

                else if (sections.contains("Measurements of Perimeters, Area, and Volume") && hold >= 45 && hold <= 54) {
                    quiznums[i] = hold; alreadyChosen[i] = hold;
                    Log.v("MPAV",""+hold);
                    i++;
                }

                else if  (sections.contains("Right Triangles and Trigonometry") && hold >= 55 && hold <= 64) {
                    quiznums[i] = hold; alreadyChosen[i] = hold;
                    Log.v("END",""+hold);
                    i++;
                }




            }
        }


        initialize();
    }

    /*starts quiz by setting up view*/
    public void initialize(){
        submitButton = (Button) findViewById(R.id.submitButton);
        questionView = (TextView) findViewById(R.id.questionView);
        showQuestion();
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // check(); add check() method to keep track of answers
                check();
                showNextQuestion();
                //showNextQuestion ?
            }
        });
    }

    public void showNextQuestion(){
        EditText d = (EditText)findViewById(R.id.answerText);
        d.setText("");
        currentQuestion++;
        if(currentQuestion == 10){
            questionView.setText("Congrats you got " + correct + " questions correct");
        }
        else
        {showQuestion();}
    }
    public void check(){
        EditText d = (EditText)findViewById(R.id.answerText);
        if(d.getText().toString().equalsIgnoreCase(data.get(quiznums[currentQuestion]).getAnswer()))
        {correct++;}
    }
    /*displays questions*/
    public void showQuestion(){

        questionView.setText(data.get(quiznums[currentQuestion]).getProblem());
    }




}
