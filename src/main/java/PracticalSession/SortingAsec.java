package PracticalSession;

import java.util.ArrayList;
import java.util.List;

public class SortingAsec {
    public static void main(String[] args) {
        List<Integer> numbers=new ArrayList();
        numbers.add(1);
        numbers.add(10);
        numbers.add(0);
        numbers.add(5);
        numbers.add(8);
        numbers.sort((num1,num2)->Integer.compare(num2,num1));
        System.out.println(numbers);
    }
}
