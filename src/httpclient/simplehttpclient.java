package httpclient;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class simplehttpclient {
        public static void main(String[] args) throws IOException {
        //拼写请求命令
        String request="GET / HTTP/1.0\r\nHost: www.baidu.com\r\n\r\n";
        //建立连接
        Socket socket =new Socket("www.baidu.com",80);
        //发送命令
        socket.getOutputStream().write(request.getBytes("utf-8"));
        byte[]bytes=new byte[4096];
        //读到bytes中
        int len=socket.getInputStream().read(bytes);
        //响应格式(response)
            //响应行/状态行  版本 状态码 状态描述
            //响应头：值
            //空行
            //响应正文
        int index=-1;
        for (int i = 0; i <len-3 ; i++) {
            if(bytes[i]=='\r'&&bytes[i+1]=='\n'&&bytes[i+2]=='\r'&&bytes[i+3]=='\n'){
                index=i;
                break;
            }
        }
        ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(bytes,0,index+3);
            Scanner in=new Scanner(byteArrayInputStream,"utf-8");
            String status=in.nextLine();
            String []a=status.split(" ");
            System.out.println("版本"+a[0].trim());
            System.out.println("状态码"+a[1].trim());
            System.out.println("状态描述"+a[2].trim());
            String line;
            int length=-1;
            while(!(line=in.nextLine()).isEmpty()){
                String []kv=line.split(":");
                System.out.println("响应头"+kv[0]+"      "+kv[1]);
                if(kv[0].equalsIgnoreCase("Content-Length")){
                    length=Integer.valueOf(kv[1].trim());
                }
            }
            int readed=bytes.length-index-4;
            int Notread=length-readed;
            byte[]bytes1=new byte[length];
            System.arraycopy(bytes,index+4,bytes1,0,readed);
            socket.getInputStream().read(bytes1,readed,Notread-readed);
            FileOutputStream os=new FileOutputStream("百度.html");
            os.write(bytes1);
        }
}
