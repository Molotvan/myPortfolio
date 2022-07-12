import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        BooleanSearchEngine engine = new BooleanSearchEngine(new File("pdfs"));

        System.out.println(engine.search("бизнес"));
        System.out.println(engine.search("бизнес").size());
        }
        try (ServerSocket serverSocket = new ServerSocket(8989);) { // стартуем сервер один(!) раз
        while (true) { // в цикле(!) принимаем подключения
            try (
                    Socket socket = serverSocket.accept();
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintWriter out = new PrintWriter(socket.getOutputStream());
            ) {
                // обработка одного подключения
            }
        }
    } catch (IOException e) {
        System.out.println("Не могу стартовать сервер");
        e.printStackTrace();
    }
        // здесь создайте сервер, который отвечал бы на нужные запросы
        // слушать он должен порт 8989
        // отвечать на запросы /{word} -> возвращённое значение метода search(word) в JSON-формате
    }