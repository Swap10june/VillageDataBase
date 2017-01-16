package handlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import ui.AddFamily;
import ui.EditFamily;

public class HomeButtonHandler implements ActionListener
{
	JDialog parent;
	public HomeButtonHandler(JDialog parent)
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
			case "Show Family":
			{
				
			}
			
			break;
			case "Edit Family":
			{
				EditFamily addMemberIntoFamily = new EditFamily(new javax.swing.JDialog());
			}
			
			break;

		default:
			break;
		}
		
		
	}

}
