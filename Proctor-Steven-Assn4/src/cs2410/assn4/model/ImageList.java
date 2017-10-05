package cs2410.assn4.model;

import javafx.scene.image.Image;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * ImageList contains an array list of ImageData objects and methods to add, remove, and get info from ImageData objects.
 * Contains methods to read and write .txt files that contain ImageData info - and image's URL and Title.
 * Tracks the content of the imageList and puts a "No Image Found" ImageData object in the array list if the user has
 * not added any images of their own. Removes the "No Image Found" ImageData when a new image is added.
 *
 * @author Steven Proctor
 */
public class ImageList {

    /**
     * Private array list of ImageData Objects
     */
    private ArrayList<ImageData> imageList;

    /**
     * Private ImageData object that contains a "No Image Found" object.
     *
     * Stored in imageList when imageList is empty.
     */
    private static final ImageData noImage = new ImageData(
            "https://www.bailey-parts.co.uk/catalogimages/products/not-found.png?mode=crop&scale=both&quality=80&width=580&height=435",
            "No Image on File");

    /**
     * Private boolean the contains true when there are no images in the imageList
     */
    private boolean noImageFound = false;

    /**
     * Public Constructor that initializes the array list.
     */
    public ImageList(String fileName) {
        imageList = new ArrayList<>();
        //imageList.add(noImage);
        readFile(fileName);
    }

    /**
     * Creates a new ImageData object using given parameters and stores the new object in imageList
     * If adding a new image to an empty imageList, removes the "No Image Found" object and resets noImageFound to false.
     * @param url String containing the images' URL
     * @param title String containing the image's Title
     * @param index integer for the index at which the new image will be added to the imageList
     */
    public void addImage(String url, String title, int index) {
        if (noImageFound) {
            removeImage(index);
            noImageFound = false;
        }
        imageList.add(index, new ImageData(url, title));
    }

    /**
     * Removes a imageData object from imageList based on a given index.
     * If removing the last image in imageList, adds the "No Image Found" object and sets noImageFound to true.
     * @param element an int for the index of the element to be removed
     */
    public void removeImage(int element) {
        imageList.remove(element);
        if (!noImageFound && imageList.isEmpty()) {
            imageList.add(noImage);
            noImageFound = true;
        }
    }

    /**
     * Getter method that returns the URL of an image, given an index for the element in imageList
     * @param element an int for the index of the image in question
     * @return the url string for the image
     */
    public String getImageURL(int element) {
        return imageList.get(element).getUrl();
    }

    /**
     * Getter method that returns the title of an image, given an index for the element in imageList
     * @param element an int for the index of the image in question
     * @return the title string for the image
     */
    public String getImageTitle(int element) {
        return imageList.get(element).getTitle();
    }

    /**
     * Getter method that returns the image contained in imageData, given an index for the element in imageList
     * @param element an int for the index of the image in question
     * @return the image object contained in imageData
     */
    public Image getImage(int element) { return imageList.get(element).getImage(); }

    /**
     * Getter method that returns the current size of the imageList
     * @return size of imageList
     */
    public int getSize() {
        return imageList.size();
    }

    /**
     * Getter method that returns false if imageList contains images, and true if it contains no images
     * @return noImageFound
     */
    public boolean noImageFound() { return noImageFound; }

    /**
     * Reads data from a .txt file containing an image's URL and Title, creates a new ImageData object using the data,
     * and stores the new object in imageList. Sets noImageFound to true if no images were obtained from the file
     * @param fileName the path and filename for the .txt file that the data will be read from.
     */
    private void readFile(String fileName) {
        try {
            FileReader file = new FileReader(fileName);
            Scanner read = new Scanner(file);
            String tmpUrl, tmpTitle;

            while (read.hasNext()) {
                tmpUrl = read.next();
                read.skip(" ");
                tmpTitle = read.nextLine();
                imageList.add(new ImageData(tmpUrl, tmpTitle));
            }

            read.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if (imageList.isEmpty()) {
            imageList.add(noImage);
            noImageFound = true;
        }
    }

    /**
     * Writes the contents of imageList to a .txt file. Specifically the URL and title of each ImageData object.
     * @param fileName the path and filename for the .txt file that the data will be written to.
     */
    public void writeFile(String fileName) {
        if (!noImageFound) {
            try {
                PrintWriter write = new PrintWriter(new FileOutputStream(fileName, false));

                for (ImageData image : imageList) {
                    write.println(image.getUrl() + " " + image.getTitle());
                }

                write.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
