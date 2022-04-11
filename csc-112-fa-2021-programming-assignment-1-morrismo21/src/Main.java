/*Morris Mo
CSC112 Fall 2021
Programming Assignment 1
September 27, 2021
This program reads data from file and store them into a 2 dimensional array. Columns are sorted according to last name
and elements of each student are displayed in the output file. Students are then divided into a groups accordingly, and
a group leader is chosen randomly and the average average of the grade is displayed.
 */

import java.util.Scanner;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class Main {
    public static void main (String[] args) throws IOException {

        //Setup
        FileInputStream fileByteStream = null;
        Scanner inFS = null;
        FileOutputStream fileStream = null;
        PrintWriter outFS = null;
        Random randGen = new Random();
        int totalnum=0, studentnum=0, totalscore=0, groupnum=0, randnum=0,remain=0;
        double averagescore=0.0, totalscore1=0.0, averagescore1=0.0;
        String test;

        //file input & output
        fileByteStream = new FileInputStream("input.txt");
        inFS = new Scanner(fileByteStream);
        fileStream = new FileOutputStream("myoutfile.txt");
        outFS = new PrintWriter(fileStream);

        //get the number of students
        while(inFS.hasNext()){
            test = inFS.next();
            totalnum++;
        }
        studentnum=totalnum/9;

        //construct the 2 dimensional array
        String [][] array = new String[5][studentnum];

        //read the file again, since it has been readed once
        fileByteStream = new FileInputStream("input.txt");
        inFS = new Scanner(fileByteStream);

        //fill in the array with data of the input file
        for (int i = 0; i<studentnum; i++){ //loop though every student
            for (int j = 0; j<4; j++) { //loop though first, middle, last name, and ID
                array[j][i] = inFS.next();
            }
            for (int j = 0; j<5; j++) { //loop though scores and calculate the total
                totalscore = totalscore + inFS.nextInt();
            }
            averagescore = totalscore/5.0; //calculate the student's average score
            array[4][i] = String.valueOf(averagescore); //convert the double into string
            totalscore = 0; //reset the value
        }

        //sort the array
        String [][] temp = new String [5][1]; //I construct a temp array to store the array elements to be swapped
        String tem1 = "";
        for (int i = 0; i < studentnum; i++){
            for (int j = 1; j<studentnum-i; j++){
                //bubble sort (2 dimensional array edition)
                if (array[2][j-1].compareTo(array[2][j])>0){
                    for (int k = 0; k<5; k++){ //I do the whole column swap
                        temp[k][0] = array[k][j-1];
                        array[k][j-1] = array[k][j];
                        array[k][j] = temp[k][0];
                    }
                }
            }
        }

        //file output section 1
        for (int i = 0; i< studentnum; i++){
            outFS.print(array[2][i]+", "); //print last name
            for (int j = 0; j<=1; j++){
                outFS.print(array[j][i]+" "); //print first name and middle name
            }
            outFS.println();
            for (int j = 3; j<=4; j++){
                outFS.println(array[j][i]); //print the student ID and average score
            }
        }
        outFS.println();

        //find the group numbers
        groupnum = studentnum/4;
        if (studentnum%4>1){
            groupnum++;
        }

        //file output section 2 (excluding the last group)
        for (int i=1; i<=groupnum-1; i++){ //exclude the last group, so i<=groupnum-1
            outFS.println("GROUP "+i); //print group number
            randnum = randGen.nextInt(4); //generate the random group leader
            for (int j=0; j<4; j++){
                //print names
                for (int k=0; k<3; k++){
                    outFS.print(array[k][j+(i-1)*4]+" ");
                }
                outFS.println();
                totalscore1+= Double.valueOf(array[4][j+(i-1)*4]); //convert string into double
            }
            averagescore1 = totalscore1/4.0;
            for (int j=0; j<4; j++){
                //print group leader
                if (j==randnum){
                    outFS.print("Group "+i+"'s leader: ");
                    for (int k=0; k<3; k++){
                        outFS.print(array[k][j+(i-1)*4]+" "); //print the name of leader
                    }
                    outFS.println();
                }
            }
            outFS.println("Group average = "+averagescore1); //print the average score
            totalscore1=0.0; //reset the value
            outFS.println();
        }

        //file output section 2 (the last group)
        //this is the case when the remainder is 1, so the last group has 5 people
        if (studentnum%4==1){
            outFS.println("GROUP "+groupnum); //print group number
            randnum = randGen.nextInt(5); //random generate the group leader
            for (int i = studentnum-5; i<studentnum; i++){
                for (int k=0; k<3; k++){
                    outFS.print(array[k][i]+" "); //print names
                }
                outFS.println();
                totalscore1+= Double.valueOf(array[4][i]); //convert string into double
            }
            averagescore1 = totalscore1/5.0;
            for (int i = studentnum-5; i<studentnum; i++){
                if((i-(groupnum-1)*4)==randnum){
                    outFS.print("Group "+groupnum+"'s leader: ");
                    for (int k=0; k<3; k++){
                        outFS.print(array[k][i]+" "); //print group leader
                    }
                    outFS.println();
                }
            }
            outFS.println("Group average = "+averagescore1); //print average score
            totalscore1=0.0; //reset the value
            outFS.println();
        }

        //this is the case when the remainder is 2, 3, or 4, so the last group has 2, 3, or 4 perople
        else{
            remain=studentnum%4;
            /*this if statement is to make sure the value of remain>0, since when there is 4 people left, the remainder
            is zero, but the value remain couldn't be zero since the random generator code needs the value of remain to
            be positive. So I set remian to be 4 if the remian is 0, very reasonable.
             */
            if (remain==0){
                remain=4;
            }
            outFS.println("GROUP "+groupnum); //print group number
            randnum = randGen.nextInt(remain); //random generate the group leader
            for (int i = studentnum-remain; i<studentnum; i++){
                for (int k=0; k<3; k++){
                    outFS.print(array[k][i]+" "); //print names
                }
                outFS.println();
                totalscore1+= Double.valueOf(array[4][i]); //convert string into double
            }
            averagescore1 = totalscore1/remain;
            for (int i = studentnum-remain; i<studentnum; i++){
                if((i-(groupnum-1)*4)==randnum){
                    outFS.print("Group "+groupnum+"'s leader: ");
                    for (int k=0; k<3; k++){
                        outFS.print(array[k][i]+" "); //print group leader
                    }
                    outFS.println();
                }
            }
            outFS.println("Group average = "+averagescore1); //print average score
            totalscore1=0.0; //reset the value
            outFS.println();
        }

        //closure
        fileByteStream.close();
        outFS.close();
    }
}
