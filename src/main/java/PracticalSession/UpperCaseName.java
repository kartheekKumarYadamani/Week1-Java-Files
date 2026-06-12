package PracticalSession;

import java.util.List;
import java.util.function.Consumer;

public class UpperCaseName {
    public static void main(String[] args) {
        List<String> names=List.of("sai","karthik","ashok");
        Consumer<List<String>> c1=l->l.
                forEach(name->
                        System.out.println(name.toUpperCase()));
        c1.accept(names);
    }
}
