
package Model;

import constants.constants;
import image.Image;
import image.ImageFactory;
import javax.swing.ImageIcon;

public class Ball extends Sprite {
   private int width;
   private int height;
    public Ball(boolean ex ,int x,int y){
        this.width=width;
        this.height =height;
        initialize(ex,x,y);
    }
    @Override
    public void move() {
        x+=dx;
        y+=dy;
        if(x<0){dx=-dx;}                            //constants.PADDLE_WIDTH-50
        if(y<0){dy=-dy;}
        if(x>constants.BOARD_WIDTH-constants.BALL_WIDTH){dx=-dx;}       //x>= constants.BOARD_WIDTH-(constants.PADDLE_WIDTH+30)
    }

    private void initialize(boolean ex ,int x,int y) {
        if(ex){
        int start_x = constants.BALL_WIDTH/2+ 300;
        int start_y = constants.BALL__HIGHT/2+380;
        setX(start_x);
        setY(start_y);
        this.setDx(-2);
        this.setDy(-2);
        }else{
             setX(x);
        setY(y);
        this.setDx(-2);
        this.setDy(-2);
        }
        
    }
      public void dec_speed(){
        //decrease ball speed in x direction
        if(this.getDx()<0)
             this.setDx(-1);
        else if (this.getDx()>0) 
            this.setDx(1);
        
        //decrease ball speed in y direction
        if(this.getDy()<0)
             this.setDy(-1);
        else if (this.getDy()>0)
            this.setDy(1);
    }
    public void ball_move_like_gun(){
        this.setDx(0);
        this.setDy(-2);
    }

    public void ball_move_like_padddle(int x){
        this.setDx(x);
        this.setDy(0);
    }
    public void stick(int x,int y){
          this.setX(x);
          this.setY(y);
      }
    public void ball_move_right(){
        this.setDx(2);
        this.setDy(-2);
    }
}
