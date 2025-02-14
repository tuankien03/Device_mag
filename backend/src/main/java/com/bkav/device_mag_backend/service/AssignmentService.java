package com.bkav.device_mag_backend.service;

import com.bkav.device_mag_backend.exception.BadRequestException;
import com.bkav.device_mag_backend.model.DTO.request.SaveAssignmentRequestDTO;
import com.bkav.device_mag_backend.model.DTO.response.AssignmentResponseDTO;
import com.bkav.device_mag_backend.model.DTO.response.PageResponse;
import com.bkav.device_mag_backend.repository.DAO.interfaces.IAssignmentDAO;
import com.bkav.device_mag_backend.repository.JpaRepository.AssignmentRepository;
import com.bkav.device_mag_backend.service.interfaces.IAssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AssignmentService implements IAssignmentService {
    private final IAssignmentDAO assignmentDaoimpl;


    @Override
    public PageResponse<AssignmentResponseDTO> getAllAssignments(Pageable pageable) {
        return assignmentDaoimpl.getAllAssignments(pageable);
    }

    @Override
    public AssignmentResponseDTO getAssignmentById(UUID id) {
        return assignmentDaoimpl.getAssignmentById(id);
    }

    @Override
    public AssignmentResponseDTO addAssignment(SaveAssignmentRequestDTO requestDTO) {
        if(requestDTO.getId() != null) {
            throw new BadRequestException("Lừa ai!!");
        }
        return assignmentDaoimpl.saveAssignment(requestDTO);
    }

    @Override
    public AssignmentResponseDTO updateAssignment(UUID id, SaveAssignmentRequestDTO requestDTO) {
        requestDTO.setId(id);
        return assignmentDaoimpl.saveAssignment(requestDTO);
    }

    @Override
    public void deleteAssignmentById(UUID id) {
        assignmentDaoimpl.deleteAssignmentById(id);
    }
}
