/**
 * @author [Lordin Yi]
 * @version 1.0
 * CS151 Fall 2025 - Project 1
 */

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
        		case 1, 2, 3: // Create a PDF file. 
        			System.out.println("Enter a new file name: "); 
        			string = scan.nextLine(); // fileName. 
        			acc.createOption(option, string); 
        			acc.accountMenu(); // Call user account menu. 
        			break;
        		case 4: // Edit a PDF file. 
        			System.out.println("Enter an existing file name: "); 
        			string = scan.nextLine(); // fileName
        			GenericPDF PDF = acc.getDrive().editPDF(string);
        			int editOption = scan.nextInt(); 
        			scan.nextLine(); // Flush out rest of the line to clear the buffer. 
        			if (editOption == 1 && (PDF.getRole() == "OWNER" || PDF.getRole() == "EDITOR")) // Add new content to PDF. 
        			{
        				if (PDF instanceof DocPDF)
        				{
        					
        				}
        				
        				else if (PDF instanceof Slides)
        				{
        					((Slides) PDF).addSlide(); 
        				}
        				
        				else if (PDF instanceof Spreadsheet)
        				{
        					
        				}
        			}
        			
        			else if (editOption == 2 && (PDF.getRole() == "OWNER" || PDF.getRole() == "EDITOR")) // Delete content from PDF. 
        			{
        				if (PDF instanceof DocPDF)
        				{
        					
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
        					
        				}
        			}
        			
        			else if (editOption == 3 && (PDF.getRole() == "OWNER" || PDF.getRole() == "EDITOR")) // Edit content on PDF. 
        			{
        				if (PDF instanceof DocPDF)
        				{
        					
        				}
        				
        				else if (PDF instanceof Slides)
        				{
        					System.out.println("What slide number would you like to add text to? Enter a number: "); 
        					editOption = scan.nextInt(); 
        					scan.nextLine(); // Flush out rest of the line to clear the buffer. 
        					System.out.println("Enter the text that you would like to append to the slide: "); 
        					string = scan.nextLine(); 
        					((Slides) PDF).editText(string, editOption); 
        				}
        				
        				else if (PDF instanceof Spreadsheet)
        				{
        					
        				}
        			}
        			acc.accountMenu(); // Call user account menu. 
        			break;
        		case 5, 6: // View Drive contents. 
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

