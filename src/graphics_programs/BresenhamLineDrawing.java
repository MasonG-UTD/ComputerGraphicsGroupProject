package graphics_programs;

import java.awt.*;
import javax.swing.*;

public class BresenhamLineDrawing extends JPanel implements GraphicsProgram {

    private JPanel panel;

    private static final int PANEL_WIDTH = 400;
    private static final int PANEL_HEIGHT = 400;

    private int startX, startY, endX, endY;

    private boolean completed = false;


    public BresenhamLineDrawing(int startX, int startY, int endX, int endY) {
        this.panel = this;
        setSize(PANEL_WIDTH, PANEL_HEIGHT);
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
        completed = true;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawLine(g);
    }

    @Override
    public JPanel getProgramPanel() {
        return panel;
    }

    @Override
    public String getProgramName() {
        return "Bresenham line drawing";
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
        return "info/bres.txt";
    }
}
