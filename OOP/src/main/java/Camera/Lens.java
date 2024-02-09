package Camera;

public class Lens {
    private String id;
    private double maxFocalLength;
    private double minFocalLength;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getMaxFocalLength() {
        return this.maxFocalLength;
    }

    public void setMaxFocalLength(double maxFocalLength) {
        this.maxFocalLength = maxFocalLength;
    }

    public double getMinFocalLength() {
        return this.minFocalLength;
    }

    public void setMinFocalLength(double minFocalLength) {
        this.minFocalLength = minFocalLength;
    }

    public Lens() {
    }

    public Lens(String id, double minFocalLength, double maxFocalLength) throws Exception {
        this.id = id;
        this.maxFocalLength = maxFocalLength;
        this.minFocalLength = minFocalLength;
        if (maxFocalLength <= minFocalLength) {
            throw new Exception("Die maximale Brennweite muss groesser sein als die minimale Brennweite");
        }
    }

    public String toString() {
        return String.format("%-40s%s\n%-40s%.2f\n%-40s%.2f", "id:", this.id, "minimale Brennweite:", this.minFocalLength, "maximale Brennweite:", this.maxFocalLength);
    }
}
