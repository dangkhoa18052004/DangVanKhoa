public class PaymentCalculator {

    public enum Type {
        MALE, FEMALE, CHILD
    }

    /**
     * Bài 4 - Calculate the Payment for the Patient
     * Rules:
     * - Child (0-17): 50
     * - Male (18-35): 100; (36-50): 120; (51-145): 140
     * - Female (18-35): 80; (36-50): 110; (51-145): 140
     */
    public static int calculate(Type type, int age) {
        // Validate tuổi chung
        if (age < 0 || age > 145) {
            throw new IllegalArgumentException("Age must be between 0 and 145.");
        }

        // Child
        if (type == Type.CHILD) {
            if (age <= 17)
                return 50;
            throw new IllegalArgumentException("Child age must be 0-17.");
        }

        // Male/Female phải >= 18
        if (age < 18) {
            throw new IllegalArgumentException("Adult age must be 18-145.");
        }

        if (type == Type.MALE) {
            if (age <= 35)
                return 100;
            if (age <= 50)
                return 120;
            return 140; // 51-145
        }

        if (type == Type.FEMALE) {
            if (age <= 35)
                return 80;
            if (age <= 50)
                return 110;
            return 140; // 51-145
        }

        throw new IllegalArgumentException("Invalid type.");
    }
}
