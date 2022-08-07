package com.cipher0x.services;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import io.micronaut.http.server.types.files.StreamedFile;
import jakarta.inject.Singleton;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

@Singleton
public class BarcodeReader {

    private BufferedImage getBuffImgFromSFile(StreamedFile sFile) throws IOException {
        InputStream image = sFile.getInputStream();
        return ImageIO.read(image);
    }

    public String readQRCodeImage(StreamedFile sFile) throws IOException, NotFoundException {
        BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(
                new BufferedImageLuminanceSource(getBuffImgFromSFile(sFile))));
        Result result = new MultiFormatReader().decode(binaryBitmap);
        return result.getText();
    }
}
