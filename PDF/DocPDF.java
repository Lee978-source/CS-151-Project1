/**
 * @author [Brian Nguyen]
 * @version 1.0
 * CS151 Fall 2025 - Project 1
 */
package PDF;

import java.util.ArrayList;
import java.util.List;
import useraccount.*; 

public class DocPDF extends GenericPDF implements Exportable   {
    private String textContent;
    private List<String> pages = new ArrayList<>();

    public DocPDF(String username, String email, String role) {
        super(username, email, role);
        this.textContent = "";
    }

    public String getText() {
        return this.textContent;
    }

    public void setText(String text) {
        this.textContent = text;
    }

    public void addTextToCurrPage(String text, AccountManager acc) {
        if (this.getListOfRoles().get(acc.getEmail()).equals("OWNER") || this.getListOfRoles().get(acc.getEmail()).equals("EDITOR"))
        {
            if (pages.isEmpty()) {
            pages.add("");
        }
        int last = pages.size() - 1;
        String current = pages.get(last);
        pages.set(last, current + " " + text);

        // update text content
        this.textContent = String.join(" ", this.pages).trim();

        // print confirmation
        System.out.println("Added text to page " + (last + 1));
        }
        else{
            System.out.println("Only OWNER and EDITOR can add text to pages.");
        }
        
    }

    public void addPageBreaker(AccountManager acc) {
        if (this.getListOfRoles().get(acc.getEmail()).equals("OWNER") || this.getListOfRoles().get(acc.getEmail()).equals("EDITOR"))
        {
            System.out.println("\n----------------------------------\n");
            pages.add("");
            System.out.println("Page " + pages.size() + " created.");
            this.textContent = String.join(" ", this.pages).trim();
        }
        else{
            System.out.println("Only OWNER and EDITOR can add pages.");
        }
        
    }

