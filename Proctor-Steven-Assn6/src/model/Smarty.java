package model;

/**
 * Abstract class that implements the PersonType and Simpleton Interfaces and adds additional functionality for a person
 * who can be described as smart. Smart people can do everything a simpleton can, but they also have an IQ and an income.
 */
public abstract class Smarty implements PersonType, Simpleton {

    /**
     * String containing the IQ of the smart person
     */
    String iq;

    /**
     * String containing the income of the smart person. Must be calculated in the class that inherits from Smarty
     */
    String income;

    /**
     *
     * @return The String containing the smart person's IQ
     */
    public String getIQ() {return iq;}

    /**
     * The income will need to be calculated in the class that inherits from Smarty
     * @return The String containing the smart person's income
     */
    public abstract String getIncome();
}
