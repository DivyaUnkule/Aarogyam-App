package com.app.service;

import java.util.Set;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.app.dto.RegularUserDTO;
import com.app.dto.UserDTO;
import com.app.dto.UserRegResponse;
import com.app.dto.UserResponseDTO;
import com.app.dto.WeightGainUserDTO;
import com.app.dto.WeightLossUserDTO;
import com.app.entities.Login;
import com.app.entities.RoleEntity;
import com.app.entities.User;
import com.app.enums.Role;
import com.app.repository.LoginRepo;
import com.app.repository.RoleEntityRepo;
import com.app.repository.UserRepo;
import com.app.security.CustomUserDetails;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;


@Service
@Transactional
@Component
public class UserServiceImpl implements UserService, UserDetailsService {
	
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private JavaMailSender sender;
	@Autowired
	private static JavaMailSender mailSender;
	
	
	@Autowired
	private LoginRepo loginRepo;
	// password enc
	@Autowired
	private PasswordEncoder encoder;
	@Autowired
	private RoleEntityRepo roleRepo;
	@Autowired
	private UserRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Login user = loginRepo.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Invalid Email ID !!"));
		return new CustomUserDetails(user);
	}

	@Override
	public UserRegResponse registerUser(UserDTO user) {
		// Objective : 1 rec inserted in users table n insert n recs in link table
		// user_roles
		// 1. Map dto --> entity
		Login userEntity = mapper.map(user, Login.class);
		// 2. Map Set<UserRole : enum> ---> Set<Role :entity> n assign it to the
		// transient user entity
		userEntity.setUserRoles(roleRepo.findByRoleNameIn(user.getRoles()));
		// 3. encode pwd
		userEntity.setPassword(encoder.encode(user.getPassword()));
		// 4 : Save user details
		Login persistentUser = loginRepo.save(userEntity);
		SimpleMailMessage mesg = new SimpleMailMessage();
		mesg.setTo(persistentUser.getEmail());
		mesg.setSubject("Congratulations you are registered successfully ");
		mesg.setText("Hello Admin"
				+ ",\nYou are successfully registered !!!!!\n\nRegards,\nMeet Your Doctor Team");
		sender.send(mesg);
		return new UserRegResponse("User registered successfully with ID " + persistentUser.getId());
	}
	
	@Override
	public <T> T temporaryValidateUser(String email) throws Exception {
		/*Login validatedUser = loginRepo.findByEmail(email).orElseThrow(() -> new Exception("User Not Found"));
//		return loginRepo.findByEmailAndPassword(request.getEmail(), encoder.encode(request.getPassword()))
//				.orElseThrow(()->new Exception("User Not Found"));
		Set<RoleEntity> roles = validatedUser.getUserRoles();
		RoleEntity patient = new RoleEntity();
		patient.setId((long) 2);
		patient.setRoleName(RoleEnum.valueOf("ROLE_PATIENT"));
		RoleEntity doctor = new RoleEntity();
		doctor.setId((long) 3);
		doctor.setRoleName(RoleEnum.valueOf("ROLE_DOCTOR"));
		RoleEntity admin = new RoleEntity();
		admin.setId((long) 1);
		admin.setRoleName(RoleEnum.valueOf("ROLE_ADMIN"));
		if (roles.contains(patient)) {
			Set<Role> loginRole = new HashSet<>();
			loginRole.add(Role.valueOf("ROLE_PATIENT"));
			PatientDTO dto = patientService.getPatientDetails(validatedUser.getEmail());
			dto.setRoles(loginRole);
			return (T) dto;
		}

		if (roles.contains(doctor)) {
			Set<RoleEnum> loginRole = new HashSet<>();
			loginRole.add(RoleEnum.valueOf("ROLE_DOCTOR"));
			DoctorDTO dto = doctorService.getDoctorDetails(validatedUser.getEmail());
			dto.setRoles(loginRole);
			return (T) dto;
		}
		if (roles.contains(admin)) {
			return (T) mapper.map(validatedUser, AdminDTO.class);
		}*/

		return null;

	}

	@Override
	public UserResponseDTO registerRegularUser(RegularUserDTO regularUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserResponseDTO registerWeightGainUser(WeightGainUserDTO weightGainUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserResponseDTO registerWeightLossUser(WeightLossUserDTO weightLossUser) {
		// TODO Auto-generated method stub
		return null;
	}

}
