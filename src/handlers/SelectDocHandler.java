package handlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;

import beans.SFamily;
import beans.SMember;
import src.Test;
import ui.AddDocuments;
import ui.AddFamily;
import ui.AddMember;
import util.Utils;

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
			Test.main.put(memberID, member);
			
		}
	}

}
