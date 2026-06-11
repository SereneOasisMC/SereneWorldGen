//package com.sonorous.utils;
//
//import net.minecraft.network.ClientboundPacketListener;
//import net.minecraft.network.ConnectionProtocol;
//import net.minecraft.network.chat.Component;
//import net.minecraft.network.protocol.PacketFlow;
//
//public class PacketListenerImpl implements ClientboundPacketListener {
//    @Override
//    public PacketFlow flow() {
//        PacketFlow packetFlow = PacketFlow.SERVERBOUND;
//        return packetFlow;
//    }
//
//    @Override
//    public ConnectionProtocol protocol() {
//
//        return null;
//    }
//
//    @Override
//    public void onDisconnect(Component reason) {
//
//    }
//
//    @Override
//    public boolean isAcceptingMessages() {
//        return false;
//    }
//
//
//}
