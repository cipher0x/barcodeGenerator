package com.cipher0x.services;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;
import com.google.zxing.oned.EAN13Writer;
import com.google.zxing.oned.UPCAWriter;
import com.google.zxing.pdf417.PDF417Writer;
import com.google.zxing.qrcode.QRCodeWriter;
import io.micronaut.http.MediaType;
import io.micronaut.http.server.types.files.StreamedFile;
import jakarta.inject.Singleton;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Singleton
public class BarcodeGenerator {
    private StreamedFile getSFileFromBuffImg(BufferedImage buffImg) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(buffImg, "png", os);
        InputStream image = new ByteArrayInputStream(os.toByteArray());
        return new StreamedFile(image, MediaType.IMAGE_PNG_TYPE);
    }
    public StreamedFile generateUPCABarcodeImage(String barcodeText) throws Exception {
        UPCAWriter barcodeWriter = new UPCAWriter();
        BitMatrix bitMatrix = barcodeWriter.encode(barcodeText, BarcodeFormat.UPC_A, 300, 150);
        BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix);
        return getSFileFromBuffImg(image);
    }

    public StreamedFile generateEAN13BarcodeImage(String barcodeText) throws Exception {
        EAN13Writer barcodeWriter = new EAN13Writer();
        BitMatrix bitMatrix = barcodeWriter.encode(barcodeText, BarcodeFormat.EAN_13, 300, 150);

        BufferedImage image =  MatrixToImageWriter.toBufferedImage(bitMatrix);
        return getSFileFromBuffImg(image);
    }

    public StreamedFile generateCode128BarcodeImage(String barcodeText) throws Exception {
        Code128Writer barcodeWriter = new Code128Writer();
        BitMatrix bitMatrix = barcodeWriter.encode(barcodeText, BarcodeFormat.CODE_128, 300, 150);

        BufferedImage image =  MatrixToImageWriter.toBufferedImage(bitMatrix);
        return getSFileFromBuffImg(image);
    }

    public StreamedFile generatePDF417BarcodeImage(String barcodeText) throws Exception {
        PDF417Writer barcodeWriter = new PDF417Writer();
        BitMatrix bitMatrix = barcodeWriter.encode(barcodeText, BarcodeFormat.PDF_417, 700, 700);

        BufferedImage image =  MatrixToImageWriter.toBufferedImage(bitMatrix);
        return getSFileFromBuffImg(image);
    }

    public StreamedFile generateQRCodeImage(String barcodeText) throws Exception {
        QRCodeWriter barcodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = barcodeWriter.encode(barcodeText, BarcodeFormat.QR_CODE, 200, 200);

        BufferedImage image =  MatrixToImageWriter.toBufferedImage(bitMatrix);
        return getSFileFromBuffImg(image);
    }
}
