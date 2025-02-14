import org.w3c.dom.css.Rect;

import java.awt.*;

public class Character {

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
    public Rectangle owlbig; // used to make the complex hitbox for the owls
    public Rectangle owlsmall;

    public Character() {
        hitbox = new Rectangle(xpos, ypos, width, height);

    }

    public Character(int paramXpos, int paramYpos, int paramDx, int paramDy, int paramWidth, int paramHeight){
        xpos = paramXpos;
        ypos = paramYpos;
        dx = paramDx;
        dy = paramDy;
        width = paramWidth;
        height = paramHeight;
        hitbox = new Rectangle(xpos, ypos, width, height);
        owlbig = new Rectangle(xpos + width/3,ypos,(int)(width/2.3),(int)(height/1.1));
        owlsmall = new Rectangle(xpos + width/8,ypos + height/4,width/3, (int) (height/1.5));

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
        owlbig = new Rectangle(xpos + width/3,ypos,(int)(width/2.3),(int)(height/1.1));
        owlsmall = new Rectangle(xpos + width/8,ypos + height/4,width/3, (int) (height/1.5));

    }
    public void wrap(){
        xpos = xpos + dx;
        ypos = ypos + dy;

        if (xpos >= 1000){
            xpos = - width;

        }
        hitbox = new Rectangle(xpos, ypos, width, height);
        owlbig = new Rectangle(xpos + width/3,ypos,(int)(width/2.3),(int)(height/1.1));
        owlsmall = new Rectangle(xpos + width/8,ypos + height/4,width/3, (int) (height/1.5));
    }


    public void printInfo(){
        System.out.println(name + " is at (" + xpos + "," + ypos + ")");

    }


}



