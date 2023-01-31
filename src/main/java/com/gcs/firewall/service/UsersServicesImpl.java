package com.gcs.firewall.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gcs.firewall.interceptors.BasicAuthInterceptor;
import com.gcs.firewall.model.Account;
import com.gcs.firewall.model.Users;
import com.gcs.firewall.repository.AccountRepo;
import com.gcs.firewall.repository.UsersRepo;

@Service
public class UsersServicesImpl implements UsersServices{
	
	@Autowired
	private UsersRepo usersRepo;
	
	@Autowired
	private AccountRepo accountRepo;
	
	public Users addUsers(String users, MultipartFile file) throws IOException{
		Users u =new ObjectMapper().readValue(users,Users.class);
		Account account=accountRepo.findById(Long.parseLong(BasicAuthInterceptor.GLOBAL_ACCOUNT)).get();
		if(account==null) {
			return null;
		}
		else {
			String key_value = generateKey();
			String nomImage=saveImage(file);
			u.setPhoto(nomImage);
			u.setAccount(account);
			u.setPassword(encrypt(u.getPassword()));
			u.setKey_value(key_value);
			u.setAuto_sync("false");
			u.setAccount(account);
			return usersRepo.save(u);
		}
	}
	public Users addUsers1(Users users){
		String username = users.getUsername();
		Users user = usersRepo.getUsersByUsername(username);
		if(user != null) {
			return null;
		}else {
			Account account=accountRepo.findById(Long.parseLong(BasicAuthInterceptor.GLOBAL_ACCOUNT)).get();
			
			if(users.getStatus() == 0) {
				users.setStatus(1);
			}
			if(users.getIp_address() == null) {
				users.setIp_address("");
			}
			if(users.getStart_date() == null) {
				users.setStart_date(new Date());
			}

			String key_value = generateKey();
			if(users.getAccount() == null) {
				users.setAccount(account);
			}
			users.setPhoto("UnknownUser_1660663421506.jpg");
			users.setPassword(encrypt(users.getPassword()));
			users.setKey_value(key_value);
			users.setAuto_sync("false");
			return usersRepo.save(users);
		}	
	}
	public List<Users> getAllUsers(){
		return usersRepo.findAll();
	}
	public Users updateUsers(String users, MultipartFile file) throws Exception{
		String fileName=saveImage(file);
		Users usr=new ObjectMapper().readValue(users,Users.class);
		usr.setPhoto(fileName);
		return usersRepo.save(usr);
	}
	public Users updateUsers1(Users users){
		
		return usersRepo.save(users);
	}
	public Optional<Users> findUsersById(Long id) {
		return usersRepo.findById(id);
	}
	public void deleteUsers(Long id) {
		usersRepo.deleteById(id);
	}
	@Override
	public List<Users> findUsersByStatus(Long account_id) {
		// TODO Auto-generated method stub
		return usersRepo.findUsersByStatus(account_id);
	}
	@Override
	public Users getOneUser(String username, String password) {
		// TODO Auto-generated method stub
		return usersRepo.getOneUser(username, password);
	}
	@Override
	public List<Users> findUsersByAccountId(Long account_id,Long user_id) {
		// TODO Auto-generated method stub
		return usersRepo.findUsersByAccountId(account_id,user_id);
	}
	@Override
	public String getUserNumber() {
		// TODO Auto-generated method stub
		return usersRepo.getUsersNumber();
	}
	private String saveImage(MultipartFile file) throws IOException {
	String filename=file.getOriginalFilename();
	String tab[]=filename.split("\\.");
	String
	filenameModif=tab[0]+"_"+System.currentTimeMillis()+"."+tab[1];
	File f=new
	File(System.getProperty("user.home")+"/OneDrive/Bureau/WAEL/FwSystem/src/assets/images/uploads/"+filenameModif);
	FileOutputStream fos=new FileOutputStream(f);
	fos.write(file.getBytes());
	fos.close();
	//FileUtils.writeByteArrayToFile(f, file.getBytes());
	return filenameModif;
	}
	
	
	@Override
	public Users getUsersList(String username) {
		// TODO Auto-generated method stub
		return usersRepo.getUsersList(username);
	}

	 
	public static String encrypt(String value) {
		String key = "AAAAAAAAAAAAAAAA";
		String initVector = "BBBBBBBBBBBBBBBB";
	    try {
	        IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
	        SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
	 
	        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
	        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
	 
	        byte[] encrypted = cipher.doFinal(value.getBytes());
	        return Base64.getEncoder().encodeToString(encrypted);
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }
	    return null;
	}
	
	public String generateKey() {
		String generatedKey = "";
		String possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	    try {
	    	for (int i = 0; i < 30; i++){
	    	      generatedKey += possible.charAt((int) Math.floor(Math.random() * possible.length()));
	    	    }
  	      return generatedKey;

	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }
	    return null;
	}
	
	@Override
	public Users getUserByUsername(String username) {
		// TODO Auto-generated method stub
		return usersRepo.getUsersByUsername(username);
	}
	
}
