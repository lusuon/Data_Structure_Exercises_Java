import java.util.NoSuchElementException;

public class Ex3_24_Two_stack_in_one_array {
    int [] twoStackContainer = new int[10];
    int stack_pointer_A = 0;
    int stack_pointer_B = twoStackContainer.length-1;
    boolean Aempty = true;
    boolean Bempty = true;
    public void pushA(int d){
        //边界条件
        if(stack_pointer_A == stack_pointer_B){
            throw new StackOverflowError();
        }else{
            twoStackContainer[stack_pointer_A] = d;
            stack_pointer_A ++;
            Aempty = false;
        }
    }
    public void pushB(int d){
        if(stack_pointer_A == stack_pointer_B){
            throw new StackOverflowError();
        }else{
            twoStackContainer[stack_pointer_B] = d;
            stack_pointer_B --;
            Bempty = false;
        }

    }
    public int popA(){
        int element;
        if(!Aempty){
            element = twoStackContainer[stack_pointer_A];
            //twoStackContainer[stack_pointer_A] = ;
            //不对“弹出”位置的元素作处理，等待覆盖修改即可
            stack_pointer_A --;
        }else{
            throw new NoSuchElementException();
        }
        if(stack_pointer_A < 0){
            stack_pointer_A =0;
            Aempty = true;
        }
        return element;
    }
    public int popB(){
        int element;
        if(!Bempty){
            element = twoStackContainer[stack_pointer_B];
            //twoStackContainer[stack_pointer_B] = ;
            //不对“弹出”位置的元素作处理，等待覆盖修改即可
            stack_pointer_B --;
        }else{
            throw new NoSuchElementException();
        }
        if(stack_pointer_B < 0){
            stack_pointer_B =0;
            Bempty = true;
        }
        return element;
    }




}
