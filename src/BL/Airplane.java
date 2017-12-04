package BL;

public class Airplane {

    String model;
    String maunfacturer;
    int amount_firstclassSeat;
    int amount_coachSeat;

    public Airplane() {
    	throw new UnsupportedOperationException();
    }

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

    public String getModel() {
        return model;
    }

    public String getMaunfacturer() {
        return maunfacturer;
    }

    public int getAmount_firstclassSeat() {
        return amount_firstclassSeat;
    }

    public int getAmount_coachSeat() {
        return amount_coachSeat;
    }
    
    public enum SeatType {
    	COACH,
    	FIRST_CLASS;
    	
    	@Override
    	public String toString() {
    		if(this == COACH) {
    			return "Coach";
    		} else if(this == FIRST_CLASS) {
    			return "FirstClass";
    		} else {
    			return null;
    		}
    	}
    }
}
