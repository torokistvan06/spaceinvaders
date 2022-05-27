import java.awt.*;
import java.util.Random;

public class EnemyWave {
    private final Integer rows, columns;
    private final Enemy[][] enemyWave;
    private Integer direction;
    private Integer aliveCount;
    private boolean allDead;
    private final Integer size;

    public EnemyWave() {
        Random rand = new Random();
        direction = 1;
        rows = rand.nextInt(1, 5);
        columns = rand.nextInt(8, 16);
        enemyWave = new Enemy[columns][rows];
        aliveCount = rows * columns;
        size = rows * columns;
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                enemyWave[i][j] = new Enemy(i + 1, j + 1, rows + 1);
            }
        }
    }

    public Enemy findHit(Shot shot) {
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                if (shot.getX() >= enemyWave[i][j].getX() && shot.getX() <= (enemyWave[i][j].getX() + 30) && shot.getY() >= enemyWave[i][j].getY() && shot.getY() <= (enemyWave[i][j].getY() + 30) && enemyWave[i][j].isDying()) {
                    return enemyWave[i][j]; // Collision detection;
                }
            }
        }
        return null;
    }

    public Enemy playerCollision(Player player) {
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                if (player.getX() >= enemyWave[i][j].getX() && player.getX() <= (enemyWave[i][j].getX() + 30) && player.getY() >= enemyWave[i][j].getY() && player.getY() <= (enemyWave[i][j].getY() + 30) && enemyWave[i][j].isDying()) {
                    return enemyWave[i][j]; // Collision detection;
                }
            }
        }
        return null;
    }

    public int gotThrough() {
        int sum = 0;
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                if (enemyWave[i][j].getY() > 600 && enemyWave[i][j].isDying())
                    sum++;
            }
        }
        return sum;
    }

    public void draw(Graphics g) {
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                enemyWave[i][j].draw(g);
            }
        }
    }

    public void move() {
        if (enemyWave[rows - 1][0].getY() < 75)
            spawnAnim();
        if (direction == 1)
            moveRight();
        else
            moveLeft();
    }

    private void moveRight() {
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                if (enemyWave[columns - 1][j].getX() < 950) {
                    enemyWave[i][j].setX(enemyWave[i][j].getX() + enemyWave[i][j].getSpeed());
                } else {
                    direction = -1;
                }
            }
        }
    }

    private void moveLeft() {
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                if (enemyWave[0][j].getX() > 50) {
                    enemyWave[i][j].setX(enemyWave[i][j].getX() - enemyWave[i][j].getSpeed());
                } else {
                    direction = 1;
                }
            }
        }
    }

    private void spawnAnim() {
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                enemyWave[i][j].setY(enemyWave[i][j].getY() + 2);
            }
        }
    }

    public void moveDown() {
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                enemyWave[i][j].setY(enemyWave[i][j].getY() + 50);
            }
        }
    }

    public Integer getAliveCount() {
        return aliveCount;
    }

    public Integer getSize() {
        return size;
    }

    public boolean isAllDead() {
        return allDead;
    }

    public void setAliveCount(Integer aliveCount) {
        this.aliveCount = aliveCount;
    }

    public void setAllDead() {
        allDead = true;
    }
}
