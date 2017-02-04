package handlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import ui.AddDocuments;
import ui.AddFamily;
import ui.EditFamily;
import ui.InsertRecords;
import ui.SendSMS;

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
				new AddFamily(new javax.swing.JDialog());
			}
			
			break;
			case "Show Family":
			{
				
			}
			
			break;
			case "Edit Family":
			{
				new EditFamily(new javax.swing.JDialog());
			}
			
			break;
			
			case "Add Document":
			{
				new AddDocuments(new javax.swing.JDialog());
			}
			
			break;
			case "Send Bulk SMS":
			{
				new SendSMS(new javax.swing.JDialog());
			}
			
			break;
			case "Insert Records":
			{
				new InsertRecords(new javax.swing.JDialog());
			}
			
			break;
			
			

		default:
			break;
		}
		
		
	}

}
