package com.example.managerEmployees.model.dto.employee;

import com.example.managerEmployees.appUtils.ValidateUtils;
import com.example.managerEmployees.model.Department;
import com.example.managerEmployees.model.Employee;
import com.example.managerEmployees.model.Enum.FileType;
import com.example.managerEmployees.model.LocationRegion;
import com.example.managerEmployees.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmployeeCreateDTO  implements Validator {

    private Long id;
    private String name;
    private String salary;
    private String experience;
    private String dateOfJoining;
    private String phone;

    private String provinceId;

    private String provinceName;

    private String districtId;

    private String districtName;

    private String wardId;

    private String wardName;

    private String address;

    private Long roleId;

    private Long departmentId;
    private MultipartFile file;


    public LocationRegion toLocationRegion(){
        return new LocationRegion()
                .setId(id)
                .setProvinceId(provinceId)
                .setProvinceName(provinceName)
                .setDistrictId(districtId)
                .setDistrictName(districtName)
                .setWardId(wardId)
                .setWardName(wardName)
                .setAddress(address);
    }

    public Role toRole () {
        return new Role()
                .setId(id);
    }

    public Department toDepartment (){
        return new Department()
                .setId(id);
    }
    public Employee toEmployee(LocationRegion locationRegion , Department department, Role role) throws ParseException {
        return new Employee()
                .setId(id)
                .setName(name)
                .setSalary(BigDecimal.valueOf(Long.parseLong(salary)))
                .setExperience(experience)
                .setDateOfJoining(LocalDate.parse(dateOfJoining))
                .setPhone(phone)
                .setLocationRegion(locationRegion)
                .setDepartment(department)
                .setRole(role);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return EmployeeCreateDTO.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
    EmployeeCreateDTO employeeCreateDTO = (EmployeeCreateDTO) o;
        MultipartFile multipartFile = employeeCreateDTO.getFile();
        if (multipartFile == null || multipartFile.getSize() == 0) {
            errors.rejectValue("file", "file.null", "Vui lòng chọn tệp tin làm ảnh đại diện");
            return;
        }

        String file = multipartFile.getContentType();
        assert file != null;
        file = file.substring(0, 5);

        if (!file.equals(FileType.IMAGE.getValue())) {
            errors.rejectValue("file", "file.type", "Vui lòng chọn tệp tin ảnh đại diện phải là JPG hoặc PNG");
            return;
        }

        long fileSize = multipartFile.getSize();

        if (fileSize > 512000) {
            errors.rejectValue("file", "file.size", "Vui lòng chọn tệp tin ảnh đại diện nhỏ hơn 500 KB");
        }

        String salary = employeeCreateDTO.getSalary();
        if (salary != null || salary.length() > 0) {
            if (salary.length() > 6) {
                errors.rejectValue("salary", "salary.max", "Lương tối đa là 999.999 $ ,vui lòng nhập lương.");
                return;
            }
            if (salary.length() < 4) {
                errors.rejectValue("salary", "salary.min", "Lương tối thiểu là 99.99 $ ,vui lòng nhập lương.");
                return;
            }
            if (!salary.matches("(^$|[0-9]*$)")) {
                errors.rejectValue("salary", "salary.number", "Lương phải là số ,vui lòng nhập lương.");
                return;
            }
        }
        else {
            errors.rejectValue("salary", "salary.null", "Lương không được để trống ,vui lòng nhập lương.");
        }

        String dateOfJoining = employeeCreateDTO.getDateOfJoining();
        if (dateOfJoining != null || dateOfJoining.length()> 0) {
            if (!dateOfJoining.matches(ValidateUtils.DATE_REGEX_YYYY_DD_MM)){
                errors.rejectValue("dateOfJoining", "dateOfJoining.number", "Ngày tham gia phải là ngày tháng năm sinh ,vui lòng nhập lại.");
                return;
            }
        }
        else {
            errors.rejectValue("dateOfJoining" , "dateOfJoining.null" , "Ngày tham gia không được để trống ,vui lòng nhập ngày tham gia.");
        }
    }

}