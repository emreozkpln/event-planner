package dev.buddly.event_planner.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.itextpdf.text.DocumentException;
import dev.buddly.event_planner.model.Reservation;
import dev.buddly.event_planner.model.Ticket;
import dev.buddly.event_planner.repo.TicketRepository;
import dev.buddly.event_planner.utils.PDFGeneration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;

@Service
@Slf4j
public class TicketService {

    private final TicketRepository ticketRepository;
    private final AmazonS3 s3client;

    public TicketService(TicketRepository ticketRepository, AmazonS3 s3client) {
        this.ticketRepository = ticketRepository;
        this.s3client = s3client;
    }

    private String bucketName = "";

    @Transactional
    public String uploadFileAndSavePdf(Reservation reservation,String ticketFileName) throws IOException, DocumentException, URISyntaxException {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PDFGeneration.createTicket(reservation, byteArrayOutputStream);

        try {
            byte[] pdfBytes = byteArrayOutputStream.toByteArray();

            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType("application/pdf");
            metadata.setContentLength(pdfBytes.length);

            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(pdfBytes);
            s3client.putObject(new PutObjectRequest(bucketName, ticketFileName, byteArrayInputStream, metadata));

            String fileUrl = s3client.getUrl(bucketName, ticketFileName).toString();
            return saveImage(reservation,fileUrl).getPdfUrl();
        }catch (AmazonServiceException e){
            throw new IOException(e.getMessage());
        }
    }

    private Ticket saveImage(Reservation reservation,String fileUrl){
        Ticket ticket = new Ticket();
        ticket.setReservation(reservation);
        ticket.setPdfUrl(fileUrl);
        ticket.setCreatedDate(LocalDateTime.now());
        return ticketRepository.save(ticket);
    }
}
