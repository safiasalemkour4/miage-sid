package tools;

import java.util.ArrayList;

/**
 * Class UserBean
 * Represente un utilisateur dans notre annuaire
 * @authors florian Collignon & Arnaud Knobloch
 */

public class UserBean 
{
	/** Le prenom de l'utilisateur */
	private String firstname;
	
	/** Le nom de l'utilisateur */
	private String name;
	
	/** Le login de l'utilisateur */
	private String login;
	
	/** Le mot de passe de l'utilisateur */
	private String password;
	
	/** l'adresse email de l'utilisateur */
	private String email;
	
	private ArrayList<String> groupList;
	
	/**
	 * Constructeur par defaut d'un utilisateur
	 */
	
	public UserBean() 
	{
		this.firstname = "";
		this.name = "";
		this.login = "";
		this.password = "";
		this.email = "";
		this.groupList = new ArrayList<String>();
		this.groupList.add("default");
	}
	
	public UserBean(String firstname, String name, String login, String password, String email) 
	{
		this.firstname = firstname;
		this.name = name;
		this.login = login;
		this.password = password;
		this.email = email;
		this.groupList = new ArrayList<String>();
		this.groupList.add("default");
	}
	/**
	 * Getter Surname
	 * @return le prenom de l'utilisateur
	 */
	
	public String getFirstname() 
	{
		return firstname;
	}

	/**
	 * Getter Name
	 * @return le nom de l'utilisateur
	 */
	
	public String getName() 
	{
		return name;
	}

	/**
	 * Getter Login
	 * @return le login de l'utilisateur
	 */
	
	public String getLogin() 
	{
		return login;
	}

	/**
	 * Getter Password
	 * @return le password de l'utilisateur
	 */
	
	public String getPassword() 
	{
		return password;
	}

	/**
	 * Getter Email
	 * @return l'adresse email de l'utilisateur
	 */
	
	public String getEmail() 
	{
		return email;
	}
	
	/**
	 * Getter Groups
	 * @return les groupes de l'uilisateur
	 */
	
	public ArrayList<String> getGroupList() 
	{
		return groupList;
	}

	/**
	 * Setter Surname
	 * @param surname le prenom de l'utilisateur
	 */
	
	public void setFirstname(String firstname) 
	{
		this.firstname = firstname;
	}

	/**
	 * Setter Name
	 * @param name le nom de l'utilisateur
	 */
	
	public void setName(String name) 
	{
		this.name = name;
	}

	/**
	 * Setter Login
	 * @param login le login de l'utilisateur
	 */
	
	public void setLogin(String login) 
	{
		this.login = login;
	}

	/**
	 * Setter Password
	 * @param password le password de l'utilisateur
	 */
	
	public void setPassword(String password)
	{
		this.password = password;
	}

	/**
	 * Setter Email
	 * @param email l'email de l'utilisateur
	 */
	public void setEmail(String email) 
	{
		this.email = email;
	}


	public void setGroupList(ArrayList<String> groupList) 
	{
		this.groupList = groupList;
	}
	
	public boolean verifyPwd(String pwd)
	{
		boolean result = false;
		
			if (this.password.compareTo(pwd) == 0)
				result = true;
			
		return result;
	}
	
	@Override
	public String toString()
	{
		String result = "<tr align='center'><td>" + this.login + "</td><td>" + this.password + "</td><td>" + this.name + "</td><td>" + this.firstname + "</td><td>" + this.email + "</td><td align='left'><ul type='square'>";
		
		for (String g:this.groupList)
			result += "<li>" + g + "</li>";
		
		result += "</ul></td></tr>";
		
		return result;
	}

}
