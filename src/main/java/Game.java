import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable {

    public static final int width = 640, height = width/12*9;

    private Thread thread;
    private boolean running =false;

    private Handler handler;
    private Random r;
    private HUD hud;
    private Spawn spawn;
    private Menu menu;

    public enum STATE {
        Menu,
        Help,
        Game,
        End
    }

    public STATE gameState = STATE.Menu;

    public Game(){

        handler = new Handler();
        hud = new HUD();
        spawn = new Spawn(handler,hud);
        menu = new Menu(this, handler, spawn, hud);
        r = new Random();

        this.addKeyListener(new KeyInput(handler));
        this.addMouseListener(menu);

        new Window(width, height, "Let's build a game", this);

        if(gameState == STATE.Game) {
            handler.addObject(new Player(width / 2 - 32, height / 2 - 32, ID.Player, handler));
            spawn.start();
        }else{
            for(int i = 0; i < 20; i++){
                handler.addObject(new MenuParticle(r.nextInt(Game.width - 48) + 16, r.nextInt(Game.height - 64) + 16, ID.MenuParticle,handler));
            }
        }
    }

    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running){
            long now = System.nanoTime();
            delta += (now-lastTime) / ns;
            lastTime = now;
            while(delta >= 1){
                tick();
                delta--;
            }
            if(running){
                render();
            }
            frames++;

            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    public synchronized void stop(){
        try{
            thread.join();
            running = false;
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void tick(){
        handler.tick();
        if(gameState == STATE.Game) {
            hud.tick();
            spawn.tick();

            if(hud.health <= 0){
                handler.object.clear();
                gameState = STATE.End;
                for(int i = 0; i < 20; i++){
                    handler.addObject(new MenuParticle(r.nextInt(Game.width - 48) + 16, r.nextInt(Game.height - 64) + 16, ID.MenuParticle,handler));
                }
            }
        }else if(gameState == STATE.Menu  || gameState == STATE.End){
            menu.tick();
        }
    }

    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.black);
        g.fillRect(0, 0, width, height);

        handler.render(g);

        if(gameState == STATE.Game) {
            hud.render(g);
        }else if(gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End){
            menu.render(g);
        }

        g.dispose();
        bs.show();

    }

    public static float clamp(float var, float min, float max){
        if(var <= min){
            return min;
        }else if(var >= max){
            return max;
        }else {
            return var;
        }
    }

    public static void main(String[] args){
        new Game();
    }
}
