package com.example.pc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.pc.entities.Pc;
import com.example.pc.repos.PcRepository;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.util.Optional;

@SpringBootTest
class PcApplicationTests {

	@Autowired
	private PcRepository pcRepository;

	@Test
	public void testCreatePc() {
		Pc pc = new Pc("Dell Inspiron", 1200.50);
		Pc savedPc = pcRepository.save(pc);
		assertNotNull(savedPc.getIdPc());
		System.out.println("PC Created: " + savedPc);
	}

	@Test
	public void testFindPc() {
		Optional<Pc> p = pcRepository.findById(1L);
		if (p.isPresent()) {
			System.out.println("Found PC: " + p.get());
		} else {
			System.out.println("PC not found");
		}
	}

	@Test
	public void testUpdatePc() {
		Optional<Pc> p = pcRepository.findById(1L);
		if (p.isPresent()) {
			Pc pcToUpdate = p.get();
			pcToUpdate.setPrixPc(1000.0);
			pcRepository.save(pcToUpdate);
			System.out.println("PC Updated: " + pcToUpdate);
		}
	}

	@Test
	public void testDeletePc() {
		pcRepository.deleteById(1L);
		System.out.println("PC Deleted");
	}
}