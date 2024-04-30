import graphics_programs.GraphicsProgram;

import javax.swing.*;
import java.awt.*;

public class GraphicsProgramListElement extends DefaultListCellRenderer implements ListCellRenderer<Object> {

    private static final int ICON_TEXT_GAP = 100;

    @Override
    public Component getListCellRendererComponent(
            JList<?> list,
            Object value,
            int index,
            boolean isSelected,
            boolean cellHasFocus) {

        GraphicsProgram program = (GraphicsProgram) value;
        setText(program.getProgramName());
        setFont(getFont().deriveFont(24.0f));
        setSize(400,200);

        if(program.getCompletedStatus()) {
            ImageIcon scaledIcon = new ImageIcon(program.getIcon().getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
            setIcon(scaledIcon);
        } else {
            setIcon(new IncompleteIcon());
        }

        setIconTextGap(ICON_TEXT_GAP);
        setEnabled(true);
        return this;
    }

}

class IncompleteIcon implements Icon {

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
    }

    @Override
    public int getIconWidth() {
        return 200;
    }

    @Override
    public int getIconHeight() {
        return 200;
    }
}
