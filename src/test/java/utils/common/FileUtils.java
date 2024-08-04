package utils.common;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileUtils {

  public static List<File> listFilesFolder(String folderPath, List<File> files) {
    File folder = new File(folderPath);

    // Get all files from a directory.
    File[] fList = folder.listFiles();
    if (fList != null) {
      for (File file : fList) {
        if (file.isFile()) {
          files.add(file);
        } else if (file.isDirectory()) {
          listFilesFolder(file.getAbsolutePath(), files);
        }
      }
    }
    return files;
  }

  public static void addContentFromFileToFile(Path sourceFilePath, Path targetFilePath)
      throws IOException {

    File fin = new File(sourceFilePath.toString());
    FileInputStream fis = new FileInputStream(fin);
    BufferedReader in = new BufferedReader(new InputStreamReader(fis));

    FileWriter fstream = new FileWriter(targetFilePath.toString(), true);
    BufferedWriter out = new BufferedWriter(fstream);

    String aLine = null;
    while ((aLine = in.readLine()) != null) {
      // Process each line and add output to Dest.txt file
      out.write(aLine);
      out.newLine();
    }

    // do not forget to close the buffer reader
    in.close();

    // close buffer writer
    out.close();
  }

  public static boolean createNewFile(Path filePath) {
    boolean fileCreated = false;
    try {
      File myObj = new File(filePath.toString());
      fileCreated = myObj.createNewFile();
      if (fileCreated) {
        System.out.println("File created: " + myObj.getName());
      } else {
        System.out.println("File already exists.");
      }
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
    return fileCreated;
  }

  public static void createNewDirectory(Path filePath) {
    System.out.println("Directory created: " + filePath);
    try {
      Path path = Paths.get(filePath.toString());
      Files.createDirectories(path);

    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
}
