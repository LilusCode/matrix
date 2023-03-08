import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

//performs mathematical operations on matrices, then returns them
public class solver {
    private static ServerSocket serverside;
    private static Socket clientside;
    private static ObjectOutputStream outStream;
    private static ObjectInputStream inStream;

    //creates ports, receives matrices
    public static matrix[] receive_matrix(int port) {
        try {
            serverside = new ServerSocket(port);
            clientside = serverside.accept();
            outStream = new ObjectOutputStream((clientside.getOutputStream()));
            inStream = new ObjectInputStream((clientside.getInputStream()));
            return (matrix[]) inStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    //does matrix math
    public static matrix multiply(matrix[] data){
        matrix result = new matrix(data[0].getR(),data[1].getC());
        for(int i=0; i<data[0].getC();i++){
            for(int j=0; j<data[1].getC();j++){
                result.set(i,j,0);
                for(int k=0; k<data[0].getC();k++){
                    double newVal = (result.getVal(i,j) + (data[0].getVal(i, k) * data[1].getVal(k, j)));
                    result.set(i,j,newVal);
                }
            }
        }
        return result;
    }

    public static void sendData(matrix data) {
        try {
            outStream.writeObject(data);
            outStream.flush();
        } catch (IOException e) {
            System.out.println("Sending matrix data failed.");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        sendData(multiply(receive_matrix(6666)));
    }
}
