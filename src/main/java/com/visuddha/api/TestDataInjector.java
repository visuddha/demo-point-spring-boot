package com.visuddha.api;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.visuddha.api.models.Purchase;
import com.visuddha.api.models.UserEntity;
import com.visuddha.api.repository.PurchaseRepository;
import com.visuddha.api.repository.UserRepository;

@Component
public class TestDataInjector implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PurchaseRepository purchaseRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) throws Exception {
		// adding user
		UserEntity demouser = UserEntity.builder().id(1).username("demouser")
				.password(passwordEncoder.encode("demouser")).build();
		userRepository.save(demouser);

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		// adding purchases
		Purchase demoPurchase01 = Purchase.builder().purchase_item("demoitem01").purchase_amount(190)
				.purchase_date((Date) dateFormat.parse("2023-01-22")).points(90).user(demouser).build();
		purchaseRepository.save(demoPurchase01);

		Purchase demoPurchase02 = Purchase.builder().purchase_item("demoitem02").purchase_amount(190)
				.purchase_date((Date) dateFormat.parse("2023-02-22")).points(90).user(demouser).build();
		purchaseRepository.save(demoPurchase02);

		Purchase demoPurchase03 = Purchase.builder().purchase_item("demoitem03").purchase_amount(190)
				.purchase_date((Date) dateFormat.parse("2023-03-22")).points(90).user(demouser).build();
		purchaseRepository.save(demoPurchase03);

		Purchase demoPurchase04 = Purchase.builder().purchase_item("demoitem04").purchase_amount(190)
				.purchase_date((Date) dateFormat.parse("2023-04-22")).points(90).user(demouser).build();
		purchaseRepository.save(demoPurchase04);

	}

}
