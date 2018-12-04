package carManagement;

import carManagement.Car;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Parking {
    private Stack<Car> park;
    private Queue<Car> waiting;
    private static int INIT_PARK_SPACE = 10;
    private int park_space;

    public Parking(){
        park = new Stack<Car>();
        waiting = new LinkedList<Car>();
        park_space = INIT_PARK_SPACE;
    }

    public Parking(int space){
        super();
        park_space = space;
    }

    public void in(){
        if(park.size()>=park_space){

        }
    }

    public void out(){
        
    }
}
