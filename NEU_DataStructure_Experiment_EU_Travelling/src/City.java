import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * maintains information about a city
 */

public class City {
    String name;
    LinkedList<Service> services;//adj list
    boolean known = false;//the status of the shortest path
    City shorestPathSource = null;//the former of shorset path



    public City(String n){
        this.name = n;
        this.services = new LinkedList<Service>();
    }

    public void addService(String g,Integer f,Integer d){
        services.add(new Service(g,f,d));
    }

    public void removeService(Service s){
        services.remove(s);
    }


    public boolean getKnown(){
        return known;
    }

    public void setKnown(boolean known){
        this.known = known;
    }

    public City getShortesCity(){
        return shorestPathSource;
    }

    public void shorestPathSource(City shortestPathSource){
        this.shortestPathSource = shortestPathSource; 
    }



    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the services
     */
    public LinkedList<Service> getServices() {
        return services;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param services the services to set
     */
    public void setServices(LinkedList<Service> services) {
        this.services = services;
    }
    
}
