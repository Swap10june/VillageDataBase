package util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import beans.SFamily;
import beans.SMember;

public class Utils 
{
	public static void applyBasicSettingsOnWindow(JDialog owner, String title) 
	{
		owner.setLayout(null);
	    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - owner.getWidth()) / 4);
	    int y = (int) ((dimension.getHeight() - owner.getHeight()) / 5);
	    owner.setLocation(200,100);
	    owner.setSize(new Dimension(900, 602));
	    owner.setModal(true);
	    owner.setResizable(false);
	    owner.setTitle(title);
	    owner.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	    
	    
	    JLabel headerLabel = new JLabel("Village Management Syastem",SwingConstants.CENTER);
	    headerLabel.setBounds(0, 0, 894, 30);
	    Font font = new Font("Courier", Font.BOLD,18);
        //set font for JLabel
	    headerLabel.setFont(font);
	    Border border = BorderFactory.createLineBorder(Color.blue);
	    headerLabel.setBorder(border);
	    owner.add(headerLabel);
	    
	    JLabel footerLabel = new JLabel("Unity Infotech Group (I) Pvt Ltd",SwingConstants.CENTER);
	    footerLabel.setBounds(0, 542, 894, 30);
	    footerLabel.setFont(font);
	    footerLabel.setBorder(border);
	    owner.add(footerLabel);
	}

	public static void applyBasicSettingsOnWindow1(JDialog owner, String string)
	{
		owner.setLayout(null);
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - owner.getWidth()) / 10);
	    int y = (int) ((dimension.getHeight() - owner.getHeight()) / 10);
	    owner.setLocation(300,100);
	    owner.setSize(new Dimension(600, 502));
	    owner.setModal(true);
	    owner.setResizable(false);
	    owner.setTitle(string);
	    owner.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	    
	    
	    JLabel headerLabel = new JLabel("Village Management Syastem",SwingConstants.CENTER);
	    headerLabel.setBounds(0, 0, 594, 30);
	    Font font = new Font("Courier", Font.BOLD,18);
        //set font for JLabel
	    headerLabel.setFont(font);
	    Border border = BorderFactory.createLineBorder(Color.blue);
	    headerLabel.setBorder(border);
	    owner.add(headerLabel);
	    
	    JLabel footerLabel = new JLabel("Unity Infotech Group (I) Pvt Ltd",SwingConstants.CENTER);
	    footerLabel.setBounds(0, 442, 594, 30);
	    footerLabel.setFont(font);
	    footerLabel.setBorder(border);
	    owner.add(footerLabel);
	}
	public static String getMaxFamilyID()
    {
        try
        {
            ResultSet set = Utils.querySELECT("SELECT MAX(family_id) FROM SFAMILY");
            String familyID=null;
            while(set.next())
            {
                familyID = String.valueOf(set.getInt(1)+1);
            }
            return familyID;
        } catch (ClassNotFoundException | SQLException ex)
        {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Error";
    }
    public  static ResultSet querySELECT(String Query) throws ClassNotFoundException, SQLException
    {
	  
        System.out.println("Select query fired: "+Query);
        ResultSet set=null;
        Statement statement = DBConnection.getConnectionInstance().createStatement();
        set=statement.executeQuery(Query);
        return set;     
	
    }

	public static String getMaxMemberID(String familyId)
	{
		try
        {
            ResultSet set = Utils.querySELECT("SELECT MAX(member_id) FROM SMEMBER where family_id="+familyId);
            String memberID=null;
            while(set.next())
            {
            	memberID = String.valueOf(set.getInt(1)+1);
            }
            return memberID;
        } catch (ClassNotFoundException | SQLException ex)
        {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Error";
	}
	public  static int queryINSERT(Object table) throws ClassNotFoundException, SQLException
	{	
		try
		{
			//String query;
			
			//PreparedStatement pStmtFamily = DBConnection.getConnectionInstance().prepareStatement("UPDATE EMPLOYEES SET SALARY = ? WHERE ID = ?");	
			if(table instanceof SFamily)
			{
				String familyId= ((SFamily) table).getFamilyId().split("-")[1];
				
				PreparedStatement pStmtFamily = DBConnection.getConnectionInstance().prepareStatement("insert into SFAMILY values(?,?)");
				pStmtFamily.setInt(1,Integer.parseInt(familyId));
				pStmtFamily.setString(2,((SFamily) table).getMembers().get(0).getM_name_e());
				int status = pStmtFamily.executeUpdate();
				pStmtFamily.close();
				/*if(status<0)
				{
					JOptionPane.showMessageDialog (null, "Failure...!", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					JOptionPane.showMessageDialog (null, status+" Family Inserted...!", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
				}*/
				return status;
			}
			if(table instanceof SMember)
			{
				SMember member = (SMember) table;
				String memberID= member.getMember_id().split("-")[1];
				String FamilyID= member.getFamily_id().split("-")[1];
				
				PreparedStatement pStmtMember = DBConnection.getConnectionInstance().prepareStatement
						("insert into SMember values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
				pStmtMember.setInt(1,Integer.parseInt(memberID));
				pStmtMember.setInt(2,Integer.parseInt(FamilyID));
				pStmtMember.setString(3, member.getM_name_e());
				pStmtMember.setString(4, member.getM_name_m());
				if(member.getFamily_head_status()!=null)
					pStmtMember.setString(5, member.getFamily_head_status());
				else
					pStmtMember.setString(5, "No");
				pStmtMember.setString(6, member.getM_sex());
				pStmtMember.setString(7, member.getM_state());
				pStmtMember.setString(8, member.getM_dist());
				pStmtMember.setString(9, member.getM_tal());
				pStmtMember.setString(10, member.getM_gaon());
				pStmtMember.setString(11, member.getM_dob());
				pStmtMember.setInt(12, member.getM_ward());
				pStmtMember.setString(13, member.getM_contact());
				
				int status = pStmtMember.executeUpdate();
				pStmtMember.close();
				/*if(status<0)
				{
					JOptionPane.showMessageDialog (null, "Failure...!", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					JOptionPane.showMessageDialog (null, status+" Family Inserted...!", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
				}*/
				return status;
			}
			
		} catch (Exception e)
		{
			// TODO: handle exception
		}
		return 0;
	}

	public static void queryUpdate(SFamily family, String columName,
			String columValue)
	{
		String query="update SFamily set "+columName+"="+columValue;
		System.out.println("update query filred: "+query);
		Statement statement;
	        try 
	        {
				statement = DBConnection.getConnectionInstance().createStatement();
				statement.executeQuery(query);
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        
	}

	public static ArrayList<String> queryMultiColumnSelect(String table, Map<String,String> columnNames)
	{
		ArrayList<String> list = new ArrayList<>();
		String columName = "";
		for (Map.Entry<String, String> entry :columnNames.entrySet())
		{
			list.add(entry.getValue());
		}
		for (int i = 0; i < list.size(); i++)
		{
			columName+=list.get(i);
			if(i!=list.size()-1)
			{
				columName+=" , ";
			}
		}
		try
		{
			
			ResultSet set = Utils.querySELECT("SELECT "+columName+" FROM "+table);
			String prefix = "FID-";
			list.clear();
			while(set.next())
			{
				list.add(set.getString(2)+":"+prefix+":"+String.valueOf(set.getInt(1)));
			}
			return list;
		} catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;		
	}
	public static void setComponenet(JComponent component, Map<String,JComponent> componenetMap)
	{
		component.setName(component.toString());
		componenetMap.put(component.getName(), component);
	}
}
