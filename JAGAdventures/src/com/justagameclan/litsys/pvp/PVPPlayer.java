package com.justagameclan.litsys.pvp;

import java.net.InetSocketAddress;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Achievement;
import org.bukkit.Effect;
import org.bukkit.EntityEffect;
import org.bukkit.GameMode;
import org.bukkit.Instrument;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Note;
import org.bukkit.Server;
import org.bukkit.Sound;
import org.bukkit.Statistic;
import org.bukkit.WeatherType;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.ConversationAbandonedEvent;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.InventoryView.Property;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.map.MapView;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.util.Vector;

public class PVPPlayer implements Player {

	private int kills = 0;
	private int killStreak = 0;
	private int deaths = 0;
	private int assists = 0;
	private int damage = 0;
	private String Team = null;
	private Player p;
	
	public PVPPlayer(Player playerEntity) {
		this.setP(playerEntity);
	}
		
	public void addKill() {
		this.kills++;
	}
	
	public void clearKills() {
		this.kills = 0;
	}
	
	public int getKills() {
		return this.kills;
	}
	
	public void clearStreak() {
		this.killStreak = 0;
	}
	
	public void addStreak() {
		this.killStreak++;
	}
	
	public int getStreak() {
		return this.killStreak;
	}
	
	public void setTeam(String TeamName) {
		this.Team = TeamName;
	}
	
	public String getTeam() {
		return this.Team;
	}

	public int getDeaths() {
		return this.deaths;
	}

	public void addDeaths() {
		this.deaths++;
	}

	public int getAssists() {
		return this.assists;
	}

	public void addAssists() {
		this.assists++;
	}

	public int getDamage() {
		return damage;
	}

	public void addDamage(int damage) {
		this.damage = this.damage + damage;
	}

	public Player getPlayerEntity() {
		return this.getP();
	}

	public void setPlayerEntity(Player playerEntity) {
		this.setP(playerEntity);
	}

	@Override
	public void closeInventory() {
		getP().closeInventory();
	}

	@Override
	public GameMode getGameMode() {
		return getP().getGameMode();
	}

	@Override
	public PlayerInventory getInventory() {
		return getP().getInventory();
	}

	@Override
	public ItemStack getItemInHand() {
		return getP().getItemInHand();
	}

	@Override
	public ItemStack getItemOnCursor() {
		return getP().getItemOnCursor();
	}

	@Override
	public String getName() {
		return getP().getName();
	}

	@Override
	public InventoryView getOpenInventory() {
		return getP().getOpenInventory();
	}

	@Override
	public int getSleepTicks() {
		return getP().getSleepTicks();
	}

	@Override
	public boolean isBlocking() {
		return getP().isBlocking();
	}

	@Override
	public boolean isSleeping() {
		return getP().isSleeping();
	}

	@Override
	public InventoryView openEnchanting(Location arg0, boolean arg1) {
		return getP().openEnchanting(arg0, arg1);
	}

	@Override
	public InventoryView openInventory(Inventory arg0) {
		return getP().openInventory(arg0);
	}

	@Override
	public void openInventory(InventoryView arg0) {
		getP().openInventory(arg0);
	}

	@Override
	public InventoryView openWorkbench(Location arg0, boolean arg1) {
		return getP().openWorkbench(arg0, arg1);
	}

	@Override
	public void setGameMode(GameMode arg0) {
		getP().setGameMode(arg0);
	}

	@Override
	public void setItemInHand(ItemStack arg0) {
		getP().setItemInHand(arg0);
	}

	@Override
	public void setItemOnCursor(ItemStack arg0) {
		getP().setItemOnCursor(arg0);
	}

	@Override
	public boolean setWindowProperty(Property arg0, int arg1) {
		return getP().setWindowProperty(arg0,arg1);
	}

	@Override
	public boolean addPotionEffect(PotionEffect effect) {
		return getP().addPotionEffect(effect);
	}

	@Override
	public boolean addPotionEffect(PotionEffect effect, boolean force) {
		return getP().addPotionEffect(effect,force);
	}

	@Override
	public boolean addPotionEffects(Collection<PotionEffect> effects) {
		return getP().addPotionEffects(effects);
	}

	@Override
	public void damage(double amount) {
		getP().damage(amount);
		
	}

