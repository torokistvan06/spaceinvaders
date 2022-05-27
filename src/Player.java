import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player {
    private Integer x, lives;
    private final Integer y;
    private final Integer speed;
    private BufferedImage img, smallImg;
    private boolean moveLeft, moveRight;
    private boolean isVisible, isDying;

    public Player(Integer x, Integer y, Integer speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        lives = 3;
        try {
            img = ImageIO.read(new File("G:\\BBTE Info\\III. felev\\Java\\Projekt\\Player.png"));
        } catch (IOException e) {
            System.out.println("Error");
        }
        try {
            smallImg = ImageIO.read(new File("G:\\BBTE Info\\III. felev\\Java\\Projekt\\PlayerSmall.png"));
        } catch (IOException e) {
            System.out.println("Error");
        }
        moveLeft = false;
        moveRight = false;
        isVisible = true;
        isDying = false;
    }

    public void draw(Graphics g) {
        if (isVisible)
            g.drawImage(img, x, y, null);
        if (isDying)
            isVisible = false;

        for (int i = 0; i < lives; i++) {
            g.drawImage(smallImg, 30 * (i + 1), 25, null);
        }
    }

    public void move() {
        if (moveLeft) {
            x -= speed;
        }
        if (moveRight) {
            x += speed;
        }
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    public Integer getLives() {
        return lives;
    }

    public void setLives(Integer lives) {
        this.lives = lives;
    }

    public void setDying(boolean dying) {
        isDying = dying;
    }

    public void setMoveLeft(boolean moveLeft) {
        this.moveLeft = moveLeft;
    }

    public void setMoveRight(boolean moveRight) {
        this.moveRight = moveRight;
    }
}
