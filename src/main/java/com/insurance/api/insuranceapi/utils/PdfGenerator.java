package com.insurance.api.insuranceapi.utils;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;

public class PdfGenerator {

    public static void generatePolicyPdf(String insuranceName) throws Exception {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("policy.pdf"));
        document.open();
        document.add(new Paragraph("Insurance Policy Document"));
        document.add(new Paragraph("Insurance Name: " + insuranceName));
        document.close();
    }
}
