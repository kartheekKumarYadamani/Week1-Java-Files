package PracticalSession;

import java.util.Random;
import java.util.function.Supplier;

public class Otp {
    public static void main(String[] args) {
        Supplier<String> otp = () -> {
            String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
            Random random = new Random();
            StringBuilder otpResult = new StringBuilder();
            for (int i = 1; i <=6; i++) {
                int index = random.nextInt(chars.length());
                otpResult.append(chars.charAt(index));
            }
            return otpResult.toString();
        };
        System.out.println(otp.get());
    }
}
