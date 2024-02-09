package stackAndQueue;

public class E02Queue {
    private E02Queue head;
    private E02Queue tail;
    private E02Queue next;
    private E02Queue previous;
    private int value;

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public E02Queue(int value) {
        this.value = value;
    }

    public E02Queue() {
    }

    public void enqueue(int number) {
        if (this.tail == null) {
            this.tail = new E02Queue(number);
        } else if (this.head == null) {
            this.head = new E02Queue(number);
            this.head.next = this.tail;
            this.tail.previous = this.head;
        } else {
            E02Queue e = new E02Queue(number);
            this.head.previous = e;
            e.next = this.head;
            this.head = e;
        }

    }

    public int size() {
        int i = 0;

        for(E02Queue e = this.tail; e != null; ++i) {
            e = e.previous;
        }

        return i;
    }

    public int dequeue() throws Exception {
        if (this.head == null) {
            throw new Exception();
        } else {
            int number;
            if (this.tail == null) {
                number = this.head.getValue();
                this.head = null;
                return number;
            } else {
                number = this.tail.getValue();
                this.tail = this.tail.previous;
                this.tail.next = null;
                return number;
            }
        }
    }

    public int[] dequeue(int amountOfElements) throws Exception {
        int index = amountOfElements;
        if (amountOfElements > 0 && amountOfElements <= this.size()) {
            int[] deletedElements = new int[amountOfElements];
            E02Queue e = this.tail;

            for(int i = 0; i < index; ++i) {
                deletedElements[i] = e.getValue();
                e = e.previous;
            }

            if (e == null) {
                this.tail = null;
                this.head = null;
            } else {
                this.tail = e;
                this.tail.next = null;
            }

            return deletedElements;
        } else {
            throw new Exception();
        }
    }

    public String returnValues() {
        E02Queue e = this.head;
        if (e == null) {
            e = this.tail;
        }

        String returnValues;
        for(returnValues = ""; e != null; e = e.next) {
            returnValues = String.format("%s, %s", returnValues, e.getValue());
        }

        StringBuilder sb = new StringBuilder();
        sb.append(returnValues);
        sb.delete(0, 2);
        returnValues = sb.toString();
        return returnValues;
    }

    public String toString() {
        return String.format("%s", this.value);
    }
}

