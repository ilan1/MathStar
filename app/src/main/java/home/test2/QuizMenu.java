package home.test2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.widget.CheckBox;

public class QuizMenu extends AppCompatActivity {
    ArrayList<String> sections = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    /*saves section selection settings from checkboxes*/
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
            case R.id.algebraicfunctions:
                if(checked){
                sections.add("Algebraic Functions");}
                else{
                    sections.remove("Algebraic Functions");
                    break;}
            case R.id.sequencesandseries:
                if(checked){
                    sections.add("Sequences and Series");}
                else{
                    sections.remove("Sequences and Series");
                    break;}
            case R.id.classofangles:
                if(checked){
                    sections.add("Classification of Angles");}
                else{
                    sections.remove("Classification of Angles");
                    break;}
            case R.id.measurements:
                if(checked){
                    sections.add("Measurements of Perimeters, Area, and Volume");}
                else{
                    sections.remove("Measurements of Perimeters, Area, and Volume");
                    break;}
            case R.id.trianglestrig:
                if(checked){
                    sections.add("Right Triangles and Trigonometry");}
                else{
                    sections.remove("Right Triangles and Trigonometry");
                    break;}

        }
    }

    /*onClick beginQuiz*/
    public void beginQuiz(View v){

    }
}
