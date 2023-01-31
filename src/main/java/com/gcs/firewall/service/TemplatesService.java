package com.gcs.firewall.service;

import java.util.List;
import java.util.Optional;
import com.gcs.firewall.model.Templates;

public interface TemplatesService {
	
	public Templates addTemplates(Templates templates) ;
	public List<Templates> getAllTemplates();
	public Templates updateTemplates(Templates templates);
	public Optional<Templates> findTemplatesById(Long id) ;
	public void deleteTemplates(Long id) ;
}
