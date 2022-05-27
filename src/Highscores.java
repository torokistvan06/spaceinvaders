import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.Scanner;

public class Highscores extends JPanel {
    private final JTextField[] top5;
    private final JTextField text, help;
    public JButton leave;

    public Highscores() {
        addKeyListener(new Adapter());
        top5 = new JTextField[5];
        loadScores();
        text = new JTextField("Highscores:\n");
        help = new JTextField("Press ESC to go back");
        leave = new JButton();

        setFocusable(true);
        setBackground(Color.black);
        setLayout(new GridLayout(8, 1));

        initFields();
        addFields();
    }

    private class Adapter extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            int code = e.getKeyCode();
            if (code == 27) {
                leave.doClick();
            }
        }
    }

    private void addFields() {
        add(help);
        add(text);
        for (int i = 0; i < 5; i++) {
            add(top5[i]);
        }
    }

    private void initFields() {

        Font font = new Font("Help", Font.BOLD, 25);
        help.setFont(new Font("Help2", Font.BOLD, 15));
        help.setForeground(Color.green);
        help.setBackground(Color.black);
        help.setBorder(null);
        help.setEditable(false);

        text.setFont(font);
        text.setForeground(Color.green);
        text.setBackground(Color.black);
        text.setHorizontalAlignment(JTextField.CENTER);
        text.setBorder(null);
        text.setEditable(false);

        for (int i = 0; i < 5; i++) {
            top5[i].setFont(font);
            top5[i].setForeground(Color.green);
            top5[i].setBackground(Color.black);
            top5[i].setHorizontalAlignment(JTextField.CENTER);
            top5[i].setBorder(null);
            top5[i].setEditable(false);
        }
    }

    public void checkScore(Integer newScore) {
        Integer[] top5 = new Integer[5];
        try {
            Scanner scanner = new Scanner(new File("G:\\BBTE Info\\III. felev\\Java\\Projekt\\highscores.txt"));
            for (int i = 0; i < 5; i++) {
                top5[i] = scanner.nextInt();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 5; i++) {
            if (top5[i] < newScore) {
                System.arraycopy(top5, i + 1 - 1, top5, i + 1, 5 - (i + 1));
                top5[i] = newScore;
                break;
            }
        }

        saveScores(top5);
        reloadScores();
    }

    private void saveScores(Integer[] top5) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("G:\\BBTE Info\\III. felev\\Java\\Projekt\\highscores.txt", false));
            for (int i = 0; i < 5; i++) {
                bufferedWriter.write(top5[i].toString() + "\n");
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void reloadScores() {
        try {
            Scanner scanner = new Scanner(new File("G:\\BBTE Info\\III. felev\\Java\\Projekt\\highscores.txt"));
            for (int i = 0; i < 5; i++) {
                top5[i].setText(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadScores() {
        try {
            Scanner scanner = new Scanner(new File("G:\\BBTE Info\\III. felev\\Java\\Projekt\\highscores.txt"));
            for (int i = 0; i < 5; i++) {
                top5[i] = new JTextField(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
