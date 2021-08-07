
package ui;

import Model.Ball;
import Model.Brick;
import Model.Gift;
import Model.Paddle;
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

public class LevelFour extends Levels {
public int existingBox[][];
Paddle paddle2;
Ball ball2;
Ball ball3;
ImageIcon ballImage2;
boolean catchGreenGift=false;
ArrayList<Gift> green_gifts =new ArrayList();
 ArrayList<Gift> red_gifts =new ArrayList();
 boolean catchRedGift =false;
    @Override
    void intializevariables() {
        this.scoreImage =ImageFactory.createImage(Image.SCORE);
        this.lifeImage =ImageFactory.createImage(Image.LIFE);
        this.shotsImage =ImageFactory.createImage(Image.SHOTS);
        this.winImage =ImageFactory.createImage(Image.WIN);
        this.gameoverImage =ImageFactory.createImage(Image.GAMEOVER);
        this.nextLevelImage = ImageFactory.createImage(Image.NEXTLEVEL);
        this.ball=new Ball(true,0,0);
        this.ballImage =ImageFactory.createImage(Image.BALL2);
        this.ballImage2 =ImageFactory.createImage(Image.BALL1);
        
        this.backgroundimage=ImageFactory.createImage(Image.BACKGROUND2);
        
        this.paddle = new Paddle(constants.PADDLE_WIDTH,constants.PADDLE__HIGHT,true,0,0);
        this.paddleImage =ImageFactory.createImage(Image.PADDLE2);
        this.RED_GIFT=ImageFactory.createImage(Image.RED_GIFT);
        this.Green_GIFT=ImageFactory.createImage(Image.GREEN_GIFT);
        this.Brickimage=ImageFactory.createImage(Image.BRICK2);
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
                    {3,1,3, 1, 1, 1, 1, 1, 1, 1, 3,1,3},
                    {0,1,0, 0, 0, 0, 0, 0, 0, 0, 0,1,0},
                    {0,0,1, 0, 0, 0, 0, 0, 0, 0, 1,0,0},
                    {1,0,0, 1, 0, 0, 0, 0, 0, 1, 0,0,1},
                    {3,0,0, 0, 1, 0, 1, 0, 1, 0, 0,0,3},
                    {1,0,0, 0, 0, 1, 0, 1, 0, 0, 0,0,1},
                    {3,1,3, 1, 0, 0, 3, 0, 0, 1, 3,1,3}
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
      drawRedGift(g);
      drawGreenGift(g);
      if(catchGreenGift){
          g.drawImage(ballImage2.getImage(), ball2.getX(),ball2.getY(), this);
          g.drawImage(ballImage2.getImage(), ball3.getX(),ball3.getY(), this);
          drawBall(g);
      }
      if(catchRedGift)
           drawPaddle(g);
          
    }

    @Override
    void drawPlayer(Graphics g) {
         g.drawImage(paddleImage.getImage(), paddle.getX(), paddle.getY(), this);
         if(catchRedGift)
             g.drawImage(paddleImage.getImage(), paddle2.getX(), paddle2.getY(), this);
             
    }

