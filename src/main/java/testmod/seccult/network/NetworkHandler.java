package testmod.seccult.network;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import testmod.seccult.Seccult;

public class NetworkHandler {
	private static final SimpleNetworkWrapper network = new SimpleNetworkWrapper(Seccult.MODID);
	
	public static void init() 
	{
		network.registerMessage(NetworkPlayerWandData.PacketMessageHandler.class, NetworkPlayerWandData.class, 0, Side.CLIENT);
		network.registerMessage(NetworkPlayerMagickData.PacketMessageHandler.class, NetworkPlayerMagickData.class, 1, Side.CLIENT);
		network.registerMessage(NetworkEffectData.PacketMessageHandler.class, NetworkEffectData.class, 2, Side.CLIENT);
		network.registerMessage(NetworkEntityMoving.PacketMessageHandler.class, NetworkEntityMoving.class, 3, Side.CLIENT);
		network.registerMessage(NetworkPlayerTransMagickToClient.PacketMessageHandler.class, NetworkPlayerTransMagickToClient.class, 4, Side.CLIENT);
		network.registerMessage(NetworPlayerMagickData.PacketMessageHandler.class, NetworPlayerMagickData.class, 5, Side.CLIENT);
		network.registerMessage(NetworkMutekiGamer.PacketMessageHandler.class, NetworkMutekiGamer.class, 6, Side.CLIENT);
		network.registerMessage(NetworkPlayerAddMagick.PacketMessageHandler.class, NetworkPlayerAddMagick.class, 7, Side.SERVER);
		network.registerMessage(NetworkTransFloat.MessageHandler.class, NetworkTransFloat.class, 8, Side.SERVER);
	}
	
	public static SimpleNetworkWrapper getNetwork()
	{
		return network;
	}
	
	public static void sendToAllAround(IMessage message, TransPoint point, World world)
	{
		for(int i = 0; i < world.playerEntities.size(); ++i)
		{
			EntityPlayer player = world.playerEntities.get(i);
			if(player.dimension == point.dimension && player.getDistance(point.x, point.y, point.z) < point.range)
				network.sendToAllTracking(message, player);
			else if(player.getDistance(point.x, point.y, point.z) < point.range)
				network.sendToAllTracking(message, player);
		}
	}
}
