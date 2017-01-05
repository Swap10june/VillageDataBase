package handlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import beans.SFamily;
import ui.AddFamily;
import ui.AddMember;
import ui.Home;

public class AddHeadButtonHandler implements ActionListener {

	AddFamily parent;
	
	public AddHeadButtonHandler(AddFamily addFamily, JDialog owner)
	{
		this.parent = addFamily;
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getActionCommand().equalsIgnoreCase("Add Family Head"))
		{
			parent.getFamily().setFamilyId(parent.txtAssignFamilyID.getText());
			AddMember addMember = new AddMember(new javax.swing.JDialog(),e.getActionCommand()); 
			addMember.getMember().setFamily_id(parent.getFamily().getFamilyId());
			parent.getFamily().addMemberIntoFamily(addMember.getMember());
			parent.listOfHead.setEnabled(true);
			
		}
		if(e.getActionCommand().equalsIgnoreCase("Add Member"))
		{
			//parent.getFamily().setFamilyId(parent.txtAssignFamilyID.getText());
			AddMember addMember = new AddMember(new javax.swing.JDialog(),e.getActionCommand()); 
			addMember.getMember().setFamily_id(parent.getFamily().getFamilyId());
			parent.getFamily().addMemberIntoFamily(addMember.getMember());
			parent.listOfMembers.setEnabled(true);
		}
	}

}
