package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

/**
 * This class creates the window where the game is to be played, and the buttons to interact with the game. The styling is on a seperate css file @application.css
 */

import java.util.Scanner;





import javafx.application.Application;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class DroneInterface extends Application{
    private int CanvasWidth = 1200, CanvasHeight = 800;
    private ArenaCanvas arenaCanvas;
    private static DroneArena Arena;
//    Image droneback = new Image("arena.jpg");
    
    /*
     * The class AnimationTimer allows to create a timer, that 
     * is called in each frame while it is active. An extending 
     * class has to override the method handle(long) which will 
     * be called in every frame. The methods start() and stop() 
     * allow to start and stop the timer.
     * 
     * important for the Start and Stop functions. 
     * 
     */
    private static AnimationTimer animation;

    private void showMessage(String TStr, String CStr) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(TStr);
        alert.setHeaderText(null);
        alert.setContentText(CStr);
        alert.showAndWait();
    }


    private void viewAbout() {
        showMessage("About", "Extension of the console version developed by Muath Khalifa");
    }

 
    private void viewHelp() {
        showMessage("Help", "ADD -> to add a new drone. \nSTART -> to start moving the drones \nSTOP -> to stop moving the drones. \nSPEED -> to speed up the drones \nARENA -> for info about the arena");
    }

  

    private void viewCredits() {
        showMessage( "Credits", "Muath Khalifa 28014385");
    }
    
    
    //
    public static void listDrones(ListView<Drone> DroneGroup){
        //clear the list view
        DroneGroup.getItems().clear();
        
//        canvas.graphics.drawImage(droneBackGround, 0, 0, 100, 100);
        
        //checks over the drones in the list and adds them to the list view
        for (Drone drone : Arena.droneArray)
            DroneGroup.getItems().add(drone);
    }
    
    public void startAndStop() {
    	 //class called above, allows for the drones to stop and resume movement. 
        animation = new AnimationTimer() {
            public void handle(long x) {
                //will stop or start the moveAllDrones function
                Arena.moveAllDrones(arenaCanvas);

            }
        };
//        return animation;
    }
    
    /**
     * Function to set up the menu
     */
    public MenuBar setMenu() {
        MenuBar menuBar = new MenuBar();		// create menu
        
        
//        Menu 
        

        Menu information = new Menu("About");			// have entry for Info

        //About on the main tab, and files below are what about in the information section. 
        MenuItem about = new MenuItem("project");
        about.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent actionEvent) {
                viewAbout();				// show the about message
            }
        });
        MenuItem instructions = new MenuItem("instructions");
        instructions.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                viewHelp();				// show the help message
            }
        });
        MenuItem developer = new MenuItem("developer");
        developer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                viewCredits();				// show the credits message
            }
        });
        information.getItems().addAll(about, instructions, developer); 	// add submenu to Info

        // file menu to allow for saving and loading files. 
        
        
        Menu file = new Menu("File");				// create File Menu
        
        
        
        ExtensionFilter ex1 = new ExtensionFilter("Text Files", "*.TXT");
        ExtensionFilter ex2 = new ExtensionFilter("All Files", "*.*");
        String directory = "C:/Users/User/eclipse-workspace/Drone/src";
        MenuItem openFile = new MenuItem("Open");
        
        
        
        openFile.setOnAction(new EventHandler<ActionEvent>() {
        	
//        	System.out.println("Enter the name of the files you want to load? ");
//			fileName = scanner.nextLine();
//			fileName.substring(0, fileName.lastIndexOf('.'));
//
//			try {
//				BufferedReader reader = new BufferedReader(new FileReader(fileName + ".txt")); 
//				String line = reader.readLine();
//				
//				String[] loadArena = line.split(" ");
//				
//				
//				int ax = Integer.parseInt(loadArena[0]);
//				int ay = Integer.parseInt(loadArena[1]);
//				borderSign = loadArena[2].charAt(0);
//				myArena = new DroneArena(ax, ay);
//				
//				
//				
//				while((line = reader.readLine()) != null) {
//					String[] numbers = line.split(" ");
//					int x = Integer.parseInt(numbers[0]);
//					int y = Integer.parseInt(numbers[1]);
//					String droneDirection = numbers[2];
//					
//					myArena.dronesArray.add(new Drone(x, y, src.Directions.direction.valueOf(droneDirection)));
//					
//				}
//				reader.close();
        	
            public void handle(ActionEvent event) {		
                FileChooser fileChooser = new FileChooser();		
                
                fileChooser.getExtensionFilters().addAll(ex1, ex2); //filters for text file, because you can only open text files for Arena info. Good to avoid accidental errors. 
                fileChooser.setTitle("Open My Files");
                
                fileChooser.setInitialDirectory(new File(directory)); //starts of on this path. 
                
                File selectedFile = fileChooser.showOpenDialog(null); //select file to open
                
                
                if(selectedFile != null) { //if we do open a file, we'lle get notifed, and the location of the file will be displayed. 
                	
                	
                	System.out.println("Open File ");
                	 showMessage("File", "File Opened, path: " + selectedFile.getPath()); //shows arena stats
//                	System.out.println(selectedFile.getPath());
                	
                }else {
                	 showMessage("File", "You did not open a file"); //shows arena stats
                }
            }
        });
        
        MenuItem saveFile = new MenuItem("Save");
        
        saveFile.setOnAction(new EventHandler<ActionEvent>() {

				
			
            public void handle(ActionEvent event) {		
                FileChooser fileChooser = new FileChooser();		
                
                fileChooser.setTitle("Save My File");
                fileChooser.setInitialDirectory(new File(directory));
                fileChooser.getExtensionFilters().addAll(ex1, ex2);
                
                String arenInfo = "";
                
                for(Drone drone: Arena.droneArray) {
                	arenInfo += drone.getX() + " " + drone.getY() + " " + drone.droneDirection + "\n";
                }
                File selectedFile = fileChooser.showSaveDialog(null); //select file to save. 
                
//                selectedFile.canWrite()
                if(selectedFile != null) {
                	
                	try {
						PrintStream printS = new PrintStream(selectedFile);
						System.out.println("Save File ");
	     
	                	showMessage("File", "File Saved, path: " + selectedFile.getPath()); //shows arena stats
						printS.println(arenInfo);
						
						printS.flush();
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                	
                }else {
                	System.out.println("Open File ");
               	 showMessage("File", "You did not save any file"); 
                }
            }
        });
        //Quit
        MenuItem quit = new MenuItem("quit");
        quit.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {		
                System.exit(0);						
            }
        });
        file.getItems().addAll(quit, openFile, saveFile);	

        menuBar.getMenus().addAll(file, information);	// menu has File and Info

        return menuBar;					// return the menu, so can be added
    }

    
    

    /**
     * 
     * start function contains the required info to run the program.
     */
    public void start(Stage stage) throws Exception  {
    	stage.setTitle("Drone Simulator");


    	int width = 1200; 
    	int height = 700;
        Group root = new Group();
        Canvas canvas = new Canvas(width, height);
        root.getChildren().add(canvas);

        arenaCanvas = new ArenaCanvas(canvas.getGraphicsContext2D(), CanvasWidth, CanvasHeight);
        Arena = new DroneArena(width, height); // same size as the canvas, so the drones move accordingly to the arena img. 
        arenaCanvas.refresh(CanvasWidth, CanvasHeight); // The first refresh to set the image of the background. Function overloading is
        												//happening here because this is not the same refresh that gets called when the drones move. 
        
        
        startAndStop(); //to start and stop the drones. 

        
        
        /*
         * Below are buttons with their css styling and functionality. 
         */
        //position and id of each drone. 
        ListView<Drone> drones = new ListView<>();
        drones.setId("Drones");

        //vbList positioning and formatiing are determined here, with a title on top for the list.
  

        HBox hbList = new HBox(drones);
        hbList.setId("hbList"); //for css styling
        
        



        //formatted button to add a drone in a random place in the arena.
        Button addDrone = new Button("Add");
        addDrone.setId("addDrone");

        addDrone.setOnAction(e -> {
            //add a drone to the arena
            Arena.addDrone(arenaCanvas, drones);
        });
        
        Button readlDrone = new Button("REAL");
        
        readlDrone.setOnAction(e -> {
        	Arena.addReadDrone(arenaCanvas, drones);
        });

        //Determining button size


        
        

        //start button to start animation
        Button MoveDronesButton = new Button("START");
        
        
        


        //starts timer and calls the moveAllDrones method to move drones
//        MoveDronesButton.setOnAction(e -> time.start());
        MoveDronesButton.setOnAction(e -> animation.start());


        
        
        // Clear Arena
        
        Button clearArena = new Button("CLEAR");
        clearArena.setOnAction(e->{
        	
        	for(Drone obj: Arena.droneArray) {
        		Arena.droneArray.remove(obj);
        		Arena.numberOfDrones--;
        		arenaCanvas.refresh(CanvasWidth, CanvasHeight);
        	}
//        	Arena.droneArray.remove(Drone d);
        });
        

        //stop button to stop animation
        Button Stop = new Button("STOP");
        Stop.setId("hello");


        //stops timer and calls the moveAllDrones method to stop all drone movement
        Stop.setOnAction(e -> animation.stop());
        
        
        // Speed up drones button
        Button Speed = new Button("SPEED");
        Speed.setOnAction(e -> {
        	Arena.speedUpDrones();
        });
        
        Button Slow = new Button("SLOW");
        Slow.setOnAction(e->{
        	Arena.slowUpDrones();
        });
        
        
        
        
        // Speed up drones button
        Button addObstacle = new Button("Obstacle");
        addObstacle.setOnAction(e -> {
        	Arena.addObstacle(arenaCanvas, drones, "rock");
        });
        
        

        //opens a info box giving the user the details about the arena and number of drones inside
        Button ArenaInfo = new Button("Arena");
     

        ArenaInfo.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent actionEvent) {
                showMessage("Arena Size", Arena.toString()); //shows arena stats
            }
        });
        
        Button addCactus = new Button("CACTUS");
        
        addCactus.setOnAction(e -> {
        	Arena.addObstacle(arenaCanvas, drones, "cactus");
        });

        //sets padding and formatting for console
        HBox hbButtons = new HBox(20);
        hbButtons.setPadding(new Insets(0, 0, 150, 0));
        hbButtons.setId("buttons-container");
  

        //adds all button to the console
        hbButtons.getChildren().addAll(addDrone, readlDrone, MoveDronesButton, Stop,Speed, addObstacle,addCactus, Slow, clearArena, ArenaInfo);

        //creates borderpane

        BorderPane bp = new BorderPane(); // New borderpane to store all
        // features
        
        //formats borderpane inserting the aforementioned items in certain areas of screen
        bp.setTop(setMenu());
        bp.setCenter(root);
        bp.setBottom(hbButtons);
        bp.setRight(hbList);

        // Set the scene with the borderpane, which is bigger than the arena size
        Scene scene = new Scene(bp, 1500, 800);
        
        // link the css stylesheet. 
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());


        stage.setScene(scene); 
        stage.show();
    }

    	
    
    //main function to start the program. 
    public static void main(String[] args) {
        Application.launch(args);
    }
}