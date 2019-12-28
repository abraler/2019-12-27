package Demo;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class IOdemo {
    private static InputStream 获取一个输入流 ()throws IOException {
        InputStream inputStream;
        //inputStream=new FileInputStream("本地文档.txt");



//        byte[]bytes="内存空间".getBytes("utf-8");
//        inputStream=new ByteArrayInputStream(bytes);

//        inputStream=System.in;
        //网络
        Socket socket=new Socket("www.baidu.com",80);
        OutputStream os=socket.getOutputStream();
        Writer writer=new OutputStreamWriter(os,"utf-8");
        PrintWriter printWriter=new PrintWriter(writer,false);
        printWriter.printf("GET / HTTP/1.0\r\n\r\n");
        printWriter.flush();
        inputStream=socket.getInputStream();
        



        return inputStream;
    }
    /*
    1,直接通过字节方式读，然后程序进行字符编码
    2,把Stream转化为Reader         进行字符形式编码
      2,1直接读
      2,2BufferReader           readLine
   3.Scanner 也可以
     */
    private static String 从字节流中最终获取字符数据(InputStream is)throws IOException{
              //字节流
        /*
        byte[]bytes=new byte[2048];
        int len=is.read(bytes,0,bytes.length);
        String message=new String(bytes,0,len,"utf-8");
        return message;
         */
        /*
        StringBuilder sb=new StringBuilder();

        Reader reader =new InputStreamReader(is,"utf-8");
        char[]buffer=new char[1024];
        int len;
        while((len=reader.read(buffer))!=-1){
            sb.append(buffer,0,len);
        }
        String message =sb.toString();
        return message;
         */

        /*
        Reader reader =new InputStreamReader(is,"utf-8");
        BufferedReader bufferedReader =new BufferedReader(reader);
        String line;
        StringBuilder sb=new StringBuilder();
        while((line=bufferedReader.readLine())!=null){
            sb.append(line+"\r\n");

        }return sb.toString();
        */
        Scanner in=new Scanner(is,"utf-8");
        return in.nextLine();


        /*
        Reader reader =new InputStreamReader(is,"utf-8");
        char[]buffer=new char[1024];
        int len=reader.read(buffer);
        String message =new String(buffer,0,len);
        return message;
         */
    }

    public static void main(String[] args)throws IOException {
        InputStream is=获取一个输入流();
        String message=从字节流中最终获取字符数据(is);
        System.out.println(message);
    }
}
