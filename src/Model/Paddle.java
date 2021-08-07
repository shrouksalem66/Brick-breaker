
package Model;
import constants.constants;
import image.Image;
import image.ImageFactory;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;


public class Paddle extends Sprite {
   private int width;
   private int height;
   boolean expands;
    public Paddle(int width , int height ,boolean ex ,int x,int y){
        this.width=width;
        this.height =height;
        intilaize(ex,x,y);
        
    }

    @Override
    public void move() {
        
        x+=dx;
        if(x<0)                                                        
            x=0;
        if(x>=constants.BOARD_WIDTH-(width)+10)          
            x=constants.BOARD_WIDTH-(width)+10;
    }
 
    public void intilaize(boolean ex ,int x,int y) {
        if(ex){
    int start_x = width/2 +250;
    int start_y = height/2+410;
    setX(start_x);
    setY(start_y);  
        }else{
        setX(x);
        setY(y); 
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        
        if(key==KeyEvent.VK_LEFT){
            dx=0;
        }
        if(key==KeyEvent.VK_RIGHT){
            dx= 0;
        }
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        
        if(key==KeyEvent.VK_LEFT){
            dx=-3;
        }
        if(key==KeyEvent.VK_RIGHT){
            dx= 3;
        }
        
    }
    
}
