package doubleLinkedList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class E02QueueTest {

    private E02Queue queue;
    private DoubleLinkedList list;


    @BeforeEach
    void setUp() {
        queue = new E02Queue();
        list = new DoubleLinkedList();
        queue.setAdjustList(list);
    }

    @Test
    void enqueue() {
        enqueueNumber(queue);
        int number = 0;
        assertEquals(4, list.getFirst().getValue());
        assertEquals(5, list.getFirst().getNext().getValue());
        assertEquals(6, list.getLast().getValue());
    }

    @Test
    void dequeue() {
        enqueueNumber(queue);
        try {
            queue.dequeue();
        } catch (Exception e) {
            System.out.println("Die Liste ist leer.");
        }
        assertEquals(5, list.getFirst().getValue());
    }

    @Test
    void dequeueMultiple() {
        enqueueNumber(queue);

        int[] arr01 = {6, 5, 4};
        int[] arr02;

        try {
            arr02 = queue.dequeue(3);
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
    void queueExceptions() {
        int number = 0;

        System.out.println();
        try {
            queue.dequeue();
        } catch (Exception e) {
            System.out.println("Die Liste ist leer.");
        }

        System.out.println();
        int[] arr;
        try {
            arr = queue.dequeue(3);
        } catch (Exception e) {
            System.out.println("Die Liste ist leer.");
        }

    }

    private void enqueueNumber(E02Queue queue) {

        queue.enqueue(4);
        queue.enqueue(5);
        queue.enqueue(6);
    }
}