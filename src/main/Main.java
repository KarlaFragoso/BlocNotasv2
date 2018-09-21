
/**
 *
 * @author Carina Amairani Díaz Ramírez
 */
package main;

import controllers.ControllerBloc;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import model.ModelBloc;
import view.ViewBloc;

public class Main {
public void readFile(String path){
        try{
            String row;
            try (FileReader file = new FileReader(path)){
                BufferedReader bufferedReader = new BufferedReader(file);
                while ((row = bufferedReader.readLine()) != null) {
                    System.out.println(row);
                }
                bufferedReader.close();
            }
        } catch(FileNotFoundException err){
            System.err.println("File not found: " + err.getMessage());
        } catch(IOException err){
            System.err.println("Error on I/O operation: " + err.getMessage());
        }
    }
    
     public void writeFile(String path, String message){
        try{
            File file = new File(path);
            FileWriter fileWriter = new FileWriter(file, true);
            try(PrintWriter printWriter = new PrintWriter(fileWriter)){
                printWriter.println(message);
                printWriter.close();
            }
        }catch(FileNotFoundException err){
            System.err.println("File not found: " + err.getMessage());
        } catch(IOException err){
            System.err.println("Error on I/O operation: " + err.getMessage());
        }
    }

    public static void main(String[] args) {
        
        ModelBloc modelBloc = new ModelBloc();
         ViewBloc viewBloc = new ViewBloc();
         ControllerBloc controllerBloc = new ControllerBloc(modelBloc, viewBloc);
        
         controllerBloc.iniciar_vista();
         viewBloc.setVisible(true);
  }
}
