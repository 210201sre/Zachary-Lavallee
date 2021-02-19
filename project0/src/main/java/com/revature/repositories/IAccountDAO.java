  
package com.revature.repositories;

import java.util.List;

import com.revature.models.Account;

public interface IAccountDAO {

	public List<Account> findAll();
	
}