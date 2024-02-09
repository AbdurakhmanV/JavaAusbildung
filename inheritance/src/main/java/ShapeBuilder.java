public abstract class ShapeBuilder {
    private String color;
    private boolean isFilled;


    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean getIsFilled() {
        return isFilled;
    }

    public void setIsFilled(boolean filled) {
        isFilled = filled;
    }

    public void data() {
        System.out.println(String.format("%-20s%.2f", "Flaeche:", this.getArea()));
        System.out.println(String.format("%-20s%.2f", "Umfang:", this.getPerimeter()));
        System.out.println(String.format("%-20s%s", "Farbe:", this.getColor()));
        System.out.println(String.format("%-20s%s\n", "gefuellt:", this.getIsFilled()));
    }

    public abstract double getArea();


    public abstract double getPerimeter();
}
