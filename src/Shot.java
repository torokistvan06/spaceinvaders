import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class Shot {
    private final Integer x;
    private Integer y;
    private final Integer speed;
    private BufferedImage img;
    private boolean isVisible, isDying;


    public Shot(Integer x, Integer y) {
        this.x = x;
        this.y = y;
        this.speed = 12;
        try {
            img = ImageIO.read(new File("G:\\BBTE Info\\III. felev\\Java\\Projekt\\Laser.png"));
        } catch (IOException e) {
            System.out.println("Error");
        }
        isVisible = true;
        isDying = false;
        if (x != -1)
            playSound();
    }

    public void playSound() {
        File in = new File("G:\\BBTE Info\\III. felev\\Java\\Projekt\\shoot.wav");
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(in.toURI().toURL());
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics g) {
        if (isVisible)
            g.drawImage(img, x, y, null);
        if (isDying)
            isVisible = false;
    }

    public void move() {
        y -= speed;
        if (y < 0)
            isDying = true;
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public boolean isDying() {
        return isDying;
    }

    public void setDying(boolean dying) {
        isDying = dying;
    }
}
