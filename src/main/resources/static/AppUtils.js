class AppUtils {
    static DOMAIN_SERVER = location.origin;

    static BASE_URL_CLOUD_IMAGE = "https://res.cloudinary.com/dlzqfpp8i/image/upload";
    static BASE_SCALE_IMAGE = "c_limit,w_250,h_100,q_100";
    static BASE_SCALE_IMAGE_SHOP = "c_limit,w_200,h_200,q_100";
    static BASE_SCALE_IMAGE_250_250 = "c_limit,w_250,h_250,q_100";
    static BASE_SCALE_IMAGE_MOVIE_350_350_100 = "c_limit,w_350,h_350,q_100";
    static BASE_SCALE_IMAGE_MOVIE_350_482_100 = "c_limit,w_350,h_482,q_100";
    static SCALE_IMAGE_W354_Q100 = "c_limit,w_354,q_100";
    static SCALE_IMAGE_W250_H400_Q100 = "c_limit,w_250,h_400,q_100";

    static SCALE_IMAGE_W202_H300_Q100 = "c_limit,w_202,h_300,q_100";
    static SCALE_IMAGE_W100_H100_Q100 = "c_limit,w_100,h_100,q_100";
    static SCALE_IMAGE_W100_H100_Q80 = "c_limit,w_202,h_300,q_90";
    static SCALE_IMAGE_W60_H60_Q100 = "c_limit,w_60,h_60,q_100";
    static SCALE_IMAGE_W158_H235_Q100 = "c_limit,w_158,h_235,q_100";

    static API_SERVER = this.DOMAIN_SERVER + "/api";
    static PROVINCE_URL = "https://vapi.vnappmob.com/api/province/";
    static POSITION_API = this.API_SERVER + "/positions";
    static DEPARTMENT_API = this.API_SERVER + "/departments";
    static EMPLOYEE_API = this.API_SERVER + "/employees";

    static AlertMessageEn = class {
        static SUCCESS_CREATED = "Successful data generation !";
        static SUCCESS_UPDATED = "Data update successful !";

        static ERROR_400 = "The operation failed, please check the data again.";
        static ERROR_401 = "Access Denied! Invalid credentials.";
        static ERROR_403 = "Access Denied! You are not authorized to perform this function.";
        static ERROR_404 = "This content has been removed or does not exist";
        static ERROR_500 = "Data saving failed, please contact the system administrator.";

        static ERROR_LOADING_PROVINCE = "Loading list of provinces - cities failed !";
        static ERROR_LOADING_DISTRICT = "Loading list of district - ward failed !"
        static ERROR_LOADING_WARD = "Loading list of wards - communes - towns failed !";
    }

    static AlertMessageVi = class {
        static SUCCESS_CREATED = "Tạo dữ liệu thành công !";
        static SUCCESS_UPDATED = "Cập nhật dữ liệu thành công !";

        static ERROR_400 = "Thao tác không thành công, vui lòng kiểm tra lại dữ liệu.";
        static ERROR_401 = "Quyền truy cập bị từ chối! Thông tin đăng nhập không hợp lệ.";
        static ERROR_403 = "Quyền truy cập bị từ chối! Bạn không được phép thực hiện chức năng này.";
        static ERROR_404 = "Nội dung này đã bị xóa hoặc không tồn tại";
        static ERROR_500 = "Lỗi hệ thống, vui lòng liên hệ với quản trị viên hệ thống.";

        static ERROR_LOADING_ROLE = "Tải danh sách vai trò không thành công !";

        static ERROR_LOADING_EMPLOYEE = "Tải danh sách nhân viên không thành công !";

        static ERROR_LOADING_DEPARTMENT = "Tải danh sách phòng ban không thành công !";

        static ERROR_LOADING_PROVINCE = "Tải danh sách tỉnh - thành phố không thành công !";
        static ERROR_LOADING_DISTRICT = "Tải danh sách quận - phường - huyện không thành công !";
        static ERROR_LOADING_WARD = "Tải danh sách phường - xã - thị trấn không thành công !";
    }

    static SweetAlert = class {
        static showDeactivateConfirmDialog() {
            return Swal.fire({
                icon: 'warning',
                text: 'Are you sure to deactivate the selected customer ?',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Yes, please deactivate this client !',
                cancelButtonText: 'Cancel',
            })
        }


        static showSuccessAlert(t) {
            Swal.fire({
                icon: 'success',
                title: t,
                position: 'top-end',
                showConfirmButton: false,
                timer: 1500
            })
        }

        static showErrorAlert(t) {
            Swal.fire({
                icon: 'error',
                title: 'Warning',
                text: t,
            })
        }

        static showError401() {
            Swal.fire({
                icon: 'error',
                title: 'Access Denied',
                text: 'Invalid credentials!',
            })
        }

        static showError403() {
            Swal.fire({
                icon: 'error',
                title: 'Access Denied',
                text: 'You are not authorized to perform this function!',
            })
        }

        static showError403VI() {
            Swal.fire({
                icon: 'error',
                title: 'TRUY CẬP BỊ TỪ CHỐI',
                text: 'Bạn không có quyền thực hiện chức năng này!',
            })
        }

        static showError500() {
            Swal.fire({
                icon: 'error',
                title: 'Access Denied',
                text: 'The server system is having problems or is not accessible.',
            })
        }

        static showError500Vi() {
            Swal.fire({
                icon: 'error',
                title: 'Truy cập bị từ chối',
                text: 'Lưu dữ liệu không thành công, vui lòng liên hệ với quản trị viên hệ thống.',
            })
        }
    }
    static IziToast = class {
        static showSuccessAlert(m) {
            iziToast.success({
                title: 'OK! ',
                position: 'topRight',
                timeout: 2500,
                message: m
            });
        }

        static showErrorAlertRight(m) {
            iziToast.error({
                title: 'Error! ',
                position: 'topRight',
                timeout: 2500,
                message: m
            });
        }


        static showErrorAlert(m) {
            iziToast.error({
                title: 'Error',
                position: 'topRight',
                timeout: 2500,
                message: m
            });
        }

        static showErrorAlertVi(m) {
            iziToast.error({
                title: 'Xin thông cảm!',
                position: 'topRight',
                timeout: 2500,
                message: m
            });
        }
    }
}


class EmployeeAvatar {
    constructor(id, fileName, fileFolder, fileUrl, fileType, cloudId, ts) {
        this.id = id;
        this.fileName = fileName;
        this.fileFolder = fileFolder;
        this.fileUrl = fileUrl;
        this.fileType = fileType;
        this.cloudId = cloudId;
        this.ts = ts;
    }
}

class LocationRegion {
    constructor(id, provinceId, provinceName, districtId, districtName, wardId, wardName, address) {
        this.id = id;
        this.provinceId = provinceId;
        this.provinceName = provinceName;
        this.districtId = districtId;
        this.districtName = districtName;
        this.wardId = wardId;
        this.wardName = wardName;
        this.address = address;
    }
}

class Employee {
    constructor(id, fullName, salary, experience, dateOfJoining, phone, locationRegion, department, employeeAvatar, position) {
        this.fullName = fullName;
        this.salary = salary;
        this.experience = experience;
        this.dateOfJoining = dateOfJoining;
        this.phone = phone;
        this.locationRegion = locationRegion;
        this.department = department;
        this.employeeAvatar = employeeAvatar;
        this.position = position;
    }
}

class Department {
    constructor(id, name) {
        this.id = id;
        this.name = name;
    }
}

// class Position {
//     constructor(id, code) {
//         this.id = id;
//         this.code = code;
//     }
// }

class Role {
    constructor(id, code, name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }
}
