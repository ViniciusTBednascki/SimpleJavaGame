import java.awt.*;

public class HUD {

    public static float health = 100;
    private double greenValue;
    private double redValue;

    public static int score = 0;
    public static int level = 1;

    public void tick(){
        health = Game.clamp(health,0,100);

        greenValue = health*2.55;
        redValue = 255 - health*2.55;

        if(health <= 0){}

        score++;
    }

    public void render(Graphics g){
        g.setColor(Color.gray);
        g.fillRect(15,15,200, 24);
        g.setColor(new Color((int)redValue,(int)greenValue,0));
        g.fillRect(15,15,(int)health*2,24);
        g.setColor(Color.white);
        g.drawRect(15,15,200, 24);
        g.drawString("Score: "+score, 15,64);
        g.drawString("Level: "+level,15,80);
    }

    public void reset(){
        health = 100;
        score = 0;
        level = 1;
    }

}
