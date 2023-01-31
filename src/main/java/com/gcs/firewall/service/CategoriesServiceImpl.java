package com.gcs.firewall.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcs.firewall.interceptors.BasicAuthInterceptor;
import com.gcs.firewall.model.Account;
import com.gcs.firewall.model.Categories;
import com.gcs.firewall.repository.AccountRepo;
import com.gcs.firewall.repository.CategoriesRepo;

@Service
public class CategoriesServiceImpl implements CategoriesService{
	
	@Autowired
	private CategoriesRepo categoriesRepo;
	
	@Autowired
	private AccountRepo accountRepo;
	
	@Override
	public Categories addCategories(Categories categories) {
		Account account=accountRepo.findById(Long.parseLong(BasicAuthInterceptor.GLOBAL_ACCOUNT)).get();
		if(categories.getAccount()==null) {
			categories.setAccount(account);
		}
		return categoriesRepo.saveAndFlush(categories);
	}

	@Override
	public List<Categories> getAllCategories() {
		// TODO Auto-generated method stub
		return categoriesRepo.findAll();
	}

	@Override
	public Categories updateCategories(Categories categories) {
		// TODO Auto-generated method stub
		Account account=accountRepo.findById(Long.parseLong(BasicAuthInterceptor.GLOBAL_ACCOUNT)).get();
		if(categories.getAccount()==null) {
			categories.setAccount(account);
		}
		return categoriesRepo.save(categories);
	}

	@Override
	public Optional<Categories> findCategoriesById(Long id) {
		// TODO Auto-generated method stub
		return categoriesRepo.findById(id);
	}

	@Override
	public void deleteCategories(Long id) {
		// TODO Auto-generated method stub
		categoriesRepo.deleteById(id);
	}

	@Override
	public List<Categories> getCategoriesList(Long account_id) {
		// TODO Auto-generated method stub
		return categoriesRepo.getCategoriesList(account_id);
	}

}
