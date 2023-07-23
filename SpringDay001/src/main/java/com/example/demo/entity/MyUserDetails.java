package com.example.demo.entity;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
/**************************
 * ■ログイン機能
 * 
 * ユーザの情報を保持するクラス。権限やアカウントの状態を扱う
 * 
 ***************************/

public class MyUserDetails implements UserDetails{

    private static final long serialVersionUID = 1L;
    
    //UserテーブルのエンティティであるAccountクラスをベースに作成
    private final Account account;
    public MyUserDetails(Account account) {
        this.account = account;
    }
    
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;//権限
	}
	@Override
	public String getPassword() {
		return account.getPassword();	//パスワード
	}

	@Override
	public String getUsername() {
		return account.getName();		//名前
	}

	//使わないものはreturn trueを設定。
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return true;
	}
}
