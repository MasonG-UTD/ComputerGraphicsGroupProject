package graphics_programs;

import javax.swing.*;

public interface GraphicsProgram {

    public JPanel getProgramPanel();

    public String getProgramName();

    public Boolean getCompletedStatus();

}