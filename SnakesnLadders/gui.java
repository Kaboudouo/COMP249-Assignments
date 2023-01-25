package SnakesnLadders;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.Timer;
import java.awt.*;

public class gui extends JPanel {

    public gui(){

        int width = 800;
        int height = 600;

        setSize(width, height);
        setLayout(null);
    }

    public void drawPlayer(Player player){
        ImageIcon image = new ImageIcon("rsc/3ds111.png");

        JLabel playerLabel = new JLabel();
        playerLabel.setText(player.name);
        playerLabel.setIcon(image);
        playerLabel.setHorizontalTextPosition(JLabel.CENTER);;
        playerLabel.setVerticalTextPosition(JLabel.TOP);

        //playerLabel.setLocation(300, 500);

        PlayLadderAndSnake.frame.add(playerLabel);
        PlayLadderAndSnake.frame.setComponentZOrder(playerLabel, 0);





        // ImageIcon image = new ImageIcon("rsc/heart.png");

        // JLabel label = new JLabel();
        // label.setText(orderedPlayers.get(0).name);
        // label.setIcon(image);
        // label.setHorizontalTextPosition(JLabel.CENTER);;
        // label.setVerticalTextPosition(JLabel.TOP);

        // JFrame frame = new JFrame();
        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // frame.setSize(250, 250);
        // frame.setVisible(true);
        // frame.add(label);
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        setBackground(Color.DARK_GRAY);
    }
}
