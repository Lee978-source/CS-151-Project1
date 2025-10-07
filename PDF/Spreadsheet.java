     /**
     * @author [Lordin Yi]
     * @version 1.0
     * CS151 Fall 2025 - Project 1
     */
    package PDF;

import java.util.ArrayList;

public class Spreadsheet extends GenericPDF {
        private int rows, cols;
        private String[][] matrix;

        public Spreadsheet(String username, String email, String role) {
            super(username, email, role);
            this.rows = Math.min(rows, 10);   // limit size
            this.cols = Math.min(cols, 10);
            this.matrix = new String[this.rows][this.cols];
        }

        public void addCell(int r, int c, String value) {
            if (in(r, c)) { matrix[r][c] = value; System.out.println("Set ("+r+","+c+") = " + value); }
            else System.out.println("Out of bounds");
        }

        public void deleteCell(int r, int c) {
            if (in(r, c)) { matrix[r][c] = null; System.out.println("Cleared ("+r+","+c+")"); }
            else System.out.println("Out of bounds");
        }

        public String viewCell(int r, int c) {
            return in(r, c) ? String.valueOf(matrix[r][c]) : "Out of bounds";
        }

        public void swapCells(int r1, int c1, int r2, int c2) {
            if (in(r1,c1) && in(r2,c2)) {
                String t = matrix[r1][c1]; matrix[r1][c1] = matrix[r2][c2]; matrix[r2][c2] = t;
                System.out.println("Swapped ("+r1+","+c1+") with ("+r2+","+c2+")");
            } else System.out.println("Out of bounds");
        }

        private boolean in(int r, int c) { return r>=0 && r<rows && c>=0 && c<cols; }

        @Override
    public void merge(GenericPDF otherSheet) {
        if (otherSheet == null) {
            System.out.println("Cannot merge with null Sheet :(");
            return;
        }
        if (!this.getRole().equals("OWNER") && !this.getRole().equals("EDITOR")) {
            System.out.println("You do not have permission to merge slides.");
            return;
        }
        int originalRow = this.rows;
        int originalCol = this.cols; 

        this.rows += ((Spreadsheet) otherSheet).rows;
        this.cols += ((Spreadsheet) otherSheet).cols;

        String[][] newMatrix = new String[this.rows][this.cols]; // New matrix with new size. 

        for (int row = 0; row < originalRow; row++) // Old content from OG sheet has the same indices. 
        {
            for (int col = 0; col < originalCol; col++)
            {
                newMatrix[row][col] = this.matrix[row][col]; 
            }
        }

        for (int row = 0; row < this.rows; row++) // New content from other sheet shifted by original size.
        {
            for (int col = 0; col < this.cols; col++)
            {
                newMatrix[this.rows+row][this.cols+col] = this.matrix[row][col]; 
            }
        }

        this.matrix = newMatrix; // Update reference to the NEW matrix. 

        System.out.println("Original sheet row count: " + originalRow + "\nOriginal sheet col count: " + originalCol);
        System.out.println("Merged sheet row count: " + this.rows + "\nMerged sheet col count: " + this.cols);
        System.out.println("Merged sheets. Total cells: " + this.rows * this.cols);
    }

    @Override
    public Spreadsheet split(int splitIndex) {
        if (splitIndex <= 0 || splitIndex >= this.cols) {
            System.out.println("Invalid split index at column." + (this.cols - 1));
            return null;
        }
        
         String[][] newMatrix = new String[this.rows][this.cols - splitIndex];
         String[][] oldMatrix = new String[this.rows][splitIndex];

         for (int row = 0; row < this.rows; row++) // Split content into two matrices. 
         {
             for (int col = 0; col < this.cols; col++)
             {
                 if (col < splitIndex) // If we are still in the left "half" of the Spreadsheet, keep the old content in the same indices.
                     oldMatrix[row][col] = this.matrix[row][col]; 
                 else // otherwise, if we are still in the right "half" of the Spreadsheet, place the remaining content in left-shifted indices. 
                     newMatrix[row][col - splitIndex] = this.matrix[row][col]; 
             }
         }

         this.matrix = oldMatrix; // Update reference to the OLD matrix (left half of the content)

         System.out.println("Original sheet row count: " + this.rows + "\nMerged sheet col count: " + this.cols);
         
         this.rows = oldMatrix.length; // Left sheet row count. 
         this.cols = oldMatrix[0].length; // Left sheet col count. 

         System.out.println("Left sheet row count after split: " + this.rows + "\nLeft sheet col count after split: " + this.cols);
         System.out.println("Right sheet row count after split: " + newMatrix.length + "\nRight sheet col count after split: " + newMatrix[0].length);
         
         System.out.println("Spreadsheet split at column " + splitIndex);
         
         Spreadsheet newSheet = new Spreadsheet(this.username, this.email, this.role);
         newSheet.matrix = newMatrix; // Right half of the content. 

         return newSheet;
    }
        @Override public void exportAsPDF()      { System.out.println("Exporting sheet as PDF"); }
        //@Override public void exportAsHTML()     { System.out.println("Ex";}

        }
