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
	 
	private ArrayList<String> existingGroupList = new ArrayList<String>();
	 
	 /**
	 * 
	 */
	public DirectoryBean()
	 {
		this.userList.add(new UserBean("Florian","Collignon","login","pass","login@fai.fr"));
		this.userList.add(new UserBean("Arnaud","Knobloch","ak","pass","ak@fai.fr"));
		this.existingGroupList.add("default");
		this.existingGroupList.add("professeur");
		this.existingGroupList.add("etudiant");
	 }

	public ArrayList<UserBean> getUserList()
	{
		return userList;
	}

	public void setUserList(ArrayList<UserBean> userList) 
	{
		this.userList = userList;
	}

	public ArrayList<String> getExistingGroupList() 
	{
		return existingGroupList;
	}

	public void setExistingGroupList(ArrayList<String> existingGroupList) 
	{
		this.existingGroupList = existingGroupList;
	}
	
	public String addUser(UserBean newUser)
	{
		String result = ""; 
		
		if (this.findUserLogin(newUser.getLogin()) == 0)
		{
			result = "existingLog";
		}
		else if (this.findUserMail(newUser.getEmail()) == 0)
		{
			result = "existingMail";
		}
		else
			this.userList.add(new UserBean(newUser.getFirstname(),newUser.getName(),
					newUser.getLogin(),newUser.getPassword(),newUser.getEmail()));
		
		return result;
	}

	public void updateUser(UserBean updatingUser)
	{
		int idUser = findUserLogin(updatingUser.getLogin());
		
		this.userList.get(idUser).setGroupList(updatingUser.getGroupList());
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
	
	private int findUserMail(String email) 
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
}
