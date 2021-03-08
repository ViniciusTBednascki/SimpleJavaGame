import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    private Handler handler;
    private boolean uP = false;
    private boolean dP = false;
    private boolean lP = false;
    private boolean rP = false;

    public KeyInput(Handler handler){
        this.handler = handler;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        for(GameObject tempObject : handler.object){
            if(tempObject.getId() == ID.Player){

                if(key == KeyEvent.VK_W) {
                    uP = true;
                    tempObject.setVely(-5);
                }
                if(key == KeyEvent.VK_D) {
                    rP = true;
                    tempObject.setVelx(5);
                }
                if(key == KeyEvent.VK_S) {
                    dP = true;
                    tempObject.setVely(5);
                }
                if(key == KeyEvent.VK_A) {
                    lP = true;
                    tempObject.setVelx(-5);
                }
            }
        }

        if(key == KeyEvent.VK_ESCAPE) System.exit(0);
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        for(GameObject tempObject : handler.object){
            if(tempObject.getId() == ID.Player){
                if(key == KeyEvent.VK_W) {
                    uP =false;
                    if(dP){
                        tempObject.setVely(5);
                    }else {
                        tempObject.setVely(0);
                    }
                }
                if(key == KeyEvent.VK_D) {
                    rP = false;
                    if(lP){
                        tempObject.setVelx(-5);
                    }else {
                        tempObject.setVelx(0);
                    }
                }
                if(key == KeyEvent.VK_S) {
                    dP = false;
                    if(uP){
                        tempObject.setVely(-5);
                    }else {
                        tempObject.setVely(0);
                    }
                }
                if(key == KeyEvent.VK_A) {
                    lP = false;
                    if(rP){
                        tempObject.setVelx(5);
                    }else {
                        tempObject.setVelx(0);
                    }
                }
            }
        }
    }
}
