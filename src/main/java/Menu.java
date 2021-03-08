import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Menu extends MouseAdapter {

    private Game game;
    private Handler handler;
    private Spawn spawn;
    private HUD hud;
    private Random r = new Random();

    public Menu(Game game, Handler handler, Spawn spawn, HUD hud){
        this.game = game;
        this.handler = handler;
        this.spawn = spawn;
        this.hud = hud;
    }

    public void mousePressed(MouseEvent e){
        int msX = e.getX();
        int msY = e.getY();

        //Play Button
        if(mouseOver(msX,msY,Game.width/2 - 100,Game.height/6 + 32, 200, 64)){
            if(game.gameState == Game.STATE.Menu){
                hud.reset();
                game.gameState = Game.STATE.Game;
                handler.addObject(new Player(Game.width / 2 - 32, Game.height / 2 - 32, ID.Player, handler));
                handler.clearEnemies();
                spawn.start();
            }
        }

        //Help Button
        if(mouseOver(msX,msY,Game.width/2 - 100,Game.height*2/6 + 32, 200, 64)){
            if(game.gameState == Game.STATE.Menu){
                game.gameState = Game.STATE.Help;
            }
        }

        //Quit/Back Button
        if(mouseOver(msX,msY,Game.width/2 - 100, Game.height*3/6 + 32, 200, 64)){
            if(game.gameState == Game.STATE.Menu){
                System.exit(0);
            }else if(game.gameState == Game.STATE.Help || game.gameState == Game.STATE.End){
                game.gameState = Game.STATE.Menu;
            }
        }
    }

    public void mouseReleased(MouseEvent e){

    }


    public void tick(){

    }

    private boolean mouseOver(int msX, int msY, int x, int y, int width, int height){
        if(msX > x && msX < x + width){
            if(msY > y && msY < y + height){
                return true;
            }else{ return false; }
        }else{ return false; }
    }

    public void render(Graphics g){
        if(game.gameState == Game.STATE.Menu) {
            Font fntMenu = new Font("arial", 1, 50);
            Font fntButton = new Font("arial", 1, 30);

            g.setFont(fntMenu);
            g.setColor(Color.white);
            g.drawString("Wave", Game.width / 2 - 65, Game.height / 6);

            g.setFont(fntButton);
            g.setColor(Color.green);
            g.drawRect(Game.width / 2 - 100, Game.height / 6 + 32, 200, 64);
            g.drawString("Play", Game.width / 2 - 35, Game.height / 6 + 74);

            g.setFont(fntButton);
            g.setColor(Color.yellow);
            g.drawRect(Game.width / 2 - 100, Game.height * 2 / 6 + 32, 200, 64);
            g.drawString("Help", Game.width / 2 - 35, Game.height * 2 / 6 + 74);

            g.setFont(fntButton);
            g.setColor(Color.red);
            g.drawRect(Game.width / 2 - 100, Game.height * 3 / 6 + 32, 200, 64);
            g.drawString("Quit", Game.width / 2 - 35, Game.height * 3 / 6 + 74);

        }else if(game.gameState == Game.STATE.Help){
            Font fntMenu = new Font("arial", 1, 50);
            Font fntButton = new Font("arial", 1, 30);
            Font fntHelp = new Font("arial",1,25);

            g.setFont(fntMenu);
            g.setColor(Color.white);
            g.drawString("Help", Game.width / 2 - 55, Game.height / 6);

            g.setFont(fntHelp);
            g.setColor(Color.white);
            g.drawString("Use WASD keys to move and doge enemies", 60, Game.height / 6 + 74);
            g.drawString("The more you survive, more points you get", 63, Game.height / 6 + 102);

            g.setFont(fntButton);
            g.setColor(Color.red);
            g.drawRect(Game.width / 2 - 100, Game.height * 3 / 6 + 32, 200, 64);
            g.drawString("Back", Game.width / 2 - 35, Game.height * 3 / 6 + 74);

        }else if(game.gameState == Game.STATE.End){
            Font fntMenu = new Font("arial", 1, 50);
            Font fntButton = new Font("arial", 1, 30);
            Font fntHelp = new Font("arial",1,25);

            g.setFont(fntMenu);
            g.setColor(Color.white);
            g.drawString("Game over", Game.width / 2 - 122, Game.height / 6);

            g.setFont(fntHelp);
            g.setColor(Color.white);
            g.drawString("You lost and your score was " + HUD.score, 122, Game.height / 6 + 74);

            g.setFont(fntButton);
            g.setColor(Color.red);
            g.drawRect(Game.width / 2 - 100, Game.height * 3 / 6 + 32, 200, 64);
            g.drawString("Back", Game.width / 2 - 35, Game.height * 3 / 6 + 74);
        }
    }
}
