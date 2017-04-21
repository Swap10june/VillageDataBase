package handlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import ui.AddDocuments;
import ui.AddFamily;
import ui.EditFamily;
import ui.InsertRecords;
import ui.SendSMS;
import util.UConstants;

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
			case UConstants.ADD_FAMILY_STRING:
			{
				AssignIDButtonHandler.mListSize =0;
				new AddFamily(new javax.swing.JDialog(),UConstants.ADD_FAMILY_STRING);
			}
			
			break;
			case UConstants.EDIT_FAMILY_STRING:
			{
				new EditFamily(new javax.swing.JDialog(),UConstants.EDIT_FAMILY_STRING);
			}
			
			break;
			
			case UConstants.ADD_DOCUMENT_STRING:
			{
				new AddDocuments(new javax.swing.JDialog(),UConstants.ADD_DOCUMENT_STRING);
			}
			
			break;
			case UConstants.SEND_BULK_SMS_STRING:
			{
				new SendSMS(new javax.swing.JDialog(),UConstants.SEND_BULK_SMS_STRING);
			}
			
			break;
			case UConstants.INSERT_RECORDS_STRING:
			{
				new InsertRecords(new javax.swing.JDialog(),UConstants.INSERT_RECORDS_STRING);
			}
			
			break;
			
			

		default:
			break;
		}
		
		
	}

}
