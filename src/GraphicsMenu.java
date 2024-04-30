import graphics_programs.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GraphicsMenu extends JFrame {

    private JPanel contentPane;
    private JList programList;
    private GraphicsProgram bresenham, dda, clipLine, polygon, cubeViewer, koch;

    public static void main(String[] args) {
        GraphicsMenu menu = new GraphicsMenu();
        menu.setVisible(true);
    }

    public GraphicsMenu() {

        setName("Graphics Review");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700,700);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        dda = new DDALineDrawing(0,0,200,200);
        bresenham = new BresenhamLineDrawing(0,0,100,100);
        clipLine = new ClipLine();
        koch = new KochFractal();

        GraphicsProgram[] elements = {dda, bresenham, clipLine, koch};
        GraphicsProgramListElement listRenderer = new GraphicsProgramListElement();
        programList = new JList(elements);
        programList.setCellRenderer(listRenderer);
        programList.setVisibleRowCount(4);
        programList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                GraphicsProgram program = (GraphicsProgram) programList.getSelectedValue();
                new GraphicsProgramWindow(program);
            }
        });
        JScrollPane listPane = new JScrollPane(programList);
        contentPane.add(listPane);

    }
}

