package beans;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SFamily
{
	private String familyId;
	private List<SMember> members = new ArrayList<>();
	private String FamilyHead;
	
	public SFamily(String familyId)
	{
		this.familyId= familyId;
	}
	public SFamily() {
		// TODO Auto-generated constructor stub
	}
	public void addMemberIntoFamily(SMember member)
	{
		this.members.add(member);
	}
	public void deleteMemberFromFamily(SMember member)
	{
		this.members.remove(member);
	}
	public void deleteAllMembersFromFamily()
	{
		this.members.clear();
	}
	public void addMembersIntoFamily(SMember[]members)
	{
		this.members.addAll(Arrays.asList(members));
	}
	
	
	public String getFamilyId() {
		return familyId;
	}

	public void setFamilyId(String familyId) {
		this.familyId = familyId;
	}

	public List<SMember> getMembers() {
		return members;
	}

	public void setMembers(List<SMember> members) {
		this.members = members;
	}
	public SMember getMember(String memberID)
	{
		SMember member = null;
		for (int i = 0; i < members.size(); i++)
		{
			if(members.get(i).getMember_id().equalsIgnoreCase(memberID))
				member = members.get(i);
		}
		return member;
	}
	/**
	 * @return the familyHead
	 */
	public String getFamilyHead() {
		return FamilyHead;
	}
	/**
	 * @param familyHead the familyHead to set
	 */
	public void setFamilyHead(String familyHead) {
		FamilyHead = familyHead;
	}
}

