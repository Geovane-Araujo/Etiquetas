
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
        int qt = Integer.parseInt(PainelPrincipal.Quantidade.getValue().toString());
        
        
        
        
       
        try{
            
            
            String ppla = lerppla(caminho);
            PainelPrincipal.CampoLog.setText(PainelPrincipal.CampoLog.getText()+" Abertura de porta: " + serial.openPort());
            PainelPrincipal.CampoLog.setText(PainelPrincipal.CampoLog.getText()+"\n************************************************");
            for(int i=0;i < qt;i++){
            PainelPrincipal.CampoLog.setText(PainelPrincipal.CampoLog.getText()+ "\n Efetuando leitura dos Parametros : Porta: " + porta);
            serial.getLinesStatus();
            PainelPrincipal.CampoLog.setText(PainelPrincipal.CampoLog.getText()+"\n Enviado para impressora");
            serial.writeBytes(ppla.getBytes());
            PainelPrincipal.CampoLog.setText(PainelPrincipal.CampoLog.getText()+"\n Imprimido "+(i+1) + " com sucesso!!");
            PainelPrincipal.CampoLog.setText(PainelPrincipal.CampoLog.getText()+"\n************************************************");
            }
            PainelPrincipal.CampoLog.setText(PainelPrincipal.CampoLog.getText()+ "\n fechamento da porta: " + serial.closePort()+"\n\n");
            
        }
        catch(SerialPortException e){
            
            PainelPrincipal.CampoLog.setText(PainelPrincipal.CampoLog.getText()+ " "+e);
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
