package com.java.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

	private int no;
	private String name;
	private String pwd;
	private String email;
	private String phone;
	private boolean delYn;
	private LocalDateTime regDate;
	private String roleName;
	
}
