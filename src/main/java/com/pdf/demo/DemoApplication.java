package com.pdf.demo;

import com.pdf.demo.PdfService.PdfServiceImpl.ExtractPdfImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class PdfExtractionApplication extends SpringBootServletInitializer implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(PdfExtractionApplication.class);
	@Autowired
	public ExtractPdfImpl extractPdfImpl;

	public static void main(String[] args) {
		SpringApplication.run(PdfExtractionApplication.class, args);
	}

	@Override
	public void run(String... strings){
		try {
			logger.info("Extraction Of PDF application is running.................");
			extractPdfImpl.start();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
}
