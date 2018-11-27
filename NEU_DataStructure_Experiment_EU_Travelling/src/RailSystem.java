import java.io.*;
import java.util.*;

/**
 * models the rail system using an adjacency list representation. 
 */

public class RailSystem {
    private HashMap<String,City> cityList = new HashMap<>();   //图的顶点集
    public HashMap<String, City> getCityList() {
        return cityList;
    }



    public HashMap<String, City> load() throws IOException {
        String pathname = "services.txt";
        File filename = new File(pathname);
        InputStreamReader reader = new InputStreamReader(new FileInputStream(filename));
        BufferedReader br = new BufferedReader(reader);
        String line = "";
        int id = 0;
        ArrayList<String> added = new ArrayList<>();

        line = br.readLine();


        while (line != null) {
            String[] infos = line.split(" ");
            String start = infos[0];
            String end = infos[1];
            int fare = Integer.parseInt(infos[2]);
            int distance = Integer.parseInt(infos[3]);
            City toBeAdd = null;


            //如果无城市，则添加
            if (!added.contains(start)||!added.contains(end)) {
                if (!added.contains(start)) {
                    added.add(start);
                    toBeAdd = new City(start);
                    cityList.put(start, toBeAdd);
                }
                if (!added.contains(end)) {
                    added.add(end);
                    toBeAdd = new City(end);
                    cityList.put(end, toBeAdd);
                }
            }

            cityList.get(start).getAdj().add(new Service(cityList.get(start),cityList.get(end),fare,distance));
            line = br.readLine();
        }
            return cityList;
    }

    public String dijkstra(HashMap<String,City> cityList, String startCity, String endCity){
        City start = cityList.get(startCity);
        City end = cityList.get(endCity);
        int fee;
        int distance = 0;
        StringBuilder wholePath = new StringBuilder();
        if(start==null||end==null) throw new NoSuchElementException();
        PriorityQueue<City> notKnown = new PriorityQueue<>();

        start.setDist(0);
        //向堆加入所有节点
        for (Map.Entry<String,City> entry:cityList.entrySet()){
            notKnown.add(entry.getValue());
        }

        while(notKnown.size()!=0){
            City smallestDist = notKnown.poll();
            smallestDist.setKnown(true);
            for (Service service:smallestDist.getAdj()) {
                City adj = service.getGoal();
                if(!adj.isKnown()){
                    int newDist = smallestDist.getDist()+service.getFee();
                    if(newDist < adj.getDist()){
                        //更新adj的路径
                        adj.setDist(newDist);

                        //刷新adj在优先队列内的位置
                        notKnown.remove(adj);
                        notKnown.add(adj);

                        adj.setPath(smallestDist);
                    }
                }
            }
        }
        fee = end.getDist();

        Stack<String> pathStack = new Stack<>();
        while(end.getPath()!=null){
            City current = end;
            end = end.getPath();
            for(Service s:end.getAdj()){
                if(s.getGoal()==current){
                    distance+=s.getDistance();
                    pathStack.push(s.getGoal().getName());
                }
            }
        }
        pathStack.push(startCity);

        while(pathStack.size()!=0) {
            wholePath.append(pathStack.pop());
            if (pathStack.size()==0) {
                continue;
            } else {
                wholePath.append("->");
            }
        }
        return String.format("The smallest cost : %d \n length: %d \n path: %s",fee,distance,wholePath.toString());
    }

    public static void main(String args[]) throws IOException {
        RailSystem rs = new RailSystem();
        HashMap<String,City> cityList = rs.load();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("We support the following cities:\n");

        for (Map.Entry<String,City> entry:cityList.entrySet()){
            System.out.print(entry.getValue().getName()+",");
        }

        String start = null;
        String end = null;
        System.out.println("\nEnter the start point:");
        start = br.readLine();
        System.out.println("Enter the goal:");
        end = br.readLine();

        System.out.println(rs.dijkstra(cityList,start,end));
    }
}
    
