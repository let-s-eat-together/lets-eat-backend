package com.example.letseat.plan;


import com.example.letseat.plan.data.QrRequest;
import com.example.letseat.plan.data.QrResponse;
import com.example.letseat.user.User;
import com.example.letseat.user.UserRepository;
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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Base64;
import java.util.Date;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class PlanService {
    private final PlanRepository planRepository;
    private final UserRepository userRepository;

    public QrResponse generateQR(QrRequest qrRequest) throws WriterException, IOException {
        Long sender_id = qrRequest.getSender_id();
        LocalDate expiration_date = qrRequest.getExpiration_date();

        var qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(
                "SenderId: " + sender_id +"\n" +
                        "ExpirationDate:" + expiration_date + "\n", BarcodeFormat.QR_CODE, 300,300
        );
        BufferedImage qrCodeImage = MatrixToImageWriter.toBufferedImage(bitMatrix);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(qrCodeImage, "png", baos);

        byte[] imageBytes = baos.toByteArray();

        QrResponse qrcode = new QrResponse();

        qrcode.setQrcode(Base64.getEncoder().encodeToString(imageBytes));

        return qrcode;
    }

    public void savePlan(Long senderId, Long receiverId, LocalDate expired_date) {
        Plan newPlan = new Plan();
        newPlan.setCreation_date(LocalDate.now());
        newPlan.setExpiration_date(expired_date);
        User sender = userRepository.findById(senderId).orElseThrow();
        User receiver = userRepository.findById(receiverId).orElseThrow();
        newPlan.addUser(sender);
        newPlan.addUser(receiver);
        planRepository.save(newPlan);
    }

}
