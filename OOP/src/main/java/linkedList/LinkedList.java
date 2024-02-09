package linkedList;

public class LinkedList {
    private NodeLinkedList first;
    private NodeLinkedList last;


    public LinkedList(NodeLinkedList first, NodeLinkedList last) {
        this.first = first;
        this.last = last;
        this.first.setNext(last);

    }

    public boolean add(NodeLinkedList e) {
        if (first.getNext() == null) {
            this.first.setNext(e);
        }
        this.last.setNext(e);
        this.last = e;

        return true;

    }

    public void add(int index, NodeLinkedList e) {
        if (index < 0 || index >= size()) {
            System.out.println("Dieser Index existiert nicht.");
        } else if (index == 0) {
            e.setNext(first);
            first = e;
        } else {
            NodeLinkedList before = get(index);
            get(index - 1).setNext(e);
            e.setNext(before);
        }
    }

    public void remove(int index) {
        if (index >= size() || index < 0) System.out.println("Dieser Index existiert nicht.");
        else if (index == size() - 1) {
            NodeLinkedList help = get(index - 1);
            help.setNext(null);
        } else if (index == 0) {
            NodeLinkedList e = first;
            int size = size() - 2;
            for (int i = 0; i <= size; i++) {
                if (i == size) {
                    e.setValue(e.getNext().getValue());
                    e.setNext(null);
                } else if (i < size()) {
                    e.setValue(e.getNext().getValue());
                    e = e.getNext();
                }
            }
        } else {
            NodeLinkedList help01 = get(index - 1);
            NodeLinkedList help02 = get(index + 1);
            help01.setNext(help02);

            NodeLinkedList remove = get(index);
            remove.setNext(null);
        }
    }

    public NodeLinkedList get(int index) {
        if (index < 0 || index >= size()) {
            System.out.println("Dieser Index existiert nicht.");
            return null;
        } else {
            NodeLinkedList current = first;
            for (int i = 0; i < index; i++) {
                current = current.getNext();
            }
            return current;
        }
    }

    public String returnValues() {
        NodeLinkedList e = first;
        String returnValues = "";
        while (e != null) {
            String preReturnValue = returnValues;
            returnValues = String.format("%s, %s", preReturnValue, e.getValue());
            e = e.getNext();
        }
        StringBuilder sb = new StringBuilder();
        sb.append(returnValues);
        sb.delete(0, 2);
        returnValues = sb.toString();
        return returnValues;
    }

    public int size() {
        int i = 0;
        NodeLinkedList e = first;
        while (e != null) {
            e = e.getNext();
            i++;
        }
        return i;
    }
}
//
