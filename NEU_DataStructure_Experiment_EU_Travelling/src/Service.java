import java.util.ArrayList;
import java.util.HashMap;

/**
 * models a rail service from the rail system. 
 * This class contains public data members for a destination city, a fee, and a distance. 
 */

public class Service {
    String goal;
    int fee;
    int distance;

    public Service(String g,int f, int d){
        this.distance = d;
        this.fee = f;
        this.goal = g; 
    }
    
    
}
