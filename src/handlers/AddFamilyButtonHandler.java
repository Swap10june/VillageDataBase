package handlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import ui.AddFamily;

public class AddFamilyButtonHandler implements ActionListener
{
	JDialog parent;
	public AddFamilyButtonHandler(JDialog parent)
	{
		this.parent = parent;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		switch (e.getActionCommand())
		{
			case "Add Family":
			{
				AddFamily addFamily = new AddFamily(new javax.swing.JDialog());
			}
			
			break;

		default:
			break;
		}
		
		
	}

}
