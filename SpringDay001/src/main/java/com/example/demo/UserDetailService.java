package com.example.demo;

/**************************
 * UserDetailService＞ログイン機能
 * 
 * ログイン画面より入力されたユーザが存在すれば、UserDetailsを返す
 * 
 ***************************/
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
 
import com.example.demo.MyUserDetails;
import com.example.demo.User;
import com.example.demo.UserRepository;

import jakarta.transaction.Transactional;
 
//@Service
@Component
@Transactional
public class UserDetailService implements UserDetailsService {
	
    /**Repositoryインスタンスの生成**/
	@Autowired
	private UserRepository userRepository;
 
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {	
		//レポジトリでDB検索して得たユーザ情報を、Customerクラスのオブジェクトに格納
		User user = userRepository.findByName(username);
		
		//検索結果がNULLだった場合　→例外を返す
		if(user == null) {
			throw new UsernameNotFoundException(username + " is not found");
		}
		
		//検索結果が存在した場合　→　MyUserDetails（ユーザー情報を保持するクラス）のインスタンスに、データを格納して返す
		return new MyUserDetails(user);
	}
 
}