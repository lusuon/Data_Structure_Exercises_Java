import java.util.NoSuchElementException;


public class Queue_NotPriority<T> {
    public class Node<T>{
        private T data;
        private Node prev;
        private Node next;

        public T getData() {
            return data;
        }
        public void setData(T data) {
            this.data = data;
        }
        public Node getPrev() {
            return prev;
        }
        public void setPrev(Node prev) {
            this.prev = prev;
        }
        public Node getNext() {
            return next;
        }
        public void setNext(Node next) {
            this.next = next;
        }

        public Node(T d){
            data = d;
            prev = null;
            next = null;
        }

        public Node(T d,Node p,Node n){
            data = d;
            prev = p;
            next = n;
        }
    }//存储工作信息的节点

    public Node front;//队首
    public Node rear;//队尾
    public int size;//队列的大小

    public Queue_NotPriority(){
        rear = new Node<T>(null,null,null);
        front = new Node<T>(null,rear,null);
        rear.next = front;
        size = 0;
    }//构造器

    public boolean isEmpty(){
        return rear.next == front;
    }//判断是否为空

    public void enQueue(Node<T> n){
        n.next = rear.next;
        rear.next = n;
        n.prev = rear;
        n.next.prev = n;
        size++;
    }//入队，保证队首队尾必须为空结点

    public void enQueue(T d){
        enQueue(new Node<T>(d));
        /*
        rear.next = new Node<T>(d,rear,rear.next);
        rear.next.next.prev = rear.next;
        */
    }

    public Node<T> deQueue(){
        if(isEmpty()) throw new NoSuchElementException();
        Node<T> pop =front.prev.prev.next;
        front.prev.prev.next = front;
        front.prev = front.prev.prev;
        size--;
        return pop;
    }//出队，并返回被出队的元素

    public void printQueue(){
        Node p = rear;
        while(p.next != null){
            System.out.print(p.data);
            System.out.print("->");
            p = p.next;
        }
        //System.out.print("Logical front:");
        System.out.println(p.data);
        System.out.println("");
    }//打印出队列的内容

    public static void main(String args[]){
        Queue_NotPriority<Integer> test = new Queue_NotPriority<Integer>();
        System.out.println("initial finished");
        test.printQueue();
        for(int i = 0;i<15;i++){
            System.out.println("enQueuing");
            test.enQueue(i);
            test.printQueue();
        }


        while(!test.isEmpty()){
            System.out.println("deQueuing");
            test.deQueue();
            test.printQueue();
        }
    }
}
