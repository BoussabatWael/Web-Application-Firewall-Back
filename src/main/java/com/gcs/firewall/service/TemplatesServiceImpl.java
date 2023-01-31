package com.gcs.firewall.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gcs.firewall.model.Account;
import com.gcs.firewall.model.Templates;
import com.gcs.firewall.repository.AccountRepo;
import com.gcs.firewall.repository.TemplatesRepo;

@Service
public class TemplatesServiceImpl implements TemplatesService{
	
	@Autowired
	private TemplatesRepo templatesRepo;
	
	@Autowired
	private AccountRepo accountRepo;
	
	@Override
	public Templates addTemplates(Templates templates) {
		Account account=accountRepo.findById(templates.getAccount().getId()).get();
		if(account==null) {
			return null;
		}
		else {
			templates.setAccount(account);
			return templatesRepo.save(templates);
		}
	}

	@Override
	public List<Templates> getAllTemplates() {
		// TODO Auto-generated method stub
		return templatesRepo.findAll();
	}

	@Override
	public Templates updateTemplates(Templates templates) {
		// TODO Auto-generated method stub
		return templatesRepo.save(templates);
	}

	@Override
	public Optional<Templates> findTemplatesById(Long id) {
		// TODO Auto-generated method stub
		return templatesRepo.findById(id);
	}

	@Override
	public void deleteTemplates(Long id) {
		// TODO Auto-generated method stub
		templatesRepo.deleteById(id);
	}

}
