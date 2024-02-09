import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CircleTest {

    private Circle circle01 = new Circle(6, "gruen", true);
    private Circle circle02 = new Circle(4, "rot", true);


    @Test
    void getArea() {
        assertEquals(Math.PI * 6 * 6, circle01.getArea());
        assertEquals(Math.PI * 4 * 4, circle02.getArea());
    }

    @Test
    void getPerimeter() {
        assertEquals(2 * Math.PI * 6, circle01.getPerimeter());
        assertEquals(2 * Math.PI * 4, circle02.getPerimeter());
    }

    @Test
    void exceptionTest() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
            Circle circle01 = new Circle(-2, "rot", false);
        });
        System.out.println(e);
        assertEquals(e.getMessage(), "Der Radius darf nicht kleiner gleich null sein.");


        IllegalArgumentException f = assertThrows(IllegalArgumentException.class, () -> {
            Circle circle02 = new Circle(0, "rot", false);
        });
        System.out.println(f);
        assertEquals(e.getMessage(), "Der Radius darf nicht kleiner gleich null sein.");

    }
}