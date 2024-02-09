import java.util.ArrayList;
import java.util.List;

public class MainShape {
    public static void main(String[] args) {

        Circle circleObj = new Circle(4, "rot", true);
        ShapeBuilder rectangleObj = new Rectangle(12, 4, "blau", true);
        ShapeBuilder squareObj = new Square(6, "grau", true);

        List<ShapeBuilder> shapeList = new ArrayList<>(List.of(circleObj, rectangleObj, squareObj));
        for (ShapeBuilder listCopy : shapeList
        ) {
            listCopy.data();

        }


    }
}
