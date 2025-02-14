import java.awt.*;

public class Column {
    public String name;
    public Image pic;
    public int xpos; //coordinates
    public int ypos;
    public int height;
    public int width;
    public Rectangle hitbox;

    public Column() {
        hitbox = new Rectangle(xpos, ypos, width, height);

    }
    public Column (int paramXpos, int paramYpos, int paramWidth, int paramHeight){
        xpos = paramXpos;
        ypos = paramYpos;
        width = paramWidth;
        height = paramHeight;
        hitbox = new Rectangle(xpos, ypos, width, height);
    }

}
