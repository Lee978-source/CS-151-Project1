OVERVIEW:
This project simulates a Google Drive-like storage system with user account and PDF management system, allowing users to create accounts, manage drives, and work with different types of document formats, such as documents, slides, and spreadsheets. 
Additionally, this program supports features such as creating, editing, and exporting files, as well as handling user authentication, email simulation, and basic account tasks. 

DESIGN:
There are two packages that this project mainly uses, PDF and userAccount.
The PDF package includes an abstract class of GenericPDF and public classes DocPDF, Slides, and Spreadsheet, and as well as an interface Exportable.
The userAccount package manages the AccountManager class and the drive class. This package also includes the Main file to run the program. 

TO RUN:
1. Clone the Repository, open your terminal and type:
git clone https://github.com/Lee978-source/GoogleDrive-CS-151-fall-2025-Project1.git
cd GoogleDrive-CS-151-fall-2025-Project1

2. Compile the code from root of the repo:
mkdir bin

javac -d bin useraccount/Main.java

3. Run the program:
java -cp bin useraccount.Main

4. Follow the terminal prompts to create accounts, manage files, and edit files with PDF/document/slide/spreadsheet menu features to make changes to your file.
   
