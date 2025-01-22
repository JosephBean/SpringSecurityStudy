package com.java.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.java.dto.MyUserDTO;
import com.java.dto.RoleDTO;
import com.java.dto.UserDTO;
import com.java.dto.UserRole;
import com.java.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {
	
	private final UserMapper userMapper;
	
	private final PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		UserDTO user = userMapper.findByUser(email);
		if(user != null) {
			RoleDTO role = userMapper.findByRole(user.getNo());
			if(role != null) {
				return new MyUserDTO(user, role);
			}
		}
		
		return null;
//		UserDTO user = userMapper.findByUser(email);
//		RoleDTO role = userMapper.findByRole(user.getNo());
//		return new MyUserDTO(user, role);
	}
	
	public String save(UserDTO user) {
		user.setPwd(passwordEncoder.encode(user.getPwd()));
		int status = userMapper.save(user);
		if(status == 1) {
			status = userMapper.saveUserRole(UserRole.builder().userNo(user.getNo()).roleNo(3).build());
			if(status == 1) {
				return "redirect:/signIn";
			}
		}
		return "redirect:/signUp";
	}
	
	
}
