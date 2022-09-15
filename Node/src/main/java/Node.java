import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class Node {
    ServerSocket nodeSocket;
    List<Socket> usersList;

    public Node(int port) throws IOException {
        this.nodeSocket = new ServerSocket(port);
        this.usersList = new ArrayList<Socket>();

    }

    public static void main(String[] args) throws IOException {
        Node node = new Node(8000);
        while(true){
            try{
                node.receive();
            }
            catch(Exception e){
                System.out.println(e);
            }
        }
    }

    private void receive() throws IOException {
        Socket newUserSocket = null;
        try{
            newUserSocket = nodeSocket.accept();
            usersList.add(newUserSocket);
            InputStream in = newUserSocket.getInputStream();
            OutputStream out = newUserSocket.getOutputStream();
            User newUser = new User(newUserSocket, in, out);
            Thread newThread = new UserManager(newUser,this);
            newThread.start();
        }
        catch (Exception e){
            System.out.println(e);
            newUserSocket.close();
        }
    }

}

