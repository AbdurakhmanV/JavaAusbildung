package Camera;

public class Camera {
    private String id;
    private String brand;
    private double megaPixels;
    private double displaySize;
    private boolean color;
    private Lens lens;

    public Camera(String id, String brand, double megaPixel, double displaySize, boolean color, Lens lens) {
        this.id = id;
        this.brand = brand;
        this.megaPixels = megaPixel;
        this.displaySize = displaySize;
        this.color = color;
        this.lens = lens;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getMegaPixels() {
        return this.megaPixels;
    }

    public void setMegaPixels(double megaPixels) {
        this.megaPixels = megaPixels;
    }

    public double getDisplaySize() {
        return this.displaySize;
    }

    public void setDisplaySize(double displaySize) {
        this.displaySize = displaySize;
    }

    public boolean isColor() {
        return this.color;
    }

    public void setColor(boolean color) {
        this.color = color;
    }

    public Lens getLens() {
        return this.lens;
    }

    public void setLens(Lens lens) {
        this.lens = lens;
    }

    public String toString() {
        String colorText;
        if (this.color) {
            colorText = "Farbe";
        } else {
            colorText = "Schwarz-Weiss";
        }

        return String.format("%-40s%s\n%-40s%s\n%-40s%.2f\n%-40s%.2f\n%-40s%s\n%-40s\n%s", "id:", this.id, "Marke:", this.brand, "MegaPixel:", this.megaPixels, "Displaygroesse:", this.displaySize, "Farbe:", colorText, "Objektiv", this.lens.toString());
    }
}
