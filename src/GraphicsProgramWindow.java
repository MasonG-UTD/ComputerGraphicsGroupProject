import graphics_programs.GraphicsProgram;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class GraphicsProgramWindow extends JFrame {

    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;

    private JButton backButton;
    private JPanel contentPane;

    public GraphicsProgramWindow(GraphicsProgram program) {

        setBackground(Color.WHITE);
        setBounds(100, 100, 800, 800);
        setVisible(true);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        backButton = new JButton("Back");
        contentPane.add(backButton, BorderLayout.SOUTH);
        contentPane.add(program.getProgramPanel());

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Go back to the menu
                setVisible(false);
            }
        });

    }
}