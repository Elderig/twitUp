package com.iup.tp.twitup.mock;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.datamodel.IDatabaseObserver;
import com.iup.tp.twitup.datamodel.Twit;
import com.iup.tp.twitup.datamodel.User;

public class MockTwitListController implements IDatabaseObserver {

	protected final Set<ITwitListObserver> mObservers = new HashSet<ITwitListObserver>();

	protected List<Twit> twitList = new ArrayList<Twit>();

	private IDatabase database;

	public MockTwitListController(IDatabase iDatabase) {
		this.database = iDatabase;
		this.database.addObserver(this);
	}

	/**
	 * @param text
	 */
	public void searchTwits(String text) {
		ArrayList<Twit> newTwitList = new ArrayList<Twit>();
		
		// Récupération des twits à filtrer
		Set<Twit> databaseTwits = this.database.getTwits();
		if (text == null || text.isEmpty())
		{
			newTwitList = new ArrayList<Twit>(databaseTwits);
		}
		else
		{
			for (Twit twit : databaseTwits) {
				if (twit.getText().contains(text))
				{
					newTwitList.add(twit);
				}
			}
		}
		
		// Ajout de la nouvelle liste
		this.twitList = newTwitList;
		this.notifyObservers();
	}

	public void addObserver(ITwitListObserver observer) {
		this.mObservers.add(observer);
	}

	@Override
	public void notifyTwitAdded(Twit addedTwit) {
		twitList.add(addedTwit);
		notifyObservers();
	}

	@Override
	public void notifyTwitDeleted(Twit deletedTwit) {
		twitList.remove(deletedTwit);
		this.notifyObservers();
	}

	private void notifyObservers() {
		for (ITwitListObserver observer : mObservers) {
			observer.notifyTwitListHasChanged(twitList);
		}
	}

	@Override
	public void notifyTwitModified(Twit modifiedTwit) {
		// NA
	}

	@Override
	public void notifyUserAdded(User addedUser) {
		// NA
	}

	@Override
	public void notifyUserDeleted(User deletedUser) {
		// NA
	}

	@Override
	public void notifyUserModified(User modifiedUser) {
		// NA
	}

}
