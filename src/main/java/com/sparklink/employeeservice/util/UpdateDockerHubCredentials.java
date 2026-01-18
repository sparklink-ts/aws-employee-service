package com.sparklink.employeeservice.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class UpdateDockerHubCredentials {

    public static void main(String[] args){

        //String filePath = "C:\\Users\\mmser\\.jenkins\\workspace\\pipeline-sparklink-employee-service-2.0.0-snapshot\\sparklink-docker-hub-token.txt";
        String filePath = "C:\\Users\\mmser\\.jenkins\\workspace\\employee-service\\sparklink-docker-hub-token.txt";

        try{

            Scanner sc = new Scanner(new File(filePath));
            StringBuffer buffer = new StringBuffer();
            //Reading lines of the file and appending them to StringBuffer
            while (sc.hasNextLine()) {
                buffer.append(sc.nextLine()+System.lineSeparator());
            }
            String fileContents = buffer.toString();
            //System.out.println("Contents of the file: "+fileContents);
            //System.out.println("Length of the file: "+fileContents.length());
            //closing the Scanner object
            sc.close();

            fileContents = fileContents.substring(0, (fileContents.length() - 6));

            //System.out.println("Updated Contents of the file: "+fileContents);
            //System.out.println("Length of the file: "+fileContents.length());

            File file = new File(filePath);

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(fileContents);
            bw.close();

            System.out.println("Done");
        }catch(IOException e){
            e.printStackTrace();
        }



    }

}
