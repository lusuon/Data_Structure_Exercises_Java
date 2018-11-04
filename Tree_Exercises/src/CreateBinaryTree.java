public class CreateBinaryTree {
    public void createBinaryTree(Node current){
        //先序遍历
    }

    public int leaves(Node t){
        if(t==null) return 0 ;
        if(leaf(t)) return 1;
        leaves(t.left) + leaves(t.right);//unfinished
    }

    //variation——the length of path
    public int paths(Node t,int l){
        if(t==null) return 0 ;
        if(leaf(t)) return l;
        leaves(t.left,l++) + leaves(t.right,l++);//unfinished
    }

    //树为空、左右遍历等流程


    //非递归遍历——如何返回上一个节点？——用栈保存
    //自行维护一个栈实现中序遍历
    public void inOrder(Node t){
        Stack<Node> stack = new Stack<Node>();

    }

    public void inOrder(){

    }
}

