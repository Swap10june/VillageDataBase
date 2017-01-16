package beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SMember
{
	private String member_id;
	private String family_id;
	private String m_name_e;
	private String m_name_m;
	private String family_head_status;
	
	private String m_sex;
	private String m_state;
	private String m_dist;
	private String m_tal;
	private String m_gaon;
	
	private String m_dob;
	private int m_ward;
	private String m_contact;
	private List<Document> documents = new ArrayList<>();
	
	
	
	
	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getFamily_id() {
		return family_id;
	}

	public void setFamily_id(String family_id) {
		this.family_id = family_id;
	}

	public String getM_name_e() {
		return m_name_e;
	}

	public void setM_name_e(String m_name_e) {
		this.m_name_e = m_name_e;
	}

	public String getM_name_m() {
		return m_name_m;
	}

	public void setM_name_m(String m_name_m) {
		this.m_name_m = m_name_m;
	}

	public String getFamily_head_status() {
		return family_head_status;
	}

	public void setFamily_head_status(String family_head_status) {
		this.family_head_status = family_head_status;
	}

	public String getM_sex() {
		return m_sex;
	}

	public void setM_sex(String m_sex) {
		this.m_sex = m_sex;
	}

	public String getM_state() {
		return m_state;
	}

	public void setM_state(String m_state) {
		this.m_state = m_state;
	}

	public String getM_dist() {
		return m_dist;
	}

	public void setM_dist(String m_dist) {
		this.m_dist = m_dist;
	}

	public String getM_tal() {
		return m_tal;
	}

	public void setM_tal(String m_tal) {
		this.m_tal = m_tal;
	}

	public String getM_gaon() {
		return m_gaon;
	}

	public void setM_gaon(String m_gaon) {
		this.m_gaon = m_gaon;
	}

	public String getM_dob() {
		return m_dob;
	}

	public void setM_dob(String m_dob) {
		this.m_dob = m_dob;
	}

	public int getM_ward() {
		return m_ward;
	}

	public void setM_ward(int m_ward) {
		this.m_ward = m_ward;
	}

	public String getM_contact() {
		return m_contact;
	}

	public void setM_contact(String m_contact) {
		this.m_contact = m_contact;
	}

	public List<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}

	public void display()
	{
		System.out.println("**************Documents**************");
		for (int i = 0; i < documents.size(); i++)
		{
			
			//System.out.println("Document id : "+documents.get(i).getDocumentID());
			documents.get(i).display();
		}
	}
	
	public void addDocumentForMember(Document document)
	{
		this.documents.add(document);
	}
	public void removeDocumentofMember(Document document)
	{
		this.documents.remove(document);
	}
	public void removeAllDocuments()
	{
		this.documents.clear();
	}
	public void addMultipleDocuments(Document[] documents)
	{
		this.documents.addAll(Arrays.asList(documents));
	}
	public static SMember createMemberFromDBInstance(ResultSet set)
	{
		SMember member = new SMember();
		try
		{
			while(set.next())
			{
				member.setMember_id(String.valueOf(set.getInt("member_id")));
				
				member.setFamily_id(String.valueOf(set.getInt("family_id")));
				member.setM_name_e(set.getString("m_name_e"));
				member.setM_name_m(set.getString("m_name_m"));
				member.setFamily_head_status(set.getString("family_head_status"));
				
				member.setM_sex(set.getString("m_sex"));
				member.setM_state(set.getString("m_state"));
				member.setM_dist(set.getString("m_dist"));
				member.setM_tal(set.getString("m_tal"));
				member.setM_gaon(set.getString("m_gaon"));
				
				member.setM_dob(set.getString("m_dob"));
				member.setM_ward(set.getInt("m_ward"));
				member.setM_contact(set.getString("m_contact"));
			}
			return member;
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
}

