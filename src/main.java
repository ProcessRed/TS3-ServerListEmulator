package de.processred.weblist;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.BitSet;
import java.util.concurrent.TimeUnit;
import java.util.Random;





public class main {

	public static void main(String[] args) throws Exception {
		String servername = args[0];
		int serverport = 9987;
		try {
			serverport = Integer.parseInt(args[1]);
		} catch (NumberFormatException e) {
			System.err.println("Argument" + args[1] + "serverport (default: 9987)");
			System.exit(1);
		}
		int maxusers = 512;
		try {
			maxusers = Integer.parseInt(args[2]);
		} catch (NumberFormatException e) {
			System.err.println("Argument" + args[2] + "maxusers (default: 512)");
			System.exit(1);
		}
		int clientsstart = 200;
		try {
			clientsstart = Integer.parseInt(args[3]);
		} catch (NumberFormatException e) {
			System.err.println("Argument" + args[3] + "clientstart (default: 200)");
			System.exit(1);
		}
		int clientsend = 512;
		try {
			clientsend = Integer.parseInt(args[4]);
		} catch (NumberFormatException e) {
			System.err.println("Argument" + args[4] + "clientsend (default: 500)");
			System.exit(1);
		}
		Random random = new Random();
		while (true) {
			DatagramSocket clientSocket = new DatagramSocket();
    		InetAddress IPAddress = InetAddress.getByName("51.68.181.92");
			byte[] request = new byte[5000];
			byte[] response = new byte[257];
			byte[] response2 = new byte[257];
			byte[] rawBytes;
			int nIndex = 0;
			DatagramPacket sendpacket = new DatagramPacket(request, nIndex, IPAddress, 2010);
			DatagramPacket receivePacket = new DatagramPacket(response, response.length);
			DatagramPacket sendpacket2 = new DatagramPacket(request, nIndex, IPAddress, 2010);
			DatagramPacket receivePacket2 = new DatagramPacket(response, response.length);
			here: nIndex = 0;
			request[nIndex++] = 0x01;
			request[nIndex++] = 0x03;
			request[nIndex++] = 0x00;
			request[nIndex++] = 0x01;
			sendpacket.setData(request, 0, nIndex);
			clientSocket.send(sendpacket);
			clientSocket.receive(receivePacket);
			nIndex = 0;
			request[nIndex++] = 0x01;
			request[nIndex++] = 0x04;
			request[nIndex++] = 0x00;
			request[nIndex++] = 0x02;
			request[nIndex++] = response[4];
			request[nIndex++] = response[5];
			request[nIndex++] = response[6];
			request[nIndex++] = response[7];
			byte[] portBytes = GetBytes(serverport);
			for (int i = 0; i <= 1; i++)
				request[nIndex++] = portBytes[i];
			byte[] slotsBytes = GetBytes(maxusers);
			for (int i = 0; i <= 1; i++)
				request[nIndex++] = slotsBytes[i];
			byte[] clientnumBytes = GetBytes(showRandomInteger(clientsstart, clientsend, random));
			for (int i = 0; i <= 1; i++)
				request[nIndex++] = clientnumBytes[i];
			BitSet bits = new BitSet();
			bits.set(1, true);
			byte[] bitss = bits.toByteArray();
			request[nIndex++] = bitss[0];
			request[nIndex++] = (byte) servername.length();
			rawBytes = servername.getBytes();
			System.arraycopy(rawBytes, 0, request, nIndex, rawBytes.length);
			nIndex += rawBytes.length;
			sendpacket2.setData(request, 0, nIndex);
			clientSocket.send(sendpacket2);
			clientSocket.receive(receivePacket2);
			TimeUnit.MINUTES.sleep(5);
		}
	}

	public static byte[] GetBytes(int value) {
		ByteBuffer buffer = ByteBuffer.allocate(4).order(ByteOrder.nativeOrder());
		buffer.putInt(value);
		return buffer.array();
	}

	private static int showRandomInteger(int aStart, int aEnd, Random aRandom) {
		if (aStart > aEnd) {
			throw new IllegalArgumentException("Start cannot exceed End.");
		}
		long range = (long) aEnd - (long) aStart + 1;
		long fraction = (long) (range * aRandom.nextDouble());
		int randomNumber = (int) (fraction + aStart);
		return randomNumber;
	}

}
