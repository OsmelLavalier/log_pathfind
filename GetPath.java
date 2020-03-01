import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;


public class GetPath
{

    public Scanner getPath(String file_name) 
    {
        String tmp = null;
        
        tmp = file_name.replace("[", "\\\\[").replace("]", "\\\\]");

        Process p = null;
        try
        {
            //init shell
            ProcessBuilder builder = new ProcessBuilder("/bin/bash");
            p = builder.start();
            
            //get stdin 
            BufferedWriter p_stdin = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));
            p_stdin.write("find $(cd ..; pwd) -name " + tmp);
            p_stdin.newLine();
            p_stdin.flush();

        } 
        catch(IOException e) 
        {
            System.err.println(e);
        }

        Scanner sc = new Scanner(p.getInputStream());
        return sc;
    }
}
