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
        this.textContent = textContent;

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
        if(pages.size() > 3) {
            System.out.println("Max pages exceeded!" );
        }
        else {
            pages.add("");
            System.out.println("----------------------------------");
            System.out.println("Page " + pages.size() + " created.");
        }
    }

    public void deleteLatestPage() {
        if (!pages.isEmpty()) {
            int last = pages.size() - 1;
            pages.remove(last);
        }
        else {
            System.out.println("No pages to delete!");
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
    // abstract methods
    @Override
    public void merge(GenericPDF file1) {




    }

    @Override
    public GenericPDF split(int splitIndex) {




    }

    @Override
    public void contextMenu() {

        if (this.getListOfRoles().get(username).equals("OWNER")) {
            System.out.println("\n--------------------------------------------------------------------------------");
            System.out.println("Document Menu Options: \n");
            System.out.println("(1) Create new page: \n");
            System.out.println("(2) Add Text to Page: \n");
            System.out.println("(3) Delete Page: \n");
            System.out.println("(4) Find Word: \n");
            System.out.println("(5) Get Word Count: \n");
            System.out.println("(6) Get Char Count: \n");
            System.out.println("(7) Get Page Count: \n");
            System.out.println("(8) Export Slide Deck as PDF");
            System.out.println("(9) Export Slide Deck as HTML");
            System.out.println("(10) Export Slide Deck as Word Document");
            System.out.println("(11) Update User Roles");
            System.out.println("\n--------------------------------------------------------------------------------");
        }
        else if (this.getListOfRoles().get(username).equals("EDITOR")) {
            System.out.println("\n--------------------------------------------------------------------------------");
            System.out.println("Document Menu Options: \n");
            System.out.println("(1) Create new page: \n");
            System.out.println("(2) Add Text to Page: \n");
            System.out.println("(3) Delete Page: \n");
            System.out.println("(4) Find Word: \n");
            System.out.println("(5) Get Word Count: \n");
            System.out.println("(6) Get Char Count: \n");
            System.out.println("(7) Get Page Count: \n");
            System.out.println("(8) Export Slide Deck as PDF");
            System.out.println("(9) Export Slide Deck as HTML");
            System.out.println("(10) Export Slide Deck as Word Document");
            System.out.println("\n--------------------------------------------------------------------------------");
        }

    }


    @Override
    public void exportAsPDF() {
        System.out.println("Exporting Doc as PDF");
    }

    @Override
    public void exportAsHTML() {
        System.out.println("Exporting Doc as HTML");
    }

    @Override
    public void exportAsWordDoc() {
        System.out.println("Exporting Doc as Word Document");
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
