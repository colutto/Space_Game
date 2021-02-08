package sample;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Created by Stefan on 27.08.15.
 */
public class bullets
{
    float pos_x;
    float pos_y;

    static float speed_x=400;

    static BufferedImage bullet;
    Rectangle bullet1;
    Rectangle bullet2;
    static LinkedList<bullets> bulletListe=new LinkedList();//Liste für bullets


    static
    {
        try
        {
            bullet = ImageIO.read(bullets.class.getClassLoader().getResourceAsStream("Bilder/bullets.png"));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public bullets()
    {
        pos_x=Player.getPos_x()+10;
        pos_y=Player.getPos_y()+8;

        bullet1=new Rectangle();
        bullet1.setBounds((int)pos_x,(int)pos_y,bullet.getWidth(),8);

        bullet2=new Rectangle();
        bullet2.setBounds((int)pos_x,(int)pos_y+61,bullet.getWidth(),8);
    }

    public void bulletLaden(float TimeSinceLastFrame)
    {
        pos_x+=speed_x*TimeSinceLastFrame;

        bullet1.setLocation((int)pos_x,(int)pos_y);
        bullet2.setLocation((int)pos_x,(int)pos_y+61);

        for (int i = 0; i < Enemy_1.getListEnemy().size(); i++)
        {
            if ((bullet1.intersects(Enemy_1.getListEnemy().get(i).getOben())) | (bullet1.intersects(Enemy_1.getListEnemy().get(i).getMitte())) | (bullet1.intersects(Enemy_1.getListEnemy().get(i).getUnten())))
            {
                Enemy_1.getListEnemy().get(i).setGelöscht(true);
                bulletListe.remove(this);
            } else if ((bullet2.intersects(Enemy_1.getListEnemy().get(i).getOben())) | (bullet2.intersects(Enemy_1.getListEnemy().get(i).getMitte())) | (bullet2.intersects(Enemy_1.getListEnemy().get(i).getUnten())))
            {
                Enemy_1.getListEnemy().get(i).setGelöscht(true);
                bulletListe.remove(this);
            }
        }
        for (int i = 0; i < Enemy_2.getListEnemy_2().size(); i++)
        {
            if ((bullet1.intersects(Enemy_2.getListEnemy_2().get(i).getVorne())) | (bullet1.intersects(Enemy_2.getListEnemy_2().get(i).getHinten())))
            {
                Enemy_2.getListEnemy_2().get(i).setGelöscht(true);
                bulletListe.remove(this);
            } else if ((bullet2.intersects(Enemy_1.getListEnemy().get(i).getOben())) | (bullet2.intersects(Enemy_1.getListEnemy().get(i).getMitte())) | (bullet2.intersects(Enemy_1.getListEnemy().get(i).getUnten())))
            {
                Enemy_2.getListEnemy_2().get(i).setGelöscht(true);
                bulletListe.remove(this);
            }
        }
        if (pos_x>1000)
        {
            bulletListe.remove(this);
        }
    }

    public static BufferedImage getBullet()
    {
        return bullet;
    }

    public float getPos_x()
    {
        return pos_x;
    }

    public float getPos_y()
    {
        return pos_y;
    }

    public static LinkedList<bullets> getBulletListe()
    {
        return bulletListe;
    }

}
