/**
 *  EXERCISE 5 - Tanker.java
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

public class Tanker extends CargoShip
{
        // Private Data Fields
        private double gallons; // Gallons tanker holds

        // Constructor
        public Tanker(String name, int year, Engine engine, double tonnage, double gallons)
        {
                super(name, year, engine, tonnage);
                this.gallons = gallons;
        }

        // Getters
        public double getGallons()
        {
                return gallons;
        }

        // Setters/Mutators
        public void setGallons(double gallons)
        {
                this.gallons = gallons;
        }

        // toString Method
        public String toString()
        {
                return "Tanker data: 1) name: " + getName() +
                        ", and 2) gallons: " + gallons + ".";
        }
}
