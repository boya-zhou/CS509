package BL;

import DB.ReserveFlight;

import java.io.IOException;

public class Reserve{
    public static void reserve(Flight flight) throws IOException {
        ReserveFlight.lock();
        ReserveFlight.reserve();
        ReserveFlight.unlock();

    }
    public static void reset(Flight flight) throws IOException {
        ReserveFlight.lock();
        ReserveFlight.reset();
        ReserveFlight.unlock();

    }
}
