package sample;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


/**
 * Created by Stefan on 18.06.15.
 */
public class Menü extends JFrame
{
    private JLabel Start;
    private JLabel Highscore;
    private JLabel Beenden;

    private Font Schriftart = new Font("TimesRoman",Font.CENTER_BASELINE,25);

    Screen zeichnen;

    Color Schriftfarbe = new Color(34, 101, 1);
    Color Schriftfarbe2 = new Color(47, 255, 0);

    private JPanel Schrift;

    private Mouse MouseListener;

    Level_1 level;


    static boolean Level_1anzeigen = false;    //Schaltet das erste Level frei

    public Menü()
    {
        super("Menü");
        setSize(600,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        addKeyListener(new Keyboard());

        Container c = getContentPane();

        MouseListener = new Mouse();

        zeichnen = new Screen();
        zeichnen.setLayout(null);
        c.add(zeichnen);

        Start = new JLabel("Spiel starten",SwingConstants.CENTER);
        Start.setBounds(230,50,140,Schriftart.getSize());
        Start.setFont(Schriftart);
        Start.setForeground(Schriftfarbe);
        Start.addMouseListener(MouseListener);


        Highscore = new JLabel("Highscore",SwingConstants.CENTER);
        Highscore.setBounds(245,176,112,Schriftart.getSize());
        Highscore.setFont(Schriftart);
        Highscore.setForeground(Schriftfarbe);
        Highscore.addMouseListener(MouseListener);


        Beenden = new JLabel("Beenden",SwingConstants.CENTER);
        Beenden.setBounds(250,302,100,Schriftart.getSize());
        Beenden.setFont(Schriftart);
        Beenden.setForeground(Schriftfarbe);
        Beenden.addMouseListener(MouseListener);


        zeichnen.add(Start);
        zeichnen.add(Highscore);
        zeichnen.add(Beenden);

        setVisible(true);

    }
    public class Screen extends JLabel
    {
        protected void paintComponent(Graphics g)
        {
            g.drawImage(Startbild.getStartbildschirm(), (int) Startbild.getPos_x(), (int) Startbild.getPos_y(), null);
        }
    }

    public class Mouse implements MouseListener
    {

        @Override
        public void mouseClicked(MouseEvent e)
        {
            if(e.getSource()==Start)
            {
                level = new Level_1();
                level.MakeStrat();
                Level_1anzeigen = true;
            }
            if(e.getSource()==Beenden)
            {
                System.exit(0);
            }

        }

        @Override
        public void mousePressed(MouseEvent e)
        {

        }

        @Override
        public void mouseReleased(MouseEvent e)
        {

        }

        @Override
        public void mouseEntered(MouseEvent e)
        {
            if(e.getSource()==Start)
            {
                Start.setForeground(Schriftfarbe2);
            }
            if(e.getSource()==Highscore)
            {
                Highscore.setForeground(Schriftfarbe2);
            }
            if(e.getSource()==Beenden)
            {
                Beenden.setForeground(Schriftfarbe2);
            }
        }

        @Override
        public void mouseExited(MouseEvent e)
        {
            if(e.getSource()==Start)
            {
                Start.setForeground(Schriftfarbe);
            }
            if(e.getSource()==Highscore)
            {
                Highscore.setForeground(Schriftfarbe);
            }
            if(e.getSource()==Beenden)
            {
                Beenden.setForeground(Schriftfarbe);
            }
        }
    }

    public static boolean isLevel_1anzeigen()
    {
        return Level_1anzeigen;
    }

    public static void setLevel_1anzeigen(boolean level_1anzeigen)
    {
        Level_1anzeigen = level_1anzeigen;
    }

    public Level_1 getLevel()
    {
        return level;
    }
}
