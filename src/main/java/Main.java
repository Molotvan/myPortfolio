import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        //BooleanSearchEngine engine = new BooleanSearchEngine(new File("pdfs"));
//        System.out.println(engine.search("бизнес"));
//        FileReader reader = new FileReader("pdfs");
        File pdfs = new File("pdfs");
        File[] pdfsList = pdfs.listFiles();
        for (File pdf : pdfsList) {
//            FileReader reader = new FileReader(pdf);
//                reader.read();
                PdfDocument pdfFile = new PdfDocument(new PdfReader(pdf));
            System.out.println(pdf.getName());
//            }catch (IOException e){
//                e.printStackTrace();
//            }

        }
        // здесь создайте сервер, который отвечал бы на нужные запросы
        // слушать он должен порт 8989
        // отвечать на запросы /{word} -> возвращённое значение метода search(word) в JSON-формате
    }
}