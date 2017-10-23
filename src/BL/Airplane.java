package BL;

public class Airplane {

    String model;
    String maunfacturer;
    int amount_firstclassSeat;
    int amount_coachSeat;

    public Airplane(String model, String maunfacturer, int amount_firstclassSeat, int amount_coachSeat) {
        this.model = model;
        this.maunfacturer = maunfacturer;
        this.amount_firstclassSeat = amount_firstclassSeat;
        this.amount_coachSeat = amount_coachSeat;
    }

    public static Airplane getAirplane(String model){
        Airplane airplane = new Airplane(/*fill up*/);
        return airplane;
    }

    public String toString() {

        return "[" + model + "||" + maunfacturer + "||" + amount_firstclassSeat + "||" + amount_coachSeat + "]\n";
    }
}
