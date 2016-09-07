
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;



public class MyServer {

    private ArrayList<Message> setOfMessages = new ArrayList<Message>();

    public void startServer() {
        try {
            ServerSocket serverListener = new ServerSocket(8080);
            System.out.println("Waiting for a connection...");

            while (true) {
                Socket clientSocket = serverListener.accept();

                ConnectionHandler myHandler = new ConnectionHandler(clientSocket);

                Thread newHandlingThread = new Thread(myHandler);
                newHandlingThread.start();
            }

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }



    public ArrayList<Message> getSetOfMessages() {
        return setOfMessages;
    }

    public void addMessageToSetOfMessages(Message messageToAdd) {
        setOfMessages.add(messageToAdd);
    }
}