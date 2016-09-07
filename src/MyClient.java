import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by jessicatracy on 8/26/16.
 */
public class MyClient {
    public static void main(String[] args) {
        System.out.println("MyClient running...");

        MyClient myClient = new MyClient();

        try {

            Socket clientSocket = new Socket("localhost", 8080);


            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));


            Scanner myScanner = new Scanner(System.in);
            String userName = myClient.getUserName(myScanner);

            out.println(userName);

            boolean keepChatting = true;
            while (keepChatting) {

                keepChatting = myClient.sendUserMessage(myScanner, in, out);
            }


            clientSocket.close();


        } catch(IOException exception) {
            exception.printStackTrace();
        }
    }

    public String getUserName(Scanner myScanner) {
        String userName = "name=";
        System.out.print("What is your name? ");
        userName += myScanner.nextLine();
        return userName;
    }

    public boolean sendUserMessage(Scanner myScanner, BufferedReader in, PrintWriter out) throws IOException {
        System.out.print("Write a message, write \"history\" to show your message history, or write \"exit\" to exit: ");
        String userMessage = myScanner.nextLine();
        if (userMessage.equalsIgnoreCase("exit")) {
            return false;
        }
        out.println(userMessage);

        System.out.println("Server's response: ");
        String serverResponse = in.readLine();
        while (!serverResponse.equals("Tx:History.End")) {
            System.out.println(serverResponse);
            serverResponse = in.readLine();
        }

        return true;
    }
}
