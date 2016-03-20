package home.test2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class QuizActivity extends AppCompatActivity {

    private Button submitButton;
    TextView questionView;
    private int currentQuestion = 1;
    List<Question> data = Data.getInstance().getData();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*display code here somehow*/
        initialize();
    }

    public void initialize(){
        submitButton = (Button) findViewById(R.id.submitButton);
        questionView = (TextView) findViewById(R.id.questionView);
        showQuestion();
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // check();
            }
        });
    }
    public void showQuestion(){
        currentQuestion++;
        questionView.setText(data.get(0).getProblem());
    }
}
