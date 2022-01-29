import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class Storage {

    static final String PROJECT_ROOT = System.getProperty("user.dir");

    public void save(TaskList tasks) {
        try {
            String path  = Path.of(PROJECT_ROOT, "data", "duke.txt").toString();
            FileOutputStream fos = new FileOutputStream(path);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(tasks);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public TaskList load() {
        Path dataFolder = Path.of(PROJECT_ROOT, "data");
        if (!Files.exists(dataFolder)) { //initialize data directory if not present
            new File(dataFolder.toString()).mkdir();
        }
        try {
            String path = Path.of(PROJECT_ROOT, "data", "duke.txt").toString();
            FileInputStream fis = new FileInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(fis);
            return (TaskList) ois.readObject();
        } catch (IOException | ClassNotFoundException e) { //if duke.txt does not exist or is weird
            return new TaskList();
        }
    }
}
