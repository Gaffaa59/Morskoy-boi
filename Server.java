import java.net.*;
import java.io.*;

class Server_client{
    Socket socket;
    DataInputStream in;
    DataOutputStream out;
    int No = -1;

    Server_client(Socket s){
        socket = s;
    }

}

public class Server {
    public static void main(String[] ar)    {
        int port = 6666; // случайный порт (может быть любое число от 1025 до 65535)
        try {
            ServerSocket ss = new ServerSocket(port); // создаем сокет сервера и привязываем его к вышеуказанному порту
            System.out.println("Waiting for a client...");

            int clientNo = 0;

            Server_client clients[] = new Server_client[2];

            while (clientNo < 2){
                Server_client client = new Server_client(ss.accept());
                client.in = new DataInputStream(client.socket.getInputStream());
                client.out = new DataOutputStream(client.socket.getOutputStream());
                clients[clientNo] = client;
                client.No = clientNo;
                //clients[clientNo].socket = ss.accept(); // заставляем сервер ждать подключений и выводим сообщение когда кто-то связался с сервером

                clientNo++;
                System.out.println("Got a client No " + clients[clientNo-1].No);
                System.out.println();
            }


            // Берем входной и выходной потоки сокета, теперь можем получать и отсылать данные клиенту


            // Конвертируем потоки в другой тип, чтоб легче обрабатывать текстовые сообщения.


            String line = null;


            while(true) {
                for(int i = 0; i < clients.length; i++){
                    clients[i].out.writeUTF("all ok!" + i);
                    clients[i].out.flush();
                }
                /*line = clients[0].in.readUTF(); // ожидаем пока клиент пришлет строку текста.
                System.out.println("The dumb client just sent me this line : " + line);
                System.out.println("I'm sending it back...");
                clients[0].out.writeUTF(line); // отсылаем клиенту обратно ту самую строку текста.
                clients[0].out.flush(); // заставляем поток закончить передачу данных.
                System.out.println("Waiting for the next line...");
                System.out.println();*/
            }
        } catch(Exception x) { x.printStackTrace(); }
    }
}