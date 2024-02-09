package doubleLinkedList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class E01StackTest {
    private E01Stack stack;
    private DoubleLinkedList list;


    @BeforeEach
    void setUp() {
        stack = new E01Stack();
        list = new DoubleLinkedList();
        stack.setAdjustList(list);
    }


    @Test
    void push() {
        pushNumber(stack);

        assertEquals(4, list.getFirst().getValue());
        assertEquals(5, list.getFirst().getNext().getValue());
        assertEquals(6, list.getLast().getValue());

    }

    @Test
    void pop() {
        pushNumber(stack);
        try {
            stack.pop();
        } catch (Exception e) {
            System.out.println("Die Liste ist leer.");
        }
        assertEquals(5, list.getLast().getValue());
    }

    @Test
    void peek() {
        pushNumber(stack);
        int number = 0;
        try {
            number = stack.peek();
        } catch (Exception e) {
            System.out.println("Die Liste ist leer.");
        }
        assertEquals(6, number);
    }

    @Test
    void popMultiple() {
        pushNumber(stack);

        int[] arr01 = {4, 5, 6};
        int[] arr02;

        try {
            arr02 = stack.pop(3);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        String text01 = "";
        for (int j : arr01) {
            text01 += j;
        }
        String text02 = "";
        for (int j : arr02) {
            text02 += j;
        }
        assertEquals(text01, text02);

    }

    @Test
    void stackExceptions() {
        int number;
        try {
            number = stack.peek();
        } catch (Exception e) {
            System.out.println("Die Liste ist leer.");
        }

        System.out.println();
        try {
            stack.pop();
        } catch (Exception e) {
            System.out.println("Die Liste ist leer.");
        }

        System.out.println();
        int[] arr;
        try {
            arr = stack.pop(3);
        } catch (Exception e) {
            System.out.println("Die Liste ist leer.");
        }

    }


    private void pushNumber(E01Stack stack) {

        stack.push(4);
        stack.push(5);
        stack.push(6);
    }
}