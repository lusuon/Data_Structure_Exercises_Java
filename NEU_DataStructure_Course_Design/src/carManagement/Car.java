package carManagement;

public class Car {
    String number;
    int arrive;
    int leave;
    String where;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getArrive() {
        return arrive;
    }

    public void setArrive(int arrive) {
        this.arrive = arrive;
    }

    public int getLeave() {
        return leave;
    }

    public void setLeave(int leave) {
        this.leave = leave;
    }

    /**
     * Calculate the fee
     * @param fee
     * @return
     */
    public int calculate(int fee){
        return  fee*(leave-arrive);
    }

    @Override
    public String toString() {
        return (where.equals("park")?"The car is in the park":"The car is in the path")+"Location:";
    }
}
