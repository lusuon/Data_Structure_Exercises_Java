import java.util.Stack;

public class Ex3_21_Valid_Symbol_Check {
    public static void main(String args[]){
        String s ="";
        Stack stack = new Stack();
        for (String symbol:s.split("")) {
            System.out.println(symbol);
            if(stack.empty()){
                System.out.println(symbol);
                System.out.println("was added in empty stadck.");
                stack.push(symbol);
            }else if(stack.peek().equals("(") && symbol.equals(")")){
                stack.pop();
            }else if(stack.peek().equals("{")  && symbol.equals("}") ){
                stack.pop();
            }else if(stack.peek().equals("[") && symbol.equals("]")){
                stack.pop();
            }else{
                stack.push(symbol);
                System.out.println("The element not match the top,pushed.");
            }
        }
        if(!stack.empty()){
            System.out.println(false);
        }else{
            System.out.println(true);
        }

    }
}
