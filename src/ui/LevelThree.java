
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

public class LevelThree extends Levels{
    public int existingBox[][];
    // ArrayList<Brick> brickList2 =new ArrayList();
    ImageIcon Brickimage2;
    ArrayList<Gift> red_gifts =new ArrayList();
    ArrayList<Gift> green_gifts =new ArrayList();
    ArrayList<Gift> blue_gifts =new ArrayList();
    boolean catchBlueGift=false;
    boolean catchGreenGift=false;
    boolean catchRedGift =false;
    Paddle paddle_exp;
    @Override
    void intializevariables() {
        
        this.scoreImage =ImageFactory.createImage(Image.SCORE);
        this.lifeImage =ImageFactory.createImage(Image.LIFE);
        this.shotsImage =ImageFactory.createImage(Image.SHOTS);
        this.winImage =ImageFactory.createImage(Image.WIN);
        this.gameoverImage =ImageFactory.createImage(Image.GAMEOVER);
        this.nextLevelImage = ImageFactory.createImage(Image.NEXTLEVEL);
        this.ball=new Ball(true,0,0);
        // this.ball.setY(this.ball.getY()-10);
        this.ballImage =ImageFactory.createImage(Image.BALL2);
        
        this.backgroundimage=ImageFactory.createImage(Image.BACKGROUND2);
        
        this.paddle = new Paddle(constants.PADDLE_WIDTH,constants.PADDLE__HIGHT,true,0,0);
        this.paddleImage =ImageFactory.createImage(Image.PADDLE2);
        this.RED_GIFT=ImageFactory.createImage(Image.RED_GIFT);
        this.Green_GIFT=ImageFactory.createImage(Image.GREEN_GIFT);
        this.BLUE_GIFT=ImageFactory.createImage(Image.BLUE_GIFT);
        this.Brickimage=ImageFactory.createImage(Image.BRICK2);
       // this.Brickimage2=ImageFactory.createImage(Image.BRICK22);
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
                    {3,0,1, 0, 1, 0, 3, 0, 1, 0, 1,0,3},
                    {0,1,0, 1, 0, 3, 0, 3, 0, 1, 0,1,0},
                    {0,0,1, 0, 1, 0, 1, 0, 1, 0, 3,0,0},
                    {1,0,0, 1, 0, 1, 0, 1, 0, 1, 0,0,1},
                    {1,1,0, 0, 1, 0, 1, 0, 1, 0, 0,1,1},
                    {1,0,1, 0, 0, 1, 0, 1, 0, 0, 1,0,1},
                    {3,3,3, 1, 0, 0, 0, 0, 0, 1, 3,3,3}
                };
        for(int i=0 ; i<constants.BRICK_ROW+3;i++){
            for(int j=0 ; j<constants.BRICK_COL+6 ; j++){
                 if(existingBox[i][j]==1){
                Brick  brick =new Brick(70+40*j,20+40*i,1);
                this.brickList.add(brick);
                //brick.set_num(1);
                }
                 else if(existingBox[i][j]==3){
                Brick  brick =new Brick(70+40*j,20+40*i,30);
                this.brickList.add(brick);
               // brick.set_num_hits(3);
                }
                
            }
        }
    }

    @Override
    void drawGifts(Graphics g) {
       drawGreenGift(g);
       drawBlueGift(g);
       drawRedGift(g);
       if(catchGreenGift)
           drawExpand(g);
       if(catchBlueGift)
           drawSlow(g);
    }

    @Override
    void drawPlayer(Graphics g) {
       if (catchGreenGift == false){
            g.drawImage(paddleImage.getImage(), paddle.getX(), paddle.getY(), this);
            
        }
        if (catchGreenGift == true){
            g.drawImage(PaddleExpandimage.getImage(), paddle.getX(), paddle.getY(), this);
            
        }
    }

    @Override
    void Gifts(int paddleX, int paddleY) {
        this.paddle_exp = new Paddle(constants.EXP_PADDLE_WIDTH,constants.EXP_PADDLE__HIGHT,false,this.paddle.getX(),this.paddle.getY());
        this.PaddleExpandimage=ImageFactory.createImage(Image.PADDLE_EXPAND);

              this.paddle_exp.move();
        //red gifts generation
        for(Brick  br : this.brickList)
        {
            if(br.isVisible() && generator.nextDouble() < constants.GIFT_DROP_PRPBABILITY){
                Gift red_gift = new Gift(br.getX(), br.getY());
                this.red_gifts.add(red_gift);
            }       
        }
        //up to next level
        for(Gift red_gift : this.red_gifts){
            int red_giftX = red_gift.getX();
            int red_giftY = red_gift.getY();
           
            
            if(!red_gift.isDead()){
                if(red_giftX >= (paddleX) && red_giftX <= (paddleX + constants.PADDLE_WIDTH) && 
                  red_giftY >= (paddleY) && red_giftY <= (paddleY + constants.PADDLE__HIGHT)){
                    red_gift.die();
                    this.catchRedGift = true;
                   // nextLevel=false;
                   //this.inGame=false;
                   new LevelTwo();
                 //  win=true;
                   inGame=false;
                   nextLevel=false;
                }
                red_gift.move();
                
            }
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
        
        
         //blue gifts generation
        for(Brick  br : this.brickList)
        {
            if(br.isVisible() && generator.nextDouble() < constants.GIFT_DROP_PRPBABILITY){
                Gift blue_gift = new Gift(br.getX(), br.getY());
                this.blue_gifts.add(blue_gift);
            }  
        }
        
        //paddle expand moving blue gift
        for(Gift blue_gift : this.blue_gifts){
            int blue_giftX = blue_gift.getX();
            int blue_giftY = blue_gift.getY();
           
            
            if(!blue_gift.isDead()){
                if(blue_giftX >= (paddleX) && blue_giftX <= (paddleX + constants.PADDLE_WIDTH) && 
                   blue_giftY >= (paddleY) && blue_giftY <= (paddleY + constants.PADDLE__HIGHT)){
                    blue_gift.die();
                    ball.dec_speed();
                    paddle=paddle_exp;
                    //shoot = false;
                    this.catchBlueGift = true;
                }
                 blue_gift.move();
            }
                 
        }
       
            }
    }
     public void keyReleased(KeyEvent e) {
        this.paddle.keyReleased(e);  
        game=true;
    }

    public void keyPressed(KeyEvent e) {
        this.paddle.keyPressed(e);
        
        int key = e.getKeyCode();
        
        if(key==KeyEvent.VK_ENTER && !nextLevel ){
            new LevelTwo();
        }
        game=true;
        //shot Gun generated
      
    }
    private void drawGreenGift(Graphics g) {
           for(Gift green_gift : this.green_gifts){
            if(!green_gift.isDead())
            {
                g.drawImage(Green_GIFT.getImage(), green_gift.getX(), green_gift.getY(), this);
            }
        }         

    }
     private void drawBlueGift(Graphics g) {
       for(Gift blue_gift : this.blue_gifts){
            if(!blue_gift.isDead()){
                g.drawImage(BLUE_GIFT.getImage(), blue_gift.getX(), blue_gift.getY(), this);
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

    private void drawSlow(Graphics g) {
       Font font;
        font = new Font ("Helvetica",Font.BOLD,15);
        g.setFont(font);
        g.setColor(Color.CYAN);
        g.drawString("Slow", constants.BOARD_WIDTH-70,90);
    }

    private void drawRedGift(Graphics g) {
         for(Gift red_gift : this.red_gifts){
            if(!red_gift.isDead()){
                g.drawImage(RED_GIFT.getImage(), red_gift.getX(), red_gift.getY(), this);
            }
        }
    }
 
    
}
