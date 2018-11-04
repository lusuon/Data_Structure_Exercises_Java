import java.util.NoSuchElementException;

public class MinHeap<T> {

    T[] elements;
    int size;
    private static final int defaultSize=10;
    //最小堆的实现
    public void makeEmpty(){
        size = 0;
    }
    public boolean isEmpty(){return size==0;}
    public boolean isFull(){return size==elements.length;}

    public MinHeap(int maxSize){
        int length = defaultSize<maxSize?
    }

    public void enQueue(T x){
        if(size==elements.length){
            //拓展或抛出异常
        }
        elements[size]=x;
        filterUp(size);//上滤
        size++;
    }
    public void filterUp(int start){
        int j =start;
        int i =(j-1)/2;//parent
        T temp = elements[j];
        while(j>0){
            if(elements[i].key<=temp.key) break;//比较优先级.key
            else{
                elements[j] = elements[i];
                j=i;
                i=(i=1)/2;
            }
        }
        elements[j] =temp;
    }

    public T deQueue(){
        if(size == 0) throw new NoSuchElementException();
        //比较路径上
        T pop = elements[0];
        elements[0] = elements[size-1];
        size--;
        filterDown(0,size-1);
        return pop;
    }

    public void filterDown(int start ,int endOfHeap){
        int i =start, j=2*i+1;
        T temp = elements[i];
        while(j<=endOfHeap){
            if(j<endOfHeap && elements[j].key>elements[j+1].key) j++;
            if(temp.key <= elements[j].key) break;
            else{
                elements[i]=elements[j];
                i=j;
                j=2*j+1;
            }
        }
        elements[i] = temp;
    }

    public MinHeap(T[] arr){
        //根据传入数组，创建堆
        int length = defaultSize<arr.length ? arr.length:defaultSize;
        elements = (T[])new Object[length];
        for(int j=0;j<arr.length;j++) elements[j]=arr[j];//将数组构造成堆的雏形
        size = arr.length;
        int currentPos = (size-2)/2;//最后一个内部节点
        while(currentPos>0){
            filterDown(currentPos,size - 1);
        }
        currentPos--;
    }
}
