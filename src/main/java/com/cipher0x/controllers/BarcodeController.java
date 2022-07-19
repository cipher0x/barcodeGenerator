package com.cipher0x.controllers;

import com.cipher0x.services.BarcodeGenerator;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.types.files.StreamedFile;
import jakarta.inject.Inject;

import javax.print.attribute.standard.Media;
import java.awt.image.BufferedImage;

@Controller("/barcode")
public class BarcodeController {
    @Inject
    BarcodeGenerator barcodeGenerator;


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

}
