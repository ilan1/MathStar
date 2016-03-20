package home.test2;

/**
 * Created by christal on 3/17/2016.
 */
public class Question {
    private String subject;
    private String section;
    private String problem;
    private String answer;
    private String id;

    public void setSubject(String subject){
        this.subject = subject;
    }
    public String getSubject(){
        return subject;
    }

    public void setSection(String section){
        this.section = section;
    }
    public String getSection(String section){
        return section;
    }

    public void setProblem(String problem){
        this.problem = problem;
    }
    public String getProblem(String problem){
        return problem;
    }

    public void setAnswer(String answer){
        this.answer = answer;
    }
    public String getAnswer(String answer){
        return answer;
    }

    public void setId(String id){
        this.id = id;
    }
    public int getId(int id){
        return id;
    }

    public String toString(){
        return id +": " + subject + "-" + section + "-" + problem + "=" + answer;
    }

}
