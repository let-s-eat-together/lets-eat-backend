package com.example.letseat.plan;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Base64;

@Service
@Transactional
@AllArgsConstructor
public class PlanService {

    public String generateQR(PlanRequestDto planRequest) throws WriterException, IOException {
        String url = planRequest.getUrl();
        Long sender_id = planRequest.getSender_id();
        LocalDate expiration_date = planRequest.getExpiration_date();

        var qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(
                "URL: " + url +"\n" +
                "SenderId: " + sender_id +"\n" +
                        "ExpirationDate:" + expiration_date + "\n", BarcodeFormat.QR_CODE, 300,300
        );
        BufferedImage qrCodeImage = MatrixToImageWriter.toBufferedImage(bitMatrix);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(qrCodeImage, "png", baos);

        byte[] imageBytes = baos.toByteArray();

        return Base64.getEncoder().encodeToString(imageBytes);
    }


}
