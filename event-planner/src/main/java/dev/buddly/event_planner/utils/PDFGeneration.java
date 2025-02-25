package dev.buddly.event_planner.utils;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import dev.buddly.event_planner.model.Reservation;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

public class PDFGeneration {

    public static void createTicket(Reservation reservation, ByteArrayOutputStream byteArrayOutputStream) throws DocumentException, MalformedURLException, URISyntaxException, IOException {
        Document doc = new Document();

        PdfWriter.getInstance(doc, byteArrayOutputStream);

        doc.open();

        Paragraph par = new Paragraph("Event Ticket\n\n");
        par.add("Title: " + reservation.getEvent().getTitle() + "\n");
        par.add("Description: " + reservation.getEvent().getDescription() + "\n");
        par.add("Full Name: " + reservation.getUser().fullName() + "\n");
        par.add("Start Date: " + reservation.getEvent().getStartDate() + "\n");
        par.add("End Date: " + reservation.getEvent().getEndDate() + "\n");
        par.add("Reservation Date: " + reservation.getCreatedDate() + "\n");

        doc.add(par);

        doc.close();
    }
}
