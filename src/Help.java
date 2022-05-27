import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Help extends JPanel {

    public JButton leave;
    public JTextField text, text1, text2, text3, text4, text5;

    public Help() {
        addKeyListener(new Adapter());
        leave = new JButton();
        setBackground(Color.black);
        setFont(new Font("Help", Font.BOLD, 25));
        setFocusable(true);
        setLayout(new GridLayout(7, 1));

        initFields();
        addFields();
    }

    public void addFields() {
        add(text);
        add(text1);
        add(text2);
        add(text3);
        add(text4);
        add(text5);
    }

    public void initFields() {
        text = new JTextField("Controls:");
        text.setFont(new Font("Help2", Font.BOLD, 25));
        text.setForeground(Color.green);
        text.setBackground(Color.black);
        text.setBorder(null);
        text.setEditable(false);
        text.setHorizontalAlignment(JTextField.CENTER);

        text1 = new JTextField("A - Move left");
        text1.setFont(new Font("Help2", Font.BOLD, 20));
        text1.setForeground(Color.green);
        text1.setBackground(Color.black);
        text1.setBorder(null);
        text1.setEditable(false);
        text1.setHorizontalAlignment(JTextField.CENTER);

        text2 = new JTextField("D - Move right");
        text2.setFont(new Font("Help2", Font.BOLD, 20));
        text2.setForeground(Color.green);
        text2.setBackground(Color.black);
        text2.setBorder(null);
        text2.setEditable(false);
        text2.setHorizontalAlignment(JTextField.CENTER);

        text3 = new JTextField("Space - Shoot");
        text3.setFont(new Font("Help2", Font.BOLD, 20));
        text3.setForeground(Color.green);
        text3.setBackground(Color.black);
        text3.setBorder(null);
        text3.setEditable(false);
        text3.setHorizontalAlignment(JTextField.CENTER);

        text4 = new JTextField("Esc - Leave current menu");
        text4.setFont(new Font("Help2", Font.BOLD, 20));
        text4.setForeground(Color.green);
        text4.setBackground(Color.black);
        text4.setBorder(null);
        text4.setEditable(false);
        text4.setHorizontalAlignment(JTextField.CENTER);

        text5 = new JTextField("Rules: Don't let the invaders reach the Earth, shoot as many as you can, they will be coming in waves,\n you can defend the Earth with your own ship but you only got 3 lives! Good luck!");
        text5.setFont(new Font("Help2", Font.BOLD, 10));
        text5.setForeground(Color.green);
        text5.setBackground(Color.black);
        text5.setHorizontalAlignment(JTextField.CENTER);
        text5.setBorder(null);
        text5.setEditable(false);
    }

    private class Adapter extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            int code = e.getKeyCode();
            if (code == 27) {
                leave.doClick();
            }
        }
    }

}