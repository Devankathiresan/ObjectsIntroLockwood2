//Game Example
//Lockwood Version 2023-24
// Learning goals:
// make an object class to go with this main class
// the object class should have a printInfo()
//input picture for background
//input picture for object and paint the object on the screen at a given point
//create move method for the object, and use it
// create a wrapping move method and a bouncing move method
//create a second object class
//give objects rectangles
//start interactions/collisions

//*******************************************************************************
//Import Section
//Add Java libraries needed for the game
//import java.awt.Canvas;

//Graphics Libraries

import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;


//*******************************************************************************
// Class Definition Section

public class GameLand implements Runnable {

    //Variable Declaration Section
    //Declare the variables used in the program
    //You can set their initial values here if you want

    //Sets the width and height of the program window
    final int WIDTH = 1000;
    final int HEIGHT = 700;

    //Declare the variables needed for the graphics
    public JFrame frame;
    public Canvas canvas;
    public JPanel panel;

    public BufferStrategy bufferStrategy;

    //Declare the objects used in the program below
    /** Step 0 declare object**/
    public Hero roary;
    public Hero enemy;
    public Hero detroitplayer;
    public Hero enemy2;

    /** Step 1 declare image for object **/
    public Image roaryPic;
    public Image enemyPic;
    public Image detroitplayerPic;
    public Image enemy2Pic;

    //declare background image
    public Image background;

    // Declare background image
    public boolean roaryIsIntersectingenemy= false;
    public boolean enemyIsIntersectingenemy2= false;
    public boolean roaryIsIntersectingdetroitplayer = false;



    // Main method definition: PSVM
    // This is the code that runs first and automatically
    public static void main(String[] args) {
       GameLand ex = new GameLand();   //creates a new instance of the game and tells GameLand() method to run
        new Thread(ex).start();       //creates a thread & starts up the code in the run( ) method
    }

    // Constructor Method
    // This has no return type and has the same name as the class
    // This section is the setup portion of the program
    // Initialize your variables and construct your program objects here.
    public GameLand() {
        setUpGraphics(); //this calls the setUpGraphics() method

        //create (construct) the objects needed for the game below
        /** Step 2 construct object **/
       roary = new Hero(200,300, 2,0,120,105);
       enemy = new Hero(100,400, 0, -1, 100, 100);
       detroitplayer = new Hero(150,350,1,2, 120,180);
       enemy2 = new Hero(50,350,1,2,120,180);



       /** Step 3 add image to object **/
       roaryPic = Toolkit.getDefaultToolkit().getImage("roary.png");
       enemyPic = Toolkit.getDefaultToolkit().getImage("enemy.png");
       background = Toolkit.getDefaultToolkit().getImage("background.jpeg");
       detroitplayerPic = Toolkit.getDefaultToolkit().getImage("simpson.png");
       enemy2Pic = Toolkit.getDefaultToolkit().getImage("Enemy2.png");


       roary.printInfo();
       enemy.printInfo();
       detroitplayer.printInfo();
       enemy2.printInfo();

        //for each object that has a picture, load in images as well


    }// GameLand()

//*******************************************************************************
//User Method Section
//
// put your code to do things here.

    // main thread
    // this is the code that plays the game after you set things up
    public void run() {
        //for the moment we will loop things forever using a while loop
        while (true) {
            moveThings();  //move all the game objects
            collisions(); // for intersecting characters
            render();  // paint the graphics
            pause(20); // sleep for 20 ms

        }
    }

    //paints things on the screen using bufferStrategy
    private void render() {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);
        //draw our background image
        g.drawImage(background, 0,0,WIDTH,HEIGHT, null);

        //draw the image of your objects below:
/** Step 4 draw object images **/
        g.drawImage(roaryPic, roary.xpos, roary.ypos, roary.width, roary.height, null);
        g.drawImage(enemyPic, enemy.xpos, enemy.ypos, enemy.width, enemy.height, null);
        g.drawImage(detroitplayerPic,detroitplayer.xpos, detroitplayer.ypos, detroitplayer.width, detroitplayer.height, null);
       g.drawImage(enemy2Pic,enemy2.xpos, enemy2.ypos, enemy2.width, enemy2.height, null );
        //dispose the images each time(this allows for the illusion of movement).

        g.dispose();

        bufferStrategy.show();
    }

    public void moveThings() {
        //call the move() method code from your object class
        roary.bouncingMove();
        enemy.wrappingMove();
        detroitplayer.bouncingMove();
        enemy2.wrappingMove();
    }

    public void collisions() {
        if (roary.rec.intersects(enemy.rec) && roaryIsIntersectingenemy == false) {
            roaryIsIntersectingenemy = true;
            roary.dx = -roary.dx;
            roary.dy = -roary.dy;
            enemy.dx = -enemy.dx;
            enemy.dy = -enemy.dy;
            System.out.println("ouch");
        }
        if (roary.rec.intersects(enemy.rec) == false) {
            roaryIsIntersectingenemy = false;
        }
        if (enemy.rec.intersects(enemy2.rec) && enemyIsIntersectingenemy2 == false) {
            enemyIsIntersectingenemy2 = true;
            enemy2.dx = -enemy2.dx;
            enemy2.dy = -enemy2.dy;
            enemy.dx = -enemy.dx;
            enemy.dy = -enemy.dy;
            System.out.println("Lets team up against the Lions");
        }
        if (enemy2.rec.intersects(enemy.rec) == false) {
            enemyIsIntersectingenemy2 = false;
        }
        if (roary.rec.intersects(detroitplayer.rec) && roaryIsIntersectingdetroitplayer == false) {
            roaryIsIntersectingdetroitplayer = true;
            roary.width = roary.width+10;
            roary.height = roary.height+10;
            detroitplayer.width = detroitplayer.width+10;
            detroitplayer.height= detroitplayer.height+10;
            System.out.println("Lets team up against the Division Rivals");
        }
        if (roary.rec.intersects(detroitplayer.rec) == false) {
            roaryIsIntersectingdetroitplayer = false;
        }

    }

    //Pauses or sleeps the computer for the amount specified in milliseconds
    public void pause(int time) {
        //sleep
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {

        }
    }

    //Graphics setup method
    private void setUpGraphics() {
        frame = new JFrame("Game Land");   //Create the program window or frame.  Names it.

        panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
        panel.setLayout(null);   //set the layout

        // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
        // and trap input events (Mouse and Keyboard events)
        canvas = new Canvas();
        canvas.setBounds(0, 0, WIDTH, HEIGHT);
        canvas.setIgnoreRepaint(true);

        panel.add(canvas);  // adds the canvas to the panel.

        // frame operations
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
        frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
        frame.setResizable(false);   //makes it so the frame cannot be resized
        frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!

        // sets up things so the screen displays images nicely.
        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        canvas.requestFocus();
        System.out.println("DONE graphic setup");
    }

}