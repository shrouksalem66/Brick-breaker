
package brick_breaker;

import java.awt.EventQueue;
import java.io.File;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import ui.GameMainFrame;
import ui.Start;
//import ui.SStart;


public class BRICK_BREAKER {
    public static void main(String[] args) {
        int f=0;
        Start s = new Start();
        s.setSize(355 ,460);
        s.setVisible(true);
      // GameMainFrame i = new GameMainFrame();
         EventQueue.invokeLater(()->{
         // SStart b = new SStart();
         // b.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //  b.setSize(600,600);
        //  b.setVisible(true);
        
        try{
          
           JFXPanel j=new JFXPanel();
           
           String uri=new File("bensound-summer.mp3").toURI().toString();
           new MediaPlayer(new Media(uri)).play();
       }catch(Exception ex){
           JOptionPane.showMessageDialog(null, ex);
       }
        });
    }
    
}
