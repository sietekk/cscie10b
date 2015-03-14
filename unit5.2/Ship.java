/**
 *  EXERCISE 5 - Ship.java
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

enum Engine {
        STEAM_ENGINE, STEAM_TURBINE,
        GAS_TURBINE, DIESEL, ELECTRIC,
        WIND, HUMAN_FORCE
}

public abstract class Ship
{
        // Private Data Fields
        private String name; // Name of ship
        private int year; // Year of the ship
        private Engine engine; // Engine of the ship

        // Constructor
        public Ship (String name, int year, Engine engine)
        {
                this.name = name;
                this.year = year;
                this.engine = engine;
        }

        // Getters
        public String getName()
        {
                return name;
        }

        public int getYear()
        {
                return year;
        }

        // Setters/Mutators
        public void setName(String name)
        {
                this.name = name;
        }

        public void setYear(int year)
        {
                this.year = year;
        }

        public void setEngine(Engine engine)
        {
                this.engine = engine;
        }

        // toString Method
        public String toString()
        {
                return "Ship data: 1) name: " + name + 
                         ", 2) year: " + year + 
                         ", and 3) engine: " + engine + ".";
        }
}

