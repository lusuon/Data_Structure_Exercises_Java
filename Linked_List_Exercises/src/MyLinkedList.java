import java.util.Iterator;

public class MyLinkedList <AnyType> implements Iterable<AnyType> {
    //the node and chain
    private static class Node<AnyType>{
        public Node(AnyType d,Node p,Node n){
            data = d;
            previous = p;
            next = n;
        }
        private AnyType data;
        private Node previous;
        private Node next;
    }
    public MyLinkedList(){
        clear();
    }
    public void clear(){
        beginMarker = new Node<AnyType>(null,null,null);
        endMarker = new Node<AnyType>(null,beginMarker,null);
        beginMarker.next =endMarker;

        theSize = 0;
        modCount ++;
    }
    public int size(){return theSize;}
    public boolean isEmpty(){return this.size() == 0;}
    public boolean contains(AnyType d){
        Iterator iter = this.iterator();
        boolean result = false;
        while(iter.hasNext()){
            if((iter.next()) == d){
                result = true;
            }
        }
        return result;
    }
    public boolean add(AnyType x){
        add(size(),x);
        return true;
    }
    public void add(int index,AnyType x){
        addBefore(getNode(index),x);
    }
    public AnyType get (int index){
        return getNode(index).data;
    }
    public AnyType set(int index,AnyType newValue){
        Node<AnyType> p = getNode(index);
        AnyType oldValue = p.data;
        p.data = newValue;
        return oldValue;
    }
    public AnyType remove(int index){return remove(getNode(index));}
    private void addBefore(Node<AnyType> p ,AnyType x){
        Node<AnyType> newNode = new Node<AnyType>(x,p.previous,p);//新结点与前后建立连接
        newNode.previous.next =newNode;
        p.previous = newNode;

        theSize++;
        modCount++;
    }
    private AnyType remove(Node<AnyType> p){
        p.next.previous = p.previous;
        p.previous.next = p.next;

        theSize --;
        modCount ++;

        return p.data;

    }
    private Node<AnyType> getNode(int index){
        Node<AnyType> p ;
        if(index <0 || index > size()){
            throw  new IndexOutOfBoundsException();
        }
        if (index<size()/2){
            p = beginMarker.next;
            for (int i =0;i<index;i++){
                p=p.next;
            }
        }else {
            p = endMarker;
            for (int i = size(); i > index; i--) {
                p = p.previous;
            }
        }
        return p;
    }
    public java.util.Iterator<AnyType> iterator(){
        return new MyLinkedListIterator();
    }


    private class MyLinkedListIterator implements java.util.Iterator<AnyType>{
        private Node<AnyType> current = beginMarker.next;
        private int expectedModCount = modCount;
        private boolean okToMove = false;
        public boolean hasNext(){
            return beginMarker.next == endMarker;
            //return current != endMarker;
        }
        public AnyType next(){
            if(modCount !=expectedModCount){
                throw  new java.util.ConcurrentModificationException();
            }
            if (!hasNext()){
                throw new java.util.NoSuchElementException();
            }

            AnyType nextItem = current.data;
            current = current.next;
            okToMove = true;
            return nextItem;
        }
        public void remove(){
            if(modCount !=expectedModCount){
                throw  new java.util.ConcurrentModificationException();
            }
            if (!okToMove) {
                throw new IllegalStateException();
            }

            MyLinkedList.this.remove(current.previous);
            okToMove = false;
            expectedModCount ++;
        }
    }


    public void print() {
        Node nd = this.beginMarker;
        try{
            while(nd.next!=endMarker){
                System.out.print(nd.data);
                System.out.print("->");
                nd = nd.next;
            }
            System.out.print("->");
            System.out.print(nd.data);
        }
        catch(Exception e){
            e.printStackTrace();
        };
    }

    private int theSize;
    private int modCount;
    private Node<AnyType> beginMarker;
    private Node<AnyType> endMarker;
}
