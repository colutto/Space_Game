package sample;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Created by Stefan on 08.09.15.
 */
public class Enemy_2
{
    float pos_x;
    float pos_y;
    float speed_x;
    float speed_y;

    static LinkedList<Enemy_2> listEnemy_2=new LinkedList<>();
    static BufferedImage enemy;
    static BufferedImage enemy_explosion;

    static long aktuelleZeit=0;

    Rectangle vorne;
    Rectangle hinten;

    boolean gelöscht;




    static
    {
        try
        {
            enemy = ImageIO.read(Enemy_2.class.getClassLoader().getResourceAsStream("Bilder/Enemy_2/enemy_02.png"));

        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }



    public Enemy_2()
    {
        speed_x=130;
        speed_y=20;

        pos_x=1300;
        pos_y=Player.getPos_y();

        vorne=new Rectangle((int)pos_x,(int)pos_y+11,36,35);
        hinten=new Rectangle((int)pos_x+36,(int)pos_y,enemy.getWidth()-36,enemy.getHeight());
    }

    public float getPos_x()
    {
        return pos_x;
    }

    public float getPos_y()
    {
        return pos_y;
    }

    public static LinkedList<Enemy_2> getListEnemy_2()
    {
        return listEnemy_2;
    }

    public static BufferedImage getEnemy()
    {
        return enemy;
    }

    public static BufferedImage getEnemy_explosion()
    {
        return enemy_explosion;
    }

    public Rectangle getVorne()
    {
        return vorne;
    }

    public Rectangle getHinten()
    {
        return hinten;
    }

    public boolean isGelöscht()
    {
        return gelöscht;
    }

    public void setGelöscht(boolean gelöscht)
    {
        this.gelöscht = gelöscht;
    }

    public void Enemy_2upload(float Time_Since_Last_Frame)
    {
        pos_x-=speed_x*Time_Since_Last_Frame;
        pos_y+=speed_y*Time_Since_Last_Frame;

        vorne.setLocation((int)pos_x,(int)pos_y+11);
        hinten.setLocation((int)pos_x+36,(int)pos_y);

    }

    public static void EnemyAnzahl()//Bestimmt die Anzahl der Gegner in der Sekunde
    {
        if (System.currentTimeMillis() > aktuelleZeit + 500)
        {
            aktuelleZeit = System.currentTimeMillis();
            listEnemy_2.add(new Enemy_2());
            System.out.println(listEnemy_2.size());
        }
    }
}