	@Override
	public void damage(double amount, Entity source) {
		getP().damage(amount,source);	
	}

	@Override
	public Collection<PotionEffect> getActivePotionEffects() {
		return getP().getActivePotionEffects();
	}

	@Override
	public double getEyeHeight() {
		return getP().getEyeHeight();
	}

	@Override
	public double getEyeHeight(boolean ignoreSneaking) {
		return getP().getEyeHeight(ignoreSneaking);
	}

	@Override
	public Location getEyeLocation() {
		return getP().getEyeLocation();
	}

	@Override
	public Player getKiller() {
		return getP().getKiller();
	}

	@Override
	public List<Block> getLastTwoTargetBlocks(HashSet<Byte> transparent,
			int maxDistance) {
		return getP().getLastTwoTargetBlocks(transparent, maxDistance);
	}

	@Override
	public List<Block> getLineOfSight(HashSet<Byte> transparent, int maxDistance) {
		return getP().getLineOfSight(transparent, maxDistance);
	}


	@Override
	public int getMaximumAir() {
		return getP().getMaximumAir();
	}

	@Override
	public int getMaximumNoDamageTicks() {
		return getP().getMaximumNoDamageTicks();
	}

	@Override
	public int getNoDamageTicks() {
		return getP().getNoDamageTicks();
	}

	@Override
	public int getRemainingAir() {
		return getP().getRemainingAir();
	}

	@Override
	public Block getTargetBlock(HashSet<Byte> transparent, int maxDistance) {
		return getP().getTargetBlock(transparent,maxDistance);
	}

	@Override
	public boolean hasPotionEffect(PotionEffectType type) {
		return getP().hasPotionEffect(type);
	}

	@Override
	public <T extends Projectile> T launchProjectile(
			Class<? extends T> projectile) {
		return getP().launchProjectile(projectile);
	}

	@Override
	public void removePotionEffect(PotionEffectType type) {
		getP().removePotionEffect(type);
	}

	@Override
	public void setHealth(double health) {
		getP().setHealth(health);
	}

	@Override
	public void setLastDamage(double damage) {
		getP().setLastDamage(damage);
	}

	@Override
	public void setMaximumAir(int ticks) {
		getP().setMaximumAir(ticks);
	}

	@Override
	public void setMaximumNoDamageTicks(int ticks) {
		getP().setMaximumNoDamageTicks(ticks);
	}

	@Override
	public void setNoDamageTicks(int ticks) {
		getP().setNoDamageTicks(ticks);
	}

	@Override
	public void setRemainingAir(int ticks) {
		getP().setRemainingAir(ticks);
	}

	@Override
	@Deprecated
	public Arrow shootArrow() {
		return getP().shootArrow();
	}

	@Override
	@Deprecated
	public Egg throwEgg() {
		return getP().throwEgg();
	}

	@Override
	@Deprecated
	public Snowball throwSnowball() {
		return getP().throwSnowball();
	}

	@Override
	public boolean eject() {
		return getP().eject();
	}

	@Override
	public int getEntityId() {
		return getP().getEntityId();
	}

	@Override
	public float getFallDistance() {
		return getP().getFallDistance();
	}

	@Override
	public int getFireTicks() {
		return getP().getFireTicks();
	}

	@Override
	public EntityDamageEvent getLastDamageCause() {
		return getP().getLastDamageCause();
	}

	@Override
	public Location getLocation() {
		return getP().getLocation();
	}

	@Override
	public int getMaxFireTicks() {
		return getP().getMaxFireTicks();
	}

	@Override
	public List<Entity> getNearbyEntities(double x, double y, double z) {
		return getP().getNearbyEntities(x,y,z);
	}

	@Override
	public Entity getPassenger() {
		return getP().getPassenger();
	}

	@Override
	public Server getServer() {
		return getP().getServer();
	}

	@Override
	public int getTicksLived() {
		return getP().getTicksLived();
	}

	@Override
	public EntityType getType() {
		return getP().getType();
	}

	@Override
	public UUID getUniqueId() {
		return getP().getUniqueId();
	}

	@Override
	public Entity getVehicle() {
		return getP().getVehicle();
	}

	@Override
	public Vector getVelocity() {
		return getP().getVelocity();
	}

	@Override
	public World getWorld() {
		return getP().getWorld();
	}

