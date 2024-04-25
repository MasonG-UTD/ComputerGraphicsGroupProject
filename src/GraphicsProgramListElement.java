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
        setIconTextGap(ICON_TEXT_GAP);
        setIcon(program.getIcon());

        setEnabled(true);

        return this;
    }


}

class FolderIcon implements Icon {
    public int getIconHeight() {
        return 8;
    }

    public int getIconWidth() {
        return 16;
    }

    public void paintIcon( Component c, Graphics g, int x, int y ) {
        g.setColor( Color.yellow.darker() );
        g.fillRoundRect( x + 3, y - 2, 8,  8, 2, 2 );
        g.fillRect     ( x + 1, y,     16, 10 );
    }
}
