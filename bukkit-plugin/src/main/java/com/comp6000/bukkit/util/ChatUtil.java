package com.comp6000.bukkit.util;

import org.bukkit.ChatColor;

public class ChatUtil {

  public static String colorize(String text) {
    return ChatColor.translateAlternateColorCodes('&', text);
  }
}
