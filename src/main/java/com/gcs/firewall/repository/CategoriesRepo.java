package com.gcs.firewall.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gcs.firewall.model.Categories;

public interface CategoriesRepo extends JpaRepository<Categories, Long>{
	
	@Query(value="SELECT * FROM firewall_categories WHERE firewall_categories.account_id = ?1",nativeQuery=true)
	public List<Categories> getCategoriesList(Long account_id);
}
