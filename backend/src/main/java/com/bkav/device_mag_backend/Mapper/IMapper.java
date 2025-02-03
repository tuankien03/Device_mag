package com.bkav.device_mag_backend.Mapper;

public interface IMapper<T, D> {
    T convertToEntity(D d);
    D convertToDto(T t);
}
