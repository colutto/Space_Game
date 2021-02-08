package sample;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.text.AttributedCharacterIterator;

/**
 * Created by Stefan on 21.06.15.
 */
public class Level_1 extends Frame
{
    static BufferedImage Level1;
    static float pos_x;
    static float pos_y;
    static float speed_x = 60;
    static float Time_Since_Last_Frame;//aktuelle Zeit zum Nachladen der bullets



    static BufferStrategy strat;

    static
    {
        try
        {
            Level1 = ImageIO.read(Level_1.class.getClassLoader().getResourceAsStream("Bilder/weltraum.png"));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    public Level_1()
    {
        super("Spiel");
        setSize(1000,600);
        setUndecorated(true);
        setResizable(false);
        setLocationRelativeTo(null);
        addKeyListener(new Keyboard());
        setVisible(true);

        Player.pos_x=10;
        Player.pos_y=10;
    }
    public static void update(float Time_Since_Last_Frame)
    {
        if(pos_x>-2000)
        {
            pos_x -= speed_x * Time_Since_Last_Frame;
        }else
        {
            pos_x=0;
        }
    }

    public void MakeStrat()
    {
        createBufferStrategy(3);
        strat = getBufferStrategy();
    }

    public static void Paint()
    {
        Graphics h = strat.getDrawGraphics();
        draw(h);
        h.dispose();
        strat.show();
    }

    private static void draw(Graphics h)
    {
        h.drawImage(Level1,(int)pos_x,(int)pos_y,null);//Level zeichnen

        for(int i = 0; i<bullets.getBulletListe().size();i++)//Bullets zeichnen

        {
            bullets kugel = bullets.getBulletListe().get(i);
            h.drawImage(kugel.getBullet(), (int) kugel.getPos_x(), (int) kugel.getPos_y(), null);
        }

        for (int i = 0; i < Enemy_1.getListEnemy().size(); i++)//Enemy zeichnen
        {
            Enemy_1 t=Enemy_1.getListEnemy().get(i);
            h.drawImage(t.getEnemy(), (int) t.getPos_x(), (int) t.getPos_y(), null);
        }
        for (int i = 0; i < Enemy_1.getListEnemy().size(); i++)//Enemy zeichnen
        {
            Enemy_1 j=Enemy_1.getListEnemy().get(i);
            if (j.isGelöscht())
            {
                h.drawImage(j.getBild(), (int) j.getPos_x(), (int) j.getPos_y(), null);
                Enemy_1.getListEnemy().remove(j);
            }
        }

        for (int i = 0; i < Enemy_2.getListEnemy_2().size(); i++)//Enemy zeichnen
        {
            Enemy_2 t=Enemy_2.getListEnemy_2().get(i);
            h.drawImage(Enemy_2.getEnemy(), (int) t.getPos_x(), (int) t.getPos_y(), null);
        }
        for (int i = 0; i < Enemy_2.getListEnemy_2().size(); i++)//Enemy zeichnen
        {
            Enemy_2 j=Enemy_2.getListEnemy_2().get(i);
            if (j.isGelöscht())
            {
                h.drawImage(Enemy_2.getEnemy_explosion(), (int) j.getPos_x(), (int) j.getPos_y(), null);
                Enemy_2.getListEnemy_2().remove(j);
            }
        }

        h.drawImage(Player.getPlayerLook(),(int)Player.getPos_x(),(int)Player.getPos_y(),null);//Player zeichnen

        if (Player.isBantrieb())//Antrieb zeichnen
        {
            h.drawImage(Player.getAntrieb2(),(int)Player.getPos_x(),(int)Player.getPos_y(),null);
        }
        if (Player.isBantriebLinks())//AntriebLinks zeichnen
        {
            h.drawImage(Player.getAntriebLinks2(),(int)Player.getPos_x(),(int)Player.getPos_y(),null);
        }
        if (Player.isBantriebRechts())//AntriebREchts zeichnen
        {
            h.drawImage(Player.getAntriebRechts2(),(int)Player.getPos_x(),(int)Player.getPos_y(),null);
        }

    }

    public static void setTime_Since_Last_Frame(float time_Since_Last_Frame)//Methode zum Nachladen der bullets
    {
        Time_Since_Last_Frame = time_Since_Last_Frame;
    }

}
