package graphics_programs;
import java.awt.*;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class KochFractal extends javax.swing.JPanel implements GraphicsProgram {
    public KochFractal() {
         JFrame frame = new JFrame("Koch Example");
         this.panel = this;
         setSize(PANEL_WIDTH, PANEL_HEIGHT); 
    }
    int height;
    int width;
    int level = 5;
    private JPanel panel;

    private static final int PANEL_WIDTH = 400;
    private static final int PANEL_HEIGHT = 400;

    private boolean completed = false;

    @Override
    public void paint(Graphics g){
        height = this.getHeight() - this.getHeight()/4;
        width = this.getWidth();
        int xStart = width/2 - height/2;
        drawKochLine(g, level, xStart + 20, height - 20,   xStart + height - 20, height - 20);
    }

    // Recursively called on each segment of the Koch curve
    private void drawKochLine (Graphics g, int lev, int x1, int y1, int x5, int y5){
        int deltaX, deltaY, x2, y2, x3, y3, x4, y4;

        if (lev == 0){
            // Draw a solid line
            g.drawLine(x1, y1, x5, y5);
        } else{
            // Total delta
              deltaX = x5 - x1;
              deltaY = y5 - y1;
              
              // Second point of the new line
              x2 = x1 + deltaX / 3;
              y2 = y1 + deltaY / 3;

              // Third point of the new line
              x3 = (int) (0.5 * (x1+x5) + Math.sqrt(3) * (y1-y5)/6);
              y3 = (int) (0.5 * (y1+y5) + Math.sqrt(3) * (x5-x1)/6);

              // Fourth point of the new line
              x4 = x1 + 2 * deltaX /3;
              y4 = y1 + 2 * deltaY /3;

              // Now draw each sub-segment of the Kock curve
              drawKochLine (g,lev-1, x1, y1, x2, y2); // 
              drawKochLine (g,lev-1, x2, y2, x3, y3);
              drawKochLine (g,lev-1, x3, y3, x4, y4);
              drawKochLine (g,lev-1, x4, y4, x5, y5);
          }
      }

       @Override
    public JPanel getProgramPanel() {
        return panel;
    }

    @Override
    public String getProgramName() {
        return "Koch Fractal";
    }

    @Override
    public Dimension getDimension() {
        return new Dimension(PANEL_WIDTH, PANEL_HEIGHT);
    }

    @Override
    public ImageIcon getIcon() {
        return new javax.swing.ImageIcon("candy/images.png");
    }


    @Override
    public Boolean getCompletedStatus() {
        return completed;
    }

    @Override
    public String getInfoFileName() {
        return "info/dda.txt";
    }
}
