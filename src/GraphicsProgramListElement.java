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
        setText("name");
        setIconTextGap(ICON_TEXT_GAP);

        setEnabled(true);

        return this;
    }


}
