import java.io.*;
import java.sql.SQLOutput;
import java.util.Random;
import java.util.Scanner;

/*Sample image files provided for this program.
        Image1.raw      a little girl           225 x 180
        Image2.raw      hibiscus flower         300 x 200
        Image3.raw      boys in masks           500 x 500
        Image4.raw      Australian shepherd     500 x 350
        Image 5.raw     trotting horse          500 x 500

 */

public class Main {


    public static void main(String[] args) throws IOException {
        boolean mood = true;

        /*I use a while loop to determine whether the dithering process should go on or not, if the use type yes, then
        the dithering would go on, if the use type no, the program would be terminated.
         */

        while (mood){
            /*Put a loop around all of this so that you can dither a different image file by whatever method
        you choose each time through the loop. This way, you can dither as many images as you
        want by as many methods as you want in one run of the program.*/
            Scanner scnr = new Scanner(System.in);
            System.out.println("What is the input file name?");
            String inputFile = scnr.next();
            System.out.println("What is width of the input?");
            int w = scnr.nextInt();
            System.out.println("What is height of the input?");
            int h = scnr.nextInt();
            System.out.println("What is name of the output file?");
            String outputFile = scnr.next();
            InputStream inputStream = new FileInputStream(inputFile);
            OutputStream outputStream = new FileOutputStream(outputFile);
            System.out.println("What dithering method do you want to use?");
            System.out.print(" 1 for threshold");
            System.out.print(" 2 for random");
            System.out.print(" 3 for pattern");
            System.out.println(" 4 for error diffusion");
            int ditherMethod = scnr.nextInt();
            switch (ditherMethod) {
                case 1:
                    threshold(inputStream, outputStream, w, h);
                    break;
                case 2:
                    random(inputStream, outputStream, w, h);
                    break;
                case 3:
                    pattern(inputStream, outputStream, w, h);
                    break;
                case 4:
                    errDiff(inputStream, outputStream, w, h);
                    break;
                default:
                    System.out.println("Not a valid choice");
                    System.exit(1);
            }
            System.out.println("Type yes to continue, type no to stop");
            String control = scnr.next();
            if(control.equals("yes")){
                mood = true;
            }
            else{
                mood = false;
            }
        }
    }

    public static void threshold(InputStream inputStream, OutputStream outputStream, int w, int h) throws IOException {
        int r = 0;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                int pixel = inputStream.read();
                if (pixel < 128) {
                    outputStream.write(0);
                } else {
                    outputStream.write(255);
                }
            }
        }
    }

    public static void random(InputStream inputStream, OutputStream outputStream, int w, int h) throws IOException {
        /*In this method, you actually wouldn't have to store the pixel values in a 2d array -- you could just
        store the pixel value in a single int, as you do in the threshold method. But I declare and use a 2D array
        here because you have to do it that way in the pattern and error diffusion methods. This code with help you
        with those methods.*/
        Random rnd = new Random();
        int r;
        int[][] pixels = new int[h][w];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                pixels[i][j] = inputStream.read();
                System.out.print(pixels[i][j]);
                r = rnd.nextInt(256);
                if (pixels[i][j] < r) {
                    outputStream.write(0);
                    System.out.println(" ----- 0");
                } else {
                    outputStream.write(255);
                    System.out.println(" ----- 255");
                }
            }
        }
    }

    public static void pattern(InputStream inputStream, OutputStream outputStream, int w, int h) throws IOException {
        int [][] c = new int [h][w]; //this matrix is for comparison
        int [][] d = new int [h][w]; //this matrix is for display
        //this is the matrix for pattern
        int b [][] = {
                {8, 3, 4},
                {6, 1, 2},
                {7, 5, 9},
        };

        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                d[y][x] = inputStream.read();
                c[y][x] = (int) (d[y][x] / 25.6); //get the quotient
            }
        }

        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                int temph = y%3; //as y varies, temph varies, and temph would always be from 0 to 2
                int tempw = x%3; //same as above
                if (c[y][x]<b[temph][tempw]){
                    d[y][x]=0; //if less than pattern, change to black
                }
                else{
                    d[y][x]=255; //if larger than pattern, change to white
                }
            }
        }

        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                outputStream.write(d[y][x]);
            }
        }

        /*The pattern to apply is
        8 3 4
        6 1 2
        7 5 9
        Hard-code the pattern into a 2D array */
    }

    public static void errDiff(InputStream inputStream, OutputStream outputStream, int w, int h) throws IOException {
        int [][] d = new int [h][w];
        int error = 0;

        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                d[y][x] = inputStream.read();
            }
        }

        int err = -1;

        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                /*
                The below is my logic for error, so it basically means if pixel is less than 128, then the error is
                equal to pixel; if pixel is larger than 128, then the error is pixel minus 255
                */
                if (d[y][x] < 128) {
                    err = 0;
                } else {
                    err = 1;
                }
                error = d[y][x] - err * 255;

                //those if statements make sure that the program won't go out of the array's bond
                if (x + 1 < w) {
                    d[y][x + 1] = d[y][x + 1] + error * 7 / 16; //these are according to the source provided
                }

                if (x - 1 >= 0 && y + 1 < h) {
                    d[y + 1][x - 1] = d[y + 1][x - 1] + error * 3 / 16;
                }

                if (y + 1 < h) {
                    d[y + 1][x] = d[y + 1][x] + error * 5 / 16;
                }

                if (x + 1 < w && y + 1 < h) {
                    d[y + 1][x + 1] = d[y + 1][x + 1] + error * 1 / 16;
                }
            }
        }

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (d[i][j] < 128) {
                    d[i][j]=0; //if less than 128, change to black
                }
                else {
                    d[i][j]=255; //if less than 128, change to white
                }
            }
        }

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                outputStream.write(d[i][j]);
            }
        }

       /* The error diffusion pattern is

                p       7
        3       5       1

        Hard-code this pattern into a 2D array
        */
    }
}
