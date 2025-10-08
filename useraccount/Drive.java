/** 
 * @author [Ethan Le] 
 * @version 1.0
 * CS151 Fall 2025 - Project 1
 */
package useraccount;

import PDF.*; // Import the HashMap class to access HashMap and its methods.
import java.util.HashMap; 

public class Drive {
    
    /** Drive object variables: */
    private HashMap<String, DocPDF> docsFiles; // HashMap to hold "docs" Drive objects (files). Key = file name, Value = docs Drive object (file). 
    private HashMap<String, Slides> slidesFiles; // HashMap to hold "slides" Drive objects (files). Key = file name, Value = slides Drive object (file). 
    private HashMap<String, Spreadsheet> spreadsheetsFiles; // HashMap to hold "spreadsheets" Drive objects (files). Key = file name, Value = spreadsheets Drive object (file). 
    private final int MAX_DOCS = 3; // Maximum number of Docs that is allowed in the HashMap. 
    private final int MAX_SLIDES = 2; // Maximum number of Slides that is allowed in the HashMap. 
    private final int MAX_SPREADSHEETS = 2; // Maximum number of Spreadsheets that is allowed in the HashMap. 

    /** Constructor to initialize Drive object (created in AccountManager class): */
    protected Drive() {

        this.docsFiles = new HashMap<>(); // Initialize an empty HashMap to hold "docs" Drive objects (files). 
        this.slidesFiles = new HashMap<>(); // Initialize an empty HashMap to hold "slides" Drive objects (files). 
        this.spreadsheetsFiles = new HashMap<>(); // Initialize an empty HashMap to hold "spreadsheets" Drive objects (files). 
    }

    /** Method to create a PDF: */
    public void createPDF(int option, String fileName, String username, String email) { // Parameters are the option number selected from AccountManager class, and the entered file name for the PDF.
/* 
        if (option == 1) // Option 1: create document.
        {
        	if (this.docsFiles.size() < MAX_DOCS) // Check to ensure we have capacity. 
        	{
        		DocPDF doc = new DocPDF(username, email, "OWNER"); // Create a new Doc object. 
	            this.docsFiles.put(fileName, doc); // Add the new Doc object to the docs HashMap, using the file name as the key.
        
        	}
        	
        	else // Print error message stating capacity is maxed out. 
        	{
        		System.out.println("Error! Max capacity reached for Documents! Delete existing Document files to create a new one!");
        	}
        }
*/
        //else 
    	if (option == 2) // Option 2: create slides.
        {
        	if (this.slidesFiles.size() < MAX_SLIDES) // Check to ensure we have capacity.
        	{
	            Slides slides = new Slides(username, email, "OWNER"); // Create a new Slides object. 
	            this.slidesFiles.put(fileName, slides); // Add the new Slides object to the slides HashMap, using the file name as the key. 
        
        	}
        	
        	else // Print error message stating capacity is maxed out. 
        	{
        		System.out.println("Error! Max capacity reached for Slides! Delete existing Slide files to create a new one!");
        	}
        }
        
        else if (option == 3) // Option 3: create spreadsheet.
        {
        	if (this.spreadsheetsFiles.size() < MAX_SPREADSHEETS) // Check to ensure we have capacity.
        	{
	            Spreadsheet sheets = new Spreadsheet(username, email, "OWNER"); // Create a new Spreadsheet object.
	            this.spreadsheetsFiles.put(fileName, sheets); // Add the new Spreadsheet object to the spreadsheets HashMap, using the file name as the key.
        
        	}
        	
        	else // Print error message stating capacity is maxed out. 
        	{
        		System.out.println("Error: Max capacity reached for Spreadsheets! Delete existing Spreadsheet files to create a new one!");
        	}
        }
    }

    /** Method to delete a PDF: */
    public void deletePDF(int option, String fileName) { // Parameters are the option number selected from AccountManager class, and the entered file name for the PDF.
    	
    	if (option == 1) // Document file to be deleted. 
    	{
    		if (docsFiles.containsKey(fileName)) // If the doc file exists in the Drive, 
    		{
    			docsFiles.remove(fileName); // then delete the doc file. 
    		}
    		
    		else 
    		{
    			System.out.println("Error: File does not exist in the Drive!"); 
    		}
    	}
    	
    	else if (option == 2) // Slides file to be deleted. 
    	{
    		if (slidesFiles.containsKey(fileName)) // If the slides file exists in the Drive, 
    		{
    			slidesFiles.remove(fileName); // then delete the slides file. 
    		}
    		
    		else 
    		{
    			System.out.println("Error: File does not exist in the Drive!"); 
    		}
    	}
    	
    	else if (option == 3) // Spreadsheet file to be deleted.
    	{
    		if (spreadsheetsFiles.containsKey(fileName)) // If the spreadsheet file exists in the Drive, 
    		{
    			spreadsheetsFiles.remove(fileName); // then delete the spreadsheet file. 
    		}
    		
    		else 
    		{
    			System.out.println("Error: File does not exist in the Drive!"); 
    		}
    	}
    }
    
    /** Method to edit a PDF: */
    public GenericPDF editPDF(String fileName)
    {
    	if (docsFiles.containsKey(fileName))
    	{
    		DocPDF document = docsFiles.get(fileName); 
    		System.out.println(document.toString());
			document.contextMenu(); // Call method to print user actions for the Slide Deck.
    		return document;
    	}
    	
    	else if (slidesFiles.containsKey(fileName))
    	{
    		Slides slideDeck = slidesFiles.get(fileName); 
    		System.out.println(slideDeck.toString()); 
    		slideDeck.contextMenu(); // Call method to print user actions for the Slide Deck.
    		return slideDeck;
    	}
    	
    	else if (spreadsheetsFiles.containsKey(fileName))
    	{
    		Spreadsheet sheet = spreadsheetsFiles.get(fileName); 
    		System.out.println(sheet.toString());
			sheet.contextMenu(); // Call method to print user actions for the Slide Deck.
    		return sheet;
    	}
    	
    	else
    	{
    		System.out.println("File does not exist in your Drive!"); 
    		return null; 
    	}
    }
}