import java.awt.*;
import java.util.LinkedList;

public class Player extends GameObject{

    private Handler handler;
    private LinkedList<ID> enemiesId = new LinkedList<>();


    public Player(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        enemiesId.add(ID.BasicEnemy);
        enemiesId.add(ID.FastEnemy);
        enemiesId.add(ID.SmartEnemy);
        enemiesId.add(ID.EnemyBoss);
    }


    public Rectangle getBounds(){
        return new Rectangle((int)x,(int)y,32,32);
    }

    public void tick() {
        x += velx;
        y += vely;

        x = Game.clamp(x,0,Game.width - 48);
        y = Game.clamp(y,0,Game.height - 72);

        collision();

        handler.addObject(new Trail((int)x,(int)y,ID.Trail,Color.white,32,32,0.08f,handler));
    }

    private void collision() {
        for(GameObject tempObject : handler.object){
            if(enemiesId.contains(tempObject.getId())){
                if(getBounds().intersects(tempObject.getBounds())){
                    HUD.health -= 2;
                }
            }
        }
    }

    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect((int)x,(int)y, 32, 32);
    }
}
