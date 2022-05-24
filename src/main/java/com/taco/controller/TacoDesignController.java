package com.taco.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.taco.model.Taco;
import com.taco.repository.TacoRepository;
@RestController
@RequestMapping(path = "/design", produces = "application/json")
@CrossOrigin(origins = "*")
public class DesignTacoController {
	private TacoRepository tacoRepo;
	   
	@Autowired
	private EntityLinks entityLinks;
	
	public DesignTacoController(TacoRepository tacoRepo) {
		this.tacoRepo = tacoRepo;
	}
	@GetMapping("/recent")
	public Iterable<Taco> recentTacos() {
		return tacoRepo.findAll();
	}
	
	@GetMapping("/{id}")
	public Taco tacoById(@PathVariable("id") Long id) {
		Optional<Taco> optTaco = tacoRepo.findById(id);
		if (optTaco.isPresent()) {
			return optTaco.get();
		}
		return null;
	}
	
	@PostMapping(consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Taco postTaco(@RequestBody Taco taco) {
		return tacoRepo.save(taco);
	}
}
