package sample;

import java.awt.event.KeyEvent;

/**
 * Created by Stefan on 18.06.15.
 */
public class Main
{
    public static void main (String[]args)
    {
        Menü Startbildschirm = new Menü();
        new Player();

        Enemy_1.getExplosion()[0]=Enemy_1.getEnemyLöschen1();
        Enemy_1.getExplosion()[1]=Enemy_1.getEnemyLöschen2();
        Enemy_1.getExplosion()[2]=Enemy_1.getEnemyLöschen3();

        Player.getBoundingPlayer()[0]=Player.getHinten();
        Player.getBoundingPlayer()[1]=Player.getVorne();


        long Time_Frame = System.currentTimeMillis();
        while(true)
        {

            if(Keyboard.isKeyDown(KeyEvent.VK_ESCAPE)&&Menü.isLevel_1anzeigen())
            {
                Menü.setLevel_1anzeigen(false);
                Startbildschirm.getLevel().dispose();
                bullets.getBulletListe().clear();
                Enemy_1.getListEnemy().clear();
            }

            long Time_nextFrame = System.currentTimeMillis();
            float Time_Since_Last_Frame = (float)(Time_nextFrame-Time_Frame)/1000;
            Time_Frame = Time_nextFrame;
            Level_1.setTime_Since_Last_Frame(Time_Since_Last_Frame);

            Startbildschirm.zeichnen.repaint();
            Startbild.update(Time_Since_Last_Frame);


            if(Menü.isLevel_1anzeigen())
            {
                new Thread()
                {
                    @Override
                    public void run()
                    {
                        Level_1.Paint();
                    }
                }.start();

                Enemy_1.EnemyAnzahl();
                Enemy_2.EnemyAnzahl();
                Level_1.update(Time_Since_Last_Frame);
                Player.update(Time_Since_Last_Frame);
                for (int i = 0; i < bullets.getBulletListe().size(); i++)
                {
                    bullets.getBulletListe().get(i).bulletLaden(Time_Since_Last_Frame);
                }
                for (int i = 0; i < Enemy_1.getListEnemy().size(); i++)
                {
                    Enemy_1.getListEnemy().get(i).update(Time_Since_Last_Frame);
                }
                for (int i = 0; i < Enemy_2.getListEnemy_2().size(); i++)
                {
                    Enemy_2.getListEnemy_2().get(i).Enemy_2upload(Time_Since_Last_Frame);
                }
            }
            try
            {
                Thread.sleep(15);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}
