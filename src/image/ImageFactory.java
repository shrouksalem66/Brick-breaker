
package image;

import constants.constants;
import javax.swing.ImageIcon;


public class ImageFactory {
    public static ImageIcon createImage(Image images){
        ImageIcon imageIcon=null;
        switch(images){
            case NEXTLEVEL:
                imageIcon = new ImageIcon(constants.NEXTLEVEL_URL);
                break;
            case GAMEOVER:
                imageIcon = new ImageIcon(constants.GAMEOVER_URL);
                break;
            case WIN:
                imageIcon = new ImageIcon(constants.WIN_URL);
                break;
            case SHOTS:
                imageIcon = new ImageIcon(constants.SHOTS_URL);
                break;
            case LIFE:
                imageIcon = new ImageIcon(constants.LIFE_URL);
                break;
            case SCORE:
                imageIcon = new ImageIcon(constants.SCORE_URL);
                break;
            case RED_GIFT:
               imageIcon = new ImageIcon(constants.RED_GIFT_URL);
                break;
            case BLUE_GIFT:
               imageIcon = new ImageIcon(constants.BLUE_GIFT_URL);
                break;  
            case SHOT_GUN:
               imageIcon = new ImageIcon(constants.SHOT_GUN_URL);
                break;
            case GREEN_GIFT:
               imageIcon = new ImageIcon(constants.GREEN_GIFT_URL);
                break;
            case PADDLE_EXPAND:
               imageIcon = new ImageIcon(constants.PADDLE_EXPAND_URL);
                break;
             case BRICKBREAKER:
               imageIcon = new ImageIcon(constants.BRICKBREACKER_URL);
                break; 
             //level 1, 2 BALL1,PADDLE1,BRICK1,BACKGROUND1, 
                case BALL1:
               imageIcon = new ImageIcon(constants.BALL1_URL);
                break; 
                 case PADDLE1:
               imageIcon = new ImageIcon(constants.paddle1_URL);
                break; 
                 case BRICK1:
               imageIcon = new ImageIcon(constants.BRICK1_URL);
                break; 
                 case BACKGROUND1:
               imageIcon = new ImageIcon(constants.BACKGROUND1_URL);
                break;
                
            //level 3,4 BACKGROUND2,BALL2,PADDLE2,BRICK2,f
                 case BALL2:
               imageIcon = new ImageIcon(constants.BALL2_URL);
                break; 
                 case PADDLE2:
               imageIcon = new ImageIcon(constants.paddle2_URL);
                break; 
                 case BRICK2:
               imageIcon = new ImageIcon(constants.BRICK2_URL);
                break; 
                 case BACKGROUND2:
               imageIcon = new ImageIcon(constants.BACKGROUND2_URL);
                break;
                
             //level monstor MONSTER,BACKGROUND3,BALL3;
                case MONSTER:
               imageIcon = new ImageIcon(constants.MONSTER_URL);
                break; 
                case BACKGROUND3:
               imageIcon = new ImageIcon(constants.GROUND3_URL);
                break; 
                case BALL3:
               imageIcon = new ImageIcon(constants.BALL3_URL);
                break; 
            default :
                return null;
        }
        return imageIcon;
    }
}
