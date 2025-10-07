/** 
 * @author [Ethan Le] 
 * @version 1.0
 * CS151 Fall 2025 - Project 1
 */
 package useraccount; 

 import java.util.ArrayList; // Import ArrayList class to access ArrayList and methods. 

 public class AccountManager {

    /** General ArrayList to hold user accounts: */
    private static ArrayList<AccountManager> accounts = new ArrayList<>(); // Overall ArrayList that holds all existing user accounts (only one to belong to WHOLE AccountManager class). 

    /** User Information Fields: */
    private String username; // First and last name. 
    private String email; // Email address (must have "@gmail.com" at the end). 
    private String password; // Password (set minimum length to it).
    private String dateOfBirth; // Date of birth. 

    /** Account Attributes: */
    private ArrayList<String> inbox; // ArrayList to hold all emails the user receives. 
    private Drive drive; // Drive object that will hold all files (Docs, Slides, Spreadsheets). 

    /** Constructor to initialize User Account object: */
    public AccountManager(String username, String email, String password, Integer month, Integer day, Integer year) {
        
    	if (!email.contains("@gmail.com")) // Ensure that the email has "@gmail.com" in it. 
    	{
    		System.out.println("Account creation failed! Email must contain \"@gmail.com\"!"); 
    	}
    	
    	else if (password.strip().length() < 8) // Ensure that the password is 8 characters long, excluding any white spaces. 
    	{
    		System.out.println("Account creation failed! Password must be at least 8 characters long!"); 
    	}
    	
    	else if (username.isBlank()) // Ensure that the username is NOT just blank with white spaces. 
    	{
    		System.out.println("Account creation failed! Username must not be blank!"); 
    	}
    	
    	else if (month.toString().isBlank() || day.toString().isBlank() || year.toString().isBlank()) // Ensure that the date of birth is NOT just blank with white spaces. 
    	{
    		System.out.println("Account creation failed! Date of birth must not be blank!"); 
    	}
    	
    	else 
    	{
    		// Initialize Account Information: 
            this.username = username; 
            this.email = email; 
            this.password = password; 
            this.dateOfBirth = month.toString() + "/" + day.toString() + "/" + year.toString();

            // Initalize Account Attributes: 
            this.inbox = new ArrayList<>(); // Initialize empty inbox for the new user account. 
            this.drive = new Drive(); // Initialize empty Drive for the new user account. 

            // Add the new account object to the General ArrayList of accounts: 
            accounts.add(this); // Add this instance (object) of AccountManager to the "accounts" ArrayList.
            
            System.out.println("Account creation successful! Welcome to your new account!"); 
    	}
    }

    /** Method to validate user login: */
    public static AccountManager login(String email, String password) {

        // Method to handle user login (match the email and password). 
        for (AccountManager account : getAccountsDatabase()) // Loop through each existing account in the general "accounts" ArrayList until we find a match.
        {
            if (account.getEmail().equals(email) && account.getPassword().equals(password)) // If the email and password match, then the user can log in. 
            {
            	account.accountMenu(); // Call the account menu method to print menu selection of options that the user can do. 

                System.out.println("User successfully logged in! Welcome, " + account.username + "!"); // Then give welcome message to user.                                
                
                return account; 
            }
        }
        
        return null; 
    }

    /** Method to print menu options for user to select (when signed in): */
    public void accountMenu() {

        System.out.println("\n--------------------------------------------------------------------------------"); 
        
        // Drive Menu Options:
        System.out.println("Drive Menu Options: \n");
        System.out.println("(1) Create Document\t(2) Create Slides\t\t(3) Create Spreadsheet");      
        System.out.println("(4) Edit PDF\t\t(5) View Drive File Names\t(6) View Drive Storage\n--------------------------------------------------------------------------------");  

        // Account Menu Options: 
        System.out.println("Account Menu Options: \n");
        System.out.println("(7) View Inbox Emails\t\t(8) Send Email\t\t(9) Get Account Info"); 
        System.out.println("(10) Change password\t\t(11) Change username\t(12) Change email"); 
        System.out.println("(13) Change date of birth\t(14) Logout\n--------------------------------------------------------------------------------");
        
        //System.out.println("What would you like to do? Enter option number: "); 
    }
    
    /** Static Method to print program options for user to select (when signed out): */ 
    public static void programMenu() {
    	
    	System.out.println("\nChoose an option number to create an account, sign in, or exit the program: ");
    	
    	// Program Menu Options:
        System.out.println("Program Menu Options: \n");
        System.out.println("(1) Create an account"); 
        System.out.println("(2) Sign in"); 
        System.out.println("(3) Exit Program");
    }
    
    /** Getter methods for each private property of the user account: */
    public String getUsername()
    {
    	return this.username; 
    }
    
    public String getEmail()
    {
    	return this.email; 
    }
    
    public String getPassword()
    {
    	return this.password; 
    }
    
    public String getDateOfBirth()
    {
    	return this.dateOfBirth; 
    }
    
    public ArrayList<String> getInbox()
    {
    	return this.inbox; 
    }
    
    public Drive getDrive()
    {
    	return this.drive; 
    }
    
    public static ArrayList<AccountManager> getAccountsDatabase()
    {
    	return accounts; // Static variable of the whole class, so there is no "this." instance. 
    }

    /** Method to take user option for creating or editing PDF: */
    public void createOption(int option, String fileName) // In "Main" class, user will be prompted to enter the File Name if they have selected Options 1-3 ("Main" class would call this method). 
    {
    	switch (option) {
    		case 1:
    			this.getDrive().createPDF(option, fileName, getUsername(), getEmail()); // Create a new PDF based on user option, their scanned file name, and their username and email.
    			break; 
            case 2:
    			this.getDrive().createPDF(option, fileName, getUsername(), getEmail()); // Create a new PDF based on user option, their scanned file name, and their username and email.
    			break;
            case 3:
    			this.getDrive().createPDF(option, fileName, getUsername(), getEmail()); // Create a new PDF based on user option, their scanned file name, and their username and email.
    			break;
    		default: // Option 4 will explicitly call the Drive.java method "editPDF()". 
    			break; 
    	}
    }
    
    /** Method to take user option for viewing Drive contents: */
    public void viewDriveOption(int option) // Call this method in the "Main" class if the user had selected Options 5-6. 
    {
    	switch (option) {
		    case 5: 
				this.getDrive().viewAllFileTitles(); // View all of the existing files in the Drive. 
				break; 
			case 6: 
				this.getDrive().checkStorage(); // Check used Drive storage and remaining Drive storage. 
				break; 
    	}
    }
    
    /** Method to return number of emails in inbox (another Getter method): */
    public int getNumberOfEmails() // Call this method FIRST in the "Main" class if the user had selected Option 7. 
    {
    	return this.getInbox().size(); // Return the number of emails to the user. The "Main" class will use this number to print a message to the user either asking them to enter an email number to read (if inbox size > 0), or a message that just tells them they have no emails to read (else inbox size == 0). 
    }
    
    /** Method to read specified email: */ 
    public void fetchEmail(int emailNumber) // Call this method NEXT in the "Main" class (from Option 7) once the user has entered an email number to read. 
    {
    	System.out.println("Email " + emailNumber + ":\n" + this.getInbox().get(emailNumber-1)); // Print the message number and the message itself. 
    }
    
	/** Method to send an email: */ 
    public void sendEmail(String recipient, String message) // In "Main" class, user will be prompted to enter recipient email and the message before calling this method (if they have chosen Option 8). 
    {
    	boolean sent = false; 
    	
    	for (AccountManager account : getAccountsDatabase()) // Loop through all existing accounts in the database to ensure the recipient email exists. 
    	{
    		if (account.getEmail().equals(recipient)) // If the recipient email exists, 
    		{
    			account.getInbox().add("To: " + account.getUsername() + "\n(" + account.getEmail() + ")" + "\n\n" + message + "\n\nFrom: " + this.getUsername() + "\n(" + this.getEmail() + ")"); // then send the message to their inbox. 
    			System.out.println("Email sent to " + account.getUsername() + " successfully!"); 
    			sent = true; // Mark the "sent" flag to true. 
    			break; 
    		}
    	}
    	
    	if (sent == false) // Use the "sent" flag to send user error message if user did not enter a valid recipient email. 
    	{
    		System.out.println("User does not exist! Please enter a valid recipient email!"); 
    	}
    }
    
    /** Method to get the user's account information: */ 
    @Override
    public String toString() // In "Main" class, call this method if user has selected Option 9. 
    {
    	return "Username: " + this.getUsername() + "\nEmail: " + this.getEmail() + "\nPassword: " + this.getPassword() + "\nDate of Birth: " + this.getDateOfBirth() + "\nNumber of Emails in Inbox: " + this.getNumberOfEmails(); 
    }
	
    /** Setter methods for each private property of the user account: */
    
    /** Method to change the user's password: */
    public void setPassword(String newPassword) // In "Main" class, if user has selected Option 10, have them first enter their old password, then call "getPassword()" from this class to get the old password to see if it matches, then have user enter NEW password and call this method to change it. 
    {
    	if (newPassword.strip().length() >= 8) // Ensure that the password is 8 characters long, excluding any white spaces.
    	{
    		this.password = newPassword; 
    		System.out.println("Password change successful!"); 
    	}
    	else
    	{
    		System.out.println("Password change failed! Password must be at least 8 characters long!"); 
    	}
    }
    
    /** Method to change the user's username: */
    public void setUsername(String newUsername) // In "Main" class, if user has selected Option 11, prompt them to enter new username. 
    {
    	if (newUsername.isBlank()) // Ensure that the username is NOT just blank with white spaces. 
    	{
    		System.out.println("Username change failed! Username must not be blank!"); 
    	}
    	
    	else
    	{
    		this.username = newUsername; 
    		System.out.println("Username change successful!"); 
    	}
    }
    
    /** Method to change the user's email: */
    public void setEmail(String newEmail) // In "Main" class, if user has selected Option 12, prompt them to enter new email. 
    {
    	if (newEmail.contains("@gmail.com")) // Ensure that the email has "@gmail.com" in it. 
    	{
    		this.email = newEmail; 
    		System.out.println("Email change successful!"); 
    	}
    	
    	else
    	{
    		System.out.println("Email change failed! Email must contain \"@gmail.com\"!"); 
    	}
    }
    
    /** Method to change the user's date of birth: */
    public void setDateOfBirth(Integer newMonth, Integer newDay, Integer newYear) // In "Main" class, if user has selected Option 13, prompt them to enter new date of birth. 
    {
    	if (newMonth.toString().isBlank() || newDay.toString().isBlank() || newYear.toString().isBlank()) // Ensure that the date of birth is NOT just blank with white spaces. 
    	{
    		System.out.println("Birthdate change failed! Date of birth must not be blank!"); 
    	}
    	
    	else 
    	{
    		this.dateOfBirth = newMonth.toString() + "/" + newDay.toString() + "/" + newYear.toString(); 
    		System.out.println("Date of Birth change successful!"); 
    	}
    }
 }
