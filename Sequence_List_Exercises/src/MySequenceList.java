import javafx.beans.binding.ObjectExpression;

import java.lang.reflect.Array;
import java.util.*;

public class MySequenceList<Type> implements Iterable<Type> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private Type[] elements;
    private boolean start = true;

    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public MySequenceList() {
        size = 0;
        //Type[] elements = (Type[]) new Object[0];
        ensureCapacity(DEFAULT_CAPACITY);
    }

    public void ensureCapacity(int newCapacity) {
        if (newCapacity < size) return;

        Type[] oldList = elements;
        elements = (Type[]) new Object[newCapacity];//不允许直接创建泛型数组，只能强制类型转换
        for (int i = 0; i < getSize(); i++) {
            elements[i] = oldList[i];
        }
    }

    public void trimToSize() {
        ensureCapacity(size);
    }

    public Type get(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();

        return elements[index];
    }

    public int find(Type item){
        int result = -1;
        if(item == null){
            for (int i =0;i<size;i++){
                if (elements[i] == null) result = i;
            }
        }else{
            for (int i = 0;i<size;i++){
                if (item.equals(elements[i])) result = i;
            }
        }
        return result;
    }

    public void set(int index, Type item) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();

        elements[index] = item;
    }

    public void add(int index, Type item) {
        if (start||size == elements.length) {
            start = false;
            ensureCapacity(size * 2 + 1);
        };
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();

        for (int i = size; i > index; i--) elements[i] = elements[i - 1];
        elements[index] = item;

        size++;
    }

    public void add(Type item) {
        add(size, item);
    }

    public void addAll(Iterable<? extends Type>items){
        for (Type item:items){
            add(item);
        }
    }

    public void remove(int index) {
        for (int i = index; i < size -1; i++) elements[i] = elements[i + 1];
        size--;
    }

    public void remove_by_element(Type item){
        remove(find(item));
    }
    
    @Override
    public java.util.Iterator<Type> iterator() {
        return new MySequenceListIterator();
    }
    public java.util.ListIterator<Type> listIterator(){return new MySequenceListListIterator();}
    public java.util.Iterator<Type> reverseIterator(){
        return new MySequenceListReverseIterator();
    }


    private class MySequenceListIterator implements java.util.Iterator<Type> {
        private int currentIndex = 0;

        public boolean hasNext() {
            return currentIndex < size;
        }

        public Type next() {
            if (!hasNext()) throw new NoSuchElementException();

            return elements[currentIndex ++];
        }

        public void remove() {
            MySequenceList.this.remove(currentIndex);
        }
    }
    private class MySequenceListListIterator implements java.util.ListIterator<Type> {
        private int currentIndex = 0;
        public boolean hasNext() {
            return currentIndex < size;
        }
        public Type next() {
            if (!hasNext()) throw new NoSuchElementException();

            return elements[currentIndex ++];
        }
        @Override
        public boolean hasPrevious() {
            return elements[currentIndex--]!=null;
        }

        @Override
        public Type previous() {
            if (!hasPrevious()) throw new NoSuchElementException();
        
            return elements[currentIndex --];
        }

        @Override
        public int nextIndex() {
            if (!hasNext()) throw new NoSuchElementException();
            
            return currentIndex + 1;
        }

        @Override
        public int previousIndex() {
            if (!hasPrevious()) throw new NoSuchElementException();
            
            return currentIndex - 1;
        }

        public void remove() {
            MySequenceList.this.remove(currentIndex);
        }

        @Override
        public void set(Type type) {
            elements[currentIndex] =type;
        }

        @Override
        public void add(Type type) {
            if (start||size == elements.length) {
                start = false;
                ensureCapacity(size * 2 + 1);
            };
            if (currentIndex < 0 || currentIndex > size) throw new IndexOutOfBoundsException();
            for (int i = size; i > currentIndex; i--) elements[i] = elements[i - 1];
            elements[currentIndex] = type;

            size++;
        }
    }
    private class MySequenceListReverseIterator implements java.util.Iterator<Type>{
        private int currentIndex = size-1;
        @Override
        public boolean hasNext() {
            return currentIndex!=0;
        }

        @Override
        public Type next() {
            if (!hasNext()) throw new NoSuchElementException();
            return elements[currentIndex--];
        }
    }

    public static void main(String args[]){
        MySequenceList list = new MySequenceList();
        //插入元素
        for(int i = 1; i<=100; i++){
            list.add(i);
        }

        System.out.println("Add numbers from 1 to 100,and insert a big number at the second place");
        list.add(1,2134763);
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
        System.out.println("The example of addAll():");
        ArrayList<Integer> items=new ArrayList<Integer>();
        for (int i =1;i<6;i++){
            items.add(i);
        }
        list.addAll(items);
        System.out.println(Arrays.toString(list.elements));
        System.out.println("Testing the reverseIterator:");
        Iterator ritr = list.reverseIterator();
        while(ritr.hasNext()) System.out.println(ritr.next());
    }
}
