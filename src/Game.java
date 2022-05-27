import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Game extends JPanel implements Runnable {
    private final Player p;
    private EnemyWave enemyWave;
    private Shot shot;
    private Thread animator;
    private BufferedImage img, gameOver;
    public JButton leave;
    public boolean ingame;
    public Integer scoreCount = 0;

    public Game() {
        addKeyListener(new Adapter());
        setFocusable(true);
        setLayout(null);

        try {
            img = ImageIO.read(new File("G:\\BBTE Info\\III. felev\\Java\\Projekt\\Background.png"));
            gameOver = ImageIO.read(new File("G:\\BBTE Info\\III. felev\\Java\\Projekt\\GameOver.png"));
        } catch (IOException e) {
            System.out.println("Error");
        }

        p = new Player(475, 500, 5);
        enemyWave = new EnemyWave();
        shot = new Shot(-1, -1); // Nem valid inicalizálás, hogy elejétől fogva halott-nak legyen tekintve

        leave = new JButton();

        if (animator == null) { // starts the thread running the game
            animator = new Thread(this);
            animator.start();
        }
    }

    private class Adapter extends KeyAdapter {
        public void keyReleased(KeyEvent e) {
            int code = e.getKeyCode();
            if (code == 65) {
                p.setMoveLeft(false);
            } else if (code == 68) {
                p.setMoveRight(false);
            }
        }

        public void keyPressed(KeyEvent e) {
            int code = e.getKeyCode();
            if (code == 65) {
                p.setMoveLeft(true);
            } else if (code == 68) {
                p.setMoveRight(true);
            } else if (code == 32) {
                if (shot == null || !shot.isVisible()) {
                    shot = new Shot(p.getX() + 22, p.getY() + 15);
                }
            } else if (code == 27) {
                leave.doClick();
                ingame = false;
            }
        }
    }

    public void update() {
        p.move();
        enemyWave.move();
        shot.move();
        updateShot();
        updatePlayer();
        updateWave();
    }

    public void updateWave() {
        int gotThrough = enemyWave.gotThrough();
        if (gotThrough > enemyWave.getSize() / 10) {
            ingame = false;
            repaint();
        }
    }

    public void updateShot() {
        Enemy hit = enemyWave.findHit(shot);
        if (hit != null && !shot.isDying()) {
            hit.setDying(true);
            hit.deathSound();
            shot.setDying(true);
            scoreCount += 10;
            enemyWave.setAliveCount(enemyWave.getAliveCount() - 1);
            if (enemyWave.getAliveCount() == 0)
                enemyWave.setAllDead();
        }
    }

    public void updatePlayer() {
        Enemy hit = enemyWave.playerCollision(p);
        if (hit != null) {
            p.setLives(p.getLives() - 1);
            hit.setDying(true);
            enemyWave.setAliveCount(enemyWave.getAliveCount() - 1);
            if (enemyWave.getAliveCount() == 0)
                enemyWave.setAllDead();
            if (p.getLives() == 0) {
                p.setDying(true);
                ingame = false;
                repaint();
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(img, 0, 0, null);
        g.setFont(new Font("ASD", Font.BOLD, 20));
        g.setColor(Color.green);
        g.drawString("Score: " + scoreCount, 800, 30);
        p.draw(g);
        enemyWave.draw(g);
        shot.draw(g);
        if (!ingame) {
            g.drawImage(gameOver, 350, 250, null);
            g.drawString("Press ESC to go back to main menu", 325, 325);
        }
    }

    @Override
    public void run() {
        int animationDelay = 15;
        int it = 0;
        while (true) {
            if (ingame) {
                repaint();
                update();
                it++;
                if (it % 100 == 0) {
                    scoreCount++;
                }
                if (it == enemyWave.getSize() * 15) {
                    enemyWave.moveDown();
                    it = 0;
                }
                if (enemyWave.isAllDead()) {
                    enemyWave = new EnemyWave();
                }
                try {
                    Thread.sleep(animationDelay);
                } catch (InterruptedException e) {
                    System.out.println("ErrorSleep");
                }
            } else {
                enemyWave = new EnemyWave();
                it = 0;
                scoreCount = 0;
                while (!ingame) {
                    try {
                        Thread.sleep(animationDelay);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
