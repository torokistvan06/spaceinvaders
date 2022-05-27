import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainMenu extends JPanel {
    public final JButton play, highScores, help, exit;
    private BufferedImage name;

    public MainMenu() {
        setLayout(null);
        setFocusable(true);
        setBackground(Color.BLACK);

        play = new JButton("Play!");
        highScores = new JButton("Highscores");
        help = new JButton("Help");
        exit = new JButton("Exit to Desktop");

        try {
            name = ImageIO.read(new File("G:\\BBTE Info\\III. felev\\Java\\Projekt\\SpaceInvaders.jpg"));
        } catch (IOException e) {
            System.out.println("Error");
        }

        initButtons();
        addButtons();
    }

    private void addButtons() {
        add(play);
        add(highScores);
        add(help);
        add(exit);
    }

    private void initButtons() {
        play.setBounds(250, 250, 500, 50);
        highScores.setBounds(250, 325, 500, 50);
        help.setBounds(250, 400, 500, 50);
        exit.setBounds(250, 475, 500, 50);

        Font font = new Font("Help", Font.BOLD, 25);
        play.setFont(font);
        highScores.setFont(font);
        help.setFont(font);
        exit.setFont(font);

        play.setForeground(Color.green);
        highScores.setForeground(Color.green);
        help.setForeground(Color.green);
        exit.setForeground(Color.green);

        play.setOpaque(false);
        play.setContentAreaFilled(false);
        play.setBorderPainted(false);

        highScores.setOpaque(false);
        highScores.setContentAreaFilled(false);
        highScores.setBorderPainted(false);

        help.setOpaque(false);
        help.setContentAreaFilled(false);
        help.setBorderPainted(false);

        exit.setOpaque(false);
        exit.setContentAreaFilled(false);
        exit.setBorderPainted(false);

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(name, 250, 25, null);
    }
}
