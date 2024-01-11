package es.ull.patrones.practica7.Model;

public class Timezone {
    private String name;
    private int offset;
    private String offsetHours;
    private String abbr;
    private String abbrName;
    private boolean isDst;

    public Timezone(String name, int offset, String offsetHours, String abbr, String abbrName, boolean isDst) {
        this.name = name;
        this.offset = offset;
        this.offsetHours = offsetHours;
        this.abbr = abbr;
        this.abbrName = abbrName;
        this.isDst = isDst;
    }

    public String getName() {
        return name;
    }

    public int getOffset() {
        return offset;
    }

    public String getOffsetHours() {
        return offsetHours;
    }

    public String getAbbr() {
        return abbr;
    }

    public String getAbbrName() {
        return abbrName;
    }

    public boolean isDst() {
        return isDst;
    }

    @Override
    public String toString() {
        return "Timezone{" +
                "name='" + name + '\'' +
                ", offset=" + offset +
                ", offsetHours='" + offsetHours + '\'' +
                ", abbr='" + abbr + '\'' +
                ", abbrName='" + abbrName + '\'' +
                ", isDst=" + isDst +
                '}';
    }
}
