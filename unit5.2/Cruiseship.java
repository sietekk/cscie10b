/**
 *  EXERCISE 5 - Cruiseship.java
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

public class Cruiseship extends Ship
{
        // Private Data Fields
        private int maxPassengers; // Maximum number of passengers
        private boolean norovirus; // Whether has norovirus

        // Constructor
        public Cruiseship (String name, int year, Engine engine, int maxPassengers, boolean norovirus)
        {
                super(name, year, engine);
                this.maxPassengers = maxPassengers;
                this.norovirus = norovirus;
        }

        // Getters
        public int getMaxPassengers()
        {
                return maxPassengers;
        }

        public boolean getNorovirus()
        {
                return norovirus;
        }

        // Setters/Mutators
        public void setMaxPassengers(int maxPassengers)
        {
                this.maxPassengers = maxPassengers;
        }

        public void setNorovirus(boolean norovirus)
        {
                this.norovirus = norovirus;
        }

        // toString Method
        public String toString()
        {
                return "Cruiseship data: 1) name: " + getName() + 
                        ", and 2) max passengers: " + maxPassengers + ".";
        }
}
