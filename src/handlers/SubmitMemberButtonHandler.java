package handlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import beans.SMember;
import ui.AddFamily;
import ui.AddMember;
import util.Props;

public class SubmitMemberButtonHandler implements ActionListener {

	private AddMember 	addMember;
	private JDialog 	owner;
	
	public SubmitMemberButtonHandler(AddMember addMember, JDialog owner)
	{
		this.addMember = addMember;
		this.owner = owner;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand().equalsIgnoreCase("Add Family Head"))
		{
			if(		addMember.getTxtAssignMemberID().getText().isEmpty() || 
					addMember.getTxtNameE().getText().isEmpty()||
					addMember.getTxtNameM().getText().isEmpty() ||
					addMember.getComboHeadStatus().getSelectedItem().toString().isEmpty() ||
					addMember.getComboSexStatus().getSelectedItem().toString().isEmpty() ||
					addMember.getComboStateStatus().getSelectedItem().toString().isEmpty()
					
					)
					
			{
				JOptionPane.showMessageDialog (owner, "Please Fill All The Fields","Error", JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				SMember member = new SMember();
				member.setMember_id(addMember.getTxtAssignMemberID().getText());
				member.setM_name_e(addMember.getTxtNameE().getText());
				member.setM_name_m(addMember.getTxtNameM().getText());
				member.setFamily_head_status("Yes");
				member.setM_sex(addMember.getComboSexStatus().getSelectedItem().toString());
				member.setM_state(addMember.getComboStateStatus().getSelectedItem().toString());
				member.setM_dist(addMember.getComboDistStatus().getSelectedItem().toString());
				member.setM_tal(addMember.getTxtTalStatus().getText());
				member.setM_gaon(addMember.getTxtVillageStatus().getText());
				member.setM_dob(addMember.getTxtDobStatus().getText());
				member.setM_ward(Integer.parseInt(addMember.getTxtWardStatus().getText()));
				member.setM_contact(addMember.getTxtContStatus().getText());
				addMember.setMember(member);
				AddFamily.getListOfHead().add(member.getMember_id()+Props.STRING_SPLIT+member.getM_name_e());
				AddFamily.getBtnAddFamilyHead().setEnabled(false);
				owner.dispose();
			}
		}
		if(e.getActionCommand().equalsIgnoreCase("Add Member"))
		{
			SMember member = new SMember();
			member.setMember_id(addMember.getTxtAssignMemberID().getText());
			member.setM_name_e(addMember.getTxtNameE().getText());
			member.setM_name_m(addMember.getTxtNameM().getText());
			member.setFamily_head_status("No");
			member.setM_sex(addMember.getComboSexStatus().getSelectedItem().toString());
			member.setM_state(addMember.getComboStateStatus().getSelectedItem().toString());
			member.setM_dist(addMember.getComboDistStatus().getSelectedItem().toString());
			member.setM_tal(addMember.getTxtTalStatus().getText());
			member.setM_gaon(addMember.getTxtVillageStatus().getText());
			member.setM_dob(addMember.getTxtDobStatus().getText());
			member.setM_ward(Integer.parseInt(addMember.getTxtWardStatus().getText()));
			member.setM_contact(addMember.getTxtContStatus().getText());
			addMember.setMember(member);
			AddFamily.listOfMembers.add(member.getMember_id()+Props.STRING_SPLIT+member.getM_name_e());
			owner.dispose();
		}
		if(e.getActionCommand().equalsIgnoreCase("Cancel"))
		{
			owner.dispose();
		}
	}

}
