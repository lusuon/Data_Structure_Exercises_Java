public class LessFist extends Simulator{



    public static void main(String args[]){
        Fifo fifo = new Fifo();
        try{
            fifo.simulate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
