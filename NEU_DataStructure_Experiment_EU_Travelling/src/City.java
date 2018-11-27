import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Maintains information about a city.
 * This class can be define as a vertex.
 *
 */

public class City implements Comparable{
    private String name;
    private LinkedList<Service> adj;//adj list
    private boolean known;//the status of the shortest path
    private City path ;//the former of shortest path
    private int dist;

    public City(String n){
        this.name = n;
        this.adj = new LinkedList<Service>();
        this.known = false;
        this.path= null;
        this.dist= Integer.MAX_VALUE;
    }

    
    public int getFee(String cityName){
        for (Service s:adj) {
            if(s.getGoal().equals(name)) return s.getFee();
        }
        return -1;
    }

    public String getName() {
        return name;
    }

    public LinkedList<Service> getAdj() {
        return adj;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAdj(LinkedList<Service> adj) {
        this.adj = adj;
    }

    public boolean isKnown() {
        return known;
    }
    public void setKnown(boolean known) {
        this.known = known;
    }
    public City getPath() {
        return path;
    }
    public void setPath(City path) {
        this.path = path;
    }
    public int getDist() {
        return dist;
    }
    public void setDist(int dist) {
        this.dist = dist;
    }

    @Override
    public int compareTo(Object o) {
        City otherCity = (City) o;
        /*
        if (this.getDist()-otherCity.getDist()<0) return -1;
        else if(this.getDist()-otherCity.getDist()>0) return 1;
        else return 0;
        */
        return this.getDist()-otherCity.getDist();
    }

}
