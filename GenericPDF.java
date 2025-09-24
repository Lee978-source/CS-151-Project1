/**
 * @author [Phuong Hua]
 * @version 1.0
 * CS151 Fall 2025 - Project 1 
 */

public abstract class GenericPDF implements Exportable {
    protected String username;
    protected String email;
    protected String role;

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
     }

     public abstract void merge();
     public abstract void split();

     public String getUsername(){
        return username;
     }

     public String getEmail(){
        return email;
     }

     public String getRole(){
        return role;
     }

     public void setUsername(String username) {
        this.username = username;
     }

     public void setEmail(String username) {
        this.email = email;
     }

     public void setRole(String username) {
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

