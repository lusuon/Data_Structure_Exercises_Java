import java.util.ArrayList;
import java.util.Iterator;

public class Ex_3_6_Josephus_problem {
    //关键：实现循环链表
    public static void main(String args[]) {
        Simple_circle_linkedList<Integer> people = new Simple_circle_linkedList<Integer>();
        int M = 4;
        int N = 12;
        Simple_circle_linkedList.Node pointer,container;

        for (int i =1;i<=N;i++){
            Simple_circle_linkedList.Node node = new Simple_circle_linkedList.Node(i);
            people.addTail(node);
        }
        //people.printList();
        pointer=people.getHead();
        container = people.getTail();

        int count = 0;
        while(people.size != 1) {
            if (count == M) {
                //delete the node
                container.setNext(pointer.getNext());
                pointer.setNext(null);
                pointer = container.getNext();
                count = 0;
                people.setSize(people.getSize()-1);
            } else if(pointer.getNext() != null) {
                count++;
                container = pointer;
                pointer = pointer.getNext();
            }
            people.setHead(container);
            people.printList();
            System.out.println('\n');
        }
        System.out.println(people.getHead().getData());
    }

}