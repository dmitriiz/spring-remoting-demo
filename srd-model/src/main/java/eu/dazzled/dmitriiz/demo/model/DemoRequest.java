package eu.dazzled.dmitriiz.demo.model;

import java.io.Serializable;

public class DemoRequest implements Serializable {
    private static final long serialVersionUID = 4763293307093092917L;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
