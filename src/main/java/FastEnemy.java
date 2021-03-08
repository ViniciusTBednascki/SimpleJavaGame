import java.awt.*;

public class FastEnemy extends GameObject{

    private Handler handler;

    public FastEnemy(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        velx = 2;
        vely = 8;
    }

    public Rectangle getBounds(){
        return new Rectangle((int)x,(int)y,16,16);
    }

    public void tick() {
        x += velx;
        y += vely;
        checkLimit();

        handler.addObject(new Trail((int)x,(int)y,ID.Trail,Color.cyan,16,16,0.06f,handler));
    }

    private void checkLimit() {
        if(x <=0 || x >= Game.width - 32){velx *= -1;}
        if(y <=0 || y >= Game.height - 48){vely *= -1;}
    }

    public void render(Graphics g) {
        g.setColor(Color.cyan);
        g.fillRect((int)x,(int)y,16,16);
    }
}
