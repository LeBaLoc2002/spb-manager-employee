package com.example.managerEmployees.service.employee;

import com.example.managerEmployees.appUtils.UploadUtil;
import com.example.managerEmployees.exception.DataInputException;
import com.example.managerEmployees.model.*;
import com.example.managerEmployees.model.dto.employee.EmployeeCreateDTO;
import com.example.managerEmployees.model.dto.employee.*;
import com.example.managerEmployees.model.dto.employee.EmployeeFillterDTO;
import com.example.managerEmployees.model.Enum.FileType;
import com.example.managerEmployees.repository.*;
import com.example.managerEmployees.service.imageEmployee.IEmployeeAvatarService;
import com.example.managerEmployees.service.locationRegion.ILocationRegionService;
import com.example.managerEmployees.upload.IUploadService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
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
public class EmployeeImpl implements IEmployeeService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private LocationRegionRepository locationRegionRepository;
    @Autowired
    private ILocationRegionService locationRegionService;

    @Autowired
    private IEmployeeAvatarService emloyeeAvatarService;

    @Autowired
    private EmployeeFilterRepository employeeFilterRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private UploadUtil uploadImage;
    @Autowired
    private IUploadService uploadService;
    @Autowired
    private ImageEmployeeRepository imageEmloyeeRepository;
    @Autowired
    private EmployeeRepository emloyeeRepository;


    @Override
    public List<Employee> findAll() {
        return null;
    }

    @Override
    public Employee getById(Long id) {
        return emloyeeRepository.getById(id);
    }

    @Override
    public Optional<Employee> findById(Long id) {
        return emloyeeRepository.findById(id);
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
        return emloyeeRepository.save(emloyee);
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public Boolean existsEmployeeByNameAndDeletedIsFalse(String name) {
        return emloyeeRepository.existsEmployeeByNameAndDeletedIsFalse(name);
    }

    @Override
    public Boolean existsEmployeeByNameAndIdNotAndDeletedIsFalse(String name, Long id) {
        return emloyeeRepository.existsEmployeeByNameAndIdNotAndDeletedIsFalse(name,id);
    }

    @Override
    public Page<Employee> findAll(Pageable pageable) {
        return emloyeeRepository.findAll(pageable);
    }

    @Override
    public List<EmployeeDTO> finByKeyword(String keyword) {
        return emloyeeRepository.finByKeyword(keyword);
    }

    @Override
    public Optional<EmployeeDTO> findEmployeeById(Long employeeId) {
        return emloyeeRepository.findEmployeeById(employeeId);
    }

    @Override
    public List<EmployeeDTO> getAllEmployeeDTO() {
        return emloyeeRepository.getAllEmployeeDTO();
    }

    @Override
    public Employee updateWithAvatar(Employee employee, MultipartFile file) throws IOException {


        Role role = employee.getRole();
        roleRepository.save(role);
        Department department = employee.getDepartment();
        departmentRepository.save(department);
        LocationRegion locationRegion = employee.getLocationRegion();
        locationRegionRepository.save(locationRegion);

        EmployeeAvatar oldEmployeeAvatar = employee.getEmployeeAvatar();
        uploadService.destroyImage(oldEmployeeAvatar.getCloudId(), uploadImage.buildImageDestroyParams(employee, oldEmployeeAvatar.getCloudId()));
        EmployeeAvatar newEmployeeAvatar = uploadAndSaveEmloyeeAvatar(file,oldEmployeeAvatar);

        employee.setEmployeeAvatar(newEmployeeAvatar);

        employee = emloyeeRepository.save(employee);
        return employee;
    }

    @Override
    public Employee updateNoAvatar(Employee employee) {
        Role role = employee.getRole();
        roleRepository.save(role);
        Department department = employee.getDepartment();
        departmentRepository.save(department);
        LocationRegion locationRegion = employee.getLocationRegion();
        locationRegionRepository.save(locationRegion);
        EmployeeAvatar employeeAvatar = employee.getEmployeeAvatar();
        imageEmloyeeRepository.save(employeeAvatar);
        employee = emloyeeRepository.save(employee);
        return employee;
    }

    @Override
    public Employee saveLocationRegion(Employee employee) {
        locationRegionService.save(employee.getLocationRegion());
        return emloyeeRepository.save(employee);
    }

    @Override
    public Employee createEmployee(EmployeeCreateDTO employeeCreateDTO, LocationRegion locationRegion, Role role , Department department) throws ParseException {
        Employee emloyee = new Employee();
        String name = employeeCreateDTO.getName();
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
        employeeAvatar = imageEmloyeeRepository.save(employeeAvatar);
        if (fileType.equals(FileType.IMAGE.getValue())) {
            employeeAvatar = uploadAndSaveEmloyeeAvatar(file,employeeAvatar);
        }
        department = new Department();
        department.setId(employeeCreateDTO.getDepartmentId());
//        department.setName(employeeCreateDTO.toDepartment().getName());

        role = new Role();
        role.setId(employeeCreateDTO.getRoleId());
//        role.setCode(employeeCreateDTO.toRole().getCode());

        locationRegion = locationRegionService.save(locationRegion);

        emloyee.setId(null)
                .setName(name)
                .setSalary(BigDecimal.valueOf(Long.parseLong(salary)))
                .setExperience(experience)
                .setDateOfJoining(dateOfJoining)
                .setPhone(phone)
                .setEmployeeAvatar(employeeAvatar)
                .setLocationRegion(locationRegion)
                .setDepartment(department)
                .setRole(role);
        emloyee = emloyeeRepository.save(emloyee);
        return emloyee;
    }



    private EmployeeAvatar uploadAndSaveEmloyeeAvatar(MultipartFile file, EmployeeAvatar emloyeeAvatar) {
        try {
            Map uploadResult = uploadService.uploadImage(file, uploadImage.buildImageUploadParams(emloyeeAvatar));
            String fileUrl = (String) uploadResult.get("secure_url");
            String fileFormat = (String) uploadResult.get("format");

            emloyeeAvatar.setFileName(emloyeeAvatar.getId() + "." + fileFormat);
            emloyeeAvatar.setFileUrl(fileUrl);
            emloyeeAvatar.setFileFolder(UploadUtil.EMLOYEES_IMAGE_UPLOAD_FOLDER);
            emloyeeAvatar.setCloudId(emloyeeAvatar.getFileFolder() + "/" + emloyeeAvatar.getId());
            return imageEmloyeeRepository.save(emloyeeAvatar);
        } catch (IOException e) {
            e.printStackTrace();
            throw new DataInputException("Upload hình ảnh thất bại.");
        }
    }

    @Transactional
    @Override
    public void softDelete(Long employeeId) {
        emloyeeRepository.softDelete(employeeId);
    }

    @Override
    public Page<EmployeeDTO> findAllByFilters(EmployeeFillterDTO employeeFillterDTO, Pageable pageable) {
        Page<Employee> emloyeePage = employeeFilterRepository.findAllByFilters(employeeFillterDTO, pageable);
        Page<EmployeeDTO> employeeDTOS = emloyeePage.map(objectEntity -> modelMapper.map(objectEntity, EmployeeDTO.class));
        return employeeDTOS;
    }

    @Override
    public EmployeeDTO getInfo(Model model) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
//        Optional<EmployeeDTO> employeeDTOOptional = .findById(EmployeeId);
        String name = employeeDTO.getName();
        BigDecimal salary = employeeDTO.getSalary();
        String experience = employeeDTO.getExperience();
        LocalDate dateOfJoining = LocalDate.parse(employeeDTO.getDateOfJoining());
        String phone = employeeDTO.getPhone();
        String roleId = String.valueOf(employeeDTO.getRole().getId());
        String locationRegionId = String.valueOf(employeeDTO.getLocationRegion().getId());
        String emloyeeAvatarId = employeeDTO.getEmployeeAvatar().getId();
        String departmentId = String.valueOf(employeeDTO.getDepartment().getId());


        model.addAttribute("name", name);
        model.addAttribute("code", roleId);
        model.addAttribute("salary", salary);
        model.addAttribute("experience", experience);
        model.addAttribute("dateOfJoining", dateOfJoining);
        model.addAttribute("phone", phone);
        model.addAttribute("locationRegionId", locationRegionId);
        model.addAttribute("emloyeeAvatarId", emloyeeAvatarId);
        model.addAttribute("Department", departmentId);
        return employeeDTO;
    }
}
