import java.util.Stack;

public class Ex3_23_infix_to_postfix {
    public String trans (String input){
        String result ="";
        Stack stack = new Stack();

        for (String s : input.split("")){
            try{
                Integer.parseInt(s);
                result += s;
            }catch (Exception e){
                if(stack.peek() == null || priority(stack.peek()) > )
            }
        }
        return result;
    }
}
