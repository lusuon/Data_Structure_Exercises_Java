import java.io.*;
import java.util.*;

/**
 * models the rail system using an adjacency list representation. 
 */

public class RailSystem {
    private HashMap<String,City> cityList = new HashMap<>();   //图
    private ArrayList<City> cities = new ArrayList<>(); //
    public HashMap<String, City> getCityList() {
        return cityList;
    }
    public HashMap<String, City> load_services() throws IOException {
        String pathname = "services_change.txt";
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
                    cities.add(toBeAdd);
                }
                if (!added.contains(end)) {
                    added.add(end);
                    toBeAdd = new City(end);
                    cityList.put(end, toBeAdd);
                    cities.add(toBeAdd);
                }
            }
            Service s1 = new Service(cityList.get(end),fare,distance);
            Service s2 = new Service(cityList.get(start),fare,distance);
            cityList.get(start).getAdj().add(s1);
            cityList.get(end).getAdj().add(s2);
            line = br.readLine();
        }
            return cityList;
    }

    public void reset(HashMap<String,City> cityList){
        for (Map.Entry<String,City> entry:cityList.entrySet()){
            entry.getValue().setDist(Integer.MAX_VALUE);
            entry.getValue().setKnown(false);
            entry.getValue().setPath(null);
        }
    }

    public void OutputGraph(HashMap<String,City> cityList){
        for (Map.Entry<String,City> entry:cityList.entrySet()){
            System.out.println(entry.getKey()+":");
            for (Service s:entry.getValue().getAdj()) {
                System.out.println("\t"+String.format("to:%s,fee:%d,distance:%d",s.getGoal().getName(),s.getFee(),s.getDistance()));
            }
        }
    }

    public int[][] OutputMatrix(HashMap<String,City> cityList){
        //遍历邻接表，无法get到则输出32767
        //如何处理下表与地名的对应，考虑建立ArrayList，下标映射
        System.out.println("initalizing the matrix");

        // 初始化数组，全32767
        int[][] matrix = new int[cityList.size()][cityList.size()];
        for (int[] row:matrix) {
            for (int column:row) {
                column = 32767;
            }
        }


        //第一行：列名
        System.out.print("\t");

        for (City city:cities) {
            System.out.print(city.getName()+"\t");
        }
        System.out.println("");
        for (int i=0;i<cities.size();i++){
            City current = cities.get(i);
            for (Service s:current.getAdj()) {
                matrix[i][cities.indexOf(s.getGoal())] = s.getDistance();
            }
            System.out.print(current.getName()+"\t");
            for (int dist:matrix[i]) {
                System.out.print(dist+"\t");
            }
            System.out.println("");
        }
        return matrix;
    }


    public String recover_route(HashMap<String,City> cityList,String start,String end){
        int distance = 0;
        City startCity = cityList.get(start);
        City endCity = cityList.get(end);
        int fee = endCity.getDist();
        StringBuilder wholePath = new StringBuilder();
        if(endCity.getPath()==null) return "Can not reach.";

        Stack<String> pathStack = new Stack<>();
        while(endCity.getPath()!=null){
            City current = endCity;
            endCity = endCity.getPath();
            for(Service s:endCity.getAdj()){
                if(s.getGoal()==current){
                    distance+=s.getDistance();
                    pathStack.push(s.getGoal().getName());
                }
            }
        }
        pathStack.push(start);
        while(pathStack.size()!=0) {
            wholePath.append(pathStack.pop());
            if (pathStack.size()==0) {
                continue;
            } else {
                wholePath.append(" to ");
            }
        }
        return String.format("The smallest cost : %d \n length: %d \n path: %s",fee,distance,wholePath.toString());
    }
    public void calc_route(HashMap<String,City> cityList, String start){
        City startCity = cityList.get(start);
        if(start==null) throw new NoSuchElementException();
        PriorityQueue<City> notKnown = new PriorityQueue<>();
        startCity.setDist(0);
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
    }

    public static void main(String args[]) throws IOException {
        RailSystem rs = new RailSystem();
        HashMap<String,City> cityList = rs.load_services();
        rs.reset(cityList);

        rs.OutputMatrix(cityList);


        /*
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String start = null;
        String end = null;
        System.out.println("\nEnter the start:");
        start = br.readLine();
        System.out.println("Enter the goal:");
        end =  br.readLine();
        rs.calc_route(cityList,start);
        System.out.println(rs.recover_route(cityList,start,end));
        */
    }
}
    
