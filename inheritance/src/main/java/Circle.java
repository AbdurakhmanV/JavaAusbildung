public class Circle extends ShapeBuilder {
    private double radius;

    public Circle(double radius, String color, boolean isFilled) throws IllegalArgumentException {
        if (radius <= 0) {
            throw new IllegalArgumentException("Der Radius darf nicht kleiner gleich null sein.");
        }
        this.radius = radius;
        this.setColor(color);
        this.setIsFilled(isFilled);
    }


    public void data() {
        System.out.println(String.format("%-20s", "Kreis"));
        super.data();
    }


    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public double getPerimeter() {
        return Math.PI * 2 * radius;

    }

}
