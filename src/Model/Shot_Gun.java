package Model;

import constants.constants;

public class Shot_Gun extends Sprite{

    public Shot_Gun(){
        
    }
    
    public Shot_Gun(int x, int y){
        this.x = x;
        this.y = y;
        initialize();
    }
    
     private void initialize() {
        setX(x + constants.PADDLE_WIDTH/2);
        setY(y);
    }
     
    @Override
    public void move() {
        //laser transformation from bottom to top
        this.y -= constants.SHOT_GUN_TRANSLATION;
        if(this.y < 0)
            this.die();
    } 
    
    
}
