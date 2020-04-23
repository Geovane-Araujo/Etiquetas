
package dataon.etiquetas.Controller;

import dataon.etiquetas.View.PainelPrincipal;
import gnu.io.CommPortIdentifier;

import java.nio.file.Files;
import java.nio.file.Path;
import java.io.*;
import java.nio.file.Paths;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.swing.JOptionPane;
import javax.*;
import jssc.SerialPort;
import jssc.SerialPortException;
import sun.print.resources.serviceui;


public class Open {
    

    @SuppressWarnings("empty-statement")
    public void OpenPort(String porta,int params, String caminho ) throws SerialPortException{
       
        SerialPort serial = new SerialPort(porta);

        String ppla = lerppla(caminho);
        String p = "";
       // PrintWriter pr = new PrintWriter(ppla);
        try{
            PainelPrincipal.CampoLog.setText(" Abertura de porta: " + serial.openPort());
            p = PainelPrincipal.CampoLog.getText();
            PainelPrincipal.CampoLog.setText(p + "\n Lido os Parametros : Porta: " + porta + " Velocidade de BIts "+ params);
            p = PainelPrincipal.CampoLog.getText();
            //System.out.println("Params setted: " + serial.setParams(params, 8, 1, 0));
            //pr.println(serial.writeByte(ppla.getBytes()));
            serial.getLinesStatus();
            
            
            System.out.println(serial.writeBytes(ppla.getBytes()));
            
            PainelPrincipal.CampoLog.setText(p +"\n Enviado para impressora");
            p = PainelPrincipal.CampoLog.getText();
           // System.out.println("Port closed: " + serial.closePort());
            PainelPrincipal.CampoLog.setText(p + "\n fechamento da porta: " + serial.closePort());
        }
        catch(SerialPortException e){
            
            PainelPrincipal.CampoLog.setText(p + " "+e);
            //JOptionPane.showMessageDialog(null, e);
        }
        
    }
    

    public String lerppla(String path) {
        String codigo = " ";
        Path caminho = Paths.get(path);
        try{
           byte[] texto = Files.readAllBytes(caminho); 
           codigo = new String(texto);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        
        
        return codigo;
    }
}
