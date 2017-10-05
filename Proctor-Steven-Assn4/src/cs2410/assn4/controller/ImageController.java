package cs2410.assn4.controller;

import cs2410.assn4.model.ImageList;
import javafx.scene.image.Image;

/**
 * Image Controller Utilizes the ImageList class to manage the imageList based on GUI inputs.
 *
 * Initializes the ImageList object when initialized with a fileName.
 *
 * Has methods to add and remove images, as well as get next and previous images.
 *
 * Includes Getter methods to get image info and status of ImageList.
 *
 * Saves the contents of ImageList to a .txt file when quit is called.
 *
 * @author Steven Proctor
 */
public class ImageController {

    /**
     * Private ImageList object
     */
    private ImageList imageList;

    /**
     * Private int that tracks the current image being viewed
     */
    private int currentImage;

    /**
     * Constructor that initializes the ImageList object given a .txt file to read image data from.
     * @param fileName .txt file path and name that contains an image's URL and Title
     */
    public ImageController(String fileName) { imageList = new ImageList(fileName); }

    /**
     * Adds a new image to the imageList object given an image's URL and Title. Increments currentImage.
     * @param url String containing an Image's URL
     * @param title String containing an Image's Title
     * @return the new image
     */
    public Image addNewImage(String url, String title) {
        if (imageList.noImageFound()) { currentImage = -1; }
        imageList.addImage(url, title, ++currentImage);
        return getCurrentImage();
    }

    /**
     * Removes the image currently being viewed. Returns the next image in the imageList.
     * @return the next image in imageList
     */
    public Image removeCurrentImage() {
        imageList.removeImage(currentImage);
        if (currentImage >= imageList.getSize()) { currentImage = 0; }
        return getCurrentImage();
    }

    /**
     * Increments currentImage by one, wrapping back to 0 if it exceeds the size of the imageList. Returns the next
     * image in the imageList
     * @return the next image in the imageList
     */
    public Image getNextImage() {
        if (currentImage >= imageList.getSize() - 1) {
            currentImage = 0;
        } else {
            currentImage++;
        }
        return getCurrentImage();
    }

    /**
     * Decrements currentImage by one, wrapping to the current size of imageList if currentImage is greater than the
     * size of imageList. Returns the previous image in imageList
     * @return the previous image in imageList
     */
    public Image getPrevImage() {
        if (currentImage <= 0) {
            currentImage = imageList.getSize() - 1;
        } else {
            currentImage--;
        }
        return getCurrentImage();
    }

    /**
     * Calls the writeFile() method of imageList to save the contents of imageList to a .txt file.
     * @param fileName .txt filename and path that contains an images URL and Title
     */
    public void quitProgram(String fileName) {
        imageList.writeFile(fileName);
    }

    /**
     * Returns an image from imageList given an index
     * @return an image from the imageList
     */
    public Image getCurrentImage() {
        return imageList.getImage(currentImage);
    }

    /**
     * Returns the title of an image, given an index
     * @return the title of an image from imageList
     */
    public String getCurrentTitle() {
        return imageList.getImageTitle(currentImage);
    }

    /**
     * Returns true if there are no images in the imageList, otherwise false.
     * @return the true if no images are contained in imageList
     */
    public boolean noImage() { return imageList.noImageFound(); }

    /**
     * Returns true if there is only one image added by the user. False otherwise
     * @return true if the user has only one image stored in imageList
     */
    public boolean isOneImage() { return imageList.getSize() == 1; }
}
