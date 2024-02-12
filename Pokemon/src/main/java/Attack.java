public class Attack {
    private int id;
    private String name;
    private String effect;
    private String type;
    private String kind;
    private int power;
    private int accuracy;
    private int pp;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEffect() {
        return this.effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKind() {
        return this.kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public int getPower() {
        return this.power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getAccuracy() {
        return this.accuracy;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    public int getPp() {
        return this.pp;
    }

    public void setPp(int pp) {
        this.pp = pp;
    }

    public Attack(int id, String name, String effect, String type, String kind, int power, int accuracy, int pp) {
        this.id = id;
        this.name = name;
        this.effect = effect;
        this.type = type;
        this.kind = kind;
        this.power = power;
        this.accuracy = accuracy;
        this.pp = pp;
    }

    public String toString() {
        return String.format("%-20s%d\n%-20s%s\n%-20s%s\n%-20s%s\n%-20s%s\n%-20s%d\n%-20s%d\n%-20s%d", "ID:", this.id, "Name:", this.name, "Effekt:", this.effect, "Typ:", this.type, "Art:", this.kind, "Power:", this.power, "Praezision", this.accuracy, "PP:", this.pp);
    }
}
//