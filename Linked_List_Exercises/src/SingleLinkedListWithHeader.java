import java.util.Iterator;
import java.util.function.Consumer;

public class SingleLinkedListWithHeader<Type> implements Iterator<Type>{
    public class Node<Type> {
        Type data;
        Node next;

        public Node(Type d,Node<Type> n){
            data = d;
            next = n;
        }
        public Node(Type d){
            data = d;
        }

    }



    private Node<Type> head;
    private int size;

    public Node<Type> getHead() {
        return head;
    }
    public void setHead(Node<Type> head) {
        this.head = head;
    }
    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public Node getNode(int index){
        Node<Type> pointer = head.next;
        if (index<0||index>size){
            throw new IndexOutOfBoundsException();
        }
        for(int i = 0;i<index;i++){
            pointer = pointer.next;
        }
        return pointer;
    }
    public void insert(int index,Node node){
        getNode(index-1).next = ;
        size++;
    }

    public SingleLinkedListWithHeader(){
        clear();
    }

    public void clear(){
        head = new Node<Type>(null,new Node<Type>(null,null));
        size = 0;
    }
    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public Type next() {
        return null;
    }

    @Override
    public void remove() {

    }

    }
}