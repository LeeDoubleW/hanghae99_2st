package kr.hhplus.be.server.domain.point;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import kr.hhplus.be.server.domain.user.dto.UserPointCommand;
import kr.hhplus.be.server.domain.user.entity.UserPoint;
import kr.hhplus.be.server.domain.user.repository.UserPointRepository;
import kr.hhplus.be.server.domain.user.service.UserPointService;

@ExtendWith(MockitoExtension.class)
public class UserPointServiceTest {
	@InjectMocks
    private UserPointService userPointService;

    @Mock
    private UserPointRepository userPointRepository;

    @DisplayName("잔액을 조회한다.")
    @Test
    void getBalance() {
        // given
    	UserPoint user = new UserPoint(1L, "홍길동", 1000L);
    	Long amount = 1000L;

        when(userPointRepository.findById(anyLong()))
            .thenReturn(user);

        // when
        UserPoint point = userPointService.getUser(1L);

        // then
        assertThat(point.point()).isEqualTo(amount);
        assertThat(point.id()).isEqualTo(user.id());
    }

    @DisplayName("충전 시, 최대 금액을 넘을 수 없다.")
    @Test
    void chargeBalanceWithExceedMaxAmount() {
        // given
    	UserPoint user = UserPoint.builder()
            .id(1L)
            .name("홍길동")
            .point(1_000_000_000L)
            .build();

        when(userPointRepository.findById(anyLong()))
            .thenReturn(user);

        Long chargeAmount = 100L;
        UserPointCommand.Charge command = UserPointCommand.Charge.of(user.userId(), chargeAmount);

        // when & then
        assertThatThrownBy(() -> userPointService.charge(command))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("최대 금액을 초과할 수 없습니다.");
    }

    @DisplayName("사용 시, 잔액이 충분해야한다.")
    @Test
    void useBalanceShouldSufficient() {
        // given
    	UserPoint user = UserPoint.builder()
            .id(1L)
            .name("홍길동")
            .point(0L)
            .build();

        when(userPointRepository.findById(anyLong()))
            .thenReturn(user);

        Long useAmount = 10_001L;
        UserPointCommand.Use command = UserPointCommand.Use.of(user.userId(), useAmount);

        // when & then
        assertThatThrownBy(() -> userPointService.use(command))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("잔액이 부족합니다.");
    }

}
