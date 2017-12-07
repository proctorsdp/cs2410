package tests.factoryTests;

import controller.factories.DifficultyFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class DifficultyFactoryTest {

    private DifficultyFactory difficultyFactory;

    private double hard;
    private double medium;
    private double easy;

    @BeforeEach
    void setUp() {
        difficultyFactory = new DifficultyFactory();
        hard = .4;
        medium = .25;
        easy = .1;
    }

    @Test
    void createDifficulty() {
        double difficulty = difficultyFactory.createDifficulty("agent");

        assert difficulty == easy;

        difficulty = difficultyFactory.createDifficulty("hard");

        assert difficulty == hard;

        difficulty = difficultyFactory.createDifficulty("MEdIUM");

        assert difficulty == medium;

        difficulty = difficultyFactory.createDifficulty("EAsy");

        assert difficulty == easy;
    }

}