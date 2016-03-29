package com.iup.tp.twitup.core;
import java.awt.CardLayout;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.swing.UnsupportedLookAndFeelException;
import com.iup.tp.twitup.common.PropertiesManager;
import com.iup.tp.twitup.datamodel.Database;
import com.iup.tp.twitup.datamodel.DatabaseObserver;
import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.datamodel.IDatabaseObserver;
import com.iup.tp.twitup.datamodel.User;
import com.iup.tp.twitup.events.file.IWatchableDirectory;
import com.iup.tp.twitup.events.file.WatchableDirectory;
import com.iup.tp.twitup.ihm.EViews;
import com.iup.tp.twitup.ihm.IView;
import com.iup.tp.twitup.ihm.TwitupConnexionView;
import com.iup.tp.twitup.ihm.TwitupHomeView;
import com.iup.tp.twitup.ihm.TwitupInscriptionView;
import com.iup.tp.twitup.ihm.TwitupMainView;
import com.iup.tp.twitup.ihm.TwitupMock;
import com.iup.tp.twitup.ihm.TwitupProfilView;
import com.iup.tp.twitup.ihm.TwitupTwitCreationView;
import com.iup.twitup.controllers.TwitupTwitController;
import com.iup.twitup.controllers.TwitupUserController;
/**
 * Classe principale l'application.
 * 
 * @author S.Lucas
 */
public class Twitup
{
	/**
	 * Base de données.
	 */
	protected IDatabase mDatabase;
	/**
	 * Observer Base de données
	 */
	protected IDatabaseObserver mDatabaseObserver;
	/**
	 * Gestionnaire des entités contenu de la base de données.
	 */
	protected EntityManager mEntityManager;
	/**
	 * Vue principale de l'application.
	 */
	protected TwitupMainView mMainView;
	/**
	 * Classe de surveillance de répertoire
	 */
	protected IWatchableDirectory mWatchableDirectory;
	/**
	 * Répertoire d'échange de l'application.
	 */
	protected String mExchangeDirectoryPath;
	/**
	 * Idnique si le mode bouchoné est activé.
	 */
	protected boolean mIsMockEnabled = true;
	/**
	 * Nom de la classe de l'UI.
	 */
	protected String mUiClassName;
	
	//Utilisateur actuellement connecté
	public User currentUser;
	
	//Controllers (ajouter le final)
	public TwitupUserController twitupUserController;
	public TwitupTwitController twitupTwitController;
	
