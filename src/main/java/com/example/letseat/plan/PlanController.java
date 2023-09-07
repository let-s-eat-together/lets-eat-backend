package com.example.letseat.plan;

import com.example.letseat.user.data.ListRequest;
import com.google.zxing.WriterException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
@AllArgsConstructor
public class PlanController {
    private final PlanService planService;

    @PostMapping("/qr")
    @ResponseBody
    public ResponseEntity<String> lists(@RequestBody @Valid PlanRequestDto planRequest) throws IOException, WriterException {

        String qrcode = planService.generateQR(planRequest);

        return ResponseEntity.ok(qrcode);
    }
}
