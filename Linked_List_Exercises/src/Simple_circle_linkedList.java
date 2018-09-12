public class Simple_circle_linkedList<Integer>{
    public static class Node{
        private int data;
        private Node next;

        public Node(int i,Node n){
            data = i;
            next = n;
        }
        public Node(int i){
            this(i,null);
        }
        public Node(){
            this(0,null);
        }

        public int getData(){
            return data;
        }
        public void setData(int i){
            this.data = i;
        }
        public Node getNext() {
            return next;
        }
        public void setNext(Node next) {
            this.next = next;
        }
    }

    private Node head,tail;
    int size;

    public Simple_circle_linkedList(){
        tail = head = null;
        size = 0;
    }
    public void addHead(Node hd){
        //如果使用该方法增加链表的第一个节点，则head=tail=hd，且next指向自身。
        if(size==0){
            hd.setNext(hd);
            tail = head = hd;
            size++;
        }
        else{
            tail.setNext(hd);
            hd.setNext(head);
            head = hd;
            size++;
        }
    }
    public void addTail(Node tl){
        //如果使用该方法增加链表的第一个节点，则tail=head= hd，且next指向自身。
        if(size==0){
            tl.setNext(tl);
            tail = head = tl;
            size++;
        }else{
            tail.setNext(tl);
            tl.setNext(head);
            tail = tl;
            size++;
        }
    }
    public Node getHead(){
        return this.head;
    }
    public void setHead(Node head) {
        this.head = head;
    }
    public Node getTail() {
        return tail;
    }
    public void setTail(Node tail) {
        this.tail = tail;
    }
    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }


    //删除头部节点，被删掉的head将被自动回收
    public void delHead(){
        if(size>1){
            head = head.getNext();
            tail.setNext(head);
            size--;
        }
        else if(size==1){
            head = tail = null;
            size--;
        }
        else{
            System.out.println("There is no elements in the linked list.");
        }
    }
    //删除尾部节点
    public void delTail(){
        if(size>1){
            Node nd = new Node();
            nd = head;
            while(nd.getNext()!=tail){
                nd = nd.getNext();
            }
            nd.setNext(head);
            size--;
        }
        else if(size==1){
            head = tail = null;
            size--;
        }
        else{
            System.out.println("There is no elements in the linked list.");
        }
    }
    //打印全部节点
    public void printList(){
        Node nd = new Node();
        nd = head;
        try{
            while(nd.getNext()!=head){
                System.out.print(nd.getData());
                System.out.print("->");
                nd = nd.getNext();
            }
            System.out.print(nd.getData());
            System.out.print("->");
            System.out.print(head.getData());
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}