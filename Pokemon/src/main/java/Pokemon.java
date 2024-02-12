public class Pokemon implements Cloneable {
    private int id;
    private String name;
    private String type1;
    private String type2;
    private int total;
    private double hp;
    private int baseAttack;
    private int defense;
    private int spAtk;
    private int spDef;
    private int speed;
    private Attack firstAttack;
    private Attack secondAttack;

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

    public String getType1() {
        return this.type1;
    }

    public void setType1(String type1) {
        this.type1 = type1;
    }

    public String getType2() {
        return this.type2;
    }

    public void setType2(String type2) {
        this.type2 = type2;
    }

    public int getTotal() {
        return this.total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public double getHp() {
        return this.hp;
    }

    public void setHp(double hp) {
        this.hp = hp;
    }

    public int getBaseAttack() {
        return this.baseAttack;
    }

    public void setBaseAttack(int baseAttack) {
        this.baseAttack = baseAttack;
    }

    public int getDefense() {
        return this.defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getSpAtk() {
        return this.spAtk;
    }

    public void setSpAtk(int spAtk) {
        this.spAtk = spAtk;
    }

    public int getSpDef() {
        return this.spDef;
    }

    public void setSpDef(int spDef) {
        this.spDef = spDef;
    }

    public int getSpeed() {
        return this.speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Attack getFirstAttack() {
        return this.firstAttack;
    }

    public void setFirstAttack(Attack firstAttack) {
        this.firstAttack = firstAttack;
    }

    public Attack getSecondAttack() {
        return this.secondAttack;
    }

    public void setSecondAttack(Attack secondAttack) {
        this.secondAttack = secondAttack;
    }

    public Pokemon(int id, String name, String type1, String type2, int total, int hp, int baseAttack, int defense, int spAtk, int spDef, int speed) {
        this.id = id;
        this.name = name;
        this.type1 = type1;
        this.type2 = type2;
        this.total = total;
        this.hp = (double)hp;
        this.baseAttack = baseAttack;
        this.defense = defense;
        this.spAtk = spAtk;
        this.spDef = spDef;
        this.speed = speed;
    }

    public String toString() {
        return String.format("%-20s%d\n%-20s%s\n%-20s%s\n%-20s%s\n%-20s%d\n%-20s%.2f\n%-20s%d\n%-20s%d\n%-20s%d\n%-20s%d\n%-20s%d\n", "ID:", this.id, "Name:", this.name, "Typ1", this.type1, "Typ2", this.type2, "Total-Bewertung:", this.total, "HP:", this.hp, "Basis-Atk:", this.baseAttack, "Verteidigung:", this.defense, "spAtk:", this.spAtk, "spDef:", this.spDef, "speed:", this.speed);
    }

    public Pokemon clone() {
        try {
            return (Pokemon)super.clone();
        } catch (CloneNotSupportedException var2) {
            throw new RuntimeException(var2);
        }
    }
}
