
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;

import java.io.*;
import java.lang.reflect.Type;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

public class Main {



    public static void main(String[] args) throws Exception {
        BooleanSearchEngine engine = new BooleanSearchEngine (new File("pdfs"));

//        System.out.println(engine.search("бизнес"));
//        System.out.println(engine.search("бизнес").size());

        try (ServerSocket serverSocket = new ServerSocket(8989);) { // стартуем сервер один(!) раз
        while (true) {
                try(Socket socket = serverSocket.accept();
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 PrintWriter out = new PrintWriter(socket.getOutputStream())){
                Type listType = new TypeToken<List<PageEntry>>() {}.getType();
                Gson gson = new Gson();
                out.print( gson.toJson(engine.search(in.readLine()), listType));
            } catch (IOException ex) {
                    ex.printStackTrace();
                }
        }
    }
    }
    }