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

    // Getters
    public int getRows() { return this.rows; }
    public int getCols() { return this.cols; }


    //Setters
    public void setRows(int newRows) {
        if (newRows <= 0) throw new IllegalArgumentException("rows must be > 0");
        this.rows = newRows;
        this.matrix = new String[this.rows][this.cols]; // clears all data
    }

    public void setCols(int newCols) {
        if (newCols <= 0) throw new IllegalArgumentException("cols must be > 0");
        this.cols = newCols;
        this.matrix = new String[this.rows][this.cols]; // clears all data
    }


    public Spreadsheet(String username, String email, String role) {
        super(username, email, role);
        this.rows = 10;   // limit size
        this.cols = 10;
        this.matrix = new String[this.rows][this.cols];
    }

    public void addRow() {
        String[][] newMatrix = new String[this.rows += 1][this.cols];
        for (int row = 0; row < this.matrix.length; row++) // Old content from OG sheet has the same indices.
        {
            for (int col = 0; col < this.matrix[0].length; col++)
            {
                newMatrix[row][col] = this.matrix[row][col];
            }
        }

        this.matrix = newMatrix;
        displaySheet();
    }

    public void addCol() {
        String[][] newMatrix = new String[this.rows][this.cols += 1];
        for (int row = 0; row < this.matrix.length; row++) // Old content from OG sheet has the same indices.
        {
            for (int col = 0; col < this.matrix[0].length; col++)
            {
                newMatrix[row][col] = this.matrix[row][col];
            }
        }

        this.matrix = newMatrix;
        displaySheet();
    }

    public void deleteRow() {
        String[][] newMatrix = new String[this.rows -= 1][this.cols];
        for (int row = 0; row < this.rows; row++) // Old content from OG sheet has the same indices.
        {
            for (int col = 0; col < this.cols; col++)
            {
                newMatrix[row][col] = this.matrix[row][col];
            }
        }

        this.matrix = newMatrix;
        displaySheet();
    }

    public void deleteCol() {
        String[][] newMatrix = new String[this.rows][this.cols -= 1];
        for (int row = 0; row < this.rows; row++) // Old content from OG sheet has the same indices.
        {
            for (int col = 0; col < this.cols; col++)
            {
                newMatrix[row][col] = this.matrix[row][col];
            }
        }

        this.matrix = newMatrix;
        displaySheet();
    }

    public String viewCell(int r, int c) {
        try {
            // adjust for 1-based user input
            String content = matrix[r - 1][c - 1];
            if (content == null) content = "(empty)";
            System.out.println("Cell content: " + content);
            return content;
        } catch (ArrayIndexOutOfBoundsException e) {
            return "Out of bounds";
        }
    }


    public void swapCells(int r1, int c1, int r2, int c2) {
        if (in(r1,c1) && in(r2,c2)) {
            String t = matrix[r1][c1]; matrix[r1][c1] = matrix[r2][c2]; matrix[r2][c2] = t;
            System.out.println("Swapped ("+r1+","+c1+") with ("+r2+","+c2+")");
        } else System.out.println("Out of bounds");
        displaySheet();
    }

    private boolean in(int r, int c) { return r>=0 && r<rows && c>=0 && c<cols; }

    @Override
    public void merge(GenericPDF otherSheet) {
        if (otherSheet == null) {
            System.out.println("Cannot merge with null Sheet :(");
            return;
        }

        // Enforce limit of 100 rows and columns after merging
        int mergedRows = this.rows + ((Spreadsheet) otherSheet).rows;
        int mergedCols = this.cols + ((Spreadsheet) otherSheet).cols;

        if (mergedRows > 100 || mergedCols > 100) {
            System.out.println("Merge aborted: limit of 30 rows or columns exceeded.");
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

        for (int row = 0; row < ((Spreadsheet) otherSheet).rows; row++) {
            for (int col = 0; col < ((Spreadsheet) otherSheet).cols; col++) {
                newMatrix[originalRow + row][originalCol + col] = ((Spreadsheet) otherSheet).matrix[row][col];
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

    public void displaySheet() {
        System.out.println("\n=== Current Spreadsheet Content ===");
        for (int row = 0; row < this.matrix.length; row++) {
            for (int col = 0; col < this.matrix[0].length; col++) {
                String val = this.matrix[row][col];
                System.out.print((val == null ? "null" : val) + "\t");
            }
            System.out.println();
        }
        System.out.println("Rows: " + this.rows + ", Cols: " + this.cols);
        System.out.println("===================================\n");
    }


    @Override
    public void contextMenu() {
        if (this.getListOfRoles().get(username).equals("OWNER")) {
            System.out.println("Select option (enter a number): ");
            System.out.println("(1) Add a row/col to the sheet");
            System.out.println("(2) Delete a row/col to the sheet");
            System.out.println("(3) Add text to a cell");
            System.out.println("(4) Merge two Spreadsheets");
            System.out.println("(5) Split two Spreadsheets");
            System.out.println("(6) Swap content between 2 cells");
            System.out.println("(7) View content of a cell");
            System.out.println("(8) Export Spreadsheet as PDF");
            System.out.println("(9) Export Spreadsheet as HTML");
            System.out.println("(10) Export Spreadsheet as Word Document");
            //I don't think we need to have a User Role
            // System.out.println("(11) Update User Roles");
        } else if (this.getListOfRoles().get(username).equals("EDITOR")) {
            System.out.println("Select option (enter a number): ");
            System.out.println("(1) Add a row/col to the sheet");
            System.out.println("(2) Delete a row/col to the sheet");
            System.out.println("(3) Add text to a cell");
            System.out.println("(4) Merge two Spreadsheets");
            System.out.println("(5) Split two Spreadsheets");
            System.out.println("(6) Swap content between 2 cells");
            System.out.println("(7) View content of a cell");
            System.out.println("(8) Export Spreadsheet as PDF");
            System.out.println("(9) Export Spreadsheet as HTML");
            System.out.println("(10) Export Spreadsheet as Word Document");
        } else if (this.getListOfRoles().get(username).equals("COMMENTER")) {
            System.out.println("Select option (enter a number): ");
            System.out.println("(1) Add comment to the last cell");
            System.out.println("(2) Export Spreadsheet as PDF");
            System.out.println("(3) Export Spreadsheet as HTML");
            System.out.println("(4) Export Spreadsheet as Word Document");
        } else // User role is: VIEWER
        {
            System.out.println("Select option (enter a number): ");
            System.out.println("(1) Export Spreadsheet as PDF");
            System.out.println("(2) Export Spreadsheet as HTML");
            System.out.println("(3) Export Spreadsheet as Word Document");
        }
    }

    public void editCell(String newText, int rowNumber, int colNumber)
    {
        if (this.getMatrix() != null)
        {
            int r = rowNumber;   // adjust for 1-based user input
            int c = colNumber;

            try {
                String existingText = this.getMatrix()[r][c];
                if (existingText == null) existingText = "";
                this.getMatrix()[r][c] = existingText.concat(newText);
                System.out.println("New text appended to row " + rowNumber + ", column " + colNumber + " successfully!");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Out of bounds — cell does not exist.");
            }
        }
        displaySheet();
    }


    public String[][] getMatrix() {
        return this.matrix;
    }

        @Override
        public String toString() {


                for (int row = 0; row < this.matrix.length; row++) // Old content from OG sheet has the same indices.
                {
                    for (int col = 0; col < this.matrix[0].length; col++)
                    {
                        System.out.print(this.matrix[row][col] + ", ");
                    }
                    System.out.println();
                }
            return "SpreadSheets{" +
                "RowCount=" + this.rows +
                ", ColumnCount='" + this.cols + '\'' +
                ", username='" + getUsername() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", role='" + getRole() + '\'' +
                '}';
    }

    @Override public void exportAsPDF() {
        for (int row = 0; row < this.matrix.length; row++) // Old content from OG sheet has the same indices.
        {
            for (int col = 0; col < this.matrix[0].length; col++) {
                System.out.print(this.matrix[row][col] + ", ");
            }
            System.out.println();
        }
    }



    @Override public void exportAsHTML()     {
        for (int row = 0; row < this.matrix.length; row++) // Old content from OG sheet has the same indices.
        {
            for (int col = 0; col < this.matrix[0].length; col++)
            {
                System.out.print(this.matrix[row][col] + "\t");
            }
            System.out.println();
        }

    }
    @Override public void exportAsWordDoc()     {
            for (int row = 0; row < this.matrix.length; row++) // Old content from OG sheet has the same indices.
            {
                for (int col = 0; col < this.matrix[0].length; col++)
                {
                    System.out.print(this.matrix[row][col] + " | ");
                }
                System.out.println();
            }

        }



}


