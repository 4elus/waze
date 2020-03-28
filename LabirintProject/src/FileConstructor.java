import java.io.*;

public class FileConstructor {
    BufferedReader reader;
    BufferedWriter writer;
    int lines;
//
    public FileConstructor(String path_in, String path_out) throws IOException {
        this.reader = new BufferedReader(new FileReader(new File(path_in)));
        this.writer = new BufferedWriter(new FileWriter(new File(path_out)));
        this.lines =  Integer.parseInt(reader.readLine());
    }


    public String[] getStrings() throws IOException {
        String[] strings = new String[lines];

        for(int i = 0; i < lines; i++)
        {
            strings[i] = reader.readLine();
        }

        return strings;
    }


    public void close() throws IOException {
        writer.close();
        reader.close();
    }
}
