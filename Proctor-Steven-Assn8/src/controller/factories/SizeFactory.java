package controller.factories;

import controller.Size;

/**
 * Creates a size object used to create a minefield.
 *
 * @author Steven Proctor
 * @version 1.0
 */
public class SizeFactory {

    /**
     * Retuns a size object describing the size of the minefield given a description of the desired size
     * @param type String describing the desired board size
     * @return a size object
     */
    public Size createSize(String type) {
        type = type.toUpperCase();
        Size size;
        switch (type) {
            case "SMALL":
                size = new Size(10, 10);
                break;
            case "MEDIUM":
                size = new Size(25, 25);
                break;
            case "LARGE":
                size = new Size(25, 50);
                break;
            default:
                size = new Size(10, 10);
                break;
        }
        return size;
    }
}
