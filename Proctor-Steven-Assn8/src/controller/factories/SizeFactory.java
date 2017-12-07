package controller.factories;

import controller.Size;

public class SizeFactory {

    public Size createSize(String type) {
        type = type.toUpperCase();
        Size size;
        switch (type) {
            case "SMALL":
                size = new Size(10, 10);
                break;
            case "MEDIUM":
                size = new Size(25, 25);
                break;
            case "LARGE":
                size = new Size(25, 50);
                break;
            default:
                size = new Size(10, 10);
                break;
        }
        return size;
    }
}
