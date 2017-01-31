package handlers;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Map.Entry;

import javax.swing.JComponent;
import beans.SMember;
import ui.AddFamily;
import util.Props;

public class AddFamilyListHandler implements ItemListener
{

	
	private AddFamily addFamily;
	public AddFamilyListHandler(AddFamily addFamily)
	{
		this.addFamily = addFamily;
	}
	@Override
	public void itemStateChanged(ItemEvent e)
	{
		addFamily.btnAddMember.setEnabled(true);	
		String selectedItem = ((java.awt.List) e.getSource()).getSelectedItem().toString();
		String [] str = selectedItem.split(Props.STRING_SPLIT);
		SMember member = addFamily.getFamily().getMember(str[0]);
		
		populateMemberInfo(member);
		addFamily.getBtnSubmitFamily().setEnabled(true);
		AddFamily.getListOfHead().setEnabled(false);
	}
	private void populateMemberInfo(SMember member)
	{
		
		for (Entry<String, JComponent> entry : addFamily.componenetMap.entrySet())
		{
			entry.getValue().setVisible(true);
		}
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
