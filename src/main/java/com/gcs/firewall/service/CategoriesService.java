package com.gcs.firewall.service;

import java.util.List;
import java.util.Optional;

import com.gcs.firewall.model.Categories;

public interface CategoriesService {
	
	public Categories addCategories(Categories categories) ;
	public List<Categories> getAllCategories();
	public List<Categories> getCategoriesList(Long account_id);
	public Categories updateCategories(Categories categories);
	public Optional<Categories> findCategoriesById(Long id) ;
	public void deleteCategories(Long id) ;
}
