package de.simonsator.partyandfriends.minestom.pafplayers.mysql;


import de.simonsator.partyandfriends.minestom.api.pafplayers.PAFPlayer;
import de.simonsator.partyandfriends.minestom.api.pafplayers.PAFPlayerClass;
import de.simonsator.partyandfriends.minestom.api.pafplayers.PAFPlayerManager;
import de.simonsator.partyandfriends.minestom.pafplayers.manager.PAFPlayerManagerMySQL;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PAFPlayerMySQL extends PAFPlayerClass {
	final int ID;

	public PAFPlayerMySQL(int pID) {
		ID = pID;
	}

	@Override
	public String getName() {
		return PAFPlayerManagerMySQL.getConnection().getName(ID);
	}

	public int getPlayerID() {
		return ID;
	}

	@Override
	public List<PAFPlayer> getFriends() {
		return idListToPAFPlayerList(PAFPlayerManagerMySQL.getConnection().getFriends(ID));
	}

	@Override
	public UUID getUniqueId() {
		return PAFPlayerManagerMySQL.getConnection().getUUID(ID);
	}

	@Override
	public int getSettingsWorth(int pSettingsID) {
		return PAFPlayerManagerMySQL.getConnection().getSettingsWorth(ID, pSettingsID);
	}

	@Override
	public void setSetting(int pSettingsID, int pNewWorth) {
		PAFPlayerManagerMySQL.getConnection().setSetting(ID, pSettingsID, pNewWorth);
	}

	@Override
	public List<PAFPlayer> getRequests() {
		return idListToPAFPlayerList(PAFPlayerManagerMySQL.getConnection().getRequests(ID));
	}


	@Override
	public boolean isAFriendOf(PAFPlayer pPlayer) {
		return PAFPlayerManagerMySQL.getConnection().isAFriendOf(ID, ((PAFPlayerMySQL) pPlayer.getPAFPlayer()).getPlayerID());
	}

	private List<PAFPlayer> idListToPAFPlayerList(List<Integer> pList) {
		List<PAFPlayer> list = new ArrayList<>();
		for (int playerID : pList)
			list.add(((PAFPlayerManagerMySQL) PAFPlayerManager.getInstance()).getPlayer(playerID));
		return list;
	}

	@Override
	public PAFPlayer getPAFPlayer() {
		return this;
	}

	@Override
	public long getLastOnline() {
		return PAFPlayerManagerMySQL.getConnection().getLastOnline(ID).getTime();
	}

	@Override
	public boolean hasRequestFrom(PAFPlayer pPlayer) {
		return PAFPlayerManagerMySQL.getConnection().hasRequestFrom(ID, ((PAFPlayerMySQL) pPlayer.getPAFPlayer()).getPlayerID());
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof PAFPlayerMySQL)
			return ((PAFPlayerMySQL) obj).getPlayerID() == ID;
		return super.equals(obj);
	}
}
