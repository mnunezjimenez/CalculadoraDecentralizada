import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerReceiver extends Thread {
    Server server;
    ObjectOutputStream outputStream;

    public ServerReceiver(Server server, ObjectOutputStream outputStream){
        this.server = server;
        this.outputStream = outputStream;
    }

    @Override
    public void run() {
        String message;
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(server.socket.getInputStream());
        } catch (IOException e) {
            System.out.println("No pude hacer un stream");
        }
        while (true) {
            try {
                message = (String) in.readObject();
                sendAnswer(message);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public void sendAnswer(String message) throws IOException {
        ArrayList<String> operands;
        Integer result = 0;
        if(message.startsWith("0")){
            message = message.substring(1);
            operands = separateOperands(message);
            result = calculateOperation(operands);
            System.out.println(message);
            this.outputStream.writeObject("1"+Integer.toString(result));
        }
    }

    public ArrayList<String> separateOperands(String operation){
        operation = operation.replace("+","suma");
        String [] operand = {};
        ArrayList<String> operands = new ArrayList<String>();
        if(operation.contains("suma")){
            operand = operation.split("suma");
            for(int i=0; i<operand.length; i++){
                operands.add(operand[i]);
            }
            operands.add("+");
        }
        return operands;
    }

    public Integer calculateOperation(ArrayList<String> operands){
        String operation = operands.get(operands.size() - 1);
        Integer result = 0;
        if(operation == "+"){
            for(int i = 0; i < operands.size() - 1; i++){
                result = result + Integer.parseInt(operands.get(i));
            }
            return result;
        }
        else if(operation == "-"){
            for(int i = 0; i < operands.size() - 1; i++){
                if(i != 0){
                    result = result - Integer.parseInt(operands.get(i));
                } else if (i == 0) {
                    result = result + Integer.parseInt(operands.get(i));
                }
            }
            return result;
        }
        else if(operation == "*"){
            for(int i = 0; i < operands.size() - 1; i++){
                if(i != 0){
                    result = result * Integer.parseInt(operands.get(i));
                } else if (i == 0) {
                    result = result + Integer.parseInt(operands.get(i));
                }
            }
            return result;
        }
        else if(operation == "/"){
            for(int i = 0; i < operands.size() - 1; i++){
                if(i != 0){
                    result = result / Integer.parseInt(operands.get(i));
                } else if (i == 0) {
                    result = result + Integer.parseInt(operands.get(i));
                }
            }
            return result;
        }
        return 0;
    }
}

