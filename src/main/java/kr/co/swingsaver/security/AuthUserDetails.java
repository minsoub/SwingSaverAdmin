package kr.co.swingsaver.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class AuthUserDetails implements UserDetails {
	private static final long serialVersionUID = 2973558538366811280L;
	private String id;
    private String password;
    private String name;
    private long   no;  // 원래 관리자 key
    private Collection<? extends GrantedAuthority> authority;
    private boolean enabled;
    
    public AuthUserDetails(String ID, String PASSWORD, String NAME, long no, Collection<? extends GrantedAuthority> AUTHORITY) {
        this.id = ID;
        this.password = PASSWORD;
        this.name = NAME;
        this.authority = AUTHORITY;
        this.enabled = true;
        this.no = no;
    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authority;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return enabled;
	}
    
    public void setName(String name) {
    	this.name = name;
    }
    
    public String getName() {
    	return this.name;
    }
    
    public void setNo(long no) {
    	this.no = no;
    }
    public long getNo() {
    	return this.no;
    }
}
