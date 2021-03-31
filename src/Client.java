import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class  Client{
    private static int rand, fromServer =0,tempResult=0;
    public static void main(String[] args) throws IOException, InterruptedException {
        //建立連線指定Ip和埠的socket
        Socket socket = new Socket("120.101.8.185",5000);
        PrintWriter out = new PrintWriter(socket.getOutputStream());
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        //建立一個執行緒用於讀取伺服器的資訊
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (fromServer <20){
                        fromServer = Integer.parseInt(in.readLine());
                        rand = (int)(Math.random() * 10) - 4;
                        tempResult = fromServer + rand;
                        System.out.println("C : " + fromServer + " + (" + rand + ") = " + tempResult);

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        while(fromServer <20){
            out.println(tempResult);
            out.flush();
            Thread.sleep(50);
            //顯示輸入的資訊
//            line = reader.readLine();
        }
        System.out.println("sum_result >= 20; stop!");
        out.close();
        in.close();
        socket.close();

    }
}