package application;

import javafx.scene.canvas.GraphicsContext;

/**
 * Drone Arena class is used to add new drones, check for collisions, and run functions upon collisions. 
 * It's very similar to the console version. 
 */


import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import application.*;

import java.util.ArrayList;
import java.util.Random;

public class DroneArena extends Direction {
    // Arena dimensions
    private int arenaWidth;
    private int arenaHeight;
    private int posX, posY;
    private ArenaCanvas canvas;
    Image droneImage = new Image("Drone.png");
    //public Drone d;
    public ArrayList<Drone> droneArray; /* Array list type Drone that will include list all the drones that are added
     by the user */
    public int numberOfDrones; // Drone counter for GUI when adding drones
    public GraphicsContext gc;
//    Image damagedDrone = new Image("brokenDrone.jpg");

    /*
     *  DroneArena constructor
     *  Initialises the drone arena by assigning it attributes like the x coordinate, y coordinate and random generation
     *  of coordinates for the drones.
     */
    
    public void addReadDrone(ArenaCanvas canvas, ListView<Drone> drones) {
		Random rand = new Random();

		
		// random position to drop the drone in. 
		int randomW = rand.nextInt(1, arenaWidth -100); // 

		int randomY = rand.nextInt(1, arenaHeight -100); 
		
		if (droneArray.size() >= 1) {
			
			if (!isHere(posX, posY)) {
				Direction.direction x = Direction.direction.getRandomDirection();
				droneArray.add(new Drone(randomW, randomY, x, false, "real-drone"));
				numberOfDrones++;
//				decider = false;
			}
		}else {
			Drone newDrone = new Drone(randomW, randomY, Direction.direction.getRandomDirection(), false, "real-drone"); // w&y set in this function, getRandom... sets a random dir from the enum in class Direction 
			droneArray.add(newDrone);
		}
		canvas.createDrone(this, droneImage);
		DroneInterface.listDrones(drones);
    }
    public DroneArena(int x, int y){
        arenaWidth = x;
        arenaHeight = y;
        droneArray = new ArrayList<Drone>();
    }
    
 
    public void speedUpDrones() {
    	for(Drone drone: droneArray) {
    		drone.moveByA *= 2;
    		drone.moveByA *= 2;
    	}
    }
    
    public void slowUpDrones() {
    	for(Drone drone: droneArray) {
    		drone.moveByA /= 2;
    		drone.moveByA /= 2;
    	}
    }
    


    /** Drones added to the arena list, which is also in turn added to the ListView method
     * in DroneInterface. Unlike the console version, the GUI will continue to add drones
     * until there is no more space within the canvas for any more drones to be added.
     */
    
    public void addObstacle(ArenaCanvas canvas, ListView<Drone> drones, String obs) {
    	Random rand = new Random();
		int randomW = rand.nextInt(1, arenaWidth -100); // 
		int randomY = rand.nextInt(1, arenaHeight -100); 
		
		if(obs == "rock") {
			Drone newDrone = new Drone(randomW, randomY, Direction.direction.getRandomDirection(), false, "rock"); 
			droneArray.add(newDrone);

			// w&y set in this function, getRandom... sets a random dir from the enum in class Direction 
		}else if (obs == "cactus") {
			Drone newDrone = new Drone(randomW, randomY, Direction.direction.getRandomDirection(), false, "cactus"); 
			droneArray.add(newDrone);

		}
        canvas.refresh(this, droneImage);

//		canvas.createDrone(this, droneImage);
//		DroneInterface.listDrones(drones);
    }
	
	public void addDrone(ArenaCanvas canvas, ListView<Drone> drones) {
		
		//randomly generates direction and x and y based on arena
		// and
		boolean decider = true;
		Random rand = new Random();

		
		// random position to drop the drone in. 
		int randomW = rand.nextInt(1, arenaWidth -100); // 

		int randomY = rand.nextInt(1, arenaHeight -100); 
		
		if (droneArray.size() >= 1) {
			
			if (!isHere(posX, posY)) {
				Direction.direction x = Direction.direction.getRandomDirection();
				droneArray.add(new Drone(randomW, randomY, x, false, "drone"));
				numberOfDrones++;
				decider = false;
			}
		}else {
			Drone newDrone = new Drone(randomW, randomY, Direction.direction.getRandomDirection(), false, "drone"); // w&y set in this function, getRandom... sets a random dir from the enum in class Direction 
			droneArray.add(newDrone);
		}
		canvas.createDrone(this, droneImage);
		DroneInterface.listDrones(drones);
	}
	
    public void moveAllDrones(ArenaCanvas canvas){
        for(Drone drone: droneArray){
        	if(drone.objectType== "drone" || drone.objectType == "real-drone") {
        		drone.tryToMove(this) ; // explanation of tryToMove funciton in the Drone.java file.         
        	}
        }
        canvas.refresh(this, droneImage);
    }
    
    

    /* Checks if drone can move to given coordinates and checks if drone position might be out
   be out of arena boundaries */
    public boolean canMoveHere(double x, double y){
        return !isHere(x, y) && x > 0 && y > 0 && x < arenaWidth - 100 && y < arenaHeight - 100;
    }

    /**
     * Loops over the existence drones, and if two drones are in the same positions, a collision happens, a and a function called takeHealth is activated. 
     * 
     */

    public boolean isHere (double x, double y) {
    	
    	
   
        for(int i = 0; i < droneArray.size() ; i++) {
        	
        	if(droneArray.size() >= 2) {
	            if(x == droneArray.get(i).getX() && x > 0 && y > 0){
	            	System.out.println("X: " + x + " DX: " + droneArray.get(i).getX());
	//            	canvas.gc.drawImage(droneImage, d.getX(), d.getY(), 100, 100);
	            	droneArray.get(i).damaged = true;
	                return true;
	            }
	            else if(y == droneArray.get(i).getY() && x > 0 && y > 0){
	            	System.out.println("Y: " + x + " DY: " + droneArray.get(i).getY());
	            	droneArray.get(i).damaged = true;
	                return true;
	            } else {
	                continue;
	            }
        	}
        }
        return false;

    }

    // Returns the information on the arena dimensions and the number of drones in the arena
    public String toString(){
        return "Arena Width: " + arenaWidth + " * ArenaHeight: " + arenaHeight + ".\n" +
                "Drone count: " + numberOfDrones + ".";
    }



}