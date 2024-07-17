package org.example;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.time.LocalTime;
import java.util.Scanner;


public class App {

    private static LocalTime time = LocalTime.of(22, 10);

    private static DatagramSocket socket;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                String message = scanner.nextLine();
                socket = new DatagramSocket();
                InetAddress serverAddress = InetAddress.getByName("localhost");
                byte[] sendBuffer;

                if (message.equals("/setTime")) {
                    message = message + "_" + time;
                }

                sendBuffer = message.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, serverAddress, 9876);
                socket.send(sendPacket);

                System.out.println("Message sent to server: " + message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
