package tools;

/**
 * @authors florian Collignon & Arnaud Knobloch
 */

public class DirectoryMsg
{	
	public DirectoryMsg()
	{
	}
	
	public String error (String typeError)
	{
		String describe = "";
		if (typeError.compareTo("empty")==0)
			describe = "Vous devez remplir tous les champs !!!";
		else if (typeError.compareTo("pwdConf")==0)
			describe = "Votre mot de passe et différent de la confirmation du mot de passe !!!";
		else if (typeError.compareTo("pwdEmpty")==0)
			describe = "Pour modifier votre mot de passe vous devez saisir l'ancien, le nouveau et confirmer le nouveau impérativement !!!";
		else if (typeError.compareTo("pwdFalse")==0)
			describe = "Votre ancien mot de passe est incorrect veuillez le resaisir !!!";
		else if (typeError.compareTo("noMdp")==0)
			describe = "Veuillez saisir votre mot de passe !!!";
		else if (typeError.compareTo("noLogin")==0)
			describe = "Veuillez saisir votre login !!!";
		else if (typeError.compareTo("logFalse")==0)
			describe = "Identifiaction incorrect : Login ou Mot de passe mal saisi !!!";
		else if (typeError.compareTo("alreadyGroup")==0)
			describe = "Vous etes deja membre de ce groupe !!!";
		else if (typeError.compareTo("existingLog")==0)
			describe = "Ce login existe deja, veuillez en choisir un autre !!!";
		else
			describe = typeError + "Erreur inconnue";
		return describe;
	}
	
	public String info (String typeInfo)
	{
		String describe = "";
		if (typeInfo.compareTo("save")==0)
			describe = "Vos informations ont bien été sauvegardées !!!";
		else if (typeInfo.compareTo("group")==0)
			describe = "Vous avez bien été rattachée au groupe !!!";
		else if (typeInfo.compareTo("inscr")==0)
			describe = "Votre inscription a bien été enregistrée !!!";
		else if (typeInfo.compareTo("addGroup")==0)
			describe = "Votre adhésion a bien été enregistrée !!!";
		else
			describe = typeInfo + "Info inconnue";
		return describe;
	}
}
