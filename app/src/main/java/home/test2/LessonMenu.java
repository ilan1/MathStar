package home.test2;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;
import java.util.ArrayList;

import android.widget.Button;
import android.widget.CheckBox;

public class LessonMenu extends AppCompatActivity {
    ArrayList<String> sections = new ArrayList<String>();
    public static String currentLesson;
    //A class-name tag for any Log calls
    private String tag = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState)  {


        getIntent().removeExtra("sectionArray");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_menu);
       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);
        Button backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               Intent i = new Intent(LessonMenu.this, MainActivity.class);
                startActivity(i);
                }

        });


       Button lessonButton = (Button) findViewById(R.id.beginlesson);
        lessonButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                /*passes the list of selected sections to the QuizActivity class*/
                Bundle passSections = new Bundle();

                /*we'll want an if statement somewhere here to go to the correct game*/
                Intent i = new Intent();

                i.putStringArrayListExtra("sectionArray", sections);
                if(sections.contains("Linear Equations") || sections.contains("Classification of Angles")){
                    if(sections.contains("Classification of Angles")){
                        currentLesson = "Classification of Angles";
                    }else currentLesson = "Linear Equations";
                    i = new Intent(LessonMenu.this, BalloonGame.class);
                    startActivity(i);
                  //  Toast.makeText(getApplicationContext(), "You must select at least 1 chapter.", Toast.LENGTH_LONG).show();
                }
                else if(sections.contains("Algebraic Functions") || sections.contains("Right Triangles and Trigonometry")){
                    if(sections.contains("Algebraic Functions")){
                        currentLesson = "Algebraic Functions";
                    }else currentLesson = "Right Triangles and Trigonometry";
                    i = new Intent(LessonMenu.this, TimerGame.class);
                    i.putStringArrayListExtra("sectionArray", sections);

                    startActivity(i);
                }
                else{

                    if(sections.contains("Sequences and Series")){
                        currentLesson = "Sequences and Series";

                    }else
                    {
                        currentLesson = "Measurements of Perimeters, Area, and Volume";

                    }
                    i = new Intent(LessonMenu.this, StoryGame.class);
                    i.putStringArrayListExtra("sectionArray", sections);

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
        boolean checked = ((RadioButton) v).isChecked();
        switch(v.getId())
        {
            case R.id.linearequations:
                if(checked){
                    sections.clear();
                    sections.add("Linear Equations");
                    uncheckRadioButtons("LE");}
                else{
                    sections.remove("Linear Equations");
                    break;}
                break;
            case R.id.algebraicfunctions:
                if(checked){
                    sections.clear();
                    sections.add("Algebraic Functions");
                    uncheckRadioButtons("AF");}
                else{
                    sections.remove("Algebraic Functions");
                    break;}
                break;
            case R.id.sequencesandseries:
                if(checked){
                    sections.clear();
                    sections.add("Sequences and Series");
                    uncheckRadioButtons("SS");}
                else{
                    sections.remove("Sequences and Series");
                    break;}
                break;
            case R.id.classofangles:
                if(checked){
                    sections.clear();
                    sections.add("Classification of Angles");
                    uncheckRadioButtons("CoA");}
                else{
                    sections.remove("Classification of Angles");
                    break;}
                break;
            case R.id.measurements:
                if(checked){
                    sections.clear();
                    sections.add("Measurements of Perimeters, Area, and Volume");
                    uncheckRadioButtons("MPAV");}
                else{
                    sections.remove("Measurements of Perimeters, Area, and Volume");
                    break;}
                break;
            case R.id.trianglestrig:
                if(checked){
                    sections.clear();
                    sections.add("Right Triangles and Trigonometry");
                    uncheckRadioButtons("RTT");}
                else{
                    sections.remove("Right Triangles and Trigonometry");
                    break;}
                break;

        }
    }

    public void uncheckRadioButtons(String s)
    {
        if( !(s.equals("LE"))) {
            ((RadioButton) findViewById(R.id.linearequations)).setChecked(false);
        }
        if( !(s.equals("AF"))) {
            ((RadioButton) findViewById(R.id.algebraicfunctions)).setChecked(false);
        }
        if( !(s.equals("SS"))) {
            ((RadioButton) findViewById(R.id.sequencesandseries)).setChecked(false);
        }
        if( !(s.equals("CoA"))) {
            ((RadioButton) findViewById(R.id.classofangles)).setChecked(false);
        }
        if( !(s.equals("MPAV"))) {
            ((RadioButton) findViewById(R.id.measurements)).setChecked(false);
        }
        if( !(s.equals("RTT"))) {
            ((RadioButton) findViewById(R.id.trianglestrig)).setChecked(false);
        }
    }
}
