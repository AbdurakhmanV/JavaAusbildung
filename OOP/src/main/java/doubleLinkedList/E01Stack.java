package doubleLinkedList;

public class E01Stack {
    private DoubleLinkedList adjustList;

    public DoubleLinkedList getAdjustList() {
        return adjustList;
    }

    public void setAdjustList(DoubleLinkedList adjustList) {
        this.adjustList = adjustList;
    }

    public E01Stack(DoubleLinkedList adjustList) {
        this.adjustList = adjustList;

    }
    public E01Stack(){

    }

    public void push(int number) {

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

    public int pop() throws Exception {
        int number;
        if (adjustList.getMarkNodeController().getMarkNode() != null) {
            number = (int) adjustList.getMarkNodeController().getMarkNode().getValue();
            Node pre = adjustList.getMarkNodeController().getMarkNode().getPrevious();
            Node nex = adjustList.getMarkNodeController().getMarkNode().getNext();
            if (nex != null && pre != null) {
                nex.setPrevious(pre);
                pre.setNext(nex);
            } else if (nex == null && pre != null) {
                adjustList.setLast(adjustList.getLast().getPrevious());
                adjustList.getLast().setNext(null);

            } else if (pre == null && nex != null) {
                adjustList.setFirst(nex);
            } else if (pre == null && nex == null) {
                adjustList.getMarkNodeController().setMarkNode(null);
            }

            if (adjustList.getMarkNodeController().getMarkNode().getMarkPrevious() != null) {
                adjustList.getMarkNodeController().setMarkNode(adjustList.getMarkNodeController().getMarkNode().getMarkPrevious());
                adjustList.getMarkNodeController().getMarkNode().setMarkNext(null);
            } else {
                adjustList.getMarkNodeController().setMarkNode(null);
            }
            return number;
        } else {
            if (adjustList.getFirst() == null) {
                throw new Exception();
            } else {
                //int number = Integer.parseInt((String) adjustList.getFirst().getValue());
                if (adjustList.getLast() != null) {
                    number = (int) adjustList.getLast().getValue();
                } else {
                    number = (int) adjustList.getFirst().getValue();
                }
                if (adjustList.getLast().getPrevious() == null) {
                    adjustList.setLast(null);
                    adjustList.setFirst(null);
                } else {
                    adjustList.setLast(adjustList.getLast().getPrevious());
                    adjustList.getLast().setNext(null);
                }

                return number;

            }
        }
    }

    public int peek() throws Exception {

        if (adjustList.getFirst() == null) {
            throw new Exception("Die Liste ist leer");
        } else {
            if(adjustList.getLast()==null){
                return (int) adjustList.getFirst().getValue();
            }
            return (int) adjustList.getLast().getValue();
        }
    }

    public int[] pop(int index) throws Exception {
        if (index <= 0 || index > adjustList.size()) {
            throw new Exception();
        } else {
            int[] deletedElements = new int[index];
            while (index > 0) {
                deletedElements[index - 1] = (pop());
                index--;
            }
            return deletedElements;
        }
    }

}
