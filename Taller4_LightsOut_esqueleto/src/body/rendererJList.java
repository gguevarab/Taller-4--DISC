package body;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

public class rendererJList extends DefaultListCellRenderer {
	  
	  private Font font = new Font("Arial", Font.BOLD, 24);
	  private Color color = Color.BLACK;

	  @Override
	  public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
	    Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
	    c.setFont(font);
	    c.setForeground(color);
	    return c;
	  }
	}
