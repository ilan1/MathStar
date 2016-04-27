package home.test2;

import android.app.ActionBar;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ResultsActivity extends AppCompatActivity {

    private ArrayList<Question> questions;
    private ArrayList<String> responses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Results");
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        questions = (ArrayList<Question>) intent.getSerializableExtra("questions");
        responses = intent.getStringArrayListExtra("responses");
        TableLayout resultsTableLayout = (TableLayout) findViewById(R.id.resultsTableLayout);

        for(int i = 0; i < 10; ++i){
            LinearLayout shadow = new LinearLayout(this);
            TextView textView = new TextView(this);
            TableRow row = new TableRow(this);

            String s = "Q" + (i + 1);
            s += " | ";

            if(responses.get(i).equalsIgnoreCase(questions.get(i).getAnswer())) {
                s += "Correct";
                textView.setTextColor(Color.parseColor("#33AA33"));            }
            else {
                s += "Incorrect";
                textView.setTextColor(Color.parseColor("#AA3333"));
            }

            s += " \n\n ";
            s += "Response = " + responses.get(i);
            s += " | ";
            s += "Answer = " + questions.get(i).getAnswer();
            textView.setText(s);

            textView.setPadding(20, 20, 20, 20);
            textView.setBackgroundColor(Color.parseColor("#FFEEEEEE"));
            textView.setWidth(this.getWindowManager().getDefaultDisplay().getWidth());
            shadow.addView(textView);

            shadow.setBottom(20);
            shadow.setPadding(0, 0, 3, 3);
            shadow.setBackgroundColor(Color.parseColor("#FF888888"));
            row.setPadding(0,0,0,40);
            row.addView(shadow);

            final String problem = questions.get(i).getProblem().toString();
            row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final AlertDialog alertDialog;
                    alertDialog = new AlertDialog.Builder(v.getContext()).create();
                    alertDialog.setTitle("Question");
                    alertDialog.setMessage(problem);
                    alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    alertDialog.show();
                }
            });
            resultsTableLayout.addView(row);
        }

    }

}
