package model;

/**
 * Hourly worker extends Smarty. Has all the functionality of A simpleton and smarty.
 */
public class HourlyWorker extends Smarty {

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
     * String containing the number of hours worked by the person
     */
    private String hoursWorked;

    /**
     * Public constructor for ContractWorker. Used to set all data members.
     * @param name String containing the name of the hourly worker
     * @param math String containing an example of a multiplication problem
     * @param phrase String containing a phrase about animals
     * @param iq String containing the IQ of the hourly worker
     * @param hoursWorked String containing the number of hours the person worked
     * @param wage String containing the amount the worker gets paid per hour
     */
    public HourlyWorker(String name, String math, String phrase, String iq, String hoursWorked, String wage) {
        this.name = name;
        this.math = math;
        this.phrase = phrase;
        this.iq = iq;
        this.hoursWorked = hoursWorked;
        this.income = "" + (Double.parseDouble(hoursWorked) * Double.parseDouble(wage));
    }

    /**
     *
     * @return String containing the phrase "Hourly Worker"
     */
    @Override
    public String getPersonType() {
        return "Hourly Worker";
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
     * @return String containing the amount the worker earned based on wage and number of hours worked
     */
    @Override
    public String getIncome() {
        return income;
    }

    /**
     *
     * @return String containing the number of hours the person worked
     */
    public String getHoursWorked() {
        return hoursWorked;
    }
}
