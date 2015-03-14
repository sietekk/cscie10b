/**
 *  EXERCISE 5 - CargoShip.java
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

public class CargoShip extends Ship
{
        // Private Data Fields
        private double tonnage; // Cargo ship tonnage
        
        // Constructor
        public CargoShip(String name, int year, Engine engine, double tonnage)
        {
                super(name, year, engine);
                this.tonnage = tonnage;
        }

        // Getters
        public double getTonnage()
        {
                return tonnage;
        }

        // Setters/Mutators
        public void setTonnage(double tonnage)
        {
                this.tonnage = tonnage;
        }

        // toString Method
        public String toString()
        {
                return "Cargo ship data: 1) name: " + getName() + 
                        ", and 2) tonnage: " + tonnage + ".";
        }
}

