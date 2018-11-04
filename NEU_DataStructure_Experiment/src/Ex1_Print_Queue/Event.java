package Ex1_Print_Queue;

/**
 * Write the class Event, which represents a submitted printing event and should have the following 2 data members: "job j" and "int arrival_time".
 */
public class Event {
    private Job j;
    private int arrival_time;

    public Event(Job j, int arrival_time) {
        this.j = j;
        this.arrival_time = arrival_time;
    }

    public Job getJ() {
        return j;
    }

    public void setJ(Job j) {
        this.j = j;
    }

    public int getArrival_time() {
        return arrival_time;
    }

    public void setArrival_time(int arrival_time) {
        this.arrival_time = arrival_time;
    }

    public void printEvent(){
        System.out.println(this.j.getUser()+","+this.j.getNumber_of_pages()+","+this.arrival_time);
    }

}
