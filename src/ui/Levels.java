 package ui;

import Model.Ball;
import Model.Brick;
import Model.Gift;
import Model.Paddle;
import Model.Shot_Gun;
import call_backs.GameEventListener;
import constants.constants;
import image.Image;
import image.ImageFactory;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import static java.awt.Font.BOLD;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

public abstract class Levels extends JPanel {
    ImageIcon backgroundimage;
    ImageIcon paddleImage;
    ImageIcon ballImage;
    ImageIcon Brickimage;
    ImageIcon ShotGunimage;
    ImageIcon RED_GIFT;
    ImageIcon PaddleExpandimage;
    ImageIcon Green_GIFT;
    ImageIcon scoreImage;
    ImageIcon lifeImage;
    ImageIcon shotsImage;
    ImageIcon winImage;
    ImageIcon nextLevelImage;
    ImageIcon gameoverImage;
    ImageIcon BLUE_GIFT;
    Timer timer;
    Paddle paddle;
    Ball ball;
    Shot_Gun gun;
    boolean inGame=true;
    boolean game=false;
    boolean win=false;
    boolean dieEnemy=true;
    boolean nextLevel=false;
    int totalPick=0;
    int score ;
    int live=3;
    Random generator = new Random();
    ArrayList<Brick> brickList =new ArrayList();
   public Levels(){
        intializevariables();
        intializationLayout();
        intializeGame();
    }
   
    abstract void intializevariables();
    abstract void drawBrick(Graphics g);
    abstract void intializeGame();
    
    private void intializationLayout() {
      addKeyListener(new GameEventListener(this));
      setFocusable(true);    
      setPreferredSize(new Dimension(constants.BOARD_WIDTH-5,constants.BOARD_HIGHT-10));
    }
    
     @Override
    public void paintComponent(Graphics g){
         super.paintComponent(g);
         g.drawImage(backgroundimage.getImage(),0,0,null);
         doDrawing(g);     
     }
   
    private void doDrawing(Graphics g) {
        
        if(inGame){
            drawScore(g);
            drawPlayer(g);
            drawBrick(g);
            drawBall(g);
            drawGifts(g);
        }
        else{
            if(timer.isRunning())
                timer.stop();
          if(nextLevel)
           {
            if(!win)
            drawGameOver(g);
            else
            drawWin(g);
            }
           else{
                drawNextLevel(g);
            }
        }
        
        
    }
    abstract void drawGifts(Graphics g);
    abstract void drawPlayer(Graphics g);
    private void drawBall(Graphics g){
        g.drawImage(ballImage.getImage(), ball.getX(),ball.getY(), this);
    }
    
    
    void doOneLoop() {
        update();
        repaint();
         
    }
     
    private void update() {
       // System.out.println(score);
        if(game){
             win=true;
        this.paddle.move();
       int ballX = this.ball.getX();
       int ballY = this.ball.getY();
        int paddleX= this.paddle.getX();
        int paddleY= this.paddle.getY();
        
        for(Brick  br : this.brickList){
            int brickX =br.getX();
            int brickY =br.getY();
            ballX = this.ball.getX();
            ballY = this.ball.getY();
            if(ballX>=brickX && ballX<=(brickX + this.Brickimage.getIconWidth())&& ballY >=brickY && ballY <= (brickY+this.Brickimage.getIconHeight())&&!br.isDead()){
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
                this.ball.setDy(-this.ball.getDy());
               // score+=5;
                //totalPick++;
                //hSystem.out.println(totalPick);
       
            }
            if(!br.isDead())
                win=false;
            
        }
        if(win){
            nextLevel=true;
            inGame=false;
        }
        if(ballX>=paddleX-10 && ballX<= (paddleX + constants.PADDLE_WIDTH-10)&& ballY >=paddleY-10 && ballY <= (paddleY+constants.PADDLE__HIGHT-10)){
                       this.ball.setDy(-this.ball.getDy());
                
        }
        if(ballY>constants.BOARD_HIGHT)
        {
             System.out.println("game over");
             live--;
             if(live>0)
             this.ball=new Ball(false,this.paddle.getX(),this.paddle.getY()-10);
             else{
                 inGame=false;
                 nextLevel=true;
             }
             
        }
        this.ball.move();
        
        Gifts(paddleX,paddleY);
        }
    }
  
    abstract  void Gifts(int paddleX,int paddleY);
   public void keyReleased(KeyEvent e) {}

    public void keyPressed(KeyEvent e) {}

    private void drawScore(Graphics g) {
       Font font;
        font = new Font ("Helvetica",Font.BOLD,15);
        g.setFont(font);
        g.setColor(Color.CYAN);
        g.drawImage(scoreImage.getImage(),constants.BOARD_WIDTH-95 ,8, this);
        g.drawImage(lifeImage.getImage(),constants.BOARD_WIDTH-90 ,28, this);
        g.drawString("Score: "+score, constants.BOARD_WIDTH-70,30);
        g.drawString("Live: "+live, constants.BOARD_WIDTH-70,50);
        
        
    }

    private void drawGameOver(Graphics g) {
        Font font;
        font = new Font ("Helvetica",Font.PLAIN,50);
        g.setFont(font);
       g.setColor(Color.CYAN);
         g.drawImage(gameoverImage.getImage(), constants.BOARD_WIDTH/2-250 ,40, this);
        g.drawString("Score : "+score, constants.BOARD_WIDTH/2-100,370);
        
       
    }

    private void drawWin(Graphics g) {
         Font font;
        font = new Font ("Helvetica",Font.PLAIN,50);
        g.setFont(font);
        g.drawImage(winImage.getImage(), constants.BOARD_WIDTH/2-250 ,40, this);
        g.setColor(Color.CYAN);
        g.drawString("Score : "+score, constants.BOARD_WIDTH/2-100,300);
       
    }

    private void drawNextLevel(Graphics g) {
        Font font;
        font = new Font ("Helvetica",Font.PLAIN,50);
        g.setFont(font);
        g.drawImage(nextLevelImage.getImage(), constants.BOARD_WIDTH/2-180 ,40, this);
        g.setColor(Color.CYAN);
        g.drawString("Score : "+score, constants.BOARD_WIDTH/2-100,300);
    }
    
}
