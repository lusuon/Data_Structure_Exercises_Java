import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * models the rail system using an adjacency list representation. 
 */

public class RailSystem {
    public HashMap<String, City> load() throws IOException {
        //用城市作为节点表
        HashMap<String, City> cities = new HashMap<String, City>();

        String pathname = "services.txt";
        File filename = new File(pathname);
        InputStreamReader reader = new InputStreamReader(new FileInputStream(filename)); // 建立一个输入流对象reader
        BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
        String line = "";
        line = br.readLine();


        while (line != null) {
            String[] infos = line.split(" ");
            String start = infos[0];
            String end = infos[1];
            int fare = Integer.parseInt(infos[2]);
            int distance = Integer.parseInt(infos[3]);

            //如果无城市，则添加
            if (cities.get(start) == null) {
                cities.put(start, new City(start));
            }

            City startCity = cities.get(start);
            startCity.addService(end, fare, distance);

            line = br.readLine();
        }
        return cities;
    }

    public void dijkstra(String cityName){
        Cities cities = load();
        int[] distance = new int[cities.size];
        City[] path = new City[cities.size]; 
        City start = cities.getValue(cityName);


        //初始化距离数组，距离更新为当前可达的，不可达则设为-1
        //初始化路径数组，全为起始点
        

    }


    public static void main(String args[]) throws IOException {

        RailSystem rs = new RailSystem();
        HashMap cities = rs.load();
        Iterator iter = cities.entrySet().iterator();

        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            String name = (String) entry.getKey();
            City city = (City) entry.getValue();

        }



    }
}
