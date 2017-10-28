package model;


/**
 * Contract worker extends Smarty. Has all the functionality of A simpleton and smarty.
 */
public class ContractWorker extends Smarty {

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
     * String containing the number of contacts the person completed
     */
    private String completedContracts;

    /**
     * Public constructor for ContractWorker. Used to set all data members.
     * @param name String containing the name of the contract worker
     * @param math String containing an example of a division problem
     * @param phrase String containing a phrase about astronomy
     * @param iq String containing the IQ of the contract worker
     * @param completedContracts String containing the number of contracts the worker completed
     * @param contractPay String containing the amount the worker gets paid per contract
     */
    public ContractWorker(String name, String math, String phrase, String iq, String completedContracts, String contractPay) {
        this.name = name;
        this.math = math;
        this.phrase = phrase;
        this.iq = iq;
        this.completedContracts = completedContracts;
        this.income = "" + (Double.parseDouble(completedContracts) * Double.parseDouble(contractPay));
    }

    /**
     *
     * @return String containing the phrase "Contract Worker"
     */
    @Override
    public String getPersonType() {
        return "Contract Worker";
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
     * @return String containing the amount the worker earned based on pay and number of contracts
     */
    @Override
    public String getIncome() {
        return income;
    }

    /**
     *
     * @return String containing the number of contracts the worker completed
     */
    public String getContractsCompleted() {
        return completedContracts;
    }
}
