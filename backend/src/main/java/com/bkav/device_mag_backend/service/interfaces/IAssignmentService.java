package com.bkav.device_mag_backend.service.interfaces;
import com.bkav.device_mag_backend.model.DTO.request.SaveAssignmentRequestDTO;
import com.bkav.device_mag_backend.model.DTO.response.AssignmentResponseDTO;
import com.bkav.device_mag_backend.model.DTO.response.PageResponse;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface IAssignmentService {
    PageResponse<AssignmentResponseDTO> getAllAssignments(Pageable pageable);
    AssignmentResponseDTO getAssignmentById(UUID id);
    AssignmentResponseDTO addAssignment(SaveAssignmentRequestDTO requestDTO);
    AssignmentResponseDTO updateAssignment(UUID id, SaveAssignmentRequestDTO requestDTO);
    void deleteAssignmentById(UUID id);
}
