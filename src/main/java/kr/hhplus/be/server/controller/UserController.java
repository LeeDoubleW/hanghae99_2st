package kr.hhplus.be.server.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.hhplus.be.server.dto.balance.BalanceRequest;

@RestController
@RequestMapping("/user")
@Tag(name = "User", description = "잔액 충전 및 조회 API")
public class UserController {
	
	@GetMapping("/{userId}")
    @Operation(
            summary = "잔액 조회",
            description = "유저의 잔액을 조회합니다."
    )
    public ResponseEntity<Map<String, Object>> getBalance(
            @Parameter(description = "유저ID", example = "1")
            @PathVariable("userId") Long userId
    ) {
        int balance = 10000;

        return ResponseEntity.ok(Map.of(
                "userId", userId,
                "balance", balance,
                "message", "잔액 조회 성공"
        ));
    }
	
	@GetMapping("/{userId}/charge")
    @Operation(
            summary = "잔액 충전",
            description = "유저의 잔액을 충전합니다."
    )
    public ResponseEntity<Map<String, Object>> getBalance(
            @RequestBody BalanceRequest request
    ) {
		long userId = 1000001;
        int balance = 10000;

        return ResponseEntity.ok(Map.of(
                "userId", userId,
                "balance", balance,
                "message", "잔액 조회 성공"
        ));
    }
}
