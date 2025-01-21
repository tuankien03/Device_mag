package com.bkav.device_mag_backend.service.interfaces;

import java.util.List;

public interface IService <T, ID> {
    List<T> findAll();
    T findById(ID id);
    T save(T t);
    T delete(ID id);
}
