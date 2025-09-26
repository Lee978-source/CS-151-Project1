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
     ...
}
