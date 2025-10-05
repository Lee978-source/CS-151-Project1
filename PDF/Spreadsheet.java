     /**
     * @author [Lordin Yi]
     * @version 1.0
     * CS151 Fall 2025 - Project 1
     */
    package PDF;
     
    public static class Spreadsheet extends GenericPDF {
        private final int rows, cols;
        private final String[][] matrix;

        public Spreadsheet(String username, String email, String role, int rows, int cols) {
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

        @Override public void merge()            { System.out.println("Merging spreadsheets"); }
        @Override public void split()            { System.out.println("Splitting spreadsheet"); }
        @Override public void exportAsPDF()      { System.out.println("Exporting sheet as PDF"); }
        //@Override public void exportAsHTML()     { System.out.println("Ex";}

        }
