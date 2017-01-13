/*
* Alik-Serguy Alphonsovich Rukubayihunga
* CS151-Object-Oriented Design
* MoWe 9:00-10:15 Fall 2014
*/

package airlinereservationsystem;

import java.io.IOException;


/**
 * This program tests the reservation system.
 */
public class ReservationSystem
{
    public static void main(String[] args) throws IOException
    {
        ReadWriteFile theFile = new ReadWriteFile(args[0]);
        
        FlightClasses theFlight = new FlightClasses();
        theFlight.createFlightClasses();
        
        theFile.readFile(theFlight);
        
        Menu theMenu = new Menu();
        theMenu.display(theFlight);
        
        theFile.writeFile(theFlight);
    }
}
