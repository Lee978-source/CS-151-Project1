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
    private final HashMap<String, DocPDF> docsFiles; // HashMap to hold "docs" Drive objects (files). Key = file name, Value = docs Drive object (file). 
    private final HashMap<String, Slides> slidesFiles; // HashMap to hold "slides" Drive objects (files). Key = file name, Value = slides Drive object (file). 
    private final HashMap<String, Spreadsheet> spreadsheetsFiles; // HashMap to hold "spreadsheets" Drive objects (files). Key = file name, Value = spreadsheets Drive object (file). 
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
    public void createPDF(String option, String fileName, String username, String email) { // Parameters are the option number selected from AccountManager class, and the entered file name for the PDF.

        if (option.equals("1")) // Option 1: create document.
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

        //else 
    	if (option.equals("2")) // Option 2: create slides.
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
        
        else if (option.equals("3")) // Option 3: create spreadsheet.
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
    public void deletePDF(String option, String fileName) { // Parameters are the option number selected from AccountManager class, and the entered file name for the PDF.
    	
    	if (option.equals("1")) // Document file to be deleted. 
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
    	
    	else if (option.equals("2")) // Slides file to be deleted. 
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
    	
    	else if (option.equals("3")) // Spreadsheet file to be deleted.
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
    		
    		return document;
    	}
    	
    	else if (slidesFiles.containsKey(fileName))
    	{
    		Slides slideDeck = slidesFiles.get(fileName); 
    		
    		return slideDeck;
    	}
    	
    	else if (spreadsheetsFiles.containsKey(fileName))
    	{
    		Spreadsheet sheet = spreadsheetsFiles.get(fileName); 
    		
    		return sheet;
    	}
    	
    	else
    	{
    		System.out.println("File does not exist in your Drive!"); 
    		return null; 
    	}
    }

	/** Method to view all File titles in the Drive: */
	public void viewAllFiles() 
	{
		System.out.println("Your existing files in your Drive:"); 

		if (this.getDocsFiles().isEmpty() && this.getSlidesFiles().isEmpty() && this.getSheetFiles().isEmpty()) // Check if you have any existing files in your Drive first. 
		{
			System.out.println("No files currently exist in your Drive!"); 
		}

		else 
		{
			for (String docTitle : this.getDocsFiles().keySet()) // Loop through all the titles (Keys from HashMap) of existing Doc files in the Drive. 
			{
				System.out.println("Document: " + docTitle); 
			}

			for (String slideTitle : this.getSlidesFiles().keySet()) // Loop through all the titles (Keys from HashMap) of existing Slide files in the Drive. 
			{
				System.out.println("Slide Deck: " + slideTitle); 
			}

			for (String sheetTitle : this.getSheetFiles().keySet()) // Loop through all the titles (Keys from HashMap) of existing Spreadsheet files in the Drive. 
			{
				System.out.println("Spreadsheet: " + sheetTitle); 
			}
		}
	}

	 
	/** toString() method to check storage of the Drive and compare it to max limits: */
	@Override
	public String toString()
	{
		return "Current Drive Storage:\n" +
			"Documents: " + this.getDocsFiles().size() + " created out of " + this.getMaxDocLimit() + " max limit.\n" +
			"Slides: " + this.getSlidesFiles().size() + " created out of " + this.getMaxSlidesLimit() + " max limit.\n" +
			"Spreadsheets: " + this.getSheetFiles().size() + " created out of " + this.getMaxSheetLimit() + " max limit."; 
	}

	/** Getter methods for Drive object variables: */
	public HashMap<String, DocPDF> getDocsFiles() 
	{
		return docsFiles; 
	}

	public HashMap<String, Slides> getSlidesFiles() 
	{
		return slidesFiles; 
	}

	public HashMap<String, Spreadsheet> getSheetFiles() 
	{
		return spreadsheetsFiles; 
	}

	public int getMaxDocLimit()
	{
		return MAX_DOCS; 
	}

	public int getMaxSlidesLimit()
	{
		return MAX_SLIDES; 
	}

	public int getMaxSheetLimit()
	{
		return MAX_SPREADSHEETS; 
	}

}