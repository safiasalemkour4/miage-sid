/**
 * 
 */
package tools;

import java.util.ArrayList;

/**
 * @authors florian Collignon & Arnaud Knobloch
 */
public class Global 
{
	
	private ArrayList<UserBean> userList = new ArrayList<UserBean>(); 
	 
	private ArrayList<String> existingGroupList = new ArrayList<String>();
	 
	 /**
	 * 
	 */
	public Global()
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
	
	public boolean addUser(UserBean newUser)
	{
		boolean result = false;
		
		if (!userList.contains(newUser))
		{
			this.userList.add(newUser);
			result = true;
		}
		
		return result;
	}
	
	public void updateUser(UserBean updatingUser)
	{
		int idUser = findUser(updatingUser.getLogin());
		
		this.userList.set(idUser,updatingUser);
	}
	
	public int findUser(String login)
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
}
