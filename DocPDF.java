/**
 * @author [Brian Nguyen]
 * @version 1.0
 * CS151 Fall 2025 - Project 1
 */

public class DocPDF extends GenericPDF  {
protected String text;

    public DocPDF(String username, String email, String role, String text) {
        super(username, email, role);
        this.text = text;
    }

    public void addPageBreaker() {
        System.out.println("--------------------");
    }

    public void deleteLatestPage() {

    }

    public int getWordCount() {

    }

    public int getCharCount() {

    }

    @Override
    public String toString() {
        return "GenericPDF{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", PDF Text='" + role + '\'' +
                ", Word Count= " + getWordCount() +
                ", Character Count=" + getCharCount() +
                '}';
    }
}
