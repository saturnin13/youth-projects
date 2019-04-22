package saturnin.dicegame;

/**
 * Created by saturnin on 25/9/2015.
 */

import java.util.Random;


public class Dice {

    private int value;
    private Random random;
    private int MAX_VALUE = 6;
    private int MIN_VALUE = 1;

    public Dice () {
        random = new Random();
        value = MIN_VALUE + random.nextInt(MAX_VALUE + 1);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        assert(value < 7 && value > 0): "value must be between 0 and 7";
        this.value = value;
    }

    public void reRoll() {
        value = MIN_VALUE + random.nextInt(MAX_VALUE + 1);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        Dice dice = (Dice) object;

        return value == dice.value;

    }

    @Override
    public int hashCode() {
        return value;
    }

}
