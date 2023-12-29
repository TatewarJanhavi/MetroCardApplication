package model;

public enum Station {
    CENTRAL("CENTRAL"),
    AIRPORT("AIRPORT");
    private String name ;
     Station(String name) {
        this.name = name ;
    }

    public String getName() {
        return name;
    }
}
