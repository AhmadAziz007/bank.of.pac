package com.bank.of.pac.service;

import com.bank.of.pac.dto.MstUserDTO;
import org.springframework.http.ResponseEntity;

public interface UserService {

    public ResponseEntity<?> createUser(MstUserDTO requestDTO);

    public ResponseEntity<?> updateUser(MstUserDTO requestDTO);

    public ResponseEntity<?> getUser(Long id);

    public ResponseEntity<?> getAll();

    public ResponseEntity<?> deleteUser(Long id);
}
