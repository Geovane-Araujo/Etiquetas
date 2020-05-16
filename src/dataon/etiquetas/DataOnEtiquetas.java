
package dataon.etiquetas;

import dataon.etiquetas.Controller.Usb;
import dataon.etiquetas.View.PainelPrincipal;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class DataOnEtiquetas {

    
    public static void main(String[] args) {
       
                try {
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("Windows".equals(info.getName())) {
                UIManager.setLookAndFeel(info.getClassName());
                break;
            }
        }
    } catch (UnsupportedLookAndFeelException e) {
         
        System.out.println("Erro: " + e.getMessage());
        e.printStackTrace();
         
    } catch (ClassNotFoundException e) {
         
        System.out.println("Erro: " + e.getMessage());
        e.printStackTrace();
         
    } catch (InstantiationException e) {
         
        System.out.println("Erro: " + e.getMessage());
        e.printStackTrace();
         
    } catch (IllegalAccessException e) {
         
        System.out.println("Erro: " + e.getMessage());
        e.printStackTrace();
    }
        
        PainelPrincipal pn = new PainelPrincipal();
        pn.setVisible(true);
    }
    
         Usb u = new Usb();
         
    
}
