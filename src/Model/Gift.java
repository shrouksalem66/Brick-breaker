package Model;

import constants.constants;

public class Gift extends Sprite{
    public Gift(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    @Override
    public void move() {
        // 1 pixel in each iteration 
        this.y ++;
        
        //check if the gift at the bottom of the canvas
        if(y >= constants.BOARD_HIGHT - constants.GIFT_HIGHT){
            die();
        }
    }
    
    
}
