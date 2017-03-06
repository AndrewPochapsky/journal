package sample;

import java.io.*;

public class ProgramController {

    private static User currentUser;

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        ProgramController.currentUser = currentUser;
    }

    public static void saveUser(User user)throws IOException{
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("users/user"+user.getId()+".bin"));
        objectOutputStream.writeObject(user);
    }

    public static void loadUser(int id)throws IOException, ClassNotFoundException{
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("users/user"+id+".bin"));
        User user = (User)objectInputStream.readObject();
        ProgramController.setCurrentUser(user);
    }



}
