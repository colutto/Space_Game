package sample;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by Stefan on 20.06.15.
 */
public class Startbild
{
    static BufferedImage Bild;
    static float pos_x = 0;
    static float pos_y = 0;
    static float speed_x = 60;
    static float speed_Y = 60;

    static
    {
        try
        {
            Bild = ImageIO.read(Startbild.class.getClassLoader().getResourceAsStream("Bilder/Startbildschirm.png"));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }


    public static void update(float Time_Since_Last_Frame)
    {
        pos_x -=speed_x*Time_Since_Last_Frame;
        pos_y -=speed_Y*Time_Since_Last_Frame;

        if(pos_x<-Bild.getWidth()+600)
        {
            speed_x *= -1;
            pos_x = -Bild.getWidth()+600;
        }
        if(pos_x>0)
        {
            speed_x *= -1;
            pos_x = 0;
        }
        if(pos_y<-Bild.getHeight()+400)
        {
            speed_Y *= -1;
            pos_y = -Bild.getHeight()+400;
        }
        if(pos_y>0)
        {
            speed_Y *=-1;
            pos_y = 0;
        }
    }

    public static BufferedImage getStartbildschirm()
    {
        return Bild;
    }

    public static float getPos_x()
    {
        return pos_x;
    }

    public static float getPos_y()
    {
        return pos_y;
    }
}
