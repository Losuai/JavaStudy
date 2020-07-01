package java8;

import lombok.Data;

@Data
public class Employee {
    private int id;
    private String name;
    private int age;

    public Employee(int id, String name, int age) {
        this.age = age;
        this.id = id;
        this.name = name;
    }
}
