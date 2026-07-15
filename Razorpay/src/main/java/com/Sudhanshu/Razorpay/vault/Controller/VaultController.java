package com.Sudhanshu.Razorpay.vault.Controller;

import com.Sudhanshu.Razorpay.vault.Dto.Request.TokenizeRequest;
import com.Sudhanshu.Razorpay.vault.Dto.Response.TokenizeResponse;
import com.Sudhanshu.Razorpay.vault.service.VaultService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/v1/vault")
@RequiredArgsConstructor
public class VaultController {

    private final VaultService vaultService;
    UUID merchantId = UUID.fromString("c1259ae9-7e2d-43a3-832c-8cdf406c173a");
    @PostMapping("/tokenize")

    public ResponseEntity<TokenizeResponse> tokenize(@RequestBody TokenizeRequest tokenizeRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(vaultService.tokenize(tokenizeRequest,merchantId));
    }

}