	@Override
	public boolean isDead() {
		return getP().isDead();
	}

	@Override
	public boolean isEmpty() {
		return getP().isEmpty();
	}

	@Override
	public boolean isInsideVehicle() {
		return getP().isInsideVehicle();
	}

	@Override
	public boolean leaveVehicle() {
		return getP().leaveVehicle();
	}

	@Override
	public void playEffect(EntityEffect type) {
		getP().playEffect(type);
	}

	@Override
	public void remove() {
		getP().remove();		
	}

	@Override
	public void setFallDistance(float distance) {
		getP().setFallDistance(distance);
	}

	@Override
	public void setFireTicks(int ticks) {
		getP().setFireTicks(ticks);
	}

	@Override
	public void setLastDamageCause(EntityDamageEvent event) {
		getP().setLastDamageCause(event);
	}

	@Override
	public boolean setPassenger(Entity passenger) {
		return getP().setPassenger(passenger);
	}

	@Override
	public void setTicksLived(int value) {
		getP().setTicksLived(value);
	}

	@Override
	public void setVelocity(Vector velocity) {
		getP().setVelocity(velocity);
	}

	@Override
	public boolean teleport(Location location) {
		return getP().teleport(location);
	}

	@Override
	public boolean teleport(Entity destination) {
		return getP().teleport(destination);
	}

	@Override
	public boolean teleport(Location location, TeleportCause cause) {
		return getP().teleport(location, cause);
	}

	@Override
	public boolean teleport(Entity destination, TeleportCause cause) {
		return getP().teleport(destination, cause);
	}

	@Override
	public List<MetadataValue> getMetadata(String metadataKey) {
		return getP().getMetadata(metadataKey);
	}

	@Override
	public boolean hasMetadata(String metadataKey) {
		return getP().hasMetadata(metadataKey);
	}

	@Override
	public void removeMetadata(String metadataKey, Plugin owningPlugin) {
		getP().removeMetadata(metadataKey, owningPlugin);
	}

	@Override
	public void setMetadata(String metadataKey, MetadataValue newMetadataValue) {
		getP().setMetadata(metadataKey, newMetadataValue);
	}

	@Override
	public PermissionAttachment addAttachment(Plugin plugin) {
		return getP().addAttachment(plugin);
	}

	@Override
	public PermissionAttachment addAttachment(Plugin plugin, int ticks) {
		return getP().addAttachment(plugin, ticks);
	}

	@Override
	public PermissionAttachment addAttachment(Plugin plugin, String name,
			boolean value) {
		return getP().addAttachment(plugin,name,value);
	}

	@Override
	public PermissionAttachment addAttachment(Plugin plugin, String name,
			boolean value, int ticks) {
		return getP().addAttachment(plugin,name,value,ticks);
	}

	@Override
	public Set<PermissionAttachmentInfo> getEffectivePermissions() {
		return getP().getEffectivePermissions();
	}

	@Override
	public boolean hasPermission(String name) {
		return getP().hasPermission(name);
	}

	@Override
	public boolean hasPermission(Permission perm) {
		return getP().hasPermission(perm);
	}

	@Override
	public boolean isPermissionSet(String name) {
		return getP().isPermissionSet(name);
	}

	@Override
	public boolean isPermissionSet(Permission perm) {
		return getP().isPermissionSet(perm);
	}

	@Override
	public void recalculatePermissions() {
		getP().recalculatePermissions();
	}

	@Override
	public void removeAttachment(PermissionAttachment attachment) {
		getP().removeAttachment(attachment);
	}

	@Override
	public boolean isOp() {
		return getP().isOp();
	}

	@Override
	public void setOp(boolean arg0) {
		getP().setOp(arg0);
	}

	@Override
	public void abandonConversation(Conversation arg0) {
		getP().abandonConversation(arg0);
	}

	@Override
	public void abandonConversation(Conversation arg0,
			ConversationAbandonedEvent arg1) {
		getP().abandonConversation(arg0,arg1);
		
	}

	@Override
	public void acceptConversationInput(String arg0) {
		getP().acceptConversationInput(arg0);
	}

	@Override
	public boolean beginConversation(Conversation arg0) {
		return getP().beginConversation(arg0);
	}

	@Override
	public boolean isConversing() {
		return getP().isConversing();
	}

