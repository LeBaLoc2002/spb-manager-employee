package com.example.managerEmployees.model.dto.employee;

import com.example.managerEmployees.appUtils.ValidateUtils;
import com.example.managerEmployees.model.Department;
import com.example.managerEmployees.model.Employee;
import com.example.managerEmployees.model.LocationRegion;
import com.example.managerEmployees.model.Position;
import com.example.managerEmployees.model.enums.EnumPosition;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmployeeUpdateDTO implements Validator {

    private Long id;
    private String fullName;
    private String salary;
    private String experience;
    private String dateOfJoining;
    private String phone;

    private Long locationRegionId;

    private String provinceId;

    private String provinceName;

    private String districtId;

    private String districtName;

    private String wardId;

    private String wardName;

    private String address;

    private String positionCode;

    private Long departmentId;


    private MultipartFile file;


    public LocationRegion toLocationRegion() {
        return new LocationRegion()
                .setId(locationRegionId)
                .setProvinceId(provinceId)
                .setProvinceName(provinceName)
                .setDistrictId(districtId)
                .setDistrictName(districtName)
                .setWardId(wardId)
                .setWardName(wardName)
                .setAddress(address);
    }

    public Position toPosition() {
        return new Position()
                .setCode(EnumPosition.valueOf(positionCode));

    }

    public Department toDepartment() {
        return new Department()
                .setId(departmentId);
    }

    public Employee toEmployee(LocationRegion locationRegion, Department department, Position position) {
        return new Employee()
                .setId(id)
                .setFullName(fullName)
                .setSalary(BigDecimal.valueOf(Long.parseLong(salary)))
                .setExperience(experience)
                .setDateOfJoining(LocalDate.parse(dateOfJoining))
                .setPhone(phone)
                .setLocationRegion(locationRegion)
                .setDepartment(department)
                .setPosition(position.getCode());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return EmployeeUpdateDTO.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        EmployeeUpdateDTO employeeUpdateDTO = (EmployeeUpdateDTO) o;
        String salary = employeeUpdateDTO.getSalary();
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

        String dateOfJoining = employeeUpdateDTO.getDateOfJoining();
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
