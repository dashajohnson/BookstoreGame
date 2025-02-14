



//Basic Game Application
// Basic Object, Image, Movement
// Threaded

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


    public class BasicGameApp implements Runnable {

        //Variable Definition Section
        //Declare the variables used in the program
        //You can set their initial values too

        Character cat1;
        Character capybara1;
        Character book1;
        Column column;
        Character owls;

        // below are the variables for the collisions

        boolean CatvsCapybara = false;
        boolean BookvsCapybara = false;
        boolean CatvsBook = false;
        boolean CatvsColumn = false;
        boolean CapybaravsColumn = false;
        boolean BookvsColumn = false;



        Image backgroundPic;



        //Sets the width and height of the program window
        final int WIDTH = 1000;
        final int HEIGHT = 700;

        //Declare the variables needed for the graphics
        public JFrame frame;
        public Canvas canvas;
        public JPanel panel;

        public BufferStrategy bufferStrategy;

        // Main method definition
        // This is the code that runs first and automatically
        public static void main(String[] args) {
            BasicGameApp ex = new BasicGameApp();   //creates a new instance of the game
            new Thread(ex).start();                 //creates a threads & starts up the code in the run( ) method
        }


        // This section is the setup portion of the program
        // Initialize your variables and construct your program objects here.
        public BasicGameApp() { // BasicGameApp constructor

            setUpGraphics();

            //variable and objects
            //create (construct) the objects needed for the game
            backgroundPic = Toolkit.getDefaultToolkit().getImage("bookstore-scene.jpg");

            cat1 = new Character(400,300,5,4,100,100);
            cat1.name = "luna";
            cat1.pic = Toolkit.getDefaultToolkit().getImage("luna-cat1.png");

            capybara1 = new Character(222,444,3,4,150,150);
            capybara1.name = "charles";
            capybara1.pic = Toolkit.getDefaultToolkit().getImage("charles-capybara1.png");

            book1 = new Character(200,300,4,2,50,50);
            book1.name = "book";
            book1.pic = Toolkit.getDefaultToolkit().getImage("book.png");

            column = new Column(30,0,85,650);

            owls = new Character(0,30,(int)(Math.random()*10+1),0,200,150);
            owls.name = "owls";
            owls.pic = Toolkit.getDefaultToolkit().getImage("owls.png");

        } // end BasicGameApp constructor


//*******************************************************************************
//User Method Section
//
// put your code to do things here.

        // main thread
        // this is the code that plays the game after you set things up

        public void run() {
            //for the moment we will loop things forever.
            while (true) {
                moveThings(); //move all the game objects
                collide();
                render();  // paint the graphics
                pause(10); // sleep for 10 ms

            }
        }

        public void moveThings() {
            //call the move() or wrap () code for each object

            cat1.move();

            capybara1.move();

            book1.move();

            owls.wrap();
        }

        public void collide() {

            if (cat1.hitbox.intersects(capybara1.hitbox) && !CatvsCapybara) {
                CatvsCapybara = true;
                capybara1.pic = Toolkit.getDefaultToolkit().getImage("scared-capybara.png");
                cat1.pic = Toolkit.getDefaultToolkit().getImage("luna-cat1.png");


            }
            if (!cat1.hitbox.intersects(capybara1.hitbox)) { // reset collision boolean as soon as they separate
                CatvsCapybara = false;
            }
            if (capybara1.hitbox.intersects(book1.hitbox) && !BookvsCapybara) {
                System.out.println("switching capybara image back to neutral");
                BookvsCapybara = true;
                capybara1.pic = Toolkit.getDefaultToolkit().getImage("charles-capybara1.png");
            }
            if (!book1.hitbox.intersects(capybara1.hitbox)) { // reset collision boolean as soon as they separate
                BookvsCapybara = false;
            }
            if (cat1.hitbox.intersects(book1.hitbox) && !CatvsBook) { // cat falls asleep after colliding w/ book
                CatvsBook = true;
                cat1.pic = Toolkit.getDefaultToolkit().getImage("cat-sleeping.png");
            }
            if (!cat1.hitbox.intersects(book1.hitbox)) {
                CatvsBook = false;
            }
            if (cat1.hitbox.intersects(column.hitbox)) {
                CatvsColumn = true;
                cat1.dx = -cat1.dx;
                cat1.dy = -cat1.dy;

            }
            if (capybara1.hitbox.intersects(column.hitbox)) {
                CapybaravsColumn = true;
                capybara1.dx = -capybara1.dx;
                capybara1.dy = -capybara1.dy;
            }
            if (book1.hitbox.intersects(column.hitbox)) {
                BookvsColumn = true;
                book1.dx = -book1.dx;
                book1.dy = -book1.dy;
            }
        }


        //Paints things on the screen using bufferStrategy
        private void render() {
            Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
            g.clearRect(0, 0, WIDTH, HEIGHT);

            g.drawImage(backgroundPic,0,0,WIDTH,HEIGHT,null);

            //draw the images

            g.drawImage(cat1.pic,cat1.xpos,cat1.ypos,cat1.width,cat1.height,null);
           // g.drawRect(cat1.hitbox.x,cat1.hitbox.y,cat1.hitbox.width,cat1.hitbox.height); // drawing the character in case we want to see the hitbox

            g.drawImage(capybara1.pic,capybara1.xpos,capybara1.ypos,capybara1.width,capybara1.height,null);
           // g.drawRect(capybara1.hitbox.x,capybara1.hitbox.y,capybara1.hitbox.width,capybara1.hitbox.height);

            g.drawImage(book1.pic,book1.xpos,book1.ypos,book1.width,book1.height,null);
            //g.drawRect(book1.hitbox.x,book1.hitbox.y,book1.hitbox.width,book1.hitbox.height);

            g.drawImage(owls.pic,owls.xpos,owls.ypos,owls.width,owls.height,null);

           // g.setColor(Color.pink);
           // g.drawRect(owls.owlbig.x,owls.owlbig.y,owls.owlbig.width,owls.owlbig.height);
           // g.setColor(Color.yellow);
           // g.drawRect(owls.owlsmall.x,owls.owlsmall.y,owls.owlsmall.width,owls.owlsmall.height);


            g.dispose();
            bufferStrategy.show();
        }

        //Pauses or sleeps the computer for the amount specified in milliseconds
        public void pause(int time ) {
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
            }
        }

        //Graphics setup method
        private void setUpGraphics() {
            frame = new JFrame("Application Template");   //Create the program window or frame.  Names it.

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


