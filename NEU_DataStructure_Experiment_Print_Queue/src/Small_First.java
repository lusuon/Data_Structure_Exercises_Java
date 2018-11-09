/**
 * Write a class Fifo which is a subclass of Simulator appropriately to model the following relationship: a fifo is a type of simulator.
 * Next, define a method "simulate" to implement the simulation as described above.
 * Your solution's output should match the output from the sample solutions.
 */

import java.io.*;

/**
 * 从数据文件读取/处理一系列事件
 * 有效数据文件中每行含有其添加时间（精确到秒）、任务信息（页数、提交源电脑）
 * 在模拟的开始，每件事件应被程序读取，并在继承的队列内排序
 * 程序应该通过在for循环或while循环内递增计数器来模拟时间的流逝。初始化计数器为0，每秒递增1。
 * 当模拟的当前时间等于队首的打印作业的提交时间时，打印作业“到达”。发生这种情况时，从工作负载队列中弹出此事件并将其放在另一个Queue <event>对象中。
 * 另一个Queue <event>对象存储“到达”的打印作业。当程序模拟其他工作的打印时，这些工作在此队列中等待。因此，您可能希望将此对象命名为等待或类似的名称.
 */

public class Small_First extends Simulator{

    public void simulate() throws IOException {
        //Queue_NotPriority<Event> workload = loadWorkLoad();
        MinHeap<Event> workload = loadWorkLoad_MinHeap();


        Queue_NotPriority<Event> arrived = new Queue_NotPriority<Event>();
        StringBuilder sb = new StringBuilder();
        int events = workload.size;


        Boolean init = true;
        //workload.printQueue();
        sb.append("Small first Simulation\r\n\r\n\t");

        while(!workload.isEmpty() || !arrived.isEmpty()){
            System.out.println("Current time:"+time);

            //
            Event currentEvent = workload.top();//待办任务

            while(workload.size!=0 && time >= currentEvent.getArrival_time()){
                System.out.println("Processing event,the user is:"+currentEvent.getJ().getUser());
                currentEvent.printEvent();


                //
                Event arrivedEvent = workload.deQueue();
                arrived.enQueue(arrivedEvent);

                //
                sb.append("Arriving: "+Integer.toString(arrivedEvent.getJ().getNumber_of_pages())+" page(s) from "+arrivedEvent.getJ().getUser()+" at "+ time +" seconds\r\n\t");
                latency-=time;
                if(init){
                    sb.append("Servicing: " + currentEvent.getJ().getNumber_of_pages() + " page(s) from " + currentEvent.getJ().getUser() + " at " + time + " seconds\r\n\t");
                    finish_count = currentEvent.getJ().getNumber_of_pages()*2;
                    init = false;
                    latency+=time;
                }

                //
                currentEvent = workload.top();

            }

            //每打印1P，耗费1时间，弹出该任务前，其他皆等待
            //弹出条件是？尝试倒数到0？正在处理的任务减去时间？
            if(finish_count == 0) {
                arrived.deQueue();
                Event processing = (Event) arrived.front.getPrev().getData();
                //在输出文件打印log
                // log is not define yet
                if (processing != null) {
                    sb.append("Servicing: " + processing.getJ().getNumber_of_pages() + " page(s) from " + processing.getJ().getUser() + " at " + time + " seconds\r\n\t");
                    latency+=time;
                    finish_count = processing.getJ().getNumber_of_pages()*2;
                }
            }
            time++;
            if (finish_count>0) finish_count--;
        }

        // the latency var are not define yet.
        sb.append("\r\n\tTotal jobs:"+Integer.toString(events)+"\r\n\t");
        sb.append("Aggregate latency:"+ Integer.toString(latency) +" seconds\r\n\t");
        sb.append("Mean latency:"+ Integer.toString(latency/events) +" seconds");
        createTxt("Small_first",sb.toString());
    }


    public static void main(String args[]){
        Small_First sm = new Small_First();
        try{
            sm.simulate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
