package com.ewyboy.bibliotheca.common.helpers;

import java.util.Random;

public class ModMathHelper {

    private static Random random = new Random();

    public static boolean roll(int percentage) {
        return percentage > (int) (Math.random() * 100);
    }

    /**
     * Returns a random integer between min (inclusive) and max (inclusive)
     */
    public static int getRandomIntegerInRange(int min, int max) {
        return (random.nextInt() * (max - min) + min);
    }

    /**
     * Returns a random double between min (inclusive) and max (inclusive)
     */
    public static double getRandomDoubleInRange(double min, double max) {
        return (random.nextDouble() * (max - min) + min);
    }

    /**
     * Returns a random float between min (inclusive) and max (inclusive)
     */
    public static float getRandomFloatInRange(float min, float max) {
        return (random.nextFloat() * (max - min) + min);
    }

    /**
     * Returns a random long between min (inclusive) and max (inclusive)
     */
    public static long getRandomLongInRange(long min, long max) {
        return (random.nextLong() * (max - min) + min);
    }

    /**
     * Returns an integer incrementing up to the max value then flips
     * starting to decrement down again [Eks: max = 3]
     * returns 0,1,2,3,2,1,0 when count = 0,1,2,3,4,5,6,7
     */
    public static int getCyclingNumberInRange(int count, int max) {
        return ((-1) * Math.abs(count - max) + max);
    }

}
