package junit;

import tools.DirectoryBean;
import tools.MessageBean;
import tools.UserBean;
import junit.framework.Assert;
import junit.framework.TestCase;

public class Test extends TestCase {

	private UserBean user_from, user_to;
	private MessageBean message;
	private DirectoryBean directory;
	

	public void setUp() {
		
		user_from = new UserBean("Arnaud", "Knobloch", "ak", "akpwd", "ak@gmail.com");
		user_to = new UserBean("Florian", "Collignon", "fc", "fcpwd", "fc@gmail.com");
		
		message = new MessageBean(1, user_from, user_to, "Sujet du Message", "Contenu du message", MessageBean.OK);
		
		directory = new DirectoryBean();
		directory.addUser(user_from);
		directory.addUser(user_to);
	}

	public void testUser() {
		
		/* On verifie les informations du l'utilisateur */
		Assert.assertEquals("Arnaud", user_from.getFirstname());
		Assert.assertEquals("Knobloch", user_from.getName());
		Assert.assertEquals("ak", user_from.getLogin());
		Assert.assertEquals("akpwd", user_from.getPassword());
		Assert.assertEquals("ak@gmail.com", user_from.getEmail());
		
		/* On verifie l'appartenance au groupe par defaut */
		Assert.assertTrue(user_from.getGroupList().size()==1);
		Assert.assertEquals("default", user_from.getGroupList().get(0));

		/* On verifie la methode de verification du mot de passe */
		Assert.assertFalse(user_from.verifyPwd("akwrongpwd"));
		Assert.assertTrue(user_from.verifyPwd("akpwd"));
	}
	
	public void testMessage() {
		
		/* On verifie les informations du message */
		Assert.assertEquals(1, message.getId());
		Assert.assertEquals("Arnaud", message.getFrom().getFirstname());
		Assert.assertEquals("Florian", message.getTo().getFirstname());
		Assert.assertEquals("Sujet du Message", message.getSubject());
		Assert.assertEquals("Contenu du message", message.getContent());
		
		/* On verifie l'etat du message */
		Assert.assertEquals(MessageBean.OK, message.getState());
		Assert.assertNotSame(MessageBean.ERROR, message.getState());
		
		/* On test un etat incoherent */
		message.setState(12345);
		Assert.assertEquals(MessageBean.ERROR, message.getState());
		
		/* On simule l'envoit d'un message */
		directory.sendMessageToUser(user_from, user_to, message);
		Assert.assertEquals(message.getId(), user_to.getMessageReceiveList().get(0).getId());
		Assert.assertEquals(message.getId(), user_from.getMessageSendList().get(0).getId());
	}
	
	public void testDirectory() {
		
		Assert.assertEquals("3", directory.getGroupList().size());
		Assert.assertEquals("2", directory.getUserList().size());
		
		Assert.assertEquals(user_from.getLogin(), directory.getUserList().get(directory.findUserLogin("ak")).getLogin());
		Assert.assertEquals(user_to.getLogin(), directory.getUserList().get(directory.findUserLogin("fc")).getLogin());
	}
	

	
	public void tearDown() {
		
	}

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		junit.textui.TestRunner.run(Test.class);

	}

}
