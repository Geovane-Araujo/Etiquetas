/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataon.etiquetas.Controller;

import dataon.etiquetas.View.PainelPrincipal;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import java.io.InputStream;
import java.io.OutputStream;


/**
 *
 * @author geova
 */
public class OpenPort {
    public String Log = " ";
   
    public SerialPort porta;
    private CommPortIdentifier cp;
    private boolean PortaOk;
    private String Porta;
    private int baudarte;
    private int timeout;
    private String ppla;
    private OutputStream saida;
    private InputStream entrada;
    Open pn = new Open();
    
    public void OpenPort2(String Porta,int params, String caminho ){
    try{
        cp = CommPortIdentifier.getPortIdentifier(Porta);
      
        PortaOk = true;
        porta.setSerialPortParams(params,porta.DATABITS_8,porta.STOPBITS_2,porta.PARITY_NONE);
        PainelPrincipal.CampoLog.setText(PainelPrincipal.CampoLog.getText()+"Porta:" +Porta + "aberta com sucesso");
        ppla = pn.lerppla(caminho);



    }
    catch(Exception e){
        PortaOk = false;
        PainelPrincipal.CampoLog.setText(PainelPrincipal.CampoLog.getText() + "\nFalha na abertura da porta"+e);
    }
        
    }
        
    public void EnviarEtiqueta(String caminho, String Porta){
        try{
            
            saida = porta.getOutputStream();
            PainelPrincipal.CampoLog.setText(PainelPrincipal.CampoLog.getText()+"\nFluxo Enviado");

        }
        catch(Exception e){
            PainelPrincipal.CampoLog.setText(PainelPrincipal.CampoLog.getText()+"\nHouve uma Falha"+ e);
        }
        try{
            PainelPrincipal.CampoLog.setText(PainelPrincipal.CampoLog.getText()+"\nEnviando Byts para a Porta" + Porta);
            ppla = pn.lerppla(caminho);
            saida.write(ppla.getBytes());
            Thread.sleep(5000);
            saida.flush();
            PainelPrincipal.CampoLog.setText(PainelPrincipal.CampoLog.getText()+"\nEnviando com sucesso");
        }
        catch(Exception e){
            PainelPrincipal.CampoLog.setText(PainelPrincipal.CampoLog.getText()+"\nHouve Falha " +e);
        }
    }
    
//    public void LerDados(){
//    
//        try {
//            entrada = porta.getInputStream();
//            System.out.println("FLUXO OK!");
//        } catch (Exception e) {
//            System.out.println("Erro.STATUS: " + e );
//            System.exit(1);
//        }
//        try {
//            porta.addEventListener(this);
//            System.out.println("SUCESSO. Porta aguardando...");
//        } catch (Exception e) {
//            System.out.println("Erro ao criar listener: ");
//            System.out.println("STATUS: " + e);
//            System.exit(1);
//        }
//        porta.notifyOnDataAvailable(true);
//        try {
//            threadLeitura = new Thread(this);
//            threadLeitura.start();
//        } catch (Exception e) {
//            System.out.println("Erro ao iniciar leitura: " + e );
//        }
//    }
    
    public void ClosePort(){
    try {
        porta.close();
        PainelPrincipal.CampoLog.setText("Conexão Encerrada");
    } catch (Exception e) {
        System.out.println("ERRO AO FECHAR. STATUS: " + e );
        System.exit(0);
    }
}
    

}
