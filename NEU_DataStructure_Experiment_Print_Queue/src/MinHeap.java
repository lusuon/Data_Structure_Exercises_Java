import java.util.NoSuchElementException;

public class MinHeap<T> {
    Event[] elements;//存放堆内元素的数组
    int size;//堆的大小
    private static final int defaultSize=10;

    //最小堆的实现
    public void makeEmpty(){
        size = 0;
    }
    public boolean isEmpty(){return size==0;}
    public boolean isFull(){return size==elements.length;}

    public MinHeap(int maxSize){
        int length = defaultSize<maxSize ? maxSize:defaultSize;
        elements = (Event[]) new Object[length];
        size=0;
    }//构造器

    public void enQueue(Event x){
        if(size==elements.length){
            //堆已满拓展或抛出异常
            Event[] oldList = elements;
            elements = (Event[]) new Object[size*2+1];
            for (int i = 0; i < size; i++) {
                elements[i] = oldList[i];
            }
        }
        elements[size]=x;
        filterUp(size);//上滤
        size++;
    }//入队，并在堆内排序

    public void filterUp(int start){
        int j =start;
        int i =(j-1)/2;//parent
        Event temp = elements[j];
        while(j>0){
            if(temp.compareTo(elements[i])>0)break;//比较优先级
            else{
                elements[j] = elements[i];
                j=i;
                i=(i=1)/2;
            }
        }
        elements[j] =temp;
    }//最小堆内元素上滤操作

    public Event deQueue(){
        if(size == 0) throw new NoSuchElementException();
        //比较路径上
        Event pop = elements[0];
        elements[0] = elements[size-1];
        size--;
        filterDown(0,size-1);
        return pop;
    }//优先级最低队尾元素出队

    public void filterDown(int start ,int endOfHeap){
        int i =start, j=2*i+1;
        Event temp = elements[i];
        while(j<=endOfHeap){
            if(j<endOfHeap && elements[j].compareTo(elements[j+1])<0 ) j++;//elements[j].key>elements[j+1].key
            if(temp.compareTo(elements[j])>=0) break;//temp.key <= elements[j].key
            else{
                elements[i]=elements[j];
                i=j;
                j=2*j+1;
            }
        }
        elements[i] = temp;
    }//最小堆内元素下滤操作
}
