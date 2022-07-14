
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Main {


    public static void main(String[] args) throws Exception {
        BooleanSearchEngine engine = new BooleanSearchEngine(new File("pdfs"));


        try (ServerSocket serverSocket = new ServerSocket(8989)) {
            while (true) {
                try (Socket socket = serverSocket.accept();
                     BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     PrintWriter out = new PrintWriter(socket.getOutputStream())) {
                    Type listType = new TypeToken<List<PageEntry>>() {}.getType();
                    Gson gson = new Gson();
                    String word = in.readLine();
                    if(!engine.responseMap.containsKey(word.toLowerCase())){
                        out.print("Слово не найдено");
                    }else{
                    out.print(gson.toJson(engine.search(word), listType));
                    }
                } catch (IOException | NullPointerException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}