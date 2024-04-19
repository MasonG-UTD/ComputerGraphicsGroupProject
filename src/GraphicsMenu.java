import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GraphicsMenu extends JFrame {

    private JPanel contentPane;
    private JMenuBar menuBar;
    private JList programList;
    private ListModel programListModel;
    private GraphicsProgram entry;

    private JLabel uncompletedState;

    public static void main(String[] args) {
        GraphicsMenu menu = new GraphicsMenu();
        menu.setVisible(true);
    }

    public GraphicsMenu() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        GraphicsProgram Cube3DViewer;
        entry = new Cube3DViewer();
        GraphicsProgram[] elements = {entry};
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
//
//
//        graphicsProgram1 = new JMenuItem("Graphics Program 1");
//        menuBar.add(graphicsProgram1);
//
//        graphicsProgram2 = new JMenuItem("Graphics Program 2");
//        menuBar.add(graphicsProgram2);
//
//        graphicsProgram3 = new JMenuItem("Graphics Program 3");
//        menuBar.add(graphicsProgram3);
//
//        completedState = new JLabel("Completed", SwingConstants.CENTER);
//        uncompletedState = new JLabel("Uncompleted", SwingConstants.CENTER);

//        graphicsProgram1.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                new GraphicsProgram("program 1");
//            }
//        });
//
//        graphicsProgram2.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                // Load the second graphics program and display it in a separate window
//                JFrame frame = new JFrame("Graphics Program 2");
//                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//                frame.setBounds(100, 100, 450, 300);
//                frame.getContentPane().add(new GraphicsProgram("program 2"));
//                frame.pack();
//                frame.setVisible(true);
//            }
//        });
//
//        graphicsProgram3.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                // Load the third graphics program and display it in a separate window
//                JFrame frame = new JFrame("Graphics Program 3");
//                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//                frame.setBounds(100, 100, 450, 300);
//                frame.getContentPane().add(new GraphicsProgram("program 3"));
//                frame.pack();
//                frame.setVisible(true);
//            }
//        });

    }
}

