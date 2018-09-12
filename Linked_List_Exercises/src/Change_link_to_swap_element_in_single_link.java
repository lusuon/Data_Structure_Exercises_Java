import java.util.Iterator;

public class Change_link_to_swap_element_in_single_link {
    public static void main(String args[]) {
        Simple_single_linkedList<Integer> list = new Simple_single_linkedList<Integer>();
        for (int i=1; i<10;i++){
            list.addTail(new Simple_single_linkedList.Node(i));
        }
        int index_a=0;
        int index_b=8;
        Simple_single_linkedList.Node Na,Nb,middle;
        Na = list.getHead();
        Nb = list.getHead();
        for (int i = 0;i<index_a;i++){
            Na = Na.getNext();
        }
        for (int j = 0;j<index_b;j++){
            Nb = Nb.getNext();
        }
        list.printList();


        //In the situation of double link
        //middle不能直接传入结点，实质为引用

        middle = new Simple_single_linkedList.Node();
        middle.setNext(Na.getNext());
        middle.setPrev(Na.getPrev());

        Na.setNext(Nb.getNext());
        Na.setPrev(Nb.getPrev());

        if (Nb != list.getHead()){
            Nb.getPrev().setNext(Na);
        }
        if(Nb != list.getTail()){
            Nb.getNext().setPrev(Na);
        }


        Nb.setNext(middle.getNext());
        Nb.setPrev(middle.getPrev());

        if (middle.getPrev() != null){
            middle.getPrev().setNext(Nb);
        }
        if(middle.getNext() != null){
            middle.getNext().setPrev(Nb);
        }
        list.printList();

    }

}
