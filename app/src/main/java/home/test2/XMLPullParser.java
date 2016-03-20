package home.test2;

import android.content.res.AssetManager;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by christal on 3/19/2016.
 */
public class XMLPullParser {
    private XmlPullParserFactory xmlFactoryObject;
    private XmlPullParser parser;

    public List<Question> parse(InputStream stream) throws Exception{
        xmlFactoryObject = XmlPullParserFactory.newInstance();
        parser = xmlFactoryObject.newPullParser();
        parser.setInput(stream, null);
        int event = parser.getEventType();

        List<Question> questions = new ArrayList<>();
        Question question = null;
        String currentAttribute = "";
        while (event != XmlPullParser.END_DOCUMENT){
            if(event == XmlPullParser.START_TAG) {
                String tagName = parser.getName();
                if(tagName.equals("questions")) {

                }
                else if(tagName.equals("question")) {
                    question = new Question();
                }
                else if(tagName.equals("section")) {
                    currentAttribute = "section";
                }
                else if(tagName.equals("problem")) {
                    currentAttribute = "problem";
                }
                else if(tagName.equals("answer")) {
                    currentAttribute = "answer";
                }
                else if(tagName.equals("id")) {
                    currentAttribute = "id";
                }
                else if(tagName.equals("subject")) {
                    currentAttribute = "subject";
                }
                else {
                    Log.i("mathstar", "Unexpected tag: " + parser.getName());
                }
             }
            else if(event == XmlPullParser.END_TAG) {
                if(parser.getName().equals("question")) {
                    questions.add(question);
                }
            }
            else if(event == XmlPullParser.TEXT) {
                Log.i("mathstar", parser.getText());
                if(currentAttribute.equals("section")) {
                    question.setSection(parser.getText());
                }
                else if(currentAttribute.equals("problem")) {
                    question.setProblem(parser.getText());
                }
                else if(currentAttribute.equals("answer")) {
                    question.setAnswer(parser.getText());
                }
                else if(currentAttribute.equals("id")) {
                    question.setId(parser.getText());
                }
                else if(currentAttribute.equals("subject")) {
                    question.setSubject(parser.getText());
                }
                currentAttribute = "";
            }
            event = parser.next();
        }

        return questions;
    }
}
