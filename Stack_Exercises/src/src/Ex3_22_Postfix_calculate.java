import java.util.Stack;

public class Ex3_22_Postfix_calculate {
    public static void main(String args[]){
        String cal = "6523+8*+3+*";
        Stack<Double> stack = new Stack();
        double a,b;
        for (String s:
                cal.split("")) {
            try{
                stack.push(Double.parseDouble(s));
                System.out.println(stack);
            }catch (Exception e){
                System.out.println(s);
                System.out.println(stack);
                a = stack.pop();
                b = stack.pop();
                if (s.equals("*")){
                    stack.push(a*b);
                }else if(s.equals("+")){
                    stack.push(a+b);
                }else if(s.equals("-")){
                    stack.push(a-b);
                }else if(s.equals("/")){
                    stack.push(a/b);
                }
            }
        }
        System.out.println(stack.pop());
    }
}
