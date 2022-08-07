package com.cipher0x.controllers;

import com.cipher0x.services.BarcodeGenerator;
import com.cipher0x.services.BarcodeReader;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.http.multipart.CompletedFileUpload;
import io.micronaut.http.server.types.files.StreamedFile;
import jakarta.inject.Inject;

@Controller("/barcode")
public class BarcodeController {
    @Inject
    BarcodeGenerator barcodeGenerator;
    @Inject
    BarcodeReader barcodeReader;


    @Get("/upca/{barcode}")
    @Produces(MediaType.IMAGE_PNG)
    public StreamedFile generateUPCABarcodeImage(String barcode)  throws Exception {
        return barcodeGenerator.generateUPCABarcodeImage(barcode);
    }

    @Get("/ean13/{barcode}")
    @Produces(MediaType.IMAGE_PNG)
    public StreamedFile generateEAN13BarcodeImage(String barcode)  throws Exception {
        return barcodeGenerator.generateEAN13BarcodeImage(barcode);
    }

    @Post("/code128")
    @Produces(MediaType.IMAGE_PNG)
    public StreamedFile generateCode128BarcodeImage(String dataToEncode)  throws Exception {
        return barcodeGenerator.generateCode128BarcodeImage(dataToEncode);
    }

    @Post("/pdf417")
    @Produces(MediaType.IMAGE_PNG)
    public StreamedFile generatePDF417BarcodeImage(String dataToEncode)  throws Exception {
        return barcodeGenerator.generatePDF417BarcodeImage(dataToEncode);
    }

    @Post("/qr")
    @Produces(MediaType.IMAGE_PNG)
    public StreamedFile generateQRCodeImage(String dataToEncode)  throws Exception {
        return barcodeGenerator.generateQRCodeImage(dataToEncode);
    }

    @Post(value = "read/qr")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_PLAIN)
    public String readQRCodeImage(CompletedFileUpload upload)  throws Exception {
        StreamedFile image = new StreamedFile(upload.getInputStream(), MediaType.IMAGE_PNG_TYPE);
        return barcodeReader.readQRCodeImage(image);
    }

}
