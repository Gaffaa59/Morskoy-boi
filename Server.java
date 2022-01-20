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

class Server_shashka{
    boolean color;
    boolean alive;
    boolean dama;
    int x, y;

    Server_shashka(boolean color, boolean alive, boolean dama, int x, int y){
        this.color = color; // black - true | white - false
        this.alive = alive;
        this.dama = dama;
        this.x = x;
        this.y = y;
    }

}

public class Server {


    public static void main(String[] ar) {
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

            // Начало логики ----------------------------------

            int hod = 0;
            Server_shashka[][] pole = new Server_shashka[8][8];
            for(int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    pole[i][j] = null;
                }
            }

            for(int i = 0; i < 8; i++){  // Заносим в поле шашки
                int x, y, z;
                if(i % 2 == 0){
                    x = 1;
                    y = 5;
                    z = 7;
                    pole[i][y] = new Server_shashka(false, true, false, i, y);
                }else{
                    x = 0;
                    y = 2;
                    z = 6;
                    pole[i][y] = new Server_shashka(true, true, false, i, y);
                }

                pole[i][x] = new Server_shashka(true, true, false, i, x);
                pole[i][z] = new Server_shashka(false, true, false, i, z);
            }
            /*for(int j = 7; j >= 0; j--){    //  Отрисовка для дебага
                for(int i = 7; i >= 0; i--){
                    //System.out.print(" |" + pole[i][j] + "| ");
                    if(pole[i][j] == null){
                        System.out.print("0 ");
                    }else if(pole[i][j].color == true){
                        System.out.print("1 ");
                    }else{
                        System.out.print("2 ");
                    }
                }
                System.out.println("");
            }*/

            clients[0].out.writeUTF("Ready");
            clients[1].out.writeUTF("Ready");







            //String line = null;



            //clients[0].out.writeInt(1);
            //clients[1].out.writeInt(3);






            // Берем входной и выходной потоки сокета, теперь можем получать и отсылать данные клиенту


            // Конвертируем потоки в другой тип, чтоб легче обрабатывать текстовые сообщения.





            //while(true) {
                /*for(int i = 0; i < clients.length; i++){
                    clients[i].out.writeUTF("all ok!" + i);
                    clients[i].out.flush();
                }*/



                /*if(clients[0].in.readUTF()){
                    System.out.println("0");
                    line = clients[0].in.readUTF(); // ожидаем пока клиент пришлет строку текста.
                    System.out.println("The dumb client just sent me this line : " + line);
                    System.out.println("I'm sending it back...");
                    clients[0].out.writeUTF(line); // отсылаем клиенту обратно ту самую строку текста.
                    clients[0].out.flush(); // заставляем поток закончить передачу данных.
                    System.out.println("Waiting for the next line...");
                    System.out.println();
                    clients[0].in = new DataInputStream(clients[0].socket.getInputStream());
                }
                if(clients[1].in.readUTF() != "null"){
                    System.out.println("1");
                    line = clients[1].in.readUTF(); // ожидаем пока клиент пришлет строку текста.
                    System.out.println("The dumb client just sent me this line : " + line);
                    System.out.println("I'm sending it back...");
                    clients[1].out.writeUTF(line); // отсылаем клиенту обратно ту самую строку текста.
                    clients[1].out.flush(); // заставляем поток закончить передачу данных.
                    System.out.println("Waiting for the next line...");
                    System.out.println();
                    clients[1].in = new DataInputStream(clients[1].socket.getInputStream());
                }*/

            //}
        } catch(Exception x) { x.printStackTrace(); }
    }
}
