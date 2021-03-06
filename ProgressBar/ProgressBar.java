import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class ProgressBar extends JFrame implements Runnable {

    private boolean start = false;
    private boolean kill_trhead = false;
    private Thread thread = new Thread(this);
    private JProgressBar bar;
    private JButton[] events = new JButton[3];


    public ProgressBar(JPanel panel, int position_x, int position_y, int size_x, int size_y) {
   
        int button_size = 20;
        int new_sizeX = size_x - button_size;

        this.bar = new JProgressBar();
        this.bar.setLayout(null);
        this.bar.setBounds(position_x, position_y, new_sizeX, size_y);
        this.bar.setStringPainted(true);
        panel.add(bar);
        this.thread.start();

        JPanel panel_buttons = new JPanel();
        panel_buttons.setLayout(new GridLayout(events.length,1,2,2));
        panel_buttons.setBounds(position_x, size_y-button_sizeY, button_sizeX, button_sizeY);
        panel.add(panel_buttons);

        events[0] = new JButton();
        events[0].setText("Start"); 
        panel_buttons.add(events[0]);

        
    }

    public void start() {
        start = true;
    }

    public void stop() {
        start = false;
    }

    public void kill() {
        kill_trhead = true;
    }

    @Override
    public void run() {

        while (!kill_trhead) {// starting thread
            while (start) {
                for (int i = 0; i < 100; i++) {
                    bar.setValue(i + 1);
                    bar.setString(String.valueOf(i + 1) + "%");
                    try {
                        Thread.sleep(100);// speed
                    } catch (Exception e) {
                    }
                }
            }

        }

    }

    public static void main(String[] args) {

        // creating a new frame
        JFrame frame = new JFrame();
        frame.setSize(700, 400);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        // creating a new panel
        JPanel panel = new JPanel(null);
        panel.setBounds(0, 0, 700, 400);
        frame.getContentPane().add(panel);

        // creating 1 bar

        ProgressBar bar1 = new ProgressBar(panel, 50, 0, 250, 50);
        ProgressBar bar2 = new ProgressBar(panel, 0, 110, 350, 80);
        bar1.start();
        bar2.start();

    }

}