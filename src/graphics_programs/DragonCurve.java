
package graphics_programs;

import java.awt.*;
import java.awt.event.*;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DragonCurve extends JPanel implements GraphicsProgram {
   private static final int PANEL_WIDTH = 400;
   private static final int PANEL_HEIGHT = 400;

   private boolean completed = false;
   private JPanel panel;

   String axiom, strF, strf, strX, strY;
   
   String strU, strV;
   
   int maxX, maxY, level = 3;
   double xLast, yLast, xCorner, yCorner, dir, rotation, dirStart, fxStart, fyStart,
          lengthFract, reductFact;
   public static void main(String[] args) {
         new DragonCurve();
   }

   @Override
    public void paint(Graphics g){
      axiom = "X";
      strF = "F";
      strf = "";
      strX = "X+YF+";
      strY = "-FX-Y";
      
      strU = "";
      strV = "";

      rotation = 90;
      dirStart = 0;
      fxStart = 0.5;
      fyStart = 0.5;
      lengthFract = 0.6;
      reductFact = 0.6;
      Dimension d = getSize();
      maxX = d.width - 1; maxY = d.height - 1;
      xLast = fxStart * maxX; yLast = fyStart * maxY;
      xCorner = xLast;
      yCorner = yLast;
      dir = dirStart; // Initial direction in degrees
      turtleGraphics(g, axiom, level, lengthFract * maxY);
    }
    int iX(double x) {return (int) Math.round(x);}
   int iY(double y) {return (int) Math.round(maxY - y);}

   void drawTo(Graphics g, double x, double y) {
      g.drawLine(iX(xLast), iY(yLast), iX(x), iY(y));
      xLast = x; yLast = y;
   }

   void moveTo(Graphics g, double x, double y) {
      xLast = x; yLast = y;
   }

   public void turtleGraphics(Graphics g, String instruction,
         int depth, double len) {
      double xMark = 0, yMark = 0, dirMark = 0;
      for (int i = 0; i < instruction.length(); i++) {
         char ch = instruction.charAt(i);
         switch (ch) {
         case 'F': // Step forward and draw
            // Start: (xLast, yLast), direction: dir, steplength: len
            if (depth == 0) {
               double rad = Math.PI / 180 * dir, // Degrees -> radians
               dx = len * Math.cos(rad), dy = len * Math.sin(rad);
               drawTo(g, xCorner + dx/4, yCorner + dy/4);
               xCorner += dx; yCorner += dy;
               drawTo(g, xCorner - dx/4, yCorner - dy/4);
               //drawTo(g, xLast + dx, yLast + dy);
            } else
               turtleGraphics(g, strF, depth - 1, reductFact * len);
            break;
         case 'f': // Step forward without drawing
            // Start: (xLast, yLast), direction: dir, steplength: len
            if (depth == 0) {
               double rad = Math.PI / 180 * dir, // Degrees -> radians
               dx = len * Math.cos(rad), dy = len * Math.sin(rad);
               moveTo(g, xLast + dx, yLast + dy);
            } else
               turtleGraphics(g, strf, depth - 1, reductFact * len);
            break;
         case 'X':
            if (depth > 0)
               turtleGraphics(g, strX, depth - 1, reductFact * len);
            break;
         case 'Y':
            if (depth > 0)
               turtleGraphics(g, strY, depth - 1, reductFact * len);
            break;
            
            
            
         case 'U':
            if (depth > 0)
               turtleGraphics(g, strU, depth - 1, reductFact * len);
            break;
         case 'V':
            if (depth > 0)
               turtleGraphics(g, strV, depth - 1, reductFact * len);
            break;
            
            
            
            
            
            
            
            
            
            
         case '+': // Turn right
            dir -= rotation;
            break;
         case '-': // Turn left
            dir += rotation;
            break;
         case '[': // Save position and direction
            xMark = xLast; yMark = yLast;
            dirMark = dir;
            break;
         case ']': // Back to saved position and direction
            xLast = xMark; yLast = yMark;
            dir = dirMark;
            break;
         }
      }
   }

   public DragonCurve() {
      JFrame frame = new JFrame("Dragon Curve");
         this.panel = this;
         setSize(PANEL_WIDTH, PANEL_HEIGHT); 
         addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
               if ((evt.getModifiers() & InputEvent.BUTTON3_MASK) != 0) {
                  level--; // Right mouse button decreases level
                  if (level < 1) level = 1;
               } else
                  level++; // Left mouse button increases level
               repaint();
            }
         });
   }
      @Override
    public JPanel getProgramPanel() {
        return panel;
    }

    @Override
    public String getProgramName() {
        return "Dragon Curve";
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