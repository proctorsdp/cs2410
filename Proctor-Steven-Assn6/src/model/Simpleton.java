package model;


/**
 * Interface for any class that is a person of type simpleton. Contains methods describing the actions that all simpletons
 * are capable of.
 */
public interface Simpleton {

    /**
     *
     * @return String containing the name of the person
     */
    String getName();

    /**
     *
     * @return String containing an example of the type of math the simpleton can do
     */
    String doMath();

    /**
     *
     * @return String containing a phrase about which the simpleton knows a lot about
     */
    String saySomethingSmart();
}
