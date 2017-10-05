package cs2410.assn4.model;

import javafx.scene.image.Image;


/**
 * Class that contains the data needed to obtain, store, and title an image.
 *
 * Pulls the image from the URL and stores the title.
 *
 * Contains getters for all three data members.
 *
 * ImageData data members can only be set with the constructor.
 *
 * @author Steven Proctor
 */
class ImageData {

    /**
     * Private String containing the images' URL
     */
    private String url;

    /**
     * Private String containing the images' Title
     */
    private String title;

    /**
     * Private Image object that stores the visual Image
     */
    private Image image;

    /**
     * Constructor that accepts an image URL and Title and creates an Image Object based on the URL.
     * @param url String containing the images' URL
     * @param title String containing the image's Title
     */
    ImageData(String url, String title) {
        this.url = url;
        this.title = title;
        image = new Image(this.url);
    }

    /**
     * Returns the URL of the image
     * @return private data member url
     */
    String getUrl() {
        return url;
    }

    /**
     * Returns the Title of the image
     * @return private data member title
     */
    String getTitle() {
        return title;
    }

    /**
     * Returns the image object
     * @return private data member image
     */
    Image getImage() { return image; }

}
