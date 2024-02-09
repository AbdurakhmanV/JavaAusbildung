package stackAndQueue;

public class E01Stack {
    private E01Stack head;
    private E01Stack tail;
    private E01Stack next;
    private E01Stack previous;
    private int value;





    public void setValue(int value) {
        this.value = value;
    }
    public int getValue(){
        return value;
    }



    public E01Stack(int value) {
        this.value=value;
    }


    public E01Stack(){


    }

    public void push(int number){


        if(head==null){
            head = new E01Stack(number);

        }
        else if(tail==null){
            tail = new E01Stack(number);
            head.next=tail;
            tail.previous=head;


        }
        else {
            E01Stack e = new E01Stack(number);
            tail.next=e;
            e.previous = tail;
            tail=e;
        }
    }

    public int size(){
        int i = 0;
        E01Stack e = head;
        while(e != null){
            e=e.next;
            i++;
        }
        return i;
    }

    public int pop() throws Exception{
        if(head==null) {
            throw new Exception();
        }else {
            int number = tail.getValue();

            tail = tail.previous;
            if(tail==null){
                tail=null;
                head=null;
            }else {
                tail.next = null;
            }
            return number;
        }
    }

    public int peek() throws Exception{
        if(head==null) {
            throw new Exception("Exception message");
        }else {
            return head.getValue();
        }
    }

    public int[] pop(int index) throws Exception{
        if(index<=0 || index> size()){
            throw new Exception();
        }else {
            int[] deletedElements = new int[index];
            E01Stack e = tail;
            for (int i = 0; i < index; i++) {
                deletedElements[i] = e.getValue();
                e = e.previous;
            }
            if(e==null){
                tail=null;
                head=null;
            }else{
                tail=e;
                tail.next=null;
            }
//            head = e;
//            head.previous = null;
            return deletedElements;
        }
    }
    public String returnValues(){
        E01Stack e = head;
        String returnValues = "";
        while(e != null){
            String preReturnValue = returnValues;
            returnValues = String.format("%s, %s", preReturnValue, e.getValue());
            e = e.next;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(returnValues);
        sb.delete(0,2);
        returnValues=sb.toString();
        return returnValues;
    }



    @Override
    public String toString() {
        return String.format("%s", value);
    }
}
