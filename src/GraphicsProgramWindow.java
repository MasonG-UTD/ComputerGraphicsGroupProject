import graphics_programs.GraphicsProgram;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

class GraphicsProgramWindow extends JFrame {

    private static final int WIDTH = 900;
    private static final int HEIGHT = 900;

    private JButton backButton;
    private JButton infoButton;
    private JPanel contentPane;

    public GraphicsProgramWindow(GraphicsProgram program) {

        setBackground(Color.WHITE);
        setBounds(0, 0, WIDTH, HEIGHT);
        setVisible(true);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
    
        infoButton = new JButton("Info");
        contentPane.add(infoButton, BorderLayout.NORTH);
        infoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InfoDialog diag = new InfoDialog(program);
                diag.pack();
                diag.setVisible(true);
            }
        });

        contentPane.add(program.getProgramPanel(), BorderLayout.CENTER);

        backButton = new JButton("Back");
        contentPane.add(backButton, BorderLayout.SOUTH);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Go back to the menu
                setVisible(false);
            }
        });

    }
}