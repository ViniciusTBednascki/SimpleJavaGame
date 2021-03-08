import java.awt.*;

public class EnemyBoss extends GameObject{

    private Handler handler;
    private int timer = 80;
    private int timer2= 50;
    private int bulletTimer = 0;

    public EnemyBoss(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        velx = 0;
        vely = 2;
    }

    public Rectangle getBounds(){
        return new Rectangle((int)x,(int)(y - 96),96,192);
    }

    public void tick() {
        x += velx;
        y += vely;
        checkLimit();

        if(timer <= 0){
            vely = 0;
            timer2--;
            if(timer2 <= 0){
                if(velx ==0){
                    velx = 2;
                }

                velx += 0.005f*Math.signum(velx);

                velx = Game.clamp(velx, -10, 10);

                if(bulletTimer <= 0){
                    handler.addObject(new EnemyBossBullet((int)x + 48,(int)y + 48,ID.BasicEnemy, handler));
                    bulletTimer = 80/(int)Math.abs(velx);
                }else{bulletTimer--;}
            }
        }
        else{
            timer --;
            handler.addObject(new Trail((int)x,(int)y,ID.Trail,Color.red,96,96,0.03f,handler));
        }
    }

    private void checkLimit() {
        if(x <=0 || x >= Game.width - 112){velx *= -1;}
    }

    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect((int)x,(int)y,96,96);
    }
}
