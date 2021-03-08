import java.awt.*;

public class SmartEnemy extends GameObject{

    private Handler handler;
    private GameObject player;

    public SmartEnemy(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        for(GameObject object : handler.object){
            if(object.getId() == ID.Player){
                player = object;
            }
        }
    }

    public Rectangle getBounds(){
        return new Rectangle((int)x,(int)y,16,16);
    }

    public void tick() {
        x += velx;
        y += vely;
        checkLimit();

        changeDirection();

        handler.addObject(new Trail((int)x,(int)y,ID.Trail,Color.green,16,16,0.06f,handler));
    }

    private void changeDirection() {

        float difX = x - player.getX() - 8;
        float difY = y - player.getY() - 8;
        float distance = (float) Math.sqrt(Math.pow(x - player.getX(),2) + Math.pow(y - player.getY(),2));

        velx = (float) ((-1.0/distance) * difX);
        vely = (float) ((-1.0/distance) * difY);
    }

    private void checkLimit() {
        if(x <=0 || x >= Game.width - 32){velx *= -1;}
        if(y <=0 || y >= Game.height - 48){vely *= -1;}
    }

    public void render(Graphics g) {
        g.setColor(Color.green);
        g.fillRect((int)x,(int)y,16,16);
    }
}
