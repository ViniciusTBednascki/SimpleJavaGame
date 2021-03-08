import java.util.Random;

public class Spawn {

    private Handler handler;
    private HUD hud;
    private int scoreKeep;
    private Random r = new Random();

    public Spawn(Handler handler, HUD hud){
        this.handler = handler;
        this.hud = hud;
    }

    public void start(){
        handler.addObject(new SmartEnemy(32,32,ID.SmartEnemy, handler));
    }

    public void levelSwitch(){
        if(scoreKeep%250 == 0) {
            hud.level += 1;

            switch (hud.level) {
                case 2:
                    handler.addObject(new BasicEnemy(r.nextInt(Game.width - 48) + 16, r.nextInt(Game.height - 64) + 16, ID.BasicEnemy, handler));
                    handler.addObject(new BasicEnemy(r.nextInt(Game.width - 48) + 16, r.nextInt(Game.height - 64) + 16, ID.BasicEnemy, handler));
                    break;
                case 3:
                    handler.addObject(new FastEnemy(r.nextInt(Game.width - 48) + 16, r.nextInt(Game.height - 64) + 16, ID.FastEnemy, handler));
                    break;
                case 4:
                    handler.addObject(new FastEnemy(r.nextInt(Game.width - 48) + 16, r.nextInt(Game.height - 64) + 16, ID.FastEnemy, handler));
                    break;
                case 5:
                    handler.addObject(new BasicEnemy(r.nextInt(Game.width - 48) + 16, r.nextInt(Game.height - 64) + 16, ID.BasicEnemy, handler));
                    handler.addObject(new BasicEnemy(r.nextInt(Game.width - 48) + 16, r.nextInt(Game.height - 64) + 16, ID.BasicEnemy, handler));
                    handler.addObject(new BasicEnemy(r.nextInt(Game.width - 48) + 16, r.nextInt(Game.height - 64) + 16, ID.BasicEnemy, handler));
                    break;
                case 10:
                    handler.clearEnemies();
                    handler.addObject(new EnemyBoss((Game.width / 2) - 48, -120, ID.EnemyBoss, handler));
                    break;
                default:
                    return;
            }
        }
    }

    public void tick(){
        scoreKeep = hud.score;

        levelSwitch();
    }
}
