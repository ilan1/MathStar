package home.test2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import java.util.ArrayList;

import android.widget.Button;
import android.widget.CheckBox;

public class LessonMenu extends AppCompatActivity {
    ArrayList<String> sections = new ArrayList<String>();

    //A class-name tag for any Log calls
    private String tag = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState)  {

        getIntent().removeExtra("sectionArray");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button quizButton = (Button) findViewById(R.id.beginquiz);
        quizButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                /*passes the list of selected sections to the QuizActivity class*/
                Bundle passSections = new Bundle();

                /*we'll want an if statement somewhere here to go to the correct game*/
                Intent i = new Intent(LessonMenu.this, BalloonGame.class);

                i.putStringArrayListExtra("sectionArray", sections);
                if(sections.size() == 0){
                    Toast.makeText(getApplicationContext(), "You must select at least 1 chapter.", Toast.LENGTH_LONG).show();
                }
                else{
                    startActivity(i);
                }
            }
        });
    }

    /**
     * Saves section selection settings from checkboxes
     * @param v - a view that encapsulates checkboxes
     */
    public void selectSection(View v){
        boolean checked = ((CheckBox) v).isChecked();
        switch(v.getId())
        {
            case R.id.linearequations:
                if(checked){
                    sections.add("Linear Equations");}
                else{
                    sections.remove("Linear Equations");
                    break;}
                break;
            case R.id.algebraicfunctions:
                if(checked){
                    sections.add("Algebraic Functions");}
                else{
                    sections.remove("Algebraic Functions");
                    break;}
                break;
            case R.id.sequencesandseries:
                if(checked){
                    sections.add("Sequences and Series");}
                else{
                    sections.remove("Sequences and Series");
                    break;}
                break;
            case R.id.classofangles:
                if(checked){
                    sections.add("Classification of Angles");}
                else{
                    sections.remove("Classification of Angles");
                    break;}
                break;
            case R.id.measurements:
                if(checked){
                    sections.add("Measurements of Perimeters, Area, and Volume");}
                else{
                    sections.remove("Measurements of Perimeters, Area, and Volume");
                    break;}
                break;
            case R.id.trianglestrig:
                if(checked){
                    sections.add("Right Triangles and Trigonometry");}
                else{
                    sections.remove("Right Triangles and Trigonometry");
                    break;}
                break;

        }
    }
}
