import javax.swing.*;
import java.awt.CardLayout;

public class MainFrame extends JFrame {

    private final MainMenu mainMenu;
    private final Game game;
    private final Highscores highscores;
    private final Help help;
    private final JPanel cards;
    public MainFrame() {

        setResizable(false);
        setFocusable(true);
        setTitle("Space Invaders");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(200,100,1000,600);

        cards = new JPanel(new CardLayout());
        mainMenu = new MainMenu();
        game = new Game();
        highscores = new Highscores();
        help = new Help();

        addPanels();
        setUpListeners();

        getContentPane().add(cards);
        setVisible(true);
    }

    private void addPanels() { // Adds the Panels to the cardlayout
        cards.add(mainMenu, "Menu");
        cards.add(game,"Game");
        cards.add(highscores,"Highscores");
        cards.add(help,"Help");
    }

    private void setUpListeners() { // Sets up the buttons from the main menu to be able to switch around the windows using cardlayout.
        mainMenu.play.addActionListener(e -> {
            CardLayout c1;
            c1 = (CardLayout) cards.getLayout();
            c1.show(cards,"Game");
            game.requestFocus(true);
            game.ingame = true;
        });

        game.leave.addActionListener(e -> {
            CardLayout c1;
            c1 = (CardLayout) cards.getLayout();
            c1.show(cards,"Menu");
            highscores.checkScore(game.scoreCount);
        });

        mainMenu.highScores.addActionListener(e -> {
            CardLayout c1;
            c1 = (CardLayout) cards.getLayout();
            c1.show(cards,"Highscores");
            highscores.requestFocus(true);
        });

        highscores.leave.addActionListener(e -> {
            CardLayout c1;
            c1 = (CardLayout) cards.getLayout();
            c1.show(cards,"Menu");
        });

        mainMenu.help.addActionListener(e -> {
            CardLayout c1;
            c1 = (CardLayout) cards.getLayout();
            c1.show(cards,"Help");
            help.requestFocus(true);
        });

        help.leave.addActionListener(e -> {
            CardLayout c1;
            c1 = (CardLayout) cards.getLayout();
            c1.show(cards,"Menu");
        });

        mainMenu.exit.addActionListener(e -> dispose());
    }

    public static void main(String[] args) {
        new MainFrame();
    }
}
