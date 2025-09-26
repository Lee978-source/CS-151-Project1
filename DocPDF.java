/**
 * @author [Brian Nguyen]
 * @version 1.0
 * CS151 Fall 2025 - Project 1
 */

public class DocPDF extends GenericPDF implements Exportable   {
private String textContent;

    public DocPDF(String username, String email, String role, String text) {
        super(username, email, role);
        this.textContent = text;
    }

    public String getText() {
        return textContent;
    }

    public void setText(String text) {
        this.textContent = text;
    }


    public void addPageBreaker() {
        System.out.println("-------------------------");
    }

    public void deleteLatestPage() {

    }

    public int getWordCount() {

    }

    public int getCharCount() {

    }

    public int getPageCount() {

    }

    public void addPageNumber() {

    }

    public void exportAsPDF() {

    }

    public void exportAsHTML() {

    }

    public void exportAsWordDoc() {

    }


    public boolean findWord(String word) {
        if(textContent != null && textContent.contains(word)) {
            System.out.println("Word found: " + word);
            return true;
        }
        else {
            System.out.println("Word was not found");
            return false;
        }
    }

    @Override
    public void merge() {

    }

    @Override
    public void split() {

    }

    @Override
    public String toString() {
        return "GenericPDF{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", PDF Text='" + textContent + '\'' +
                ", Word Count= " + getWordCount() + '\'' +
                ", Character Count=" + getCharCount() + '\'' +
                ", Page Count=" + getPageCount() + '\'' +
                '}';
    }
}
