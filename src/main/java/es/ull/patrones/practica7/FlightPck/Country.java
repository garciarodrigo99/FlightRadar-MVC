package es.ull.patrones.practica7.FlightPck;

public class Country {
    private int id;
    private String name;
    private String code;
    private String codeLong;

    public Country(int id, String name, String code, String codeLong) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.codeLong = codeLong;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getCodeLong() {
        return codeLong;
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", codeLong='" + codeLong + '\'' +
                '}';
    }
}
