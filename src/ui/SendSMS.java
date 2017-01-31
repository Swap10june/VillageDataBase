package ui;

import handlers.SendSMSBtnsHandler;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import util.Utils;

public class SendSMS extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4751620116396415943L;
	private JPanel upperBody = new JPanel();
	

	private JPanel middlePanel = new JPanel();
	

	private JPanel footerPanel = new JPanel();	

	private JTextField textWard = new JTextField();
	private JTextField textSex = new JTextField();
	private JTextField textSurName = new JTextField();
	private JTextField textHeadStatus = new JTextField();
	

	

	public SendSMS(JDialog owner)
	{
		super(owner);
		Utils.getUtilityInstance().applyBasicSettingsOnWindow(owner,"Send SMS");
		initUI(owner);
		owner.setVisible(true);
	}

	private void initUI(JDialog owner)
	{
		
		upperBody.setBounds(0, 110, 894, 1);
		upperBody.setBorder(BorderFactory.createLineBorder(Color.black));
		owner.add(upperBody);
		
		JLabel lblSelectBY = new JLabel("Select By:");
		lblSelectBY.setBounds(10, 50, 100, 25);
		owner.add(lblSelectBY);
		
		
		JLabel lblWard = new JLabel("Enter Ward No");
		lblWard.setBounds(120, 40, 100, 25);
		owner.add(lblWard);
		
		textWard.setBounds(120, 70, 100, 25);
		textWard.setToolTipText("Ward No");
		textWard.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char vChar = e.getKeyChar();
                if (!(Character.isDigit(vChar)
                        || (vChar == KeyEvent.VK_BACK_SPACE)
                        || (vChar == KeyEvent.VK_DELETE)))
                {
                    e.consume();
                }
            }
        });
		owner.add(textWard);
		
		JLabel lblAdd2 = new JLabel("Sex");
		lblAdd2.setBounds(350, 40, 100, 25);
		owner.add(lblAdd2);
		
		textSex.setBounds(350, 70, 100, 25);
		textSex.setToolTipText("Sex");
		owner.add(textSex);
		
		JLabel lblSurName = new JLabel("Enter Surname");
		lblSurName.setBounds(460, 40, 100, 25);
		owner.add(lblSurName);
		
		textSurName.setBounds(460, 70, 100, 25);
		textSurName.setToolTipText("Surname");
		owner.add(textSurName);
		
		JLabel lblAdd1 = new JLabel("Head Status");
		lblAdd1.setBounds(240, 40, 100, 25);
		owner.add(lblAdd1);
		
		textHeadStatus.setBounds(240, 70, 100, 25);
		textHeadStatus.setToolTipText("Head Status");
		owner.add(textHeadStatus);
		
		
		JButton BtnShow = new JButton("Show");
		BtnShow.setBounds(580, 50, 100, 25);
		owner.add(BtnShow);
		BtnShow.addActionListener(new SendSMSBtnsHandler(this,owner));
		
		middlePanel.setBounds(0, 110, 894, 390);
		middlePanel.setVisible(false);
		middlePanel.setBorder(BorderFactory.createLineBorder(Color.black));
		middlePanel.setBackground(Color.gray);
		owner.add(middlePanel);
		
		footerPanel.setBounds(0, 500, 894, 40);
		footerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		footerPanel.setBackground(Color.cyan);
		footerPanel.setVisible(false);
		owner.add(footerPanel);
		
		JButton btnSelectAll = new JButton("Select All");
        footerPanel.add(btnSelectAll);
        btnSelectAll.addActionListener(new SendSMSBtnsHandler(this,owner));
        
        JButton btnDeSelectAll = new JButton("De-Select");
        footerPanel.add(btnDeSelectAll);
        btnDeSelectAll.addActionListener(new SendSMSBtnsHandler(this,owner));
        
        JButton btnSendSMS = new JButton("Send SMS");
        footerPanel.add(btnSendSMS);
        btnSendSMS.addActionListener(new SendSMSBtnsHandler(this,owner));
	}
	public JPanel getUpperBody() {
		return upperBody;
	}

	public void setUpperBody(JPanel upperBody) {
		this.upperBody = upperBody;
	}
	public JPanel getMiddlePanel() {
		return middlePanel;
	}

	public void setMiddlePanel(JPanel middlePanel) {
		this.middlePanel = middlePanel;
	}

	public JPanel getFooterPanel() {
		return footerPanel;
	}

	public void setFooterPanel(JPanel footerPanel) {
		this.footerPanel = footerPanel;
	}

	public JTextField getTextWard() {
		return textWard;
	}

	public void setTextWard(JTextField textWard) {
		this.textWard = textWard;
	}

	public JTextField getTextSex() {
		return textSex;
	}

	public void setTextSex(JTextField textSex) {
		this.textSex = textSex;
	}

	public JTextField getTextSurName() {
		return textSurName;
	}

	public void setTextSurName(JTextField textSurName) {
		this.textSurName = textSurName;
	}

	public JTextField getTextHeadStatus() {
		return textHeadStatus;
	}

	public void setTextHeadStatus(JTextField textHeadStatus) {
		this.textHeadStatus = textHeadStatus;
	}
}
