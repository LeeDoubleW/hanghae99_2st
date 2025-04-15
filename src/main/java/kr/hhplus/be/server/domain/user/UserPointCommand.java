package kr.hhplus.be.server.domain.user;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserPointCommand {
	@Getter
    public static class Charge {

        private final Long userId;
        private final Long amount;

        private Charge(Long userId, Long amount) {
            this.userId = userId;
            this.amount = amount;
        }

        public static Charge of(Long userId, Long amount) {
            return new Charge(userId, amount);
        }
    }

    @Getter
    public static class Use {

        private final Long userId;
        private final Long amount;

        private Use(Long userId, Long amount) {
            this.userId = userId;
            this.amount = amount;
        }

        public static Use of(Long userId, Long amount) {
            return new Use(userId, amount);
        }
    }
}
