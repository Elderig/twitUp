package com.iup.tp.twitup.mock;

import java.util.List;

import com.iup.tp.twitup.datamodel.Twit;

public interface ITwitListObserver {

	void notifyTwitListHasChanged(List<Twit> twits);
}
