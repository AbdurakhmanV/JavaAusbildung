package doubleLinkedList;

public class E02Queue {
    private DoubleLinkedList adjustList;

    public DoubleLinkedList getAdjustList() {
        return adjustList;
    }

    public void setAdjustList(DoubleLinkedList adjustList) {
        this.adjustList = adjustList;
    }

    public E02Queue(DoubleLinkedList adjustList) {
        this.adjustList = adjustList;
    }

    public E02Queue() {
    }

    public void enqueue(int number) {


        if (adjustList.getFirst() == null) {
            adjustList.setFirst(new Node(number));

        } else if (adjustList.getLast() == null) {
            adjustList.setLast(new Node(number));
            adjustList.getFirst().setNext(adjustList.getLast());
            adjustList.getLast().setPrevious(adjustList.getFirst());
        } else {
            Node e = new Node(number);
            adjustList.getLast().setNext(e);
            e.setPrevious(adjustList.getLast());
            adjustList.setLast(e);
        }

    }

    public int dequeue() throws Exception {

        if (adjustList.getFirst() == null) {
            throw new Exception();
        } else if (adjustList.getLast() == null) {
            int number = (int) adjustList.getFirst().getValue();

            adjustList.setFirst(null);
            return number;
        } else {

            int number = (int) adjustList.getFirst().getValue();
            adjustList.setFirst(adjustList.getFirst().getNext());
            adjustList.getFirst().setPrevious(null);
            return number;
        }
    }


    public int[] dequeue(int amountOfElements) throws Exception {
        if (amountOfElements <= 0 || amountOfElements > adjustList.size()) {
            throw new Exception();
        } else {
            int[] deletedElements = new int[amountOfElements];
            Node e = adjustList.getLast();
            for (int i = 0; i < amountOfElements; i++) {
                deletedElements[i] = (int) e.getValue();
                e = e.getPrevious();
            }
            if (e == null) {
                adjustList.setLast(null);
                adjustList.setFirst(null);
            } else {
                adjustList.setLast(e);
                adjustList.getLast().setNext(null);
            }
            return deletedElements;
        }
    }

}