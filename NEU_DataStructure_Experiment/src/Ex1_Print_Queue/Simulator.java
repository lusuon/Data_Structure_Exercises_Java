package Ex1_Print_Queue;

import java.io.*;


/**
 * 从数据文件读取/处理一系列事件
 * 有效数据文件中每行含有其添加时间（精确到秒）、任务信息（页数、提交源电脑）
 * 在模拟的开始，每件事件应被程序读取，并在继承的队列内排序
 * 程序应该通过在for循环或while循环内递增计数器来模拟时间的流逝。初始化计数器为0，每秒递增1。
 * 当模拟的当前时间等于队首的打印作业的提交时间时，打印作业“到达”。发生这种情况时，从工作负载队列中弹出此事件并将其放在另一个Queue <event>对象中。
 * 另一个Queue <event>对象存储“到达”的打印作业。
 * 当程序模拟其他工作的打印时，这些工作在此队列中等待。因此，您可能希望将此对象命名为等待或类似的东西。
 */

/**
 * Write the class Simulator to simulate a networked printer.
 * It should have the following 2 data members: "int seconds_per_page" and "MinHeap<Event> workload".
 * seconds_per_page data member determines how long a print job takes to print.
 * This class should provide a member function "loadWorkLoad" to load the printing event data from corresponding files.
 */
public class Simulator {
    private int seconds_per_page; //determines how long a print job takes to print
    private Queue_NotPriority<Event> workload;
    int time = 0;
    int events = 0;
    int finish_count = -1;
    int latency = 0;

    /**
     * 从数据文件读取/处理一系列事件
     * 有效数据文件中每行含有其添加时间（精确到秒）、任务信息（页数、提交源电脑）
     * 在模拟的开始，每件事件应被程序读取，并在继承的队列内排序
     * 程序应该通过在for循环或while循环内递增计数器来模拟时间的流逝。初始化计数器为0，每秒递增1。
     * 当模拟的当前时间等于队首的打印作业的提交时间时，打印作业“到达”。发生这种情况时，从工作负载队列中弹出此事件并将其放在另一个Queue <event>对象中。另一个Queue <event>对象存储“到达”的打印作业。
     * 当程序模拟其他工作的打印时，这些工作在此队列中等待。因此，您可能希望将此对象命名为等待或类似的东西。
     */


    //load the printing event data from corresponding files
    public Queue_NotPriority loadWorkLoad() throws IOException {
        //Initialize the workload queue
        Queue_NotPriority<Event> workload = new Queue_NotPriority<>();

        //Input the filename
        System.out.println("Enter the filename you want to open.");
        BufferedReader fnbr = new BufferedReader(new InputStreamReader(System.in));
        File filename = new File(fnbr.readLine());
        InputStreamReader reader = new InputStreamReader(new FileInputStream(filename));
        BufferedReader br = new BufferedReader(reader);

        //Data enqueuing
        String line = "";
        line = br.readLine();
        while (line != null) {
            String [] infos = line.split(" ");
            String user = infos[2];
            int pages = Integer.parseInt(infos[1]);
            int arrival_time = Integer.parseInt(infos[0]);
            System.out.println("user:"+user);
            System.out.println("pages:"+Integer.toString(pages));
            System.out.println("arrival time:"+Integer.toString(arrival_time));
            Event event = new Event(new Job(user,pages),arrival_time);
            workload.enQueue(event);
            line = br.readLine();
        }
        //return workload;
        return workload;
    }
    public static void createTxt(String Filename,String content){
        try { // 防止文件建立或读取失败，用catch捕捉错误并打印，也可以throw
            /* 写入Txt文件 */
            File writename = new File(Filename+".txt"); // 相对路径，如果没有则要建立一个新的output。txt文件
            writename.createNewFile(); // 创建新文件
            BufferedWriter out = new BufferedWriter(new FileWriter(writename));
            out.write(content); //
            out.flush(); // 把缓存区内容压入文件
            out.close(); // 最后记得关闭文件
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
