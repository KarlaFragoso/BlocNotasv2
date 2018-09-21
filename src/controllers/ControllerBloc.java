/**
 *
 * @author Carina Amairani Díaz Ramírez
 */
package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import model.ModelBloc;
import view.ViewBloc;


public class ControllerBloc implements ActionListener{
    
    private  ModelBloc modelBloc; 
    private  ViewBloc viewBloc; //le grita  a la vista para que venga
    JFileChooser seleccionar=new JFileChooser();
    File archivo;
    FileOutputStream salida;
    
    public ControllerBloc(ModelBloc modelBloc, ViewBloc viewBloc){
        this.modelBloc = modelBloc;
        this.viewBloc = viewBloc;
        
        this.viewBloc.jm_leer.addActionListener(this);
        this.viewBloc.jm_guardar.addActionListener(this);
         initView();
    }
    
    public void iniciar_vista(){
        viewBloc.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == viewBloc.jm_leer){
            jMI_Leer_action_performed();
        }
        if(e.getSource() == viewBloc.jm_guardar) {
            jMI_Guardar_action_performed();
        }
    }
File a;
    private void jMI_Leer_action_performed() {
        
    JFileChooser fileChooser = new JFileChooser(); 
    int seleccion = fileChooser.showOpenDialog(null);
            if(seleccion==JFileChooser.APPROVE_OPTION) 
    {
        FileReader b= null;
        try{
            a=fileChooser.getSelectedFile(); 
            viewBloc.jTA_BlocNotas.setText(a.getAbsolutePath()); 
            System.out.println(a); 
           
            b=new FileReader(a); 
            viewBloc.jTA_BlocNotas.read(b, ""); 
        } catch (Exception ex){
            Logger.getLogger(ControllerBloc.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try{
                b.close();
            }catch(IOException ex){
                Logger.getLogger(ControllerBloc.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
       
    }
              
 }

    private void jMI_Guardar_action_performed() {
        JFileChooser fileChooser = new JFileChooser(); 
        int seleccion= fileChooser.showSaveDialog(viewBloc.jTA_BlocNotas);
        if(seleccion==JFileChooser.APPROVE_OPTION){ 
            File fichero=fileChooser.getSelectedFile();
            try(FileWriter fileWriter = new FileWriter(fichero)){
                fileWriter.write(viewBloc.jTA_BlocNotas.getText());
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        
     
        
    }

    public void initView() {
        viewBloc.setLocationRelativeTo(null);
        viewBloc.jTA_BlocNotas.setText(modelBloc.getMenu());
        viewBloc.setVisible(true);    }

    private String Guardar(File archivo, String Documento) { //metodo de guardar
         String mensaje=null;
         String documento="";
          try{
              salida=new FileOutputStream(archivo);
              byte[] bytxt=documento.getBytes();
              salida.write(bytxt);
              mensaje="Guardado excelente";
          } catch(Exception e){
              return mensaje;
       }
        return null;
    }
}
