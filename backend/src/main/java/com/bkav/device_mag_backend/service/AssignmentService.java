package com.bkav.device_mag_backend.service;

import com.bkav.device_mag_backend.exception.BadRequestException;
import com.bkav.device_mag_backend.model.DTO.request.SaveAssignmentRequestDTO;
import com.bkav.device_mag_backend.model.DTO.request.SaveDeviceRequestDTO;
import com.bkav.device_mag_backend.model.DTO.response.AssignmentResponseDTO;
import com.bkav.device_mag_backend.model.DTO.response.DeviceResponseDTO;
import com.bkav.device_mag_backend.model.DTO.response.PageResponse;
import com.bkav.device_mag_backend.model.entity.Device;
import com.bkav.device_mag_backend.model.entity.DeviceStatus;
import com.bkav.device_mag_backend.repository.DAO.interfaces.IAssignmentDAO;
import com.bkav.device_mag_backend.service.interfaces.IAssignmentService;
import com.bkav.device_mag_backend.service.interfaces.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AssignmentService implements IAssignmentService {
    private final IAssignmentDAO assignmentDaoimpl;
    private final IUserService userService;
    private final DeviceService deviceService;


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
            throw new BadRequestException("Đã có người mượn!!!");
        }
        if( userService.findUserById(requestDTO.getUserId()).getId() == null) {
            throw new BadRequestException("User không tồn tại!!");
        }
        DeviceResponseDTO device = deviceService.findDeviceById(requestDTO.getDeviceId());
        if(device == null) {
            throw new BadRequestException("Device không tồn tại!!");
        }

        if(!deviceService.checkDeviceAvailability(requestDTO.getDeviceId())) {
            throw new BadRequestException("Không thể mượn thiết bị!");
        }
        device.setStatus(DeviceStatus.ASSIGNED);
        SaveDeviceRequestDTO deviceRequestDTO = new SaveDeviceRequestDTO();
        deviceRequestDTO.setDeviceId(device.getDeviceId());
        deviceRequestDTO.setStatus(device.getStatus());
        deviceRequestDTO.setDescription(device.getDescription());
        deviceRequestDTO.setName(device.getName());
        deviceService.saveDevice(deviceRequestDTO);

        return assignmentDaoimpl.saveAssignment(requestDTO);
    }

    @Override
    public AssignmentResponseDTO updateAssignment(UUID id, SaveAssignmentRequestDTO requestDTO) {
        requestDTO.setId(id);
        return assignmentDaoimpl.saveAssignment(requestDTO);
    }

    @Override
    public void returnDevice(UUID id) {
        AssignmentResponseDTO assignmentResponseDTO =  assignmentDaoimpl.getAssignmentById(id);

        //update device
        DeviceResponseDTO device = deviceService.findDeviceById(assignmentResponseDTO.getDeviceId());
        SaveDeviceRequestDTO deviceRequestDTO = new SaveDeviceRequestDTO();
        deviceRequestDTO.setDeviceId(device.getDeviceId());
        deviceRequestDTO.setStatus(DeviceStatus.AVAILABLE) ;
        deviceRequestDTO.setDescription(device.getDescription());
        deviceRequestDTO.setName(device.getName());
        deviceService.saveDevice(deviceRequestDTO);

        //update assignment
        SaveAssignmentRequestDTO assignmentRequestDTO = new SaveAssignmentRequestDTO();
        assignmentRequestDTO.setId(id);
        assignmentRequestDTO.setReturnedAt(LocalDateTime.now());
        assignmentRequestDTO.setDeviceId(assignmentResponseDTO.getDeviceId());
        assignmentRequestDTO.setUserId(assignmentResponseDTO.getUserId());
        assignmentDaoimpl.saveAssignment(assignmentRequestDTO);
    }

    @Override
    public void deleteAssignmentById(UUID id) {
        assignmentDaoimpl.deleteAssignmentById(id);
    }
}
