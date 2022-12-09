package application;


import java.util.Random;


// Direction class, similar to the Console version but with more directions. 
public class Direction {

    /**
     * The enum direction has 16 different directions, which means the drones are not contraint to go to just 4 directions. 
     * 
     */

    public enum direction {
        North,
        NorthEast,
        NorthEastNorth,
        NorthWestNorth,
        NorthWest,
        South,
        SouthEast,
        SouthEastSouth,
        SouthWest,
        SouthWestSouth,
        East,
        EastSouth,
        EastNorth,
        West,
    	WestSouth,
    	WestNorth;
    	

        // from the 16 directions, this functions select one random direction. 
    	
    	// follow the link on blackBoard to learn how this function works. 
        public static direction getRandomDirection() {
            Random random = new Random();
            return values()[random.nextInt(values().length)];
        }

        /**
         * 
         * This function is called whenever the drone collides with something, e.g. antoher drone or wall. When called the direction changes, and when the direction changes the tryToMove function in the drone class is called, 
         * which moves the drone to a new direction. 
         */

        public direction changeDirection(direction n) {
        	
        	// swithc for each direction
        	switch (n) {
        	case North:
        		return direction.NorthEastNorth;
        	case NorthEastNorth:
        		return direction.NorthEast;
        	case NorthEast:
        		return direction.EastNorth;
        	case NorthWest:
        		return NorthWestNorth;
        	case NorthWestNorth:
        		return North;
        	case East:
        		return direction.EastSouth;
        	case EastSouth:
        		return direction.SouthEast;
        	case EastNorth:
        		return direction.East;
        	case South:
        		return direction.SouthWestSouth;
        	case SouthWestSouth:
        		return direction.SouthWest;
        	case SouthEast:
        		return direction.South;
        	case SouthWest:
        		return direction.WestSouth;
        	case SouthEastSouth:
        		return direction.South;
        	case West:
        		return direction.WestNorth;
        	case WestSouth:
        		return direction.West;
        	case WestNorth:
        		return direction.NorthWest;
        	default:
        		return direction.North;
        	
        	}	
        	
       }
    }    
 }       
        

