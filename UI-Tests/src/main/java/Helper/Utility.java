package Helper;

import java.util.Random;

public class Utility {

    public int getRandomNumber() {
        Random rand = new Random();
        int upperbound = 1000;
        return rand.nextInt(upperbound);
    }
}
