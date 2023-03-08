import java.io.Serializable;
import java.util.Scanner;

//matrix object class
public class matrix implements Serializable {
    int R; //number of rows
    int C; //number of columns
    double[][] data; //RxC multidimentional array

    static Scanner sc = new Scanner(System.in);

    //creates matrix of RxC, and its data array
    public matrix(int R, int C) {
        this.R = R;
        this.C = C;
        data = new double[R][C];
    }

    public int getR() {
        return R; // returns number of rows
    }

    public int getC() {
        return C; // returns number of columns
    }

    public void set(int m, int n, double value) {
        data[m][n] = value;
    }

    public double getVal(int i, int j){
        return this.data[i][j];
    }

    //gets user input for every cell in the matrix. beware very large ones.
    public static void matrix_user_input(matrix m) {
        for(int i = 0;i < m.getR();i++){
            for(int j = 0;j < m.getC();j++){
                System.out.println("Input double value for matrix at position (" + i + "," + j + "):");
                m.set(i,j,sc.nextDouble());

            }
        }
        System.out.println("Matrix verbosely populated.");
        for(int i = 0; i < m.C; i++){
            for(int j = 0; j < m.R; j++){
                System.out.print(m.getVal(i,j) + ", ");
            }
            System.out.print("\n");
        }
    }

    //fills matrix with random double values
    public static void matrix_random(matrix m) {
        for(int i = 0;i < m.getR();i++){
            for(int j = 0;j < m.getC();j++){
                m.set(i,j,(Math.random()*100));
            }
        }
        System.out.println("Matrix randomly populated.");
        for(int i = 0; i < m.C; i++){
            for(int j = 0; j < m.R; j++){
                System.out.print(m.getVal(i,j) + ", ");
            }
            System.out.print("\n");
        }
    }

    //takes one matrix, constrains creation of second matrix within calculable bounds.
    public static matrix matrix_maker(matrix a) {
        if(a != null){
            System.out.println("First matrix defined. Matrix creation constrained to " + a.getC() + " rows.");
            int r = a.getC();
            System.out.println("Define number of columns for new Matrix:");
            int c = sc.nextInt();
            matrix generated = new matrix(r,c);
            System.out.println("Do you want to randomly fill the matrix? Y/N:");
            char response = sc.next().charAt(0);
            if(response == 'Y'){
                matrix_random(generated);
                return generated;
            }
            if(response == 'N'){
                matrix_user_input(generated);
                return generated;
            }
            else{
                System.out.println("Invalid response!");
                return null;
            }
        }
        else{
            //if no matrix is passed, generate from nothing.
            System.out.println("No matrix passed, prompting creation.");
            System.out.println("Define number of rows for new Matrix:");
            int r = sc.nextInt();
            System.out.println("Define number of columns for new Matrix:");
            int c = sc.nextInt();
            matrix generated = new matrix(r,c);
            System.out.println("Do you want to randomly fill the matrix? Y/N:");
            char response = sc.next().charAt(0);
            if(response == 'Y'){
                matrix_random(generated);
                return generated;
            }
            if(response == 'N'){
                matrix_user_input(generated);
                return generated;
            }
            else{
                System.out.println("Invalid response!");
                return null;
            }
        }
    }

    //TODO: make a random matrix generator, make a compatible matrix filter


}
