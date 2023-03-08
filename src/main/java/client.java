import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

//clients job is to connect to the solver, send user input matrix data, and print the returned math.
public class client {
    private static Socket clientside;
    private static ObjectOutputStream outStream;
    private static ObjectInputStream inStream;

    //creates socket connection to server
    public static void startConnection(String ip, int port) throws IOException  {
        clientside = new Socket(ip,port);
        outStream = new ObjectOutputStream((clientside.getOutputStream()));
        inStream = new ObjectInputStream((clientside.getInputStream()));
    }

    //sends data to server, passed as an array of both matrices
    public static void sendData(matrix[] data) {
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

    //waits for reply from solver with solved matrix
    public static matrix getResult(){
        try {
            return (matrix) inStream.readObject();
        } catch (IOException e) {
            System.out.println("Receiving data failed");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Receiving data failed");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws IOException {
        //Creates a matrix with user prompts.
        matrix matA = matrix.matrix_maker(null);
        //feeds matrix back to
        matrix matB = matrix.matrix_maker(matA);
        //assembles into one array then sends to solver
        matrix[] matrices = {matA, matB};
        //create server connection
        startConnection("localhost",6666);
        sendData(matrices);
        //waits for reply
        matrix solved = getResult();
        //print solved matrix
        System.out.println("Printing resulting matrix dot product");
        for(int i = 0; i < solved.C; i++){
            for(int j = 0; j < solved.R; j++){
                System.out.print(solved.getVal(i,j) + ", ");
            }
            System.out.print("\n");
        }
    }


    //TODO: take random matrix input, create websocket shit, send thru websocket to matrix math module
}
