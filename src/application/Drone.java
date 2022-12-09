package application;

/**
 * Drone class, this class is used to move the drone, and to create new drones. 
 */


public class Drone {
    private double dx, dy;
    double moveByA = 1;
    double moveByB = 0.5;
    private int droneID;
    public Direction.direction droneDirection;
    public String objectType;
    boolean damaged = false;
    public static int droneCount = 1;

    //drone constructor
    public Drone(int x, int y, Direction.direction f, boolean damaged, String type) {
        dx = x;
        dy = y;
        damaged = damaged;
        droneID = droneCount++;
        objectType = type;
        droneDirection = f;
    }

    public double getX() {
        return dx;
    }
    public double getY() {
        return dy;
    }

    public String toString() {
        return "Drone " + droneID + " at " + dx + "," + dy + " facing " + droneDirection;
    }

    /**
     * In the switch statement, I have different cases for different directions, after a case has been reached, I'll set the position dronex and droney
     * to go to that positions but before I do that I have to check if my drone can go there without colliding, if the answer is yes, then my drone position x and y get set
     * to the dronex and droney, but if I can't move there I'll call the change direction function which will move my drone to a diff direction, and the proces is repeated. 
     * 
     * 
     */
    public void tryToMove(DroneArena a) {
        double dronedx = 0, dronedy = 0;
        switch(droneDirection) {
        	case North:
        		dronedx = dx;
        		dronedy = dy - moveByA;
                break;
        	case NorthEast:
        		dronedx = dx + moveByA;
        		dronedy = dy + moveByA;
        		break;
        	case NorthWest:
        		dronedx = dx + moveByA;
        		dronedy = dy - moveByA;
                break;
        	case NorthWestNorth:
        		dronedx = dx - moveByB;
        		dronedy = dy + moveByA;
                break;
        	case NorthEastNorth:
        		dronedx = dx + moveByB;
        		dronedy = dy + moveByA;
        		break;
        	case East:
        		dronedx = dx + moveByA;
        		dronedy = dy;
                break;
        	case EastSouth:
        		dronedx = dx + moveByA;
        		dronedy = dy - moveByB;
                break;
        	case EastNorth:
        		dronedx = dx + moveByA;
        		dronedy = dy + moveByB;
                break;
        	case South:
        		dronedx = dx;
        		dronedy = dy - moveByA;
                break;
        	case SouthEast:
        		dronedx = dx - moveByA;
        		dronedy = dy + moveByA;
        		break;
        	case SouthWest:
        		dronedx = dx - moveByB;
        		dronedy = dy - moveByA;
        		break;
        	case SouthEastSouth:
        		dronedx = dx + moveByB;
        		dronedy = dy - moveByA;
        		break;
        	case SouthWestSouth:
        		dronedx = dx - moveByB;
        		dronedy = dy - moveByA;
                break;
        	case West:
        		dronedx = dx - moveByA;
        		dronedy = dy;
                break;
        	case WestNorth:
        		dronedx = dx - moveByA;
        		dronedy = dy + moveByB;
                break;
        	case WestSouth:
        		dronedx = dx - moveByA;
        		dronedy = dy - moveByB;
        		break;
        }
        if (a.canMoveHere(dronedx, dronedy)) {
            this.dx = dronedx;
            this.dy = dronedy;
        } else {
            droneDirection = droneDirection.changeDirection(droneDirection);
        }

    }
}