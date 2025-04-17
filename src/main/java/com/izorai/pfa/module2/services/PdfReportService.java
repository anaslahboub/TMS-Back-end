package com.izorai.pfa.module2.services;

import com.izorai.pfa.module2.DTO.report.CamionInfo;
import com.izorai.pfa.module2.DTO.report.DocumentAlert;
import com.izorai.pfa.module2.DTO.report.DriverInfo;
import com.izorai.pfa.module2.DTO.report.ReportDto;
import com.izorai.pfa.module2.DTO.report.VoyageInfo;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
@Slf4j
public class PdfReportService {

    private static final String OUTPUT_DIR = "reports/";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /**
     * Generates a PDF report from the provided ReportDto and saves it to the specified file name.
     *
     * @param report   The ReportDto containing enterprise activity data.
     * @param fileName The name of the output PDF file (e.g., "report_2025-04-16.pdf").
     * @return The path to the generated PDF file.
     * @throws RuntimeException if PDF generation fails.
     */
        public String generatePdfReport(ReportDto report, String fileName) {
        try {
            // Ensure output directory exists
            File outputDir = new File(OUTPUT_DIR);
            if (!outputDir.exists()) {
                outputDir.mkdirs();
            }

            String filePath = OUTPUT_DIR + fileName;
            PdfWriter writer = new PdfWriter(filePath);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            // Title
            document.add(new Paragraph("Enterprise 30-Day Activity Report")
                    .setFontSize(18)
                    .setBold()
                    .setTextAlignment(TextAlignment.CENTER));
            document.add(new Paragraph("Generated on: " + LocalDate.now().format(DATE_FORMATTER))
                    .setFontSize(12)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setMarginBottom(20));

            // Voyage Information Section
            document.add(new Paragraph("Voyage Information")
                    .setFontSize(14)
                    .setBold()
                    .setMarginTop(10));
            Table voyageTable = new Table(UnitValue.createPercentArray(new float[]{10, 30, 20, 20, 20}))
                    .useAllAvailableWidth();
            addTableHeader(voyageTable, "ID", "Route", "Departure Date", "Truck", "Driver");
            for (VoyageInfo voyage : report.getVoyagesPlanifies()) {
                voyageTable.addCell(new Cell().add(new Paragraph(voyage.getId() != null ? voyage.getId().toString() : "")));
                voyageTable.addCell(new Cell().add(new Paragraph(voyage.getRoute() != null ? voyage.getRoute() : "")));
                voyageTable.addCell(new Cell().add(new Paragraph(voyage.getDepartureDate() != null ? voyage.getDepartureDate().format(DATE_FORMATTER) : "")));
                voyageTable.addCell(new Cell().add(new Paragraph(voyage.getCamion() != null ? voyage.getCamion() : "")));
                voyageTable.addCell(new Cell().add(new Paragraph(voyage.getChaufeurFullName() != null ? voyage.getChaufeurFullName() : "")));
            }
            document.add(voyageTable);
            document.add(new Paragraph("Total Upcoming Voyages: " + report.getTotalUpcomingVoyages())
                    .setMarginBottom(10));

            // Vehicle Information Section
            document.add(new Paragraph("Vehicle Information")
                    .setFontSize(14)
                    .setBold()
                    .setMarginTop(20));
            Table camionTable = new Table(UnitValue.createPercentArray(new float[]{33, 33, 33}))
                    .useAllAvailableWidth();
            addTableHeader(camionTable, "Registration", "Type", "Status");
            for (CamionInfo camion : report.getCamionsActive()) {
                camionTable.addCell(new Cell().add(new Paragraph(camion.getImmatriculation() != null ? camion.getImmatriculation() : "")));
                camionTable.addCell(new Cell().add(new Paragraph(camion.getType() != null ? camion.getType() : "")));
                camionTable.addCell(new Cell().add(new Paragraph(camion.getStatus() != null ? camion.getStatus() : "")));
            }
            document.add(camionTable);

            // Driver Information Section
            document.add(new Paragraph("Driver Information")
                    .setFontSize(14)
                    .setBold()
                    .setMarginTop(20));
            Table driverTable = new Table(UnitValue.createPercentArray(new float[]{15, 30, 25, 30}))
                    .useAllAvailableWidth();
            addTableHeader(driverTable, "ID", "Name", "License Number", "License Expiry");
            for (DriverInfo driver : report.getActiveDrivers()) {
                driverTable.addCell(new Cell().add(new Paragraph(driver.getId() != null ? driver.getId().toString() : "")));
                driverTable.addCell(new Cell().add(new Paragraph(driver.getName() != null ? driver.getName() : "")));
                driverTable.addCell(new Cell().add(new Paragraph(driver.getLicenseNumber() != null ? driver.getLicenseNumber() : "")));
                driverTable.addCell(new Cell().add(new Paragraph(driver.getLicenseExpiry() != null ? driver.getLicenseExpiry().format(DATE_FORMATTER) : "")));
            }
            document.add(driverTable);

            // Document Expirations Section
            document.add(new Paragraph("Document Expirations")
                    .setFontSize(14)
                    .setBold()
                    .setMarginTop(20));
            Table documentTable = new Table(UnitValue.createPercentArray(new float[]{40, 30, 30}))
                    .useAllAvailableWidth();
            addTableHeader(documentTable, "Document Type", "Expiry Date", "Days Until Expiry");
            for (DocumentAlert alert : report.getDocumentsExpirants()) {
                documentTable.addCell(new Cell().add(new Paragraph(alert.getDocumentType() != null ? alert.getDocumentType() : "")));
                documentTable.addCell(new Cell().add(new Paragraph(alert.getExpiryDate() != null ? alert.getExpiryDate().format(DATE_FORMATTER) : "")));
                documentTable.addCell(new Cell().add(new Paragraph(String.valueOf(alert.getDaysUntilExpiry()))));
            }
            document.add(documentTable);

            // Close the document
            document.close();
            log.info("PDF report generated successfully at {}", filePath);
            return filePath;
        } catch (Exception e) {
            log.error("Failed to generate PDF report", e);
            throw new RuntimeException("Error generating PDF report: " + e.getMessage(), e);
        }
    }

    /**
     * Adds a header row to the table with the specified column names.
     *
     * @param table The table to add the header to.
     * @param headers The column names for the header.
     */
    private void addTableHeader(Table table, String... headers) {
        for (String header : headers) {
            table.addHeaderCell(new Cell()
                    .add(new Paragraph(header))
                    .setBackgroundColor(ColorConstants.LIGHT_GRAY)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setBold());
        }
    }
}