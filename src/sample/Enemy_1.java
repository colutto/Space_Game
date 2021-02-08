package sample;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Created by Stefan on 21.06.15.
 */
public class Enemy_1
{
    static BufferedImage enemy;
    float pos_x;
    float pos_y;
    float speed_x;
    float speed_y;
    static long aktuelleZeit = 0l;
    Rectangle oben;
    Rectangle mitte;
    Rectangle unten;
    boolean Gelöscht = false;
    static BufferedImage enemyLöschen1;
    static BufferedImage enemyLöschen2;
    static BufferedImage enemyLöschen3;

    static BufferedImage[] explosion = new BufferedImage[3];

    static LinkedList<Enemy_1> listEnemy = new LinkedList<Enemy_1>();
    static final float ANIEXPLOSION=0.5f;
    float aniTime=0;


    static
    {
        try
        {
            enemy = ImageIO.read(Enemy_1.class.getClassLoader().getResourceAsStream("Bilder/Enemy_1/enemy01.png"));
            enemyLöschen1 = ImageIO.read(Enemy_1.class.getClassLoader().getResourceAsStream("Bilder/Enemy_1/explosion1.png"));
            enemyLöschen2 = ImageIO.read(Enemy_1.class.getClassLoader().getResourceAsStream("Bilder/Enemy_1/explosion2.png"));
            enemyLöschen3 = ImageIO.read(Enemy_1.class.getClassLoader().getResourceAsStream("Bilder/Enemy_1/explosion3.png"));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public Enemy_1()
    {
        speed_x = 130;
        speed_y = 0;
        pos_x = 1300;
        pos_y = Player.getPos_y();

        oben = new Rectangle();
        oben.setBounds((int) pos_x, (int) pos_y, 48, 8);

        mitte = new Rectangle();
        mitte.setBounds((int) pos_x + 7, (int) pos_y + 8, 47, 25);

        unten = new Rectangle();
        unten.setBounds((int) pos_x, (int) pos_y + 33, 48, 8);
    }

    public void update(float Time_Since_Last_Frame)//Bewegung des Gegners
    {
        pos_x -= speed_x * Time_Since_Last_Frame;
        pos_y += speed_y * Time_Since_Last_Frame;

        oben.setLocation((int) pos_x, (int) pos_y);
        mitte.setLocation((int) pos_x + 7, (int) pos_y + 8);
        unten.setLocation((int) pos_x, (int) pos_y + 33);

        if (pos_x < -enemy.getWidth())
        {
            pos_x = 1300;
        }
        aniTime+=Time_Since_Last_Frame;
        if (aniTime>ANIEXPLOSION)
        {
            aniTime=0;
        }
        if (!listEnemy.isEmpty())
        {
            for (int i = 0; i < Player.getBoundingPlayer().length; i++)
            {
                if ((this.oben.intersects(Player.getBoundingPlayer()[i])) | (this.mitte.intersects(Player.getBoundingPlayer()[i])) | (this.unten.intersects(Player.getBoundingPlayer()[i])))
                {
                    Gelöscht = true;
                }
            }
        }
    }

    public float getPos_x()
    {
        return pos_x;
    }

    public float getPos_y()
    {
        return pos_y;
    }

    public Rectangle getOben()
    {
        return oben;
    }

    public Rectangle getMitte()
    {
        return mitte;
    }

    public Rectangle getUnten()
    {
        return unten;
    }

    public boolean isGelöscht()
    {
        return Gelöscht;
    }

    public void setGelöscht(boolean gelöscht)
    {
        Gelöscht = gelöscht;
    }

    public static BufferedImage getEnemy()
    {
        return enemy;
    }

    public static LinkedList<Enemy_1> getListEnemy()
    {
        return listEnemy;
    }

    public static BufferedImage getEnemyLöschen1()
    {
        return enemyLöschen1;
    }

    public static BufferedImage getEnemyLöschen2()
    {
        return enemyLöschen2;
    }

    public static BufferedImage getEnemyLöschen3()
    {
        return enemyLöschen3;
    }

    public static BufferedImage[] getExplosion()
    {
        return explosion;
    }

    public static void EnemyAnzahl()//Bestimmt die Anzahl der Gegner in der Sekunde
    {
        if (System.currentTimeMillis() > aktuelleZeit + 500)
        {
            aktuelleZeit = System.currentTimeMillis();
            listEnemy.add(new Enemy_1());
        }
    }
    public BufferedImage getBild()
    {
        if (explosion.length==0) return null;
        for (int i = 0; i < explosion.length; i++)
        {
            if (aniTime<ANIEXPLOSION/explosion.length*(i+1))
                return explosion[i];
        }
        return explosion[explosion.length-1];
    }
}