    public void deleteLatestPage(AccountManager acc) {
        if (this.getListOfRoles().get(acc.getEmail()).equals("OWNER") || this.getListOfRoles().get(acc.getEmail()).equals("EDITOR"))
        {
            // checks if the current page is empty, throw exception if no page
        try {
            if (pages.isEmpty()) {
                throw new IllegalStateException("Cannot delete page! Document has no pages to delete... Exiting...");
            }

            // find the index of the last page in the list
            int last = pages.size() - 1;
            pages.remove(last);

            if (pages.isEmpty()) {
                this.textContent = "";
            } else {
                // updates the text content to match after deleting the latest page
                this.textContent = String.join(" ", this.pages).trim();
            }
            System.out.println("Deleted page " + (last + 1) + ". Remaining pages: " + pages.size());

        } catch(IllegalStateException e) {
            System.err.println("Error: " + e.getMessage());
            // if error wasn't caught, try general exception
        } catch(Exception e) {
            System.err.println("Uh oh! Something unexpected happened while deleting page: " + e.getMessage());
        }
        }
        else{
            System.out.println("Only OWNER and EDITOR can delete pages.");
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
        if(textContent != null && textContent.contains(word)) { // if textContent is there and contains word, print true
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
    public void merge(GenericPDF otherDoc) {
        if(otherDoc == null) {
            System.out.println("Cannot merge with null document :(");
            return;
        }
        if(!(otherDoc instanceof DocPDF)) {
            System.out.println("Cannot merge: incompatible type" + otherDoc.getClass().getTypeName());
            return;
        }
        if(!this.getRole().equals("OWNER") && !this.getRole().equals("EDITOR")) {
            System.out.println("You do not have permission to merge document.");
            return;
        }

        DocPDF docToMerge = (DocPDF) otherDoc;

        int originalPageCount = this.getPageCount();
        int newPageCount = docToMerge.getPageCount();

        // merge all pages
        this.pages.addAll(docToMerge.pages);

        // merge all text from both docs
        this.textContent = String.join(" ", this.pages).trim();

        System.out.println("Merge successful! :D");
        System.out.println("Original Page Count: " + originalPageCount);
        System.out.println("New Page Count: " + newPageCount);
    }

    @Override
    public GenericPDF split(int splitIndex) {

        // checks if the split is valid
        if (splitIndex <= 0 || splitIndex >= this.getPageCount()) {
            System.out.println("Invalid split index." + (this.getPageCount() - 1));
            return null;
        }

        // creating new document object
        DocPDF newDoc = new DocPDF(this.username, this.email, this.role);
        newDoc.pages.clear();

        // copy everything from the original document from the index to the end
        // and add them to a list
        List<String> newPages = new ArrayList<>(this.pages.subList(splitIndex, this.pages.size()));

        // add the new pages to the new document
        newDoc.pages.addAll(newPages);

        // remove the pages from the original document
        this.pages.subList(splitIndex, this.pages.size()).clear();


        // if old list has no pages, add an empty string to textContent
        if (this.pages.isEmpty()) {
            this.textContent = "";
            // takes the content of the page and put it together in textContent
        } else {
            this.textContent = String.join(" ", this.pages).trim();
        }

        if (newDoc.pages.isEmpty()) {
            newDoc.textContent = "";
        } else {
            newDoc.textContent = String.join(" ", newDoc.pages).trim();
        }

        // prints out success split with page count for each document
        System.out.println("Split at page " + splitIndex+1
                + " -> this doc has " + this.getPageCount() + " pages"
                + ", new doc has " + newDoc.getPageCount() + " pages.");

        // returns the new document
        return newDoc;
    }

    @Override
    public void contextMenu(AccountManager acc) {

        if (this.getListOfRoles().get(acc.getEmail()).equals("OWNER")) {
            System.out.println("\n--------------------------------------------------------------------------------");
            System.out.println("Document Menu Options: ");
            System.out.println("(1) Create new page: ");
            System.out.println("(2) Delete Page: ");
            System.out.println("(3) Add Text to Page: ");
            System.out.println("(4) Find Word: ");
            System.out.println("(5) Get Word Count: ");
            System.out.println("(6) Get Char Count: ");
            System.out.println("(7) Get Page Count: ");
            System.out.println("(8) Choose document to merge with: ");
            System.out.println("(9) Split document ");
            System.out.println("(10) Export Slide Deck as PDF");
            System.out.println("(11) Export Slide Deck as HTML");
            System.out.println("(12) Export Slide Deck as Word Document");
            System.out.println("(13) Update User Roles");
            System.out.println("--------------------------------------------------------------------------------");
            System.out.println("Your role: " + this.getListOfRoles().get(acc.getEmail()));
        }
        else if (this.getListOfRoles().get(acc.getEmail()).equals("EDITOR")) {
            System.out.println("--------------------------------------------------------------------------------");
            System.out.println("Document Menu Options: ");
            System.out.println("(1) Create new page: ");
            System.out.println("(2) Delete Page: ");
            System.out.println("(3) Add Text to Page: ");
            System.out.println("(4) Find Word: ");
            System.out.println("(5) Get Word Count: ");
            System.out.println("(6) Get Char Count: ");
            System.out.println("(7) Get Page Count: ");
            System.out.println("(10) Export Slide Deck as PDF");
            System.out.println("(11) Export Slide Deck as HTML");
            System.out.println("(12) Export Slide Deck as Word Document");
            System.out.println("--------------------------------------------------------------------------------");
            System.out.println("Your role: " + this.getListOfRoles().get(acc.getEmail()));
        }
        else if (this.getListOfRoles().get(acc.getEmail()).equals("COMMENTER")) {
            System.out.println("Document Menu Options: ");
            System.out.println("(1) Add comment to the end of the document");
            System.out.println("(4) Find Word: ");
            System.out.println("(5) Get Word Count: ");
            System.out.println("(6) Get Char Count: ");
            System.out.println("(7) Get Page Count: ");
            // System.out.println("(2) Add a Hashtag to your slides"); // removed
            System.out.println("(10) Export Slide Deck as PDF");
            System.out.println("(11) Export Slide Deck as HTML");
            System.out.println("(12) Export Slide Deck as Word Document");
            System.out.println("--------------------------------------------------------------------------------");
            System.out.println("Your role: " + this.getListOfRoles().get(acc.getEmail()));
        }
        else {
            // viewer
            System.out.println("Document Menu Options: ");
            System.out.println("(4) Find Word: ");
            System.out.println("(5) Get Word Count: ");
            System.out.println("(6) Get Char Count: ");
            System.out.println("(7) Get Page Count: ");
            System.out.println("(10) Export spreadsheet as PDF");
            System.out.println("(11) Export spreadsheet as HTML");
            System.out.println("(12) Export spreadsheet as Word Document");
            System.out.println("--------------------------------------------------------------------------------");
            System.out.println("Your role: " + this.getListOfRoles().get(acc.getEmail()));
        }

    }

    @Override 
   public void updateUserRole(String email, String newRole, String fileName, AccountManager acc) 
   {
         if (!this.getListOfRoles().get(acc.getEmail()).equals("OWNER")) {
              System.out.println("Only OWNER can update user roles.");
         }
         else if (!newRole.equals("OWNER") && !newRole.equals("EDITOR") && !newRole.equals("VIEWER") && !newRole.equals("COMMENTER")) {
              System.out.println("Invalid role. Valid roles are: OWNER, EDITOR, VIEWER, COMMENTER.");
         }
         
         else 
         {
            for (AccountManager account : AccountManager.getAccountsDatabase())
            {
                if (account.getEmail().equals(email))
                {
                    if (newRole.equals("OWNER")) 
                    {
                        this.getListOfRoles().remove(acc.getEmail()); // Remove current OWNER.
                        this.getListOfRoles().put(acc.getEmail(), "EDITOR"); // Downgrade the current OWNER to EDITOR. 

                        this.setUsername(account.getUsername());
                        this.setEmail(account.getEmail()); 
                        this.roles.put(account.getEmail(), newRole);
                        account.getDrive().getDocsFiles().put(fileName, this); // Update the Docs object in the user's Drive.  
                        System.out.println("Updated " + account.getEmail() + "'s role to " + newRole);
                    }

                    else 
                    {
                        this.roles.put(account.getEmail(), newRole);
                        account.getDrive().getDocsFiles().put(fileName, this); // Update the Docs object in the user's Drive.  
                        System.out.println("Updated " + account.getEmail() + "'s role to " + newRole);
                    }
                    return; 
                }
            }

            System.out.println("No user exists with the email: " + email); 
            
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
        return "DocPDF{" +
                "\nText Content:\n\n\'" + this.textContent + '\'' +
                "\n\nusername= \'" + username + '\'' +
                ", \nemail= \'" + email + '\'' +
                ", \n\nPDF Text=\n\n'" + textContent + '\'' +
                ", \n\nWord Count= \'" + getWordCount() + '\'' +
                ", \nCharacter Count= \'" + getCharCount() + '\'' +
                ", \nPage Count= \'" + getPageCount() + '\'' +
                '}';
    }
}
