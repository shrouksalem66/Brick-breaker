
package ui;

import Model.Ball;
import Model.Brick;
import Model.Gift;
import Model.Paddle;
import Model.Shot_Gun;
import constants.constants;
import image.Image;
import image.ImageFactory;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.Timer;

public class LevelTwo extends Levels{
public int existingBox[][];
 ImageIcon Brickimage2;
  ArrayList<Gift> red_gifts =new ArrayList();
   ArrayList<Gift> green_gifts =new ArrayList();
     boolean catchRedGift=false;
     int NumShots=0;
    boolean catchGreenGift=false;
    boolean shoot=false;
    Paddle paddle_exp;
    @Override
    void intializevariables() {
        
         this.scoreImage =ImageFactory.createImage(Image.SCORE);
        this.lifeImage =ImageFactory.createImage(Image.LIFE);
        this.shotsImage =ImageFactory.createImage(Image.SHOTS);
         this.winImage =ImageFactory.createImage(Image.WIN);
         this.gameoverImage =ImageFactory.createImage(Image.GAMEOVER);
         
         this.ShotGunimage=ImageFactory.createImage(Image.SHOT_GUN);
        this.gun = new Shot_Gun();
         
        nextLevel=true;
         this.ball=new Ball(true,0,0);
        this.ballImage =ImageFactory.createImage(Image.BALL1);
        
        this.backgroundimage=ImageFactory.createImage(Image.BACKGROUND1);
        
         this.paddle = new Paddle(constants.PADDLE_WIDTH,constants.PADDLE__HIGHT,true,0,0);
        this.paddleImage =ImageFactory.createImage(Image.PADDLE2);
        
        this.RED_GIFT=ImageFactory.createImage(Image.RED_GIFT);
        this.Green_GIFT=ImageFactory.createImage(Image.GREEN_GIFT);
        
        this.Brickimage=ImageFactory.createImage(Image.BRICK1);
        this.timer =new Timer(constants.GAME_SPEED,new GameLoop(this));
        this.timer.start();
    }

    @Override
    void drawBrick(Graphics g) {
      for(Brick  br : this.brickList)
        {
            if(br.isVisible())
               g.drawImage(Brickimage.getImage(), br.getX(), br.getY(), this);
                
        }
    }

    @Override
    void intializeGame() {
        existingBox = new int[][]{
                    {1,1,1, 1, 1, 1, 1, 1, 1, 1, 1,1,1},
                    {0,0,1, 0, 1, 0, 1, 0, 1, 0, 1,0,0},
                    {1,1,1, 0, 1, 0, 1, 0, 1, 0, 1,1,1},
                    {0,0,1, 0, 1, 0, 1, 0, 1, 0, 1,0,0},
                    {3,3,1, 0, 1, 0, 1, 0, 1, 0, 1,3,3},
                    {0,0,1, 0, 1, 0, 1, 0, 1, 0, 1,0,0},
                    {1,1,1, 3, 1, 1, 1, 1, 1, 3, 1,1,1}
                };
        for(int i=0 ; i<constants.BRICK_ROW+3;i++){
            for(int j=0 ; j<constants.BRICK_COL+6 ; j++){
                if(existingBox[i][j]==1){
                Brick  brick =new Brick(70+40*j,20+40*i,1);
                this.brickList.add(brick);
                
                }
                else if(existingBox[i][j]==3){
                Brick  brick =new Brick(70+40*j,20+40*i,30);
                this.brickList.add(brick);
               
                }
                
            }
        }
    }

    @Override
    void drawGifts(Graphics g) {
       drawGun(g);
       drawRedGift(g);
       drawGreenGift(g);
            if(NumShots>0){
                drawShotsNum(g);
            }
       if(catchGreenGift)
           drawExpand(g);
    }

    @Override
    void drawPlayer(Graphics g) {
       if (catchGreenGift == false){
            g.drawImage(paddleImage.getImage(), paddle.getX(), paddle.getY(), this);
            
        }
        if (catchGreenGift == true){
            g.drawImage(PaddleExpandimage.getImage(), paddle.getX(), paddle.getY(), this);
            gun.isDead();
        }
    }

