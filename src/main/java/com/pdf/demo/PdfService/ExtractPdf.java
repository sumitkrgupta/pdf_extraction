package com.pdf.demo.PdfService;

import org.springframework.stereotype.Service;

@Service
public interface ExtractPdf {

    boolean splitPdf(String getPdfLocation,String savePdfLocation,int fromPage, int toPage) throws Exception;
}
