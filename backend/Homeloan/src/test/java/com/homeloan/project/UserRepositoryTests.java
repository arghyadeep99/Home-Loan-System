package com.homeloan.project;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.homeloan.project.model.UserLogin;
import com.homeloan.project.repository.UserLoginRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {

	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private UserLoginRepository repo;
	
	@Test
	public void testCreateUser() {
		UserLogin user = new UserLogin();
		user.setSeq_id("123");
		user.setUserid("abc");
		user.setPassword("1234");
		
		UserLogin savedUser = repo.save(user);
		
		UserLogin existUser = entityManager.find(UserLogin.class, savedUser.getUserid());
		
		assertThat(user.getUserid()).isEqualTo(existUser.getUserid());
		
	}
//	
//	@Test
//	public void testFindBySeqId() {
//		String seqid = "123";
//		UserLogin user = repo.findBySeqId(seqid);
//		
//		assertThat(user.getSeqId()).isEqualTo(seqid);
//	}
}
