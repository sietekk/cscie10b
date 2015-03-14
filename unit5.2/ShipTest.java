/**
 *  EXERCISE 5 - ShipTest.java
 *  -------------------------
 *  CSCI E-10b
 *  Unit 5.2
 *  Michael Conroy
 *  mconroy@g.harvard.edu
 *  sietekk@gmail.com
 *  -------------------------
 *  NOTES: 
 */

import java.util.*;

public class ShipTest
{
        public static void main (String [] args)
        {
                // Create Ship Array
                Ship [] shipArray = new Ship [6];
                // Define Ships
                shipArray[0] = new Cruiseship("Carnival", 2000, Engine.STEAM_ENGINE, 100, false);
                shipArray[1] = new Cruiseship("Royal Carribean", 1985, Engine.STEAM_TURBINE, 350, true);
                shipArray[2] = new CargoShip("USS Awesome", 1990, Engine.GAS_TURBINE, 1000.0);
                shipArray[3] = new CargoShip("USS Bob", 2010, Engine.DIESEL, 873.02);
                shipArray[4] = new Tanker("USS Moose", 2015, Engine.ELECTRIC, 250.3, 1500.5);
                shipArray[5] = new Tanker("USS Waffle", 1995, Engine.WIND, 3.14159, 3.14159);

                // Call Each Ship Object's toString() Method
                for (Ship ship : shipArray)
                {
                        System.out.println(ship.toString());
                }
        }
}

