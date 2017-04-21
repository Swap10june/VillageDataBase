package dbutil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import Constants.DBQueryConstants;
import beans.SFamily;
import beans.SMember;

public class Dao
{
	private Connection sqliteConnection = null;
	
	public Dao() 
	{
		// TODO Auto-generated constructor stub
	}

	public void updateFamily(List<SFamily> familyList)
	{
		PreparedStatement pStmtFamily = null;
		try 
		{
			
			if(sqliteConnection==null)
				sqliteConnection = SQliteConnection.getSQliteConnection(DBQueryConstants.DB_FILE_NAME);
			
			for (int i = 0; i < familyList.size(); i++)
			{
				SFamily family= familyList.get(i);
				pStmtFamily = sqliteConnection.prepareStatement(DBQueryConstants.INSERT_FAMILY_QUERY);
				pStmtFamily.setString(1,family.getFamilyId().toUpperCase());
				pStmtFamily.setString(2,family.getFamilyHead().toUpperCase());
				pStmtFamily.executeUpdate();
				updateMember(family.getMembers());
					
			}
			pStmtFamily.close();
			
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private void updateMember(List<SMember> memberList)
	{
		PreparedStatement pStmtFamily = null;
		try 
		{
			
			if(sqliteConnection==null)
				sqliteConnection = SQliteConnection.getSQliteConnection(DBQueryConstants.DB_FILE_NAME);
			
			for (int i = 0; i < memberList.size(); i++)
			{
				SMember member= memberList.get(i);
				pStmtFamily = sqliteConnection.prepareStatement(DBQueryConstants.INSERT_SMEMBER_QUERY);
				pStmtFamily.setString(1,member.getMember_id().toUpperCase());
				pStmtFamily.setString(2,member.getFamily_id().toUpperCase());
				
				pStmtFamily.setString(3,member.getM_name_e().toUpperCase());
				pStmtFamily.setString(4,member.getM_name_m().toUpperCase());
				
				pStmtFamily.setString(5,member.getFamily_head_status().toUpperCase());
				pStmtFamily.setString(6,member.getM_sex().toUpperCase());
				
				pStmtFamily.setString(7,member.getM_state().toUpperCase());
				pStmtFamily.setString(8,member.getM_dist().toUpperCase());
				
				pStmtFamily.setString(9,member.getM_tal().toUpperCase());
				pStmtFamily.setString(10,member.getM_gaon().toUpperCase());
				
				pStmtFamily.setString(11,member.getM_dob().toUpperCase());
				pStmtFamily.setInt(12,member.getM_ward());
				
				pStmtFamily.setString(13,member.getM_contact().toUpperCase());
				
				
				pStmtFamily.executeUpdate();
					
			}
			pStmtFamily.close();
			
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
