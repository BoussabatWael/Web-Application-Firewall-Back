package com.gcs.firewall.service;

import java.util.List;
import java.util.Optional;

import com.gcs.firewall.model.EmailsSenders;

public interface EmailsSendersService {
	
	public EmailsSenders addEmailsSenders(EmailsSenders emailsSenders) ;
	public List<EmailsSenders> getAllEmailsSenders();
	public EmailsSenders updateEmailsSenders(EmailsSenders emailsSenders);
	public Optional<EmailsSenders> findEmailsSendersById(Long id) ;
	public void deleteEmailsSenders(Long id) ;
}
