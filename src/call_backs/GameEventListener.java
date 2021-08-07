
package call_backs;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import ui.Levels;

public class GameEventListener extends KeyAdapter {
    private Levels level;
    
    public GameEventListener(Levels level){
        this.level = level;
    }
   
    @Override
    public void keyReleased(KeyEvent e){
        this.level.keyReleased(e);
    }
    @Override
    public void keyPressed(KeyEvent e){
       this.level.keyPressed(e);
    }
    
}
