import java.util.*;
/**
 * @author [Phuong Hua]
 * @version 1.0 
 * CS151 Fall 2025 - Project 1 
 */
public class Slides extends GenericPDF {
    private int slideCount;
    private String sequence;

    /**
     * @param username Creator's username
     * @param email Creator's email
     * @param role User's role
     */

     public Slides(String username, String email, String role) {
        super(username, email, role);
        this.slideCount = 0;
        this.sequence = "";
     }

     public void addSlide(){
        slideCount++;
        updateSequence();
        System.out.println("Slide added. Total slides: " + slideCount);
     }

     public void deleteSlide(){
        if(slideCount > 1){
            slideCount--;
            updateSequence();
            System.out.println("Slide deleted. Total slides: " + slideCount);
        } else {
            System.out.println("No slides to delete.");
        }
     }

     public void swapSlideOrder() {
        if (slideCount >= 2) {
            System.out.println("Slide order swapped.");
        } else {
            System.out.println("Not enough slides to swap.");
        }
     }
     public void addHashTag(String tag) {
        System.out.println("Hashtag '" + tag + "' added to the slides.");
     }

     private void updateSequence() {
        StringBuilder seqBuilder = new StringBuilder();
        for (int i = 1; i <= slideCount; i++) {
            seqBuilder.append(i);
            if (i < slideCount) {
                seqBuilder.append("->");
            }
        }
        sequence = seqBuilder.toString();
     }
    @Override
    public void merge() {
        System.out.println("Merging presentation with another presentation");
    }

    @Override
    public void split() {
        System.out.println("Splitting presentation into separate presentations");
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

    public String getSequence() {
        return sequence;
    }

    public void setSlideCount(int slideCount) {
        this.slideCount = slideCount;
        updateSequence();
    }

    public void setSequence(String sequence) {
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
}
}

