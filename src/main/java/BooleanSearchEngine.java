import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BooleanSearchEngine implements SearchEngine {
    public Map<String, List<PageEntry>> responseMap = new HashMap<>();


    public BooleanSearchEngine(File pdfsDir) throws IOException {
        File pdfs = new File("pdfs");
        File[] pdfsList = pdfs.listFiles();
        for (File pdf : pdfsList) {
            String fileName = pdf.getName();
            try(PdfDocument pdfFile = new PdfDocument(new PdfReader(pdf))){
                for (int i=1; i<=pdfFile.getNumberOfPages(); i++) {
                    PdfPage page = pdfFile.getPage(i);
                    String text = PdfTextExtractor.getTextFromPage(page);
                    String[] words = text.split("\\P{IsAlphabetic}+");
                    Map<String, Integer> freqs = new HashMap<>();
                    for (var word : words) {
                        if (word.isEmpty()) {
                            continue;
                        }
                        freqs.put(word.toLowerCase(), freqs.getOrDefault(word.toLowerCase(), 0) + 1);
                    }

                    for (String word : freqs.keySet()) {
                        List<PageEntry> pageEntries = responseMap.getOrDefault(word, new ArrayList<>());
                        pageEntries.add(new PageEntry(fileName, i, freqs.get(word)));
                        responseMap.put(word, pageEntries);
                    }
                }
        }catch (IOException e){
                e.printStackTrace();
            }
            }




    }
    @Override
    public List<PageEntry> search(String word)throws NullPointerException{
        if (!responseMap.containsKey(word.toLowerCase())) {
            throw new NullPointerException("Слово не найдено");
        }
        List<PageEntry> list = responseMap.get(word.toLowerCase());
                list.sort(PageEntry::compareTo);
         return list;

    }
}
