package control;

import model.*;
import java.util.ArrayList;

/**
 * An arrayList of PersonTypes. Can add hobbits, hourly workers, and contract workers to the list. Returns a single list
 * containing various types of information about the people contained in the list. Each method returns a different list
 * with different information.
 */
public class PersonList extends ArrayList<PersonType> {

    /**
     * Simpleton used to gain access to methods in Simpleton
     */
    private Simpleton simple;

    /**
     * Smarty used to gain access to methods in Smarty
     */
    private Smarty smart;

    /**
     * StringBuilder used to create a list of people sharing specific information
     */
    private StringBuilder list;

    /**
     * String used to store information about one person at a time
     */
    private String tmp;

    /**
     * Adds a new Hobbit to the arrayList using Hobbit's public constructor
     * @param name String containing the name of the hobbit
     * @param math String containing an example of an addition problem
     * @param phrase String containing a phrase about gardening
     * @param carrots String containing the number of carrots the hobbit picked
     */
    public void createNewHobbit(String name, String math, String phrase, String carrots) {
        this.add(new Hobbit(name, math, phrase, carrots));
    }

    /**
     * Adds a new HourlyWorker to the arrayList using HourlyWorker's public constructor
     * @param name String containing the name of the hourly worker
     * @param math String containing an example of a multiplication problem
     * @param phrase String containing a phrase about animals
     * @param iq String containing the IQ of the hourly worker
     * @param hours String containing the number of hours the person worked
     * @param wage String containing the amount the worker gets paid per hour
     */
    public void createNewHourlyWorker(String name, String math, String phrase, String iq, String hours, String wage) {
        this.add(new HourlyWorker(name, math, phrase, iq, hours, wage));
    }

    /**
     * Adds a new ContractWorker to the arrayList using ContractWorker's public constructor
     * @param name String containing the name of the contract worker
     * @param math String containing an example of a division problem
     * @param phrase String containing a phrase about astronomy
     * @param iq String containing the IQ of the contract worker
     * @param contracts String containing the number of contracts the worker completed
     * @param pay String containing the amount the worker gets paid per contract
     */
    public void createNewContractWorker(String name, String math, String phrase, String iq, String contracts, String pay) {
        this.add(new ContractWorker(name, math, phrase, iq, contracts, pay));
    }

    /**
     *
     * @return String containing a list of every person in the arrayList and a phrase about something they know a lot about
     */
    public String getSayList() {
        list = new StringBuilder();
        StringBuilder list = new StringBuilder();
        for (PersonType person : this) {
            if (person instanceof Simpleton) {
                simple = (Simpleton) person;
                tmp = simple.getName() + ", " + person.getPersonType() + ": " + simple.saySomethingSmart() + "\n";
                list.append(tmp);
            }
        }
        return list.toString();
    }

    /**
     *
     * @return String containing a list of every Smarty in the arrayList and their income
     */
    public String getIncomeList() {
        list = new StringBuilder();
        for (PersonType person : this) {
            if (person instanceof Smarty) {
                smart = (Smarty) person;
                tmp = smart.getName() + ", " + person.getPersonType() + ": my income is $" + smart.getIncome() + "\n";
                list.append(tmp);
            }
        }
        return list.toString();
    }

    /**
     *
     * @return String containing a list of every person in the arrayList and an example of the kind of math they can do
     */
    public String getMathList() {
        list = new StringBuilder();
        for (PersonType person : this) {
            if (person instanceof Simpleton) {
                simple = (Simpleton) person;
                tmp = simple.getName() + ", " + person.getPersonType() + ": I can do this math, " + simple.doMath() + "\n";
                list.append(tmp);
            }
        }
        return list.toString();
    }

    /**
     *
     * @return String containing a list of every HourlyWorker in the arrayList and the number of hours they worked
     */
    public String getHoursList() {
        list = new StringBuilder();
        HourlyWorker hourly;
        for (PersonType person : this) {
            if (person instanceof HourlyWorker) {
                hourly = (HourlyWorker) person;
                tmp = hourly.getName() + ", " + person.getPersonType() + ": I worked " + hourly.getHoursWorked() + " hours\n";
                list.append(tmp);
            }
        }
        return list.toString();
    }

    /**
     *
     * @return String containing a list of every ContractWorker in the arrayList and the number of contracts they completed
     */
    public String getContractsList() {
        list = new StringBuilder();
        ContractWorker contract;
        for (PersonType person : this) {
            if (person instanceof ContractWorker) {
                contract = (ContractWorker) person;
                tmp = contract.getName() + ", " + contract.getPersonType() + ": I completed " + contract.getContractsCompleted() + " contracts\n";
                list.append(tmp);
            }
        }
        return list.toString();
    }

    /**
     *
     * @return String containing a list of every Smarty in the arrayList and their IQ
     */
    public String getIQList() {
        list = new StringBuilder();
        for (PersonType person : this) {
            if (person instanceof Smarty) {
                smart = (Smarty) person;
                tmp = smart.getName() + ", " + person.getPersonType() + ": My IQ is " + smart.getIQ() + "\n";
                list.append(tmp);
            }
        }
        return list.toString();
    }

    /**
     *
     * @return String containing a list of every Hobbit in the arrayList and the number of carrots they picked
     */
    public String getCarrotsList() {
        list = new StringBuilder();
        Hobbit hobbit;
        for (PersonType p : this) {
            if (p instanceof Hobbit) {
                hobbit = (Hobbit) p;
                tmp = hobbit.getName() + ", " + p.getPersonType() + ", I picked " + hobbit.getCarrotsPicked() + " carrots\n";
                list.append(tmp);
            }
        }
        return list.toString();
    }
}
