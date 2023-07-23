package com.example.demo.service;

/**************************
 * UserDetailService＞ログイン機能
 * 
 * 一致するユーザが存在すれば、UserDetailsにデータを格納して返す
 * 
 ***************************/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Account;
import com.example.demo.entity.MyUserDetails;
import com.example.demo.repository.UserDetailRepository;

//import com.example.demo.Account;
//import com.example.demo.UserDetailRepository;
//import com.example.demo.MyUserDetails;

 
@Service
public class UserDetailService implements UserDetailsService {
	
	/**Repositoryインスタンスの生成**/
	@Autowired
	private UserDetailRepository userdetailRepository;
	
	/**
	 * DB検索をして、一致するユーザ情報をUserDetailsにつめて返却する
	 */
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {	
		//レポジトリでDB検索して得たユーザ情報を、Userクラスのオブジェクトに格納
		Account account = userdetailRepository.findByEmail(email);
		
		//検索結果がNULLだった場合　→例外を返す
		if(account == null) {
			throw new UsernameNotFoundException(email + " is not found");
		}
		//検索結果が存在した場合　→　MyUserDetails（ユーザー情報を保持するクラス）のインスタンスに、データを格納して返す
		return new MyUserDetails(account);
	}
 
}