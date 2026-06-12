package PracticalSession;

public class PrintUsingThreadwithFunctional {
    public static void main(String[] args) {
        Runnable r = () -> {
        for (int i = 1; i <= 10; i++) {
                        System.out.println(i);
                    }
                };
                Thread t = new Thread(r);
                t.start();
            }
        }
