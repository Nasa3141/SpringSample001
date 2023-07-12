package com.example.demo;
/**************************
 * MyUserDetails＞ログイン機能
 * 
 * UserDetails・・・ユーザの情報を保持するクラス。権限やアカウントの状態を扱う
 * 
 ***************************/
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MyUserDetails implements UserDetails{

    private static final long serialVersionUID = 1L;
    private final User user;
     
    public MyUserDetails(User user) {
        this.user = user;
    }
    
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return user.getPassword();	//パスワード
	}

	@Override
	public String getUsername() {
		return user.getName();		//名前
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
