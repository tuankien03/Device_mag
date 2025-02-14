package com.bkav.device_mag_backend.repository.DAO.interfaces;

import com.bkav.device_mag_backend.model.DTO.request.SaveAssignmentRequestDTO;
import com.bkav.device_mag_backend.model.DTO.response.AssignmentResponseDTO;
import com.bkav.device_mag_backend.model.DTO.response.PageResponse;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface IAssignmentDAO {
    AssignmentResponseDTO saveAssignment(SaveAssignmentRequestDTO saveAssignmentRequestDTO);
    PageResponse<AssignmentResponseDTO> getAllAssignments(Pageable pageable);
    AssignmentResponseDTO getAssignmentById(UUID assignmentId);
    void deleteAssignmentById(UUID assignmentId);
}
