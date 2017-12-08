package view;


/**
 * ICellView defines the interface required by MineFinderController for a CellView
 *
 * @author Steven Proctor
 * @version 1.0
 */
public interface ICellView {

    /**
     * Sets the cellView invisible to mouseEvents
     */
    void disableCell();

    /**
     * Changes the style of the cellView
     * @param style the name of a style in cellStyles.css
     */
    void addStyle(String style);
}
