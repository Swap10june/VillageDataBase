package handlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import beans.SMember;
import src.Login;
import ui.AddDocuments;
import ui.AddFamily;

public class SelectDocHandler implements ActionListener {

	AddDocuments addFamily;
	String  selectedMember;
	JDialog owner;
	static SelectDocHandler assignIDButtonHandler;
	public SelectDocHandler(AddDocuments addDocuments) {
		this.addFamily =  addDocuments;
		
	}

	public SelectDocHandler(String selectedMember,JDialog owner)
	{
		this.selectedMember =selectedMember;
		this.owner=owner;
	}
	@Override
	public void actionPerformed(ActionEvent e)
	{
		// family id
		if(e.getActionCommand().equalsIgnoreCase("Select"))
		{
			AddDocuments.listOfHead.add(selectedMember);
		}
		// member id
		if(e.getActionCommand().equalsIgnoreCase("Assign"))
		{
			
			/*String temp = AddFamily.txtAssignFamilyID.getText();
			String[] splitString = temp.split("-");*/
			String memberID = "MID-"+String.valueOf(AddFamily.family.getMembers().size()+1);
			//addMember.txtAssignMemberID.setText(memberID);
			
			SMember member = new SMember();
			member.setMember_id(memberID);
			Login.main.put(memberID, member);
			
		}
	}

}
