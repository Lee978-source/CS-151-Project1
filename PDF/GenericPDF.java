package PDF;

/**
 * @author [Phuong Hua]
 * @version 1.0
 * CS151 Fall 2025 - Project 1 
 */

import java.util.HashMap; 

public abstract class GenericPDF implements Exportable {
    protected String username;
    protected String email;
    protected String role;
    protected HashMap<String, String> roles; // Key = username, Value = role. 

    /**
     * Constructor for GenericPDF
     * @param username The username of the document creator
     * @param email The email of the document creator  
     * @param role The role of the user (OWNER, EDITOR, VIEWER, COMMENTER)
     */

     public GenericPDF(String username, String email, String role) {
        this.username = username;
        this.email = email;
        this.role = role;
        this.roles = new HashMap<>(); 
        
        this.roles.put(this.getUsername(), this.getRole()); 
     }

     public abstract void merge(GenericPDF file1, GenericPDF file2);
     public abstract GenericPDF split(int splitIndex);     
     public abstract void contextMenu();

     public String getUsername(){
        return username;
     }

     public String getEmail(){
        return email;
     }

     public String getRole(){
        return role;
     }
     
     public HashMap<String, String> getListOfRoles()
     {
    	 return this.roles; 
     }

     public void setUsername(String username) {
        this.username = username;
     }

     public void setEmail(String email) {
        this.email = email;
     }

     public void setRole(String role) {
        this.role = role;
     }

     /**
      * @return String containing object state
    */

    @Override
    public String toString() {
        return "GenericPDF{" + 
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}

