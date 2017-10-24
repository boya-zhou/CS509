package BL;

import DB.ReserveFlight;

public class Reserve {
    public void reserve(Flight flight){
        ReserveFlight.lock();
        ReserveFlight.reserve(null);
        ReserveFlight.unlock();
    }
}
