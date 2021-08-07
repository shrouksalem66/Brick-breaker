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

public class LevelMonster extends Levels{

    Ball ball2;
Ball ball3;
ImageIcon ballImage2;
boolean catchGreenGift=false;
ArrayList<Gift> green_gifts =new ArrayList();
    ArrayList<Gift> red_gifts =new ArrayList();
    ArrayList<Gift> bombs =new ArrayList();
      
   int NumShots=0;
   int direction=-3;
    boolean catchRedGift=false;
     boolean shoot=false;
   boolean movex=true ,movey=true ,movexy=true;
    @Override
    void intializevariables() {
        this.paddle = new Paddle(constants.PADDLE_WIDTH,constants.PADDLE__HIGHT,true,0,0);
        this.paddleImage =ImageFactory.createImage(Image.PADDLE2);
        this.dieEnemy=false; 
        this.scoreImage =ImageFactory.createImage(Image.SCORE);
        this.lifeImage =ImageFactory.createImage(Image.LIFE);
        this.shotsImage =ImageFactory.createImage(Image.SHOTS);
        this.winImage =ImageFactory.createImage(Image.WIN);
        this.gameoverImage =ImageFactory.createImage(Image.GAMEOVER);
        this.RED_GIFT=ImageFactory.createImage(Image.RED_GIFT);
        nextLevel=true;
        this.ball=new Ball(true,0,0);
        this.ball.setY(this.ball.getY()-20);
        this.ballImage2 =ImageFactory.createImage(Image.BALL2);
        this.ballImage =ImageFactory.createImage(Image.BALL3);
        this.Green_GIFT=ImageFactory.createImage(Image.GREEN_GIFT);
        this.ShotGunimage=ImageFactory.createImage(Image.SHOT_GUN);
        this.gun = new Shot_Gun(); 
        this.backgroundimage=ImageFactory.createImage(Image.BACKGROUND3);
        Brick brick =new Brick(25,50,50);
        this.Brickimage=ImageFactory.createImage(Image.MONSTER);
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
    private void drawBombs(Graphics g) {
        for(Gift bomb : this.bombs){
            if(!bomb.isDead())
               g.drawImage(ShotGunimage.getImage(), bomb.getX(),bomb.getY(), this);
        }
    }

    @Override
    void intializeGame() {
        Brick  brick =new Brick(510,300,50);
              
                this.brickList.add(brick);
    }

    @Override
    void drawGifts(Graphics g) {

        drawGun(g);
            drawRedGift(g);
            drawBombs(g);
            if(NumShots>0){
                drawShotsNum(g);
            }
            drawGreenGift(g);
      if(catchGreenGift){
          g.drawImage(ballImage2.getImage(), ball2.getX(),ball2.getY(), this);
          g.drawImage(ballImage2.getImage(), ball3.getX(),ball3.getY(), this);
          drawBall(g);
      }
    }

    @Override
    void drawPlayer(Graphics g) {
         g.drawImage(paddleImage.getImage(), paddle.getX(), paddle.getY(), this);
    }

    @Override
    void Gifts(int paddleX, int paddleY) {
        if (paddle.isDead()){
            inGame = false;
            this.gameoverImage =ImageFactory.createImage(Image.GAMEOVER);
        }
        
        //7raket el enemy 
        if(this.totalPick>=25)
            this.dieEnemy=true;
        for(Brick  br : this.brickList)
        {
            if(movexy){
            if(movex){
            if(br.getX()==0){
                direction=3;
              
            }
            if(br.getX()>=constants.BOARD_WIDTH-50){
                direction =-3;
                 movex=false ;
                 if(br.getY()<=20)
                     movey=false;
            }
            if(br.isVisible())
              br.move(true,direction);
            }
            if(!movex){
                
                br.move(false,direction-50); 
                movex=true;
                    
            }
            if(!movey){
                movexy=false;
                br.move(false,direction+20); 
            }
             
            }else{
                br.move(true,direction);
                if(br.getX()>=constants.BOARD_WIDTH/2){
                 br.move(false,-direction);
                 
                }
                if(br.getX()<=0){
                   
                    movexy=true;
                }
                if(br.getY()<=0){
                      br.move(true,-direction);
                    movexy=true;
                }                
            }
            //bombs generation
            if(br.isVisible() && generator.nextDouble() < constants.Monster_DROP_PRPBABILITY){
                Gift bomb  = new Gift(br.getX(),br.getY());
                this.bombs.add(bomb);                
            }
            for(Gift bomb:bombs){
                int bombX = bomb.getX();
                int bombY = bomb.getY();
                int paddelX = paddle.getX();
                int paddelY = paddle.getY();
                
                if(!bomb.isDead() && !paddle.isDead()){
                    if(bombX >= (paddelX) && bombX <= (paddelX + constants.PADDLE_WIDTH) && 
                    bombY >= (paddelY) && bombY <= (paddelY + constants.PADDLE__HIGHT)){
                        bomb.die();
                        this.live--;
                        if(live==0){
                             paddle.die();
                             inGame=false;
                        }
                    }
                }
                
                if(!bomb.isDead()){
                bomb.move();
            }
          }
        }
         //red gifts generation
        for(Brick  br : this.brickList)
        {
            if(br.isVisible() && generator.nextDouble() < constants.Monster_DROP_PRPBABILITY){
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
                    int gunX = gun.getX();
                    int gunY = gun.getY();
                    if(!br.isVisible()) continue;
                    //collision detection of shotGun with the bricks
                    if(shotX >= (brX) && shotX <= (brX + constants.BRICK_WIDTH) && 
                            shotY >= (brY) && shotY <= (brY + constants.BRICK_HIGHT)){
                        if(this.dieEnemy){
                        br.setVisible(false);
                        br.die();
                        gun.die();
                        }
                        //monster lives decrease when hit by shot gun
                        if(gunX>=brX && gunX<=(brX + this.Brickimage.getIconWidth())&&
                            gunY >=brY && gunY <= (brY+this.Brickimage.getIconHeight())
                            && br.getNum_hits()==1){
                            br.setVisible(false);
                            br.die();
                            br.set_TotalBricks(br.get_TotalBricks()-1);
                            totalPick++;
                            System.out.println(totalPick);
                        }
                        else{
                            System.out.println(br.getNum_hits());
                            br.setNum_hits(br.getNum_hits()-1);
                        }
                        this.score+=5;                     
                    }
                }
              this.gun.move();  
            }
            
            
        }
        
          //green gifts generation
        for(Brick  br : this.brickList)
        {
            if(br.isVisible() && generator.nextDouble() < constants.Monster_DROP_PRPBABILITY){
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
         
        this.ball3.move();
       
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
        g.drawImage(shotsImage.getImage(),constants.BOARD_WIDTH-90 ,40, this);
        g.drawString("Shots : "+NumShots, constants.BOARD_WIDTH-70,70);
    }

    private void drawGreenGift(Graphics g) {
        for(Gift green_gift : this.green_gifts){
            if(!green_gift.isDead())
            {
                g.drawImage(Green_GIFT.getImage(), green_gift.getX(), green_gift.getY(), this);
            }
        }     
    }

    private void drawBall(Graphics g) {
        Font font;
        font = new Font ("Helvetica",Font.BOLD,15);
        g.setFont(font);
        g.setColor(Color.CYAN);
        g.drawString("2 Ball", constants.BOARD_WIDTH-70,90);
    }
 
    }