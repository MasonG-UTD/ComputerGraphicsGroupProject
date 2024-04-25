import graphics_programs.BresenhamLineDrawing;

import javax.swing.*;
import java.awt.event.*;

public class BresenhamInput extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField x2TextField;
    private JTextField x1TextField;
    private JTextField y1TextField;
    private JTextField y2TextField;

    public BresenhamInput() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

    }

    private void onOK() {

        int x1 = Integer.parseInt(x1TextField.getText());
        int y1 = Integer.parseInt(y1TextField.getText());

        int x2 = Integer.parseInt(x1TextField.getText());
        int y2 = Integer.parseInt(y2TextField.getText());

        new GraphicsProgramWindow(new BresenhamLineDrawing(x1,y1,x2,y2));

        System.out.println("");

        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        BresenhamInput dialog = new BresenhamInput();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
