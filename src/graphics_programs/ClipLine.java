package graphics_programs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// original submission by Ananya

public class ClipLine extends JPanel implements GraphicsProgram {
   private int x1, y1, x2, y2;
   private Rectangle clippingWindow;
   private JPanel panel;

   private static final int PANEL_WIDTH = 400;
   private static final int PANEL_HEIGHT = 400;

   private boolean completed = false;

   public ClipLine() {
      this.panel = this;
      setSize(PANEL_WIDTH, PANEL_HEIGHT);

      clippingWindow = new Rectangle(100, 100, 200, 200); // Define clipping window rectangle

      addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent e) {
            if (x1 == 0 && y1 == 0) {
               x1 = e.getX();
               y1 = e.getY();
            } else {
               x2 = e.getX();
               y2 = e.getY();
               clipAndDraw();
               x1 = y1 = x2 = y2 = 0; // Reset points after drawing
            }
         }
      });
   }

   private void clipAndDraw() {
      boolean accept = clipLine();

      Graphics g = getGraphics();
      Graphics2D g2d = (Graphics2D) g;
      Stroke solidStroke = new BasicStroke(1);
      Stroke dashedStroke = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{5}, 0);

      // Draw the line
      g2d.setColor(Color.BLACK);
      g2d.setStroke(solidStroke);
      g2d.drawLine(x1, y1, x2, y2);

      // Clip and draw the clipped part as dashed
      if (!accept) {
         g2d.setColor(Color.RED);
         g2d.setStroke(dashedStroke);
         g2d.drawLine(x1, y1, x2, y2);
      }
   }

   private boolean clipLine() {
      int code1 = computeCode(x1, y1, clippingWindow);
      int code2 = computeCode(x2, y2, clippingWindow);
      boolean accept = false;

      while (true) {
         if ((code1 == 0) && (code2 == 0)) {
            accept = true;
            break;
         } else if ((code1 & code2) != 0) {
            break;
         } else {
            int codeOut = (code1 != 0) ? code1 : code2;
            int x, y;

            if ((codeOut & 8) != 0) { // Top
               x = x1 + (x2 - x1) * (clippingWindow.y + clippingWindow.height - y1) / (y2 - y1);
               y = clippingWindow.y + clippingWindow.height;
            } else if ((codeOut & 4) != 0) { // Bottom
               x = x1 + (x2 - x1) * (clippingWindow.y - y1) / (y2 - y1);
               y = clippingWindow.y;
            } else if ((codeOut & 2) != 0) { // Right
               y = y1 + (y2 - y1) * (clippingWindow.x + clippingWindow.width - x1) / (x2 - x1);
               x = clippingWindow.x + clippingWindow.width;
            } else { // Left
               y = y1 + (y2 - y1) * (clippingWindow.x - x1) / (x2 - x1);
               x = clippingWindow.x;
            }

            if (codeOut == code1) {
               x1 = x;
               y1 = y;
               code1 = computeCode(x1, y1, clippingWindow);
            } else {
               x2 = x;
               y2 = y;
               code2 = computeCode(x2, y2, clippingWindow);
            }
         }
      }

      return accept;
   }

   // Compute the region code for a point relative to a rectangle
   private int computeCode(int x, int y, Rectangle rect) {
      int code = 0;
      if (x < rect.x) {
         code |= 1; // Left
      } else if (x > rect.x + rect.width) {
         code |= 2; // Right
      }
      if (y < rect.y) {
         code |= 4; // Bottom
      } else if (y > rect.y + rect.height) {
         code |= 8; // Top
      }
      return code;
   }

   @Override
   public void paint(Graphics g) {
      super.paint(g);
      g.setColor(Color.BLACK);
      g.drawRect(clippingWindow.x, clippingWindow.y, clippingWindow.width, clippingWindow.height);
   }

   @Override
   public JPanel getProgramPanel() {
      return this.panel;
   }

   @Override
   public String getProgramName() {
      return "clip line";
   }

   @Override
   public ImageIcon getIcon() {
      return new javax.swing.ImageIcon("candy/images.png");
   }

   @Override
   public Dimension getDimension() {
      return new Dimension(PANEL_WIDTH, PANEL_HEIGHT);
   }

   @Override
   public Boolean getCompletedStatus() {
      return completed;
   }

   @Override
   public String getInfoFileName() {
      return "info/clip.txt";
   }
}
