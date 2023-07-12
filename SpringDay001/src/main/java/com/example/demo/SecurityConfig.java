package com.example.demo;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig{
    
	//パスワードのハッシュ化
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		// URL毎の認証設定の記述開始
		http.authorizeHttpRequests(authz -> authz
				.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
	            .requestMatchers("/css/*","/js/*","user/input","/user/login").permitAll()	//ログイン(認可)無しでアクセス可能なパスを設定
	            .anyRequest().authenticated()			    				// ログイン（認可）が必要なパスを設定。他のURLでは、ログイン時のみアクセス可能
	            );
	    
	    //フォーム認証の設定記述開始
	    http.formLogin(login -> login
	    		.loginProcessingUrl("/user/top")			// ログイン処理のパス
	        	.loginPage("/user/login")					// ログインページの指定
	        		
	        	.usernameParameter("name")					// ここで、ログイン画面の入力値を受け取っている　→ログイン画面のテンプレートのname属性と一致させる
	        	.passwordParameter("password")				// ここで、ログイン画面の入力値を受け取っている
	        	.defaultSuccessUrl("/user/top", true)		// 成功後の遷移先
	        	.failureUrl("/user/login?error")			// ログイン失敗時の遷移先
	        	.permitAll()								// ログイン画面は未ログインでもアクセス可能
	        	);
	        
	    //ログアウトの設定記述開始
	    http.logout(logout -> logout
	        	.logoutSuccessUrl("/user/login")			//ログアウト成功後の遷移先URL   			
	            );
	    return http.build();
	    
	}
 
    //ユーザ情報の取得（userServiceの実行）
    //@Override
    /**public void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userService).
        passwordEncoder(passwordEncoder());
    }**/
    //ここは西さんのコピペ（上記の書き方、AuthenticationManagerBuilderは非推奨だ以。手順だとうまくいく？
	//じさくのUserDetailServiceを使用してデータを取得するには・・・？
    @Bean
    public UserDetailsService userDetailsService (DataSource datasource) {
        return new JdbcUserDetailsManager(datasource);
    }
}
