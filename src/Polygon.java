// Polygon.java: Clipping a polygon.
// Uses: Point2D (Section 1.4).
import java.awt.*;
import java.awt.event.*;
import java.util.*;

// original submission by Ananya

public class Polygon extends Frame {
    public static void main(String[] args) {new Polygon();   }

    Polygon() {
        super("Define polygon vertices by clicking");
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {System.exit(0);}
        });
        setSize(500, 300);
        add("Center", new CvPolygon());
        setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
        setVisible(true);
    }
}

class CvPolygon extends Canvas {
    Poly poly = null;
    float rWidth = 10.0F, rHeight = 7.5F, pixelSize;
    int x0, y0, centerX, centerY;
    boolean ready = true;

    CvPolygon() {
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                int x = evt.getX(), y = evt.getY();
                if (ready) {
                    poly = new Poly();
                    x0 = x; y0 = y;
                    ready = false;
                }
                if (poly.size() > 0 &&
                        Math.abs(x - x0) < 3 && Math.abs(y - y0) < 3)
                    ready = true;
                else
                    poly.addVertex(new Point2D(fx(x), fy(y)));
                repaint();
            }
        });
    }

    void initgr() {
        Dimension d = getSize();
        int maxX = d.width - 1, maxY = d.height - 1;
        pixelSize = Math.max(rWidth / maxX, rHeight / maxY);
        centerX = maxX / 2; centerY = maxY / 2;
    }

    int iX(float x) {return Math.round(centerX + x / pixelSize);}
    int iY(float y) {return Math.round(centerY - y / pixelSize);}
    float fx(int x) {return (x - centerX) * pixelSize;}
    float fy(int y) {return (centerY - y) * pixelSize;}

    void drawLine(Graphics g, float xP, float yP, float xQ, float yQ, boolean solid) {
        if (solid)
            g.drawLine(iX(xP), iY(yP), iX(xQ), iY(yQ));
        else {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 1, new float[]{3}, 0));
            g2d.drawLine(iX(xP), iY(yP), iX(xQ), iY(yQ));
            g2d.dispose();
        }
    }

    void drawPoly(Graphics g, Poly poly, boolean solid) {
        int n = poly.size();
        if (n == 0) return;
        Point2D a = poly.vertexAt(n - 1);
        for (int i = 0; i < n; i++) {
            Point2D b = poly.vertexAt(i);
            drawLine(g, a.x, a.y, b.x, b.y, solid);
            a = b;
        }
    }

    public void paint(Graphics g) {
        initgr();
        float xmin = -rWidth / 3, xmax = rWidth / 3,
                ymin = -rHeight / 3, ymax = rHeight / 3;
        if (poly == null) return;
        int n = poly.size();
        if (n == 0) return;
        Point2D a = poly.vertexAt(0);
        if (!ready) { // Show tiny rectangle around first vertex:
            g.drawRect(iX(a.x) - 2, iY(a.y) - 2, 4, 4);
            // Draw incomplete polygon:
            for (int i = 1; i < n; i++) {
                Point2D b = poly.vertexAt(i);
                drawLine(g, a.x, a.y, b.x, b.y, false); // Draw as dotted line
                a = b;
            }
        } else {
            drawPoly(g, poly, true); // Draw polygon as solid lines
            fillPoly(g, poly); // Fill the polygon
        }
    }

    void fillPoly(Graphics g, Poly poly) {
        int[] xPoints = new int[poly.size()];
        int[] yPoints = new int[poly.size()];
        for (int i = 0; i < poly.size(); i++) {
            Point2D p = poly.vertexAt(i);
            xPoints[i] = iX(p.x);
            yPoints[i] = iY(p.y);
        }
        g.setColor(Color.blue);
        g.fillPolygon(xPoints, yPoints, poly.size());
    }
}

class Poly {
    Vector<Point2D> v = new Vector<Point2D>();

    void addVertex(Point2D p) {
        v.addElement(p);
    }

    int size() {
        return v.size();
    }

    Point2D vertexAt(int i) {
        return v.elementAt(i);
    }
}
