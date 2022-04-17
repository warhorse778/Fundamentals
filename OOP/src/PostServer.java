import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
interface SetLoader {
	Set<PostProfile> loadData();
}

class PostProfile implements Comparable<PostProfile> {
   private String user;
   private String password;
   public PostProfile(String usr, String pwd){
	user = usr;
	password = pwd;
   }
   @Override
   public boolean equals(Object from){
	   return user.equals(((PostProfile)from).user) &&
			   password.equals(((PostProfile)from).password);
   }
   public String getUser() {
	   return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPass() {
		return password;
	}
	public void setPass(String em) {
		password = em;
	}
	public int compareTo( PostProfile from ){
		return user.compareTo(((PostProfile)from).user)==0?
				password.compareTo(((PostProfile)from).password):
				user.compareTo(((PostProfile)from).user);
   }
   public String toString() {
      return ("user : " + user);      
   }
}

class ProfileManager implements SetLoader {
	private String fileName=null;
	public ProfileManager(String source){
		fileName=source;
	}
   public Set<PostProfile> loadData(){
	Set<PostProfile> collection = new TreeSet<PostProfile>();
	try{	
		Scanner readScaner = new Scanner(new File(fileName));
		while(readScaner.hasNextLine())	{
			collection.add(new PostProfile(readScaner.next(),readScaner.next()));
		}
		readScaner.close();
	}
	catch(FileNotFoundException e){
		System.out.println("File Not Found...");
	}
		
    return collection;
  }
}

public class PostServer {
	   private String serverIP;
	   private Set<PostProfile> accounts=new TreeSet<>();
	   public PostServer(String user, ProfileManager ldr){
		serverIP = user;
		accounts=ldr.loadData();
	   }
	   public String getserverIP(){
		return serverIP;
	   }
	   public String toString() {
	      return ("serverIP : " + serverIP + "\n : " + accounts.toString());      
	   }
	   public PostProfile findAccount(String usr, String pass){
		   PostProfile toFind=new PostProfile(usr,pass);
		   if (((TreeSet<PostProfile>)accounts).ceiling(toFind).equals(toFind)){
			   return (PostProfile)((TreeSet<PostProfile>)accounts).ceiling(toFind);
		   } else {
			   return null;
		   }
	   }
	   public List<String> findUsers(String user){
		   List<String> result = new ArrayList<>();
		   for (PostProfile p : accounts) {
	            if ((p.getUser()).equals(user)) {
	                result.add((p.getPass()));
	            }
	        }
		   return result;
	   }
	   public static void main(String[] args) {
		   PostServer objP2=new PostServer("123.123. 12.1.", new ProfileManager("PostServer.txt"));
		   System.out.println(objP2.toString());
		   String findUserName="U3333456";
		   PostProfile searchUser = objP2.findAccount(findUserName, "pass2");
		   System.out.println("Find User : "+searchUser);
		   List<String> searchSet=objP2.findUsers(findUserName);
		   if(searchSet!=null){
			   System.out.println("Found List of : "+findUserName+" are "+searchSet);
		   }else{
			   System.out.println("No Found List of : " +findUserName);
		   }
	}
}

