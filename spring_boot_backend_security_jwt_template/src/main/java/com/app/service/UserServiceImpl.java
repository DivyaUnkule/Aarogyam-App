package com.app.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.app.dto.Signup;
import com.app.dto.UserRegResponse;
import com.app.entities.Login;
import com.app.repository.LoginRepo;
import com.app.repository.RoleEntityRepo;
import com.app.security.CustomUserDetails;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	//dep : dao layer i/f
		@Autowired
		private LoginRepo userDao;
		//dep
		@Autowired
		private ModelMapper mapper;
		//dep 
		@Autowired
		private PasswordEncoder encoder;
		@Autowired
		private RoleEntityRepo roleRepo;
		@Autowired
		private LoginRepo loginRepo;
		
		public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
			Login user = userDao.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Invalid Email ID !!"));
			return new CustomUserDetails(user);
		}

	public UserRegResponse userRegistration(Signup reqDTO) {
		//dto --> entity
		Login userEntity = mapper.map(reqDTO, Login.class);
		// 2. Map Set<UserRole : enum> ---> Set<Role :entity> n assign it to the
		// transient user entity
		userEntity.setUserRoles(roleRepo.findByRoleNameIn(reqDTO.getRoles()));
		// 3. encode pwd
		userEntity.setPassword(encoder.encode(reqDTO.getPassword()));
		// 4 : Save user details
		Login persistentUser = loginRepo.save(userEntity);
		
		return new UserRegResponse("User registered successfully with ID " + persistentUser.getId());
	}

	
	

}
