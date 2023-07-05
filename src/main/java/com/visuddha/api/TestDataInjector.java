package com.visuddha.api;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.visuddha.api.models.Purchase;
import com.visuddha.api.models.UserEntity;
import com.visuddha.api.repository.PurchaseRepository;
import com.visuddha.api.repository.UserRepository;

import lombok.AllArgsConstructor;

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
		Purchase demoPurchase = Purchase.builder().id(1)
				.purchase_item("demoitem01")
				.purchase_amount(190)
				.purchase_date((Date) dateFormat.parse("2023-01-22"))
				.points(90)
				.user(demouser)
				.build();
		purchaseRepository.save(demoPurchase);

		demoPurchase.setPurchase_date((Date) dateFormat.parse("2023-02-22"));
		purchaseRepository.save(demoPurchase);

		demoPurchase.setPurchase_date((Date) dateFormat.parse("2023-03-22"));
		purchaseRepository.save(demoPurchase);

		demoPurchase.setPurchase_date((Date) dateFormat.parse("2023-04-22"));
		purchaseRepository.save(demoPurchase);

		demoPurchase.setPurchase_date((Date) dateFormat.parse("2023-05-22"));
		purchaseRepository.save(demoPurchase);
		
		demoPurchase.setPurchase_date((Date) dateFormat.parse("2023-06-22"));
		purchaseRepository.save(demoPurchase);

	}

}