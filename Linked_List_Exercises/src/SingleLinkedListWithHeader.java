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
        if (index<0||index>size){
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
            System.out.println(pointer.data);
            System.out.println("->");
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
        MySequenceList list = new MySequenceList();
        //插入元素
        for(String s:line.split(",")){
            list.add(Integer.parseInt(s));
        }
        System.out.println("Add numbers from 1 to 100,and insert a big number at the second place");
        list.add(1,Integer.parseInt(br.readLine()));
        System.out.println(Arrays.toString(list.elements));


        //查找并获取元素
        System.out.println("Get the fifth element:");
        System.out.println(list.get(4));


        //删除元素
        System.out.println("Remove the second element:");
        list.remove(1);
        System.out.println(Arrays.toString(list.elements));
        System.out.println("Remove the element,whose value is 10,by finding element's index:");
        list.remove_by_element(10);
        System.out.println(Arrays.toString(list.elements));

        //传入数组实现添加的addAll()方法
        line = br.readLine();
        System.out.println("The example of addAll(),add an array from 100 to 95:");
        ArrayList<Integer> items=new ArrayList<Integer>();
        for (String s:line.split(",")){
            items.add(Integer.parseInt(s));
        }
        list.addAll(items);
        System.out.println(Arrays.toString(list.elements));

        //反向迭代演示
        System.out.println("Testing the reverseIterator:");
        Iterator ritr = list.reverseIterator();
        while(ritr.hasNext()) System.out.println(ritr.next());
    }
}