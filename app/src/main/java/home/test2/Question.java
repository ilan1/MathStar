package home.test2;

import java.io.Serializable;

/**
 * Created by christal on 3/17/2016.
 */
public class Question implements Serializable{
    private String subject;
    private String section;
    private String problem;
    private String answer;
    private String id;

    /**
     * Standard setter for subject name
     * @param subject  subject name
     */
    public void setSubject(String subject){
        this.subject = subject;
    }
    public String getSubject(){
        return subject;
    }

    /**
     * Standard setter for section name
     * @param section  section name
     */
    public void setSection(String section){
        this.section = section;
    }

    /**
     * Standard getter for section name
     * @return the section name
     */
    public String getSection(){
        return section;
    }

    /**
     * Standard setter for problem text
     * @param problem  problem text
     */
    public void setProblem(String problem){
        this.problem = problem;
    }

    /**
     * Standard getter for problem text
     * @return the problem text
     */
    public String getProblem(){
        return problem;
    }

    /**
     * Standard setter for the answer text
     * @param answer  the answer text
     */
    public void setAnswer(String answer){
        this.answer = answer;
    }

    /**
     * Standard getter for answer text
     * @return the answer text
     */
    public String getAnswer(){
        return answer;
    }

    /**
     * Standard setter for the Question ID
     * @param id  the id to be set to
     */
    public void setId(String id){
        this.id = id;
    }

    /**
     * Standard getter for the Question ID
     * @return the id of the Question
     */
    public String getId(){
        return id;
    }

    /**
     * A custom toString for Question objects
     * @return a string representation of a Question object
     */
    public String toString(){
        return id +": " + subject + "-" + section + "-" + problem + "=" + answer;
    }

}
