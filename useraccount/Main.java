/**
 * @author [Lordin Yi]
 * @version 1.0
 * CS151 Fall 2025 - Project 1
 */
//testing
package useraccount;

import PDF.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

    	Scanner scan = new Scanner(System.in);

        // Create one demo account (constructor also creates its Drive)
        AccountManager acc = new AccountManager(
                "Alice Nguyen",
                "alice@gmail.com",
                "secret123",
                01, 
                01, 
                2000
        );

        boolean finished = false; // Flag to mark when user is done using the program. 

        acc.accountMenu(); // Call user account menu.

        while (finished == false)
        {
        	System.out.println(); 
        	System.out.println("What would you like to do? Enter option number: ");

        	int option = scan.nextInt(); 
        	scan.nextLine(); // Flush out rest of the line to clear the buffer. 

        	String string = null; 

        	switch (option)
        	{
        		case 1: // Create a PDF file. 
        			System.out.println("Enter a new file name: "); 
        			string = scan.nextLine(); // fileName. 
        			acc.createOption(option, string); 
        			acc.accountMenu(); // Call user account menu. 
        			break;
				case 2: // Create a PDF file. 
        			System.out.println("Enter a new file name: "); 
        			string = scan.nextLine(); // fileName. 
        			acc.createOption(option, string); 
        			acc.accountMenu(); // Call user account menu. 
        			break;
				case 3: // Create a PDF file. 
        			System.out.println("Enter a new file name: "); 
        			string = scan.nextLine(); // fileName. 
        			acc.createOption(option, string); 
        			acc.accountMenu(); // Call user account menu. 
        			break;
        		case 4: // Edit a PDF file.
                    System.out.println("Enter an existing file name: ");
                    string = scan.nextLine(); // fileName
                    GenericPDF PDF = acc.getDrive().editPDF(string);

                    if (PDF instanceof DocPDF) {
                        boolean editingDoc = true;
                        while (editingDoc) {
                            ((DocPDF) PDF).contextMenu();
                            System.out.println("Choose an option for this document (0 to exit to Main Menu):");
                            int editOption = scan.nextInt();
                            scan.nextLine();

                            switch (editOption) {
                                case 1:
                                    ((DocPDF) PDF).addPageBreaker();
                                    break;
                                case 2:
                                    ((DocPDF) PDF).deleteLatestPage();
                                    break;
                                case 3:
                                        System.out.print("Enter text to add: ");
                                        String text = scan.nextLine();
                                        ((DocPDF) PDF).addTextToCurrPage(text);
                                    break;
                                case 4:
                                        System.out.print("Enter word to search: ");
                                        String word = scan.nextLine();
                                        ((DocPDF) PDF).findWord(word);
                                    break;
                                case 5:
                                        System.out.println("Word count: " + ((DocPDF) PDF).getWordCount());
                                    break;
                                case 6:
                                        System.out.println("Char count: " + ((DocPDF) PDF).getCharCount());
                                    break;
                                case 7:
                                        System.out.println("Page count: " + ((DocPDF) PDF).getPageCount());
                                    break;
                                case 8:
                                    ((DocPDF)PDF).exportAsPDF();
                                    break;
                                case 9:
									((DocPDF)PDF).exportAsHTML();
                                    break;
                                case 10:
									((DocPDF)PDF).exportAsWordDoc();
                                    break;
                                case 0:
                                    editingDoc = false;
                                    System.out.println("Returning to Main Menu...");
                                    break;
                                default:
                                    System.out.println("Invalid option. Please try again.");
                            }
                        }
                        acc.accountMenu();
                        break;

					} else if (PDF instanceof Slides) {
						boolean editingSlides = true;
						while (editingSlides) {
							((Slides) PDF).contextMenu();
							System.out.println(((Slides)PDF).toString());
							System.out.println("Choose an option for this document (0 to exit to Main Menu):");
							int editOption = scan.nextInt();
							scan.nextLine();

							switch (editOption) {
								case 1:
									((Slides) PDF).addSlide();
									break;
								case 2:
									System.out.print("Enter slide number to delete: ");
									int slideIndex = scan.nextInt();
									scan.nextLine();
									int deleteNum = slideIndex - 1; // Convert to zero-based index
									((Slides) PDF).deleteSlide(deleteNum);
									
									break;
								case 3:
									System.out.print("Enter slide number to edit: ");
									slideIndex = scan.nextInt();
									scan.nextLine();
									System.out.print("Enter text to add: ");
									String text = scan.nextLine();
									int editNum = slideIndex - 1; // Convert to zero-based index
									((Slides) PDF).editSlide(text, editNum);
									
									break;
								case 4:
									System.out.print("Enter filename of second slide deck to merge: ");
									String name2 = scan.nextLine();
									GenericPDF other = acc.getDrive().editPDF(name2);
									if (other == null) {
										System.out.println("File not found. Cannot merge.");
									} else if (!(other instanceof Slides)) {
										System.out.println("Cannot merge. The file is not a slide deck.");
									} else {
										((Slides) PDF).merge(other);
									}
									break;
								case 5:
									System.out.print("Enter the slide number you want to split at: ");
									int splitIndex = scan.nextInt();
									scan.nextLine();
									int splitNum = splitIndex - 1;
									GenericPDF newSplit = ((Slides) PDF).split(splitNum);
									if (newSplit != null) {
										System.out.println("Deck successfully split. New deck created.");
									} else {
										System.out.println("Split index out of bounds. No split performed.");
									}
									break;
								case 6:
									System.out.print("Enter first slide order: ");
									int index1 = scan.nextInt();
									System.out.print("Enter second slide order: ");
									int index2 = scan.nextInt();
									scan.nextLine();
									int userIndex1 = index1; // Convert to zero-based index
									int userIndex2 = index2; // Convert to zero-based index
									((Slides) PDF).swapSlideOrder(userIndex1, userIndex2);
									break;
								case 7:
									((Slides)PDF).exportAsPDF();;
									break;
								case 8:
									((Slides)PDF).exportAsHTML();
									break;
								case 9:
									((Slides)PDF).exportAsWordDoc();
									break;
								case 10:
									System.out.println("User roles editing not implemented here.");
									// update user role?
									break;
								case 0:
									editingSlides = false;
									System.out.println("Returning to Main Menu...");
									break;
								default:
									System.out.println("Invalid option. Please try again.");
							}
						
						}
						acc.accountMenu();
						break;
					} else if (PDF instanceof Spreadsheet) {
						boolean editingSpreadSheet = true;
						while (editingSpreadSheet) {
							((Spreadsheet) PDF).contextMenu();
							System.out.println("Choose an option for this document (0 to exit to Main Menu):");
							int editOption = scan.nextInt();
							scan.nextLine();

							switch (editOption) {
								case 1:
									System.out.println("Enter 1 to add row, 2 to add column: ");
									int rowOrCol = scan.nextInt();
									scan.nextLine(); // consume newline character
									if(rowOrCol == 1) {
										((Spreadsheet) PDF).addRow();
									}
									else if (rowOrCol == 2) {
										((Spreadsheet) PDF).addCol();
									}
									else {
										System.out.println("Invalid input....");
									}
									break;
								case 2:
									System.out.println("Enter 1 to delete row, 2 to delete column: ");
									rowOrCol = scan.nextInt();
									scan.nextLine(); // consume newline character
									if(rowOrCol == 1) {
										((Spreadsheet) PDF).deleteRow();
									}
									else if (rowOrCol == 2) {
										((Spreadsheet) PDF).deleteRow();
									}
									else {
										System.out.println("Invalid input....");
									}
									break;
								case 3:
									System.out.println("Enter row number: ");
									int row = scan.nextInt();
									System.out.println("Enter column number: ");
									int col = scan.nextInt(); scan.nextLine();
									System.out.print("Enter text to add: ");
									String text = scan.nextLine();
									((Spreadsheet) PDF).editCell(text, row-1, col-1); // convert to 0 index. 
									break;
								case 4:
									System.out.print("Enter name of second Spreadsheet to merge: ");
									String fileName = scan.nextLine();
									Spreadsheet other = (Spreadsheet) acc.getDrive().editPDF(fileName);
									((Spreadsheet) PDF).merge(other);
									break;
								case 5:
									System.out.println("Enter column to split: ");
									int splitIndex = scan.nextInt();
									scan.nextLine();
									((Spreadsheet) PDF).split(splitIndex-1); // convert to 0 index. 
									break;
								case 6:
									System.out.println("Please choose which cell row and cell col to switch....");
									System.out.print("First cell row: ");
									int r1 = scan.nextInt();
									System.out.print("First cell col: ");
									int c1 = scan.nextInt();
									System.out.print("Second cell row: ");
									int r2 = scan.nextInt();
									System.out.print("Second cell col: ");
									int c2 = scan.nextInt();
									scan.nextLine(); // consume newline character
									((Spreadsheet) PDF).swapCells(r1-1,c1-1,r2-1,c2-1); // convert to 0 index. 
									break;
								case 7:
									System.out.println("Please choose which cell content to view");
									System.out.print("Cell row: ");
									r1 = scan.nextInt();
									System.out.print("Cell col: ");
									c1 = scan.nextInt();
									scan.nextLine();
									((Spreadsheet) PDF).viewCell(r1-1,c1-1); // convert to 0 index. 
									break;
								case 8:
									((Spreadsheet)PDF).exportAsPDF();
									break;
								case 9:
									((Spreadsheet)PDF).exportAsHTML();
									break;
								case 10:
									((Spreadsheet)PDF).exportAsWordDoc();
									break;
								case 11:
									// update user role? don't think we need to do this
									break;
								case 0:
									editingSpreadSheet = false;
									System.out.println("Returning to Main Menu...");
									break;
								default:
									System.out.println("Invalid option. Please try again.");
							}
						}
                    } else {
                        System.out.println("Unknown or unsupported PDF type.");
                    }
                    break;

                    /*
        			int editOption = scan.nextInt(); 
        			scan.nextLine(); // Flush out rest of the line to clear the buffer. 
        			if (editOption == 1 && (PDF.getRole() == "OWNER" || PDF.getRole() == "EDITOR")) // Add new content to PDF. 
        			{
        				if (PDF instanceof DocPDF)
        				{
                            ((DocPDF) PDF).addPageBreaker(); // JUST ADDED
        				}
        				
        				else if (PDF instanceof Slides)
        				{
        					((Slides) PDF).addSlide(); 
        				}
        				
        				else if (PDF instanceof Spreadsheet)
        				{
							System.out.println("Enter 1 to add a new row. Enter 2 to add a new column. Enter a number: "); 
        					editOption = scan.nextInt(); 

							if (editOption == 1)
							{
								((Spreadsheet) PDF).addRow(); // Add a new row to the sheet if the user entered 1. 
							}

							else if (editOption == 2)
							{
								((Spreadsheet) PDF).addCol(); // Add a new column to the sheet if the user entered 2. 
							}
        				}
        			}
        			
        			else if (editOption == 2 && (PDF.getRole() == "OWNER" || PDF.getRole() == "EDITOR")) // Delete content from PDF. 
        			{
        				if (PDF instanceof DocPDF)
        				{
        					System.out.println("Deleting current page..."); // JUST ADDED
                            ((DocPDF) PDF).deleteLatestPage();
        				}
        				
        				else if (PDF instanceof Slides)
        				{
        					System.out.println("What slide number would you like to delete? Enter a number: ");
        					editOption = scan.nextInt(); 
        					scan.nextLine(); // Flush out rest of the line to clear the buffer. 
        					((Slides) PDF).deleteSlide(editOption); 
        				}
        				
        				else if (PDF instanceof Spreadsheet)
        				{
        					System.out.println("Enter 1 to delete a row. Enter 2 to delete a column. Enter a number: "); 
        					editOption = scan.nextInt(); 
							scan.nextLine(); // Flush out rest of the line to clear the buffer. 

							if (editOption == 1)
							{
								((Spreadsheet) PDF).deleteRow(); // Add a new row to the sheet if the user entered 1. 
							}

							else if (editOption == 2)
							{
								((Spreadsheet) PDF).deleteCol(); // Add a new column to the sheet if the user entered 2. 
							}
        				}
        			}
        			
        			else if (editOption == 3 && (PDF.getRole() == "OWNER" || PDF.getRole() == "EDITOR")) // Edit content on PDF. 
        			{
        				if (PDF instanceof DocPDF)
        				{
        					System.out.println("What text do you want to add to the current page?"); // JUST ADDED
                            scan.nextLine();
                            string = scan.nextLine();
                            ((DocPDF) PDF).addTextToCurrPage(string);
                        }
        				
        				else if (PDF instanceof Slides)
        				{
        					System.out.println("What slide number would you like to add text to? Enter a number: "); 
        					editOption = scan.nextInt(); 
        					scan.nextLine(); // Flush out rest of the line to clear the buffer. 
        					System.out.println("Enter the text that you would like to append to the slide: "); 
        					string = scan.nextLine(); 
							((Slides) PDF).editSlides(string, editOption);
					}
        				
        				else if (PDF instanceof Spreadsheet)
        				{
        					System.out.println("What row number would you like to add text to? Enter a number: "); 
        					editOption = scan.nextInt(); 
							scan.nextLine(); // Flush out rest of the line to clear the buffer. 
							System.out.println("What column number would you like to add text to? Enter a number: ");
							int editOption2 = scan.nextInt(); 
        					scan.nextLine(); // Flush out rest of the line to clear the buffer. 
        					System.out.println("Enter the text that you would like to append to the slide: "); 
        					string = scan.nextLine(); 
        					((Spreadsheet) PDF).editCell(string, editOption, editOption2); 
        				}
        			}
        			acc.accountMenu(); // Call user account menu. 
        			break;


                     */
        		case 5: // View Drive contents. 
        			acc.viewDriveOption(option);
        			break;
				case 6: // View Drive contents. 
        			acc.viewDriveOption(option); 
        			break;
        		case 7: // View Inbox Emails. 
        			if (acc.getNumberOfEmails() > 0) // Condition if user has at least one email.
        			{
        				System.out.println("You have " + acc.getNumberOfEmails() + " emails! Read which email? (Enter any number from 1 to " + acc.getNumberOfEmails() + "): "); // Tell user how many emails they currently have, and ask them for a number for what email to read.
	        			int emailNumber = scan.nextInt(); 
	        			scan.nextLine(); // Flush out rest of the line to clear the buffer. 
	        			acc.accountMenu(); // Call user account menu. 
	        			acc.fetchEmail(emailNumber);
        			}
        			else // Condition if user has no emails. 
        			{
        				acc.accountMenu(); // Call user account menu.
        	    		System.out.println("You have " + acc.getNumberOfEmails() + " emails! No emails to read!"); // Tell user they have no emails. 
        			}
        	    	break;
        		case 8: // Send an email. 
        			System.out.println("Enter recipient email: "); 
        			string = scan.nextLine(); // recipient
        			System.out.println("Enter message to send: "); 
        			String message = scan.nextLine(); 
        			acc.accountMenu(); // Call user account menu.
        			acc.sendEmail(string, message);
        			break;
        		case 9: // Get Account Info. 
        			acc.accountMenu(); // Call user account menu.
        			System.out.println(acc.toString());
        			break;
        		case 10: // Change password. 
        			System.out.println("Enter your old password: ");
        			string = scan.nextLine(); // oldPassword
        			if (string.equals(acc.getPassword()))
        			{
        				System.out.println("Enter your new password (must be at least 8 characters long): ");
        				string = scan.nextLine(); // newPassword 
        				acc.accountMenu(); // Call user account menu.
        				acc.setPassword(string);
        			}
        			else
        			{
        				acc.accountMenu(); // Call user account menu.
        				System.out.println("Old password does not match! Try again!"); 
        			}
        			break;
        		case 11: // Change username. 
        			System.out.println("Enter your new username: ");
        			string = scan.nextLine(); // newUsername
        			acc.accountMenu(); // Call user account menu.
        			acc.setUsername(string);
        			break;
        		case 12: // Change email. 
        			System.out.println("Enter your new email: ");
        			string = scan.nextLine(); // newEmail
        			acc.accountMenu(); // Call user account menu.
        			acc.setEmail(string);
        			break;
        		case 13: // Change date of birth. 
        			System.out.println("Enter your new month of birth (Integer): ");
        			Integer newMonth = scan.nextInt();
        			System.out.println("Enter your new day of birth (Integer): ");
        			Integer newDay = scan.nextInt();
        			System.out.println("Enter your new year of birth (Integer): ");
        			Integer newYear = scan.nextInt();
        			acc.accountMenu(); // Call user account menu.
        			acc.setDateOfBirth(newMonth, newDay, newYear);
        			break;
        		case 14: // Logout. 
        			acc = null; // Sign out. 
        			while (acc == null && finished == false)
        			{
        				AccountManager.programMenu(); // Print the program menu (user is signed out). 

                    	option = scan.nextInt(); 
                    	scan.nextLine(); // Flush out rest of the line to clear the buffer. 

                    	switch (option)
                    	{
                    		case 1: // Create an account. 
                    			System.out.println("Enter a username (enter CANCEL to cancel): ");
                    			String username = scan.nextLine(); 
                    			if (username.equals("CANCEL"))
                    				break; 
                    			System.out.println("Enter an email (must contain \"@gmail.com\"): ");
                    			String email = scan.nextLine(); 
                    			System.out.println("Enter a password (must be at least 8 characters long): ");
                    			String password = scan.nextLine();
                    			System.out.println("Enter your month of birth (Integer): ");
                    			Integer month = scan.nextInt();
                    			System.out.println("Enter your day of birth (Integer): ");
                    			Integer day = scan.nextInt();
                    			System.out.println("Enter your year of birth (Integer): ");
                    			Integer year = scan.nextInt();
                    			acc = new AccountManager( // If any of the fields are invalid as developed in the constructor, all fields will be assigned as "null" by default. 
                    	                username,
                    	                email,
                    	                password,
                    	                month, 
                    	                day,
                    	                year
                    	        );                   			
                    			// Fields being assigned as "null" when account creation fails are used to determine whether acc=null (to determine if user is signed into their new account or not):
                    			if (acc.getUsername() == null || acc.getEmail() == null || acc.getPassword() == null || acc.getDateOfBirth() == null)
                    				acc = null;
                    			else
                    				acc.accountMenu(); 
                    			break; 
                    		case 2: // Sign in.                     			
                    			while (acc == null)
                    			{
                    				System.out.println("Enter your email (enter CANCEL to cancel): ");
                        			email = scan.nextLine(); 
                        			if (email.equals("CANCEL"))
                        				break; 
                        			System.out.println("Enter your password: ");
                        			password = scan.nextLine(); 
                        			acc = AccountManager.login(email, password); 
                        			if (acc == null)
                        				System.out.println("Invalid email and/or password! Try again!\n");		
                    			} 
                    			break; 
                    		case 3: // Exit the program. 
                    			finished = true;  
                    			break; 
                    	} 
        			}        			
                	break; 
        	}
        }


        scan.close(); 
        System.out.println("Program terminated! Thank you for using!"); 
/*
        // To simulate a login
        // on success it will print the account menu
        acc.login("alice@gmail.com", "secret123");

        // call menu directly to always show it:
        // acc.accountMenu();

        System.out.println("\n--- Demo finished ---");*/
    }

}