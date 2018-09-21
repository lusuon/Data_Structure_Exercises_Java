import java.util.Iterator;

public class MyLinkedList <Type> implements Iterable<Type> {
    private int theSize;
    private int modCount;

    private Node<Type> beginMarker;
    private Node<Type> endMarker;

    //the node and chain
    public static class Node<Type>{
        public Node(Type d,Node p,Node n){
            data = d;
            previous = p;
            next = n;
        }

        private Type data;
        private Node previous;
        private Node next;

        public Node getPrevious() {
            return previous;
        }

        public void setPrevious(Node previous) {
            this.previous = previous;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Type getData() {
            return data;
        }

        public void setData(Type data) {
            this.data = data;
        }
    }
    
    public MyLinkedList(){
        clear();
    }
    public void clear(){
        beginMarker = new Node<Type>((Type) "Begin",null,null);
        endMarker = new Node<Type>((Type)"End",beginMarker,null);
        beginMarker.next =endMarker;

        theSize = 0;
        modCount ++;
    }


    public Node<Type> getBeginMarker() {
        return beginMarker;
    }

    public void setBeginMarker(Node<Type> beginMarker) {
        this.beginMarker = beginMarker;
    }

    public Node<Type> getEndMarker() {
        return endMarker;
    }

    public void setEndMarker(Node<Type> endMarker) {
        this.endMarker = endMarker;
    }


    public int size(){return theSize;}
    public boolean isEmpty(){return this.size() == 0;}
    public boolean contains(Type d){
        Iterator iter = this.iterator();
        boolean result = false;
        while(iter.hasNext()){
            if((iter.next()) == d){
                result = true;
            }
        }
        return result;
    }
    
    public boolean add(Type x){
        add(size(),x);
        return true;
    }
    public void add(int index,Type x){
        addBefore(getNode(index),x);
    }
    
    public Type get (int index){
        return getNode(index).data;
    }
    public Type set(int index,Type newValue){
        Node<Type> p = getNode(index);
        Type oldValue = p.data;
        p.data = newValue;
        return oldValue;
    }
    
    public Type remove(int index){return remove(getNode(index));}
    public Type remove(Node<Type> p){
        p.next.previous = p.previous;
        p.previous.next = p.next;

        theSize --;
        modCount ++;

        return p.data;

    }
    public void removeAll(Iterable<? extends Type> items){
        Type item,element;
        Iterator<? extends Type> iter = items.iterator();

        while (iter.hasNext()){
            item = iter.next();
            Iterator<? extends Type> iterList = iterator();
            while(iterList.hasNext()){
                element = iterList.next();
                if(element.equals(item)) iterList.remove();
            }
        }
    }
    //O(MN) M-size of items,N-size of the list


    public void addBefore(Node<Type> p ,Type x){
        Node<Type> newNode = new Node<Type>(x,p.previous,p);//新结点与前后建立连接
        newNode.previous.next =newNode;
        p.previous = newNode;
        theSize++;
        modCount++;
    }
    public Node<Type> getNode(int index){
        Node<Type> p ;
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
    public java.util.Iterator<Type> iterator(){
        return new MyLinkedListIterator();
    }


    private class MyLinkedListIterator implements java.util.Iterator<Type>{
        private Node<Type> current = beginMarker.next;
        private int expectedModCount = modCount;
        private boolean okToMove = false;
        public boolean hasNext(){
            return beginMarker.next == endMarker;
            //return current != endMarker;
        }
        public Type next(){
            if(modCount !=expectedModCount){
                throw  new java.util.ConcurrentModificationException();
            }
            if (!hasNext()){
                throw new java.util.NoSuchElementException();
            }

            Type nextItem = current.data;
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

            while(nd!=endMarker){
                System.out.print(nd.data);
                System.out.print("->");
                nd = nd.next;
            }
            System.out.print(nd.data);
            System.out.println("\n");
        }
        catch(Exception e){
            e.printStackTrace();
        };
    }

    public static void main(String args[]){

    }
}
