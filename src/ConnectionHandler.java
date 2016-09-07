import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by jessicatracy on 8/26/16.
 */
public class ConnectionHandler implements Runnable {
    Socket clientSocket;

    public ConnectionHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public void run() {
        try {
            handleIncomingConnection(clientSocket);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void handleIncomingConnection(Socket clientSocket) throws IOException {
        MyServer myServer = new MyServer();

        System.out.println("New connection from " + clientSocket.getInetAddress().getHostAddress());


        BufferedReader inputFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        PrintWriter outputToClient = new PrintWriter(clientSocket.getOutputStream(), true);

        String inputLine = inputFromClient.readLine();
        String userNameFromClient = null;
        boolean validUserName = true;

        if (!inputLine.startsWith("name=")) {
            outputToClient.println("You did not put your name in the proper format i.e. \"name=\" ");
            validUserName = false;
        } else {

            userNameFromClient = inputLine.split("=")[1];

        }

        while(((inputLine = inputFromClient.readLine()) != null) && validUserName) {
            if (inputLine.equalsIgnoreCase("history")) {

                for (Message message : myServer.getSetOfMessages()) {
                    outputToClient.println(message.getMessageContent());
                }
                outputToClient.println("Tx:History.End");
            } else {
                Message clientMessage = new Message();
                String myOutput = "On " + clientMessage.getDayOfWeek() + " at " + clientMessage.getTime() + ", " + userNameFromClient + " said: ";
                myOutput += inputLine;
                System.out.println("* " + myOutput);

                clientMessage.setMessageContent(myOutput);

                myServer.addMessageToSetOfMessages(clientMessage);

                outputToClient.println("Your message was received! " + myOutput);
                outputToClient.println("Tx:History.End");
            }
        }
    }
}