package PracticalSession;

import java.util.List;
import java.util.stream.Collectors;

public class EmployeeSortDemo {
    public static void main(String[] args) {
        List<Integer> numbers=List.of(1,1,6,6,6,2,2,2,3,3,4,4,4,5,5);
//        list.stream()
//                .sorted(Comparator.comparing(Employee::getSalary))
//                .forEach(System.out::println);
//        list.stream()
//                .sorted(
//                        Comparator.comparing(Employee::getSalary)
//                                .reversed()
//                )
//                .forEach(System.out::println);

        List<Integer> result=numbers.stream().distinct().sorted().skip(3).limit(3).collect(Collectors.toList());
        System.out.println(result);
    }
}
