package graphics_programs;

import javax.swing.*;
import java.awt.*;

public interface GraphicsProgram {

    public JPanel getProgramPanel();

    public String getProgramName();

    public ImageIcon getIcon();

    public Dimension getDimension();

    public Boolean getCompletedStatus();

    public JPanel getInfoPanel();


}