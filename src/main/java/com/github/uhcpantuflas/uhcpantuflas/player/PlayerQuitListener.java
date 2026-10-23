package com.github.uhcpantuflas.uhcpantuflas.player;

import com.github.uhcpantuflas.uhcpantuflas.gameplay.GameState;
import com.github.uhcpantuflas.uhcpantuflas.gameplay.GameplayManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        if (GameplayManager.get().getGameState() == GameState.BEGINNING) {
            PlayerManager.get().unregisterPlayer(event.getPlayer().getName());
        }
    }
}
