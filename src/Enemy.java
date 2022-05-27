import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Enemy {
    private Integer x, y;
    private final Integer speed;
    private BufferedImage img;
    private boolean isVisible, isDying, isExploded;

    public Enemy(int index, int row, int totalRow) {
        x = index * 50;
        y = -totalRow * 50 + row * 50;
        speed = 2;
        isVisible = true;
        isDying = false;
        try {
            img = ImageIO.read(new File("G:\\BBTE Info\\III. felev\\Java\\Projekt\\Enemy.png"));
        } catch (IOException e) {
            System.out.println("Error");
        }
    }

    public void draw(Graphics g) {
        if (isVisible)
            g.drawImage(img, x, y, null);
        if (isExploded)
            isVisible = false;
        if (isDying && !isExploded) {
            try {
                img = ImageIO.read(new File("G:\\BBTE Info\\III. felev\\Java\\Projekt\\Explosion.png"));
            } catch (IOException e) {
                System.out.println("Error");
            }
            g.drawImage(img, x, y, null);
            isExploded = true;
        }
    }

    public void deathSound() {
        File in = new File("G:\\BBTE Info\\III. felev\\Java\\Projekt\\enemyDeath.wav");

        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(in.toURI().toURL());
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    public Integer getSpeed() {
        return speed;
    }

    public boolean isDying() { return !isDying; }

    public void setY(Integer y) {
        this.y = y;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public void setDying(boolean dying) {
        isDying = dying;
    }
}
