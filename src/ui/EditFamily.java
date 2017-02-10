package ui;

import handlers.EditFamilyButtonHandler;
import handlers.EditFamilyListHandler;

import java.awt.Color;
import java.awt.Font;
import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
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

import util.AutoSuggestor;
import util.Utils;

public class EditFamily extends JDialog
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Utils 					utility 			= 		Utils.getUtilityInstance();
	private JTextField 				txtSelectFamilyID 	= 		new JTextField(500);
	private List 					listOfHead 			= 		new List(1,true);
	private List 					listOfMembers 		= 		new List();
	private Map<String,JComponent> 	componenetMap 		= 		new HashMap<String,JComponent>();
	
	private JTextField 				txtNameM ;
	private JLabel 					lblHeadStatus ;
	private JLabel 					lblSexStatus ;
	private JLabel 					lblStateStatus ;
	private JLabel 					lblDistStatus ;
	private JLabel 					lblTalStatus ;
	private JLabel 					lblVillageStatus ;
	private JLabel 					lblDobStatus ;
	private JLabel 					lblContStatus ;
	private JLabel 					lblWardStatus ;
	private JTextField 				txtAssignMemberID 	= 		new JTextField();
	private JTextField 				txtNameE ;
	private  ArrayList<String> 		list				= 		null;
	
	
	
	
	public EditFamily(JDialog owner)
	{
		super(owner);
		utility.applyBasicSettingsOnWindow(owner,"Edit Family");
		initUI(owner);
		owner.setVisible(true);
	}

	private void initUI(JDialog owner)
	{
		
		JPanel upperBody = new JPanel();
		upperBody.setBounds(0, 110, 894, 1);
		upperBody.setBorder(BorderFactory.createLineBorder(Color.black));
		owner.getContentPane().add(upperBody);
		
		JLabel lblSelectFamilyID = new JLabel("Select Family ID",SwingConstants.CENTER);
		lblSelectFamilyID.setBounds(10, 50, 120, 30);
		owner.getContentPane().add(lblSelectFamilyID);

		txtSelectFamilyID.setBounds(150, 50, 400, 30);
		owner.getContentPane().add(txtSelectFamilyID);
		
		JButton btnSelectFamilyID = new JButton("Select");
		btnSelectFamilyID.setBounds(570, 50, 120, 30);
		owner.getContentPane().add(btnSelectFamilyID);
		btnSelectFamilyID.addActionListener(new EditFamilyButtonHandler(this));
		
		
		listOfHead.setBounds(10, 160, 300, 50);
		listOfHead.setVisible(false);
		listOfHead.addItemListener(new EditFamilyListHandler(this));
		owner.add(listOfHead);
		
		
		listOfMembers.setBounds(10, 250, 300, 250);
		listOfMembers.setMultipleMode(false);
		listOfMembers.setVisible(false);
		listOfMembers.addItemListener(new EditFamilyListHandler(this));
		owner.add(listOfMembers);
		
		
		JPanel infoPanel = new JPanel();
		infoPanel.setBounds(315, 160, 560, 340);
		infoPanel.setEnabled(false);
		//infoPanel.setBackground(Color.cyan);
		infoPanel.setLayout(null);
		initInformationPanel(infoPanel);
		owner.add(infoPanel);
		JPanel middleBody = new JPanel();
		middleBody.setBounds(0, 505, 894, 1);
		middleBody.setBorder(BorderFactory.createLineBorder(Color.black));
		owner.add(middleBody);
		@SuppressWarnings("unused")
		AutoSuggestor autoSuggestor = new AutoSuggestor(txtSelectFamilyID, owner, null, Color.WHITE.brighter(), Color.BLUE, Color.RED, 0.75f)
		{
		    protected boolean wordTyped(String typedWord) {

		        //create list for dictionary this in your case might be done via calling a method which queries db and returns results as arraylist
		        ArrayList<String> words = new ArrayList<>();

		        Map<String, String> columnNames = new HashMap<String,String>();
		        columnNames.put("family_id", "family_id");
		        columnNames.put("family_head", "family_head");
		        if(list==null)
		        {
		        	words = utility.queryMultiColumnSelect("SFamily",columnNames);
			        list = new ArrayList<String>(new LinkedHashSet<String>(words));
		        }
		        
		        setDictionary(list);
		        //addToDictionary("bye");//adds a single word

		        return super.wordTyped(typedWord);//now call super to check for any matches against newest dictionary
		    }
		};
	}
	private void initInformationPanel(JPanel infoPanel) 
	{
		JLabel lblNameE = new JLabel("Name(Eng.)");
		lblNameE.setBounds(5,10, 100, 25);
		lblNameE.setFont(new Font("Andalus", Font.BOLD,15));
		utility.setComponenet(lblNameE,componenetMap);
		infoPanel.add(lblNameE);
		
		
		txtNameE = new JTextField();
		txtNameE.setBounds(110, 10, 200, 25);
		utility.setComponenet(txtNameE,componenetMap);
		infoPanel.add(txtNameE);
		
		JLabel lblNameM = new JLabel("Name(Mar.)");
		lblNameM.setFont(new Font("Andalus", Font.BOLD,15));
		lblNameM.setBounds(5,45, 100, 25);
		utility.setComponenet(lblNameM,componenetMap);
		infoPanel.add(lblNameM);
		
		
		txtNameM = new JTextField();
		txtNameM.setBounds(110, 45, 200, 25);
		txtNameM.setFont(new Font("Shivaji05", Font.BOLD,15));
		utility.setComponenet(txtNameM,componenetMap);
		infoPanel.add(txtNameM);
		
		JLabel lblHead = new JLabel("Family Head");
		lblHead.setFont(new Font("Andalus", Font.BOLD,15));
		lblHead.setBounds(350,10, 100, 25);
		utility.setComponenet(lblHead,componenetMap);
		infoPanel.add(lblHead);
		
		
		lblHeadStatus = new JLabel();
		lblHeadStatus.setBounds(460,10, 100, 25);
		utility.setComponenet(lblHeadStatus,componenetMap);
		infoPanel.add(lblHeadStatus);
		
		JLabel lblSex = new JLabel("Sex");
		lblSex.setFont(new Font("Andalus", Font.BOLD,15));
		lblSex.setBounds(350,45, 100, 25);
		utility.setComponenet(lblSex,componenetMap);
		infoPanel.add(lblSex);
		
		
		lblSexStatus = new JLabel();
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
		
		
		lblStateStatus = new JLabel();
		lblStateStatus.setBounds(170,120, 100, 25);
		utility.setComponenet(lblStateStatus,componenetMap);
		infoPanel.add(lblStateStatus);
		
		JLabel lblDist = new JLabel("Dist.");
		lblDist.setFont(new Font("Andalus", Font.BOLD,15));
		lblDist.setBounds(280,120, 60, 25);
		utility.setComponenet(lblDist,componenetMap);
		infoPanel.add(lblDist);
		
		
		lblDistStatus =new JLabel();
		lblDistStatus.setBounds(350,120, 100, 25);
		utility.setComponenet(lblDistStatus,componenetMap);
		infoPanel.add(lblDistStatus);
		
		JLabel lblTal = new JLabel("Tal.");
		lblTal.setFont(new Font("Andalus", Font.BOLD,15));
		lblTal.setBounds(110,160, 60, 25);
		utility.setComponenet(lblTal,componenetMap);
		infoPanel.add(lblTal);
		
		
		lblTalStatus = new JLabel();
		lblTalStatus.setBounds(170,160, 150, 25);
		utility.setComponenet(lblTalStatus,componenetMap);
		infoPanel.add(lblTalStatus);
		
		JLabel lblVillage = new JLabel("Gaon");
		lblVillage.setFont(new Font("Andalus", Font.BOLD,15));
		lblVillage.setBounds(280,160, 60, 25);
		utility.setComponenet(lblVillage,componenetMap);
		infoPanel.add(lblVillage);
		
		
		lblVillageStatus = new JLabel();
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
		
		
		lblDobStatus = new JLabel();
		lblDobStatus.setBounds(170,220, 150, 25);
		utility.setComponenet(lblDobStatus,componenetMap);
		infoPanel.add(lblDobStatus);
		
		JLabel lblCont = new JLabel("Mob No.");
		lblCont.setFont(new Font("Andalus", Font.BOLD,15));
		lblCont.setBounds(280,220, 60, 25);
		utility.setComponenet(lblCont,componenetMap);
		infoPanel.add(lblCont);
		
		
		lblContStatus = new JLabel();
		lblContStatus.setBounds(350,220, 150, 25);
		utility.setComponenet(lblContStatus,componenetMap);
		infoPanel.add(lblContStatus);
		
		JLabel lblWard = new JLabel("Ward No.");
		lblWard.setFont(new Font("Andalus", Font.BOLD,15));
		lblWard.setBounds(450,220, 100, 25);
		utility.setComponenet(lblWard,componenetMap);
		infoPanel.add(lblWard);
		
		
		lblWardStatus = new JLabel();
		lblWardStatus.setBounds(540,220, 50, 25);
		utility.setComponenet(lblWardStatus,componenetMap);
		infoPanel.add(lblWardStatus);
		
		
		JLabel lblAssignMemberID = new JLabel("Member ID",SwingConstants.CENTER);
		lblAssignMemberID.setBounds(5, 310, 150, 30);
		utility.setComponenet(lblAssignMemberID,componenetMap);
		infoPanel.add(lblAssignMemberID);
		
		
		
		txtAssignMemberID = new JTextField();
		txtAssignMemberID.setEditable(false);
		txtAssignMemberID.setBounds(160, 310, 200, 30);
		utility.setComponenet(txtAssignMemberID,componenetMap);
		infoPanel.add(txtAssignMemberID);
		
		for (Entry<String, JComponent> entry : componenetMap.entrySet())
		{
			entry.getValue().setVisible(false);
		}
	}
	public JTextField getTxtSelectFamilyID() {
		return txtSelectFamilyID;
	}

	public void setTxtSelectFamilyID(JTextField txtSelectFamilyID) {
		this.txtSelectFamilyID = txtSelectFamilyID;
	}

	public List getListOfHead() {
		return listOfHead;
	}

	public void setListOfHead(List listOfHead) {
		this.listOfHead = listOfHead;
	}

	public List getListOfMembers() {
		return listOfMembers;
	}

	public void setListOfMembers(List listOfMembers) {
		this.listOfMembers = listOfMembers;
	}

	public Map<String, JComponent> getComponenetMap() {
		return componenetMap;
	}

	public void setComponenetMap(Map<String, JComponent> componenetMap) {
		this.componenetMap = componenetMap;
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

	public JTextField getTxtAssignMemberID() {
		return txtAssignMemberID;
	}

	public void setTxtAssignMemberID(JTextField txtAssignMemberID) {
		this.txtAssignMemberID = txtAssignMemberID;
	}

}
