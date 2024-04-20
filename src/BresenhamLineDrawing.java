import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class BresenhamLineDrawing extends JPanel {
    private int startX, startY, endX, endY;

    public BresenhamLineDrawing(int startX, int startY, int endX, int endY) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
    }

    private void drawLine(Graphics g) {
        // Adjusting the origin to the center of the window
        int width = getWidth() / 2;
        int height = getHeight() / 2;

        int x1 = startX + width;
        int y1 = height - startY;
        int x2 = endX + width;
        int y2 = height - endY;

        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        int sx = x1 < x2 ? 1 : -1;
        int sy = y1 < y2 ? 1 : -1;
        int err = (dx > dy ? dx : -dy) / 2;
        int e2;

        int x = x1;
        int y = y1;

        while (true) {
            g.fillRect(x, y, 1, 1); // Draw the pixel at (x, y)
            if (x == x2 && y == y2) break;
            e2 = err;
            if (e2 > -dx) {
                err -= dy;
                x += sx;
            }
            if (e2 < dy) {
                err += dx;
                y += sy;
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawLine(g);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Bresenham Line Drawing Algorithm");
        BresenhamLineDrawing lineDrawer = new BresenhamLineDrawing(10, 10, 100, 100);
        frame.setContentPane(lineDrawer);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
