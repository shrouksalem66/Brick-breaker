
package Model;

import constants.constants;


public class Brick extends Sprite{
private boolean visible = true;
 private int TOTAL_BRICKS;
 private int num_hits;

    public int getNum_hits() {
        return num_hits;
    }

    public void setNum_hits(int num_hits) {
        this.num_hits = num_hits;
    }
 
public Brick (int x , int y ,int num_hits){
    set_TotalBricks(constants.TOTAL_BRICK);
    setX(x);
    setY(y);
    setDead(false);
    this.setNum_hits(num_hits);

}


    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
  public void move(boolean x,int direction) {
        if(x)
         this.x+=direction;
        else
         this.y+=direction;
    }
    @Override
    public void move() {
    }
     public void set_TotalBricks(int TOTAL_BRICKS) {
        this.TOTAL_BRICKS=TOTAL_BRICKS;
    }
    public int get_TotalBricks() {
        return TOTAL_BRICKS;
    }
}
