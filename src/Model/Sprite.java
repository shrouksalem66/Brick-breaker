
package Model;

import image.Image;
import javax.swing.ImageIcon;

public abstract class Sprite {
    private boolean dead;
    protected int x;
    protected int y;
    protected int dx;
    protected int dy;
    public abstract void move();
    public Sprite(){
        this.dead =false;
    }
    public void die(){
        this.dead = true;
    }
    public void setX(int x){
        this.x = x;
    }
    public int getX(){
        return this.x;
    }
    
    public void setY(int y){
        this.y = y;
    }
    public int getY(){
        return this.y;
    }

    public int getDx() {
        return dx;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public int getDy() {
        return dy;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }
    
    public void setDead(boolean dead){
        this.dead = dead;
    }
    public boolean isDead(){
        return this.dead;
    }
    
    
}
