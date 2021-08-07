
package ui;

import constants.constants;
import image.Image;
import image.ImageFactory;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class GameMainFrame extends JFrame {
    public GameMainFrame(int num){
       
        intilalization(num);
       
    }
    
    private void intilalization(int num) {
        if(num==1)
        add(new LevelOne());   
        if(num==2)
        add(new LevelTwo());   
        if(num==3)
        add(new LevelThree());   
        if(num==4)
        add(new LevelFour());   
        if(num==5)
        add(new LevelMonster());   
    setTitle(constants.TITLE);
    setIconImage(ImageFactory.createImage(Image.BRICKBREAKER).getImage());
    pack();
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setResizable(false);
    setVisible(true);
    }
}
