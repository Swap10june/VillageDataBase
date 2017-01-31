package ui;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import beans.SMember;
import handlers.AssignIDButtonHandler;
import handlers.SubmitMemberButtonHandler;
import util.Utils;

public class AddMember extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnSubmitMember;

	private String buttonString;
	public JTextField txtAssignMemberID;
	private SMember member;
	
	private JTextField txtNameE;
	private JTextField txtNameM;
	private JComboBox<String> comboHeadStatus;
	private JComboBox<String> comboSexStatus;
	private JComboBox<String> comboStateStatus;
	private JComboBox<String> comboDistStatus;
	private JTextField txtTalStatus;
	private JTextField txtVillageStatus;
	private JTextField txtDobStatus;
	private JTextField txtContStatus;
	private JTextField txtWardStatus;
	
	
	public JTextField getTxtAssignMemberID() {
		return txtAssignMemberID;
	}

	public void setTxtAssignMemberID(JTextField txtAssignMemberID) {
		this.txtAssignMemberID = txtAssignMemberID;
	}

	public SMember getMember() {
		return member;
	}

	public void setMember(SMember member) {
		this.member = member;
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

	public JComboBox<String> getComboHeadStatus() {
		return comboHeadStatus;
	}

	public void setComboHeadStatus(JComboBox<String> comboHeadStatus) {
		this.comboHeadStatus = comboHeadStatus;
	}

	public JComboBox<String> getComboSexStatus() {
		return comboSexStatus;
	}

	public void setComboSexStatus(JComboBox<String> comboSexStatus) {
		this.comboSexStatus = comboSexStatus;
	}

	public JComboBox<String> getComboStateStatus() {
		return comboStateStatus;
	}

	public void setComboStateStatus(JComboBox<String> comboStateStatus) {
		this.comboStateStatus = comboStateStatus;
	}

	public JComboBox<String> getComboDistStatus() {
		return comboDistStatus;
	}

	public void setComboDistStatus(JComboBox<String> comboDistStatus) {
		this.comboDistStatus = comboDistStatus;
	}

	public JTextField getTxtTalStatus() {
		return txtTalStatus;
	}

	public void setTxtTalStatus(JTextField txtTalStatus) {
		this.txtTalStatus = txtTalStatus;
	}

	public JTextField getTxtVillageStatus() {
		return txtVillageStatus;
	}

	public void setTxtVillageStatus(JTextField txtVillageStatus) {
		this.txtVillageStatus = txtVillageStatus;
	}

	public JTextField getTxtDobStatus() {
		return txtDobStatus;
	}

	public void setTxtDobStatus(JTextField txtDobStatus) {
		this.txtDobStatus = txtDobStatus;
	}

	public JTextField getTxtContStatus() {
		return txtContStatus;
	}

	public void setTxtContStatus(JTextField txtContStatus) {
		this.txtContStatus = txtContStatus;
	}

	public JTextField getTxtWardStatus() {
		return txtWardStatus;
	}

	public void setTxtWardStatus(JTextField txtWardStatus) {
		this.txtWardStatus = txtWardStatus;
	}

	public AddMember(JDialog owner, String string)
	{
		super(owner);
		Utils.applyBasicSettingsOnWindow_Small(owner,string);
		buttonString =  string;
		initUI(owner);
		owner.setVisible(true);
		
	}

	private void initUI(JDialog owner)
	{
		JLabel lblNameE = new JLabel("Name(Eng.)");
		lblNameE.setBounds(5,40, 100, 25);
		lblNameE.setFont(new Font("Andalus", Font.BOLD,15));
		owner.add(lblNameE);
		
		txtNameE = new JTextField(250);
		txtNameE.setBounds(110, 40, 200, 25);
		owner.add(txtNameE);
		
		JLabel lblNameM = new JLabel("Name(Mar.)");
		lblNameM.setFont(new Font("Andalus", Font.BOLD,15));
		lblNameM.setBounds(5,75, 100, 25);
		owner.add(lblNameM);
		
		txtNameM = new JTextField(250);
		txtNameM.setFont(new Font("Shivaji05", Font.BOLD,15));
		txtNameM.setBounds(110, 75, 200, 25);
		owner.add(txtNameM);
		
		JLabel lblHead = new JLabel("Family Head");
		lblHead.setFont(new Font("Andalus", Font.BOLD,15));
		lblHead.setBounds(350,40, 100, 25);
		owner.add(lblHead);
		
		comboHeadStatus = new JComboBox<String>(new String[]{"Yes","No"});
		comboHeadStatus.setBounds(460,40, 100, 25);
		comboHeadStatus.setEnabled(true);
		if(buttonString.equalsIgnoreCase("Add Member"))
		{
			comboHeadStatus.setEnabled(false);
		}
		owner.add(comboHeadStatus);
		
		JLabel lblSex = new JLabel("Sex");
		lblSex.setFont(new Font("Andalus", Font.BOLD,15));
		lblSex.setBounds(350,75, 100, 25);
		owner.add(lblSex);
		
		comboSexStatus = new JComboBox<String>(new String[]{"Male","Female"});
		comboSexStatus.setBounds(460,75, 100, 25);
		owner.add(comboSexStatus);
		
		JLabel lblAddress = new JLabel("Enter Address Info");
		lblAddress.setFont(new Font("Andalus", Font.ITALIC,15));
		lblAddress.setBounds(5,120, 150, 25);
		owner.add(lblAddress);
		
		JLabel lblState = new JLabel("State");
		lblState.setFont(new Font("Andalus", Font.BOLD,15));
		lblState.setBounds(110,150, 60, 25);
		owner.add(lblState);
		
		comboStateStatus = new JComboBox<String>(new String[]{"Maharashtra","GOA"});
		comboStateStatus.setBounds(170,150, 100, 25);
		owner.add(comboStateStatus);
		
		JLabel lblDist = new JLabel("Dist.");
		lblDist.setFont(new Font("Andalus", Font.BOLD,15));
		lblDist.setBounds(320,150, 60, 25);
		owner.add(lblDist);
		
		comboDistStatus = new JComboBox<String>(new String[]{"Ahmednagar","Pune"});
		comboDistStatus.setBounds(370,150, 100, 25);
		owner.add(comboDistStatus);
		
		JLabel lblTal = new JLabel("Tal.");
		lblTal.setFont(new Font("Andalus", Font.BOLD,15));
		lblTal.setBounds(110,190, 60, 25);
		owner.add(lblTal);
		
		txtTalStatus = new JTextField(100);
		txtTalStatus.setBounds(170,190, 150, 25);
		owner.add(txtTalStatus);
		
		JLabel lblVillage = new JLabel("Gaon");
		lblVillage.setFont(new Font("Andalus", Font.BOLD,15));
		lblVillage.setBounds(320,190, 60, 25);
		owner.add(lblVillage);
		
		txtVillageStatus = new JTextField(100);
		txtVillageStatus.setBounds(370,190, 150, 25);
		owner.add(txtVillageStatus);
		
		
		JLabel lblPersonalInfo = new JLabel("Enter Personal Info");
		lblPersonalInfo.setFont(new Font("Andalus", Font.ITALIC,15));
		lblPersonalInfo.setBounds(5,220, 150, 25);
		owner.add(lblPersonalInfo);
		
		JLabel lblDob = new JLabel("DOB");
		lblDob.setFont(new Font("Andalus", Font.BOLD,15));
		lblDob.setBounds(110,250, 60, 25);
		owner.add(lblDob);
		
		txtDobStatus = new JTextField(100);
		txtDobStatus.setBounds(170,250, 100, 25);
		owner.add(txtDobStatus);
		
		JLabel lblDobFormat = new JLabel("DD/MM/YYYY");
		lblDobFormat.setFont(new Font("Andalus", Font.ITALIC,10));
		lblDobFormat.setBounds(170,276, 150, 25);
		owner.add(lblDobFormat);
		
		JLabel lblCont = new JLabel("Mob No.");
		lblCont.setFont(new Font("Andalus", Font.BOLD,15));
		lblCont.setBounds(280,250, 60, 25);
		owner.add(lblCont);
		
		txtContStatus = new JTextField(10);
		txtContStatus.setBounds(350,250, 100, 25);
		owner.add(txtContStatus);
		
		JLabel lblWard = new JLabel("Ward No.");
		lblWard.setFont(new Font("Andalus", Font.BOLD,15));
		lblWard.setBounds(460,250, 100, 25);
		owner.add(lblWard);
		
		txtWardStatus = new JTextField(3);
		txtWardStatus.setBounds(540,250, 30, 25);
		owner.add(txtWardStatus);
		
		
		JLabel lblAssignMemberID = new JLabel("Assign Member ID",SwingConstants.CENTER);
		lblAssignMemberID.setBounds(5, 340, 150, 30);
		owner.add(lblAssignMemberID);
		
		
		txtAssignMemberID = new JTextField(250);
		txtAssignMemberID.setEditable(false);
		txtAssignMemberID.setBounds(160, 340, 200, 30);
		owner.add(txtAssignMemberID);
		
		JButton btnAssignMemberID = new JButton("Assign");
		btnAssignMemberID.setBounds(380, 340, 120, 30);
		owner.add(btnAssignMemberID);
		btnAssignMemberID.addActionListener(new AssignIDButtonHandler(this, owner));
		
		btnSubmitMember = new JButton(buttonString);
		btnSubmitMember.setBounds(10, 400, 150, 30);
		//btnSubmitMember.setEnabled(false);
		owner.add(btnSubmitMember);
		btnSubmitMember.addActionListener(new SubmitMemberButtonHandler(this,owner));
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(300, 400, 150, 30);
		//btnSubmitMember.setEnabled(false);
		owner.add(btnCancel);
		btnCancel.addActionListener(new SubmitMemberButtonHandler(this,owner));
	}

}
