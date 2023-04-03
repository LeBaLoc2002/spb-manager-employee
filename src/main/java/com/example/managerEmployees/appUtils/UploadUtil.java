package com.example.managerEmployees.appUtils;

import com.cloudinary.utils.ObjectUtils;
import com.example.managerEmployees.exception.DataInputException;
import com.example.managerEmployees.model.Employee;
import com.example.managerEmployees.model.EmployeeAvatar;
import java.util.Map;
import org.springframework.stereotype.Component;


@Component
public class UploadUtil {
    public static final String EMLOYEES_IMAGE_UPLOAD_FOLDER = "emloyee_images";


    public Map buildImageUploadParams(EmployeeAvatar emloyeeAvatar) {
        if (emloyeeAvatar == null || emloyeeAvatar.getId() == null)
            throw new DataInputException("Không thể upload hình ảnh của khách hàng chưa được lưu");

        String publicId = String.format("%s/%s", EMLOYEES_IMAGE_UPLOAD_FOLDER, emloyeeAvatar.getId());

        return ObjectUtils.asMap(
                "public_id", publicId,
                "overwrite", true,
                "resource_type", "image"
        );
    }

    public Map buildImageDestroyParams(Employee emloyee, String publicId) {
        if (emloyee == null || emloyee.getId() == null)
            throw new DataInputException("Không thể destroy hình ảnh của nhân viên không xác định");

        return ObjectUtils.asMap(
                "public_id", publicId,
                "overwrite", true,
                "resource_type", "image"
        );
    }
}