	protected Map<EViews,IView> views = new HashMap<EViews,IView>();
	/**
	 * Constructeur.
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public Twitup() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException
	{
		// Init du look and feel de l'application
		this.initLookAndFeel();
		// Initialisation de la base de données
		this.initDatabase();
		//Initialisation de l'observable de la base de données
		this.initDatabaseObserver();
		if (this.mIsMockEnabled)
		{
			// Initialisation du bouchon de travail
			this.initMock();
		}
		// Initialisation de l'IHM
		this.initGui();
		// Initialisation du répertoire d'échange
		this.initDirectory(PropertiesManager.loadProperties("src/resources/configuration.properties").getProperty("EXCHANGE_DIRECTORY"));
	}
	/**
	 * Initialisation du look and feel de l'application.
	 */
	protected void initLookAndFeel()
	{}
	/**
	 * Initialisation de l'interface graphique.
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	protected void initGui() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException
	{
		mMainView = new TwitupMainView(this, mDatabase, mEntityManager);
		
		twitupUserController = new TwitupUserController(mDatabase, this, mEntityManager);
		twitupTwitController = new TwitupTwitController(mDatabase, this, mEntityManager);
		
		mMainView.add(EViews.CONNEXION_VIEW, twitupUserController.initConnexion());
		mMainView.add(EViews.INSCRIPTION_VIEW, twitupUserController.initInscription());
		mMainView.add(EViews.PROFIL_VIEW, twitupUserController.initProfil());
		mMainView.add(EViews.HOME_VIEW, twitupTwitController.initAccueil());
		mMainView.add(EViews.TWIT_CREATION_VIEW, twitupTwitController.initCreationTwit());
		mMainView.add(EViews.LIST_USER_VIEW, twitupUserController.initListUser());
		
		
		mMainView.showGUI();
		this.goToConnexion();
	}
	/**
	 * Initialisation du répertoire d'échange (depuis la conf ou depuis un file chooser). <br/>
	 * <b>Le chemin doit obligatoirement avoir été saisi et être valide avant de pouvoir utiliser l'application</b>
	 */
	protected void initDirectory()
	{}
	/**
	 * Indique si le fichier donné est valide pour servire de répertoire d'échange
	 * 
	 * @param directory
	 *          , Répertoire à tester.
	 */
	protected boolean isValideExchangeDirectory(File directory)
	{
		// Valide si répertoire disponible en lecture et écriture
		return directory != null && directory.exists() && directory.isDirectory() && directory.canRead()
				&& directory.canWrite();
	}
	public User getCurrentUser() {
		return currentUser;
	}
	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}
	/**
	 * Initialisation du mode bouchoné de l'application
	 */
	protected void initMock()
	{
		TwitupMock mock = new TwitupMock(this.mDatabase, this.mEntityManager);
		mock.showGUI();
	}
	/**
	 * Initialisation de la base de données
	 */
	protected void initDatabase()
	{
		mDatabase = new Database();
		mEntityManager = new EntityManager(mDatabase);
	}
	/**
	 * Initialisation de l'observer de la database
	 */
	protected void initDatabaseObserver(){
		mDatabaseObserver = new DatabaseObserver();
		mDatabase.addObserver(mDatabaseObserver);
	
	}
	public String getmExchangeDirectoryPath() {
		return mExchangeDirectoryPath;
	}
	public void setmExchangeDirectoryPath(String mExchangeDirectoryPath) {
		this.mExchangeDirectoryPath = mExchangeDirectoryPath;
	}
	/**
	 * Initialisation du répertoire d'échange.
	 * 
	 * @param directoryPath
	 */
	public void initDirectory(String directoryPath)
	{
		mExchangeDirectoryPath = directoryPath;
		mWatchableDirectory = new WatchableDirectory(directoryPath);
		mEntityManager.setExchangeDirectory(directoryPath);
		mWatchableDirectory.initWatching();
		mWatchableDirectory.addObserver(mEntityManager);
	}
	/**
	 * 
	 */
	public void goToConnexion(){
		mMainView.show(EViews.CONNEXION_VIEW);
	}
	
	public void goToInscription(){
		mMainView.show(EViews.INSCRIPTION_VIEW);
	}
	
	public void goToAccueil(){
		if(mMainView.getToolbar()!=null){
			mMainView.getToolbar().init();
		}
		mMainView.show(EViews.HOME_VIEW);
	}
	public void goToCreationTwit(){
		mMainView.show(EViews.TWIT_CREATION_VIEW);
	}

	public void goToProfil(){ 
		mMainView.show(EViews.PROFIL_VIEW);
	}
	
	public void goToListUser(){ 
		mMainView.show(EViews.LIST_USER_VIEW);
	}
	
	public void propagerCurrentUser(User currentUser){
		this.setCurrentUser(currentUser);
		twitupTwitController.setUser(currentUser);
		twitupUserController.setUser(currentUser);
		twitupUserController.refreshProfil(currentUser);
	}
	
	public TwitupUserController getTwitupUserController() {
		return twitupUserController;
	}
	public void setTwitupUserController(TwitupUserController twitupUserController) {
		this.twitupUserController = twitupUserController;
	}
	public TwitupTwitController getTwitupTwitController() {
		return twitupTwitController;
	}
	public void setTwitupTwitController(TwitupTwitController twitupTwitController) {
		this.twitupTwitController = twitupTwitController;
	}
	public void logout(){
		this.propagerCurrentUser(null);
		this.goToConnexion();
	}
	
}