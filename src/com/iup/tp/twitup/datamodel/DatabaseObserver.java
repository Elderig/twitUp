package com.iup.tp.twitup.datamodel;

public class DatabaseObserver implements IDatabaseObserver{

	@Override
	public void notifyTwitAdded(Twit addedTwit) {
		// TODO Auto-generated method stub
		System.out.println("Twit added");
	}

	@Override
	public void notifyTwitDeleted(Twit deletedTwit) {
		// TODO Auto-generated method stub
		System.out.println("Twit deleted");
	}

	@Override
	public void notifyTwitModified(Twit modifiedTwit) {
		// TODO Auto-generated method stub
		System.out.println("Twit modifed");
	}

	@Override
	public void notifyUserAdded(User addedUser) {
		// TODO Auto-generated method stub
		System.out.println("User added");
	}

	@Override
	public void notifyUserDeleted(User deletedUser) {
		// TODO Auto-generated method stub
		System.out.println("User deleted");
	}

	@Override
	public void notifyUserModified(User modifiedUser) {
		// TODO Auto-generated method stub
		System.out.println("User modified");
	}

}
