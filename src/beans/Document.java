package beans;

public class Document
{
	private String documentID;
	private String dName;
	private String dPath;
	
	
	public Document (String documentID, String name, String path)
	{
		this.documentID = documentID;
		this.dName = name;
		this.dPath = path;
	}
	public void display()
	{
		
		System.out.println("Document id : "+getDocumentID());
		System.out.println("Document Name : "+getdName());
		System.out.println("Document Path : "+getdPath());
		System.out.println("*****************************");
		
	}
	public String getDocumentID() {
		return documentID;
	}
	public void setDocumentID(String documentID) {
		this.documentID = documentID;
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
	
}