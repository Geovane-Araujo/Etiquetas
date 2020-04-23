
package dataon.etiquetas.Controller;

import gnu.io.*;
import java.util.Enumeration;

public class Port {
    
    private String[] portName;// portas disponéveis
    private String[] tipoPorta;
    Enumeration listaPorta;
    
    public String[] ReturnPort(){
        
        listaPorta = CommPortIdentifier.getPortIdentifiers();
        portName = new String[10];
        tipoPorta = new String[10];
        
        
        
        int i = 0;
        
        while(listaPorta.hasMoreElements()){
            
            CommPortIdentifier numPort = (CommPortIdentifier) listaPorta.nextElement();
            portName[i] = numPort.getName();
            tipoPorta[i] = TipoPorta(numPort.getPortType());
            i++;
        }
        
        System.out.println(portName);
        return portName;
    }


    
    public String TipoPorta(int tipoPorta){
        
        switch(tipoPorta){
            case CommPortIdentifier.PORT_SERIAL:
                return "Porta Serial";
            case CommPortIdentifier.PORT_PARALLEL:
                return "Porta Paralela";
            case CommPortIdentifier.PORT_I2C:
                return "POrta i2C";
            case CommPortIdentifier.PORT_RAW:
                return "Porta Raw";
            case CommPortIdentifier.PORT_RS485:
                return "POrta RS485"; 
                default:
                    return "Porta não Identificada";
        }  
    }

    public String[] getPortName() {
        return portName;
    }

    public void setPortName(String[] portName) {
        this.portName = portName;
    }

    public String[] getTipoPorta() {
        return tipoPorta;
    }

    public void setTipoPorta(String[] tipoPorta) {
        this.tipoPorta = tipoPorta;
    }
    

}
