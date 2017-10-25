package DB;

public class ReserveFlight {
    String xmlFlights;
    String lockDB="http://cs509.cs.wpi.edu:8181/CS509.server/ReservationSystem?team=404&action=lockDB\n";
    String resetDB="http://cs509.cs.wpi.edu:8181/CS509.server/ReservationSystem?team=404&action=resetDB";
    String ReserveFlight="http://cs509.cs.wpi.edu:8181/CS509.server/ReservationSystem?team=404&action=buyTickets&flightData"+xmlFlights;

    public static void lock(){


    }
    public static void reserve(){

    }
    public static void unlock(){

    }
}
