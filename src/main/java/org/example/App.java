package org.example;

import java.awt.*;
import java.awt.event.InputEvent;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.time.LocalTime;
import java.awt.event.KeyEvent;
import java.util.Arrays;


//Symulator telewizora zdalnie, prodotkol dowlony, na jednym z komputerów uruchamia sie telwizor i film z yt a na drugim
//pilot z mozliwoscia sterowania (javafx)

public class App {

    private static LocalTime time = LocalTime.of(12, 30);

    private static byte[] sendBuffer;

    private static DatagramPacket receivePacket;

    private static DatagramSocket socket = null;




    public static void main(String[] args) {
        try {
            socket = new DatagramSocket(9876);
            byte[] receiveBuffer = new byte[1024];

            System.out.println("Server is listening on port 9876...");

            while (true) {
                receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                socket.receive(receivePacket);
                String message = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Received message: " + message);

                if (message.startsWith("/setTime")) {
                    sendResponse("Ustawiam czas");
                    LocalTime lt = RobotTimer.divideTime(message);
                    System.out.println("Zwracam z metody " + RobotTimer.divideTime(message));

                    String hour = String.valueOf(lt.getHour());
                    String minute = String.valueOf(lt.getMinute());

                    RobotTimer.setTimer(hour, minute);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void sendResponse(String message) throws IOException {
        // Prepare the response message
        String responseMessage = "Acknowledged: " + message;
        sendBuffer = responseMessage.getBytes();

        // Get client address and port from the received packet
        InetAddress clientAddress = receivePacket.getAddress();
        int clientPort = receivePacket.getPort();

        // Create a new packet with the response message
        DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, clientAddress, clientPort);

        // Send the response packet
        socket.send(sendPacket);
        System.out.println("Response sent: " + responseMessage);
    }
}



class RobotTimer {

    public static void setTimer(String hour, String minute) throws AWTException {
        Robot robot = new Robot();

        robot.delay(1000);
        robot.keyPress(KeyEvent.VK_META);
        robot.keyPress(KeyEvent.VK_SPACE);

        robot.keyRelease(KeyEvent.VK_SPACE);

        robot.keyRelease(KeyEvent.VK_META);
        robot.delay(2000);

        String text = "data i czas";

        // Type each character in the string
        for (char c : text.toCharArray()) {
            typeCharacter(robot, c);
        }

        robot.keyPress(KeyEvent.VK_ENTER );

        robot.mouseMove(-1800, -115);

        robot.delay(2000);

        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);


        robot.delay(1000);

        robot.mouseMove(773, 221);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

        robot.delay(2000);
        robot.mouseMove(605, 466);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

        typeTime(robot, hour);

        robot.delay(500);
        robot.mouseMove(622, 466);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);


        typeTime(robot, minute);
        robot.mouseMove(646, 538);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);



    }

    public static LocalTime divideTime(String time) {
        String[] tab = time.split("_");
        String onlyTime = tab[1];

        String[] timer = onlyTime.split(":");

        LocalTime localTime = LocalTime.of(Integer.parseInt(timer[0]), Integer.parseInt(timer[1]));

        return localTime;
    }

    private static void typeCharacter(Robot robot, char character) {
        switch (character) {
            case 'a': case 'A': robot.keyPress(KeyEvent.VK_A); break;
            case 'b': case 'B': robot.keyPress(KeyEvent.VK_B); break;
            case 'c': case 'C': robot.keyPress(KeyEvent.VK_C); break;
            case 'd': case 'D': robot.keyPress(KeyEvent.VK_D); break;
            case 'e': case 'E': robot.keyPress(KeyEvent.VK_E); break;
            case 'f': case 'F': robot.keyPress(KeyEvent.VK_F); break;
            case 'g': case 'G': robot.keyPress(KeyEvent.VK_G); break;
            case 'h': case 'H': robot.keyPress(KeyEvent.VK_H); break;
            case 'i': case 'I': robot.keyPress(KeyEvent.VK_I); break;
            case 'j': case 'J': robot.keyPress(KeyEvent.VK_J); break;
            case 'k': case 'K': robot.keyPress(KeyEvent.VK_K); break;
            case 'l': case 'L': robot.keyPress(KeyEvent.VK_L); break;
            case 'm': case 'M': robot.keyPress(KeyEvent.VK_M); break;
            case 'n': case 'N': robot.keyPress(KeyEvent.VK_N); break;
            case 'o': case 'O': robot.keyPress(KeyEvent.VK_O); break;
            case 'p': case 'P': robot.keyPress(KeyEvent.VK_P); break;
            case 'q': case 'Q': robot.keyPress(KeyEvent.VK_Q); break;
            case 'r': case 'R': robot.keyPress(KeyEvent.VK_R); break;
            case 's': case 'S': robot.keyPress(KeyEvent.VK_S); break;
            case 't': case 'T': robot.keyPress(KeyEvent.VK_T); break;
            case 'u': case 'U': robot.keyPress(KeyEvent.VK_U); break;
            case 'v': case 'V': robot.keyPress(KeyEvent.VK_V); break;
            case 'w': case 'W': robot.keyPress(KeyEvent.VK_W); break;
            case 'x': case 'X': robot.keyPress(KeyEvent.VK_X); break;
            case 'y': case 'Y': robot.keyPress(KeyEvent.VK_Y); break;
            case 'z': case 'Z': robot.keyPress(KeyEvent.VK_Z); break;
            case '0': robot.keyPress(KeyEvent.VK_0); break;
            case '1': robot.keyPress(KeyEvent.VK_1); break;
            case '2': robot.keyPress(KeyEvent.VK_2); break;
            case '3': robot.keyPress(KeyEvent.VK_3); break;
            case '4': robot.keyPress(KeyEvent.VK_4); break;
            case '5': robot.keyPress(KeyEvent.VK_5); break;
            case '6': robot.keyPress(KeyEvent.VK_6); break;
            case '7': robot.keyPress(KeyEvent.VK_7); break;
            case '8': robot.keyPress(KeyEvent.VK_8); break;
            case '9': robot.keyPress(KeyEvent.VK_9); break;
            case ' ': robot.keyPress(KeyEvent.VK_SPACE); break;
            // Add more cases as needed
        }
        robot.keyRelease(character);
    }

    // Helper method to type the time
    private static void typeTime(Robot robot, String time) {
        for (char c : time.toCharArray()) {
            typeCharacter(robot, c);
        }
    }
}