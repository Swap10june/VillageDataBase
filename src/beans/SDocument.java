package beans;

public class SDocument
{
	private String dId;
	private String dName;
	private String dPath;
	private String dOwnerName;
	
	
	public SDocument (String documentID, String name, String path,String dOwnerName)
	{
		this.dId = documentID;
		this.dName = name;
		this.dPath = path;
		this.setdOwnerName(dOwnerName);
	}
	
	public String getDocumentID() {
		return dId;
	}
	public void setDocumentID(String documentID) {
		this.dId = documentID;
	}
	public String getdName() {
		return dName;
	}
	public void setdName(String dName) {
		this.dName = dName;
	}
	public String getdPath() {
		return dPath;
	}
	public void setdPath(String dPath) {
		this.dPath = dPath;
	}
	/**
	 * @return the dOwnerName
	 */
	public String getdOwnerName() {
		return dOwnerName;
	}
	/**
	 * @param dOwnerName the dOwnerName to set
	 */
	public void setdOwnerName(String dOwnerName) {
		this.dOwnerName = dOwnerName;
	}
	
}