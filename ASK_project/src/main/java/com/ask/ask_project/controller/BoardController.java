package com.ask.ask_project.controller;


import com.ask.ask_project.DTO.*;
import com.ask.ask_project.service.BoardService;
import com.ask.ask_project.service.S3UploaderService;
import com.ask.ask_project.service.S3UploaderService2;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
public class BoardController {

    @Autowired
    private BoardService boardService;
    private final S3UploaderService s3UploaderService;
    private final S3UploaderService2 s3UploaderService2;
    // 로그인
    @RequestMapping("login")
    public boolean login(@RequestBody UserDTO userDTO) {
        Emp_payDTO emp_payDTO = new Emp_payDTO();
        System.out.println("================================================");
        System.out.println("[ 로그인 ] 들어온 userDTO값: " + userDTO);
        System.out.println("================================================");
        int loginCheck = 0;
        try {
            loginCheck = boardService.loginCheck(userDTO);
            System.out.println("[ 로그인 ] 결과값 loginCheck : " + loginCheck);
            if (loginCheck == 1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 마스터 id 값 가져오기
    @RequestMapping("getMaster")
    public String getMaster(@RequestBody UserDTO userDTO) {
        System.out.println("================================================");
        System.out.println("[ getMaster ] 들어온 userDTO값: " + userDTO);
        System.out.println("================================================");
        try {
            String userId = boardService.getMaster(userDTO);
            System.out.println("[ getMaster ] 결과값 userId : " + userId);
            return userId;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // 회원가입
    @RequestMapping("joinProcess")
    public boolean joinProcess(@RequestBody MemberDTO memberDTO) {
        System.out.println("[ 회원가입 ] 들어온 data : " + memberDTO);
        try {
            // 관리자 계정 insert
            int check = boardService.insert_memberInfo(memberDTO);
            System.out.println("[ 회원가입 ] 결과값 check : " + check);
            // 사용자 관리 insert
            int check2 = boardService.insert_userInfo(memberDTO);
            System.out.println("[ 회원가입 ] 결과값 check2 : " + check2);
            if (check == 1 && check2 == 1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 회원가입 - 아이디 중복 체크
    @RequestMapping("checkId")
    public boolean checkId(@RequestBody MemberDTO memberDTO) {
        int checkNum = -1;
        System.out.println("================================================");
        System.out.println("[ 회원가입 - id중복체크 ] 들어온 memberDTO값 : " + memberDTO);
        System.out.println("================================================");
        try {
            checkNum = boardService.checkId(memberDTO);
            System.out.println("[ 회원가입 - id중복체크 ] 결과값 checkNum 값 : " + checkNum);
            if (checkNum == 0) {
                return true;
            } else {

                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    //-------------------------------- < 사용자관리 > ----------------------------------
    // 사용자관리 (create)
    @RequestMapping("createUser")
    public boolean createUser(@RequestBody UserDTO userDTO) {
        System.out.println("================================================");
        System.out.println("[ 사용자관리(create) ] 들어온 userDTO 값 : " + userDTO);
        System.out.println("================================================");
        try {
            int check = boardService.createUser(userDTO);
            System.out.println("[ 사용자관리(create) ] 결과값 check 값 : " + check);
            if (check == 1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //사용자관리 (read)
    @RequestMapping("readUser")
    public List<UserDTO> readUser(@RequestBody UserDTO userDTO) {
        System.out.println("================================================");
        System.out.println("[ 사용자관리(Read) ] 들어온 userDTO값: " + userDTO);
        System.out.println("================================================");
        try {
            List<UserDTO> userList = boardService.readUser(userDTO);
            System.out.println("[ 사용자관리(Read) ] 결과값 userList 값 : " + userList);
            if (userList != null) {
                return userList;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //사용자관리 (update)
    @RequestMapping("updateUser")
    public boolean updateUser(@RequestBody UserDTO userDTO) {
        System.out.println("================================================");
        System.out.println("[ 사용자관리 (update) ] 들어온 userDTO 값: " + userDTO);
        System.out.println("================================================");
        try {
            int check = boardService.updateUser(userDTO);
            System.out.println("[ 사용자관리 (update) ] 결과값 check 값 : " + check);
            if (check > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 수정 눌러서 해당 id의 update창만 보기
    @RequestMapping("updateUserModal")
    public List<UserDTO> updateUserModal(@RequestBody UserDTO userDTO) {
        System.out.println("================================================");
        System.out.println("[ 사용자관리 (updateUserModal) ] 들어온 userDTO 값: " + userDTO);
        System.out.println("================================================");
        try {
            List<UserDTO> userList = boardService.updateUserModal(userDTO);
            System.out.println("[ 사용자관리 (updateUserModal) ] 결과값 userList 값 : " + userList);
            if (userList != null) {
                return userList;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    //사용자관리 (delete)
    @RequestMapping("deleteUser")
    public boolean deleteUser(@RequestBody UserDTO userDTO) {
        System.out.println("================================================");
        System.out.println("[ 사용자관리(delete) ] 들어온 userDTO 값: " + userDTO);
        System.out.println("================================================");
        try {
            String[] list = userDTO.getDeleteList();
            int check = 0;
            for (int i = 0; i < list.length; i++) {
                check = boardService.deleteUser(list[i]);
                System.out.println("[ 사용자관리(delete) ] 결과값 check 값 : " + check);
                if (check == 0) {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //-------------------------------- < 모바일관리 > ----------------------------------
    // CRUD (create, read, update, delete)
    // 모바일관리 - 모바일계정 정보 등록(create)
    @RequestMapping("createMobile")
    public boolean createMobile(@RequestBody MbUserDTO mbUserDTO) {
        System.out.println("================================================");
        System.out.println("[ 모바일관리 - 모바일계정 정보 등록(create) ] 들어온 mbUserDTO 값 : " + mbUserDTO);
        System.out.println("================================================");
        try {
            int check = boardService.createMobile(mbUserDTO);
            System.out.println("[ 모바일관리 - 모바일계정 정보 등록(create) ] 결과값 check 값 : " + check);
            if (check == 1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    // 모바일관리 - 모바일계정 정보 불러오기(read)
    @RequestMapping("readMobile")
    public ArrayList<MbUserDTO> readMobile(@RequestBody MbUserDTO mbUserDTO) {
        System.out.println("================================================");
        System.out.println("[ 모바일관리 - 모바일계정 정보 불러오기(read) ] 들어온 mbUserDTO 값: " + mbUserDTO);
        System.out.println("================================================");
        try {
            ArrayList<MbUserDTO> mbList = boardService.readMobile(mbUserDTO);
            System.out.println("[ 모바일관리 - 모바일계정 정보 불러오기(read) ] 결과값 mbList 값 : " + mbList);
            if (mbList.size() == 0) {
                return null;
            }
            return mbList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // 모바일관리 - 모바일계정 정보 (update)
    @RequestMapping("updateMobile")
    public boolean updateMobile(@RequestBody MbUserDTO mbUserDTO) {
        System.out.println("================================================");
        System.out.println("[ 모바일관리 - 모바일계정 정보 (update) ] 들어온 mbUserDTO 값: " + mbUserDTO);
        System.out.println("================================================");
        try {
            int check = boardService.updateMobile(mbUserDTO);
            System.out.println("[ 모바일관리 - 모바일계정 정보 (update) ] 결과값 check 값 : " + check);
            if (check > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    // 모바일관리 - 모바일계정 정보 수정 - 모달 창에 업데이트 할 값 보내기
    @RequestMapping("updateMobileModal")
    public ArrayList<MbUserDTO> updateMobileModal(@RequestBody MbUserDTO mbUserDTO) {
        // param : code
        System.out.println("================================================");
        System.out.println("[ 모바일관리 (updateMobileModal) ] 들어온 mbUserDTO 값: " + mbUserDTO);
        System.out.println("================================================");
        try {
            ArrayList<MbUserDTO> mobileList = boardService.updateMobileModal(mbUserDTO);
            System.out.println("[ 모바일관리 (updateMobileModal) ] 결과값 mobileList 값 : " + mobileList);
            if (mobileList != null) {
                return mobileList;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // 모바일관리 - 모바일계정 정보 삭제(delete)
    @RequestMapping("deleteMobile")
    public boolean deleteMobile(@RequestBody MbUserDTO mbUserDTO) {
        System.out.println("================================================");
        System.out.println("[ 모바일관리 - 모바일계정 정보 삭제(delete) ] 들어온 userDTO 값: " + mbUserDTO);
        System.out.println("================================================");
        try {
            String[] list = mbUserDTO.getDeleteList();
            int check = 0;
            for (int i = 0; i < list.length; i++) {
                check = boardService.deleteMobile(list[i]);
                System.out.println("[ 모바일관리 - 모바일계정 정보 삭제(delete) ] 결과값 check 값 : " + check);
                if (check == 0) {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    //-------------------------------- < 회사 설정 > ----------------------------------
    // 회사 설정 - 회사 등록(create)
    @RequestMapping("createCompany")
    public boolean createCompany(@RequestBody CompanyDTO companyDTO) {
        System.out.println("================================================");
        System.out.println("[ 회사설정 - 회사등록 ] 들어온 companyDTO값: " + companyDTO);
        System.out.println("================================================");
        try {
            int check = boardService.createCompany(companyDTO);
            System.out.println("[ 회사설정 - 회사등록 ] 결과값 check 값 : " + check);
            if (check == 1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 회사 설정 - 회사정보 보기(read)
    @RequestMapping("readCompany")
    public ArrayList<CompanyDTO> readCompany(@RequestBody CompanyDTO companyDTO) {
        System.out.println("================================================");
        System.out.println("[ 회사설정 - 회사정보 보기 ] 들어온 companyDTO값: " + companyDTO);
        System.out.println("================================================");
        ArrayList<CompanyDTO> companyInfo = new ArrayList<>();
        try {
            companyInfo = boardService.readCompany(companyDTO);
            return companyInfo;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // 회사 설정 - 회사정보 수정(update)
    @RequestMapping("updateCompany")
    public boolean updateCompany(@RequestBody CompanyDTO companyDTO) {
        System.out.println("================================================");
        System.out.println("[ 회사설정 - 회사정보 수정 ] 들어온 companyDTO값: " + companyDTO);
        System.out.println("================================================");
        int check = 0;
        try {
            check = boardService.updateCompany(companyDTO);
            System.out.println("[ 회사설정 - 회사등록 ] 결과값 check 값 : " + check);
            if (check == 1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 회사 설정 - 회사정보 삭제(delete)
    @RequestMapping("deleteCompany")
    public boolean deleteCompany(@RequestBody CompanyDTO companyDTO) {
        System.out.println("================================================");
        System.out.println("[ 회사설정 - 회사정보 삭제 ] 들어온 companyDTO값: " + companyDTO);
        System.out.println("================================================");
        int check = 0;
        try {
            check = boardService.deleteCompany(companyDTO);
            System.out.println("[ 회사설정 - 회사등록 ] 결과값 check 값 : " + check);
            if (check == 1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    //-------------------------------- < 부서관리 > ----------------------------------
    // CRD (create, read, delete)
    // 부서관리 - 부서정보 등록(create)
    @RequestMapping("createDep")
    public boolean createDep(@RequestBody DepDTO depDTO) {
        // param data : compCode, depCode, depName, depDetail
        System.out.println("================================================");
        System.out.println("[ 부서관리 - 부서정보 등록 ] 들어온 depDTO 값: " + depDTO);
        System.out.println("================================================");
        try {
            int check = boardService.createDep(depDTO);
            System.out.println("[ 부서관리 - 부서정보 등록 ] 결과값 check 값 : " + check);
            if (check == 1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 부서관리 - 부서정보 불러오기(read)
    @RequestMapping("readDep")
    public ArrayList<DepDTO> readDep(@RequestBody DepDTO depDTO) {
        // param data : compCode
        System.out.println("================================================");
        System.out.println("[ 부서관리 - 부서정보 불러오기(read) ] 들어온 depDTO 값: " + depDTO);
        System.out.println("================================================");
        try {
            ArrayList<DepDTO> depList = boardService.readDep(depDTO);
            System.out.println("[ 부서관리 - 부서정보 불러오기(read) ] 결과값 check 값 : " + depList);
            return depList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // 부서관리 - 부서정보 삭제(delete)
    @RequestMapping("deleteDep")
    public boolean deleteDep(@RequestBody DepDTO depDTO) {
        // param data : compCode, depCode
        System.out.println("================================================");
        System.out.println("[ 부서관리 - 부서정보 삭제(delete) ] 들어온 depDTO 값: " + depDTO);
        System.out.println("================================================");
        try {
            int check = boardService.deleteDep(depDTO);
            System.out.println("[ 부서관리 - 부서정보 삭제(delete) ] 결과값 check 값 : " + check);
            if (check == 1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //-------------------------------- < 사원관리 > ----------------------------------
    // CRUD (create, read, update, delete)
    // 사원관리 - 사원정보 등록(create)
    @RequestMapping("createEmp")
    public boolean createEmp(@RequestBody EmpDTO empDTO) {
        // param data : 모든 데이터(compCode는 session값으로 받는다.) -
        System.out.println("================================================");
        System.out.println("[ 사원관리 - 사원정보 등록(create) ] 들어온 empDTO 값: " + empDTO);
        System.out.println("================================================");

        // [주민등록번호] 합치기( 주민번호 앞 + 주민번호 뒷 )
        empDTO.setEmpSSN(empDTO.getEmpFirstSSN() + "-" + empDTO.getEmpSecondSSN());
        System.out.println("완성된 주민등록번호: " + empDTO.getEmpSSN());

        // [입사일] 년/월/일 합치기
        empDTO.setEmpStart(empDTO.getEmpStartYear() + "-" + empDTO.getEmpStartMonth() + "-" + empDTO.getEmpStartDay());
        System.out.println("완성된 입사일: " + empDTO.getEmpStart());

        // [퇴사일] 년/월/일 합치기
        if (empDTO.getEmpEndYear() != null && empDTO.getEmpEndMonth() != null && empDTO.getEmpEndDay() != null) {
            empDTO.setEmpEnd(empDTO.getEmpEndYear() + "-" + empDTO.getEmpEndMonth() + "-" + empDTO.getEmpEndDay());
            System.out.println("완성된 퇴사일: " + empDTO.getEmpEnd());
        }
        // 잔여 휴가 값 넣기
        empDTO.setRemindVacation(empDTO.getTotalVacation());
        try {
            int check = boardService.createEmp(empDTO);
            System.out.println("[ 사원관리 - 사원정보 등록(create) ] 결과값 check 값 : " + check);
            if (check == 1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 사원관리 - 사원정보 불어오기(read)
    @RequestMapping("readEmp")
    public ArrayList<EmpDTO> readEmp(@RequestBody EmpDTO empDTO) {
        // param data : compCode
        System.out.println("================================================");
        System.out.println("[ 사원관리 - 사원정보 불러오기(read) ] 들어온 empDTO 값: " + empDTO);
        System.out.println("================================================");
        try {
            ArrayList<EmpDTO> empList = boardService.readEmp(empDTO);
            System.out.println("[ 사원관리 - 사원정보 불어오기(read) ] 결과값 empList 값 : " + empList);
            if (empList == null) {
                return null;
            }
            return empList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // 사원관리 - 사원정보 수정(update)
    @RequestMapping("updateEmp")
    public boolean updateEmp(@RequestBody EmpDTO empDTO) {
        // param data : emp의 거의 모든 데이터(compCode는 session값으로 받는다.)
        System.out.println("================================================");
        System.out.println("[ 사원관리 - 사원정보 수정(update) ] 들어온 empDTO 값: " + empDTO);
        System.out.println("================================================");

        // [주민등록번호] 합치기( 주민번호 앞 + 주민번호 뒷 )
        empDTO.setEmpSSN(empDTO.getEmpFirstSSN() + "-" + empDTO.getEmpSecondSSN());
        System.out.println("완성된 주민등록번호: " + empDTO.getEmpSSN());

        // [입사일] 년/월/일 합치기
        empDTO.setEmpStart(empDTO.getEmpStartYear() + "-" + empDTO.getEmpStartMonth() + "-" + empDTO.getEmpStartDay());
        System.out.println("완성된 입사일: " + empDTO.getEmpStart());

        // [퇴사일] 년/월/일 합치기
        if (empDTO.getEmpEndYear() != null) {
            empDTO.setEmpEnd(empDTO.getEmpEndYear() + "-" + empDTO.getEmpEndMonth() + "-" + empDTO.getEmpEndDay());
            System.out.println("완성된 퇴사일: " + empDTO.getEmpEnd());
        }


        try {
            int check = boardService.updateEmp(empDTO);
            System.out.println("[ 사원관리 - 사원정보 수정(update) ] 결과값 check 값 : " + check);
            if (check == 1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 사원관리 - 사원정보 수정모달(updateEmpPayModal)
    @RequestMapping("updateEmpModal")
    public ArrayList<EmpDTO> updateEmpModal(@RequestBody EmpDTO empDTO) {
        // param : empId
        System.out.println("================================================");
        System.out.println("[ 사원관리 - 사원정보 수정모달(updateEmpPayModal) ] 들어온 empDTO 값: " + empDTO);
        System.out.println("================================================");
        try {
            ArrayList<EmpDTO> empList = boardService.updateEmpModal(empDTO);
            System.out.println("[ 사원관리 - 사원정보 수정모달(updateEmpPayModal) ] 결과값 empList 값 : " + empList);
            if (empList != null) {
                return empList;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // 사원관리 - 사원정보 삭제(delete)
    @RequestMapping("deleteEmp")
    public boolean deleteEmp(@RequestBody EmpDTO empDTO) {
        // param data : empId
        System.out.println("================================================");
        System.out.println("[ 사원관리 - 사원정보 삭제(delete) ] 들어온 empDTO 값: " + empDTO);
        System.out.println("================================================");
        try {
            int check = boardService.deleteEmp(empDTO);
            System.out.println("[ 사원관리 - 사원정보 삭제(delete) ] 결과값 check 값 : " + check);
            if (check == 1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //-------------------------------- < 수당관리 > ----------------------------------
    // CRUD (create, read, update, delete)
    // 수당관리 - 수당항목 등록(create) - 배열로 받는다고 생각하고 만들기
    @RequestMapping("createEmpPay")
    public boolean createEmpPay(@RequestBody Emp_payDTO emp_payDTO) {
        // param data : 모든 데이터
        System.out.println("================================================");
        System.out.println("[ 수당관리 - 수당항목 등록(create) ] 들어온 emp_payDTO 값: " + emp_payDTO);
        System.out.println("================================================");
        try {
            int check = 0;
            System.out.println("[ 수당관리 - 수당항목 등록(create) ] 결과값 paylist 값 : " + check);
            if (check == 0) {
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 수당관리 - 수당항목 불러오기(read)
    @RequestMapping("readEmpPay")
    public ArrayList<Emp_payDTO> readEmpPay(@RequestBody Emp_payDTO emp_payDTO) {
        // param data : compCode
        System.out.println("================================================");
        System.out.println("[ 수당관리 - 수당항목 불러오기(read) ] 들어온 emp_payDTO 값: " + emp_payDTO);
        System.out.println("================================================");
        try {
            ArrayList<Emp_payDTO> paylist = boardService.readEmpPay(emp_payDTO);
            System.out.println("[ 수당관리 - 수당항목 불러오기(read) ] 결과값 paylist 값 : " + paylist);
            if (paylist.size() == 0) {
                return null;
            }
            return paylist;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    // 수당관리 - 수당항목 수정(update)
    @RequestMapping("updateEmpPay")
    public boolean updateEmpPay(@RequestBody Emp_payDTO emp_payDTO) {
        // param data : 모든 데이터
        System.out.println("================================================");
        System.out.println("[ 수당관리 - 수당항목 수정(update) ] 들어온 emp_payDTO 값: " + emp_payDTO);
        System.out.println("================================================");
        try {
            int check = 0;
            check = boardService.updateEmpPay(emp_payDTO);
            System.out.println("[ 수당관리 - 수당항목 수정(update) ] 결과값 check 값 : " + check);
            if (check == 0) {
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 수당관리 - 수당항목 수정모달(updateEmpPayModal)
    @RequestMapping("updateEmpPayModal")
    public ArrayList<Emp_payDTO> updateEmpPayModal(@RequestBody Emp_payDTO emp_payDTO) {
        // param : empPayID
        System.out.println("================================================");
        System.out.println("[ 수당관리 - 수당항목 수정모달(updateEmpPayModal) ] 들어온 emp_payDTO 값: " + emp_payDTO);
        System.out.println("================================================");
        try {
            ArrayList<Emp_payDTO> empPayList = boardService.updateEmpPayModal(emp_payDTO);
            System.out.println("[ 수당관리 - 수당항목 수정모달(updateEmpPayModal) ] 결과값 empPayList 값 : " + empPayList);
            if (empPayList != null) {
                return empPayList;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // 수당관리 - 수당항목 삭제(delete)
    @RequestMapping("deleteEmpPay")
    public boolean deleteEmpPay(@RequestBody Emp_payDTO emp_payDTO) {
        // param data : deleteEmpPay(리스트형태) - 리스트 안에는 empPayId 가 있음
        System.out.println("================================================");
        System.out.println("[ 수당관리 - 수당항목 삭제(delete) ] 들어온 emp_payDTO 값: " + emp_payDTO);
        System.out.println("================================================");
        String[] deleteArr;
        try {
            // 1. 배열로 들어가
            deleteArr = emp_payDTO.getDeleteEmpPay();
            for (int i = 0; i < deleteArr.length; i++) {
                int check = boardService.deleteEmpPay(deleteArr[i]);
                System.out.println("[ 수당관리 - 수당항목 삭제(delete) ] 결과값 check 값: " + check);
                if (check == 0) {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    //-------------------------------- < 세금관리 > ----------------------------------
    // CRUD (create, read, update, delete)
    // 세금관리 - 세금정보 등록(create)
    @RequestMapping("createTax")
    public boolean createTax(@RequestBody TaxDTO taxDTO) {
        System.out.println("==================================");
        System.out.println("[ 세금관리 - 세금정보 등록(create) ] 들어온 taxDTO: " + taxDTO);
        System.out.println("==================================");
        try {
            int check = boardService.createTax(taxDTO);
            System.out.println("[ 세금관리 - 세금정보 등록(create) ] 결과값 check : " + check);
            if (check == 0) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    // 세금관리 - 세금정보 불러오기(read)
    @RequestMapping("readTax")
    public ArrayList<TaxDTO> readTax(@RequestBody TaxDTO taxDTO) {
        // param data : compCode
        System.out.println("================================================");
        System.out.println("[ 세금관리 - 세금정보 불러오기(read) ] 들어온 taxDTO 값: " + taxDTO);
        System.out.println("================================================");
        try {
            ArrayList<TaxDTO> taxList = boardService.readTax(taxDTO);
            System.out.println("[ 세금관리 - 세금정보 불러오기(read) ] 결과값 taxList 값 : " + taxList);
            if (taxList.size() == 0) {
                return null;
            }
            return taxList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    // 세금관리 - 세금정보 수정(update)
    @RequestMapping("updateTax")
    public boolean updateTax(@RequestBody TaxDTO taxDTO) {
        // param data : 모든 데이터
        System.out.println("================================================");
        System.out.println("[ 세금관리 - 세금정보 수정(update) ] 들어온 taxDTO 값: " + taxDTO);
        System.out.println("================================================");
        try {
            int check = boardService.updateTax(taxDTO);
            System.out.println("[ 세금관리 - 세금정보 수정(update) ] 결과값 check 값 : " + check);
            if (check == 0) {
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 세금관리 - 세금 수정모달(updateEmpPayModal)
    @RequestMapping("updateTaxModal")
    public ArrayList<TaxDTO> updateTaxModal(@RequestBody TaxDTO taxDTO) {
        // param : taxInfoID
        System.out.println("================================================");
        System.out.println("[ 세금관리 - 세금 수정모달(updateEmpPayModal) ] 들어온 taxDTO 값: " + taxDTO);
        System.out.println("================================================");
        try {
            ArrayList<TaxDTO> taxList = boardService.updateTaxModal(taxDTO);
            System.out.println("[ 세금관리 - 세금 수정모달(updateEmpPayModal) ] 결과값 taxList 값 : " + taxList);
            if (taxList != null) {
                return taxList;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // 세금관리 - 세금정보 삭제(delete)
    @RequestMapping("deleteTax")
    public boolean deleteTax(@RequestBody TaxDTO taxDTO) {
        // param data : deleteEmpPay(리스트형태) - taxInfoID 가 리스트로 담겨있음.
        System.out.println("================================================");
        System.out.println("[ 수당관리 - 수당항목 삭제(delete) ] 들어온 taxDTO 값: " + taxDTO);
        System.out.println("================================================");
        String[] deleteArr;
        try {
            deleteArr = taxDTO.getDeleteTaxList();
            for (int i = 0; i < deleteArr.length; i++) {
                int check = boardService.deleteTax(deleteArr[i]);
                System.out.println("[ 수당관리 - 수당항목 삭제(delete) ] 결과값 check 값: " + check);
                if (check == 0) {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 근태관리 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ

    //휴가항목 등록 (create)
    //입력해야되는 데이터 : vactCode, vactName, vactDetail
    @RequestMapping("create_Vactcategory")
    public boolean create_vactcategory(@RequestBody Vact_CategoryDTO vact_categoryDTO) {
        System.out.println("==================================");
        System.out.println("[ 휴가항목등록 - 휴가항목 추가 ] 들어온 vact_categoryDTO값: " + vact_categoryDTO);
        System.out.println("==================================");
        try {
            int codecheck = boardService.checkVactCategory(vact_categoryDTO);
            System.out.println(codecheck);
            if (codecheck > 0) {
                return false;
            }
            int check = boardService.createVactCategory(vact_categoryDTO);
            System.out.println("[ 휴가항목등록 - 휴가항목 추가 ] 결과값 check 값 : " + check);
            if (check == 1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //휴가항목등록 마달창
    //compCode, deleteVactListId
    @RequestMapping("modal_Vactcategory")
    public List<Vact_CategoryDTO> modal_Vactcategory(@RequestBody Vact_CategoryDTO vact_categoryDTO) {
        System.out.println("==================================");
        System.out.println("[ 휴가항목등록 - 모달창 ] 들어온 vact_categoryDTO값: " + vact_categoryDTO);
        System.out.println("==================================");
        try {
            List<Vact_CategoryDTO> vactList = boardService.modalVactCetegory(vact_categoryDTO);
            System.out.println("모달창 결과값 " + vactList);
            if (vactList != null) {
                return vactList;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //휴가항목 등록 (read)
    //요청값 : compCode ( 회사코드 )
    //           리스트 (PK, AI)  회사코드   휴가코드    휴가명     휴가상세
    //return값 : vactNameListId, compCode, vactCode, vactName, vactDetail
    @RequestMapping("read_Vactcategory")
    public List<Vact_CategoryDTO> read_vactcategory(@RequestBody Vact_CategoryDTO vact_categoryDTO) {
        System.out.println("==================================");
        System.out.println("[ 휴가항목등록 - 휴가항목 리스트 ] 들어온 vact_categoryDTO값: " + vact_categoryDTO);
        System.out.println("==================================");
        try {
            List<Vact_CategoryDTO> vactList = boardService.readVactCategory(vact_categoryDTO);
            if (vactList != null) {
                return vactList;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //휴가항목 등록 (update)
    //요청값 : compCode, vactNameListId
    @RequestMapping("update_Vactcategory")
    public boolean update_Vactcategory(@RequestBody Vact_CategoryDTO vact_categoryDTO) { //front로 휴가코드, 마스터아이디 요청
        System.out.println("==================================");
        System.out.println("[ 휴가항목등록 - 휴가항목 수정 ] 들어온 vact_categoryDTO값: " + vact_categoryDTO);
        System.out.println("==================================");
        try {
            int codecheck = boardService.checkVactCategory(vact_categoryDTO);
            System.out.println(codecheck);
            if (codecheck > 0) {
                return false;
            }
            int check = boardService.updateVactCategory(vact_categoryDTO);
            System.out.println("[ 휴가항목등록 - 휴가항목 수정 ] 결과값 check 값 : " + check);
            if (check == 1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //휴가항목 등록 (delete)
    //요청값 : compCode, deleteVactListId (pk, ai)
    @RequestMapping("delete_Vactcategory")  //front로 DeleteVactCode, compCode 요청
    public boolean delete_Vactcategory(@RequestBody Vact_CategoryDTO vact_categoryDTO) {
        System.out.println("==================================");
        System.out.println("[ 휴가항목등록 - 휴가항목 삭제 ] 들어온 vact_categoryDTO값: " + vact_categoryDTO);
        System.out.println("==================================");
        try {
            int check = boardService.deleteVactCategory(vact_categoryDTO);
            System.out.println("[ 휴가항목등록 - 휴가항목 삭제 ] 결과값 check 값 : " + check);
            if (check == 0) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //휴가처리 (create)
    //              시작일           종료일     사원번호  사원명    부서명     휴가기간    휴가항목명   상세
    // 입력데이터 : vactStartDate, vactEndDate, empNum, empName, depName, vactPeriod, vactName, vactDetail
    @RequestMapping("create_VactDispose")
    public boolean create_VactDispose(@RequestBody Vact_disposeDTO vact_disposeDTO) {
        System.out.println("==================================");
        System.out.println("[ 휴가처리 -  추가 ] 들어온 vact_disposeDTO: " + vact_disposeDTO);
        System.out.println("==================================");
        try {
            int check = boardService.createVactDispose(vact_disposeDTO);
            System.out.println("[ 휴가처리 -  추가 ] 결과값 check 값 : " + check);
            if (check == 1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //휴가처리 (read) - 전체
    @RequestMapping("read_VactDispose")
    public List<Vact_disposeDTO> read_VactDispose(@RequestBody Vact_disposeDTO vact_dispose) {
        try {
            System.out.println("==================================");
            System.out.println("[ 휴가처리 ] 들어온 vact_dispose: " + vact_dispose);
            System.out.println("==================================");
            int check2 = boardService.updateRemindVact(vact_dispose);
            System.out.println("휴가보유현황 check값 확인" + check2);
            List<Vact_disposeDTO> VactDispose = boardService.readVactDispose(vact_dispose);
            if (VactDispose != null) {
                return VactDispose;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //휴가처리 (read) - 승인
    @RequestMapping("Approval_VactDispose")
    public List<Vact_disposeDTO> Approval_VactDispose(@RequestBody Vact_disposeDTO vact_dispose) {
        try {
            System.out.println("==================================");
            System.out.println("[ 휴가처리 ] 들어온 vact_dispose: " + vact_dispose);
            System.out.println("==================================");
            List<Vact_disposeDTO> VactDispose = boardService.Approval_VactDispose(vact_dispose);
            if (VactDispose != null) {
                return VactDispose;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //휴가처리 (read) - 요청
    @RequestMapping("request_VactDispose")
    public List<Vact_disposeDTO> request_VactDispose(@RequestBody Vact_disposeDTO vact_dispose) {
        try {
            System.out.println("==================================");
            System.out.println("[ 휴가처리 ] 들어온 vact_dispose: " + vact_dispose);
            System.out.println("==================================");
            List<Vact_disposeDTO> VactDispose = boardService.request_VactDispose(vact_dispose);
            if (VactDispose != null) {
                return VactDispose;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //휴가처리 (read) - 미승인
    @RequestMapping("process_VactDispose")
    public List<Vact_disposeDTO> process_VactDispose(@RequestBody Vact_disposeDTO vact_dispose) {
        try {
            System.out.println("==================================");
            System.out.println("[ 휴가처리 ] 들어온 vact_dispose: " + vact_dispose);
            System.out.println("==================================");
            List<Vact_disposeDTO> VactDispose = boardService.process_VactDispose(vact_dispose);
            if (VactDispose != null) {
                return VactDispose;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //휴가처리 - 휴가항목 모달창
    @RequestMapping("modal_VactDispose")
    public List<Vact_CategoryDTO> modal_VactDispose(@RequestBody Vact_CategoryDTO vact_categoryDTO) {
        try {
            System.out.println("==================================");
            System.out.println("[ 휴가처리 ] 들어온 vact_dispose: " + vact_categoryDTO);
            System.out.println("==================================");

            List<Vact_CategoryDTO> list = boardService.modalVactCategory(vact_categoryDTO);
            if (list != null) {
                return list;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //휴가처리 (update) - 권한 승인
    // 요청값 :  empName , empNum, disposeVactListId, vactState, vactNote
    @RequestMapping("authority_VactDispose")
    public boolean update_VactDispose(@RequestBody Vact_disposeDTO vact_disposeDTO) {
        System.out.println("==================================");
        System.out.println("[ 휴가처리 - 권한 승인 ] 들어온 vact_disposeDTO: " + vact_disposeDTO);
        System.out.println("==================================");

        try {
            Integer check1 = boardService.VactDispose(vact_disposeDTO); //잔여휴가 select 값
            System.out.println("휴가처리 권한승인 : vact_disposeDTO" + vact_disposeDTO);
            System.out.println("잔여휴가 select 값 : " + check1);
            if (vact_disposeDTO.getVactPeriod() > check1 || vact_disposeDTO.getVactPeriod() == 0) {
                return false;       //휴가신청일이 잔여휴가보다 크거나 잔여휴가가 0일때
            } else {
                System.out.println("여기까지들어옴");
                int check = boardService.updateVactDispose(vact_disposeDTO);
                System.out.println("보유휴가현황 업데이트 check값 :" + check);
                if (check == 1) {
                    int check2 = boardService.updateRemindVact(vact_disposeDTO);
                    System.out.println("휴가보유현황 check값 확인" + check2);
                }
                if (check == 1) {
                    return true;   //성공
                } else {
                    return false;   //실패
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //휴가 보유현황 (read)
    @RequestMapping("read_Vactlist")
    public List<EmpDTO> read_Vactlist(@RequestBody EmpDTO empDTO) {
        try {
            System.out.println("==================================");
            System.out.println("[ 휴가보유현황 ] 들어온 vact_listDTO: " + empDTO);
            System.out.println("==================================");
            List<EmpDTO> list = boardService.readVactlist(empDTO);
            if (list != null) {
                return list;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //휴가처리 상세정보 모달창
    @RequestMapping("modaldetail_Vactlist")
    public List<Vact_disposeDTO> modaldetail_Vactlist(@RequestBody Vact_disposeDTO vact_disposeDTO) {
        try {
            System.out.println("==================================");
            System.out.println("[ 휴가 보유현황 모달창 디테일 ] vact_disposeDTO: " + vact_disposeDTO);
            System.out.println("==================================");
            List<Vact_disposeDTO> list = boardService.modaldetailVactlist(vact_disposeDTO);
            if (list != null) {
                return list;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //승인된것만
    //휴가 보유현황 모달창
    //요청값 :  ,  empName , empNum ;
    @RequestMapping("modal_Vaclist")
    public List<Vact_disposeDTO> modal_Vaclist(@RequestBody Vact_disposeDTO vact_disposeDTO) {
        try {
            System.out.println("==================================");
            System.out.println("[ 휴가 보유현황 모달창 ] 들어온 empDTO: " + vact_disposeDTO);
            System.out.println("==================================");
            List<Vact_disposeDTO> list = boardService.modalVactlist(vact_disposeDTO);
            if (list != null) {
                return list;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //요청값 :  compCode, empName, empNum;
    @RequestMapping("update_Vactlist")
    public boolean update_Vactlist(@RequestBody EmpDTO empDTO) {
        System.out.println("==================================");
        System.out.println("[ 보유휴가현황 - 보유휴가현황 수정 ] 들어온 vact_listDTO: " + empDTO);
        System.out.println("==================================");
        try {
            int check = boardService.updateVactlist(empDTO);
            System.out.println("보유휴가현황 업데이트 check값 :" + check);
            if (check == 1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ모바일 출퇴근ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
    //출퇴근 관리 (출근 요청)
    @RequestMapping("start_inOutInfo")
    public boolean create_inOutInfo(@RequestBody InOut_infoDTO inOut_infoDTO) {
        System.out.println("==================================");
        System.out.println("[ 출퇴근관리 -  추가 ] 들어온 inOut_infoDTO: " + inOut_infoDTO);
        System.out.println("==================================");
        try {
            int check = boardService.createinOut_info(inOut_infoDTO);
            System.out.println("[ 출퇴근관리 -  추가 ] 들어온 inOut_infoDTO: " + check);
            if (check == 1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //출퇴근 관리 (퇴근 요청)
    @RequestMapping("end_inOutInfo")
    public boolean createEnd_inOutInfo(@RequestBody InOut_infoDTO inOut_infoDTO) {
        System.out.println("==================================");
        System.out.println("[ 출퇴근관리 -  퇴근요청 ] 들어온 inOut_infoDTO: " + inOut_infoDTO);
        System.out.println("==================================");
        try {
            int check = boardService.createEnd_inOutInfo(inOut_infoDTO);
            System.out.println("출퇴근관리 업데이트 check값 :" + check);
            if (check == 1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //모바일 출퇴근관리 (read)
    @RequestMapping("readMb_inOutInfo")
    public List<InOut_infoDTO> readMb_inOutInfo(@RequestBody InOut_infoDTO inOut_infoDTO) {
        try {
            System.out.println("==================================");
            System.out.println("[ 출퇴근관리 모바일 read ] 들어온 inOut_infoDTO: " + inOut_infoDTO);
            System.out.println("==================================");
            List<InOut_infoDTO> list = boardService.readMb_inOutInfo(inOut_infoDTO);
            if (list != null) {
                return list;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
    //출퇴근 관리 (create)
    @RequestMapping("create_inOutInfo")
    public boolean createAdd_inOutInfo(@RequestBody InOut_infoDTO inOut_infoDTO) {
        System.out.println("==================================");
        System.out.println("[ 출퇴근관리 -  추가 ] 들어온 inOut_infoDTO: " + inOut_infoDTO);
        System.out.println("==================================");
        try {
            int check = boardService.createAdd_inOutInfo(inOut_infoDTO);
            System.out.println("[ 출퇴근관리 -  추가 ] 들어온 inOut_infoDTO: " + check);


            if (check == 1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //출퇴근 관리 (read)
    //          리스트 PK, AI  회사코드   사원코드  사원명    부서명    출근시간     퇴근시간    초과근무
    //return값 : inOutListId, compCode, empCode, empName, depName, inOutStart, inOutEnd, inOutOver
    @RequestMapping("read_inOutInfo")
    public List<InOut_infoDTO> read_inOutInfo(@RequestBody InOut_infoDTO inOut_infoDTO) {
        try {

            System.out.println("==================================");
            System.out.println("[ 출퇴근관리 출근승인 ] 들어온 inOut_infoDTO: " + inOut_infoDTO);
            System.out.println("==================================");
            int check2 = boardService.OvertimeNightUpdate(inOut_infoDTO);
            System.out.println("출퇴근관리 야간근무 업데이트 check값 " + check2);
            int check = boardService.OvertimeUpdate(inOut_infoDTO);
            System.out.println("출퇴근관리 초과근무 업데이트 check값 " + check);


            List<InOut_infoDTO> list = boardService.readinout_info(inOut_infoDTO);
            if (list != null) {
                return list;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //출퇴근관리 모달
    @RequestMapping("modal_inOutInfo")
    public List<InOut_infoDTO> modal_inOutInfo(@RequestBody InOut_infoDTO inOut_infoDTO) {
        try {
            System.out.println("==================================");
            System.out.println("[ 출퇴근관리 모달창 ] 들어온 inOut_infoDTO: " + inOut_infoDTO);
            System.out.println("==================================");
            List<InOut_infoDTO> list = boardService.modal_inOutInfo(inOut_infoDTO);
            System.out.println("modal_inOutInfo 출력값 : " + list);
            if (list != null) {
                return list;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //출퇴근관리 (update)
    //요청값 : compCode (회사코드), inOutListId ( 리스트 PK, AI  )
    @RequestMapping("update_inOutInfo")
    public boolean update_inOutInfo(@RequestBody InOut_infoDTO inOut_infoDTO) {
        System.out.println("==================================");
        System.out.println("[ 출퇴근관리 -  수정 ] 들어온 inOut_infoDTO: " + inOut_infoDTO);
        System.out.println("==================================");
        try {
            int check = boardService.updateinOut_info(inOut_infoDTO);
            System.out.println("출퇴근관리 업데이트 check값 :" + check);
            if (check == 1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //출퇴근관리 (delete)
    //요청값 : compCode (회사코드), getDeleteList (아이디)
    @RequestMapping("delete_inOutInfo")     //front로 getDeleteList, compCode 요청
    public boolean delete_inOutInfo(@RequestBody InOut_infoDTO inOut_infoDTO) {
        System.out.println("==================================");
        System.out.println("[ 출퇴근관리 -  삭제 ] 들어온 inOut_infoDTO: " + inOut_infoDTO);
        System.out.println("==================================");
        try {
            int check = boardService.deleteinOut_info(inOut_infoDTO);
            System.out.println("[ 출퇴근관리 - 삭제 ] 결과값 check 값 : " + check);
            if (check == 1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //출퇴근현황 ( search)
    //요청값 = compCode(회사코드), empName (이름), startDate (시작날짜), endDate(종료날짜)
    @RequestMapping("search_inOutInfo")
    public List<InOut_infoDTO> search_inOutInfo(@RequestBody InOut_infoDTO inOut_infoDTO) {
        try {
            System.out.println("==================================");
            System.out.println("[ 출퇴근현황 ] 들어온 inOut_infoDTO: " + inOut_infoDTO);
            System.out.println("==================================");
            if (inOut_infoDTO.getStartDate() == null && inOut_infoDTO.getEndDate() == null && inOut_infoDTO.getEmpName() != null) {
                List<InOut_infoDTO> searchName = boardService.searchInoutName(inOut_infoDTO);
                System.out.println("이름검색 : " + searchName);
                if (searchName != null) {
                    return searchName;
                } else {
                    return null;
                }

            }
            if (inOut_infoDTO.getEmpName() == null && inOut_infoDTO.getStartDate() != null && inOut_infoDTO.getEndDate() != null) {
                List<InOut_infoDTO> searchDate = boardService.searchInoutDate(inOut_infoDTO);
                System.out.println("날자 검색 ;" + searchDate);
                if (searchDate != null) {
                    return searchDate;
                } else {
                    return null;
                }
            }
            if (inOut_infoDTO.getStartDate() != null && inOut_infoDTO.getEmpName() != null && inOut_infoDTO.getEndDate() == null) {
                List<InOut_infoDTO> searchstartDate = boardService.searchInoutstartDate(inOut_infoDTO);
                System.out.println("시작날짜 + 이름만 검색" + searchstartDate);
                if (searchstartDate != null) {
                    return searchstartDate;
                } else {
                    return null;
                }
            }
            if (inOut_infoDTO.getStartDate() != null && inOut_infoDTO.getEndDate() == null && inOut_infoDTO.getEmpName() == null) {
                List<InOut_infoDTO> checklast = boardService.searchInoutlastDate(inOut_infoDTO);
                System.out.println("시작날짜 + 이름만 검색" + checklast);
                if (checklast != null) {
                    return checklast;
                } else {
                    return null;
                }
            }
            if (inOut_infoDTO.getEndDate() != null && inOut_infoDTO.getStartDate() == null && inOut_infoDTO.getEmpName() == null) {
                List<InOut_infoDTO> checklast = boardService.searchInoutendDate(inOut_infoDTO);
                System.out.println("종료날짜검색" + checklast);
                if (checklast != null) {
                    return checklast;
                } else {
                    return null;
                }
            }
            if (inOut_infoDTO.getEndDate() != null && inOut_infoDTO.getStartDate() == null && inOut_infoDTO.getEmpName() != null) {
                List<InOut_infoDTO> checklast = boardService.searchInoutEndNameDate(inOut_infoDTO);
                System.out.println("종료날짜 + 이름" + checklast);
                if (checklast != null) {
                    return checklast;
                } else {
                    return null;
                }
            }
            List<InOut_infoDTO> list = boardService.searchInout(inOut_infoDTO);
            System.out.println("둘다검색" + list);
            if (list != null) {
                return list;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    //-------------------------------- < 일용직관리[일용직등록] > ----------------------------------
    // CRUD (create, read, update, delete)
    // 일용직관리[일용직등록] - 일용직정보 등록(create)
    @RequestMapping("createDailyEmp")
    public boolean createDailyEmp(@RequestBody DailyEmpDTO dailyEmpDTO){
        // param : 모든 데이터
        System.out.println("==================================");
        System.out.println("[ 일용직관리 - 일용직정보 등록(create) ] 들어온 dailyEmpDTO 값 : " + dailyEmpDTO);
        System.out.println("==================================");

        if(dailyEmpDTO.getDailyStart().contains("null") || dailyEmpDTO.getDailyStart().contains("undefined")){
            dailyEmpDTO.setDailyStart(null);
            System.out.println("start: " + dailyEmpDTO.getDailyStart());
        }
        if(dailyEmpDTO.getDailyEnd().contains("null") || dailyEmpDTO.getDailyEnd().contains("undefined")) {
            dailyEmpDTO.setDailyEnd(null);
            System.out.println("end: " + dailyEmpDTO.getDailyEnd());
        }
        if(dailyEmpDTO.getDailySsn().contains("null") || dailyEmpDTO.getDailySsn().contains("undefined")){
            dailyEmpDTO.setDailySsn(null);
            System.out.println("ssn: " + dailyEmpDTO.getDailySsn());
        }

        try {
            // 중복값 확인
            int checkDailyEmp = boardService.getDailyCode(dailyEmpDTO);
            System.out.println("일용직 코드 중복값 확인: " +  checkDailyEmp);
            if(checkDailyEmp > 0){
                System.out.println("----- 중복된 코드입니다. -----");
                return false;
            }

            int check = boardService.createDailyEmp(dailyEmpDTO);
            System.out.println("[ 일용직관리 - 일용직정보 등록(create) ] 결과값 check 값 : " + check);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping("readDailyEmp")
    // 일용직관리[일용직등록] - 일용직정보 불러오기(read)
    public ArrayList<DailyEmpDTO> readDailyEmp(@RequestBody DailyEmpDTO dailyEmpDTO){
        // param : compCode
        System.out.println("==================================");
        System.out.println("[ 일용직관리 - 일용직정보 불러오기(read) ] 들어온 dailyEmpDTO 값 : " + dailyEmpDTO);
        System.out.println("==================================");
        try {
            ArrayList<DailyEmpDTO> dailyEmpList = boardService.readDailyEmp(dailyEmpDTO);
            System.out.println("[ 일용직관리 - 일용직정보 불러오기(read) ] 결과값 dailyEmpList 값 : " + dailyEmpList);
            if(dailyEmpList == null){
                return null;
            }
            return dailyEmpList;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    // 일용직관리[일용직등록] - 일용직정보 수정(update)
    @RequestMapping("updateDailyEmp")
    public boolean updateDailyEmp(@RequestBody DailyEmpDTO dailyEmpDTO){
        // param : .dailyId, 모든 데이터, 중복 확인을 위한 compCode
        System.out.println("==================================");
        System.out.println("[ 일용직관리 - 일용직정보 수정(update) ] 들어온 dailyEmpDTO 값 : " + dailyEmpDTO);
        System.out.println("==================================");

        if(dailyEmpDTO.getDailyStart().contains("null")){
            dailyEmpDTO.setDailyStart(null);
            System.out.println("start: " + dailyEmpDTO.getDailyStart());
        }
        if(dailyEmpDTO.getDailyEnd().contains("null")) {
            dailyEmpDTO.setDailyEnd(null);
            System.out.println("end: " + dailyEmpDTO.getDailyEnd());
        }
        if(dailyEmpDTO.getDailySsn().contains("null")){
            dailyEmpDTO.setDailySsn(null);
            System.out.println("ssn: " + dailyEmpDTO.getDailySsn());
        }

        try {
            // 중복값 확인(param : dailyId, dailyCode)
            int checkDailyEmp = boardService.getUpdateDailyCode(dailyEmpDTO);
            System.out.println("[ 일용직관리 - 일용직정보 중복처리 ] 중복된 값 : " + checkDailyEmp);
            if(checkDailyEmp > 0){
                System.out.println("--------- 이미 사용중인 일용직 코드 입니다. ---------");
                return false;
            }

            int check = boardService.updateDailyEmp(dailyEmpDTO);
            System.out.println("[ 일용직관리 - 일용직정보 수정(update)  ] 결과값 check 값 : " + check);
            if(check == 0){
                System.out.println("--------- [check==0] SQL문은 작동했으나 반환값이 0입니다. ---------");
                return false;
            }
            System.out.println("--------- 일용직 정보 수정 성공하였습니다. ---------");
            return true;
        }catch (Exception e){
            System.out.println("--------- [예외] 일용직 정보 수정 실패하였습니다. ---------");
            e.printStackTrace();
            return false;
        }
    }

    // 일용직관리[일용직등록] - 일용직정보 수정 모달
    @RequestMapping("updateDailyEmpModal")
    public ArrayList<DailyEmpDTO> updateDailyEmpModal(@RequestBody DailyEmpDTO dailyEmpDTO){
        // param : dailyId
        System.out.println("==================================");
        System.out.println("[ 일용직관리 - 일용직정보 수정 모달 ] 들어온 dailyEmpDTO 값 : " + dailyEmpDTO);
        System.out.println("==================================");
        try {
            ArrayList<DailyEmpDTO> dailyEmpModal = boardService.updateDailyEmpModal(dailyEmpDTO);
            System.out.println("[ 일용직관리[일용직등록] - 일용직정보 수정 모달 ] 결과값 dailyEmpModal 값 : " + dailyEmpModal);
            if(dailyEmpDTO == null){
                return null;
            }
            return dailyEmpModal;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    // 일용직관리[일용직등록] - 일용직정보 삭제(delete)
    @RequestMapping("deleteDailyEmp")
    public boolean deleteDailyEmp(@RequestBody DailyEmpDTO dailyEmpDTO){
        // param : dailyId
        System.out.println("==================================");
        System.out.println("[ 일용직관리 - 일용직 정보 삭제(delete) ] 들어온 dailyEmpDTO 값 : " + dailyEmpDTO);
        System.out.println("==================================");
        try {
            int check = boardService.deleteDailyEmp(dailyEmpDTO);
            System.out.println("[ 일용직관리 - 일용직 정보 삭제(delete) ] 결과값 check 값 : " + check);
            if(check == 0){
                return false;
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    //-------------------------------- < 일용직관리[일용직 수당] > ----------------------------------
    // CRUD (create, read, update, delete)
    // daily_pay
    // 일용직관리[일용직 수당] - 수당 등록
    @RequestMapping("createDailyPay")
    public boolean createDailyPay(@RequestBody DailyEmpPayDTO dailyEmpPayDTO){
        // param : 모든 데이터
        System.out.println("==================================");
        System.out.println("[ 일용직관리[일용직 수당] - 일용직 수당 등록 ] 들어온 dailyEmpPayDTO 값 : " + dailyEmpPayDTO);
        System.out.println("==================================");
        try {
            // 중복값 확인
            int checkDPayCode = boardService.getDPayCode(dailyEmpPayDTO);
            if(checkDPayCode > 0){
                return false;
            }

            int check = boardService.createDailyPay(dailyEmpPayDTO);
            System.out.println("[ 일용직관리[일용직 수당] - 일용직 수당 등록 ] 결과값 check 값 : " + check);
            if(check == 0){
                return false;
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    // 일용직관리[일용직 수당] - 수당 불러오기
    @RequestMapping("readDailyPay")
    public ArrayList<DailyEmpPayDTO> readDailyPay(@RequestBody DailyEmpPayDTO dailyEmpPayDTO){
        // param : compCode
        System.out.println("==================================");
        System.out.println("[ 일용직관리[일용직 수당] - 수당 불러오기 ] 들어온 dailyEmpPayDTO 값 : " + dailyEmpPayDTO);
        System.out.println("==================================");
        try {
            ArrayList<DailyEmpPayDTO> dailyEPList = boardService.readDailyPay(dailyEmpPayDTO);
            System.out.println("[ 일용직관리[일용직 수당] - 수당 불러오기 ] 결과값 dailyEPList 값 : " + dailyEPList);
            if(dailyEPList == null){
                return null;
            }
            return dailyEPList;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    // 일용직관리[수당등록] - 일용직 비과세 정보 가져오기
    @RequestMapping("readDailyTaxFree")
    public ArrayList<DailyTaxFreeDTO> readDailyTaxFree(){
        // param : 없음
        DailyTaxFreeDTO dailyTaxFreeDTO = new DailyTaxFreeDTO();
        try {
            ArrayList<DailyTaxFreeDTO> taxFreeList = boardService.readDailyTaxFree(dailyTaxFreeDTO);
            System.out.println("[ 일용직관리[수당등록] - 일용직 비과세 정보 가져오기 ] 결과값 taxFreeList 값 : " + taxFreeList);
            if(taxFreeList == null){
                return null;
            }
            return taxFreeList;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    // 일용직관리[일용직 수당] - 수당 수정
    @RequestMapping("updateDailyPay")
    public boolean updateDailyPay(@RequestBody DailyEmpPayDTO dailyEmpPayDTO){
        // param : dailyPayId
        System.out.println("==================================");
        System.out.println("[ 일용직관리[일용직 수당] - 수당 수정 ] 들어온 dailyEmpPayDTO 값 : " + dailyEmpPayDTO);
        System.out.println("==================================");
        try {
            // 중복값 확인
            int checkDPayCode = boardService.getDPayCode(dailyEmpPayDTO);
            if(checkDPayCode > 0){
                System.out.println("------ 중복 되었습니다. ------");
                return false;
            }

            int check = boardService.updateDailyPay(dailyEmpPayDTO);
            System.out.println("[ 일용직관리[일용직 수당] - 수당 수정 ] 결과값 check 값 : " + check);
            if(check == 0){
                return false;
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    // 일용직관리[일용직 수당] - 수당 수정 모달(modal)
    @RequestMapping("updateDailyPayModal")
    public ArrayList<DailyEmpPayDTO> updateDailyPayModal(@RequestBody DailyEmpPayDTO dailyEmpPayDTO){
        // param : dailyPayId
        System.out.println("==================================");
        System.out.println("[ 일용직관리[일용직 수당] - 수당 수정 모달(modal) ] ] 들어온 dailyEmpPayDTO 값 : " + dailyEmpPayDTO);
        System.out.println("==================================");
        try {
            ArrayList<DailyEmpPayDTO> dailyEPMList = boardService.updateDailyPayModal(dailyEmpPayDTO);
            System.out.println("[ 일용직관리[일용직 수당] - 수당 수정 모달(modal)] 결과값 dailyEPList 값 : " + dailyEPMList);
            if(dailyEPMList == null){
                return null;
            }
            return dailyEPMList;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    // 일용직관리[일용직 수당] - 수당 삭제
    @RequestMapping("deleteDailyPay")
    public boolean deleteDailyPay(@RequestBody DailyEmpPayDTO dailyEmpPayDTO){
        // param : dailyPayId
        System.out.println("==================================");
        System.out.println("[ 일용직관리[일용직 수당] - 수당 삭제 ] 들어온 dailyEmpPayDTO 값 : " + dailyEmpPayDTO);
        System.out.println("==================================");
        try {
            int check = boardService.deleteDailyPay(dailyEmpPayDTO);
            System.out.println("[ 일용직관리[일용직 수당] - 수당 삭제 ] 결과값 check 값 : " + check);
            if(check == 0) {
                return false;
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    //-------------------------------- < 일용직관리[출퇴근관리] > ----------------------------------
    // daily_InOut
    // 일용직관리[출퇴근관리] - 일용직 정보 가져오기(read)
    @RequestMapping("searchDailyEmp")
    // 일용직관리[출퇴근관리] - 일용직 정보 가져오기(read) - 아마도 기존의 일용직 정보를 그대로 가져가도 될듯? 실험해보세요
    public ArrayList<DailyEmpDTO> searchDailyEmp(@RequestBody DailyEmpDTO dailyEmpDTO){
        // param : compCode
        System.out.println("==================================");
        System.out.println("[ 일용직관리[출퇴근관리] - 일용직정보 불러오기(read) ] 들어온 dailyEmpDTO 값 : " + dailyEmpDTO);
        System.out.println("==================================");
        try {
            ArrayList<DailyEmpDTO> dailyEmpList = boardService.searchDailyEmp(dailyEmpDTO);
            System.out.println("[ 일용직관리[출퇴근관리] - 일용직정보 불러오기(read) ] 결과값 dailyEmpList 값 : " + dailyEmpList);
            if(dailyEmpList == null){
                return null;
            }
            return dailyEmpList;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    // 일용직관리[출퇴근관리] - 당일 날짜의 일용직 직원들의 정보를 세팅하여 보여준다.
    /*
    @RequestMapping("readDailyInOutMange")
    public ArrayList<DailyEmpDTO> readDailyInOutMange(@RequestBody DailyEmpDTO dailyEmpDTO){
        // param : dailyId
        System.out.println("==================================");
        System.out.println("[ 일용직관리[출퇴근관리] - 일용직정보 불러오기(read) ] 들어온 dailyEmpDTO 값 : " + dailyEmpDTO);
        System.out.println("==================================");
        try {
            ArrayList<DailyEmpDTO> dailyEmpList = boardService.searchDailyEmp(dailyEmpDTO);
            System.out.println("[ 일용직관리[출퇴근관리] - 일용직정보 불러오기(read) ] 결과값 dailyEmpList 값 : " + dailyEmpList);
            if(dailyEmpList == null){
                return null;
            }
            return dailyEmpList;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    */

    // ------------------------
    // 일용직관리[출퇴근관리] - 일용직 출퇴근정보 등록(create)
    @RequestMapping("createDailyInOut")
    public boolean createDailyInOut(@RequestBody DailyInOutDTO dailyInOutDTO){
        // param : 모든 데이터
        System.out.println("==================================");
        System.out.println("[ 일용직관리[출퇴근관리] - 일용직 출퇴근정보 등록(create) ] 들어온 dailyInOutDTO 값 : " + dailyInOutDTO);
        System.out.println("==================================");
        try {
            int check = boardService.createDailyInOut(dailyInOutDTO);
            System.out.println("[ 일용직관리[출퇴근관리] - 일용직 출퇴근정보 등록(create) ] 결과값 dailyInOutDTO 값 : " + dailyInOutDTO);
            if(check == 0){
                System.out.println("------------------- 출퇴근 정보 등록에 실패하였습니다. -------------------");
                return false;
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    // 일용직관리[출퇴근관리] - 일용직 출퇴근정보 불러오기(read)
    @RequestMapping("readDailyInOut")
    public ArrayList<DailyInOutDTO> readDailyInOut(@RequestBody DailyInOutDTO dailyInOutDTO){
        // param : compCode
        System.out.println("==================================");
        System.out.println("[일용직관리[출퇴근관리] - 일용직 출퇴근정보 불러오기(read) ] 들어온 dailyInOutDTO 값 : " + dailyInOutDTO);
        System.out.println("==================================");
        try {
            // 초과근무시간 update, 야간근무시간 update
            int checkNightTime = boardService.updateNightTime(dailyInOutDTO);
            System.out.println("[야간근무시간이 업데이트 되었습니다 ]checkNightTime : " + checkNightTime);
            int checkOverTime = boardService.updateOverTime(dailyInOutDTO);
            System.out.println("[초과근무시간이 업데이트 되었습니다 ]checkNightTime : " + checkOverTime);
            ArrayList<DailyInOutDTO> dailyInOutList = boardService.readDailyInOut(dailyInOutDTO);
            System.out.println("[ 일용직관리[출퇴근관리] - 일용직 출퇴근정보 불러오기(read) ] 결과값 dailyInOutList 값 : " + dailyInOutList);
            if(dailyInOutList == null){
                return null;
            }
            return dailyInOutList;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    // 일용직관리[출퇴근관리] - 일용직 출퇴근정보 수정(update)
    @RequestMapping("updateDailyInOut")
    public boolean updateDailyInOut(@RequestBody DailyInOutDTO dailyInOutDTO){
        // param : dailyListId
        System.out.println("==================================");
        System.out.println("[ 일용직관리[출퇴근관리] - 일용직 출퇴근정보 수정(update) ] 들어온 dailyInOutDTO 값 : " + dailyInOutDTO);
        System.out.println("==================================");
        try {
            // 중복값 확인
            int checkInOutDCode = boardService.getInOutDCode(dailyInOutDTO);
            if(checkInOutDCode > 0){
                return false;
            }

            int check = boardService.updateDailyInOut(dailyInOutDTO);
            System.out.println("[ 일용직관리[출퇴근관리] - 일용직 출퇴근정보 수정(update) ] 결과값 check 값 : " + check);
            if(check == 0){
                return false;
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    // 일용직관리[출퇴근관리] - 일용직 출퇴근정보 수정모달(modal)
    @RequestMapping("updateDailyInOutModal")
    public ArrayList<DailyInOutDTO> updateDailyInOutModal(@RequestBody DailyInOutDTO dailyInOutDTO){
        // param : dailyListId
        System.out.println("==================================");
        System.out.println("[ 일용직관리[출퇴근관리] - 일용직 출퇴근정보 수정모달(modal) ] 들어온 dailyInOutDTO 값 : " + dailyInOutDTO);
        System.out.println("==================================");

        try {
//            // 초과근무시간 update, 야간근무시간 update
//            int checkNightTime = boardService.updateNightTime(dailyInOutDTO);
//            System.out.println("[야간근무시간이 업데이트 되었습니다 ]checkNightTime : " + checkNightTime);
//            int checkOverTime = boardService.updateOverTime(dailyInOutDTO);
//            System.out.println("[초과근무시간이 업데이트 되었습니다 ]checkNightTime : " + checkOverTime);

            ArrayList<DailyInOutDTO> modalList = boardService.updateDailyInOutModal(dailyInOutDTO);
            System.out.println("[ 일용직관리[출퇴근관리] - 일용직 출퇴근정보 수정모달(modal) ] 결과값 modalList 값 : " + modalList);
            if(modalList == null){
                return null;
            }
            return modalList;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    // 일용직관리[출퇴근관리] - 일용직 출퇴근정보 삭제(delete)
    @RequestMapping("deleteDailyInOut")
    public boolean deleteDailyInOut(@RequestBody DailyInOutDTO dailyInOutDTO){
        // param : dailyListId
        System.out.println("==================================");
        System.out.println("[ 일용직관리[출퇴근관리] - 일용직 출퇴근정보 삭제(delete) ] 들어온 dailyInOutDTO 값 : " + dailyInOutDTO);
        System.out.println("==================================");
        try {
            int check = boardService.deleteDailyInOut(dailyInOutDTO);
            System.out.println("[ 일용직관리[출퇴근관리] - 일용직 출퇴근정보 삭제(delete) ] 결과값 check 값 : " + check);
            if(check == 0){
                return false;
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    //-------------------------------- < 일용직관리[출퇴근현황] > ----------------------------------
    @RequestMapping("searchDailyInOut")
    public ArrayList<DailyInOutDTO> searchDailyInOut(@RequestBody DailyInOutDTO dailyInOutDTO){
        System.out.println("==========================");
        System.out.println("일용직관리[출퇴근현황] 들어온 dailyInOutDTO 값 : " + dailyInOutDTO);
        System.out.println("==========================");
        // 시작 날짜, 종료 날짜, 이름
        ArrayList<DailyInOutDTO> searchList = new ArrayList<>();
        try {
            System.out.println("==========================");
            System.out.println("일용직관리[출퇴근현황] 들어온 dailyStartDate 값 : " + dailyInOutDTO.getDailyStartDate());
            System.out.println("일용직관리[출퇴근현황] 들어온 dailyEndDate 값 :" + dailyInOutDTO.getDailyEndDate());
            System.out.println("일용직관리[출퇴근현황] 들어온 dailyName 값 :" + dailyInOutDTO.getDailyName());
            System.out.println("==========================");


            // [ ----------------- 날짜만 선택한 경우 ----------------- ]
            if(dailyInOutDTO.getDailyStartDate() != null && dailyInOutDTO.getDailyEndDate() != null && dailyInOutDTO.getDailyName() == null){
                System.out.println("==========================");
                System.out.println("< 날짜만 선택한 경우 >");
                System.out.println("==========================");
                searchList = boardService.searchDailyInOut1(dailyInOutDTO);
                System.out.println("searchList : " + searchList);
                if(searchList == null){
                    return null;
                }
                return searchList;


                // [ ----------------- 날짜랑 이름 둘 다 선택한 경우 ----------------- ]
            }else if(dailyInOutDTO.getDailyStartDate() != null && dailyInOutDTO.getDailyEndDate() != null && dailyInOutDTO.getDailyName() != null){
                System.out.println("==========================");
                System.out.println("< 날짜랑 이름 둘 다 선택한 경우 >");
                System.out.println("==========================");
                searchList = boardService.searchDailyInOut2(dailyInOutDTO);
                System.out.println("searchList : " + searchList);
                if(searchList == null){
                    return null;
                }
                return searchList;


                // [ ----------------- 이름만 선택한 경우 ----------------- ]
            }else if(dailyInOutDTO.getDailyStartDate() == null && dailyInOutDTO.getDailyEndDate() == null && dailyInOutDTO.getDailyName() != null){
                System.out.println("==========================");
                System.out.println("< 이름만 선택한 경우 >");
                System.out.println("==========================");
                searchList = boardService.searchDailyInOut3(dailyInOutDTO);
                System.out.println("searchList : " + searchList);
                if(searchList == null){
                    return null;
                }
                return searchList;


                // [ ----------------- startDate만 입력한 경우 ----------------- ]
            }else if(dailyInOutDTO.getDailyStartDate() != null && dailyInOutDTO.getDailyEndDate() == null && dailyInOutDTO.getDailyName() == null) {
                System.out.println("==========================");
                System.out.println("< startDate만 입력한 경우 >");
                System.out.println("==========================");
                searchList = boardService.searchDailyInOut4(dailyInOutDTO);
                System.out.println("searchList : " + searchList);
                if (searchList == null) {
                    return null;
                }
                return searchList;


                // [ ----------------- startDate와 이름만 입력한 경우 ----------------- ]
            }else if(dailyInOutDTO.getDailyStartDate() != null && dailyInOutDTO.getDailyEndDate() == null && dailyInOutDTO.getDailyName() != null) {
                System.out.println("==========================");
                System.out.println("< startDate와 이름만 입력한 경우 >");
                System.out.println("==========================");
                searchList = boardService.searchDailyInOut5(dailyInOutDTO);
                System.out.println("searchList : " + searchList);
                if (searchList == null) {
                    return null;
                }
                return searchList;


                // [ ----------------- endDate만 입력한 경우 ----------------- ]
            }else if(dailyInOutDTO.getDailyStartDate() == null && dailyInOutDTO.getDailyEndDate() != null && dailyInOutDTO.getDailyName() == null) {
                System.out.println("==========================");
                System.out.println("< endDate만 입력한 경우 >");
                System.out.println("==========================");
                searchList = boardService.searchDailyInOut6(dailyInOutDTO);
                System.out.println("searchList : " + searchList);
                if (searchList == null) {
                    return null;
                }
                return searchList;


                // [ ----------------- endDate와 이름만 입력한 경우 ----------------- ]
            }else if(dailyInOutDTO.getDailyStartDate() == null && dailyInOutDTO.getDailyEndDate() != null && dailyInOutDTO.getDailyName() != null) {
                System.out.println("==========================");
                System.out.println("< endDate와 이름만 입력한 경우 >");
                System.out.println("==========================");
                searchList = boardService.searchDailyInOut7(dailyInOutDTO);
                System.out.println("searchList : " + searchList);
                if (searchList == null) {
                    return null;
                }
                return searchList;
            }

            System.out.println("if문 오류 : " + searchList);
            return searchList;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    //-------------------------------- < 급여관리[일용직] > ----------------------------------
    // 급여관리 - 급여 계산(일용직)
    @RequestMapping("setDailySalary")
    public DailySalaryStatementDTO setDailySalary(@RequestBody DailySalaryStatementDTO dailySalaryStatementDTO){
        //param : 대상기간시작: startDate, 대상기간끝: endDate, 사원번호: dailyCode 사원명: dailyName, 직급: dailyRank, 회사코드: compCode
        System.out.println("==================================");
        System.out.println("[ 급여관리 - 급여 계산(일용직) ] 들어온 dailySalaryStatement 값 : " + dailySalaryStatementDTO);
        System.out.println("==================================");
        // 계산을 위한 엔진 설정
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");

        try {
            // ------------------------------------------------------------------
            // [ 세금 처리 ]
            // 기본급(일급)
            int baseWage = boardService.getbaseWage2(dailySalaryStatementDTO);
            System.out.println("기본급: " + baseWage);

            // 소득세
            // 일급이 187000원 기준으로 초과하면 아래와 같은 식을 사용한다.
            // 소득세 = ( 일급여액 - 15만원 ) * 2.97%
            // 지방세 = 소득세 * 10%
            double incomeTax1 = 0;
            int incomeTax = 0;
            if(baseWage > 187000) {
                incomeTax1 = (baseWage - 150000) * 0.027;
                incomeTax = (int)Math.round(incomeTax1);
                System.out.println("소득세:"+incomeTax);
            }

            // 소득세 세팅
            dailySalaryStatementDTO.setDailyIncomeTax(incomeTax);
            System.out.println("dto 소득세 재확인 : " + dailySalaryStatementDTO.getDailyIncomeTax());


            // 지방소득세
            double localTaxes1 =  incomeTax * 0.1;
            int localTaxes = (int)Math.round(localTaxes1);
            System.out.println("지방소득세: " + localTaxes);


            int ntnlPnsn = 0;
            // 국민연금
            if(baseWage > 5530000){                   //기본급이 553만원 이상이면 553만원으로 계산해야됨
                double ntnlPnsn1 =  5530000 * 0.045;
                ntnlPnsn = (int)Math.round(ntnlPnsn1);
                dailySalaryStatementDTO.setDailyNtnlPnsn(ntnlPnsn);
                System.out.println("국민연금:"+ntnlPnsn);
            }else{
                // 국민연금
                double ntnlPnsn1 =  baseWage * 0.045;
                ntnlPnsn = (int)Math.round(ntnlPnsn1);
                dailySalaryStatementDTO.setDailyNtnlPnsn(ntnlPnsn);
                System.out.println("국민연금:"+ntnlPnsn);
            }

            // 건강보험
            double hlthInsrn1 = baseWage * 0.03495;
            int hlthInsrn = (int)Math.round(hlthInsrn1);
            System.out.println("건강보험:" + hlthInsrn);

            // 장기요양보험
            double lngTrmCrIns1 = hlthInsrn * 0.1227;
            int lngTrmCrIns = (int)Math.round(lngTrmCrIns1);
            System.out.println("장기요양보험: " + lngTrmCrIns);

            // 고용보험
            double empIns1 = baseWage * 0.009;
            int empIns = (int) Math.round(empIns1);
            System.out.println("고용보험: "+ empIns);

            //공제총액
            int taxtotal = (incomeTax + localTaxes + dailySalaryStatementDTO.getDailyNtnlPnsn() + hlthInsrn + lngTrmCrIns + empIns);
            System.out.println("공제총액: " + taxtotal);

            // [ 수당 계산 ]
            //연장근로수당 변동(시간)
            String dailyInOutOverTime1 = boardService.getinOutOver2(dailySalaryStatementDTO);
            System.out.println("inOutOverTime1: " + dailyInOutOverTime1);
            if(dailyInOutOverTime1 == null){
                dailyInOutOverTime1 = "0";
            }
            double dailyInOutOverTime2 = Integer.parseInt(dailyInOutOverTime1);
            double dailyInOutOverTime = dailyInOutOverTime2/3600;
            System.out.println("연장근로수당 변동(시간): " + dailyInOutOverTime);

            //연장근로수당 변동(일)
            String dailyInOutOverTimeday1 = boardService.getinOutOverDayTax2(dailySalaryStatementDTO);
            System.out.println("inOutOverTimeday1: " + dailyInOutOverTimeday1);
            if(dailyInOutOverTimeday1 == null){
                dailyInOutOverTimeday1 = "0";
            }
            double dailyInOutOverTimeday2 = Integer.parseInt(dailyInOutOverTimeday1);
            double dailyInOutOverTimeday = dailyInOutOverTimeday2/3600;
            System.out.println("연장근로수당 변동(일): "+ dailyInOutOverTimeday);

            //야간근로수당 변동(시간)
            String dailyInOutNightTime1 = boardService.getinOutnight2(dailySalaryStatementDTO);
            System.out.println("inOutNightTime1: " + dailyInOutNightTime1);
            if(dailyInOutNightTime1 == null){
                dailyInOutNightTime1 = "0";
            }
            double dailyInOutNightTime2 = Integer.parseInt(dailyInOutNightTime1);
            double dailyInOutNightTime = dailyInOutNightTime2/3600;
            System.out.println("야간근로수당 변동(시간): " + dailyInOutNightTime);

            //야간근로수당 변동(일)
            String dailyInOutNightDay1 = boardService.getinOutnightday2(dailySalaryStatementDTO);
            System.out.println("inOutNightDay1: " + dailyInOutNightDay1);
            if(dailyInOutNightDay1 == null){
                dailyInOutNightDay1 = "0";
            }
            double dailyInOutNightDay2 = Integer.parseInt(dailyInOutNightDay1);
            double dailyInOutNightDay = dailyInOutNightDay2/3600;
            System.out.println("야간근로수당 변동(일): " + dailyInOutNightDay);

            // [ 수당 계산 ]
            Emp_payDTO emp_payDTO = new Emp_payDTO();

            double finalOverTime = 0;  // 연장근로수당[변동(시간)]
            double finalOverday = 0;   // 연장근로수당[변동(일)]
            double finalNightTime = 0; // 야간근로수당[변동(시간)]
            double finalNightday = 0;  // 야간근로수당[변동(일)]

            int dayWage = dailySalaryStatementDTO.getDailyPay();
            int timeWage = dayWage / 8;

            // 연장근로수당[변동(시간)]에 값이 있다면
            if(dailyInOutOverTime != 0.0){
                emp_payDTO.setPayName("연장근로수당");
                emp_payDTO.setPayType("변동(시간)");
                // ex) "timeWage" * 1.5
                String dailyInOutOverTimeCalc = boardService.getCalc2(emp_payDTO);
                // ex) timeWage * 1.5 * inOutOverTime
                String dailyOverTimeResult =  dailyInOutOverTimeCalc + " * " + dailyInOutOverTime;
                // ex) 기본급에서 만든 timeWage * 1.5 * inOutOverTime
                dailyOverTimeResult = dailyOverTimeResult.replace("timeWage", String.valueOf(timeWage));
                // 최종값 도출
                finalOverTime = (double) engine.eval(dailyOverTimeResult);
            }
            // 연장근로수당[변동(일)]에 값이 있다면
            if(dailyInOutOverTimeday != 0.0){
                emp_payDTO.setPayName("연장근로수당");
                emp_payDTO.setPayType("변동(일)");
                String dailyInOutOverDayCalc = boardService.getCalc(emp_payDTO);
                // ex) dayWage * 1.5 * inOutOverTime
                String dailyOverDayResult =  dailyInOutOverDayCalc + "*" + dailyInOutOverTimeday;
                // ex) 기본급에서 만든 dayWage * 1.5 * inOutOverTime
                dailyOverDayResult = dailyOverDayResult.replace("dayWage", String.valueOf(dayWage));
                // 최종값 도출
                finalOverday = (double) engine.eval(dailyOverDayResult);
            }
            // 야간근로수당[변동(시간)]에 값이 있다면
            if(dailyInOutNightTime != 0.0){
                emp_payDTO.setPayName("야간근로수당");
                emp_payDTO.setPayType("변동(시간)");
                String dailyInOutNightTimeCalc = boardService.getCalc(emp_payDTO);
                // ex) timeWage * 1.5 * inOutOverTime
                String dailyNightTimeResult =  dailyInOutNightTimeCalc + "*" + dailyInOutNightTime;
                // ex) 기본급에서 만든 timeWage * 1.5 * inOutOverTime
                dailyNightTimeResult = dailyNightTimeResult.replace("timeWage", String.valueOf(timeWage));
                // 최종값 도출
                finalNightTime = (double) engine.eval(dailyNightTimeResult);
            }
            // 야간근로수당[변동(일)]에 값이 있다면
            if(dailyInOutNightDay != 0.0){
                emp_payDTO.setPayName("야간근로수당");
                emp_payDTO.setPayType("변동(일)");
                String dailyInOutNightDayCalc = boardService.getCalc(emp_payDTO);
                // ex) timeWage * 1.5 * inOutOverTime
                String dailyNightDayResult =  dailyInOutNightDayCalc + "*" + dailyInOutNightDay;
                // ex) 기본급에서 만든 timeWage * 1.5 * inOutOverTime
                dailyNightDayResult = dailyNightDayResult.replace("dayWage", String.valueOf(dayWage));
                // 최종값 도출
                finalNightday = (double) engine.eval(dailyNightDayResult);
            }


            System.out.println("finalOverTime: " + finalOverTime + "  " + "finalOverTime: " + finalOverTime);
            // 연장근무수당 합치기
            double overtimePay = finalOverTime + finalOverday;

            System.out.println("finalNightTime: " + finalNightTime + "  " + "finalNightday: " + finalNightday);
            // 야간근무수당 합치기
            double nightTimePay = finalNightTime + finalNightday;

            // 기본급, 연장근무수당, 야간근무수당, 식대, 차량유지비, 출산보육수당
            double totalPay = baseWage + overtimePay + nightTimePay;

            // 실지급액 계산
            double actlPymnt = totalPay - taxtotal;


            // 경비
            int expense = boardService.getExpense2(dailySalaryStatementDTO);
            System.out.println("expense 경비 : " + expense);


            // [ 최종 세팅 ]
            dailySalaryStatementDTO.setDailyPay(baseWage);                    // empPay(기본급)
//            salary_statementDTO.setWeeklyPay(weeklyPay);              // 주휴수당
            dailySalaryStatementDTO.setDailyOvertimePay(overtimePay);         // 야근근무수당
            dailySalaryStatementDTO.setDailyNightTimePay(nightTimePay);       // 연장근무수당
            dailySalaryStatementDTO.setDailyIncomeTax(incomeTax);             // 소득세
            dailySalaryStatementDTO.setDailyLocalTaxes(localTaxes);           // 지방소득세
            dailySalaryStatementDTO.setDailyNtnlPnsn(ntnlPnsn);               // 국민연금
            dailySalaryStatementDTO.setDailyHlthInsrn(hlthInsrn);             // 건강보험
            dailySalaryStatementDTO.setDailyEmpIns(empIns);                   // 고용보험
            dailySalaryStatementDTO.setDailyIngTrmCrIns(lngTrmCrIns);         // 장기요양보험

            dailySalaryStatementDTO.setDailyExpense(expense);                 // 경비총액
            dailySalaryStatementDTO.setDailyTotalPay((int) totalPay);         // 지급총액
            dailySalaryStatementDTO.setDailyDdctn(taxtotal);                  // 공제금액
            dailySalaryStatementDTO.setDailyActlPymnt((int) actlPymnt);       // 실지급액(지급총액 - 공제금액)

            // 각 일용직원별 총 추가수당 계산
            double totalAddPay = overtimePay + nightTimePay;
            // 각 일용직원별 세금 계산
            double totalAddTax = incomeTax + localTaxes + ntnlPnsn + hlthInsrn + empIns + lngTrmCrIns;
            // 각 일용직원별 합계 계산
            double sumTotalAdd = baseWage + totalAddPay - totalAddTax;
            // 값 세팅
            dailySalaryStatementDTO.setDailyTotalAddPay((int) totalAddPay);
            dailySalaryStatementDTO.setDailyTotalAddTax((int) totalAddTax);
            dailySalaryStatementDTO.setDailySumTotalAdd((int) sumTotalAdd);
            // 잘 계산되었는지 확인
            System.out.println("각 사원별 총 추가수당: " + totalAddPay);
            System.out.println("각 사원별 세금:  " + totalAddTax);
            System.out.println("각 사원별 합계: " + sumTotalAdd);



            // ----------------------- 콘솔값으로 데이터 확인 -----------------------
            System.out.println("------------------------------------------------------");
            System.out.println("회사코드: " + dailySalaryStatementDTO.getCompCode());
            System.out.println("급여대장명칭: " + dailySalaryStatementDTO.getDailyPayTitle());
            System.out.println("대상기간시작: " + dailySalaryStatementDTO.getDailyStartDate());
            System.out.println("대상기간끝: " + dailySalaryStatementDTO.getDailyEndDate());
            System.out.println("지급일: " + dailySalaryStatementDTO.getDailyPayDay());
            System.out.println("사원번호: " + dailySalaryStatementDTO.getDailyCode());
            System.out.println("사원명: " + dailySalaryStatementDTO.getDailyName());
            System.out.println("기본급(일급) : " + dailySalaryStatementDTO.getDailyPay());
            System.out.println("야간근무수당: " + dailySalaryStatementDTO.getDailyNightTimePay());
            System.out.println("연장근무수당: " + dailySalaryStatementDTO.getDailyOvertimePay());
            System.out.println("소득세: " + dailySalaryStatementDTO.getDailyIncomeTax());
            System.out.println("지방소득세: " + dailySalaryStatementDTO.getDailyLocalTaxes());
            System.out.println("국민연금: " + dailySalaryStatementDTO.getDailyNtnlPnsn());
            System.out.println("건강보험: " + dailySalaryStatementDTO.getDailyHlthInsrn());
            System.out.println("고용보험: " + dailySalaryStatementDTO.getDailyEmpIns());
            System.out.println("장기요양보험: " + dailySalaryStatementDTO.getDailyIngTrmCrIns());
            System.out.println("지급총액: " + dailySalaryStatementDTO.getDailyTotalPay());
            System.out.println("공제금액: " + dailySalaryStatementDTO.getDailyDdctn());
            System.out.println("실지급액: " + dailySalaryStatementDTO.getDailyActlPymnt());

            System.out.println("===========================================");
            System.out.println(dailySalaryStatementDTO);

            return dailySalaryStatementDTO;
        }catch (Exception e){
            System.out.println("[에러] 급여계산[일용직]에서 에러가 발생하였습니다.");
            e.printStackTrace();
            return null;
        }
    }

    // 급여관리 - 급여 등록(일용직)
    @RequestMapping("createDailySalary")
    public boolean createDailySalary(@RequestBody DailySalaryStatementDTO dailySalaryStatementDTO){
        System.out.println("==================================");
        System.out.println("[ 급여관리 - 급여 등록(일용직) ] 들어온 dailySalaryStatement 값 : " + dailySalaryStatementDTO);
        System.out.println("==================================");

        try {
            int check = boardService.createDailySalary(dailySalaryStatementDTO);
            System.out.println("급여관리 - 급여 등록(일용직) 결과값 : " + check);
            if(check == 0){
                System.out.println("----- 급여등록[일용직]에 실패하였습니다 -----");
                return false;
            }
            System.out.println("----- 급여등록[일용직]이 성공적으로 완료되었습니다 -----");
            return true;
        }catch (Exception e){
            System.out.println("[에러]급여등록[일용직]에서 에러가 발생하였습니다.");
            e.printStackTrace();
            return false;
        }
    }

    // 급여관리 - [메인] 급여 불러오기(일용직)
    @RequestMapping("readDailyMainSalary")
    public ArrayList<DailySalaryStatementDTO> readDailyMainSalary(@RequestBody DailySalaryStatementDTO dailySalaryStatementDTO){
        System.out.println("==================================");
        System.out.println("[ 급여관리 - [메인] 급여 불러오기(일용직) ] 들어온 dailySalaryStatement 값 : " + dailySalaryStatementDTO);
        System.out.println("==================================");

        try {
            ArrayList<DailySalaryStatementDTO> dailySalaryList = boardService.readDailyMainSalary(dailySalaryStatementDTO);
            System.out.println("[ 급여관리 - [메인] 급여 불러오기(일용직) ] 결과 값 : " + dailySalaryList);
            if(dailySalaryList == null){
                System.out.println("----- [null]일용직 급여[메인] 불러오기 실패하였습니다 -----");
                return null;
            }

            System.out.println("----- 일용직 급여[메인] 불러오기 성공하였습니다 -----");
            return dailySalaryList;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("----- [에러]일용직 급여[메인] 불러오기 실패하였습니다 -----");
            return null;
        }
    }

    // 급여관리 - [모달] 급여 불러오기(일용직)
    @RequestMapping("readDailyModalSalary")
    public ArrayList<DailySalaryStatementDTO> readDailyModalSalary(@RequestBody DailySalaryStatementDTO dailySalaryStatementDTO){
        System.out.println("==================================");
        System.out.println("[급여관리 - [모달] 급여 불러오기(일용직) ] 들어온 dailySalaryStatement 값 : " + dailySalaryStatementDTO);
        System.out.println("==================================");
        try {
            ArrayList<DailySalaryStatementDTO> dailySalaryModalList = boardService.readDailyModalSalary(dailySalaryStatementDTO);
            System.out.println("[급여관리 - [모달] 급여 불러오기(일용직)) ] 결과 값 : " + dailySalaryModalList);
            if(dailySalaryModalList == null){
                System.out.println("----- [null][모달] 급여 불러오기(일용직) 불러오기 실패하였습니다 -----");
                return null;
            }

            System.out.println("----- 급여 불러오기(일용직) 불러오기 성공하였습니다 -----");
            return dailySalaryModalList;
        }catch (Exception e){
            System.out.println("----- [에러] [모달] 급여 불러오기(일용직) 실패하였습니다 -----");
            e.printStackTrace();
            return null;
        }
    }



    // 급여관리 - 급여 삭제하기(일용직)
    @RequestMapping("deleteDailySalary")
    public boolean deleteDailySalary(@RequestBody DailySalaryStatementDTO dailySalaryStatementDTO){
        System.out.println("==================================");
        System.out.println("[급여관리 - 급여 삭제하기(일용직)] 들어온 dailySalaryStatement 값 : " + dailySalaryStatementDTO);
        System.out.println("==================================");
        try {
            int check = boardService.deleteDailySalary(dailySalaryStatementDTO);
            System.out.println("급여관리 - 급여 삭제하기(일용직) 결과값 : " + check);
            if(check == 0){
                System.out.println("-----[0]급여관리 - 급여 삭제하기(일용직) 실패하였습니다 -----");
                return false;
            }

            return true;
        }catch (Exception e){
            System.out.println("-----[에러]급여관리 - 급여 삭제하기(일용직) 실패하였습니다 -----");
            e.printStackTrace();
            return false;
        }
    }


    // 급여관리 - 출력 버튼[일용직]
    @RequestMapping("printDailySalary")
    public ArrayList<DailySalaryStatementDTO> printDailySalary(@RequestBody DailySalaryStatementDTO dailySalaryStatementDTO){
        // param : statementId
        System.out.println("==================================");
        System.out.println("[ 급여관리 - 출력 버튼[일용직] ] 들어온 dailySalaryStatementDTO 값 : " + dailySalaryStatementDTO);
        System.out.println("==================================");

        String[] printButton = dailySalaryStatementDTO.getPrintButton();
        ArrayList<DailySalaryStatementDTO> dailySalaryList = new ArrayList<>();
        String temp = "";
        try {
            for(int i=0; i<printButton.length; i++){
                temp = String.valueOf(printButton[i]);       // id1, id2, id3, ...
                System.out.println("id 값[" + i + "] : " + temp);
                if(temp == null){
                    System.out.println("---------- id값에 null 값이 있습니다 ----------");
                    return null;
                }

                dailySalaryStatementDTO = boardService.getSalaryList2(temp);
                System.out.println("[ 급여관리 - 출력 버튼[일용직] ] dailySalaryStatementDTO 값1 : " + dailySalaryStatementDTO);
                if(dailySalaryStatementDTO == null){
                    System.out.println("----------  dailySalaryStatementDTO 값이 null입니다 ----------");
                    return null;
                }

                System.out.println("[ 급여관리 - 출력 버튼[일용직] ] dailySalaryStatementDTO 값2: " + dailySalaryStatementDTO);
                dailySalaryList.add(dailySalaryStatementDTO);
            }


            System.out.println("---------- 출력 버튼을 성공적으로 마쳤습니다 ----------");
            System.out.println(" 급여관리 - 출력 버튼[일용직] 최종 결과값 dailySalaryStatementDTO: " + dailySalaryList);
            return dailySalaryList;
        }catch (Exception e){
            System.out.println("[에러] 급여관리-출력버튼[일용직]에서 에러가 발생했습니다.");
            e.printStackTrace();
            return null;
        }
    }

    //-------------------------------- < 급여관리[임원직] > ----------------------------------
    // CRUD (create, read, update, delete)

    // 급여관리 - 사원 정보 보여주기(read)
    /*
    @RequestMapping("readSalaryEmp")
    public ArrayList<EmpDTO> readSalaryEmp(EmpDTO empDTO){
        // param : 대상기간: monthYear, 사원명: empName, 부서명: depName
        System.out.println("==================================");
        System.out.println("[ 급여관리 - 급여계산 ] 들어온 empDTO 값 : " + empDTO);
        System.out.println("==================================");

        try {
            ArrayList<EmpDTO> salaryEmp = boardService.readSalaryEmp(empDTO);


            return null;
        }catch (Exception e){
            System.out.println("[에러]급여관리 - 사원정보 보여주기");
            e.printStackTrace();
            return null;
        }
    }
    */


    // 급여관리 - 급여 계산(임원직)
    @RequestMapping("setSalary")
    public Salary_statementDTO setSalary(@RequestBody Salary_statementDTO salary_statementDTO) {
        // param : 대상기간시작: startDate, 대상기간끝: endDate, 사원번호: empNum 사원명: empName, 부서명: depName, 회사코드: compCode
        System.out.println("==================================");
        System.out.println("[급여관리 - 급여 계산(임원직) ] 들어온 salary_statementDTO 값 : " + salary_statementDTO);
        System.out.println("==================================");

        // 계산을 위한 엔진 설정
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");

        try {
            //-------------------------------------------------------------------------
            // [ 사원 정보 세팅 ]
            EmpDTO empDTO = new EmpDTO();

            // 사원번호 세팅
            empDTO.setEmpNum(salary_statementDTO.getEmpNum());
            // 사원명 세팅
            empDTO.setEmpName(salary_statementDTO.getEmpName());
            // 부서명 세팅
            empDTO.setDepName(salary_statementDTO.getDepName());
            // 회사코드 세팅
            empDTO.setCompCode(salary_statementDTO.getCompCode());
            // 대상기간시작
            empDTO.setStartDate(salary_statementDTO.getStartDate());
            // 대상기간끝
            empDTO.setEndDate(salary_statementDTO.getEndDate());

            empDTO = boardService.getEmpInfo(empDTO);

            // 사원번호 세팅
            empDTO.setEmpNum(salary_statementDTO.getEmpNum());
            System.out.println("empNum: " + salary_statementDTO.getEmpNum());
            // 사원명 세팅
            empDTO.setEmpName(salary_statementDTO.getEmpName());
            // 부서명 세팅
            empDTO.setDepName(salary_statementDTO.getDepName());
            // 회사코드 세팅
            empDTO.setCompCode(salary_statementDTO.getCompCode());
            // 대상기간시작
            empDTO.setStartDate(salary_statementDTO.getStartDate());
            System.out.println("startDate: " + salary_statementDTO.getStartDate());
            // 대상기간끝
            empDTO.setEndDate(salary_statementDTO.getEndDate());
            System.out.println("endDate: " + salary_statementDTO.getEndDate());

            List<SimpleTaxTableDTO> getincomeTax = boardService.getincomeTax(empDTO);

            System.out.println("---------------------------");
            System.out.println("부양가족수 확인용 : " + empDTO);
            System.out.println("---------------------------");
            //부양가족수 가져오기
            Integer family = boardService.getfamilyNum(empDTO);
            if(family == null){
                family = 0;
            }
            System.out.println("부양가족수 : " + family);
            //소득세
            int incomeTax = 0;
            switch (family){
                case 1: incomeTax = getincomeTax.get(0).getOne(); break;
                case 2: incomeTax = getincomeTax.get(0).getTwo(); break;
                case 3: incomeTax = getincomeTax.get(0).getThree(); break;
                case 4: incomeTax = getincomeTax.get(0).getFour(); break;
                case 5: incomeTax = getincomeTax.get(0).getFive(); break;
                case 6: incomeTax = getincomeTax.get(0).getSix(); break;
                case 7: incomeTax = getincomeTax.get(0).getSeven(); break;
                case 8: incomeTax = getincomeTax.get(0).getEigth(); break;
                case 9: incomeTax = getincomeTax.get(0).getNine(); break;
                case 10: incomeTax = getincomeTax.get(0).getTen(); break;
                case 11: incomeTax = getincomeTax.get(0).getEleven(); break;
            }

            System.out.println("소득세:"+incomeTax);
            salary_statementDTO.setIncomeTax(incomeTax);
            System.out.println("값출력" + getincomeTax);
            // ------------------------------------------------------------------
            // [ 세금 처리 ]
            // 기본급
            int baseWage = boardService.getbaseWage(empDTO);
            System.out.println("기본급: " + baseWage);

            // 소득세
            System.out.println("소득세:"+incomeTax);

            // 지방소득세
            double localTaxes1 =  incomeTax * 0.1;
            int localTaxes = (int)Math.round(localTaxes1);
            System.out.println("지방소득세: " + localTaxes);


            int ntnlPnsn = 0;
            // 국민연금
            if(baseWage > 5530000){                   //기본급이 553만원 이상이면 553만원으로 계산해야됨
                double ntnlPnsn1 =  5530000 * 0.045;
                ntnlPnsn = (int)Math.round(ntnlPnsn1);
                salary_statementDTO.setNtnlPnsn(ntnlPnsn);
                System.out.println("국민연금:"+ntnlPnsn);
            }else{
                // 국민연금
                double ntnlPnsn1 =  baseWage * 0.045;
                ntnlPnsn = (int)Math.round(ntnlPnsn1);
                salary_statementDTO.setNtnlPnsn(ntnlPnsn);
                System.out.println("국민연금:"+ntnlPnsn);
            }

            // 건강보험
            double hlthInsrn1 = baseWage * 0.03495;
            int hlthInsrn = (int)Math.round(hlthInsrn1);
            System.out.println("건강보험:" + hlthInsrn);

            // 장기요양보험
            double lngTrmCrIns1 = hlthInsrn * 0.1227;
            int lngTrmCrIns = (int)Math.round(lngTrmCrIns1);
            System.out.println("장기요양보험: " + lngTrmCrIns);

            // 고용보험
            double empIns1 = baseWage * 0.009;
            int empIns = (int) Math.round(empIns1);
            System.out.println("고용보험: "+ empIns);

            //공제총액
            int taxtotal = (incomeTax + localTaxes + salary_statementDTO.getNtnlPnsn() + hlthInsrn + lngTrmCrIns + empIns);
            System.out.println("공제총액: " + taxtotal);



            //---------------------------------------------------------------
            // [ 식대, 차량유지비, 출산보육수당 세팅 ]
            int mealPay = empDTO.getMealPay();
            int carPay = empDTO.getCarPay();
            int childPay = empDTO.getChildPay();


            //---------------------------------------------------------------
            // [ 값 세팅 ]
            // compCode
            String compCode = empDTO.getCompCode();

            // 이 사원의 시급
            String timeWage = String.valueOf(baseWage /209);
            System.out.println("기본급 baseWage : " + baseWage);

            // 이 사원의 일급
            String dayWage = String.valueOf(Integer.valueOf(timeWage) * 8);

            //연장근로수당 변동(시간)
            String inOutOverTime1 = boardService.getinOutOver(empDTO);
            System.out.println("inOutOverTime1: " + inOutOverTime1);
            if(inOutOverTime1 == null){
                inOutOverTime1 = "0";
            }
            double inOutOverTime2 = Integer.parseInt(inOutOverTime1);
            double inOutOverTime = inOutOverTime2/3600;
            System.out.println("연장근로수당 변동(시간): " + inOutOverTime);

            //연장근로수당 변동(일)
            String inOutOverTimeday1 = boardService.getinOutOverDayTax(empDTO);
            System.out.println("inOutOverTimeday1: " + inOutOverTimeday1);
            if(inOutOverTimeday1 == null){
                inOutOverTimeday1 = "0";
            }
            double inOutOverTimeday2 = Integer.parseInt(inOutOverTimeday1);
            double inOutOverTimeday = inOutOverTimeday2/3600;
            System.out.println("연장근로수당 변동(일): "+ inOutOverTimeday);

            //야간근로수당 변동(시간)
            String inOutNightTime1 = boardService.getinOutnight(empDTO);
            System.out.println("inOutNightTime1: " + inOutNightTime1);
            if(inOutNightTime1 == null){
                inOutNightTime1 = "0";
            }
            double inOutNightTime2 = Integer.parseInt(inOutNightTime1);
            double inOutNightTime = inOutNightTime2/3600;
            System.out.println("야간근로수당 변동(시간): " + inOutNightTime);

            //야간근로수당 변동(일)
            String inOutNightDay1 = boardService.getinOutnightday(empDTO);
            System.out.println("inOutNightDay1: " + inOutNightDay1);
            if(inOutNightDay1 == null){
                inOutNightDay1 = "0";
            }
            double inOutNightDay2 = Integer.parseInt(inOutNightDay1);
            double inOutNightDay = inOutNightDay2/3600;
            System.out.println("야간근로수당 변동(일): " + inOutNightDay);


            // [ -------- 수당 계산 -------- ]
            Emp_payDTO emp_payDTO = new Emp_payDTO();

            // 해당 회사의 compCode를 미리 세팅
            emp_payDTO.setCompCode(compCode);

            double finalOverTime = 0;  // 연장근로수당[변동(시간)]
            double finalOverday = 0;   // 연장근로수당[변동(일)]
            double finalNightTime = 0; // 야간근로수당[변동(시간)]
            double finalNightday = 0;  // 야간근로수당[변동(일)]

            // 연장근로수당[변동(시간)]에 값이 있다면
            if(inOutOverTime != 0.0){
                emp_payDTO.setPayName("연장근로수당");
                emp_payDTO.setPayType("변동(시간)");
                // ex) "timeWage" * 1.5
                String inOutOverTimeCalc = boardService.getCalc(emp_payDTO);
                // ex) timeWage * 1.5 * inOutOverTime
                String overTimeResult =  inOutOverTimeCalc + " * " + inOutOverTime;
                // ex) 기본급에서 만든 timeWage * 1.5 * inOutOverTime
                overTimeResult = overTimeResult.replace("timeWage", timeWage);
                // 최종값 도출
                finalOverTime = (double) engine.eval(overTimeResult);
            }
            // 연장근로수당[변동(일)]에 값이 있다면
            if(inOutOverTimeday != 0.0){
                emp_payDTO.setPayName("연장근로수당");
                emp_payDTO.setPayType("변동(일)");
                String inOutOverDayCalc = boardService.getCalc(emp_payDTO);
                // ex) dayWage * 1.5 * inOutOverTime
                String overDayResult =  inOutOverDayCalc + "*" + inOutOverTimeday;
                // ex) 기본급에서 만든 dayWage * 1.5 * inOutOverTime
                overDayResult = overDayResult.replace("dayWage", dayWage);
                // 최종값 도출
                finalOverday = (double) engine.eval(overDayResult);
            }
            // 야간근로수당[변동(시간)]에 값이 있다면
            if(inOutNightTime != 0.0){
                emp_payDTO.setPayName("야간근로수당");
                emp_payDTO.setPayType("변동(시간)");
                String inOutNightTimeCalc = boardService.getCalc(emp_payDTO);
                // ex) timeWage * 1.5 * inOutOverTime
                String nightTimeResult =  inOutNightTimeCalc + "*" + inOutNightTime;
                // ex) 기본급에서 만든 timeWage * 1.5 * inOutOverTime
                nightTimeResult = nightTimeResult.replace("timeWage", timeWage);
                // 최종값 도출
                finalNightTime = (double) engine.eval(nightTimeResult);
            }
            // 야간근로수당[변동(일)]에 값이 있다면
            if(inOutNightDay != 0.0){
                emp_payDTO.setPayName("야간근로수당");
                emp_payDTO.setPayType("변동(일)");
                String inOutNightDayCalc = boardService.getCalc(emp_payDTO);
                // ex) timeWage * 1.5 * inOutOverTime
                String nightDayResult =  inOutNightDayCalc + "*" + inOutNightDay;
                // ex) 기본급에서 만든 timeWage * 1.5 * inOutOverTime
                nightDayResult = nightDayResult.replace("dayWage", dayWage);
                // 최종값 도출
                finalNightday = (double) engine.eval(nightDayResult);
            }

            System.out.println("finalOverTime: " + finalOverTime + "  " + "finalOverTime: " + finalOverTime);
            // 연장근무수당 합치기
            double overtimePay = finalOverTime + finalOverday;

            System.out.println("finalNightTime: " + finalNightTime + "  " + "finalNightday: " + finalNightday);
            // 야간근무수당 합치기
            double nightTimePay = finalNightTime + finalNightday;

            // 기본급, 연장근무수당, 야간근무수당, 식대, 차량유지비, 출산보육수당
            double totalPay = baseWage + overtimePay + nightTimePay + mealPay + carPay + childPay;

            // 실지급액 계산
            double actlPymnt = totalPay - taxtotal;

            // 경비
            int expense = boardService.getExpense(empDTO);
            System.out.println("expense 경비 : " + expense);

            // [ 최종 세팅 ]
            salary_statementDTO.setCompCode(compCode);                  // compCode(회사코드)
            salary_statementDTO.setEmpPay(baseWage);                    // empPay(기본급)
//            salary_statementDTO.setWeeklyPay(weeklyPay);              // 주휴수당
            salary_statementDTO.setOvertimePay(overtimePay);            // 야근근무수당
            salary_statementDTO.setNightTimePay(nightTimePay);          // 연장근무수당
//            salary_statementDTO.setWeekendPay(weekendPay);              // 주말근무수당
//            salary_statementDTO.setAnnualAllowance();                   // 연차수당
//            salary_statementDTO.setDpndnAlwnc();                        // 부양가족수당
            salary_statementDTO.setIncomeTax(incomeTax);                // 소득세
            salary_statementDTO.setLocalTaxes(localTaxes);              // 지방소득세
            salary_statementDTO.setNtnlPnsn(ntnlPnsn);                  // 국민연금
            salary_statementDTO.setHlthInsrn(hlthInsrn);                // 건강보험
            salary_statementDTO.setEmpIns(empIns);                      // 고용보험
            salary_statementDTO.setLngTrmCrIns(lngTrmCrIns);            // 장기요양보험

            salary_statementDTO.setFoodPay(mealPay);                    // 식대
            salary_statementDTO.setCarStatePay(carPay);                 // 차량유지비
            salary_statementDTO.setChldbChalw(childPay);                // 출산보육수당

            salary_statementDTO.setExpense(expense);                  // 경비총액
            salary_statementDTO.setTotalPay(totalPay);                // 지급총액
            salary_statementDTO.setDdctn(taxtotal);                   // 공제금액
            salary_statementDTO.setActlPymnt(actlPymnt);              // 실지급액(지급총액 - 공제금액)

            // 각 사원별 총 추가수당 계산
            double totalAddPay = overtimePay + nightTimePay + mealPay + carPay + childPay;
            // 각 사원별 세금 계산
            double totalAddTax = incomeTax + localTaxes + ntnlPnsn + hlthInsrn + empIns + lngTrmCrIns;
            // 각 사원별 합계 계산
            double sumTotalAdd = baseWage + totalAddPay - totalAddTax;
            // 값 세팅
            salary_statementDTO.setTotalAddPay(totalAddPay);
            salary_statementDTO.setTotalAddTax(totalAddTax);
            salary_statementDTO.setSumTotalAdd(sumTotalAdd);
            // 잘 계산되었는지 확인
            System.out.println("각 사원별 총 추가수당: " + totalAddPay);
            System.out.println("각 사원별 세금:  " + totalAddTax);
            System.out.println("각 사원별 합계: " + sumTotalAdd);



            // ----------------------- 콘솔값으로 데이터 확인 -----------------------
            System.out.println("------------------------------------------------------");
            System.out.println("회사코드: " + salary_statementDTO.getCompCode());
            System.out.println("급여대장명칭: " + salary_statementDTO.getPayTitle());
            System.out.println("대상기간시작: " + salary_statementDTO.getStartDate());
            System.out.println("대상기간끝: " + salary_statementDTO.getEndDate());
            System.out.println("지급일: " + salary_statementDTO.getPayDay());
            System.out.println("사원번호: " + salary_statementDTO.getEmpNum());
            System.out.println("사원명: " + salary_statementDTO.getEmpName());
            System.out.println("부서명: " + salary_statementDTO.getDepName());
            System.out.println("기본급: " + salary_statementDTO.getEmpPay());
            System.out.println("야간근무수당: " + salary_statementDTO.getNightTimePay());
            System.out.println("연장근무수당: " + salary_statementDTO.getOvertimePay());
            System.out.println("부양가족수당: " + salary_statementDTO.getDpndnAlwnc());
            System.out.println("소득세: " + salary_statementDTO.getIncomeTax());
            System.out.println("지방소득세: " + salary_statementDTO.getLocalTaxes());
            System.out.println("국민연금: " + salary_statementDTO.getNtnlPnsn());
            System.out.println("건강보험: " + salary_statementDTO.getHlthInsrn());
            System.out.println("고용보험: " + salary_statementDTO.getEmpIns());
            System.out.println("장기요양보험: " + salary_statementDTO.getLngTrmCrIns());
            System.out.println("출산보육수당: " + salary_statementDTO.getChldbChalw());
            System.out.println("식대: " + salary_statementDTO.getFoodPay());
            System.out.println("차량유지비: " + salary_statementDTO.getCarStatePay());
            System.out.println("지급총액: " + salary_statementDTO.getTotalPay());
            System.out.println("공제금액: " + salary_statementDTO.getDdctn());
            System.out.println("실지급액: " + salary_statementDTO.getActlPymnt());

            System.out.println("===========================================");
            System.out.println(salary_statementDTO);

            return salary_statementDTO;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("[[ 에러 ]] 계산에 에러가 발생하였습니다.");
            return null;
        }
    }


    // 급여관리 - 급여 명세서 저장(임원직)(create)
    @RequestMapping("createSalary")
    public boolean createSalary(@RequestBody Salary_statementDTO salary_statementDTO){
        // param : 모든 데이터
        System.out.println("==================================");
        System.out.println("[ 급여관리 - 급여 명세서 저장(임원직)(create) ] 들어온 salary_statementDTO 값 : " + salary_statementDTO);
        System.out.println("==================================");
        try {
            // "해당 급여대장명칭"으로 "방금 들어온 사원번호" 값이 이미 존재한다면 해당 달월의 급여계산이 생성된 것이므로 중복체크
            // payTitle, empNum
            int check = boardService.duplicateCheck(salary_statementDTO);
            System.out.println(check);
            if(check > 0){
                System.out.println("--- " + salary_statementDTO.getPayTitle() + "의 급여대장명칭으로" + salary_statementDTO.getEmpName() + "은 이미 존재합니다.");
                return false;
            }


            int check2 = boardService.createSalary(salary_statementDTO);
            System.out.println("[ 급여관리 - 급여 명세서 저장(임원직)(create) ] 결과값 check2 값 : " + check2);
            if(check2 == 0){
                System.out.println("-------- 급여 명세서 등록에 실패하였습니다 --------");
                return false;
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("-------- [에러]급여 명세서 등록에 실패하였습니다 --------");
            return false;
        }
    }



    // 급여관리 - [메인]급여 명세서 불러오기(임원직)(Read)
    @RequestMapping("readMainSalary")
    public ArrayList<Salary_statementDTO> readMainSalary(@RequestBody Salary_statementDTO salary_statementDTO){
        // param : compCode, 날짜 or 급여대장명칭
        System.out.println("==================================");
        System.out.println("[ 급여관리 - [메인]급여 명세서 불러오기(임원직)(Read) ] 들어온 salary_statementDTO 값 : " + salary_statementDTO);
        System.out.println("==================================");
        try {
            ArrayList<Salary_statementDTO> readMainList = boardService.readMainSalary(salary_statementDTO);
            System.out.println("[메인]급여 명세서 불러오기 결과값: " + readMainList);
            if(readMainList == null){
                System.out.println("[NULL] 급여 명세서를 불러오지 못했습니다.");
                return null;
            }

            // 전체 추가수당 : 야근수당, 주말근로수당, 연차수당, 출산보육수당, 부양가족수당
//            double sumPay = readMainList.get(0).getWeekendPay() + readMainList.get(0).getOvertimePay() + readMainList.get(0).getWeekendPay() + readMainList.get(0).getAnnualAllowance()
//                    + readMainList.get(0).getChldbChalw() + readMainList.get(0).getDpndnAlwnc();
            int sumWage = 0;        // 기본급 총합
            double sumPay = 0;      // 추가수당 총합
            double sumTax = 0;      // 세금+경비 총합
            double totalSum = 0;    // 합계( 기본급 총합 + 추가수당 총합 + (세금+경비 총합) )

            // 값 정제
            for(int i=0; i<readMainList.size(); i++){
                // 기본급 총합
                sumWage += readMainList.get(i).getEmpPay();
                // 추가수당 총합
                sumPay += readMainList.get(i).getTotalAddPay();
                // 세금 총합
                sumTax += readMainList.get(i).getDdctn();   // 경비 부분 제거함.
            }
            // 합계
            totalSum = sumWage + sumPay + sumTax;

            // [ 결과값 세팅 ]
            salary_statementDTO.setSumWage(sumWage);
            salary_statementDTO.setSumPay(sumPay);
            salary_statementDTO.setSumTax(sumTax);
            salary_statementDTO.setTotalSum(totalSum);

            System.out.println("sumWage : " + sumWage);
            System.out.println("sumPay : " + sumPay);
            System.out.println("sumTax : " + sumTax);
            System.out.println("totalSum : " + totalSum);
            System.out.println("----------------------------------------------------------");
            System.out.println("totalAddPay : " + salary_statementDTO.getTotalAddPay());
            System.out.println("totalAddTax : " + salary_statementDTO.getTotalAddTax());
            System.out.println("sumTotalAdd : " + salary_statementDTO.getSumTotalAdd());

            System.out.println("임원직 명세서 [메인] 불러오기 성공");
            return readMainList;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    // 급여관리 - [모달]급여 명세서 불러오기(임원직)(Read)
    @RequestMapping("readModalSalary")
    public ArrayList<Salary_statementDTO> readModalSalary(@RequestBody Salary_statementDTO salary_statementDTO){
        // param : payStatementId
        System.out.println("==================================");
        System.out.println("[ 급여관리 - [모달]급여 명세서 불러오기(임원직)(Read) ] 들어온 salary_statementDTO 값 : " + salary_statementDTO);
        System.out.println("==================================");
        try {
            ArrayList<Salary_statementDTO> salaryList = boardService.readModalSalary(salary_statementDTO);
            System.out.println("[ 급여관리 - [모달]급여 명세서 불러오기(임원직)(Read)] 결과값 salaryList 값 : " + salaryList);
            if(salaryList == null){
                System.out.println("[NULL]급여 명세서 불러오기를 실패하였습니다.");
                return null;
            }
            System.out.println("임원직 명세서 [모달] 불러오기 성공");
            return salaryList;
        }catch (Exception e){
            System.out.println("");
            e.printStackTrace();
            return null;
        }
    }

    // 급여관리 - 급여 명세서 수정(임원직)(Update)
    /*
    @RequestMapping("updateSalary")
    public boolean updateSalary(@RequestBody Salary_statementDTO salary_statementDTO){
        // param : payStatementId
        System.out.println("==================================");
        System.out.println("[ 급여관리 - 급여 명세서 수정(임원직)(Update) ] 들어온 salary_statementDTO 값 : " + salary_statementDTO);
        System.out.println("==================================");
        try {
            // 나 자신의 데이터를 제외한 나머지 데이터에서 "payTitle"과 "empNum" 값이 같은게 있지 않아야 한다.
            int check = boardService.duplicateCheckUpdate(salary_statementDTO);
            if(check > 0){
                System.out.println("------ 해당 사원의 정보가 중복되었습니다. ------");
                return false;
            }

            int check2 = boardService.updateSalary(salary_statementDTO);
            if(check2 == 0){
                System.out.println("----- 급여 명세서 업데이트를 실패하였습니다 -----");
                return false;
            }
            return true;
        }catch (Exception e){
            System.out.println("[에러]급여 명세서 수정 실패하였습니다.");
            e.printStackTrace();
            return false;
        }
    }
    */


    // 급여관리 - 급여 명세서 삭제(임원직)(Delete)
    @RequestMapping("deleteSalary")
    public boolean deleteSalary(@RequestBody Salary_statementDTO salary_statementDTO){
        // param : payStatementId
        System.out.println("==================================");
        System.out.println("[ 급여관리 - 급여 명세서 삭제(임원직)(Delete) ] 들어온 salary_statementDTO 값 : " + salary_statementDTO);
        System.out.println("==================================");
        try{
            int check = boardService.deleteSalary(salary_statementDTO);
            if(check == 0){
                System.out.println("------ 급여 명세서 삭제를 실패하였습니다 ------");
                return false;
            }
            System.out.println("------ 급여 명세서 삭제 성공하였습니다 ------");
            return true;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("----- 급여 명세서 삭제를 실패하였습니다 -----");
            return false;
        }
    }


    // 급여관리 - 출력 버튼[임원직]
    @RequestMapping("printSalary")
    public ArrayList<Salary_statementDTO> printSalary(@RequestBody Salary_statementDTO salary_statementDTO){
        // param : payStatementId
        System.out.println("==================================");
        System.out.println("[ 급여관리 - 출력 버튼[임원직] ] 들어온 salary_statementDTO 값 : " + salary_statementDTO);
        System.out.println("==================================");

        String[] printButton = salary_statementDTO.getPrintButton();
        ArrayList<Salary_statementDTO> salaryList = new ArrayList<>();
        String temp = "";
        try {
            for(int i=0; i<printButton.length; i++){
                temp = String.valueOf(printButton[i]);       // id1, id2, id3, ...
                System.out.println("id 값[" + i + "] : " + temp);
                if(temp == null){
                    System.out.println("---------- id값에 null 값이 있습니다 ----------");
                    return null;
                }

                salary_statementDTO = boardService.getSalaryList(temp);
                System.out.println("[ 급여관리 - 출력 버튼[임원직] ] salary_statementDTO 값1 : " + salary_statementDTO);
                if(salary_statementDTO == null){
                    System.out.println("----------  salary_statementDTO 값이 null입니다 ----------");
                    return null;
                }

                System.out.println("[ 급여관리 - 출력 버튼[임원직] ] salary_statementDTO 값2: " + salary_statementDTO);
                salaryList.add(salary_statementDTO);
            }


            System.out.println("---------- 출력 버튼을 성공적으로 마쳤습니다 ----------");
            System.out.println(" 급여관리 - 출력 버튼[임원직] 최종 결과값 salaryList: " + salaryList);
            return salaryList;
        }catch (Exception e){
            System.out.println("[에러] 급여관리-출력버튼[임원직]에서 에러가 발생했습니다.");
            e.printStackTrace();
            return null;
        }
    }

    //경비관리 ( create )
    @RequestMapping("expenseCreate")
    public boolean expenseCreate(@RequestBody Expense_infoDTO expense_infoDTO) {
        System.out.println("==================================");
        System.out.println("[ 경비관리 create] 들어온 expense_infoDTO 값 : " + expense_infoDTO);
        System.out.println("==================================");
        try {
            expense_infoDTO.setTotalPrice(expense_infoDTO.getPrice());  //금액 total에 넣어줌
            int check = boardService.expenseCreate(expense_infoDTO);
            System.out.println("[ 경비관리] 들어온 expense_infoDTO 값 : " + check);
            if (check == 0) {
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //경비관리 ( read )
    @RequestMapping("expenseRead")
    public List<Expense_infoDTO> expenseRead(@RequestBody Expense_infoDTO expense_infoDTO) {
        // param : compCode
        System.out.println("==================================");
        System.out.println("[경비관리 read 들어온 expense_infoDTO 값 : " + expense_infoDTO);
        System.out.println("==================================");
        try {
            List<Expense_infoDTO> list = boardService.expenseRead(expense_infoDTO);
            System.out.println("[ 경비관리[read]  ] 결과값 dailyEPList 값 : " + expense_infoDTO);
            if (list == null) {
                return null;
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // 경비처리 (update)
    @RequestMapping("expenseUpdate")
    public boolean expenseUpdate(@RequestBody Expense_infoDTO expense_infoDTO) {
        System.out.println("==================================");
        System.out.println("[경비관리  업데이트 들어온 expense_infoDTO 값 : " + expense_infoDTO);
        System.out.println("==================================");
        try {
            int check = boardService.expenseUpdate(expense_infoDTO);
            System.out.println("[ 경비관리  업데이트 들어온] -  수정 ] 결과값 check 값 : " + check);
            if (check == 0) {
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //경비처리 (delete)
    @RequestMapping("expenseDelete")
    public boolean expenseDelete(@RequestBody Expense_infoDTO expense_infoDTO) {
        System.out.println("==================================");
        System.out.println("[경비관리  삭제 들어온 expense_infoDTO 값 : " + expense_infoDTO);
        System.out.println("==================================");
        try {
            int check = boardService.expenseDelete(expense_infoDTO);
            System.out.println("[ 경비관리  삭제 들어온] -  수정 ] 결과값 check 값 : " + check);
            if (check == 0) {
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //경비처리 모달창
    //parm : expenseListId
    @RequestMapping("expenseModal")
    public List<Expense_infoDTO> expenseModal(@RequestBody Expense_infoDTO expense_infoDTO) {
        System.out.println("==================================");
        System.out.println("[경비관리 모달창 들어온 expense_infoDTO 값 : " + expense_infoDTO);
        System.out.println("==================================");
        try {
            List<Expense_infoDTO> list = boardService.expenseRead(expense_infoDTO);
            System.out.println("[ 경비관리[모달창]   ] 결과값 expense_infoDTO 값 : " + expense_infoDTO);
            if (list == null) {
                return null;
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //경비처리 ( search )
    @RequestMapping("expenseSearch")
    public List<Expense_infoDTO> expenseSearch(@RequestBody Expense_infoDTO expense_infoDTO) {
        try {
            System.out.println("==================================");
            System.out.println("[ 경비처리 검색 ] 들어온 inOut_infoDTO: " + expense_infoDTO);
            System.out.println("==================================");
            if (expense_infoDTO.getStartDate() == null && expense_infoDTO.getEndDate() == null && expense_infoDTO.getEmpName() != null) {
                List<Expense_infoDTO> searchName = boardService.expenseName(expense_infoDTO);
                System.out.println("이름검색 : " + searchName);
                if (searchName != null) {
                    return searchName;
                } else {
                    return null;
                }

            }
            if (expense_infoDTO.getEmpName() == null && expense_infoDTO.getStartDate() != null && expense_infoDTO.getEndDate() != null) {
                List<Expense_infoDTO> searchDate = boardService.expenseEndDate(expense_infoDTO);
                System.out.println("날자 검색 ;" + searchDate);
                if (searchDate != null) {
                    return searchDate;
                } else {
                    return null;
                }
            }
            if (expense_infoDTO.getStartDate() != null && expense_infoDTO.getEmpName() != null && expense_infoDTO.getEndDate() == null) {
                List<Expense_infoDTO> searchstartDate = boardService.expenseStartDateName(expense_infoDTO);
                System.out.println("시작날짜 + 이름만 검색" + searchstartDate);
                if (searchstartDate != null) {
                    return searchstartDate;
                } else {
                    return null;
                }
            }
            if (expense_infoDTO.getStartDate() != null && expense_infoDTO.getEndDate() == null && expense_infoDTO.getEmpName() == null) {
                List<Expense_infoDTO> checklast = boardService.expenseStartDate(expense_infoDTO);
                System.out.println("시작날짜  검색" + checklast);
                if (checklast != null) {
                    return checklast;
                } else {
                    return null;
                }
            }
            if (expense_infoDTO.getEndDate() != null && expense_infoDTO.getStartDate() == null && expense_infoDTO.getEmpName() == null) {
                List<Expense_infoDTO> checklast = boardService.expenseEndDate(expense_infoDTO);
                System.out.println("종료날짜검색" + checklast);
                if (checklast != null) {
                    return checklast;
                } else {
                    return null;
                }
            }
            if (expense_infoDTO.getEndDate() != null && expense_infoDTO.getStartDate() == null && expense_infoDTO.getEmpName() != null) {
                List<Expense_infoDTO> checklast = boardService.expenseEndDateName(expense_infoDTO);
                System.out.println("종료날짜 + 이름" + checklast);
                if (checklast != null) {
                    return checklast;
                } else {
                    return null;
                }
            }
            List<Expense_infoDTO> list = boardService.expenseSearch(expense_infoDTO);
            System.out.println("둘다검색" + list);
            if (list != null) {
                return list;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 경리/회계 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
    //거래처 관리 (create)
    @RequestMapping("clientCreate")
    public boolean clientCreate(@RequestBody Client_infoDTO client_infoDTO) {
        System.out.println("==================================");
        System.out.println("[ 거레처관리 create] 들어온 client_infoDTO 값 : " + client_infoDTO);
        System.out.println("==================================");
        try {
            int check = boardService.clientCreate(client_infoDTO);
            System.out.println("[  거레처관리] 들어온  값 : " + check);
            if (check == 0) {
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //거래처관리 (read)
    //param : compCode
    @RequestMapping("clientRead")
    public List<Client_infoDTO> clientRead(@RequestBody Client_infoDTO client_infoDTO) {
        // param : compCode
        System.out.println("==================================");
        System.out.println("[거레처관리 read 들어온 expense_infoDTO 값 : " + client_infoDTO);
        System.out.println("==================================");
        try {
            List<Client_infoDTO> list = boardService.clientRead(client_infoDTO);
            System.out.println("[ 거레처관리 read 들어온 ] 값 : " + client_infoDTO);
            if (list == null) {
                return null;
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //거래처관리 ( update )
    //parm :clientCompNum
    @RequestMapping("clientUpdate")
    public boolean clientUpdate(@RequestBody Client_infoDTO client_infoDTO) {
        System.out.println("==================================");
        System.out.println("[거래처관리 수정 client_infoDTO 값 : " + client_infoDTO);
        System.out.println("==================================");
        try {
            int check = boardService.clientUpdate(client_infoDTO);
            System.out.println("[ 거래처관리] -  수정 ] 결과값 check 값 : " + check);
            if (check == 0) {
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //거래처관리 (delete)
    //parm :clientCompNum
    @RequestMapping("clientDelete")
    public boolean clientDelete(@RequestBody Client_infoDTO client_infoDTO) {
        System.out.println("==================================");
        System.out.println("[거래처관리  삭제 들어온 expense_infoDTO 값 : " + client_infoDTO);
        System.out.println("==================================");
        try {
            int check = boardService.clientDelete(client_infoDTO);
            System.out.println("[ 거래처관리  삭제 들어온] -  수정 ] 결과값 check 값 : " + check);
            if (check == 0) {
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //거레처 관리 모달창
    //parm :clientCompNum
    @RequestMapping("clientModal")
    public List<Client_infoDTO> clientModal(@RequestBody Client_infoDTO client_infoDTO) {
        System.out.println("==================================");
        System.out.println("[거레처관리 모달창 들어온 client_infoDTO 값 : " + client_infoDTO);
        System.out.println("==================================");
        try {
            List<Client_infoDTO> list = boardService.clientModal(client_infoDTO);
            System.out.println("[ 거래처관리 모달창  ] 값 : " + client_infoDTO);
            if (list == null) {
                return null;
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //거래 상세페이지
    @RequestMapping("clientdetail")
    public List<IncomeDTO> clientdetail(@RequestBody Client_infoDTO client_infoDTO){
        System.out.println("==================================");
        System.out.println("[거래상세페이지  들어온 client_infoDTO 값 : " + client_infoDTO);
        System.out.println("==================================");
        try {
            List<IncomeDTO> list = boardService.clientdetail(client_infoDTO);
            System.out.println("[ 거래상세페이지  ] 값 : " + client_infoDTO);
            System.out.println("거래상세 return값 : " + list);
            if (list == null) {
                return null;
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    //매입관리 (create)
    @RequestMapping("income_Create")
    public boolean income_outcomeCreate(@RequestBody IncomeDTO income_outcomeDTO) {
        System.out.println("==================================");
        System.out.println("[ 매입관리 create] 들어온 client_infoDTO 값 : " + income_outcomeDTO);
        System.out.println("==================================");
        try {
            if (income_outcomeDTO.getP_supplyValue() == 0 && income_outcomeDTO.getP_surTax() == 0 && income_outcomeDTO.getP_totalPrice() == 0) {
                if (income_outcomeDTO.getP_taxType().equals("비과세")) {
                    income_outcomeDTO.setP_supplyValue(income_outcomeDTO.getP_iCount() * income_outcomeDTO.getP_unitPrice());     //공급가액 수량 * 단가
                    income_outcomeDTO.setP_totalPrice(income_outcomeDTO.getP_supplyValue());    // 공급가액 set
                } else {
                    income_outcomeDTO.setP_supplyValue(income_outcomeDTO.getP_iCount() * income_outcomeDTO.getP_unitPrice());     //공급가액 수량 * 단가
                    income_outcomeDTO.setP_surTax((income_outcomeDTO.getP_supplyValue() / 10));                            //부가세에 SET
                    income_outcomeDTO.setP_totalPrice(income_outcomeDTO.getP_supplyValue() + income_outcomeDTO.getP_surTax());    //부가세 + 공급가액 set
                }

            }
            int check = boardService.income_Create(income_outcomeDTO);
            System.out.println("[  매입관리] 들어온  값 : " + check);
            if (check == 0) {
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    //매입매출 파일, json create
    @RequestMapping("taxfile_create")
    public boolean taxfile_create(@RequestPart(value = "file", required = false)MultipartFile multipartFile, @RequestPart(value = "request") IncomeDTO incomeDTO){
        System.out.println("-----------------파일 : "+ multipartFile);
        System.out.println("----------------매입매출 파일 dto:"+ incomeDTO);
        try{
            int count = boardService.getCount(incomeDTO);
            count++;
            incomeDTO = s3UploaderService2.upload(incomeDTO, multipartFile, "taxBill",count);
//            String separ = File.separator;
//            String today = new SimpleDateFormat("yyMMdd").format(new Date());
//            File file = new File("");
//            String rootPath = file.getAbsolutePath().split("src")[0];
//            String savePath = rootPath + separ + "taxPaper" + separ + today;
//            if(!new File(savePath).exists()){
//                try {
//                    new File(savePath).mkdirs();
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//            }
//            String originFileName = multipartFile.getOriginalFilename();
//            String saveFileName = UUID.randomUUID().toString() + originFileName.substring(originFileName.lastIndexOf("."));
//            String filePath = savePath + separ + saveFileName;
//            multipartFile.transferTo(new File(filePath));
//            incomeDTO.setTaxBill(filePath);
            if (incomeDTO.getP_supplyValue() == 0 && incomeDTO.getP_surTax() == 0 && incomeDTO.getP_totalPrice() == 0) {
                if (incomeDTO.getP_taxType().equals("비과세")) {
                    incomeDTO.setP_supplyValue(incomeDTO.getP_iCount() * incomeDTO.getP_unitPrice());     //공급가액 수량 * 단가
                    incomeDTO.setP_totalPrice(incomeDTO.getP_supplyValue());    // 공급가액 set
                } else {
                    incomeDTO.setP_supplyValue(incomeDTO.getP_iCount() * incomeDTO.getP_unitPrice());     //공급가액 수량 * 단가
                    incomeDTO.setP_surTax((incomeDTO.getP_supplyValue() / 10));                            //부가세에 SET
                    incomeDTO.setP_totalPrice(incomeDTO.getP_supplyValue() + incomeDTO.getP_surTax());    //부가세 + 공급가액 set
                }
            }
            int check = boardService.taxfile_create(incomeDTO);
            System.out.println("check값 : " + check);
            if(check == 1){
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return false;
    }

    //매입관리 read
    //parm : compCode
    @RequestMapping("income_Read")
    public List<IncomeDTO> income_outcomeRead(@RequestBody IncomeDTO incomeDTO) {
        // param : compCode
        System.out.println("==================================");
        System.out.println("[매입관리 read 들어온 incomeDTO 값 : " + incomeDTO);
        System.out.println("==================================");
        try {
            List<IncomeDTO> list = boardService.income_Read(incomeDTO);
            List<IncomeDTO> total = boardService.income_total(incomeDTO);
            List<IncomeDTO> total1 = boardService.income_total1(incomeDTO);
            for (int i = 0; i < list.size(); i++) {
                list.get(i).setTotalsupplyValue(total.get(0).getTotalsupplyValue());
                list.get(i).setTotalsurTax(total.get(0).getTotalsurTax());
                list.get(i).setTotal(total.get(0).getTotal());
                list.get(i).setTotalsupplyValue1(total1.get(0).getTotalsupplyValue());
                list.get(i).setTotalsurTax1(total1.get(0).getTotalsurTax());
                list.get(i).setTotal1(total1.get(0).getTotal());
            }
            System.out.println("[ 매입관리 read 들어온 ] 값 : " + incomeDTO);
            if (list == null) {
                return null;
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //매입매출 상세페이지
    //param: purchaseId
    @RequestMapping("income_Modal")
    public List<IncomeDTO> income_Modal(@RequestBody IncomeDTO incomeDTO) {
        System.out.println("==================================");
        System.out.println("[매입관리 상세페이지 들어온 incomeDTO 값 : " + incomeDTO);
        System.out.println("==================================");
        try {
            List<IncomeDTO> list = boardService.income_Modal(incomeDTO);
            System.out.println("매입관리 모달창 return값 :" +list );
            if (list == null) {
                return null;
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    //세금계산서 정보
    @RequestMapping("taxPaper")
    public List<TaxPaperDTO> taxPaper(@RequestBody TaxPaperDTO taxPaperDTO){
        try {
            System.out.println("pk값 : " + taxPaperDTO);
            System.out.println("compCode값 : " + taxPaperDTO);
            int[] purchaseList = taxPaperDTO.getPurchaseList();
            List<String> getClient  = new ArrayList<>();
            List<TaxPaperDTO> getCompany = new ArrayList<>();
            List<TaxPaperDTO> getClientInfo = new ArrayList<>();
            TaxPaperDTO getTaxPaper = new TaxPaperDTO();
            List<TaxPaperDTO> getTotalPay = new ArrayList<>();
            List<TaxPaperDTO> finalPrint = new ArrayList<>();
            int temp = 0;   //공급가액 합계
            int temp1 = 0;  //부가세 합계
            int temp2 = 0;  //합계 금액 합계
            for(int i = 0; i < purchaseList.length; i++){
                getTotalPay = boardService.getTotalPay(purchaseList[i]);           //총합금액 get
                System.out.println("합계금액 출력:" + getTotalPay);
                temp += getTotalPay.get(0).getTotalsupplyValue();
                temp1 += getTotalPay.get(0).getTotalsurTax();
                temp2 += getTotalPay.get(0).getTotalPrice();
            }

            for (int i = 0; i < purchaseList.length; i++) {
                getClient = boardService.getClientInfo(purchaseList[i]);    //거래내역 사업자등록번호 get
                System.out.println("사업자등록번호 : " + getClient);
                System.out.println(getClient.size());
                getCompany = boardService.getCompanyinfo(taxPaperDTO.getCompCode());  //우리회사정보 get
                System.out.println("getCompany:" + getCompany);
                getClientInfo = boardService.getClientInfo1(getClient.get(0));      //거래처 정보 get
                System.out.println("getClientInfo:" + getClientInfo);
                getTaxPaper = boardService.getTransaction(purchaseList[i]);         //거래내역 get
                System.out.println("getTaxPaper: "+ getTaxPaper);

                getTaxPaper.setClientCompNum(getClientInfo.get(0).getClientCompNum());
                getTaxPaper.setClientName(getClientInfo.get(0).getClientName());
                getTaxPaper.setClientCEO(getClientInfo.get(0).getClientCEO());
                getTaxPaper.setClientstate(getClientInfo.get(0).getClientstate());
                getTaxPaper.setClientevent(getClientInfo.get(0).getClientevent());
                getTaxPaper.setClientAddress(getClientInfo.get(0).getClientAddress());
                getTaxPaper.setClientEmail(getClientInfo.get(0).getCompEmail());
                getTaxPaper.setCompNum(getCompany.get(0).getCompNum());
                getTaxPaper.setCompName(getCompany.get(0).getCompName());
                getTaxPaper.setCompName(getCompany.get(0).getCompName());
                getTaxPaper.setCompAddress(getCompany.get(0).getCompAddress());
                getTaxPaper.setCompType(getCompany.get(0).getCompType());
                getTaxPaper.setCompItems(getCompany.get(0).getCompItems());
                getTaxPaper.setCompEmail(getCompany.get(0).getCompEmail());

                getTaxPaper.setTotalsupplyValue(temp);
                getTaxPaper.setTotalsurTax(temp1);
                getTaxPaper.setTotalPrice(temp2);

                finalPrint.add(getTaxPaper);
            }
            System.out.println("전자세금계산서 출력값:" + finalPrint);
            return finalPrint;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    //매입관리 update
    //param: purchaseId
    @RequestMapping("income_Update")
    public boolean income_outcomeUpdate(@RequestBody IncomeDTO incomeDTO) {
        System.out.println("==================================");
        System.out.println("[매입관리 수정 income_outcomeDTO 값 : " + incomeDTO);
        System.out.println("==================================");
        try {
            int check = boardService.income_Update(incomeDTO);
            System.out.println("[ 매입관리] -  수정 ] 결과값 check 값 : " + check);
            if (check == 0) {
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //매입관리 delete
    //param : purchaseId
    @RequestMapping("income_Delete")
    public boolean income_outcomeDelete(@RequestBody IncomeDTO incomeDTO) {
        System.out.println("==================================");
        System.out.println("[매입관리 삭제 income_outcomeDTO 값 : " + incomeDTO);
        System.out.println("==================================");
        try {
            int check = boardService.income_Delete(incomeDTO);
            System.out.println("[ 매입관리] -  삭제 ] 결과값 check 값 : " + check);
            if (check == 0) {
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //매입검색
    @RequestMapping("income_only")
    public List<IncomeDTO> income_only(@RequestBody IncomeDTO incomeDTO) {
        // param : compCode
        System.out.println("==================================");
        System.out.println("[매입관리 read 들어온 incomeDTO 값 : " + incomeDTO);
        System.out.println("==================================");
        try {
            List<IncomeDTO> list = boardService.income_income(incomeDTO);
            List<IncomeDTO> total = boardService.income_incomeTotal(incomeDTO);
            for (int i = 0; i < list.size(); i++) {
                list.get(i).setTotalsupplyValue(total.get(0).getTotalsupplyValue());
                list.get(i).setTotalsurTax(total.get(0).getTotalsurTax());
                list.get(i).setTotal(total.get(0).getTotal());
            }
            System.out.println("매입 only 출력값: " + list);
            if (list == null) {
                return null;
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //매출검색
    @RequestMapping("outcome_only")
    public List<IncomeDTO> outcome_only(@RequestBody IncomeDTO incomeDTO) {
        // param : compCode
        System.out.println("==================================");
        System.out.println("[매출관리 read 들어온 incomeDTO 값 : " + incomeDTO);
        System.out.println("==================================");
        try {
            List<IncomeDTO> list = boardService.income_outcome(incomeDTO);
            List<IncomeDTO> total = boardService.income_outcomeTotal(incomeDTO);
            for (int i = 0; i < list.size(); i++) {
                list.get(i).setTotalsupplyValue(total.get(0).getTotalsupplyValue());
                list.get(i).setTotalsurTax(total.get(0).getTotalsurTax());
                list.get(i).setTotal(total.get(0).getTotal());
            }
            System.out.println("[ 매출관리 read 들어온 ] 값 : " + incomeDTO);
            if (list == null) {
                return null;
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // 매입관리 search
    @RequestMapping("income_Search")
    public List<IncomeDTO> income_outcomeSearch(@RequestBody IncomeDTO incomeDTO) {
        try {
            System.out.println("==================================");
            System.out.println("[ 매입관리 ] 들어온 incomeDTO: " + incomeDTO);
            System.out.println("==================================");

            if (incomeDTO.getStartDate() == null && incomeDTO.getEndDate() == null && incomeDTO.getP_clientName() != null) {
                List<IncomeDTO> searchName = boardService.income_Name(incomeDTO);
                List<IncomeDTO> searchNameTotal = boardService.income_NameTotal(incomeDTO);
                for (int i = 0; i < searchName.size(); i++) {
                    searchName.get(i).setTotalsupplyValue(searchNameTotal.get(0).getTotalsupplyValue());
                    searchName.get(i).setTotalsurTax(searchNameTotal.get(0).getTotalsurTax());
                    searchName.get(i).setTotal(searchNameTotal.get(0).getTotal());
                }
                System.out.println("공급처명 검색 : " + searchName);
                if (searchName != null) {
                    return searchName;
                } else {
                    return null;
                }
            }
            if (incomeDTO.getP_clientName() == null && incomeDTO.getStartDate() != null && incomeDTO.getEndDate() != null) {
                List<IncomeDTO> searchDate = boardService.income_Date(incomeDTO);
                List<IncomeDTO> searchNameTotal = boardService.income_DateTotal(incomeDTO);
                for (int i = 0; i < searchDate.size(); i++) {
                    searchDate.get(i).setTotalsupplyValue(searchNameTotal.get(0).getTotalsupplyValue());
                    searchDate.get(i).setTotalsurTax(searchNameTotal.get(0).getTotalsurTax());
                    searchDate.get(i).setTotal(searchNameTotal.get(0).getTotal());
                }
                System.out.println("날짜 검색 " + searchDate);
                if (searchDate != null) {
                    return searchDate;
                } else {
                    return null;
                }
            }
            if (incomeDTO.getStartDate() != null && incomeDTO.getEndDate() == null && incomeDTO.getP_clientName() == null) {
                List<IncomeDTO> checklast = boardService.income_StartDate(incomeDTO);
                List<IncomeDTO> searchNameTotal = boardService.income_StartDateTotal(incomeDTO);
                for (int i = 0; i < checklast.size(); i++) {
                    checklast.get(i).setTotalsupplyValue(searchNameTotal.get(0).getTotalsupplyValue());
                    checklast.get(i).setTotalsurTax(searchNameTotal.get(0).getTotalsurTax());
                    checklast.get(i).setTotal(searchNameTotal.get(0).getTotal());
                }
                System.out.println("시작날짜  검색" + checklast);
                if (checklast != null) {
                    return checklast;
                } else {
                    return null;
                }
            }
            if (incomeDTO.getStartDate() != null && incomeDTO.getEndDate() == null && incomeDTO.getP_clientName() != null) {
                List<IncomeDTO> checklast = boardService.income_StartDate(incomeDTO);
                List<IncomeDTO> searchNameTotal = boardService.income_StartDateNameTotal(incomeDTO);
                for (int i = 0; i < checklast.size(); i++) {
                    checklast.get(i).setTotalsupplyValue(searchNameTotal.get(0).getTotalsupplyValue());
                    checklast.get(i).setTotalsurTax(searchNameTotal.get(0).getTotalsurTax());
                    checklast.get(i).setTotal(searchNameTotal.get(0).getTotal());
                }
                System.out.println("시작날짜 + 공급처명 검색" + checklast);
                if (checklast != null) {
                    return checklast;
                } else {
                    return null;
                }
            }
            if (incomeDTO.getEndDate() != null && incomeDTO.getStartDate() == null && incomeDTO.getP_clientName() == null) {
                List<IncomeDTO> checklast = boardService.income_EndDate(incomeDTO);
                List<IncomeDTO> searchNameTotal = boardService.income_EndDateTotal(incomeDTO);
                for (int i = 0; i < checklast.size(); i++) {
                    checklast.get(i).setTotalsupplyValue(searchNameTotal.get(0).getTotalsupplyValue());
                    checklast.get(i).setTotalsurTax(searchNameTotal.get(0).getTotalsurTax());
                    checklast.get(i).setTotal(searchNameTotal.get(0).getTotal());
                }
                System.out.println("종료날짜검색" + checklast);
                if (checklast != null) {
                    return checklast;
                } else {
                    return null;
                }
            }
            if (incomeDTO.getEndDate() != null && incomeDTO.getStartDate() == null && incomeDTO.getP_clientName() != null) {
                List<IncomeDTO> checklast = boardService.income_EndDateName(incomeDTO);
                List<IncomeDTO> searchNameTotal = boardService.income_EndDateNameTotal(incomeDTO);
                for (int i = 0; i < checklast.size(); i++) {
                    checklast.get(i).setTotalsupplyValue(searchNameTotal.get(0).getTotalsupplyValue());
                    checklast.get(i).setTotalsurTax(searchNameTotal.get(0).getTotalsurTax());
                    checklast.get(i).setTotal(searchNameTotal.get(0).getTotal());
                }
                System.out.println("종료날짜 + 공급처검색" + checklast);
                if (checklast != null) {
                    return checklast;
                } else {
                    return null;
                }
            }
            if (incomeDTO.getStartDate() != null && incomeDTO.getEndDate() != null && incomeDTO.getP_clientName() != null) {
                List<IncomeDTO> list = boardService.income_Search(incomeDTO);
                List<IncomeDTO> searchNameTotal = boardService.income_SearchTotal(incomeDTO);
                for (int i = 0; i < list.size(); i++) {
                    list.get(i).setTotalsupplyValue(searchNameTotal.get(0).getTotalsupplyValue());
                    list.get(i).setTotalsurTax(searchNameTotal.get(0).getTotalsurTax());
                    list.get(i).setTotal(searchNameTotal.get(0).getTotal());
                }
                System.out.println("둘다검색" + list);
                if (list != null) {
                    return list;
                } else {
                    return null;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    //한글명주소 -> 위경도 변환
    @RequestMapping("getAddress")
    public Map<String, String> getAddress(@RequestParam("address") String address){
        try {
            return boardService.getAddress(address);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
