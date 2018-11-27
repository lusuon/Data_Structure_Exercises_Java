/**
 * models a rail service from the rail system. 
 * This class contains public data members for a destination city, a fee, and a distance. 
 */

public class Service {
    private City start;
    private City goal;
    private int fee;
    private int distance;

    public Service(City s,City g,int f, int d){
        this.start = s;
        this.distance = d;
        this.fee = f;
        this.goal = g;
    }



    public City getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = new City(goal);
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
