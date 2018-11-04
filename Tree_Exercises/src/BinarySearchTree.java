import jdk.nashorn.api.tree.Tree;

import java.util.NoSuchElementException;
import java.util.Stack;

import static java.lang.StrictMath.max;

/**
 * 二叉查找树：树中的每个节点X，其左子树所有项小于X，右子树所有项大于X
 * 平均深度：O（logN）
 * 要求所有项可排序——实现Comparable接口，令两个项可使用compareTo方法比较；若返回0则相等
 * @param <T>
 */
public class BinarySearchTree<T extends Comparable<? super T>> {
    private static class Node<T>{
        T data;
        Node<T> left;
        Node<T> right;
        //拓展:可以多一个parent节点
        public Node(T data, Node<T> left, Node<T> right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

        public Node(T data) {
            this(data,null,null);
        }
    }

    private Node<T> root;

    public BinarySearchTree(){root = null;}

    public void clear(){root = null;}

    public boolean isEmpty(){return root == null;}

    //使用递归查找
    public boolean contain(T d,Node<T> t){
        if(t == null) return false;
        int result = d.compareTo(t.data);
        //尾递归，可用while代替
        if(result < 0){
            return contain(d,t.left);
        }else if(result > 0){
            return  contain(d,t.right);
        }else{
            return true;
        }
    }
    public boolean contain(T d){return contain(d,root);}

    //若有左子树则一直向左，使用递归实现
    public Node<T> findMin(Node<T> t){
        if(t == null){
            return null;
        }else if(t.left == null){
            return t;
        }
        return t.left;
    }
    public T findMin(){
        if(isEmpty()) throw new NoSuchElementException();
        return (T)findMin(root).data;
    }

    //若有右子树则一直向右，使用while循环实现
    public Node<T> findMax(Node<T> t){
        if(t !=null){
            while(t.right != null) t=t.right;
        }
        return t;
    }
    public T findMax(){
        if(isEmpty()) throw new NoSuchElementException();
        return findMax(root).data;
    }

    public Node<T> insert(T d,Node<T> t){
        if (t==null) return new Node<T>(d,null,null);

        int result = d.compareTo(t.data);

        if(result<0){
            t.left = insert(d,t.left);
        }else if(result>0){
            t.right = insert(d,t.right);
        }else{
            //在此暂不考虑相等的情况
        }
        return t;
    }
    public void insert(T d){root = insert(d,root);}
    public Node<T> remove(T d,Node<T> t){
        if(t==null) return t;

        int result = d.compareTo(t.data);

        if(result<0){
            t.left = remove(d,t.left);
        }else if(result>0){
            t.right = remove(d,t.right);
        }else if(t.left!=null && t.right!=null){
            //有两个子树的情形
            t.data = findMin(t.right).data;
            t.right = remove(t.data,t.right);
        }else{
            //
            t= (t.left!=null) ? t.left:t.right;
        }
        return t;
    }
    public void remove(T d){root = remove(d,root);}

    //中序遍历，复杂度O(N)
    public void printTree(Node<T> t){
        if(t != null){
            printTree(t.left);
            System.out.println(t.data);
            printTree(t.right);
        }
    }
    public void printTree(){
        if(isEmpty()){
            System.out.println("Empty");
        }else {
            printTree(root);
        }
    }

    //递归查找节点的父节点
    public Node<T> parent(Node<T> root,Node<T> child){
        if(root == null) return null;
        if(root.left == child || root.right == child) return root;
        Node<T> n =parent(root.left,child);
        if(n != null){
            return n;
        }else {
            return parent(root.right, child);
        }
    }

    public Node<T> parent(Node<T> child){
        return parent(root,child);
    }

    //对于普通二叉树，递归查找含指定数据的节点：
    public Node<T> find(Node<T> pointer,T data){
        if(pointer == null || data==null) return null;
        if(pointer.data == data) return pointer;
        Node<T> n = find(pointer.left,data);
        if(n != null){
            return n;
        }else{
            return find(pointer.right,data);
        }
    }

    public Node<T> find(T data){
        return find(root,data);
    }

    //preOrer print
    public void preOrder(Node<T> root){
        if(root != null){
            System.out.print(root.data);
            preOrder(root.left);
            preOrder(root.right);
        }
    }

    public void preOrder(){
        preOrder(root);
    }

    //InOrder print
    public void inOrder(Node<T> root){
        if(root != null){
            inOrder(root.left);
            System.out.print(root.data);
            inOrder(root.right);
        }
    }

    public void inOrder_noRecur(Node<T> c){
        Stack<Node> stack = new Stack<Node>();
        Node<T> p=root;

        while(p != null || !stack.isEmpty()){
            while(p !=null){
                stack.push(p);
                p= p.left;
            }
            if(!stack.isEmpty()){
                p=stack.peek();
                stack.pop();
                System.out.println(p.data);
                p=p.right;
            }
        }
    }

    public void inOrder(){
        inOrder(root);
    }

    //postOrder print
    public void postOrder(Node<T> root){
        if(root!=null){
            postOrder(root.left);
            postOrder(root.right);
            System.out.print(root.data);
        }
    }

    public void postOrder_noRecur(Node<T> root){

    }

    public void postOrder(){
        postOrder(root);
    }

    //求结点数：后序遍历
    private int size(Node<T> current){
        if(current == null) return 0;
        else{
            return 1+size(current.left)+size(current.right);
        }
    }

    public int size(){return size(root);}

    //高度
    private int height(Node<T> current){
        if(current == null) return -1;
        else{
            return 1+max(height(current.left),height(current.right));
        }
    }

    public int height(){return height(root);}

    public abstract class TreeIterator<Type>{
        private Node<T> root;
        private Node<T> c;
        public TreeIterator(BinarySearchTree<T> tree){
            root = tree.root;
            c = null;
        }
        public abstract void first();
        public abstract void next();

        public boolean isValid(){return c!=null;};
    }
    /*
    public class postIterator extends TreeIterator<T>{
        private Stack<StackNode<T>>  s =new Stack<StackNode<T>>();
        @Override
        public void first() {

        }

        @Override
        public void next() {
            if()
        }

        public class StackNode<T>{

        }
    }
    */
}
