public class Edge implements Comparable<Edge>{
    private final double distance;
    private final int v;
    private final int w;

    public Edge(int v,int w,double distance){
        this.v = v;
        this.w = w;
        this.distance = distance;
    }

    public double getDistance(){
        return distance;
    }

    public int getV(){
        return v;
    }

    public int getW(){
        return w;
    }

    public int other(int vertex){
        if (vertex==v) return w;
        else if(vertex==w) return v;
        else throw new RuntimeException("Your vertex input is not in the edge.");
    }

    @Override
    public int compareTo(Edge another) {
        return (int)this.distance-(int)another.distance;
    }

    @Override
    public String toString() {
        return String.format("%d-%d %.2f",v,w,distance);
    }


}
