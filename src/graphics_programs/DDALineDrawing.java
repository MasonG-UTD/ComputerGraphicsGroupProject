package graphics_programs;

import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DDALineDrawing extends JPanel implements GraphicsProgram {
    private int startX, startY, endX, endY;
    private JPanel panel;


    public DDALineDrawing(int startX, int startY, int endX, int endY) {
        this.panel = this;

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

        int dx = x2 - x1;
        int dy = y2 - y1;
        int steps = Math.abs(dx) > Math.abs(dy) ? Math.abs(dx) : Math.abs(dy);

        float xIncrement = dx / (float) steps;
        float yIncrement = dy / (float) steps;

        float x = x1;
        float y = y1;

        for (int i = 0; i <= steps; i++) {
            g.fillRect((int) x, (int) y, 1, 1); // Draw a pixel at (x, y)
            x += xIncrement;
            y += yIncrement;
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawLine(g);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("DDA Line Drawing Algorithm");
        DDALineDrawing lineDrawer = new DDALineDrawing(10, 10, 100, 100);
        frame.setContentPane(lineDrawer);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    @Override
    public JPanel getProgramPanel() {
        return panel;
    }

    @Override
    public String getProgramName() {
        return "dda line drawing";
    }

    @Override
    public Boolean getCompletedStatus() {
        return null;
    }
}
