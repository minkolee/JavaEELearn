package Net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
public class TCPServer {
	public static void main(String[] args) throws IOException{
		ServerSocket server = new ServerSocket(8888);
		Socket socket = server.accept();
		InputStream in = socket.getInputStream();
		byte[] data = new byte[1024];
		int len = in.read(data);
		System.out.println(new String(data,0,len));
		
		OutputStream out = socket.getOutputStream();
		out.write("返回给客户端的内容".getBytes());

		socket.close();
		server.close();
	}
}
