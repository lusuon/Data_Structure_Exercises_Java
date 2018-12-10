import java.util.LinkedList;

public class Vertex {
    private int popularity ;
    private String name;
    private LinkedList<Edge> adj;//adj list
    private boolean known;//the status of the shortest path
    private Vertex path ;//the former of shortest path
    private int dist;


}
