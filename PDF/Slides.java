package PDF;




import java.util.*;
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
   public void contextMenu() {
   if (this.getListOfRoles().get(username).equals("OWNER")) {
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
   } else if (this.getListOfRoles().get(username).equals("EDITOR")) {
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
   } else if (this.getListOfRoles().get(username).equals("COMMENTER")) {
       System.out.println("Select option (enter a number): ");
       System.out.println("(1) Add comment to the last slide");
       // System.out.println("(2) Add a Hashtag to your slides"); // removed
       System.out.println("(2) Export Slide Deck as PDF");
       System.out.println("(3) Export Slide Deck as HTML");
       System.out.println("(4) Export Slide Deck as Word Document");
   } else { // Viewer
       System.out.println("Select option (enter a number): ");
       // System.out.println("(1) Add a Hashtag to your slides"); // removed
       System.out.println("(1) Export Slide Deck as PDF");
       System.out.println("(2) Export Slide Deck as HTML");
       System.out.println("(3) Export Slide Deck as Word Document");
   }
}


 
   public void editSlide(String newText, int slideNumber) {
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




   public void addSlide(){
      slideCount++;
      this.getSequence().add("");
      System.out.println("Slide added. Total slides: " + slideCount);
   }




   public void deleteSlide(int slideNumber){
      /*if(slideCount > 0){
          slideCount--;
          this.getSequence().remove(slideNumber);
          System.out.println("Slide deleted. Total slides: " + slideCount);
      } else {
          System.out.println("No slides to delete!");
      }*/


        // Enhanced error handling with exceptions
       
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




   public void swapSlideOrder(int firstIndex, int secondIndex) {
      /*if (firstIndex < 0 || secondIndex < 0 || firstIndex >= slideCount || secondIndex >= slideCount) {
          System.out.println("Invalid slide indices.");
          return;
      } if (firstIndex == secondIndex) {
          System.out.println("No swap needed; indices are the same.");
          return;
   }
   String temp = sequence.get(firstIndex);
   String temp2 = sequence.get(secondIndex);
      sequence.set(firstIndex, temp2);
      sequence.set(secondIndex, temp);
      System.out.println("Swapped slides at indices " + firstIndex + " and " + secondIndex);*/


      // Enhanced error handling with exceptions
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
       System.out.println("Swapped slides at indices " + firstIndex + " and " + secondIndex);
   }




   /*public void addHashTag(String tag) {
      System.out.println("Hashtag '" + tag + "' added to the slides.");
   }
/*
   private void updateSequence() {
      StringBuilder seqBuilder = new StringBuilder();
      for (int i = 1; i <= slideCount; i++) {
          seqBuilder.append(i);
          if (i < slideCount) {
              seqBuilder.append("->");
          }
      }
      sequence = seqBuilder.toString();
   }*/
  @Override
  public void merge(GenericPDF other) {
    
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
        
      if (!this.getRole().equals("OWNER") && !this.getRole().equals("EDITOR")) {
          System.out.println("You do not have permission to merge slides.");
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
  public GenericPDF split(int splitIndex) {
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


  @Override
  public void exportAsPDF() {
      System.out.println("Exporting slides as PDF");
  }


  @Override
  public void exportAsHTML() {
      System.out.println("Exporting slides as HTML");
  }


   @Override
  public void exportAsWordDoc() {
      System.out.println("Exporting slides as Word Document");
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
