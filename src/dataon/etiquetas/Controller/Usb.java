
package dataon.etiquetas.Controller;

import dataon.etiquetas.View.PainelPrincipal;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.swing.JOptionPane;
import javax.swing.SpringLayout;


public class Usb {
    
    private static PrintService impressora;
    private static PrintService[] pse;
    
    public Usb(){
        
    }
    
    public void detectaImpressora(){
        
        try{
            DocFlavor df = DocFlavor.SERVICE_FORMATTED.PRINTABLE;
            PrintService[] ps = PrintServiceLookup.lookupPrintServices(df,null);
            int i = 0;
            for(PrintService p:ps){
                //System.out.println("Impressora encontrada: "+ p.getName());
               
                impressora = p;
                
                i = i+1;
                PainelPrincipal.campoimpressora.addItem(p.getName());
            }
            pse = ps;
        }
        catch(Exception e){
            PainelPrincipal.CampoLog1.setText(PainelPrincipal.CampoLog1.getText()+"\n\n" + e);
        }
    }
    
    public synchronized  boolean imprime(String caminho, int indice,int quantidade){
        boolean ret;
        Open lerppla = new Open();
        PainelPrincipal.CampoLog1.setText(PainelPrincipal.CampoLog1.getText()+"Preparando arquivo a ser impresso ");
        PainelPrincipal.CampoLog1.setText(PainelPrincipal.CampoLog1.getText()+"\n************************************************");
        String ppla = lerppla.lerppla(caminho);
        PainelPrincipal.CampoLog1.setText(PainelPrincipal.CampoLog1.getText()+"\nPreparação concluída");
        
        try{
            PainelPrincipal.CampoLog1.setText(PainelPrincipal.CampoLog1.getText()+"\nEnviando arquivo para impressora ");
            DocPrintJob dpj = pse[indice].createPrintJob();
            InputStream stream = new ByteArrayInputStream(ppla.getBytes());
            
            DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
            Doc doc = new SimpleDoc(stream,flavor,null);
            for(int i = 0;i < quantidade;i++){
                
                dpj.print(doc, null);
                
            }
            PainelPrincipal.CampoLog1.setText(PainelPrincipal.CampoLog1.getText()+"\nArquivos enviados com sucesso!! ");
            ret =  true;
            
        }
        catch(Exception e){
            PainelPrincipal.CampoLog1.setText(PainelPrincipal.CampoLog1.getText()+"\n\n" + e);
            ret = false;
        }
        
        return ret;
    }
    
}
