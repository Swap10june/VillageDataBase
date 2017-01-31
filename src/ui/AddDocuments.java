package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import beans.SFamily;
import handlers.SelectDocHandler;
import util.Utils;

public class AddDocuments extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static JTextField txtMemberID = new JTextField();
	public JLabel lblAssignedFamilyID;
	public static JButton btnAddFamilyHead;
	public static JButton btnSubmitFamily;
	public JButton btnAddMember;
	public static List listOfHead;
	public static List listOfMembers;
	
	public JTextField txtAssignMemberID = new JTextField(250);
	
	private JTextField txtNameE = new JTextField(250);
	private JTextField txtNameM = new JTextField(250);
	private JLabel lblHeadStatus = new JLabel("Yes");
	private JLabel lblSexStatus = new JLabel("M");
	private JLabel lblStateStatus = new JLabel("Maharshtra");
	private JLabel lblDistStatus = new JLabel("Ahmednagar");
	private JLabel lblTalStatus = new JLabel("Parner");
	private JLabel lblVillageStatus = new JLabel("Babhulwade");
	private JLabel lblDobStatus = new JLabel("10/06/1991");
	private JLabel lblContStatus = new JLabel("9665929145");
	private JLabel lblWardStatus = new JLabel("1");
	
	public static SFamily family = new SFamily();
	
	public static List getListOfHead() {
		return listOfHead;
	}

	public static void setListOfHead(List listOfHead) {
		AddDocuments.listOfHead = listOfHead;
	}

	public static List getListOfMembers() {
		return listOfMembers;
	}

	public static void setListOfMembers(List listOfMembers) {
		AddDocuments.listOfMembers = listOfMembers;
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
		AddDocuments.family = family;
	}

	public AddDocuments(JDialog owner)
	{
		super(owner);
		Utils.getUtilityInstance().applyBasicSettingsOnWindow(owner,"Add Document");
		initUI(owner);
		owner.setVisible(true);
	}

	private void initUI(JDialog owner)
	{
		JPanel upperBody = new JPanel();
		upperBody.setBounds(0, 110, 894, 1);
		upperBody.setBorder(BorderFactory.createLineBorder(Color.black));
		owner.add(upperBody);
		
		JLabel lblAddDocument = new JLabel("Select Member",SwingConstants.CENTER);
		lblAddDocument.setBounds(10, 50, 120, 30);
		owner.add(lblAddDocument);	
		
		txtMemberID.setEditable(true);
		txtMemberID.setBounds(150, 50, 400, 30);
		owner.add(txtMemberID);
		
		JButton btnSelectDocument = new JButton("Select");
		btnSelectDocument.setBounds(570, 50, 120, 30);
		owner.add(btnSelectDocument);
		btnSelectDocument.addActionListener(new SelectDocHandler(txtMemberID.getText(),owner));
		
		listOfHead = new List(1,true);
		listOfHead.setBounds(10, 160, 300, 50);
		listOfHead.setEnabled(false);
		
		owner.add(listOfHead);
		
		/*listOfHead = new List(1,true);
		listOfHead.setBounds(10, 160, 300, 50);
		//listOfHead.setMultipleMode(false);
		 
		listOfHead.setEnabled(false);
		//listOfHead.addActionListener(new headListHandler(this));
		listOfHead.addItemListener(new headListHandler(this));
		for (int i = 0; i < 10; i++) {
			listOfHead.add("swap:"+i);
		}
		owner.add(listOfHead);
		
		listOfMembers = new List();
		listOfMembers.setBounds(10, 250, 300, 250);
		listOfMembers.setMultipleMode(false);
		listOfMembers.setEnabled(false);
		listOfMembers.addItemListener(new headListHandler(this));
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
		initPanelUI(infoPanel);
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
		*/
		
	}

	@SuppressWarnings("unused")
	private void initPanelUI(JPanel infoPanel) 
	{
		JLabel lblNameE = new JLabel("Name(Eng.)");
		lblNameE.setBounds(5,10, 100, 25);
		lblNameE.setFont(new Font("Andalus", Font.BOLD,15));
		infoPanel.add(lblNameE);
		
		
		txtNameE.setBounds(110, 10, 200, 25);
		infoPanel.add(txtNameE);
		
		JLabel lblNameM = new JLabel("Name(Mar.)");
		lblNameM.setFont(new Font("Andalus", Font.BOLD,15));
		lblNameM.setBounds(5,45, 100, 25);
		infoPanel.add(lblNameM);
		
		
		txtNameM.setBounds(110, 45, 200, 25);
		txtNameM.setFont(new Font("Shivaji05", Font.BOLD,15));
		infoPanel.add(txtNameM);
		
		JLabel lblHead = new JLabel("Family Head");
		lblHead.setFont(new Font("Andalus", Font.BOLD,15));
		lblHead.setBounds(350,10, 100, 25);
		infoPanel.add(lblHead);
		
		
		lblHeadStatus.setBounds(460,10, 100, 25);
		infoPanel.add(lblHeadStatus);
		
		JLabel lblSex = new JLabel("Sex");
		lblSex.setFont(new Font("Andalus", Font.BOLD,15));
		lblSex.setBounds(350,45, 100, 25);
		infoPanel.add(lblSex);
		
		
		lblSexStatus.setBounds(460,45, 100, 25);
		infoPanel.add(lblSexStatus);
		
		JLabel lblAddress = new JLabel("Enter Address Info");
		lblAddress.setFont(new Font("Andalus", Font.ITALIC,15));
		lblAddress.setBounds(5,90, 150, 25);
		infoPanel.add(lblAddress);
		
		JLabel lblState = new JLabel("State");
		lblState.setFont(new Font("Andalus", Font.BOLD,15));
		lblState.setBounds(110,120, 60, 25);
		infoPanel.add(lblState);
		
		
		lblStateStatus.setBounds(170,120, 100, 25);
		infoPanel.add(lblStateStatus);
		
		JLabel lblDist = new JLabel("Dist.");
		lblDist.setFont(new Font("Andalus", Font.BOLD,15));
		lblDist.setBounds(280,120, 60, 25);
		infoPanel.add(lblDist);
		
		
		lblDistStatus.setBounds(350,120, 100, 25);
		infoPanel.add(lblDistStatus);
		
		JLabel lblTal = new JLabel("Tal.");
		lblTal.setFont(new Font("Andalus", Font.BOLD,15));
		lblTal.setBounds(110,160, 60, 25);
		infoPanel.add(lblTal);
		
		
		lblTalStatus.setBounds(170,160, 150, 25);
		infoPanel.add(lblTalStatus);
		
		JLabel lblVillage = new JLabel("Gaon");
		lblVillage.setFont(new Font("Andalus", Font.BOLD,15));
		lblVillage.setBounds(280,160, 60, 25);
		infoPanel.add(lblVillage);
		
		
		lblVillageStatus.setBounds(350,160, 150, 25);
		infoPanel.add(lblVillageStatus);
		
		
		JLabel lblPersonalInfo = new JLabel("Enter Personal Info");
		lblPersonalInfo.setFont(new Font("Andalus", Font.ITALIC,15));
		lblPersonalInfo.setBounds(5,190, 150, 25);
		infoPanel.add(lblPersonalInfo);
		
		JLabel lblDob = new JLabel("DOB");
		lblDob.setFont(new Font("Andalus", Font.BOLD,15));
		lblDob.setBounds(110,220, 60, 25);
		infoPanel.add(lblDob);
		
		
		lblDobStatus.setBounds(170,220, 150, 25);
		infoPanel.add(lblDobStatus);
		
		JLabel lblCont = new JLabel("Mob No.");
		lblCont.setFont(new Font("Andalus", Font.BOLD,15));
		lblCont.setBounds(280,220, 60, 25);
		infoPanel.add(lblCont);
		
		
		lblContStatus.setBounds(350,220, 150, 25);
		infoPanel.add(lblContStatus);
		
		JLabel lblWard = new JLabel("Ward No.");
		lblWard.setFont(new Font("Andalus", Font.BOLD,15));
		lblWard.setBounds(450,220, 100, 25);
		infoPanel.add(lblWard);
		
		
		lblWardStatus.setBounds(540,220, 50, 25);
		infoPanel.add(lblWardStatus);
		
		
		JLabel lblAssignMemberID = new JLabel("Member ID",SwingConstants.CENTER);
		lblAssignMemberID.setBounds(5, 310, 150, 30);
		infoPanel.add(lblAssignMemberID);
		
		
		
		txtAssignMemberID.setEditable(false);
		txtAssignMemberID.setBounds(160, 310, 200, 30);
		infoPanel.add(txtAssignMemberID);
		
		/*JButton btnAssignMemberID = new JButton("Assign");
		btnAssignMemberID.setBounds(380, 310, 120, 30);
		infoPanel.add(btnAssignMemberID);
		//btnAssignMemberID.addActionListener(new AssignButtonHandler(this,infoPanel));*/
		
		
	}

	
}
