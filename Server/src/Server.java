import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

import javax.print.attribute.standard.Severity;

public class Server {
  
	private ServerSocket serverSocket;
	private static String VIRGULA = ",";
	String matri;
	String nome;
	String nota1;
	String nota2;
	String nota3;
	Date hoje = new Date();
	String hora;
	
	// Ruhan é meu pai
	private void criarServerSocket(int porta) throws IOException {
		serverSocket = new ServerSocket(porta);
	}
	private Socket esperaConexao() throws IOException {
		Socket socket = serverSocket.accept();
		return socket;
	}
	
	private void fechaSocket(Socket s) throws IOException {
		s.close();
	}
	
	public void tratarConexao(Socket socket) throws IOException {
		try {
			
	    BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Users\\Guilherme\\Desktop\\Trabalho\\Arquivos CSV\\entrada.csv")));
		String linha = null;	
		ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
		ObjectInputStream input = new ObjectInputStream( socket.getInputStream() );
		String msg = input.readUTF();
		System.out.println("Mensagem recebida!");
		int i2 = hoje.getHours();
		if( 12 > i2 & i2 >= 6) {
			hora = "bom dia";
		}else if(19 > i2 & i2 >= 12) {
			hora = "boa tarde";
		}else if( i2 >= 19) {
			hora = "boa noite";
		}else if(6 > i2) {
			hora = "boa madrugada";
		}
		
		while(socket.isClosed() == false) {
		
		output.writeUTF("Olá, "+ hora +", escolha o número referente a ação que deseja realizar:"
				+ " 1 - Receber matrículas;"
				+ " 2 - Receber nomes;"
				+ " 3 - Receber notas;"
				+ " 4 - Receber tudo;"); 
		output.flush();
		
		
		int numero = 0;
		numero = input.read();
		if(numero == 1 || numero == 2 || numero == 3 || numero == 4 ) {
		System.out.println("Ação selecionada: "+ numero );
		}else {
        	System.out.println("Ação selecionada invalida.");
        }
		while ( (linha = reader.readLine()) != null) {
            String[] dadosAluno = linha.split(VIRGULA);      
        if( linha != null)   {
        if(numero == 1) {
        matri = ("Matricula: " + dadosAluno[0]);
        output.writeUTF(matri);
        output.flush();
        }
        else if(numero == 2) {
        nome = ("Nome: " + dadosAluno[1]);
        output.writeUTF(nome);
        output.flush();
        }
        else if(numero == 3 ) {
        nota1 = ("Nota 1: " + dadosAluno[2]);
        output.writeUTF(nota1);
        output.flush();
        nota2 = ("Nota 2: " + dadosAluno[3]);
        output.writeUTF(nota2);
        output.flush();
        nota3 = ("Nota 3: " + dadosAluno[4]);
        output.writeUTF(nota3);
        output.flush();
        }
        else if(numero == 4) {
        	matri = ("Matricula: " + dadosAluno[0]);
            output.writeUTF(matri);
            output.flush();
        	nome = ("Nome: " + dadosAluno[1]);
            output.writeUTF(nome);
            output.flush();
        	nota1 = ("Nota 1: " + dadosAluno[2]);
            output.writeUTF(nota1);
            output.flush();
            nota2 = ("Nota 2: " + dadosAluno[3]);
            output.writeUTF(nota2);
            output.flush();
            nota3 = ("Nota 3: " + dadosAluno[4]);
            output.writeUTF(nota3);
            output.flush();
        }
        while(numero != 1 & numero != 2 & numero != 3 & numero != 4) {
        	output.writeUTF("Você não selecionou nenhuma das opções validas, tente novamente, servidor finalizado.");
            output.flush();
            input.close();
            output.close();
        }
     
            }}
		
		break;}
		

		
		} catch(IOException e){
		System.out.println("Problema ao se conectar com o cliente: " + socket.getInetAddress());
		System.out.println("Erro " + e.getMessage());
	} /*finally {
		fechaSocket(socket);
	}*/
	}
	public static void main(String[] args) {
		try {
			do {
		    Server server = new Server();
		    System.out.println("Aguardando conexão...");
			server.criarServerSocket(5555);
	    	Socket socket = server.esperaConexao();
	    	System.out.println("Cliente conectado.");
		    server.tratarConexao(socket);
	        System.out.println("Cliente finalizado.");
			} while(1 == 1);
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
}