	@Override
	public void sendMessage(String message) {
		getP().sendMessage(message);
	}

	@Override
	public void sendMessage(String[] messages) {
		getP().sendMessage(messages);
	}

	@Override
	public long getFirstPlayed() {
		return getP().getFirstPlayed();
	}

	@Override
	public long getLastPlayed() {
		return getP().getLastPlayed();
	}

	@Override
	public Player getPlayer() {
		return getP().getPlayer();
	}

	@Override
	public boolean hasPlayedBefore() {
		return getP().hasPlayedBefore();
	}

	@Override
	public boolean isBanned() {
		return getP().isBanned();
	}

	@Override
	public boolean isOnline() {
		return getP().isOnline();
	}

	@Override
	public boolean isWhitelisted() {
		return getP().isWhitelisted();
	}

	@Override
	public void setBanned(boolean banned) {
		getP().setBanned(banned);
	}

	@Override
	public void setWhitelisted(boolean value) {
		getP().setWhitelisted(value);
	}

	@Override
	public Map<String, Object> serialize() {
		return getP().serialize();
	}

	@Override
	public Set<String> getListeningPluginChannels() {
		return getP().getListeningPluginChannels();
	}

	@Override
	public void sendPluginMessage(Plugin arg0, String arg1, byte[] arg2) {
		getP().sendPluginMessage(arg0, arg1, arg2);
	}

	@Override
	public void awardAchievement(Achievement arg0) {
		getP().awardAchievement(arg0);
	}

	@Override
	public boolean canSee(Player arg0) {
		return getP().canSee(arg0);
	}

	@Override
	public void chat(String arg0) {
		getP().chat(arg0);
	}

	@Override
	public InetSocketAddress getAddress() {
		return getP().getAddress();
	}

	@Override
	public boolean getAllowFlight() {
		return getP().getAllowFlight();
	}

	@Override
	public Location getBedSpawnLocation() {
		return getP().getBedSpawnLocation();
	}

	@Override
	public Location getCompassTarget() {
		return getP().getCompassTarget();
	}

	@Override
	public String getDisplayName() {
		return getP().getDisplayName();
	}

	@Override
	public float getExhaustion() {
		return getP().getExhaustion();
	}

	@Override
	public float getExp() {
		return getP().getExp();
	}

	@Override
	public int getFoodLevel() {
		return getP().getFoodLevel();
	}

	@Override
	public int getLevel() {
		return getP().getLevel();
	}

	@Override
	public String getPlayerListName() {
		return getP().getPlayerListName();
	}

	@Override
	public long getPlayerTime() {
		return getP().getPlayerTime();
	}

	@Override
	public long getPlayerTimeOffset() {
		return getP().getPlayerTimeOffset();
	}

	@Override
	public float getSaturation() {
		return getP().getSaturation();
	}

	@Override
	public int getTotalExperience() {
		return getP().getTotalExperience();
	}

	@Override
	public void giveExp(int arg0) {
		getP().giveExp(arg0);
	}

	@Override
	public void hidePlayer(Player arg0) {
		getP().hidePlayer(arg0);
	}

	@Override
	public void incrementStatistic(Statistic arg0) {
		getP().incrementStatistic(arg0);
	}

	@Override
	public void incrementStatistic(Statistic arg0, int arg1) {
		getP().incrementStatistic(arg0, arg1);
	}

	@Override
	public void incrementStatistic(Statistic arg0, Material arg1) {
		getP().incrementStatistic(arg0, arg1);
	}

	@Override
	public void incrementStatistic(Statistic arg0, Material arg1, int arg2) {
		getP().incrementStatistic(arg0, arg1, arg2);
	}

	@Override
	public boolean isFlying() {
		return getP().isFlying();
	}

	@Override
	public boolean isPlayerTimeRelative() {
		return getP().isPlayerTimeRelative();
	}

	@Override
	public boolean isSleepingIgnored() {
		return getP().isSleepingIgnored();
	}

	@Override
	public boolean isSneaking() {
		return getP().isSneaking();
	}

	@Override
	public boolean isSprinting() {
		return getP().isSprinting();
	}

	@Override
	public void kickPlayer(String arg0) {
		getP().kickPlayer(arg0);
	}

	@Override
	public void loadData() {
		getP().loadData();
	}

