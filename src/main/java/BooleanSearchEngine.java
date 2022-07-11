import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;

import javax.print.Doc;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class BooleanSearchEngine implements SearchEngine {
    //???

    public BooleanSearchEngine(File pdfsDir) throws IOException {
        File pdfs = new File("pdfs");
        File[] pdfsList = pdfs.listFiles();
        for (File pdf : pdfsList) {
            try {FileReader reader = new FileReader(pdf);
                 reader.read();
                PdfDocument pdfFile = new PdfDocument(new PdfReader(pdf));
                PdfPage[] filePages = new PdfPage[pdfFile.getNumberOfPages()];
                for (int i=0; i<pdfFile.getNumberOfPages(); i++) {
                    filePages[i] = pdfFile.getPage(i);

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }




    }
    @Override
    public List<PageEntry> search(String word){
        return null;
    }
}
