/**
 * 
 */
package tools;

import java.util.ArrayList;

/**
 * @authors florian Collignon & Arnaud Knobloch
 */
public class DirectoryBean 
{
	
	private ArrayList<UserBean> userList = new ArrayList<UserBean>(); 
	 
	private ArrayList<String> groupList = new ArrayList<String>();
	 
	 /**
	 * 
	 */
	public DirectoryBean()
	 {
		this.userList.add(new UserBean("Florian","Collignon","login","pass","login@fai.fr"));
		this.userList.add(new UserBean("Arnaud","Knobloch","ak","pass","ak@fai.fr"));
		this.groupList.add("default");
		this.groupList.add("professeur");
		this.groupList.add("etudiant");
	 }

	public DirectoryBean(int test)
	 {

		this.groupList.add("default");
		this.groupList.add("professeur");
		this.groupList.add("etudiant");
	 }
	
	public ArrayList<UserBean> getUserList()
	{
		return userList;
	}

	public void setUserList(ArrayList<UserBean> userList) 
	{
		this.userList = userList;
	}

	public ArrayList<String> getGroupList() 
	{
		return groupList;
	}

	public void setGroupList(ArrayList<String> groupList) 
	{
		this.groupList = groupList;
	}
	
	public String addUser(UserBean newUser)
	{
		String result = ""; 
		
		if (this.findUserLogin(newUser.getLogin()) != -1)
		{
			result = "existingLog";
		}
		else if (this.findUserMail(newUser.getEmail()) != -1)
		{
			result = "existingMail";
		}
		else
		{
			this.userList.add(new UserBean(newUser.getFirstname(),newUser.getName(),
					newUser.getLogin(),newUser.getPassword(),newUser.getEmail()));
			
			/* Ajout du groupe a la list de groupe de l'annuaire si l'utilisateur fait parti d'un groupe
			 * non repertorie
			 */
			/*for (String groupName : newUser.getGroupList()) {
				
				if (!this.groupList.contains(groupName)) {
					this.groupList.add(groupName);
				}
			}*/

		}
		
		return result;
	}

	public void updateUser(UserBean updatingUser)
	{
		int idUser = findUserLogin(updatingUser.getLogin());
		
		UserBean oldUser = this.userList.get(idUser);
		
		oldUser.setFirstname(updatingUser.getFirstname());
		oldUser.setName(updatingUser.getName());
		oldUser.setPassword(updatingUser.getPassword());
		oldUser.setGroupList(updatingUser.getGroupList());
	}
	
	public int findUserLogin(String login)
	{
		int result = -1;
		int meter = 0;
		
		for (UserBean u: this.userList)
		{
			if (u.getLogin().compareTo(login) == 0)
				result = meter;
			meter++;
		}
		return result;
	}
	
	public int findUserMail(String email) 
	{
		int result = -1;
		int meter = 0;
		
		for (UserBean u: this.userList)
		{
			if (u.getEmail().compareTo(email) == 0)
				result = meter;
			meter++;
		}
		return result;
	}
	
	public String printUsers()
	{
		String result = "";
		
		for (UserBean u: this.userList)
			result += u;
		
		return result;
	}
	
	public int login(String login, String password)
	{
		int result = -1;
		
		if (findUserLogin(login) != -1)
		{
			if (userList.get(findUserLogin(login)).getPassword().compareTo(password)==0)
				result = findUserLogin(login);
		}
		
		return result;
	}
	
	public void sendMessageToUser(UserBean userFrom, UserBean userTo, MessageBean message) {
		
		userFrom.addMessageSend(message);
		userTo.addMessageReceive(message);
	}
	
	public void sendMessageToGroup(UserBean userFrom, String groupName, MessageBean message) {
		
		userFrom.addMessageSend(message);
		
		for (UserBean user : userList) {
			if (user.getGroupList().contains(groupName)) {
				user.addMessageReceive(message);
			}
		}
	}
	
	public void sendMessageToList(UserBean userFrom, MessageBean message) {
		
		userFrom.addMessageSend(message);
		
		for (UserBean user : userList) {
				user.addMessageReceive(message);
		}
	}
}