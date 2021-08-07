
package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameLoop implements ActionListener {
    private Levels level;
      
    public GameLoop ( Levels level){
        this.level =level;
        
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        this.level.doOneLoop();
    }
    
}