    @Override
    void Gifts(int paddleX, int paddleY) {
         this.paddle_exp = new Paddle(constants.EXP_PADDLE_WIDTH,constants.EXP_PADDLE__HIGHT,false,this.paddle.getX(),this.paddle.getY());
        this.PaddleExpandimage=ImageFactory.createImage(Image.PADDLE_EXPAND);

              this.paddle_exp.move();
        
         //green gifts generation
        for(Brick  br : this.brickList)
        {
            if(br.isVisible() && generator.nextDouble() < constants.GIFT_DROP_PRPBABILITY){
                Gift green_gift = new Gift(br.getX(), br.getY());
                this.green_gifts.add(green_gift);
            }  
        }
        //paddle expand moving green gift
        for(Gift green_gift : this.green_gifts){
            int green_giftX = green_gift.getX();
            int green_giftY = green_gift.getY();
           
            
            if(!green_gift.isDead()){
                if(green_giftX >= (paddleX) && green_giftX <= (paddleX + constants.PADDLE_WIDTH) && 
                   green_giftY >= (paddleY) && green_giftY <= (paddleY + constants.PADDLE__HIGHT)){
                    green_gift.die();
                    paddle=paddle_exp;
                    //shoot = false;
                    this.catchGreenGift = true;
                }
                 green_gift.move();
            }
                 
        }
        
        //red gifts 
       for(Brick  br : this.brickList)
        {
            if(br.isVisible() && generator.nextDouble() < constants.GIFT_DROP_PRPBABILITY){
                Gift red_gift = new Gift(br.getX(), br.getY());
                this.red_gifts.add(red_gift);
            }       
        }
        
         //shot gun moving red gift
        for(Gift red_gift : this.red_gifts){
            int red_giftX = red_gift.getX();
            int red_giftY = red_gift.getY();
           
            
            if(!red_gift.isDead()){
                if(red_giftX >= (paddleX) && red_giftX <= (paddleX + constants.PADDLE_WIDTH) && 
                  red_giftY >= (paddleY) && red_giftY <= (paddleY + constants.PADDLE__HIGHT)){
                    red_gift.die();
                     NumShots+=3;
                    this.catchRedGift = true;
                    shoot= true;
                   
                }
                red_gift.move();
                
            }
            //shot guns
            if(shoot == true && !gun.isDead() && this.catchRedGift == true ){
                int shotX = gun.getX();
                int shotY = gun.getY();

                for(Brick  br : this.brickList){
                    int brX = br.getX();
                    int brY = br.getY();
                    if(!br.isVisible()) continue;
                    //collision detection of shotGun with the bricks
                    if(shotX >= (brX) && shotX <= (brX + constants.BRICK_WIDTH) && 
                            shotY >= (brY) && shotY <= (brY + constants.BRICK_HIGHT)){
                        br.setVisible(false);
                        br.die();
                        gun.die();
                        this.score+=5;
                      
                    }
                }
              this.gun.move();  
            }       
        }
            }
    
     public void keyReleased(KeyEvent e) {
        this.paddle.keyReleased(e);  
        game=true;
    }

    public void keyPressed(KeyEvent e) {
        this.paddle.keyPressed(e);
        game=true;
        //shot Gun generated
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_SPACE &&NumShots>0){
            int GunX = this.paddle.getX();
            int GunY = this.paddle.getY();
               
            if(inGame && gun.isDead()){
                gun = new Shot_Gun(GunX, GunY);
                NumShots--;
            }

        }
    }

    private void drawGun(Graphics g) {
        if (!gun.isDead())
            g.drawImage(ShotGunimage.getImage(), gun.getX(), gun.getY(), this);
    }

    private void drawRedGift(Graphics g) {
        for(Gift red_gift : this.red_gifts){
            if(!red_gift.isDead()){
                g.drawImage(RED_GIFT.getImage(), red_gift.getX(), red_gift.getY(), this);
            }
        }
    }

    private void drawShotsNum(Graphics g) {
        Font font;
        font = new Font ("Helvetica",Font.BOLD,15);
        g.setFont(font);
        g.setColor(Color.CYAN);
        g.drawImage(shotsImage.getImage(),constants.BOARD_WIDTH-90 ,60, this);
        g.drawString("Shots : "+NumShots, constants.BOARD_WIDTH-70,90);
     }

    private void drawGreenGift(Graphics g) {
           for(Gift green_gift : this.green_gifts){
            if(!green_gift.isDead())
            {
                g.drawImage(Green_GIFT.getImage(), green_gift.getX(), green_gift.getY(), this);
            }
        }         

    }

    private void drawExpand(Graphics g) {
        Font font;
        font = new Font ("Helvetica",Font.BOLD,15);
        g.setFont(font);
        g.setColor(Color.CYAN);
        g.drawString("Expands", constants.BOARD_WIDTH-70,70);
    }
 
    
}
