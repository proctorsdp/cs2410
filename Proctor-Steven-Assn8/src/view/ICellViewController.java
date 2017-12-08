package view;

/**
 * ICellViewController defines the interface required for a controller, in order for the CellView to update the controller of user input
 *
 * @author Steven Proctor
 * @version 1.0
 */
public interface ICellViewController {

    /**
     * Calls the select method of the cell associated with the CellView
     * @param cellView a CellView which has been selected
     */
    void selectCell(CellView cellView);

    /**
     * Calls the flag method of the cell associated with the CellView
     * @param cellView a CellView which has been flagged
     */
    void flagCell(CellView cellView);
}
