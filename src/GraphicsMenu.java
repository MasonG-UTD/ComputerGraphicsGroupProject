import graphics_programs.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GraphicsMenu extends JFrame {

    private JPanel contentPane;
    private JList programList;
    private GraphicsProgram bresenham, dda, clipLine, polygon, cubeViewer;

    public static void main(String[] args) {
        GraphicsMenu menu = new GraphicsMenu();
        menu.setVisible(true);
    }

    public GraphicsMenu() {

        bresenham = new BresenhamLineDrawing(0,0,200,200);
        dda = new DDALineDrawing(0,0,200,200);
        clipLine = new ClipLine();
        //polygon = new  graphics_programs.Polygon();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        GraphicsProgram[] elements = {bresenham, dda, clipLine};
        programList = new JList(elements);
        programList.setCellRenderer(new GraphicsProgramListElement());

        programList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                GraphicsProgram program = (GraphicsProgram) programList.getSelectedValue();
                new GraphicsProgramWindow(program);
            }
        });

        contentPane.add(programList);
    }
}

