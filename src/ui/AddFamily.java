package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import beans.SFamily;
import handlers.AddHeadButtonHandler;
import handlers.AssignIDButtonHandler;
import handlers.SubmitFamilyButtonHandler;
import handlers.AddFamilyListHandler;
import util.Utils;

public class AddFamily extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Utils 		utility 						= Utils.getUtilityInstance();
	private JLabel 		lblAssignedFamilyID;		
	private JTextField 	txtAssignFamilyID 				= new JTextField();
	private JButton 	btnSubmitFamily;
	private JTextField 	txtNameE 						= new JTextField(250);
	private JTextField 	txtNameM 						= new JTextField(250);
	private JLabel 		lblHeadStatus 					= new JLabel(/*"Yes"*/);
	private JLabel 		lblSexStatus 					= new JLabel(/*"M"*/);
	private JLabel 		lblStateStatus 					= new JLabel(/*"Maharshtra"*/);
	private JLabel 		lblDistStatus 					= new JLabel(/*"Ahmednagar"*/);
	private JLabel 		lblTalStatus 					= new JLabel(/*"Parner"*/);
	private JLabel 		lblVillageStatus 				= new JLabel(/*"Babhulwade"*/);
	private JLabel 		lblDobStatus 					= new JLabel(/*"10/06/1991"*/);
	private JLabel 		lblContStatus 					= new JLabel(/*"9665929145"*/);
	private JLabel 		lblWardStatus 					= new JLabel(/*"1"*/);
	
	private static JButton 			btnAddFamilyHead;
	private static List 			listOfHead;
	
	public static List 				listOfMembers;
	public static SFamily 			family 				= new SFamily();
	
	public Map<String,JComponent> 	componenetMap 		= new HashMap<String,JComponent>();
	public JButton 					btnAddMember;
	public JTextField 				txtAssignMemberID 	= new JTextField(250);
	
	
	
	
	public AddFamily(JDialog owner, String addFamilyString)
	{
		super(owner);
		utility.applyBasicSettingsOnWindow(owner,addFamilyString);
		initUI(owner);
		owner.setVisible(true);
	}
	
	private void initUI(JDialog owner)
	{
		
		JPanel upperBody = new JPanel();
		upperBody.setBounds(0, 110, 894, 1);
		upperBody.setBorder(BorderFactory.createLineBorder(Color.black));
		owner.add(upperBody);
		
		JLabel lblAssignFamilyID = new JLabel("Assign Family ID",SwingConstants.CENTER);
		lblAssignFamilyID.setBounds(10, 50, 120, 30);
		owner.add(lblAssignFamilyID);
		
		
		
		txtAssignFamilyID.setEditable(false);
		txtAssignFamilyID.setBounds(150, 50, 400, 30);
		owner.add(txtAssignFamilyID);
		
		JButton btnAssignFamilyID = new JButton("Assign ");
		btnAssignFamilyID.setBounds(570, 50, 120, 30);
		owner.add(btnAssignFamilyID);
		btnAssignFamilyID.addActionListener(new AssignIDButtonHandler(this,owner));
		
		lblAssignedFamilyID = new JLabel("",SwingConstants.CENTER);
		lblAssignedFamilyID.setBounds(10, 115, 120, 30);
		owner.add(lblAssignedFamilyID);
		
		btnAddFamilyHead = new JButton("Add Family Head");
		btnAddFamilyHead.setBounds(150, 115, 150, 30);
		btnAddFamilyHead.setEnabled(false);
		owner.add(btnAddFamilyHead);
		btnAddFamilyHead.addActionListener(new AddHeadButtonHandler(this,owner));
		
		listOfHead = new List(1,true);
		listOfHead.setBounds(10, 160, 300, 50);
		//listOfHead.setMultipleMode(false);
		 
		listOfHead.setEnabled(false);
		//listOfHead.addActionListener(new headListHandler(this));
		listOfHead.addItemListener(new AddFamilyListHandler(this));
		owner.add(listOfHead);
		
		listOfMembers = new List();
		listOfMembers.setBounds(10, 250, 300, 250);
		listOfMembers.setMultipleMode(false);
		listOfMembers.setEnabled(false);
		listOfMembers.addItemListener(new AddFamilyListHandler(this));
		owner.add(listOfMembers);
		
		btnAddMember = new JButton("Add Member");
		btnAddMember.setBounds(10, 215, 150, 30);
		btnAddMember.setEnabled(false);
		owner.add(btnAddMember);
		btnAddMember.addActionListener(new AddHeadButtonHandler(this,owner));
		
		JPanel infoPanel = new JPanel();
		infoPanel.setBounds(315, 160, 560, 340);
		infoPanel.setEnabled(false);
		//infoPanel.setBackground(Color.cyan);
		infoPanel.setLayout(null);
		initInformationPanel(infoPanel);
		JPanel middleBody = new JPanel();
		middleBody.setBounds(0, 505, 894, 1);
		middleBody.setBorder(BorderFactory.createLineBorder(Color.black));
		owner.add(middleBody);
		
		btnSubmitFamily = new JButton("Submit Family");
		btnSubmitFamily.setBounds(400, 510, 150, 30);
		btnSubmitFamily.setEnabled(false);
		owner.add(btnSubmitFamily);
		btnSubmitFamily.addActionListener(new SubmitFamilyButtonHandler(this,owner));
		owner.add(infoPanel);		
	}

	private void initInformationPanel(JPanel infoPanel) 
	{
		JLabel lblNameE = new JLabel("Name(Eng.)");
		lblNameE.setBounds(5,10, 100, 25);
		lblNameE.setFont(new Font("Andalus", Font.BOLD,15));
		utility.setComponenet(lblNameE,componenetMap);
		infoPanel.add(lblNameE);
		
		txtNameE.setBounds(110, 10, 200, 25);
		utility.setComponenet(txtNameE,componenetMap);
		infoPanel.add(txtNameE);
		
		JLabel lblNameM = new JLabel("Name(Mar.)");
		lblNameM.setFont(new Font("Andalus", Font.BOLD,15));
		lblNameM.setBounds(5,45, 100, 25);
		utility.setComponenet(lblNameM,componenetMap);
		infoPanel.add(lblNameM);
		
		txtNameM.setBounds(110, 45, 200, 25);
		txtNameM.setFont(new Font("Shivaji05", Font.BOLD,15));
		utility.setComponenet(txtNameM,componenetMap);
		infoPanel.add(txtNameM);
		
		JLabel lblHead = new JLabel("Family Head");
		lblHead.setFont(new Font("Andalus", Font.BOLD,15));
		lblHead.setBounds(350,10, 100, 25);
		utility.setComponenet(lblHead,componenetMap);
		infoPanel.add(lblHead);
		
		
		lblHeadStatus.setBounds(460,10, 100, 25);
		utility.setComponenet(lblHeadStatus,componenetMap);
		infoPanel.add(lblHeadStatus);
		
		JLabel lblSex = new JLabel("Sex");
		lblSex.setFont(new Font("Andalus", Font.BOLD,15));
		lblSex.setBounds(350,45, 100, 25);
		utility.setComponenet(lblSex,componenetMap);
		infoPanel.add(lblSex);
		
		
		lblSexStatus.setBounds(460,45, 100, 25);
		utility.setComponenet(lblSexStatus,componenetMap);
		infoPanel.add(lblSexStatus);
		
		JLabel lblAddress = new JLabel("Enter Address Info");
		lblAddress.setFont(new Font("Andalus", Font.ITALIC,15));
		lblAddress.setBounds(5,90, 150, 25);
		utility.setComponenet(lblAddress,componenetMap);
		infoPanel.add(lblAddress);
		
		JLabel lblState = new JLabel("State");
		lblState.setFont(new Font("Andalus", Font.BOLD,15));
		lblState.setBounds(110,120, 60, 25);
		utility.setComponenet(lblState,componenetMap);
		infoPanel.add(lblState);
		
		lblStateStatus.setBounds(170,120, 100, 25);
		utility.setComponenet(lblStateStatus,componenetMap);
		infoPanel.add(lblStateStatus);
		
		JLabel lblDist = new JLabel("Dist.");
		lblDist.setFont(new Font("Andalus", Font.BOLD,15));
		lblDist.setBounds(280,120, 60, 25);
		utility.setComponenet(lblDist,componenetMap);
		infoPanel.add(lblDist);
		
		lblDistStatus.setBounds(350,120, 100, 25);
		utility.setComponenet(lblDistStatus,componenetMap);
		infoPanel.add(lblDistStatus);
		
		JLabel lblTal = new JLabel("Tal.");
		lblTal.setFont(new Font("Andalus", Font.BOLD,15));
		lblTal.setBounds(110,160, 60, 25);
		utility.setComponenet(lblTal,componenetMap);
		infoPanel.add(lblTal);
		
		lblTalStatus.setBounds(170,160, 150, 25);
		utility.setComponenet(lblTalStatus,componenetMap);
		infoPanel.add(lblTalStatus);
		
		JLabel lblVillage = new JLabel("Gaon");
		lblVillage.setFont(new Font("Andalus", Font.BOLD,15));
		lblVillage.setBounds(280,160, 60, 25);
		utility.setComponenet(lblVillage,componenetMap);
		infoPanel.add(lblVillage);
		
		lblVillageStatus.setBounds(350,160, 150, 25);
		utility.setComponenet(lblVillageStatus,componenetMap);
		infoPanel.add(lblVillageStatus);
		
		JLabel lblPersonalInfo = new JLabel("Enter Personal Info");
		lblPersonalInfo.setFont(new Font("Andalus", Font.ITALIC,15));
		lblPersonalInfo.setBounds(5,190, 150, 25);
		utility.setComponenet(lblPersonalInfo,componenetMap);
		infoPanel.add(lblPersonalInfo);
		
		JLabel lblDob = new JLabel("DOB");
		lblDob.setFont(new Font("Andalus", Font.BOLD,15));
		lblDob.setBounds(110,220, 60, 25);
		utility.setComponenet(lblDob,componenetMap);
		infoPanel.add(lblDob);
		
		lblDobStatus.setBounds(170,220, 150, 25);
		utility.setComponenet(lblDobStatus,componenetMap);
		infoPanel.add(lblDobStatus);
		
		JLabel lblCont = new JLabel("Mob No.");
		lblCont.setFont(new Font("Andalus", Font.BOLD,15));
		lblCont.setBounds(280,220, 60, 25);
		utility.setComponenet(lblCont,componenetMap);
		infoPanel.add(lblCont);
		
		lblContStatus.setBounds(350,220, 150, 25);
		utility.setComponenet(lblContStatus,componenetMap);
		infoPanel.add(lblContStatus);
		
		JLabel lblWard = new JLabel("Ward No.");
		lblWard.setFont(new Font("Andalus", Font.BOLD,15));
		lblWard.setBounds(450,220, 100, 25);
		utility.setComponenet(lblWard,componenetMap);
		infoPanel.add(lblWard);
		
		lblWardStatus.setBounds(540,220, 50, 25);
		utility.setComponenet(lblWardStatus,componenetMap);
		infoPanel.add(lblWardStatus);
		
		JLabel lblAssignMemberID = new JLabel("Member ID",SwingConstants.CENTER);
		lblAssignMemberID.setBounds(5, 310, 150, 30);
		utility.setComponenet(lblAssignMemberID,componenetMap);
		infoPanel.add(lblAssignMemberID);
		
		txtAssignMemberID.setEditable(false);
		txtAssignMemberID.setBounds(160, 310, 200, 30);
		utility.setComponenet(txtAssignMemberID,componenetMap);
		infoPanel.add(txtAssignMemberID);
		
		for (Entry<String, JComponent> entry : componenetMap.entrySet())
		{
			entry.getValue().setVisible(false);
		}
	}

	public JTextField getTxtAssignFamilyID() {
		return txtAssignFamilyID;
	}
	public void setTxtAssignFamilyID(JTextField txtAssignFamilyID) {
		this.txtAssignFamilyID = txtAssignFamilyID;
	}
	public static JButton getBtnAddFamilyHead() {
		return btnAddFamilyHead;
	}
	public void setBtnAddFamilyHead(JButton btnAddFamilyHead) {
		AddFamily.btnAddFamilyHead = btnAddFamilyHead;
	}
	public JButton getBtnSubmitFamily() {
		return btnSubmitFamily;
	}
	public void setBtnSubmitFamily(JButton btnSubmitFamily) {
		this.btnSubmitFamily = btnSubmitFamily;
	}
	public JLabel getLblAssignedFamilyID() {
		return lblAssignedFamilyID;
	}
	public void setLblAssignedFamilyID(JLabel lblAssignedFamilyID) {
		this.lblAssignedFamilyID = lblAssignedFamilyID;
	}
	public static List getListOfHead() {
		return listOfHead;
	}

	public static void setListOfHead(List listOfHead) {
		AddFamily.listOfHead = listOfHead;
	}

	public static List getListOfMembers() {
		return listOfMembers;
	}

	public static void setListOfMembers(List listOfMembers) {
		AddFamily.listOfMembers = listOfMembers;
	}

	public JTextField getTxtAssignMemberID() {
		return txtAssignMemberID;
	}

	public void setTxtAssignMemberID(JTextField txtAssignMemberID) {
		this.txtAssignMemberID = txtAssignMemberID;
	}

	public JTextField getTxtNameE() {
		return txtNameE;
	}

	public void setTxtNameE(JTextField txtNameE) {
		this.txtNameE = txtNameE;
	}

	public JTextField getTxtNameM() {
		return txtNameM;
	}

	public void setTxtNameM(JTextField txtNameM) {
		this.txtNameM = txtNameM;
	}

	public JLabel getLblHeadStatus() {
		return lblHeadStatus;
	}

	public void setLblHeadStatus(JLabel lblHeadStatus) {
		this.lblHeadStatus = lblHeadStatus;
	}

	public JLabel getLblSexStatus() {
		return lblSexStatus;
	}

	public void setLblSexStatus(JLabel lblSexStatus) {
		this.lblSexStatus = lblSexStatus;
	}

	public JLabel getLblStateStatus() {
		return lblStateStatus;
	}

	public void setLblStateStatus(JLabel lblStateStatus) {
		this.lblStateStatus = lblStateStatus;
	}

	public JLabel getLblDistStatus() {
		return lblDistStatus;
	}

	public void setLblDistStatus(JLabel lblDistStatus) {
		this.lblDistStatus = lblDistStatus;
	}

	public JLabel getLblTalStatus() {
		return lblTalStatus;
	}

	public void setLblTalStatus(JLabel lblTalStatus) {
		this.lblTalStatus = lblTalStatus;
	}

	public JLabel getLblVillageStatus() {
		return lblVillageStatus;
	}

	public void setLblVillageStatus(JLabel lblVillageStatus) {
		this.lblVillageStatus = lblVillageStatus;
	}

	public JLabel getLblDobStatus() {
		return lblDobStatus;
	}

	public void setLblDobStatus(JLabel lblDobStatus) {
		this.lblDobStatus = lblDobStatus;
	}

	public JLabel getLblContStatus() {
		return lblContStatus;
	}

	public void setLblContStatus(JLabel lblContStatus) {
		this.lblContStatus = lblContStatus;
	}

	public JLabel getLblWardStatus() {
		return lblWardStatus;
	}

	public void setLblWardStatus(JLabel lblWardStatus) {
		this.lblWardStatus = lblWardStatus;
	}
	public SFamily getFamily() {
		return family;
	}

	public void setFamily(SFamily family) {
		AddFamily.family = family;
	}
}
