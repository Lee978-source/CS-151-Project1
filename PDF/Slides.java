package PDF;




import java.util.*;
import useraccount.*;
/**
* @author [Phuong Hua]
* @version 1.0
* CS151 Fall 2025 - Project 1
*/
public class Slides extends GenericPDF {
  private int slideCount;
  private ArrayList<String> sequence;




  /**
   * @param username Creator's username
   * @param email Creator's email
   * @param role User's role
   */




   public Slides(String username, String email, String role) {
      super(username, email, role);
      this.slideCount = 0;
      this.sequence = new ArrayList<>();
   }


   @Override
   public void contextMenu(AccountManager acc) {
   if (this.getListOfRoles().get(acc.getEmail()).equals("OWNER")) {
       System.out.println("Select option (enter a number): ");
       System.out.println("(1) Add a slide");
       System.out.println("(2) Delete a slide");
       System.out.println("(3) Add text to a slide");
       System.out.println("(4) Merge two Slide Decks");
       System.out.println("(5) Split two Slide Decks");
       System.out.println("(6) Swap slide order between 2 slides");
       // System.out.println("(7) Add a Hashtag to your slides"); // removed
       System.out.println("(7) Export Slide Deck as PDF");
       System.out.println("(8) Export Slide Deck as HTML");
       System.out.println("(9) Export Slide Deck as Word Document");
       System.out.println("(10) Update User Roles");
       System.out.println("Your role: " + this.getListOfRoles().get(acc.getEmail()));
   } else if (this.getListOfRoles().get(acc.getEmail()).equals("EDITOR")) {
       System.out.println("Select option (enter a number): ");
       System.out.println("(1) Add a slide");
       System.out.println("(2) Delete a slide");
       System.out.println("(3) Add text to a slide");
       System.out.println("(4) Merge two Slide Decks");
       System.out.println("(5) Split two Slide Decks");
       System.out.println("(6) Swap slide order between 2 slides");
       // System.out.println("(7) Add a Hashtag to your slides"); // removed
       System.out.println("(7) Export Slide Deck as PDF");
       System.out.println("(8) Export Slide Deck as HTML");
       System.out.println("(9) Export Slide Deck as Word Document");
       System.out.println("Your role: " + this.getListOfRoles().get(acc.getEmail()));
   } else if (this.getListOfRoles().get(acc.getEmail()).equals("COMMENTER")) {
       System.out.println("Select option (enter a number): ");
       System.out.println("(1) Add comment to the last slide");
       // System.out.println("(2) Add a Hashtag to your slides"); // removed
       System.out.println("(7) Export Slide Deck as PDF");
       System.out.println("(8) Export Slide Deck as HTML");
       System.out.println("(9) Export Slide Deck as Word Document");
       System.out.println("Your role: " + this.getListOfRoles().get(acc.getEmail()));
   } else { // Viewer
       System.out.println("Select option (enter a number): ");
       // System.out.println("(1) Add a Hashtag to your slides"); // removed
       System.out.println("(7) Export Slide Deck as PDF");
       System.out.println("(8) Export Slide Deck as HTML");
       System.out.println("(9) Export Slide Deck as Word Document");
       System.out.println("Your role: " + this.getListOfRoles().get(acc.getEmail()));
   }
}


 
   public void editSlide(String newText, int slideNumber, AccountManager acc) {
    if (this.getListOfRoles().get(acc.getEmail()).equals("OWNER") || this.getListOfRoles().get(acc.getEmail()).equals("EDITOR"))
    {
        if (slideNumber < 0 || slideNumber >= slideCount) {
        throw new SlideException("Invalid slide number! Valid slides are from 1 to " + slideCount + ".");
    }
        String existingText = this.getSequence().get(slideNumber);
        sequence.set(slideNumber, existingText.concat(newText));
        System.out.println("New text added to slide " + (slideNumber + 1));
        /*if (this.getSequence() != null)
        {
            String existingText = this.getSequence().get(slideNumber); // Fetch the current existing text at specified slide number.
            
            this.getSequence().remove(slideNumber); // Remove the current existing slide first to avoid duplicates.
            
            this.getSequence().add(slideNumber, existingText.concat(newText)); // Append the new text with the existing text, then insert this at the specified slide number.
            
            System.out.println("New text appended to slide " + slideNumber);
        }*/
    }   
    else{
        System.out.println("Only OWNER and EDITOR can edit slides.");
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
                        account.getDrive().getSlidesFiles().put(fileName, this); // Update the Slides object in the user's Drive.  
                        System.out.println("Updated " + account.getEmail() + "'s role to " + newRole);
                    }

                    else 
                    {
                        this.roles.put(account.getEmail(), newRole);
                        account.getDrive().getSlidesFiles().put(fileName, this); // Update the Slides object in the user's Drive.  
                        System.out.println("Updated " + account.getEmail() + "'s role to " + newRole);
                    }
                    return; 
                }
            }

            System.out.println("No user exists with the email: " + email); 
            
         }
   }




   public void addSlide(AccountManager acc){
      if (this.getListOfRoles().get(acc.getEmail()).equals("OWNER") || this.getListOfRoles().get(acc.getEmail()).equals("EDITOR"))
      {
        slideCount++;
        this.getSequence().add("");
        System.out.println("Slide added. Total slides: " + slideCount);
      }

      else{
        System.out.println("Only OWNER and EDITOR can add slides.");
      }
   }




   public void deleteSlide(int slideNumber, AccountManager acc){
      /*if(slideCount > 0){
          slideCount--;
          this.getSequence().remove(slideNumber);
          System.out.println("Slide deleted. Total slides: " + slideCount);
      } else {
          System.out.println("No slides to delete!");
      }*/


        // Enhanced error handling with exceptions
       if (this.getListOfRoles().get(acc.getEmail()).equals("OWNER") || this.getListOfRoles().get(acc.getEmail()).equals("EDITOR"))
       {
            if (slideCount == 0) {
               throw new SlideException("No slides to delete!");
           }
           if (slideNumber < 0 || slideNumber >= slideCount) {
               throw new SlideException("Invalid slide number! Valid slides are from 1 to " + slideCount + ".");
           }
           slideCount--;
           this.getSequence().remove(slideNumber);
           System.out.println("Slide " + (slideNumber + 1) + " deleted. Total slides: " + slideCount);
       }

       else{
        System.out.println("Only OWNER and EDITOR can delete slides.");
      }

       }




   public void swapSlideOrder(int firstIndex, int secondIndex, AccountManager acc) {
      // Enhanced error handling with exceptions
      if (this.getListOfRoles().get(acc.getEmail()).equals("OWNER") || this.getListOfRoles().get(acc.getEmail()).equals("EDITOR"))
    {
        firstIndex--; 
        secondIndex--;// Convert to 0-based index
        try {
            if (firstIndex < 0 || secondIndex < 0 || firstIndex >= slideCount || secondIndex >= slideCount) {
                throw new SlideException("Invalid slide indices!");
            }
            if (firstIndex == secondIndex) {
                throw new SlideException("No swap needed; indices are the same :)");
            }
        } catch (SlideException e) {
            System.out.println(e.getMessage());
            return;
        }

        String temp = sequence.get(firstIndex);
        String temp2 = sequence.get(secondIndex);
        sequence.set(firstIndex, temp2);
        sequence.set(secondIndex, temp);
        System.out.println("Swapped slides at order " + firstIndex + " and " + secondIndex);
    }

    else 
    {
        System.out.println("Only OWNER and EDITOR can swap slides.");
    }
   }

  @Override
  public void merge(GenericPDF other, AccountManager acc) {
    
      if (!(other instanceof Slides)) {
    
              System.out.println("Can only merge with another Slides document!");
              return;
          }
          Slides otherSlides = (Slides) other;


/*
      if (otherSlides == null) {
              System.out.println("Cannot merge with null Slides :(");
              return;
          }*/
        
      if (!this.getListOfRoles().get(acc.getEmail()).equals("OWNER") && !this.getListOfRoles().get(acc.getEmail()).equals("EDITOR")) {
          System.out.println("Only OWNER and EDITOR can merge slides.");
          return;
      }


      int originalSlideCount = this.slideCount;
      this.sequence.addAll(otherSlides.sequence);
      this.slideCount = this.sequence.size();




      System.out.println("Original slide count: " + originalSlideCount);
      System.out.println("Merged slide count: " + this.slideCount);
      System.out.println("Merged slides. Total slides: " + this.slideCount + " (added " + otherSlides.sequence.size() + " slides)");
  }






  @Override
  public GenericPDF split(int splitIndex, AccountManager acc) {
    
    if (this.getListOfRoles().get(acc.getEmail()).equals("OWNER") || this.getListOfRoles().get(acc.getEmail()).equals("EDITOR"))
    {
        if (splitIndex <= 0 || splitIndex >= this.slideCount) {
          System.out.println("Invalid split index." + (this.slideCount - 1));
          return null;
      }


       Slides newSlides = new Slides(this.username, this.email, this.role);
    
       ArrayList<String> newSequence = new ArrayList<>(this.sequence.subList(splitIndex, this.slideCount));
       this.sequence.subList(splitIndex, this.slideCount).clear();

       newSlides.sequence = newSequence;
       newSlides.slideCount = newSequence.size();


       this.slideCount = this.sequence.size();

       System.out.println("Original slide count after split: " + this.slideCount);
       System.out.println("New slide count after split: " + newSlides.slideCount);
       System.out.println("Slides split at index " + splitIndex);
       return newSlides;
    }

    else 
    {
        System.out.println("Only OWNER and EDITOR can split slides.");
        return null; 
    }
    
  }


  @Override
  public void  exportAsPDF() {
      System.out.println("Exporting slides as PDF");
      for (int i = 0; i < sequence.size(); i++) {
          System.out.println(sequence.get(i));
      }
      //System.out.println(sequence);

  }


  @Override
  public void exportAsHTML() {
      System.out.println("Exporting slides as HTML");
      //for loop for each slide content in a new slide
      for (String slide : sequence) {
          System.out.println("<div class='slide'>" + slide + "</div>");
      }

  }

   @Override
  public void exportAsWordDoc() {
      System.out.println("Exporting slides as Word Document");
      //for loop as one long string with appending arrows (->) between each slide
      for (String slide : sequence) {
        if (!slide.equals(sequence.get(sequence.size() - 1))) {
          System.out.print(slide + " -> ");
      } 
    } System.out.println(sequence.get(sequence.size() - 1));
  }




  public int getSlideCount() {
      return slideCount;
  }




  public ArrayList<String> getSequence() {
      return sequence;
  }




  public void setSlideCount(int slideCount) {
      this.slideCount = slideCount;
      //updateSequence();
  }




  public void setSequence(ArrayList<String> sequence) {
      this.sequence = sequence;
  }




  /**
   * @return String containing object state
   */
  @Override
  public String toString() {
      return "Slides{" +
              "slideCount=" + slideCount +
              ", sequence='" + sequence + '\'' +
              ", username='" + getUsername() + '\'' +
              ", email='" + getEmail() + '\'' +
              ", role='" + getRole() + '\'' +
              '}';
  }
 
  public class SlideException extends RuntimeException {
      public SlideException(String message) {
          super(message);
      }
  }
}
