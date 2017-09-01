package seng202.team5;

import java.io.FileReader;

public class FileManager {
    private FileReader reader;

    /**
     * Serializes an instance of the User class (exporting out of the program).
     * @param user The User object to be stored in a file.
     * @param fileName Name of the file the user will be stored in.
     */
    public void userSerialize(User user, String fileName){

    }

    /**
     * Deserializes an instance of the User class (importing into the program).
     * @param fileName The file where the instance of the User class exists.
     * @return The User object stored in the named file.
     */
    public User userDeserialize(String fileName) {


        // TODO Get rid of the below code once the function is implemented, only here as a placeholder.
        User user = new User("Not a real user", 107);
        return user;
    }


    /**
     * Method to read a .csv file.
     * TODO: add what the direct parameter means, and a way of determining what type of data the file contains.
     * @param direct
     * @return
     */
    // Do we want to add a type param that just tells what type of data is in the file?
    public void readFile(String direct) {
    }

    /**
     * Function to write a .csv file to import to other instances of the app.
     * @param direct
     * @param contents
     */
    public void writeFile(String direct, String contents) {

    }
}
