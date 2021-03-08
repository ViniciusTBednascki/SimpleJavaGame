import java.awt.*;
import java.util.Random;

public class MenuParticle extends GameObject{

    private Handler handler;
    private Random r = new Random();
    private Color color;
    private boolean bounce = false;

    public MenuParticle(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        color = new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255));
        int direction = r.nextInt(2);
        int mult = 1;
        if(direction == 0){
            mult = 1;
        }else if(direction == 1){
            mult = -1;
        }
        velx = (r.nextInt(3)+2)*mult;
        vely = (r.nextInt(3)+2)*mult;
    }

    public Rectangle getBounds(){
        return new Rectangle((int)x,(int)y,16,16);
    }

    public void tick() {
        x += velx;
        y += vely;
        checkLimit();

        if(bounce) {
            color = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
            bounce = false;
        }

        handler.addObject(new Trail((int)x,(int)y,ID.Trail,color,16,16,0.04f,handler));
    }

    private void checkLimit() {
        if(x <=0 || x >= Game.width - 32){velx *= -1; bounce = true;}
        if(y <=0 || y >= Game.height - 48){vely *= -1; bounce = true;}
    }

    public void render(Graphics g) {
        g.setColor(color);
        g.fillRect((int)x,(int)y,16,16);
    }
}
