// file: OntoCDOntology.java generated by ontology bean generator.  DO NOT EDIT, UNLESS YOU ARE REALLY SURE WHAT YOU ARE DOING!
package protege;

import jade.content.onto.*;
import jade.content.schema.*;
import jade.util.leap.HashMap;
import jade.content.lang.Codec;
import jade.core.CaseInsensitiveString;

/** file: OntoCDOntology.java
 * @author ontology bean generator
 * @version 2008/12/2, 15:24:06
 */
public class OntoCDOntology extends jade.content.onto.Ontology  {
  //NAME
  public static final String ONTOLOGY_NAME = "ontoCD";
  // The singleton instance of this ontology
  private static ReflectiveIntrospector introspect = new ReflectiveIntrospector();
  private static Ontology theInstance = new OntoCDOntology();
  public static Ontology getInstance() {
     return theInstance;
  }


   // VOCABULARY
    public static final String STOPEVERYBODY="StopEverybody";
    public static final String OK="OK";
    public static final String VALIDERACHAT_REPONSE="reponse";
    public static final String VALIDERACHAT="ValiderAchat";
    public static final String DISPONIBLE_QTE="qte";
    public static final String DISPONIBLE_DISQUE="disque";
    public static final String DISPONIBLE="Disponible";
    public static final String NOUVELLEPHASE_NUMEROPHASE="numeroPhase";
    public static final String NOUVELLEPHASE="NouvellePhase";
    public static final String REPONSEDISPONIBILITE_DISQUE="disque";
    public static final String REPONSEDISPONIBILITE_PRIX="prix";
    public static final String REPONSEDISPONIBILITE="ReponseDisponibilite";
    public static final String DVD="DVD";
    public static final String DISQUE="Disque";
    public static final String CD="CD";
    public static final String SERVICE_VENTE_CD_CLIENT = "VenteCD_Client";
	public static final String SERVICE_VENTE_DVD_CLIENT = "VenteDVD_Client";
	public static final String SERVICE_START = "Start";
	public static final String SERVICE_VENTE_CD_ENT = "VenteDVD_Ent";
	public static final String SERVICE_VENTE_DVD_ENT = "VenteCD_Ent";

  /**
   * Constructor
  */
  private OntoCDOntology(){ 
    super(ONTOLOGY_NAME, BasicOntology.getInstance());
    try { 

    // adding Concept(s)
    ConceptSchema cdSchema = new ConceptSchema(CD);
    add(cdSchema, protege.CD.class);
    ConceptSchema disqueSchema = new ConceptSchema(DISQUE);
    add(disqueSchema, protege.Disque.class);
    ConceptSchema dvdSchema = new ConceptSchema(DVD);
    add(dvdSchema, protege.DVD.class);

    // adding AgentAction(s)
    PredicateSchema reponseDisponibiliteSchema = new PredicateSchema(REPONSEDISPONIBILITE);
    add(reponseDisponibiliteSchema, protege.ReponseDisponibilite.class);

    // adding AID(s)

    // adding Predicate(s)
    PredicateSchema nouvellePhaseSchema = new PredicateSchema(NOUVELLEPHASE);
    add(nouvellePhaseSchema, protege.NouvellePhase.class);
    PredicateSchema disponibleSchema = new PredicateSchema(DISPONIBLE);
    add(disponibleSchema, protege.Disponible.class);
    PredicateSchema validerAchatSchema = new PredicateSchema(VALIDERACHAT);
    add(validerAchatSchema, protege.ValiderAchat.class);
    PredicateSchema okSchema = new PredicateSchema(OK);
    add(okSchema, protege.OK.class);
    PredicateSchema stopEverybodySchema = new PredicateSchema(STOPEVERYBODY);
    add(stopEverybodySchema, protege.StopEverybody.class);


    // adding fields
    reponseDisponibiliteSchema.add(REPONSEDISPONIBILITE_DISQUE, disqueSchema, ObjectSchema.OPTIONAL);
    reponseDisponibiliteSchema.add(REPONSEDISPONIBILITE_PRIX, (TermSchema)getSchema(BasicOntology.FLOAT), ObjectSchema.OPTIONAL);
    nouvellePhaseSchema.add(NOUVELLEPHASE_NUMEROPHASE, (TermSchema)getSchema(BasicOntology.INTEGER), ObjectSchema.OPTIONAL);
    disponibleSchema.add(DISPONIBLE_DISQUE, disqueSchema, ObjectSchema.OPTIONAL);
    disponibleSchema.add(DISPONIBLE_QTE, (TermSchema)getSchema(BasicOntology.INTEGER), ObjectSchema.OPTIONAL);
    validerAchatSchema.add(VALIDERACHAT_REPONSE, (TermSchema)getSchema(BasicOntology.BOOLEAN), ObjectSchema.OPTIONAL);

    // adding name mappings

    // adding inheritance
    cdSchema.addSuperSchema(disqueSchema);
    dvdSchema.addSuperSchema(disqueSchema);

   }catch (java.lang.Exception e) {e.printStackTrace();}
  }
  }
