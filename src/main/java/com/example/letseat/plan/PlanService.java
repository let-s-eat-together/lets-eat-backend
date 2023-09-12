package com.example.letseat.plan;

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
    private final UserRepository userRepository;

    private final PlanRepository planRepository;
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

    public String stingInfo(StingRequestDto stingRequestDto) {
        Long myId = stingRequestDto.getUser_id();
        Long planId = stingRequestDto.getPlan_id();
        Optional<User> findMember  = userRepository.findNameById(myId);
        User sendUser = findMember.get();

        Date now = new Date();
        // 출력 형식을 지정할 SimpleDateFormat 객체를 생성합니다.
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        // 현재 시간을 원하는 형식으로 문자열로 변환합니다.
        String formattedDate = sdf.format(now);

        Optional<Plan> usersWithSamePlan = planRepository.findById(planId);

        if (!usersWithSamePlan.isEmpty()){
            Long otherUserId = usersWithSamePlan.get().getId();
            Optional<User> receiveMember = userRepository.findNameById(otherUserId);
            User receiveUser = receiveMember.get();

            return "sender_name : "+sendUser.getName()+", receiver_name : "+receiveUser.getName()+" , now_date : "+formattedDate;
        }else{
            return "result"+ -1;
        }
    }
}
