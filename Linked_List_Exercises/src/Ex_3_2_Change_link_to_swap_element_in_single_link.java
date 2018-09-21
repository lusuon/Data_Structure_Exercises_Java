import java.util.Iterator;
import java.util.NoSuchElementException;

public class Ex_3_2_Change_link_to_swap_element_in_single_link {


    //使用两个中继点存储
    public static void swap_single(MyLinkedList list, int index1, int index2){
        MyLinkedList.Node node1 = list.getNode(index1);
        MyLinkedList.Node node2 = list.getNode(index2);
        MyLinkedList.Node middle1 = list.getNode(index1 -1);
        MyLinkedList.Node middle2 = node1.getNext();
        System.out.println(node1.getData());
        System.out.println(node2.getData());
        if(node1 != list.getBeginMarker() && node1 != list.getEndMarker() && node2 != list.getBeginMarker()  && node2 != list.getEndMarker()){
            node1.setNext(node2.getNext());
            node2.getPrevious().setNext(node1);

            middle1.setNext(node2);
            node2.setNext(middle2);
        }else{
            throw new NoSuchElementException();
        }
    }

    //使用一个中继结点储存信息，记得修改相关结点的指针，结点初始化时不应直接指定为null，会引发空指针异常
    public static void swap_double(MyLinkedList list, int index1, int index2){
        MyLinkedList.Node node1 = list.getNode(index1);
        MyLinkedList.Node node2 = list.getNode(index2);
        System.out.println(node1.getData());
        System.out.println(node2.getData());
        MyLinkedList.Node middle= new MyLinkedList.Node(null,null,null);
        if(node1 != list.getBeginMarker() && node1 != list.getEndMarker() && node2 != list.getBeginMarker()  && node2 != list.getEndMarker()){
            middle.setNext(node1.getNext());
            middle.setPrevious(node1.getPrevious());

            node1.setPrevious(node2.getPrevious());
            node1.setNext(node2.getNext());

            node2.getPrevious().setNext(node1);
            node2.getNext().setPrevious(node1);

            node2.setPrevious(middle.getPrevious());
            node2.setNext(middle.getNext());

            middle.getPrevious().setNext(node2);
            middle.getNext().setPrevious(node2);
        }else{
            throw new NoSuchElementException();
        }
    }

    public static void main(String args[]) {
       MyLinkedList<Integer> list = new MyLinkedList<Integer>();
       for(int i = 1;i<6;i++){
           list.add(i);
       }
       list.print();
       swap_single(list,2,4);
       list.print();
       //swap_double(list,2,4);
       //list.print();
    }

}
