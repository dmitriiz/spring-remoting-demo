package eu.dazzled.dmitriiz.demo.model;

import java.io.Serializable;
import java.util.Set;

public class DemoResponse implements Serializable {
    private static final long serialVersionUID = 8634380386664773758L;
    private int id;
    private String hostname;
    private Set<String> addresses;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public Set<String> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<String> addresses) {
        this.addresses = addresses;
    }
}
