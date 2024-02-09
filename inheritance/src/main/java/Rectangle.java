public class Rectangle extends ShapeBuilder {

    private double length;
    private double width;

    public Rectangle(double length, double width, String color, boolean isFilled) throws IllegalArgumentException {
        if (length <= 0 && length == width) {
            throw new IllegalArgumentException("Die Laenge des Quadrates darf nicht kleiner gleich null sein.");
        }
        if (length <= 0 || width <= 0) {
            throw new IllegalArgumentException("Die Laenge und die Breite des Rechtsecks duerfen nicht kleiner gleich null sein.");
        }
        this.length = length;
        this.width = width;
        this.setColor(color);
        this.setIsFilled(isFilled);
    }

    @Override
    public void data() {
        if (this.length == this.width) {
            System.out.println(String.format("%-20s", "Quadrat"));
        } else {
            System.out.println(String.format("%-20s", "Rechteck"));
        }
        super.data();
    }


    @Override
    public double getArea() {
        return width * length;
    }

    @Override
    public double getPerimeter() {
        return (2 * width) + (2 * length);
    }
}