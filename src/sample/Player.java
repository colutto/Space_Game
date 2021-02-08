package sample;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Created by Stefan on 21.06.15.
 */
public class Player
{
    static boolean bantrieb=false;//Entscheidung ob gezeichnet wird oder nicht
    static boolean bantriebLinks=false;//Entscheidung ob gezeichnet wird oder nicht
    static boolean bantriebRechts=false;//Entscheidung ob gezeichnet wird oder nicht

    static BufferedImage antrieb2;//Bild von Antrieb
    static BufferedImage antriebLinks2;//Bild von AntriebLinks
    static BufferedImage antriebRechts2;//Bild von AntriebRechts
    static BufferedImage Player;//Bild von Raumschiffsws

    static float pos_x;
    static float pos_y;
    static float speed_x = 180;
    static float speed_y = 180;
    static long aktuelleZeit=0;//bestimmt die Feuerrate

    static Rectangle hinten;
    static Rectangle vorne;

    static Rectangle[] boundingPlayer=new Rectangle[2];

    static
    {
        try
        {
            Player = ImageIO.read(Player.class.getClassLoader().getResourceAsStream("Bilder/Raumschiff/raumschiff_2.png"));
            antrieb2 = ImageIO.read(Player.class.getClassLoader().getResourceAsStream("Bilder/Raumschiff/raumschiff_2.antrieb2.png"));
            antriebLinks2 = ImageIO.read(Player.class.getClassLoader().getResourceAsStream("Bilder/Raumschiff/raumschiff_2.antriebLinks2.png"));
            antriebRechts2 = ImageIO.read(Player.class.getClassLoader().getResourceAsStream("Bilder/Raumschiff/raumschiff_2.antriebRechts2.png"));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    public Player()
    {
        pos_x = 10;
        pos_y = 10;

        hinten=new Rectangle();
        hinten.setBounds((int)pos_x,(int)pos_y,Player.getWidth()/2,Player.getHeight());

        vorne=new Rectangle();
        vorne.setBounds(Player.getWidth()/2,(int)pos_y+27,Player.getWidth()/2,32);
    }


    public static BufferedImage getPlayerLook()
    {
        return Player;
    }

    public static float getPos_x()
    {
        return pos_x;
    }

    public static float getPos_y()
    {
        return pos_y;
    }

    public static boolean isBantrieb()
    {
        return bantrieb;
    }

    public static boolean isBantriebLinks()
    {
        return bantriebLinks;
    }

    public static boolean isBantriebRechts()
    {
        return bantriebRechts;
    }

    public static BufferedImage getAntrieb2()
    {
        return antrieb2;
    }

    public static BufferedImage getAntriebLinks2()
    {
        return antriebLinks2;
    }

    public static BufferedImage getAntriebRechts2()
    {
        return antriebRechts2;
    }

    public static Rectangle getHinten()
    {
        return hinten;
    }

    public static Rectangle getVorne()
    {
        return vorne;
    }

    public static Rectangle[] getBoundingPlayer()
    {
        return boundingPlayer;
    }

    public static void update(float Time_Since_Last_Frame)//Position vom Spieler updaten
    {
        if(Keyboard.isKeyDown(KeyEvent.VK_W)&&(pos_y>=0))
        {
            pos_y -= speed_y * Time_Since_Last_Frame;
            bantriebLinks=true;
        }else bantriebLinks=false;

        if(Keyboard.isKeyDown(KeyEvent.VK_S)&&(pos_y+Player.getHeight())<=600)
        {
            pos_y += speed_y * Time_Since_Last_Frame;
            bantriebRechts=true;
        }else bantriebRechts=false;

        if(Keyboard.isKeyDown(KeyEvent.VK_D)&&(pos_x+Player.getWidth())<=1000)
        {
            pos_x += speed_x * Time_Since_Last_Frame;
            bantrieb=true;
        }else bantrieb=false;

        if(Keyboard.isKeyDown(KeyEvent.VK_A)&&(pos_x>=0))
        {
            pos_x -= speed_x * Time_Since_Last_Frame;
            bantriebLinks=true;
            bantriebRechts=true;
        }

        if (Keyboard.isKeyDown(KeyEvent.VK_SPACE))
        {
            if (System.currentTimeMillis()>aktuelleZeit+150)
            {
                aktuelleZeit = System.currentTimeMillis();
                bullets.bulletListe.add(new bullets());
            }
        }

        hinten.setLocation((int)pos_x,(int)pos_y);
        vorne.setLocation(Player.getWidth()/2,(int)pos_y+28);
    }
}