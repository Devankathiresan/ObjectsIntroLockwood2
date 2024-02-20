import java.awt.*;

public class Hero {

    public int xpos;
    public int ypos;
    public int dx;
    public int dy;
    public int width;
    public int height;
    public boolean isAlive;
    public Rectangle rec;

    //constructor method
    public Hero(int pXpos, int pYpos, int pDx, int pDy, int pWidth, int pHeight){
        xpos=pXpos;
        ypos=pYpos;
        dx=pDx;
        dy=pDy;
        width=pWidth;
        height=pHeight;
        isAlive=true;
        rec=new Rectangle(xpos,ypos,width,height);
    }

    public void printInfo() {
        System.out.println("X position: "+ xpos);
        System.out.println("Y position: " +ypos);
        System.out.println("x speed: " + dx);
        System.out.println("y speed: " + dy);
        System.out.println("width: " + width);
        System.out.println("height:" + height);
        System.out.println("isAlive:" + isAlive);
    }

    public void move(){
//as an astronaut hits a wall, it shows up on the opposite wall.
        //donow: set this up for the other three walls
        xpos=xpos+dx;
        ypos=ypos+dy;

        rec=new Rectangle(xpos,ypos,width,height);

    }

    public void wrappingMove(){

        if(xpos>1000){
            xpos=1;
        }
        if(ypos<0){
            ypos=700;
        }
        if(xpos<0){
            xpos= 1000;
        }
        if(ypos>700){
            ypos=0;
        }
        xpos= xpos+dx;
        ypos=ypos+dy;
        rec=new Rectangle(xpos,ypos,width,height);

    }

    public void bouncingMove(){
        if(xpos>1000-width){
            dx=-dx;
        }
        if(xpos<0){
            dx=-dx;
        }

        if(ypos>700-height){
            dy=-dy;
        }
        if(ypos<0){
            dy=-dy;
        }
        //the two lines below actually tell the character to remove
        xpos=xpos+dx;
        ypos=ypos+dy;
        rec=new Rectangle(xpos,ypos,width,height);

        
    }


}
