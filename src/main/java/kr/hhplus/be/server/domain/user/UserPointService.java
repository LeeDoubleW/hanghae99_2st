package kr.hhplus.be.server.domain.user;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserPointService {
	private final UserPointRepository userRepo;
	
	public UserPoint getUser(Long userId) {
		return userRepo.findById(userId);
	}
	
	public UserPoint charge(UserPointCommand.Charge command) {
		UserPoint user = userRepo.findById(command.getUserId());
		user.charge(command.getAmount());
		
		userRepo.update(user);
		
		return user;
	}
	
	public UserPoint use(UserPointCommand.Use command) {
		UserPoint user = userRepo.findById(command.getUserId());
		user.use(command.getAmount());
		
		userRepo.update(user);
		
		return user;
	}
}
