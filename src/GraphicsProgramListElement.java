import graphics_programs.GraphicsProgram;

import javax.swing.*;
import java.awt.*;

public class GraphicsProgramListElement extends DefaultListCellRenderer implements ListCellRenderer<Object> {

    private static final int ICON_TEXT_GAP = 5;

    @Override
    public Component getListCellRendererComponent(
            JList<?> list,
            Object value,
            int index,
            boolean isSelected,
            boolean cellHasFocus) {

        GraphicsProgram program = (GraphicsProgram) value;
        setText(program.getProgramName());
        setSize(800,300);
        if(program.getCompletedStatus()) {
            setIcon(program.getIcon());
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
        return 0;
    }

    @Override
    public int getIconHeight() {
        return 0;
    }
}
