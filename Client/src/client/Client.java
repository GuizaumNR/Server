/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author Guilherme
 */
public class Client {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      
        try {
            
            int numero4;
           do {
               
            Socket socket = new Socket("localhost", 5555);
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            
            String msg = "Hello";
            output.writeUTF(msg);
            output.flush();

            msg = input.readUTF();
            String numero;
            numero = JOptionPane.showInputDialog(null, msg);
            int numero2 = Integer.parseInt(numero);
            output.write(numero2);
            output.flush();
            while (socket.isClosed() == false) {
                String resposta = input.readUTF();
                System.out.println(resposta);
                  
               } 
             String numero3 = JOptionPane.showInputDialog(null, "Deseja realizar outra consulta? 1- Sim; 2 - Não;");
             
             numero4 = Integer.parseInt(numero3);
            if(numero4 != 1){
            input.close();
            output.close();
            socket.close(); 
            }else{
                
            }
                    
            }while(numero4 == 1);
            
            
            
            
        } catch (IOException e) {

        }
    }

}
