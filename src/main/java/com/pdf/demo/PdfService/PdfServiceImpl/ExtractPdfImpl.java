package com.pdf.demo.PdfService.PdfServiceImpl;

import com.pdf.demo.PdfService.ExtractPdf;
import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@Service
public class ExtractPdfImpl implements ExtractPdf {

    private static final Logger logger = LoggerFactory.getLogger(ExtractPdfImpl.class);
    @Autowired
    private ExtractPdf extractPdf;

    public void start() throws Exception{
        int fromPage = 7;
        int toPage = 14;
        String getPdfLocation = "/home/gupta/Desktop/test.pdf";
        String savePdfLocation = "/home/gupta/Desktop/sample.pdf";
        boolean isSuccess = extractPdf.splitPdf(getPdfLocation, savePdfLocation, fromPage, toPage);
        if (isSuccess){
            logger.info("PDF Extraction Success and Save to Given Location");
        }else {
            logger.error("PDF Extraction Failed");
        }
    }

    @Override
    public boolean splitPdf(String getPdfLocation,String savePdfLocation,int fromPage, int toPage){
        try {
            File file = new File(getPdfLocation);
            PDDocument pdfFile = PDDocument.load(file);
            int numberOfPagesInPdf = pdfFile.getNumberOfPages();
            logger.info("Total Number Of Pages in this Pdf " + numberOfPagesInPdf);
            if (numberOfPagesInPdf >=1) {
                Splitter splitter = new Splitter();
                splitter.setStartPage(fromPage);
                splitter.setEndPage(toPage);
                splitter.setSplitAtPage(toPage);
                List<PDDocument> splitList = splitter.split(pdfFile);
                for (PDDocument document : splitList) {
                    document.save(savePdfLocation);
                    document.close();
                }
            }else {
                logger.info("Given PDF Contents Zero Number of Pages");
            }
            pdfFile.close();
            return true;
        } catch (FileNotFoundException e){
            logger.error("File Not Found Exception " + e.getMessage(),e);
            return false;
        } catch (IOException e) {
            logger.error("File Input Output Exception " + e.getMessage(),e);
            return false;
        }
    }
}
