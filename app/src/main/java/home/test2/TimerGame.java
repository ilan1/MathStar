package home.test2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class TimerGame extends AppCompatActivity {
    ArrayList<String> sections;
    List<Question> data = Data.getInstance().getData();
    int[] timerNums = new int[10];
    private Button quitButton;
    TextView questionView;
    TextView timerValueText;
    TextView scoreValueText;
    Button answer1;
    Button answer2;
    Button answer3;
    Button answer4;
    int timerValue = 30;
    int scoreValue = 0;
    int j = 0;
    int right;
    boolean noDuplicates;
    String currentAnswer;
    Random r = new Random();
    Intent quizIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_game);

        questionView = (TextView) findViewById(R.id.questionView);
        timerValueText = (TextView) findViewById(R.id.timerValueText);
        scoreValueText = (TextView) findViewById(R.id.scoreValueText);

        answer1 = (Button) findViewById(R.id.answer1);
        answer2 = (Button) findViewById(R.id.answer2);
        answer3 = (Button) findViewById(R.id.answer3);
        answer4 = (Button) findViewById(R.id.answer4);

        quitButton = (Button) findViewById(R.id.quitButton);

        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable(){
                    @Override
                    public void run(){
                        if(timerValue == 0){
                            showNextQuestion(0);
                            timer.cancel();
                        }
                        if(answer1.getText().equals(""))
                        {timer.cancel();}
                        else {
                            timerValue -= 1;
                            timerValueText.setText("" + timerValue);
                            answer1.setBackgroundColor(Color.rgb(211,211,211));
                            answer2.setBackgroundColor(Color.rgb(211,211,211));
                            answer3.setBackgroundColor(Color.rgb(211,211,211));
                            answer4.setBackgroundColor(Color.rgb(211,211,211));
                        }
                    }
                });
            }
        }, 0, 1000);

        quizIntent = getIntent();
        sections = quizIntent.getStringArrayListExtra("sectionArray");

        if (sections.contains("Algebraic Functions")) {
            for (int i = 0; i <= 9; i++) {
                timerNums[i] = 15 + i;
            }
        }
        else {
            for (int i = 0; i <= 9; i++) {
                timerNums[i] = 55 + i;
            }
        }
        gen(j);
    }

    public void gen(int j) {
        quitButton = (Button) findViewById(R.id.quitButton);
        scoreValueText = (EditText) findViewById(R.id.scoreValueText);
        questionView = (EditText) findViewById(R.id.questionView);
        scoreValueText.setText(""+scoreValue);

        if (j == 10) {
            questionView.setText("You finished all of the questions with a score of: " + scoreValue);
            finishGame();
        }
        else if(timerValue == 0) {
            questionView.setText("Sorry your time is up and your score is: " +  scoreValue);
            finishGame();
        }
        else{

            questionView.setText(""+data.get(timerNums[j]).getProblem());

            currentAnswer = data.get(timerNums[j]).getAnswer();

            noDuplicates = false;

            int mid = r.nextInt(18);
            answer1.setText(""+mid);
            mid = r.nextInt(18);
            answer2.setText(""+mid);
            mid = r.nextInt(18);
            answer3.setText(""+mid);
            mid = r.nextInt(18);
            answer4.setText(""+mid);

            right = r.nextInt(4);
            if(right == 3)
            {answer4.setText(""+currentAnswer);}
            else if(right == 2)
            {answer3.setText(""+currentAnswer);}
            else if(right == 1)
            {answer2.setText(""+currentAnswer);}
            else
            {answer1.setText(""+currentAnswer);}

            quitButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {

                    //navigate to Lesson selection page
                    Intent i = new Intent(TimerGame.this, LessonMenu.class);
                    startActivity(i);
                    return;

                }
            });

            answer1.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    if (quitButton.getText().toString().equalsIgnoreCase("FINISH")) {
                        //navigate to Lesson selection page
                        Intent i = new Intent(TimerGame.this, LessonMenu.class);
                        startActivity(i);
                        return;
                    }
                    else
                    {
                        checkAnswer1();
                    }
                }
            });

            answer2.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    if (quitButton.getText().toString().equalsIgnoreCase("FINISH")) {
                        //navigate to Lesson selection page
                        Intent i = new Intent(TimerGame.this, LessonMenu.class);
                        startActivity(i);
                        return;
                    }
                    else
                    {
                        checkAnswer2();
                    }
                }
            });

            answer3.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    if (quitButton.getText().toString().equalsIgnoreCase("FINISH")) {
                        //navigate to Lesson selection page
                        Intent i = new Intent(TimerGame.this, LessonMenu.class);
                        startActivity(i);
                        return;
                    }
                    else
                    {
                        checkAnswer3();
                    }
                }
            });

            answer4.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    if (quitButton.getText().toString().equalsIgnoreCase("FINISH")) {
                        //navigate to Lesson selection page
                        Intent i = new Intent(TimerGame.this, LessonMenu.class);
                        startActivity(i);
                        return;
                    }
                    else
                    {
                        checkAnswer4();
                    }
                }
            });
        }
    }

    public void finishGame(){
        answer1.setText("");
        answer2.setText("");
        answer3.setText("");
        answer4.setText("");

        answer1.setBackgroundColor(Color.rgb(211,211,211));
        answer2.setBackgroundColor(Color.rgb(211,211,211));
        answer3.setBackgroundColor(Color.rgb(211,211,211));
        answer4.setBackgroundColor(Color.rgb(211,211,211));
        quitButton.setText("FINISH");
    }

    public void showNextQuestion(int j){
        gen(j);
    }

    public void checkAnswer1(){
        if (right == 0)
        {
            answer1.setBackgroundColor(Color.GREEN);
            scoreValue += 1;
        }
        else{
            answer1.setBackgroundColor(Color.RED);
        }
        j++;
        showNextQuestion(j);
    }

    public void checkAnswer2(){
        if (right == 1)
        {
            answer2.setBackgroundColor(Color.GREEN);
            scoreValue += 1;
        }
        else{
            answer2.setBackgroundColor(Color.RED);
        }
        j++;
        showNextQuestion(j);
    }

    public void checkAnswer3(){
        if (right == 2)
        {
            answer3.setBackgroundColor(Color.GREEN);
            scoreValue += 1;
        }
        else{
            answer3.setBackgroundColor(Color.RED);
        }
        j++;
        showNextQuestion(j);
    }

    public void checkAnswer4(){
        if (right == 3)
        {
            answer4.setBackgroundColor(Color.GREEN);
            scoreValue += 1;
        }
        else{
            answer4.setBackgroundColor(Color.RED);
        }
        j++;
        showNextQuestion(j);
    }
}
