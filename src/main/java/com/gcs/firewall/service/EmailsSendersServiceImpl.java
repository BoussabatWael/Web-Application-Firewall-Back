package com.gcs.firewall.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gcs.firewall.model.Account;
import com.gcs.firewall.model.EmailsSenders;
import com.gcs.firewall.model.Providers;
import com.gcs.firewall.repository.AccountRepo;
import com.gcs.firewall.repository.EmailsSendersRepo;
import com.gcs.firewall.repository.ProvidersRepo;

@Service
public class EmailsSendersServiceImpl implements EmailsSendersService{
	
	@Autowired
	private ProvidersRepo providersRepo;
	
	@Autowired
	private AccountRepo accountRepo;
	
	@Autowired
	private EmailsSendersRepo emailsSendersRepo;
	
	@Override
	public EmailsSenders addEmailsSenders(EmailsSenders emailsSenders) {
		Account account=accountRepo.findById(emailsSenders.getAccount().getId()).get();
		Providers providers=providersRepo.findById(emailsSenders.getProviders().getId()).get();
		if(account==null) {
			return null;
		}
		if(providers==null) {
			return null;
		}
		else {
			emailsSenders.setAccount(account);
			emailsSenders.setProviders(providers);
			return emailsSendersRepo.save(emailsSenders);
		}
	}

	@Override
	public List<EmailsSenders> getAllEmailsSenders() {
		// TODO Auto-generated method stub
		return emailsSendersRepo.findAll();
	}

	@Override
	public EmailsSenders updateEmailsSenders(EmailsSenders emailsSenders) {
		// TODO Auto-generated method stub
		return emailsSendersRepo.save(emailsSenders);
	}

	@Override
	public Optional<EmailsSenders> findEmailsSendersById(Long id) {
		// TODO Auto-generated method stub
		return emailsSendersRepo.findById(id);
	}

	@Override
	public void deleteEmailsSenders(Long id) {
		// TODO Auto-generated method stub
		emailsSendersRepo.deleteById(id);
	}

}
