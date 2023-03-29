package com.example.managerEmployees.service.ImageEmployee;

import com.example.managerEmployees.model.EmployeeAvatar;
import com.example.managerEmployees.service.IGeneralService;

public interface IEmployeeAvatarService extends IGeneralService<EmployeeAvatar> {
    void delete(String id);

}
