package kr.hhplus.be.server.interfaces.point;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kr.hhplus.be.server.application.user.UserFacade;
import kr.hhplus.be.server.domain.user.entity.UserPoint;
import kr.hhplus.be.server.domain.user.entity.UserPointHistory;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Tag(name = "User", description = "잔액 충전, 사용, 조회 API")
public class UserController {
	
	private final UserFacade userFacade;
	
	@GetMapping("/{userId}")
    @Operation(
            summary = "잔액 조회",
            description = "유저의 잔액을 조회합니다."
    )
    public ResponseEntity<UserResponse.V1> getBalance(
            @Parameter(description = "유저ID", example = "1")
            @PathVariable("userId") Long userId
    ) {
        UserPoint user = userFacade.getUser(userId);
        return ResponseEntity.ok(UserResponse.V1.of(user.id(), user.point()));
    }
	
	@GetMapping("/{userId}/charge")
    @Operation(
            summary = "잔액 충전",
            description = "유저의 잔액을 충전합니다."
    )
    public ResponseEntity<UserResponse.V1> getBalance(
    		@Parameter(description = "유저ID", example = "1")
            @PathVariable("userId") Long userId,
            @RequestBody @Valid UserRequest.V1 request
    ) {
		UserPoint user = userFacade.charge(request.toCommand(userId));
        return ResponseEntity.ok(UserResponse.V1.of(user.id(), user.point()));
    }
	
	// 포인트 사용내역 조회
	@GetMapping("/history")
	@Operation(
			summary = "포인트 내역", 
			description = "유저의 포인트 내역 확인."
	)
	public ResponseEntity<UserResponse.V2> getHistory(
			@Parameter(description = "유저ID", example = "1")
            @PathVariable("userId") Long userId
    ) {
		List<UserPointHistory> histories = userFacade.getPointHistories(userId);
        return ResponseEntity.ok(UserResponse.V2.of(histories));
    }
}
