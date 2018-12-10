import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * A graph with EdgeWeighted
 */
public class Graph {
    private final int V;
    private int E;
    private LinkedList<Edge>[] adj;
    /**
     * 从外界txt读取图
     * @param file 文件路径
     */
    public Graph(String file){
        this.V = 0;
        this.E = 0;

    }

    /**
     * 图的顶点数
     * @return
     */
    public int V(){
        return V;
    }

    /**
     * 图的边数
     * @return
     */
    public int E(){
        return E;
    }

    /**
     * 向图添加边
     * @param edge
     */
    public void addEdge(Edge edge){

    }


}
