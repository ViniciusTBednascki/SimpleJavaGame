import java.awt.*;
import java.util.Random;

public class EnemyBossBullet extends GameObject{

    private Handler handler;
    private Random r = new Random();

    public EnemyBossBullet(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        velx = (r.nextInt(5 - -5)+ -5);
        vely = 5;
    }

    public Rectangle getBounds(){
        return new Rectangle((int)x,(int)y,16,16);
    }

    public void tick() {
        x += velx;
        y += vely;
        checkLimit();


        handler.addObject(new Trail((int)x,(int)y,ID.Trail,Color.red,16,16,0.095f,handler));

        if(y >= Game.height){handler.removeObject(this);}
    }

    private void checkLimit() {
        if(x <=0 || x >= Game.width - 32){velx *= -1;}
    }

    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect((int)x,(int)y,16,16);
    }
}
