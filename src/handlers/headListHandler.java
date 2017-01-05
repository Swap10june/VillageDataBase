package handlers;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import beans.SMember;
import ui.AddFamily;

public class headListHandler implements ItemListener
{

	
	private AddFamily addFamily;
	public headListHandler(AddFamily addFamily)
	{
		this.addFamily = addFamily;
	}
	@Override
	public void itemStateChanged(ItemEvent e)
	{
		addFamily.btnAddMember.setEnabled(true);	
		String selectedItem = ((java.awt.List) e.getSource()).getSelectedItem().toString();
		String [] str = selectedItem.split(">");
		SMember member = addFamily.getFamily().getMember(str[0]);
		
		populateMemberInfo(member);
		addFamily.btnSubmitFamily.setEnabled(true);
	}
	private void populateMemberInfo(SMember member)
	{
		addFamily.getTxtAssignMemberID().setText(member.getMember_id());
		addFamily.getTxtNameE().setText(member.getM_name_e());
		addFamily.getTxtNameM().setText(member.getM_name_m());
		addFamily.getLblHeadStatus().setText(member.getFamily_head_status());
		addFamily.getLblSexStatus().setText(member.getM_sex());
		addFamily.getLblStateStatus().setText(member.getM_state());
		addFamily.getLblDistStatus().setText(member.getM_dist());
		addFamily.getLblTalStatus().setText(member.getM_tal());
		addFamily.getLblVillageStatus().setText(member.getM_gaon());
		addFamily.getLblDobStatus().setText(member.getM_dob());
		addFamily.getLblWardStatus().setText(String.valueOf(member.getM_ward()));
		addFamily.getLblContStatus().setText(member.getM_contact());
	}
}
