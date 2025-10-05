/**
 * @author [Brian Nguyen]
 * @version 1.0
 * CS151 Fall 2025 - Project 1
 */
package PDF; 

import java.util.ArrayList;
import java.util.List;

public class DocPDF extends GenericPDF implements Exportable   {
    private String textContent;
    private List<String> pages = new ArrayList<>();


    public DocPDF(String username, String email, String role, String textContent) {
        super(username, email, role);

        if (textContent == null) {
            pages.add("");
        } else {
            pages.add(textContent);
        }
    }

    public String getText() {
        return textContent;
    }

    public void setText(String text) {
        this.textContent = text;
    }

    public void addTextToCurrPage(String text) {
        int last = pages.size() - 1;
        pages.set(last, pages.get(last) + " " + text);
    }

    public void addPageBreaker() {
        pages.add("");
        System.out.println("Page " + pages.size() + " created.");
    }

    public void deleteLatestPage() {
        if (!pages.isEmpty()) {
            int last = pages.size() - 1;
            pages.remove(last);
        }
        else {
            System.out.println("No pages to delete");
        }
    }

    public int getWordCount() {
        int total = 0;
        for(String page : pages) {
            if(page != null && !page.trim().isEmpty()) {                // checks if the page is empty and makes sure after trimming
                total += page.trim().split("\\s+").length;        // that the page is not empty
            }
        }
        return total;   // returns the total word count in the page
    }

    public int getCharCount() {
        int total = 0;
        for(String page : pages) {
            if(page != null && !page.trim().isEmpty()) {
                total += page.trim().length();
            }
    }
        return total;
    }

    public int getPageCount() {
        return pages.size();
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
        System.out.println("Merging doc with another doc");
    }

    @Override
    public void split() {
        System.out.println("Splitting doc into separate doc");
    }

    @Override
    public void exportAsPDF() {
        System.out.println("Exporting slides as PDF");
    }

    @Override
    public void exportAsHTML() {
        System.out.println("Exporting slides as HTML");
    }

    @Override
    public void exportAsWordDoc() {
        System.out.println("Exporting slides as Word Document");
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
