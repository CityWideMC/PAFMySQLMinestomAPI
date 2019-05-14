package de.simonsator.partyandfriends.spigot.placeholders.placeholderapi;

import de.simonsator.partyandfriends.spigot.api.FriendRequestCountPlaceHolder;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class FriendRequestCountPlaceholderAPI extends PlaceholderExpansion implements FriendRequestCountPlaceHolder {
	private final boolean IS_ONLINE_SERVER;

	@Override
	public boolean persist() {
		return true;
	}

	@Override
	public boolean canRegister() {
		return true;
	}

	@Override
	public String getAuthor() {
		return "Simonsator";
	}

	@Override
	public String getIdentifier() {
		return "friendsapi";
	}

	@Override
	public String getVersion() {
		return "1.0";
	}

	public FriendRequestCountPlaceholderAPI(Plugin pPlugin) {
		IS_ONLINE_SERVER = pPlugin.getConfig().getBoolean("IsOnlineServer");
	}

	@Override
	public String onPlaceholderRequest(Player pPlayer, String pIdentifier) {
		if (pIdentifier.equals("friendrequestcount"))
			if (IS_ONLINE_SERVER)
				return getFriendRequestCount(pPlayer.getUniqueId()).toString();
			else
				return getFriendRequestCount(pPlayer.getName()).toString();
		return null;
	}

}