	@Override
	public boolean performCommand(String arg0) {
		return getP().performCommand(arg0);
	}

	@Override
	public void playEffect(Location arg0, Effect arg1, int arg2) {
		getP().playEffect(arg0, arg1, arg2);
	}

	@Override
	public <T> void playEffect(Location arg0, Effect arg1, T arg2) {
		getP().playEffect(arg0, arg1, arg2);
	}

	@Override
	public void playNote(Location arg0, byte arg1, byte arg2) {
		getP().playNote(arg0, arg1, arg2);
	}

	@Override
	public void playNote(Location arg0, Instrument arg1, Note arg2) {
		getP().playNote(arg0, arg1, arg2);		
	}

	@Override
	public void resetPlayerTime() {
		getP().resetPlayerTime();		
	}

	@Override
	public void saveData() {
		getP().saveData();		
	}

	@Override
	public void sendBlockChange(Location arg0, Material arg1, byte arg2) {
		getP().sendBlockChange(arg0, arg1, arg2);
	}

	@Override
	public void sendBlockChange(Location arg0, int arg1, byte arg2) {
		getP().sendBlockChange(arg0, arg1, arg2);		
	}

	@Override
	public boolean sendChunkChange(Location arg0, int arg1, int arg2, int arg3,
			byte[] arg4) {
		
		return getP().sendChunkChange(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void sendMap(MapView arg0) {
		getP().sendMap(arg0);
	}

	@Override
	public void sendRawMessage(String arg0) {
		getP().sendRawMessage(arg0);
	}

	@Override
	public void setAllowFlight(boolean arg0) {
		getP().setAllowFlight(arg0);		
	}

	@Override
	public void setBedSpawnLocation(Location arg0) {
		getP().setBedSpawnLocation(arg0);
	}

	@Override
	public void setCompassTarget(Location arg0) {
		getP().setCompassTarget(arg0);
	}

	@Override
	public void setDisplayName(String arg0) {
		getP().setDisplayName(arg0);		
	}

	@Override
	public void setExhaustion(float arg0) {
		getP().setExhaustion(arg0);
	}

	@Override
	public void setExp(float arg0) {
		getP().setExp(arg0);
	}

	@Override
	public void setFlying(boolean arg0) {
		getP().setFlying(arg0);
	}

	@Override
	public void setFoodLevel(int arg0) {
		getP().setFoodLevel(arg0);
	}

	@Override
	public void setLevel(int arg0) {
		getP().setLevel(arg0);
	}

	@Override
	public void setPlayerListName(String arg0) {
		getP().setPlayerListName(arg0);
	}

	@Override
	public void setPlayerTime(long arg0, boolean arg1) {
		getP().setPlayerTime(arg0, arg1);
	}

	@Override
	public void setSaturation(float arg0) {
		getP().setSaturation(arg0);		
	}

	@Override
	public void setSleepingIgnored(boolean arg0) {
		getP().setSleepingIgnored(arg0);		
	}

	@Override
	public void setSneaking(boolean arg0) {
		getP().setSneaking(arg0);
	}

	@Override
	public void setSprinting(boolean arg0) {
		getP().setSprinting(arg0);
	}

	@Override
	public void setTotalExperience(int arg0) {
		getP().setTotalExperience(arg0);
	}

	@Override
	public void showPlayer(Player arg0) {
		getP().showPlayer(arg0);
	}

	@Override
	@Deprecated
	public void updateInventory() {
		getP().updateInventory();
	}

	public Player getP() {
		return p;
	}

	public void setP(Player p) {
		this.p = p;
	}

	@Override
	public Inventory getEnderChest() {
		return getP().getEnderChest();
	}

	@Override
	public int getExpToLevel() {
		return getP().getExpToLevel();
	}

	@Override
	public boolean getCanPickupItems() {
		return getP().getCanPickupItems();
	}

	@Override
	public String getCustomName() {
		return getP().getCustomName();
	}

	@Override
	public EntityEquipment getEquipment() {
		return getP().getEquipment();
	}

	@Override
	public boolean getRemoveWhenFarAway() {
		return getP().getRemoveWhenFarAway();
	}

	@Override
	public boolean hasLineOfSight(Entity other) {
		return getP().hasLineOfSight(other);
	}

	@Override
	public boolean isCustomNameVisible() {
		return getP().isCustomNameVisible();
	}

	@Override
	public void setCanPickupItems(boolean pickup) {
		getP().setCanPickupItems(pickup);
	}

	@Override
	public void setCustomName(String name) {
		getP().setCustomName(name);
	}

	@Override
	public void setCustomNameVisible(boolean flag) {
		getP().setCustomNameVisible(flag);
	}

	@Override
	public void setRemoveWhenFarAway(boolean remove) {
		getP().setRemoveWhenFarAway(remove);
	}

	@Override
	public Location getLocation(Location arg0) {
		return getP().getLocation();
	}

	@Override
	public boolean isValid() {
		return getP().isValid();
	}

	@Override
	public void resetMaxHealth() {
		getP().resetMaxHealth();
	}

	@Override
	public void setMaxHealth(double arg0) {
		getP().setMaxHealth(arg0);
	}

	@Override
	public float getFlySpeed() {
		return getP().getFlySpeed();
	}

	@Override
	public WeatherType getPlayerWeather() {
		return getP().getPlayerWeather();
	}

	@Override
	public Scoreboard getScoreboard() {
		return getP().getScoreboard();
	}

	@Override
	public float getWalkSpeed() {
		return getP().getWalkSpeed();
	}

	@Override
	public void giveExpLevels(int amount) {
		getP().giveExpLevels(amount);
	}

	public boolean isScaledHealth() {
		return ((PVPPlayer) getP()).isScaledHealth();
	}

	@Override
	public void playSound(Location location, Sound sound, float volume,
			float pitch) {
		getP().playSound(location, sound, volume, pitch);
	}

	@Override
	public void resetPlayerWeather() {
		getP().resetPlayerWeather();
	}

	@Override
	public void setBedSpawnLocation(Location location, boolean force) {
		getP().setBedSpawnLocation(location, force);
	}

	@Override
	public void setFlySpeed(float value) throws IllegalArgumentException {
		getP().setFlySpeed(value);
	}

	@Override
	public void setPlayerWeather(WeatherType type) {
		getP().setPlayerWeather(type);
	}

	public void setScaleHealth(boolean arg0) {
		((PVPPlayer) getP()).setScaleHealth(arg0);
	}

	@Override
	public void setScoreboard(Scoreboard scoreboard)
			throws IllegalArgumentException, IllegalStateException {
		getP().setScoreboard(scoreboard);
	}

	@Override
	public void setTexturePack(String url) {
		getP().setTexturePack(url);
	}

	@Override
	public void setWalkSpeed(float value) throws IllegalArgumentException {
		getP().setWalkSpeed(value);
	}

	@Override
	public double getLastDamage() {
		return getP().getLastDamage();
	}

	@Override
	public double getHealth() {
		return getP().getHealth();
	}

	@Override
	public double getMaxHealth() {
		return getP().getMaxHealth();
	}

	@Override
	@Deprecated
	public int _INVALID_getLastDamage() {
		return getP()._INVALID_getLastDamage();
	}

	@Override
	@Deprecated
	public void _INVALID_setLastDamage(int arg0) {
		getP()._INVALID_setLastDamage(arg0);
	}

	@Override
	@Deprecated
	public void _INVALID_damage(int arg0) {
		getP()._INVALID_damage(arg0);
	}

	@Override
	@Deprecated
	public void _INVALID_damage(int arg0, Entity arg1) {
		getP()._INVALID_damage(arg0, arg1);
	}

	@Override
	@Deprecated
	public int _INVALID_getHealth() {
		return getP()._INVALID_getHealth();
	}

	@Override
	@Deprecated
	public int _INVALID_getMaxHealth() {
		return getP()._INVALID_getMaxHealth();
	}

	@Override
	@Deprecated
	public void _INVALID_setHealth(int arg0) {
		getP()._INVALID_setHealth(arg0);
	}

	@Override
	@Deprecated
	public void _INVALID_setMaxHealth(int arg0) {
		getP()._INVALID_setMaxHealth(arg0);
	}
	
	@Deprecated
	@Override
	public boolean isOnGround() {
		return getP().isOnGround();
	}

	@Override
	public double getHealthScale() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isHealthScaled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setHealthScale(double arg0) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setHealthScaled(boolean arg0) {
		// TODO Auto-generated method stub
		
	}
}
