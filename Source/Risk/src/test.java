/*import java.awt.BorderLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;

class InternalFramePropertyChangeHandler implements PropertyChangeListener {
  public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
    String propertyName = propertyChangeEvent.getPropertyName();
    System.out.println(propertyName);
    if (propertyName.equals(JInternalFrame.IS_ICON_PROPERTY)) {
      System.out.println("Icon property changed. React.");
    }
  }
}

public class test {
  public static void main(final String[] args) {
    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    
    JDesktopPane desktop = new JDesktopPane();
    JInternalFrame internalFrame = new JInternalFrame("Can Do All", true, true, true, true);
  
    
    AttackScreen attackframe = new AttackScreen(jgui);
    attackframe.setBounds(23, 10, 851, 600);
    attackframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //Display the window.
    attackframe.setVisible(true);
    
    InternalFramePropertyChangeHandler ins = new InternalFramePropertyChangeHandler();

    // Add listener for iconification events
    internalFrame.addPropertyChangeListener(ins);

    desktop.add(internalFrame);
    desktop.add(attackframe);

    internalFrame.setBounds(25, 25, 200, 100);

    JLabel label = new JLabel(internalFrame.getTitle(), JLabel.CENTER);
    internalFrame.add(label, BorderLayout.CENTER);

    internalFrame.setVisible(true);

    frame.add(desktop, BorderLayout.CENTER);
    frame.setSize(500, 300);
    frame.setVisible(true);
  }
}*/