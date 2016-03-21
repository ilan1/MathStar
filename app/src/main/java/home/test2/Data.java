package home.test2;

import java.util.List;

/**
 * Created by christal on 3/19/2016.
 */
public class Data {
    private List<Question> questions;
    public List<Question> getData() {return questions;}
    public void setData(List<Question> data) {
        this.questions = data;
    }

    private static final Data holder = new Data();

    /**
     * A Singleton accessor
     * @return the Singleton instance of Data
     */
    public static Data getInstance() {return holder;}
}