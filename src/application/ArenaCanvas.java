package application;

import javafx.application.Application;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Canvas class. Has the image for the Arena and drones, and displays the appropriate drone accordingly. 
 */



public class ArenaCanvas extends Application {

    int canvasWidth; 
    int canvasHeight; //height and width used to set Arena size approriatly. 
    //Images loaded in at the start
    Image brokenDrone = new Image("brokenDrone.jpg"); //Image class is used for loading iamges for a specified URL. In this instance, our images are saved in the same file. 
    Image rock = new Image("rock.png");
    Image realDrone = new Image("realDrone.png");
    Image cactusImg = new Image("cactus.png");
    Image background = new Image("white.jpg");
    GraphicsContext graphics;


    /**
     * constructor for canvas
     */
    public ArenaCanvas(GraphicsContext graphicContext, int x, int y) {
        //Sets up the canvas
    	graphics = graphicContext;
        canvasWidth = x;
        canvasHeight = y;
    }


    /**
     * the refresh function updates the images of the back ground and drone/s position to match the new position of the drone/s. Essentially refreshing the screen to match the new states.  
     */
    public void refresh(DroneArena Arena, Image droneImage){
    	graphics.clearRect(0, 0, canvasWidth, canvasHeight);	
        graphics.drawImage(background, 0, 0, canvasWidth, canvasHeight);
        createDrone(Arena, droneImage);

    }
    
    public void refresh(int CanvasWidth, int CanvasHeight){
    	
//        setFillArenaColour(canvasWidth, canvasHeight);
        graphics.drawImage(background, 0, 0, CanvasWidth, CanvasHeight);

    }

    /**
     * Loop over the numDrone array filled with drone and display image, everytime the drones move, this function is called throguh refresh. 
     * 
     */
    public void createDrone(DroneArena Arena, Image droneImage) {
        //loops through drone array multidrone
        for (Drone drone : Arena.droneArray) {
        	
        	if(drone.damaged && drone.objectType == "drone") {
        		graphics.drawImage(brokenDrone, drone.getX(), drone.getY(), 100, 100);
        		
        		
        		
        	}else if(drone.objectType == "rock") {
        		graphics.drawImage(rock, drone.getX(), drone.getY(), 100, 100);
        	}else if(drone.objectType == "real-drone"){
        		graphics.drawImage(realDrone, drone.getX(), drone.getY(), 100, 100);

        	}else if(drone.objectType == "cactus") {
        		graphics.drawImage(cactusImg, drone.getX(), drone.getY(), 100, 100);

        	}
        	
        	else {
        		double X = drone.getX();
        		double Y = drone.getY();
        		// 100 is the size of the image, droneImage is the image file, and X & Y are the drone positions. 
        		graphics.drawImage(droneImage, X, Y, 100, 100);
        	}
        }

    }


    //automatic function to avoid errors. 
	@Override
	public void start(Stage arg0) throws Exception {}


}