import java.awt.*;

public class CapybaraCharacter {


        public String name;
        public Image pic;
        public int xpos; //coordinates
        public int ypos;
        public int dx; //represents speed
        public int dy;
        public int height;
        public int width;
        public boolean isAlive;
        public Rectangle hitbox;

        public CapybaraCharacter() {
            hitbox = new Rectangle(xpos, ypos, width, height);

        }

        public CapybaraCharacter(int paramXpos, int paramYpos, int paramDx, int paramDy, int paramWidth, int paramHeight){
            xpos = paramXpos;
            ypos = paramYpos;
            dx = paramDx;
            dy = paramDy;
            width = paramWidth;
            height = paramHeight;
            hitbox = new Rectangle(xpos, ypos, width, height);

        }
        public void move(){
            xpos = xpos + dx;
            ypos = ypos + dy;

            if (ypos >= 700 - height || ypos <= 0){
                dy = -dy;
                // bottom or top bounce
            }
            if (xpos >= 1000 - width || xpos <= 0){
                dx = -dx;
                //right or left bounce
            }
            hitbox = new Rectangle(xpos, ypos, width, height);

        }
        public void wrap(){
            xpos = xpos + dx;
            ypos = ypos + dy;

            if (xpos >= 1000){
                xpos = - width;

            }
            hitbox = new Rectangle(xpos, ypos, width, height);
        }


        public void printInfo(){
            System.out.println(name + " is at (" + xpos + "," + ypos + ")");

        }


    }





