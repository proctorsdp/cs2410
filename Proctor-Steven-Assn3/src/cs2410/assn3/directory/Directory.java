package cs2410.assn3.directory;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Add a description of the class here
 *
 * @author Steven Proctor
 * @version 1.0
 */
public class Directory {

    /**
     * Private ArrayList of Person objects. Used to store directory of students
     */
    private List<Person> studentList;

    /**
     * Private double that stores the average age of all students in the directory
     */
    private double averageAge = 0;

    /**
     * Private string that contains the file name and path of the directory data
     */
    private String dataFile = "data/cs2410-directory.data";


    /**
     * Constructor that initializes the studentList, calls readData() and stores the average age of the current directory
     */
    public Directory() {
        studentList = new ArrayList<>();
        readData();
        calculateAveAge();
    }


    /**
     * Public function that takes in a students identifying information, creates a new person object and initializes the data,
     * then stores the new person in the studentList. Calls updateAveAge() and writeData() after adding the new student.
     * @param firstName a String containing the first name of the new student
     * @param lastName a String containing the last name of the new student
     * @param age an int containing the age of the new student
     * @param majorCode a String containing the major of the new student - written in University code
     * @param studentID a String containing the student ID of the new student
     */
    public void addStudent(String firstName, String lastName, int age, String majorCode, String studentID) {
        Person newPerson = new Person(firstName, lastName, age, majorCode, studentID);
        studentList.add(newPerson);
        updateAveAge(age);
        writeData(newPerson);
    }


    /**
     * Public function that stores the information of each person in student list in a formatted string, and returns said string
     * @return a formatted string containing the information of each person in studentList
     */
    public String listContent() {
        String listContent = String.format("%-12s \t %-12s \t %4s \t %-6s \t %-11s\n",
                "First Name:", "Last Name:", "Age:", "Major:", "Student ID:");

        listContent += String.format("%-12s \t %-12s \t %4s \t %-6s \t %-11s\n",
                "------------", "------------", "----", "------", "-----------");

        for (Person p : studentList) {
            listContent += String.format("%-12s \t %-12s \t %4d \t %-6s \t %-11s\n",
                    p.getFirstName(), p.getLastName(), p.getAge(), p.getMajorCode(), p.getStudentID()
            );
        }

        return listContent;
    }


    /**
     * Public function that returns the average age of all the students in the directory
     * @return averageAge
     */
    public double getAverageAge() {
        return averageAge;
    }


    /**
     * Creates a new FileReader and Scanner based on the data file. Adds each student from the data file to the studentList
     */
    private void readData() {
        try {
            FileReader file = new FileReader(dataFile);
            Scanner input = new Scanner(file);

            while (input.hasNext()) {
                studentList.add(new Person(input.next(), input.next(), Integer.parseInt(input.next()), input.next(), input.next()));
            }

            input.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**
     * Appends the data of a new person object to the data file and saves the file.
     * @param p the Person object that was most recently added to the directory
     */
    private void writeData(Person p) {
        try {
            PrintWriter writer = new PrintWriter(new FileOutputStream(dataFile, true));

            writer.print("\n" + p.getFirstName() + " " + p.getLastName() + " " + p.getAge() + " " + p.getMajorCode() + " " + p.getStudentID());

            writer.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**
     * calculates the average age of every student in the directory
     */
    private void calculateAveAge() {
        for (Person p : studentList) {
            averageAge += p.getAge();
        }
        averageAge /= studentList.size();
    }


    /**
     * Updates averageAge when a new student is added to the directory. Eliminates the need to traverse the whole list
     * after adding a new student.
     * @param newAge the age of the new student added to the directory
     */
    private void updateAveAge(int newAge) {
        averageAge *= (studentList.size() - 1);
        averageAge += newAge;
        averageAge /= studentList.size();
    }
}
