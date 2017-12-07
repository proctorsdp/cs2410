package controller;

public class Size {

    private int rows;
    private int cols;

    public Size(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
    }

    int getRows() {
        return rows;
    }


    int getCols() {
        return cols;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Size) {
            Size s = (Size) o;
            return s.getCols() == cols && s.getRows() == rows;
        }
        return false;
    }
}
