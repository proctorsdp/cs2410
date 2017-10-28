package model;

/**
 * Hobbit implements the Simpleton and PersonType interfaces. A Hobbit is a simpleton with the ability to do math, say
 * something smart about a subject, and pick carrots.
 */
public class Hobbit implements Simpleton, PersonType {

    /**
     * String containing the person's name
     */
    private String name;

    /**
     * String containing an example of the type of math the person can do
     */
    private String math;

    /**
     * String containing a phrase about something the person is knowledgeable about
     */
    private String phrase;

    /**
     * String containing the number of carrots the hobbit picked
     */
    private String carrotsPicked;

    /**
     * Public constructor for Hobbit. Used to set all data members contained by Hobbit.
     * @param name String containing the name of the hobbit
     * @param math String containing an example of an addition problem
     * @param phrase String containing a phrase about gardening
     * @param carrotsPicked String containing the number of carrots the hobbit picked
     */
    public Hobbit(String name, String math, String phrase, String carrotsPicked) {
        this.name = name;
        this.math = math;
        this.phrase = phrase;
        this.carrotsPicked = carrotsPicked;
    }

    /**
     *
     * @return String containing the word "Hobbit"
     */
    @Override
    public String getPersonType() {
        return "Hobbit";
    }

    /**
     *
     * @return String containing the person's name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     *
     * @return String containing an example of the kind of math the person can do
     */
    @Override
    public String doMath() {
        return math;
    }

    /**
     *
     * @return String containing a phrase about which the person is knowledgeable about
     */
    @Override
    public String saySomethingSmart() {
        return phrase;
    }

    /**
     *
     * @return String containing the number of carrots the hobbit picked
     */
    public String getCarrotsPicked() {
        return carrotsPicked;
    }
}
