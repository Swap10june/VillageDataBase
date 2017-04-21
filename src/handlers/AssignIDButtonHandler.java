package handlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;

import model.VDBSModel;
import beans.SMember;
import ui.AddFamily;
import ui.AddMember;

public class AssignIDButtonHandler implements ActionListener {

	private 	AddFamily 		addFamily;
	private 	AddMember 		addMember;	
	private 	VDBSModel 		model 		= new VDBSModel();
	public static int mListSize = 0;
	
	public AssignIDButtonHandler(AddFamily addFamily,JDialog owner)
	{
		this.addFamily =  addFamily;
	}

	public AssignIDButtonHandler(AddMember addMember, JDialog owner)
	{
		this.addMember = addMember;
	}
	@Override
	public void actionPerformed(ActionEvent e)
	{
		// family id
		if(e.getActionCommand().equalsIgnoreCase("Assign "))
		{
			if(model==null)
			{
				model = new VDBSModel();
			}
			String familyID = "FID-"+String.valueOf(model.getAllFamiliesUID().length+1);
			addFamily.getTxtAssignFamilyID().setText(familyID);
			addFamily.getLblAssignedFamilyID().setText(familyID);
			AddFamily.getBtnAddFamilyHead().setEnabled(true);
			JButton btn = (JButton) e.getSource();
			btn.setEnabled(false);
		}
		// member id
		if(e.getActionCommand().equalsIgnoreCase("Assign"))
		{
			if(model==null)
			{
				model = new VDBSModel();
			}
			
			String memberID = "MID-"+String.valueOf((mListSize + model.getAllMembersUID().length)+1);
			mListSize++;
			addMember.getTxtAssignMemberID().setText(memberID);
			
			SMember member = new SMember();
			member.setMember_id(memberID);
			//Login.main.put(memberID, member);
			
		}
	}

}
