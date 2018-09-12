import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class List_unique_and_merge_by_basic_operate {
    //取交集
    public static ArrayList<Integer> unique(ArrayList<Integer> l1, ArrayList<Integer> l2){
        ArrayList<Integer> l3 = new ArrayList<Integer>();
        System.out.println(l1);
        System.out.println(l2);
        int count = 0;
        int index = 0;
        for (int i = 0; i<l1.size() ;i++){
            int e1 = l1.get(i);
            for (int j = 0;j<l2.size() ; j++){
                int e2 = l2.get(j);
                //Optimize:reduce the loop
                if (e2>e1){
                    break;
                }
                count ++;
                if (e2 == e1){
                    l3.add(e1);
                }
            }
        }
        return l3;
}
    //取并集
    public static ArrayList<Integer> merge(ArrayList<Integer> l1, ArrayList<Integer> l2){
        System.out.println(l1);
        System.out.println(l1.size());
        System.out.println(l2);
        System.out.println(l2.size());
        int count = 0;
        int index = 0;
        for (int i = 0; i<l1.size() ;i++){
            int e1 = l1.get(i);
            for (int j = 0; j<l2.size() ; j++){
                int e2 = l2.get(j);
                //Optimize:reduce the loop
                if (e1<=e2){
                    l2.add(j,e1);
                    break;
                }
                count ++;
            }
        }
        System.out.println(l2.size());
        return l2;
    }

    public static  void main(String args[]){
        ArrayList<Integer> l1 = new ArrayList<Integer>();
        ArrayList<Integer> l2 = new ArrayList<Integer>();
        for(int i = 0;i<20; i+=2){
            l1.add(i);
        }
        for(int j = 1;j <= 25;j+=3){
            l2.add(j);
        }
        System.out.println(unique(l1,l2));
        System.out.println(merge(l1,l2));

    }
}
