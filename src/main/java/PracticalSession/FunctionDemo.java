package PracticalSession;

import java.util.function.Function;

public class FunctionDemo {
    public static void main(String[] args) {
        Function<Integer,Integer> f= a->{
            return a*10;
        };
        System.out.println(f.apply(10));
    }
}
