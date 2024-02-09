package linkedList;

public class NodeLinkedList<T> {
    private T value;
    private NodeLinkedList next;

    public void setValue(T value) {
        this.value = value;
    }

    public void setNext(NodeLinkedList next) {
        this.next = next;
    }

    public T getValue() {
        return value;
    }

    public NodeLinkedList getNext() {
        return next;
    }


    public NodeLinkedList(T value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.format("%s", value);
    }
}
//
