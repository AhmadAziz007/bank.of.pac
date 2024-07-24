package com.bank.of.pac.service.implement;

import com.bank.of.pac.common.ResponseDTO;
import com.bank.of.pac.dto.MstUserDTO;
import com.bank.of.pac.model.MstUser;
import com.bank.of.pac.repository.UserRepository;
import com.bank.of.pac.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service(value = "userService")
public class UserServiceImplement implements UserService {
    @Autowired
    private UserRepository userRepo;

    @Override
    public ResponseEntity<?> createUser(MstUserDTO requestDTO) {
        ResponseDTO response = new ResponseDTO();
        try {
            Optional<MstUser> existingUser = userRepo.findByEmail(requestDTO.getUserEmail());

            if (!validateSignUpMap(requestDTO)) {
                response.setCode("400");
                response.setMessage("Field mandatory tidak boleh kosong");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            } else if (!isValidEmail(requestDTO.getUserEmail())) {
                response.setCode("400");
                response.setMessage("Email tidak valid");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            } else if (existingUser.isPresent()) {
                response.setCode("409");
                response.setMessage("Email " + requestDTO.getUserEmail() + " already exists");
                return new ResponseEntity<>(response, HttpStatus.CONFLICT);
            }

            MstUser userEntity = getUserFromDTO(requestDTO);
            userRepo.save(userEntity);

            response.setCode("201");
            response.setData(userEntity);
            response.setMessage("Master user has been saved successfully");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            response.setCode("500");
            response.setMessage("Internal Server Error: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> updateUser(MstUserDTO requestDTO) {
        ResponseDTO response = new ResponseDTO();
        try {
            Optional<MstUser> userOpt = userRepo.findByUserId(requestDTO.getUserId());
            if (!userOpt.isPresent()) {
                response.setCode("404");
                response.setMessage("Id " + requestDTO.getUserId() + " not found");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            } else if (!validateSignUpMap(requestDTO)) {
                response.setCode("400");
                response.setMessage("Field mandatory tidak boleh kosong");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }

            MstUser userEntity = userOpt.get();
            updateUserFromDTO(userEntity, requestDTO);
            userRepo.save(userEntity);

            response.setCode("200");
            response.setData(userEntity);
            response.setMessage("Master user has been updated successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setCode("500");
            response.setMessage("Internal Server Error: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> getUser(Long id) {
        ResponseDTO response = new ResponseDTO();
        try {
            Optional<MstUser> mstUser = userRepo.findByUserId(id);
            if (!mstUser.isPresent()) {
                response.setCode("404");
                response.setMessage("id dengan " + id + " tidak ditemukan");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
            response.setCode("200");
            response.setData(mstUser.get());
            response.setMessage("Delete data by Master user successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setCode("500");
            response.setMessage("Internal Server Error: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> getAll() {
        ResponseDTO response = new ResponseDTO();
        List<MstUser> mstUsers = userRepo.findAll();
        response.setCode("200");
        response.setData(mstUsers);
        response.setMessage("Get Data By Master user successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteUser(Long id) {
        ResponseDTO response = new ResponseDTO();
        try {
            Optional<MstUser> mstUser = userRepo.findByUserId(id);
            if (!mstUser.isPresent()) {
                response.setCode("404");
                response.setMessage("id dengan " + id + " tidak ditemukan");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
            userRepo.delete(mstUser.get());
            response.setCode("200");
            response.setData(mstUser.get());
            response.setMessage("Delete data by Master user successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setCode("500");
            response.setMessage("Internal Server Error: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private boolean validateSignUpMap(MstUserDTO requestDTO) {
        return requestDTO.getUserName() != null &&
                requestDTO.getUserEmail() != null &&
                requestDTO.getBalance() > 0;
    }

    private MstUser getUserFromDTO(MstUserDTO requestDTO) {
        MstUser user = new MstUser();
        user.setUserName(requestDTO.getUserName());
        user.setUserEmail(requestDTO.getUserEmail());
        user.setBalance(requestDTO.getBalance());
        return user;
    }

    private void updateUserFromDTO(MstUser user, MstUserDTO requestDTO) {
        user.setUserName(requestDTO.getUserName());
        user.setUserEmail(requestDTO.getUserEmail());
        user.setBalance(requestDTO.getBalance());
    }

    private boolean isValidEmail(String email){
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email.matches(emailRegex);
    }
}
