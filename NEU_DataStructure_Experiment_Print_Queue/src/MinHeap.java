import java.util.NoSuchElementException;

public class MinHeap<T extends Comparable<? super T>> {
    private static final int DEFAULT_CAPACITY = 10;

    int size;      // Number of elements in heap
    T [] elements; // The heap elements

    //创建默认大小的最小堆
    public MinHeap()
    {
        this( DEFAULT_CAPACITY );
    }

    /**
     * 创建指定大小的最小堆
     * @param capacity  最小堆的容量..
     */
    public MinHeap(int capacity )
    {
        size = 0;
        elements = (T[]) new Comparable[capacity + 1];
    }

    /**
     * 根据给定未排序数组构建堆
     */
    public MinHeap(T [ ] items )
    {
        size = items.length;
        elements = (T[]) new Comparable[ ( size + 2 ) * 11 / 10 ];

        int i = 1;
        for( T item : items )
            elements[ i++ ] = item;
        //此时堆未形成，逐个地对中间节点进行下滤
        buildHeap( );
    }

    /**
     * 维持堆序性质的同时进行插入
     * 使用标记，允许重复
     * @param x 待插入的元素
     */
    public void enQueue(T x){
        if( size == elements.length - 1 )
            enlargeArray( elements.length * 2 + 1 );

        int hole = size++;// 看作在最底新创建一个叶节点
        for(elements[0]=x;x.compareTo(elements[hole/2])<0; hole/=2)//第0号索引充当temp存放位置
            elements[hole] = elements[hole/2];//如果父节点优先级低，其值下滤到“空”的子节点
        elements[hole] = x;
    }

    /**
     * 扩容，同顺序表
     * @param newSize
     */
    private void enlargeArray(int newSize){
        T [] old = elements;
        elements = (T []) new Comparable[newSize];
        for( int i = 0; i < old.length; i++ )
            elements[i] = old[i];
    }

    /**
     * 找到最小元素，注意，第0位为temp空间
     * @return 返回最小元素，如果没有则返回空
     */
    public T findMin(){
        if(isEmpty())
            throw new NoSuchElementException();
        return elements[1];
    }

    /**
     * 删除最小元素/出队
     * @return the smallest item, or throw an NoSuchElementException if empty.
     */
    public T deQueue(){
        if(isEmpty()) throw new NoSuchElementException( );

        T pop = findMin();
        size--;
        elements[1] = elements[size];

        //此时堆序性质可能遭到破坏，对中间节点逐个进行下滤
        percolateDown(1);
        return pop;
    }

    /**
     * 将无序数组建堆
     * 线性时间复杂度
     */
    private void buildHeap( ){
        //size-2为最后一个中继节点
        for(int i=size/2;i>0;i--) percolateDown(i);
    }

    /**
     * 判断堆是否为空
     * @return 空为true，否则false
     */
    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * 使队列逻辑上为空
     */
    public void makeEmpty(){size = 0;}

    /**
     * 对元素进行下滤
     * @param hole 开始下滤的位置
     */
    private void percolateDown(int hole){
        int child;
        T temp = elements[hole];

        for(;hole*2 <= size;hole = child)
        {
            child = hole*2;
            if(child != size && elements[child + 1].compareTo(elements[child]) < 0)
                child++;
            if(elements[child].compareTo(temp) < 0){
                elements[hole] = elements[child];
            }else {
                break;
            }
        }
        elements[hole] = temp;
    }

    public T top(){return elements[1];}
}