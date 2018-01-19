package utils;


/**
 * @author Vojtěch Kinkor
 */
public class MathEx {

    /**
     * Clamps value between 0.0 and 1.0 (inclusive).
     */
    public static double clamp(double value) {
        return clamp(value, 0.0, 1.0);
    }

    /**
     * Clamps value between minVal and maxVal (inclusive).
     */
    public static double clamp(double val, double minVal, double maxVal) {
        return Math.max(minVal, Math.min(maxVal, val));
    }

}
