package wavy;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SpeedTestLauncher {

    public static void main(String[] args) {

        final JFrame frame = new JFrame("Wavy!");
        final WavyPanel wp = new WavyPanel();

        frame.getContentPane().add(wp, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final Ticker t = new Ticker(wp);
        final Repainter r = new Repainter(wp);

        frame.pack();
        frame.setVisible(true);

        final Timer tickTimer = new Timer();
        final Timer paintTimer = new Timer();

        paintTimer.schedule(r, 1000, 50);
        tickTimer.schedule(t, 1000, 10);
    }

    private static class WavyPanel extends JPanel {

        /**
		 * 
		 */
		private static final long serialVersionUID = -980663413315181837L;
		private final Dimension size = new Dimension(640, 480);
        private int amplitude = 50;
        private int frequency = 5;

        private int x = 0;
        private double y = size.height / 2;
        private int yBase = 0;

        WavyPanel() {
            super(true);
        }
        
        protected void paintComponent(final Graphics g) {
            final Graphics2D g2 = (Graphics2D)g;
            g2.setColor(Color.WHITE);
            g2.fillRect(0, 0, size.width, size.height);
            g2.setColor(Color.BLACK);
            g2.fillOval(x, (int)y, 30, 30);

        }

        public Dimension getPreferredSize() {
            return size;
        }
        public Dimension getMinimumSize() {
            return size;
        }
        public Dimension getMaximumSize() {
            return size;
        }

        public void tick() {
            //Move a pixel to the right; loop over to the left when reaching edge
            x = (++x) % size.width;
            //Length of one full wave = panel width divided by frequency
            final int waveLength = size.width / frequency;
            //Incrementing yBase; capping off at wavelength
            yBase = (++yBase) % waveLength;

            //Normalizing to [0..1]
            final double normalized = (double)yBase / (double)waveLength;
            //Full wave at 2*pi, means...
            final double radians = normalized * Math.PI * 2;
            //Getting the sine
            final double sine = Math.sin(radians);
            //Multiplying with amplitude, add to center position and we have our y
            y = (int)(sine * amplitude) + size.height/2;
        }

    }

    private static class Ticker extends TimerTask {
        private final WavyPanel panel;
        Ticker(final WavyPanel panel) {
            this.panel = panel;
        }

        public void run() {
            panel.tick();
        }
    }

    private static class Repainter extends TimerTask {
        private final WavyPanel panel;
        Repainter(final WavyPanel panel) {
            this.panel = panel;
        }

        @Override
        public void run() {
            panel.repaint();
        }
    }
}
