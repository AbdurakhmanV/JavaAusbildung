package doubleLinkedList;

public class DoubleLinkedList {
    private Node first;

    public Node getFirst() {
        return first;
    }

    public void setFirst(Node first) {
        this.first = first;
    }

    public Node getLast() {
        return last;
    }

    public void setLast(Node last) {
        this.last = last;
    }

    private Node last;

    private MarkNode markNodeController = new MarkNode();

    public MarkNode getMarkNodeController() {
        return markNodeController;
    }

    public void setMarkNodeController(MarkNode markNodeController) {
        this.markNodeController = markNodeController;
    }


    public DoubleLinkedList() {

    }

    public boolean add(Node e) {
        if (first == null) {
            this.first = e;

        } else if (last == null) {
            this.last = e;
            this.first.setNext(last);
            this.last.setPrevious(first);


        } else {
            this.last.setNext(e);
            e.setPrevious(this.last);
            this.last = e;

        }
        if (markNodeController.getMarkNode() != null) {
            markNodeController.getMarkNode().setMarkNext(e);
            e.setMarkPrevious(markNodeController.getMarkNode());
            markNodeController.setMarkNode(e);
        }
        return true;
    }

    public void add(int index, Node e) {
        if (index < 0 || index > size()) {
            System.out.println("Dieser Index existiert nicht.");
        } else if (index == size()) {
            this.add(e);

        } else if (index == 0) {
            first.setPrevious(e);
            e.setNext(first);
            first = e;
        } else {
            Node before = get(index);
            before.getPrevious().setNext(e);
            e.setNext(before);
            e.setPrevious(before.getPrevious());
            before.setPrevious(e);
        }
        if (markNodeController.getMarkNode() == null) {
            markNodeController.setMarkNode(e);
        } else {
            markNodeController.getMarkNode().setMarkNext(e);
            e.setMarkPrevious(markNodeController.getMarkNode());
            markNodeController.setMarkNode(e);
        }
    }

    public void remove(int index) {
        if (index >= size() || index < 0) System.out.println("Dieser Index existiert nicht.");
        else if (index == size() - 1) {
            last = last.getPrevious();
            last.setNext(null);
        } else if (index == 0) {
            first = first.getNext();
            first.setPrevious(null);
        } else {
            Node nodeToRemove = get(index);
            Node prevNode = nodeToRemove.getPrevious();
            Node nextNode = nodeToRemove.getNext();

            prevNode.setNext(nextNode);

            nextNode.setPrevious(prevNode);
        }
    }

    public Node get(int index) {
        if (index < 0 || index >= size()) {
            System.out.println("Dieser Index existiert nicht.");
        } else {
            int middel = size() / 2;
            if (index <= middel) {
                Node current = first;
                for (int i = 0; i < index; i++) {
                    current = current.getNext();
                }
                return current;
            } else {
                Node current = last;
                for (int i = size() - 1; i > index; i--) {
                    current = current.getPrevious();
                }
                return current;
            }
        }
        return null;
    }

    public String returnValues() {
        Node e = first;
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

    public String returnValuesReverse() {
        Node e = last;
        String returnValues = "";
        while (e != null) {
            String preReturnValue = returnValues;
            returnValues = String.format("%s, %s", preReturnValue, e.getValue());
            e = e.getPrevious();
        }
        StringBuilder sb = new StringBuilder();
        sb.append(returnValues);
        sb.delete(0, 2);
        returnValues = sb.toString();
        return returnValues;
    }


    public int size() {
        int i = 0;
        Node e = first;
        while (e != null) {
            e = e.getNext();
            i++;
        }
        return i;
    }

}
