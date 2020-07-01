package hibernate;

import org.springframework.stereotype.Component;

@Component("c")
public class Category {
    private int id;
    private String name = "category";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
