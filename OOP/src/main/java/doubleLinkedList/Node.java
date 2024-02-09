package doubleLinkedList;

public class Node<T>{
    private T value;
    private Node previous;
    private Node next;

    private Node markPrevious;

    public Node getMarkPrevious() {
        return markPrevious;
    }

    public void setMarkPrevious(Node markPrevious) {
        this.markPrevious = markPrevious;
    }

    public Node getMarkNext() {
        return markNext;
    }

    public void setMarkNext(Node markNext) {
        this.markNext = markNext;
    }

    private Node markNext;

    public void setValue(T value) {
        this.value = value;
    }

    public void setPrevious(Node previous) {
        this.previous = previous;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public T getValue() {
        return value;
    }

    public Node getPrevious() {
        return previous;
    }

    public Node getNext() {
        return next;
    }


    public Node(T value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.format("%s", value);
    }
}
