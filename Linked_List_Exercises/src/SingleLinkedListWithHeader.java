import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class SingleLinkedListWithHeader<Type> implements Iterable<Type>{
    public class Node<Type> {
        private Type data;
        private Node next;

        public Type getData() {
            return data;
        }
        public void setData(Type data) {
            this.data = data;
        }
        public Node getNext() {
            return next;
        }
        public void setNext(Node next) {
            this.next = next;
        }

        public Node(Type d,Node<Type> n){
            data = d;
            next = n;
        }
        public Node(Type d){
            data = d;
        }

    }
    public Node getNode(int index){
        Node<Type> pointer = head.next;
        if(index == -1) return head;
        if (index<-1||index>size){
            throw new IndexOutOfBoundsException();
        }
        for(int i = 0;i<index;i++){
            pointer = pointer.next;
        }
        return pointer;
    }
    public void setNode(int index,Type d){
        getNode(index).data = d;
    }


    private Node<Type> head;
    private int size;

    //basic getter and setter
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

    //initialize the linkedList
    public SingleLinkedListWithHeader(){
        clear();
    }
    public void clear(){
        head = new Node<Type>(null,new Node<Type>(null,null));
        size = 0;
    }


    //empty checker
    public boolean isEmpty(){
        return size == 0;
    }



    //insert the node at the appointed location
    public void add(int index,Type d){
        Node node = new Node(d);
        node.next = getNode(index-1).next;
        getNode(index-1).next = node;
        size++;
    }
    //add to the tail of the linkedList
    public void add(Type d){
        add(size,d);
    }

    //remove the appointed element
    public void remove(int index){
        Node front = getNode(index-1);
        Node toBeDelete = front.next;
        front.next = toBeDelete.next;
        toBeDelete.next = null;
        size --;
    }

    @Override
    public Iterator<Type> iterator() {
        return new SingleLinkedListWithHeaderIterator();
    }

    private class SingleLinkedListWithHeaderIterator implements java.util.Iterator<Type>{
        private Node<Type> current = head.next;
        int index = 0;

        @Override
        public boolean hasNext() {
            return current.next!=null;
        }

        @Override
        public Type next() {
            if(!hasNext()){
                throw new NoSuchElementException();
            }

            Type item = current.data;
            current = current.next;
            index++;
            return item;
        }

        @Override
        public void remove() {
            SingleLinkedListWithHeader.this.remove(index);
        }
    }

    public void printList(){
        Node<Type> pointer = head.next;
        while(pointer.next != null){
            System.out.print(pointer.data);
            System.out.print("->");
            pointer = pointer.next;
        }
        System.out.println(pointer.data);
    }

    public boolean contain(Type d){
        Node<Type> pointer = head.next;
        while(pointer != null){
            if(pointer.data.equals(d)){
                return true;
            }
            pointer = pointer.next;
        }
        return false;
    }


    public static void main(String args[]) throws IOException {
        String path ="./test.txt";
        File filename = new File(path); // 要读取以上路径的input。txt文件
        InputStreamReader reader = new InputStreamReader(new FileInputStream(filename)); // 建立一个输入流对象reader
        BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
        String line = "";
        line = br.readLine();
        SingleLinkedListWithHeader list = new SingleLinkedListWithHeader();


        //插入元素
        for(String s:line.split(",")){
            list.add(Integer.parseInt(s));
        }
        System.out.println("Add numbers from 1 to 10,and insert a big number at the second place");
        list.add(1,Integer.parseInt(br.readLine()));
        list.printList();


        //查找并获取元素
        System.out.println("Get the fifth element:");
        System.out.println(list.getNode(4).getData());


        //删除元素
        System.out.println("Remove the second element:");
        list.remove(1);
        list.printList();

        //迭代演示
        System.out.println("Testing the reverseIterator:");
        Iterator itr = list.iterator();
        while(itr.hasNext()) System.out.println(itr.next());
    }
}