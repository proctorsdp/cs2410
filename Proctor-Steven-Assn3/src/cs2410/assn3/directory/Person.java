package cs2410.assn3.directory;

/**
 * The Person class contains all relevant information on a given person needed for a student directory. Stores information
 * such as name, ID, major, and age. Public functions allow each data member to be retrieved or changed. Constructor
 * allows for the immediate initialization of each data member, as appropriate parameters are passed in.
 *
 * @author Steven Proctor
 * @version 1.0
 */
public class Person {

    /**
     * Private String that stores the peron's first name
     */
    private String firstName;

    /**
     * Private String that stores the peron's last name
     */
    private String lastName;

    /**
     * Private String that stores the peron's age
     */
    private int age;

    /**
     * Private String that stores the peron's major - in University Code
     */
    private String majorCode;

    /**
     * Private String that stores the peron's student ID
     */
    private String studentID;


    /**
     * Constructor for Person object. Accepts all identifying information, and initializes object's data members.
     * @param firstName A String containing the first name of the person
     * @param lastName A String containing the last name of the person
     * @param age An int containing the age of the person
     * @param majorCode A String containing the person's major - written in University Code
     * @param studentID A String containing the person's student ID
     */
    Person(String firstName, String lastName, int age, String majorCode, String studentID) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.majorCode = majorCode;
        this.studentID = studentID;
    }


    /**
     * Public function that returns the person's first name
     * @return firstName
     */
    public String getFirstName() {
        return firstName;
    }


    /**
     * Public function that accepts a String and replaces the person's first name with the new String
     * @param firstName A String containing the person's first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    /**
     * Public function that returns the person's last name
     * @return lastName
     */
    public String getLastName() {
        return lastName;
    }


    /**
     * Public function that accepts a String and replaces the person's last name with the new String
     * @param lastName A String containing the person's last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    /**
     * Public function that returns the person's age
     * @return age
     */
    public int getAge() {
        return age;
    }


    /**
     * Public function that accepts an int and replaces the person's age with the new int
     * @param age A String containing the person's age
     */
    public void setAge(int age) {
        this.age = age;
    }


    /**
     * Public function that returns the person's major - in University Code
     * @return majorCode
     */
    public String getMajorCode() {
        return majorCode;
    }


    /**
     * Public function that accepts a String and replaces the person's major with the new String
     * @param majorCode A String containing the person's major - in university code
     */
    public void setMajorCode(String majorCode) {
        this.majorCode = majorCode;
    }


    /**
     * Public function that returns the person's student ID
     * @return studentID
     */
    public String getStudentID() {
        return studentID;
    }


    /**
     * Public function that accepts a String and replaces the person's student ID with the new String
     * @param studentID A String containing the person's student ID
     */
    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }
}
