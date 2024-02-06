package com.example.letseat.plan;

import com.example.letseat.auth.AuthMember;
import com.example.letseat.auth.argumentresolver.Auth;
import com.example.letseat.plan.data.MetRequest;
import com.example.letseat.plan.data.PlanRequest;
import com.example.letseat.plan.data.QrRequest;
import com.example.letseat.plan.data.QrResponse;
import com.google.zxing.WriterException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Objects;

@Controller
@AllArgsConstructor
public class PlanController {
    private final PlanService planService;

    @PostMapping("/qr")
    @ResponseBody
    public ResponseEntity<QrResponse> lists(@Auth AuthMember authMember, @RequestBody @Valid QrRequest qrRequest) throws IOException, WriterException {
        if(authMember==null){
            throw  new RuntimeException("authMember가 없음");
        }
        Long user_id = authMember.getId();

        QrResponse qrcode = planService.generateQR(user_id, qrRequest);

        return ResponseEntity.ok(qrcode);
    }

    @PostMapping("/plan/create")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void plan(@Auth AuthMember authMember, @RequestBody @Valid PlanRequest planRequest) {
        if(authMember==null){
            throw  new RuntimeException("authMember가 없음");
        }
        Long receiverId = authMember.getId();
        Long senderId = planRequest.getSender_id();
        LocalDate expiration_date = planRequest.getExpired_date();
        planService.savePlan(senderId, receiverId, expiration_date);
    }

    @PutMapping("/plan/complete")
    @ResponseBody
    public void changeMet(@RequestBody @Valid MetRequest metRequest){
        Long planId = metRequest.getPlan_id();
        planService.changeToTrueAboutMet(planId);
    }
    @GetMapping("/test")
    @ResponseBody
    public ResponseEntity<Object> test() throws IOException{
        ClassPathResource resource = new ClassPathResource("json/test.json");

        String jsonContent = new String(Objects.requireNonNull(resource.getInputStream().readAllBytes()), StandardCharsets.UTF_8);

        return ResponseEntity.ok(jsonContent);
    }
}
