package com.example.managerEmployees.controller.API;

import com.example.managerEmployees.AppUtils.AppUtils;
import com.example.managerEmployees.exception.DataInputException;
import com.example.managerEmployees.model.Enum.FileType;
import com.example.managerEmployees.model.dto.employee.EmployeeCreateDTO;
import com.example.managerEmployees.model.dto.employee.EmployeeDTO;
import com.example.managerEmployees.model.dto.employee.EmployeeFillterDTO;
import com.example.managerEmployees.model.dto.employee.EmployeeUpdateDTO;
import com.example.managerEmployees.model.Department;
import com.example.managerEmployees.model.Employee;
import com.example.managerEmployees.model.LocationRegion;
import com.example.managerEmployees.model.Role;
import com.example.managerEmployees.service.Department.IDepartmentService;
import com.example.managerEmployees.service.Employee.IEmployeeService;
import com.example.managerEmployees.service.LocationRegion.ILocationRegionService;
import com.example.managerEmployees.service.Role.IRoleService;
import io.jsonwebtoken.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.*;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

@RestController
@RequestMapping("api/employees")
public class EmployeeAPI {

    @Autowired
    private AppUtils appUtils;
    @Autowired
    private ILocationRegionService locationRegionService;
    @Autowired
    private IDepartmentService departmentService;
    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private IRoleService roleService;
    @GetMapping
    public ResponseEntity<?> getAllEmployee(Pageable pageable) {
//        Page<Employee> employeeDTOList = employeeService.findAll(pageable);
//
//        List<EmployeeDTO> employeeDTOS = new ArrayList<>();
//
//        for (Employee item : employeeDTOList.getContent()) {
//            employeeDTOS.add(item.toEmployeeDTO());
//        }

//        return new ResponseEntity<>(new PageImpl<>(employeeDTOS, pageable, employeeDTOList.getTotalElements()), HttpStatus.OK);
        List<EmployeeDTO> employeeDTOList = employeeService.getAllEmployeeDTO();

        if(employeeDTOList.size() == 0 ){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(employeeDTOList,HttpStatus.OK);
    }

    @PostMapping("/fillter")
    public ResponseEntity <?> employeeFilter (@RequestBody EmployeeFillterDTO employeeFillterDTO ,BindingResult bindingResult , @RequestParam(name = "sort" , required = false,defaultValue = "ASC") String sort){
        if (bindingResult.hasFieldErrors()) {
            return appUtils.mapErrorToResponse(bindingResult);
        }
        int start = 0;
        int length = 10;
        Sort sortAble = null;
        if (sort.equals("ASC")){
            sortAble = Sort.by("id").ascending();
        }else {
            sortAble = Sort.by("id").descending();
        }
        Pageable pageable = PageRequest.of(start,length,sortAble);
        Page<EmployeeDTO> employeeDTOS = employeeService.findAllByFilters(employeeFillterDTO,pageable);
        return new ResponseEntity<>(employeeDTOS.getContent(),HttpStatus.OK);
    }
    @GetMapping("/{employeeId}")
    public ResponseEntity<?> getbyIdEmployee(@PathVariable Long employeeId){
      Optional<Employee> emloyeeOptional = employeeService.findById(employeeId);
      if (!emloyeeOptional.isPresent()) {
          throw new DataInputException("ID không hợp lệ");
      }
      return new ResponseEntity<>(emloyeeOptional.get().toEmployeeDTO(),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> CreateEmployee (@Validated EmployeeCreateDTO employeeCreateDTO , BindingResult bindingResult) throws ParseException {
        new EmployeeCreateDTO().validate(employeeCreateDTO, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            return appUtils.mapErrorToResponse(bindingResult);
        }
//        if (employeeService.existsEmployeeByNameAndDeletedIsFalse(employeeCreateDTO.getName())){
//            throw new DataInputException("Tên nhân viên này đã được tồn tại");
//        }

        MultipartFile file = employeeCreateDTO.getFile();
        if (file == null) {
            throw new DataInputException("Ảnh không được để trống");
        }

        LocationRegion locationRegion = employeeCreateDTO.toLocationRegion();
        locationRegion.setId(null);
        Role role = employeeCreateDTO.toRole();
        role.setId(null);
        Department department = employeeCreateDTO.toDepartment();
        department.setId(null);

        Employee newEmployee = employeeService.createEmployee(employeeCreateDTO, locationRegion,role, department);

        return new ResponseEntity<>(newEmployee.toEmployeeDTO(), HttpStatus.CREATED);
    }

    @PatchMapping("{employeeId}")
    public ResponseEntity<?> updateEmployee(@PathVariable Long employeeId, MultipartFile file, @Validated EmployeeUpdateDTO employeeUpdateDTO, BindingResult bindingResult) throws ParseException, java.io.IOException {
        new EmployeeUpdateDTO().validate(employeeUpdateDTO,bindingResult);
        if (bindingResult.hasFieldErrors()) {
            return appUtils.mapErrorToResponse(bindingResult);
        }
        if (file != null && !file.isEmpty()) {
            String fileType = file.getContentType();
            assert fileType != null;
            fileType = fileType.substring(0, 5);

            if (!fileType.equals(FileType.IMAGE.getValue())) {
                throw new DataInputException("Vui lòng chọn tệp tin ảnh đại diện phải là JPG hoặc PNG");
            }

            long fileSize = file.getSize();

            if (fileSize > 512000) {
                throw new DataInputException("Vui lòng chọn tệp tin ảnh đại diện nhỏ hơn 500 KB");
            }
        }


        Optional<Employee> emloyeeOptional = employeeService.findById(employeeId);
        if (!emloyeeOptional.isPresent()) {
            throw new DataInputException("Id của bạn không hợp lệ, xin vui lòng hãy thử lại!");
        }

//        if (employeeService.existsEmployeeByNameAndIdNotAndDeletedIsFalse(employeeUpdateDTO.getName(), employeeUpdateDTO.getId())) {
//            throw new DataInputException("Tên nhân viên này đã được sử dụng");
//        }

        Long locationRegionId = employeeUpdateDTO.getLocationRegionId();
        Optional<LocationRegion>  locationRegionOptional = locationRegionService.findById(locationRegionId);
        if (!locationRegionOptional.isPresent()){
            throw new DataInputException("ID khu vực vị trí không hợp lệ.");
        }

        Long roleId ;
        try {
            roleId = employeeUpdateDTO.getRoleId();
        }catch (IOException e){
        throw new DataInputException("Role không hơp lệ");
        }
        Optional<Role> roleOptional = roleService.findById(roleId);
        if (!roleOptional.isPresent()) {
            throw new DataInputException("Role không tồn tại.");
        }

        Long departmentId;
        try {
            departmentId = employeeUpdateDTO.getDepartmentId();
        }catch (IOException e) {
            throw new DataInputException("Phòng nhân sự không hợp lệ");
        }

        Optional<Department> optionalDepartment = departmentService.findById(departmentId);
        if (!optionalDepartment.isPresent()){
            throw new DataInputException("Phòng nhân sự không tồn tại");
        }

        LocationRegion locationRegion = employeeUpdateDTO.toLocationRegion();
        locationRegion.setId(emloyeeOptional.get().getLocationRegion().getId());
        Department department = employeeUpdateDTO.toDepartment();
        department.setId(emloyeeOptional.get().getDepartment().getId());
        department.setName(emloyeeOptional.get().getDepartment().getName());
        Role role = employeeUpdateDTO.toRole();
        role.setId(emloyeeOptional.get().getRole().getId());
        role.setCode(emloyeeOptional.get().getRole().getCode());
        role.setName(emloyeeOptional.get().getRole().getName());
        Employee employee = employeeUpdateDTO.toEmloyee(locationRegion, department, role);
        employee.setId(employeeId);
        employee.setEmployeeAvatar(emloyeeOptional.get().getEmployeeAvatar());
//        employee.getLocationRegion()
//                .setProvinceId(locationRegion.getProvinceId())
//                .setProvinceName(locationRegion.getProvinceName())
//                .setDistrictId(locationRegion.getDistrictId())
//                .setDistrictName(locationRegion.getDistrictName())
//                .setWardId(locationRegion.getWardId())
//                .setWardName(locationRegion.getWardName())
//                .setAddress(locationRegion.getAddress());

//        employee = employeeService.saveLocationRegion(employee);

//        Department department = optionalDepartment.get();
//        employee.getDepartment()
//                .setId(department.getId())
//                .setName(department.getName());
//        department = departmentService.save(department);
//
//        Role role = roleOptional.get();
//        employee.getRole()
//                .setId(role.getId())
//                .setCode(role.getCode());
//
//        role = roleService.save(role);

//        employee = employeeService.save(employee);
        if (file != null) {
            employee = employeeService.updateWithAvatar(employee, file);
        }else {
            employee = employeeService.updateNoAvatar(employee);
        }
        return new ResponseEntity<>(employee.toEmployeeDTO(),HttpStatus.OK);
    }

    @DeleteMapping("{employeeId}")
    public ResponseEntity<?>deletedEmployee(@PathVariable Long employeeId){
        Optional<Employee> emloyeeOptional = employeeService.findById(employeeId);

        if (!emloyeeOptional.isPresent()){
            throw new DataInputException("ID nhân viên không hợp lệ");
        }

        try {
        employeeService.softDelete(employeeId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }catch (IOException e){
            throw new DataInputException("Vui lòng liên hệ admin");
        }
    }


    @PostMapping("/searchKeyWord/{keySearch}")
    public ResponseEntity<?> searchEmployee(@PathVariable String keySearch) {
        if (Objects.equals(keySearch, "null") || Objects.equals(keySearch, "")) {
            keySearch = "%%";
        }
        else {
            keySearch = "%" + keySearch + "%";
        }
        return ResponseEntity.ok(employeeService.finByKeyword(keySearch));
    }

}
