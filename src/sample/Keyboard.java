package sample;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Stefan on 21.06.15.
 */
public class Keyboard implements KeyListener
{
    private static boolean[] Keys = new boolean[1024];

    public static boolean isKeyDown(int KeyCode)
    {
        if(KeyCode>=0&&KeyCode<Keys.length)
        {
            return Keys[KeyCode];
        }else return false;
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        int KeyCode = e.getKeyCode();
        if(KeyCode>=0&&KeyCode<Keys.length)
        {
            Keys[KeyCode] = false;
        }
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        int KeyCode = e.getKeyCode();
        if(KeyCode>=0&&KeyCode<Keys.length)
        {
            Keys[KeyCode] = true;
        }

    }


    @Override
    public void keyTyped(KeyEvent e)
    {

    }
}
