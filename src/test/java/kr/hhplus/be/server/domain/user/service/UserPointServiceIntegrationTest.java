package kr.hhplus.be.server.domain.user.service;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.hhplus.be.server.domain.user.dto.UserPointCommand;
import kr.hhplus.be.server.domain.user.entity.UserPoint;
import kr.hhplus.be.server.domain.user.entity.UserPointHistory;

@SpringBootTest
public class UserPointServiceIntegrationTest {	
	
	@Autowired
	private UserPointService userPointService;
	
	private UserPoint userInfo;
	
	@BeforeEach
	void setUp() {
		userInfo = userPointService.save(UserPoint.of(1L, "user", 0L));
	}
	
	// 조회
	@Test
	void 유저_조회() {
		
		assertThat(userPointService.getUser(1L)).isEqualTo(userInfo);
	}
	
	// 충전
	@Test
	void 유저_충전() {
		Long chargePoint = 100L;
		
		UserPointCommand.Charge charge = UserPointCommand.Charge.of(userInfo.userId(), chargePoint);
		
		userPointService.charge(charge);
		
		UserPoint chargeInfo = userPointService.getUser(userInfo.userId());
		assertThat(chargeInfo.point()).isEqualTo(chargePoint);
	}
	
	// 충전시 금액이 음수일경우 
	@Test
	void 유저_충전_음수() {
		Long chargePoint = -100L;
		
		UserPointCommand.Charge charge = UserPointCommand.Charge.of(userInfo.userId(), chargePoint);
		
		;
		
		assertThatThrownBy(()->userPointService.charge(charge))
		.isInstanceOf(IllegalArgumentException.class)
        .hasMessage("충전 금액은 0보다 커야 합니다.");
	}
	
	// 충전시 최대 금액 이상일 경우
	@Test
	void 유저_충전_최대() {
		Long chargePoint = 1_000_000_001L;
		
		UserPointCommand.Charge charge = UserPointCommand.Charge.of(userInfo.userId(), chargePoint);
		
		;
		
		assertThatThrownBy(()->userPointService.charge(charge))
		.isInstanceOf(IllegalArgumentException.class)
        .hasMessage("최대 금액을 초과하여 충전할 수 없습니다.");
	}
	
	// 내역 조회
	@Test
	void 유저_내역조회() {
		Long chargePoint = 100L;
		Long usePoint = 100L;
		
		// 충천 하나와 사용 하나
		UserPointCommand.Charge charge = UserPointCommand.Charge.of(userInfo.userId(), chargePoint);
		userPointService.charge(charge);
		
		UserPointCommand.Use use = UserPointCommand.Use.of(userInfo.userId(), usePoint);
		userPointService.use(use);
		
		List<UserPointHistory> histories = userPointService.getHistoryList(userInfo.userId());
		assertThat(histories).hasSize(2);
	}
}
