package seng202.team5;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {

    /**
     * Serializes an instance of the User class (exporting out of the program).
     * @param user The User object to be stored in a file
     */
    public void userSerialize(User user){
        try {
            String filePath = "SENG202-Team5-Cyclr/src/main/resources/data_files" + user.getName() + ".ser";
            FileOutputStream userOutFile = new FileOutputStream(filePath);
            ObjectOutputStream out = new ObjectOutputStream(userOutFile);
            out.writeObject(user);
            out.close();
            userOutFile.close();

        } catch (IOException ioExcept) {
            ioExcept.printStackTrace();
        }
    }


    /**
     * Deserializes an instance of the User class (importing into the program).
     * @param nameOfUser The name of the user to be deserialized.
     * @return The User object stored in the named file.
     */
    public User userDeserialize(String nameOfUser) {
        User user = null;
        try {
            String filePath = "SENG202-Team5-Cyclr/src/main/resources/data_files" + nameOfUser + ".ser";
            FileInputStream userInFile = new FileInputStream(filePath);
            ObjectInputStream userObjectStream = new ObjectInputStream(userInFile);
            try {
                user = (User) userObjectStream.readObject();
                userObjectStream.close();
                userInFile.close();
                return user;
            } catch (ClassNotFoundException classException) {
                System.out.println("User class not found");
                classException.printStackTrace();
            }
        } catch (IOException ioExcept) {
            ioExcept.printStackTrace();
        }
        return user;
    }


    /**
     * Method to read a .csv file from a chosen folder.
     * @param fileName The name of the .csv file to be read.
     * @return An arrayList of the data from the .csv file.
     */
    // Do we want to add a type param that just tells what type of data is in the file?
    public ArrayList<String> readFile(String fileName) {
        File file = new File(fileName);
        ArrayList<String> dataList = new ArrayList<String>();
        try {
            Scanner inputStream = new Scanner(file);
            while (inputStream.hasNext()) {
                String data = inputStream.next();
                dataList.add(data);
            }
            inputStream.close();
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        }
        return dataList;
    }


    /**
     * Function to write a .csv file to import to other instances of the app.
     * @param fileName The name of the file for the .csv to
     * @param content The content for the .csv file.
     */
    public void writeFile(String fileName, ArrayList<String> content) {
        try {
            PrintWriter printWriter = new PrintWriter(new File(fileName));
            StringBuilder stringBuilder = new StringBuilder();
            for (String line : content) {
                for (String item : line.split(",")) {
                    stringBuilder.append(item);
                }
                stringBuilder.append('\n');
            }
            printWriter.write(stringBuilder.toString());
            printWriter.close();
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Retrieves the list of the given retailers and converts it into a list of Retailer objects.
     * Then adds this file to the currentStorage of the app.
     * @param currentStorage The instance of the current storage object that the list of retailers will be added to.
     */
    public void retailerRetriever(CurrentStorage currentStorage) {

    }
}