    @Override
    void Gifts(int paddleX, int paddleY) {
        //red gifts generation
        for(Brick  br : this.brickList)
        {
            if(br.isVisible() && generator.nextDouble() < constants.GIFT_DROP_PRPBABILITY){
                Gift red_gift = new Gift(br.getX(), br.getY());
                this.red_gifts.add(red_gift);
            }       
        }
        
        //two paddle
         for(Gift red_gift : this.red_gifts){
            int red_giftX = red_gift.getX();
            int red_giftY = red_gift.getY();
           
            
            if(!red_gift.isDead()){
                if(red_giftX >= (paddleX) && red_giftX <= (paddleX + constants.PADDLE_WIDTH) && 
                  red_giftY >= (paddleY) && red_giftY <= (paddleY + constants.PADDLE__HIGHT)){
                    red_gift.die();
                    if(!catchRedGift)
                        this.paddle2 = new Paddle(constants.PADDLE_WIDTH,constants.PADDLE__HIGHT,true,0,0);
                    this.catchRedGift = true;
                    
                }
                red_gift.move();
                
            }
         }
         //move paddle2
        
         if(catchRedGift){
             this.paddle2.move();
              int ballX = this.ball.getX();
       int ballY = this.ball.getY();
        int paddle2X= this.paddle2.getX();
        int paddle2Y= this.paddle2.getY();
        
        
            
        if(ballX>=paddle2X-10 && ballX<= (paddle2X + constants.PADDLE_WIDTH-10)&& ballY >=paddle2Y-10 && ballY <= (paddle2Y+constants.PADDLE__HIGHT-10)){
                       this.ball.setDy(-this.ball.getDy());
                
        }
         }
          //green gifts generation
        for(Brick  br : this.brickList)
        {
            if(br.isVisible() && generator.nextDouble() < constants.GIFT_DROP_PRPBABILITY){
                Gift green_gift = new Gift(br.getX(), br.getY());
                this.green_gifts.add(green_gift);
            }  
        }
        // ball2 
        for(Gift green_gift : this.green_gifts){
            int green_giftX = green_gift.getX();
            int green_giftY = green_gift.getY();
            
            if(!green_gift.isDead()){
                if(green_giftX >= (paddleX) && green_giftX <= (paddleX + constants.PADDLE_WIDTH) && 
                   green_giftY >= (paddleY) && green_giftY <= (paddleY + constants.PADDLE__HIGHT)){
                    green_gift.die();
                    if(!catchGreenGift){
                         this.ball2=new Ball(false,paddleX,paddleY-50);
                         this.ball3=new Ball(false,paddleX,paddleY-200);
                        
                    }
                    this.catchGreenGift = true;
                }
                 green_gift.move();
            }
                 
        }
        //MOVE BALL2
       if(catchGreenGift){
        int ball2X = ball2.getX();
       int ball2Y = ball2.getY();
        
        for(Brick  br : this.brickList){
            int brickX =br.getX();
            int brickY =br.getY();
            ball2X = this.ball2.getX();
            ball2Y = this.ball2.getY();
           
            if(ball2X>=brickX && ball2X<=(brickX + this.Brickimage.getIconWidth())&& ball2Y >=brickY && ball2Y <= (brickY+this.Brickimage.getIconHeight())&&!br.isDead()){
             if(/*dieEnemy*/br.getNum_hits()==1){
                br.setVisible(false);
                br.die();
                br.set_TotalBricks(br.get_TotalBricks()-1);
                score+=5;
                totalPick++;
                System.out.println(totalPick);
                
              }
              else{
                  System.out.println(br.getNum_hits());
                  br.setNum_hits(br.getNum_hits()-1);
              }
                this.ball2.setDy(-this.ball2.getDy());
            }
        }
        if(ball2X>=paddleX-10 && ball2X<= (paddleX + constants.PADDLE_WIDTH-10)&& ball2Y >=paddleY-10 && ball2Y <= (paddleY+constants.PADDLE__HIGHT-10)){
                       this.ball2.setDy(-this.ball2.getDy());
                
        }
         if(catchRedGift && catchGreenGift){
           int paddle2X= this.paddle2.getX();
            int paddle2Y= this.paddle2.getY();
        
        if(ball2X>=paddle2X-10 && ball2X<= (paddle2X + constants.PADDLE_WIDTH-10)&& ball2Y >=paddle2Y-10 && ball2Y <= (paddle2Y+constants.PADDLE__HIGHT-10)){
                       this.ball2.setDy(-this.ball2.getDy());
                
        }
        
         }
        this.ball2.move();
        int ball3X = ball3.getX();
       int ball3Y = ball3.getY();
        
        for(Brick  br : this.brickList){
            int brickX =br.getX();
            int brickY =br.getY();
            ball2X = this.ball3.getX();
            ball2Y = this.ball3.getY();
           
            if(ball3X>=brickX && ball3X<=(brickX + this.Brickimage.getIconWidth())&& ball3Y >=brickY && ball3Y <= (brickY+this.Brickimage.getIconHeight())&&!br.isDead()){
             if(/*dieEnemy*/br.getNum_hits()==1){
                br.setVisible(false);
                br.die();
                br.set_TotalBricks(br.get_TotalBricks()-1);
                score+=5;
                totalPick++;
                System.out.println(totalPick);
                
              }
              else{
                  System.out.println(br.getNum_hits());
                  br.setNum_hits(br.getNum_hits()-1);
              }
                this.ball3.setDy(-this.ball3.getDy());
            }
        }
        if(ball3X>=paddleX-10 && ball3X<= (paddleX + constants.PADDLE_WIDTH-10)&& ball3Y >=paddleY-10 && ball3Y <= (paddleY+constants.PADDLE__HIGHT-10)){
                       this.ball3.setDy(-this.ball3.getDy());
                
        }
         if(catchRedGift && catchGreenGift){
           int paddle2X= this.paddle2.getX();
            int paddle2Y= this.paddle2.getY();
        
        if(ball3X>=paddle2X-10 && ball3X<= (paddle2X + constants.PADDLE_WIDTH-10)&& ball3Y >=paddle2Y-10 && ball3Y <= (paddle2Y+constants.PADDLE__HIGHT-10)){
                       this.ball3.setDy(-this.ball3.getDy());
                
        }
        
         }
        this.ball3.move();
       
       }
       
    
    }
    public void keyReleased(KeyEvent e) {
        this.paddle.keyReleased(e); 
        if(catchRedGift)
         this.paddle2.keyReleased(e); 
        game=true;
    }

    public void keyPressed(KeyEvent e) {
         this.paddle.keyPressed(e);
        if(catchRedGift)
          this.paddle2.keyPressed(e);
   
        game=true;
        
      
    }

    private void drawRedGift(Graphics g) {
       for(Gift red_gift : this.red_gifts){
            if(!red_gift.isDead()){
                g.drawImage(RED_GIFT.getImage(), red_gift.getX(), red_gift.getY(), this);
            }
        }
    }

    private void drawGreenGift(Graphics g) {
         for(Gift green_gift : this.green_gifts){
            if(!green_gift.isDead())
            {
                g.drawImage(Green_GIFT.getImage(), green_gift.getX(), green_gift.getY(), this);
            }
        }     
    }

    private void drawPaddle(Graphics g) {
       Font font;
        font = new Font ("Helvetica",Font.BOLD,15);
        g.setFont(font);
        g.setColor(Color.CYAN);
        g.drawString("Paddle", constants.BOARD_WIDTH-70,70);
    }

    private void drawBall(Graphics g) {
        Font font;
        font = new Font ("Helvetica",Font.BOLD,15);
        g.setFont(font);
        g.setColor(Color.CYAN);
        g.drawString("2 Ball", constants.BOARD_WIDTH-70,90);
    }
    
}
