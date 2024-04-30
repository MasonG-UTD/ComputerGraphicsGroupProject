package graphics_programs;
import java.awt.*;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class KochFractal extends javax.swing.JPanel implements GraphicsProgram {
    int height;
    int width;
    int level = 3;
    private JPanel panel;

    private static final int PANEL_WIDTH = 400;
    private static final int PANEL_HEIGHT = 400;

    private boolean completed = false;
    
    public KochFractal() {
         JFrame frame = new JFrame("Koch Example");
         this.panel = this;
         setSize(PANEL_WIDTH, PANEL_HEIGHT); 
    }

    @Override
    public void paint(Graphics g){
        height = this.getHeight() - this.getHeight()/4;
        width = this.getWidth();
        int xStart = 50;
        drawKochLineSegment(g, level, xStart, height - 80,   xStart + width - 60, height - 80);
    }

    // Recursively called on each segment of the Koch curve
    private void drawKochLineSegment (Graphics g, int lev, int x1, int y1, int x5, int y5){
        int deltaX, deltaY;
        int x2, y2;
        int x3, y3;
        int x4, y4;

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
              drawKochLineSegment (g,lev-1, x1, y1, x2, y2);
              drawKochLineSegment (g,lev-1, x2, y2, x3, y3);
              drawKochLineSegment (g,lev-1, x3, y3, x4, y4);
              drawKochLineSegment (g,lev-1, x4, y4, x5, y5);
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
