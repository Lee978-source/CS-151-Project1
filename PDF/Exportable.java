/**
 * @author [Brian Nguyen]
 * @version 1.0
 * CS151 Fall 2025 - Project 1
 */
 
package PDF; 
// interface class
interface Exportable {
    // methods to export into a different format
    void exportAsPDF();

    void exportAsHTML();
    void exportAsWordDoc();
}

