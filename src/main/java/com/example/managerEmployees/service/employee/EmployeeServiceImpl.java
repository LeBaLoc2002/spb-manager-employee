package com.example.managerEmployees.service.employee;

import com.example.managerEmployees.appUtils.UploadUtil;
import com.example.managerEmployees.exception.DataInputException;
import com.example.managerEmployees.model.*;
import com.example.managerEmployees.model.dto.employee.EmployeeCreateDTO;
import com.example.managerEmployees.model.dto.employee.*;
import com.example.managerEmployees.model.dto.employee.EmployeeFillterDTO;
import com.example.managerEmployees.model.enums.EnumPosition;
import com.example.managerEmployees.model.enums.FileType;
import com.example.managerEmployees.repository.*;
import com.example.managerEmployees.service.locationRegion.ILocationRegionService;
import com.example.managerEmployees.upload.IUploadService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@Service
@Transactional
public class EmployeeServiceImpl implements IEmployeeService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private LocationRegionRepository locationRegionRepository;

    @Autowired
    private ILocationRegionService locationRegionService;

    @Autowired
    private EmployeeFilterRepository employeeFilterRepository;

    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private UploadUtil uploadUtil;

    @Autowired
    private IUploadService uploadService;

    @Autowired
    private EmployeeAvatarRepository employeeAvatarRepository;

    @Autowired
    private EmployeeRepository employeeRepository;


    @Override
    public List<Employee> findAll() {
        return null;
    }

    @Override
    public Employee getById(Long id) {
        return employeeRepository.getById(id);
    }

    @Override
    public Optional<Employee> findById(Long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public Employee getById(String id) {
        return null;
    }

    @Override
    public Optional<Employee> findById(String id) {
        return Optional.empty();
    }

    @Override
    public Employee save(Employee emloyee) {
        return employeeRepository.save(emloyee);
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public Boolean existsEmployeeByFullNameAndDeletedIsFalse(String fullName) {
        return employeeRepository.existsEmployeeByFullNameAndDeletedIsFalse(fullName);
    }

    @Override
    public Boolean existsEmployeeByFullNameAndIdNotAndDeletedIsFalse(String fullName, Long id) {
        return employeeRepository.existsEmployeeByFullNameAndIdNotAndDeletedIsFalse(fullName,id);
    }

    @Override
    public Page<Employee> findAll(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    @Override
    public List<EmployeeDTO> finByKeyword(String keyword) {
        return employeeRepository.finByKeyword(keyword);
    }

    @Override
    public Optional<EmployeeDTO> findEmployeeDTOById(Long employeeId) {
        return employeeRepository.findEmployeeById(employeeId);
    }

    @Override
    public List<EmployeeDTO> getAllEmployeeDTO() {
        return employeeRepository.getAllEmployeeDTO();
    }

    @Override
    public Employee updateWithAvatar(Employee employee, MultipartFile file) throws IOException {

        Department department = employee.getDepartment();
        departmentRepository.save(department);
        LocationRegion locationRegion = employee.getLocationRegion();
        locationRegionRepository.save(locationRegion);

        EmployeeAvatar oldEmployeeAvatar = employee.getEmployeeAvatar();
        uploadService.destroyImage(oldEmployeeAvatar.getCloudId(), uploadUtil.buildImageDestroyParams(employee, oldEmployeeAvatar.getCloudId()));
        EmployeeAvatar newEmployeeAvatar = uploadAndSaveEmployeeAvatar(file,oldEmployeeAvatar);

        employee.setEmployeeAvatar(newEmployeeAvatar);

        employee = employeeRepository.save(employee);
        return employee;
    }

    @Override
    public Employee updateNoAvatar(Employee employee) {
        Department department = employee.getDepartment();
        departmentRepository.save(department);
        LocationRegion locationRegion = employee.getLocationRegion();
        locationRegionRepository.save(locationRegion);
        EmployeeAvatar employeeAvatar = employee.getEmployeeAvatar();
        employeeAvatarRepository.save(employeeAvatar);
        employee = employeeRepository.save(employee);
        return employee;
    }

    @Override
    public Employee saveLocationRegion(Employee employee) {
        locationRegionService.save(employee.getLocationRegion());
        return employeeRepository.save(employee);
    }

    @Override
    public Employee createEmployee(EmployeeCreateDTO employeeCreateDTO, LocationRegion locationRegion, Position position, Department department) throws ParseException {
        Employee emloyee = new Employee();
        String name = employeeCreateDTO.getFullName();
        String salary = employeeCreateDTO.getSalary();
        String experience = employeeCreateDTO.getExperience();

        LocalDate dateOfJoining = LocalDate.parse(employeeCreateDTO.getDateOfJoining());
        String phone = employeeCreateDTO.getPhone();

        MultipartFile file = employeeCreateDTO.getFile();
        String fileType = file.getContentType();
        assert fileType != null ;
        fileType = fileType.substring(0,5);

        EmployeeAvatar employeeAvatar = new EmployeeAvatar();
        employeeAvatar.setFileType(fileType);
        employeeAvatar = employeeAvatarRepository.save(employeeAvatar);
        if (fileType.equals(FileType.IMAGE.getValue())) {
            employeeAvatar = uploadAndSaveEmployeeAvatar(file,employeeAvatar);
        }
        department = new Department();
        department.setId(employeeCreateDTO.getDepartmentId());
//        department.setName(employeeCreateDTO.toDepartment().getName());

        locationRegion = locationRegionService.save(locationRegion);

        EnumPosition enumPosition = EnumPosition.valueOf(employeeCreateDTO.getPositionCode());

        emloyee.setId(null)
                .setFullName(name)
                .setSalary(BigDecimal.valueOf(Long.parseLong(salary)))
                .setExperience(experience)
                .setDateOfJoining(dateOfJoining)
                .setPhone(phone)
                .setEmployeeAvatar(employeeAvatar)
                .setLocationRegion(locationRegion)
                .setDepartment(department)
                .setPosition(enumPosition);
        emloyee = employeeRepository.save(emloyee);
        return emloyee;
    }


    private EmployeeAvatar uploadAndSaveEmployeeAvatar(MultipartFile file, EmployeeAvatar emloyeeAvatar) {
        try {
            Map uploadResult = uploadService.uploadImage(file, uploadUtil.buildImageUploadParams(emloyeeAvatar));
            String fileUrl = (String) uploadResult.get("secure_url");
            String fileFormat = (String) uploadResult.get("format");

            emloyeeAvatar.setFileName(emloyeeAvatar.getId() + "." + fileFormat);
            emloyeeAvatar.setFileUrl(fileUrl);
            emloyeeAvatar.setFileFolder(UploadUtil.EMLOYEES_IMAGE_UPLOAD_FOLDER);
            emloyeeAvatar.setCloudId(emloyeeAvatar.getFileFolder() + "/" + emloyeeAvatar.getId());
            return employeeAvatarRepository.save(emloyeeAvatar);
        } catch (IOException e) {
            e.printStackTrace();
            throw new DataInputException("Upload hình ảnh thất bại.");
        }
    }

    @Override
    public void softDelete(Long employeeId) {
        employeeRepository.softDelete(employeeId);
    }

    @Override
    public Page<EmployeeDTO> findAllByFilters(EmployeeFillterDTO employeeFillterDTO, Pageable pageable) {
        Page<Employee> emloyeePage = employeeFilterRepository.findAllByFilters(employeeFillterDTO, pageable);
        Page<EmployeeDTO> employeeDTOS = emloyeePage.map(objectEntity -> modelMapper.map(objectEntity, EmployeeDTO.class));
        return employeeDTOS;
    }

}
