package ch.heigvd;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.charset.StandardCharsets;
import java.nio.charset.UnsupportedCharsetException;

public class Config {
    public Charset encoding;
    public boolean includeDateInFilename;
    public boolean includeClassNameInFilename;
    public String[] subdirs;
    public String notesFileFormat;

    //Json object mapper
    private static ObjectMapper mapper = new ObjectMapper();

    /**
     * Construct a construct object with default attributes values: <br><br>
     * {<br>
     * encoding = UTF-8,<br>
     * includeDateInFilename = true,<br>
     * includeClassNameInFilename = true,<br>
     * subdirs = {"class material", "course notes"},<br>
     * notesFileFormat = "md" <br>
     * }
     */
    public Config(){
        encoding = StandardCharsets.UTF_8;
        includeDateInFilename = true;
        includeClassNameInFilename = true;
        subdirs = new String[]{"class material", "course notes"};
        notesFileFormat = "md";
    }

    /**
     * Construct a new Config object
     * @param encoding the encoding of the newly created note files
     * @param includeDateInFilename should the filename of the new notes contain today's date
     * @param includeClassNameInFilename should the filename of the new notes contain the class name
     * @param notesFileFormat file format of the course notes. Supported file formats {.txt, .md}. Default: .md (markdown)
     * @param subdirs subdirectories to create when creating a new class file tree. Default: {"class material", "course notes"}
     */
    public Config(String encoding, boolean includeDateInFilename, boolean includeClassNameInFilename, String notesFileFormat, String[] subdirs){
        try{
            //Check if the given encoding is valid and converts it from a String to an actual charset object
            //Source: https://www.geeksforgeeks.org/java/charset-forname-method-in-java-with-examples/
            this.encoding = Charset.forName(encoding);
        }
        catch(IllegalArgumentException e){
            System.out.println("Invalid or Unsupported encoding");
            System.out.println("Defaulting to UTF-8");
            this.encoding = StandardCharsets.UTF_8;
        }
        this.includeDateInFilename = includeDateInFilename;
        this.includeClassNameInFilename = includeClassNameInFilename;

        //Safe way to copy an array to another
        //Source: for this one I got help from ChatGPT
        this.subdirs = (subdirs != null) ? subdirs.clone() : new String[]{"class material", "course notes"};

        if(notesFileFormat.isEmpty()){
            this.notesFileFormat = "md";
        }else{
            this.notesFileFormat = notesFileFormat;
        }
    }

    /**
     * Parse Config from a JSON file to a Config object
     * @param filename the file containing the config
     * @return a Config object initialized with the content of the JSON file
     * @throws IOException if the file doesn't exist of failed to open. Or if the JSON doesn't correspond to the Config object structure
     */
    static public Config getConfigFromFile(String filename) throws IOException{
        try(Reader rd = new FileReader(filename, StandardCharsets.UTF_8); BufferedReader brd = new BufferedReader(rd)){
            return mapper.readValue(brd, ch.heigvd.Config.class);
        }
    }

    /**
     * Write Config object to file as JSON
     * @param filename the file to write to
     * @throws IOException if the file is missing or failed to open
     */
    public void writeConfigToFile(String filename) throws IOException{
        try(Writer wr = new FileWriter(filename, StandardCharsets.UTF_8); BufferedWriter bwr = new BufferedWriter(wr)){
            mapper.writeValue(bwr, this);
        }
    }

    /**
     * Overload of toString() method just for test purposes
     * @return a string representation of the Config object
     */
    @Override
    public String toString(){
        return "Encoding=" + this.encoding +
                ", includeDateInFileName=" + this.includeDateInFilename +
                ", includeClassNameInFilename=" + this.includeClassNameInFilename +
                ", subdirs=[" + String.join(",", this.subdirs) +
                "], noteFileFormat=" + this.notesFileFormat;
    }

}
