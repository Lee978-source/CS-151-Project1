/** 
 * @author [Ethan Le] 
 * @version 1.0
 * CS151 Fall 2025 - Project 1
 */
 package useraccount; 

 import java.util.ArrayList; // Import ArrayList class to access ArrayList and methods. 

 public class AccountManager {

    /** General ArrayList to hold user accounts: */
    private ArrayList<AccountManager> accounts = new ArrayList<>(); // Overall ArrayList that holds all existing user accounts. 

    /** User Information Fields: */
    private String username; // First and last name. 
    private String email; // Email address (must have "@gmail.com" at the end). 
    private String password; // Password (set minimum length to it).
    private String dateOfBirth; // Date of birth. 

    /** Account Attributes: */
    private ArrayList<String> inbox; // ArrayList to hold all emails the user receives. 
    private Drive drive; // Drive object that will hold all files (Docs, Slides, Spreadsheets). 

    /** Constructor to initialize User Account object: */
    public AccountManager(String username, String email, String password, String dateOfBirth) {
        
        // Initialize Account Information: 
        this.username = username; 
        this.email = email; 
        this.password = password; 
        this.dateOfBirth = dateOfBirth;

        // Initalize Account Attributes: 
        this.inbox = new ArrayList<>(); // Initialize empty inbox for the new user account. 
        this.drive = new Drive(); // Initialize empty Drive for the new user account. 

        // Add the new account object to the General ArrayList of accounts: 
        accounts.add(this); // Add this instance (object) of AccountManager to the "accounts" ArrayList. 
    }

    /** Method to validate user login: */
    public void login(String email, String password) {

        // Method to handle user login (match the email and password). 
        for (AccountManager account : accounts) // Loop through each existing account in the general "accounts" ArrayList until we find a match.
        {
            if (account.email.equals(email) && account.password.equals(password)) // If the email and password match, then the user can log in. 
            {
                System.out.println("User successfully logged in! Welcome, " + account.username + "!"); // Give welcome message to user. 

                accountMenu(); // Then call the account menu method to print menu selection of options that the user can do. 
            }
        }
    }

    /** Method to print menu options for user to select: */
    public void accountMenu() {

        System.out.println("What would you like to do? Enter option number: \n" + "-------------------------------"); 
        
        // Drive Menu Options:
        System.out.println("Drive Menu Options: \n");
        System.out.println("(1) Create Document"); 
        System.out.println("(2) Create Slides"); 
        System.out.println("(3) Create Slides.Spreadsheet");
        System.out.println("(4) Edit PDF");  
        System.out.println("(5) View Drive File Names"); 
        System.out.println("(6) View Drive Storage\n" + "-------------------------------"); 

        // Account Menu Options: 
        System.out.println("Account Menu Options: \n");
        System.out.println("(7) View Inbox Emails"); 
        System.out.println("(8) Send Email"); 
        System.out.println("(9) Get Account Info"); 
        System.out.println("(10) Change password"); 
        System.out.println("(11) Logout"); 
    }

 }