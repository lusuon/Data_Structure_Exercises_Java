import java.io.*;
import java.util.*;

/**
 * models the rail system using an adjacency list representation. 
 */

public class RailSystem {
    private HashMap<String,City> cityList = new HashMap<>();   //图的顶点集
    private HashMap<City, List<Service>> ver_edgeList_map = new HashMap<>();  //图的每个顶点对应的有向边
    public RailSystem() throws IOException {
        super();
        ArrayList<Map> r = load();
        this.cityList = (HashMap<String,City>)r.get(0);
        this.ver_edgeList_map = (HashMap<City, List<Service>>)r.get(1);
    }
    public HashMap<String, City> getCityList() {
        return cityList;
    }
    public void setCityList(HashMap<String,City> cityList) {
        this.cityList = cityList;
    }
    public HashMap<City, List<Service>> getVer_edgeList_map() {
        return ver_edgeList_map;
    }
    public void setVer_edgeList_map(HashMap<City, List<Service>> ver_edgeList_map) {
        this.ver_edgeList_map = ver_edgeList_map;
    }



    public ArrayList<Map> load() throws IOException {
        ArrayList<Map> loadResult = new ArrayList<>();
        //用城市作为节点表
        //ArrayList<String> added = new ArrayList<>();
        String pathname = "services.txt";
        File filename = new File(pathname);
        InputStreamReader reader = new InputStreamReader(new FileInputStream(filename));
        BufferedReader br = new BufferedReader(reader);
        String line = "";
        int id = 0;
        ArrayList<String> names = new ArrayList<>();

        line = br.readLine();


        while (line != null) {
            String[] infos = line.split(" ");
            String start = infos[0];
            String end = infos[1];
            int fare = Integer.parseInt(infos[2]);
            int distance = Integer.parseInt(infos[3]);
            City toBeAdd = null;


            //如果无城市，则添加
            if (!names.contains(start)||!names.contains(end)) {
                if (!names.contains(start)) {
                    names.add(start);
                    toBeAdd = new City(start);
                    cityList.put(start, toBeAdd);
                    ver_edgeList_map.put(toBeAdd, new LinkedList<Service>());
                }
                if (!names.contains(end)) {
                    names.add(end);
                    toBeAdd = new City(end);
                    cityList.put(end, toBeAdd);
                    ver_edgeList_map.put(toBeAdd, new LinkedList<Service>());
                }
            }

            ver_edgeList_map.get(cityList.get(start)).add(new Service(cityList.get(start),cityList.get(end),fare,distance));
            line = br.readLine();
        }
            loadResult.add(0,cityList);
            loadResult.add(1,ver_edgeList_map);
            return loadResult;
    }

    /**
     *
     * @param startIndex dijkstra遍历的起点节点下标
     * @param destIndex dijkstra遍历的终点节点下标
     */
    public void dijkstraTravasal(String startName,String destName)	{
        City start = cityList.get(startName);
        City dest = cityList.get(destName);
        String path = "["+dest.getName()+"]";
        int path_distance = 0;
        start.setPath(null);
        start.setDist(0);
        updateChildren(start);
        int shortest_length = dest.getDist();
        while((dest.getPath()!=null)&&(!dest.equals(start)))	{
            path = "["+dest.getPath().getName()+"] --> "+path;
            dest = dest.getPath();
        }
        System.out.println("["+cityList.get(startName).getName() +"] to ["+cityList.get(destName).getName()+"] dijkstra shortest path :: "+path);
        System.out.println("shortest length::"+shortest_length);
    }

    /**
     *  从初始节点开始递归更新邻接表
     *  @param v
     *  */
    private void updateChildren(City v)	{
        if (v==null) {
            System.out.println("v is null,update aborted");
            return;
        }
        if (ver_edgeList_map.get(v)==null||ver_edgeList_map.get(v).size()==0) {
            if(ver_edgeList_map.get(v)==null){
                System.out.println("Can not find the corresponding linkedlist");
            }else{
                System.out.println("Empty");
            }
            return;
        }

        System.out.println("start updating");
        List<City> childrenList = new LinkedList<City>();
        for(Service e:ver_edgeList_map.get(v))	{
            City childCity = e.getGoal();
            int nowDist = v.getDist()+e.getFee();
            //如果子节点之前未知，则把当前子节点假如更新列表
            if(!childCity.isKnown())	{
                childCity.setKnown(true);
                childCity.setDist(nowDist);
                childCity.setPath(v);
                childrenList.add(childCity);
            }
            //子节点之前已知，则比较子节点的ajduDist&&nowDist
            if(nowDist>=childCity.getDist()){
                continue;
            }else{
                childCity.setDist(nowDist);
                childCity.setPath(v);
            }
        }
        //更新每一个子节点
        for(City vc:childrenList)	{
            updateChildren(vc);
        }
    }

    public static void main(String args[]) throws IOException {
        RailSystem rs = new RailSystem();
        rs.dijkstraTravasal("Lisbon","Paris");


    }
}
    
