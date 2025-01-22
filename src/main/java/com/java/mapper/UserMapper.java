package com.java.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.mapping.StatementType;

import com.java.dto.RoleDTO;
import com.java.dto.UserDTO;
import com.java.dto.UserRole;

@Mapper
public interface UserMapper {
	
	@Select("  SELECT a.*, b.`name` AS roleName "
			+ "  FROM `user` AS a "
			+ "  LEFT OUTER JOIN  "
			+ "  ( "
			+ "  	SELECT ur.`userNo`, r.`name` "
			+ "	      FROM `role` AS r "
			+ "	     INNER JOIN `user_role` AS ur "
			+ "	        ON (r.`no` = ur.`roleNo`) "
			+ "  ) AS b "
			+ "    ON (a.`no` = b.`userNo`) "
			+ " WHERE a.`delYn` = 0")
	public List<UserDTO> findALL();

	@Select("SELECT * FROM `user` WHERE `delYn` = 0 AND email = #{email}")
	public UserDTO findByUser(String email);
	
	@Select("SELECT * FROM `role` WHERE `no` = (SELECT `roleNo` FROM `user_role` AS a WHERE a.`userNo` = #{no})")
	public RoleDTO findByRole(int no);
	
	@SelectKey(statementType = StatementType.PREPARED, statement = "select last_insert_id() as no", keyProperty = "no", before = false, resultType = int.class)
	@Insert("INSERT INTO `user` (`name`, `pwd`, `email`, `phone`) VALUE (#{name}, #{pwd}, #{email}, #{phone})")
	public int save(UserDTO user);
	
	@Insert("INSERT INTO `user_role` VALUE (#{userNo}, #{roleNo})")
	public int saveUserRole(UserRole userRole);
	
}
