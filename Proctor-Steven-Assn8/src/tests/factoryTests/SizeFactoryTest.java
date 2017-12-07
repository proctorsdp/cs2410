package tests.factoryTests;

import controller.Size;
import controller.factories.SizeFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class SizeFactoryTest {

    private SizeFactory sizeFactory;

    private Size smallSize;
    private Size mediumSize;
    private Size largeSize;

    @BeforeEach
    void setUp() {
        sizeFactory = new SizeFactory();
        smallSize = new Size(10, 10);
        mediumSize = new Size(25, 25);
        largeSize = new Size(25, 50);
    }

    @Test
    void createSize() {
        Size size = sizeFactory.createSize("SMALL");

        assert size.equals(smallSize);

        size = sizeFactory.createSize("medium");

        assert size.equals(mediumSize);

        size = sizeFactory.createSize("lrg");

        assert !size.equals(largeSize);
        assert size.equals(smallSize);

        size = sizeFactory.createSize("LARGE");

        assert size.equals(largeSize);
    }

}