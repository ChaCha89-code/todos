package com.github.chacha89.todos.user.service;

import com.github.chacha89.todos.exception.PasswordMismatchException;
import com.github.chacha89.todos.exception.TeamCreateException;
import com.github.chacha89.todos.exception.UserCreateException;
import com.github.chacha89.todos.exception.UserIdNotFoundException;
import com.github.chacha89.todos.team.entity.Team;
import com.github.chacha89.todos.team.repository.TeamRepository;
import com.github.chacha89.todos.todo.repository.TodoRepository;
import com.github.chacha89.todos.user.dto.requestDto.UserCreateRequestDto;
import com.github.chacha89.todos.user.dto.requestDto.UserUpdateRequestDto;
import com.github.chacha89.todos.user.dto.responseDto.APIResponse;
import com.github.chacha89.todos.user.dto.responseDto.UserCreateResponseDto;
import com.github.chacha89.todos.user.dto.responseDto.UserInfoResponseDto;
import com.github.chacha89.todos.user.entity.User;
import com.github.chacha89.todos.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import static com.github.chacha89.todos.user.dto.responseDto.APIResponse.success;

// 서비스(권장): 도메인 데이터 또는 Dto 반환 O. ResponseEntity 반환 X.
@Service
public class UserService {
    // 속성
    private final UserRepository userRepository;
    private final TeamRepository teamRepository;
    private final PasswordEncoder passwordEncoder;
    private final TodoRepository todoRepository;

    @Value("${file.path}") // 이미지 업로드 파일 경로
    private String uploadFolder;

    // 생성자
    public UserService(UserRepository userRepository, TeamRepository teamRepository, PasswordEncoder passwordEncoder, TodoRepository todoRepository) {
        this.teamRepository = teamRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.todoRepository = todoRepository;
    }

    /**
     * 회원 가입 기능
     */
//    public UserCreateResponseDto createUserService(UserCreateRequestDto requestDto) {
//        // 1. 데이터 준비
//        String teamName = requestDto.getTeamName();
//        String userName = requestDto.getUserName();
//        String email = requestDto.getEmail();
//        String password = requestDto.getPassword();
//        String checkPassword = requestDto.getCheckPassword();
//        MultipartFile userImage = requestDto.getUserImage();
//
//        // 1-1) 사진 파일 준비
//        String url = null;
//        if (userImage != null) {
//            UUID uuid = UUID.randomUUID(); // uuid : 파일 이름 앞에 붙여서 이름 겹치지 않게 도와줌.
//            String imageFileName = uuid + "_" + requestDto.getUserImage().getOriginalFilename();
//            System.out.println("파일 이미지 이름 " + imageFileName);
//            url = uploadFolder + imageFileName;
//            Path imageFilePath = Paths.get(url); // 자원경로(String) -> Path 클래스로 만들기
//
//            // 아래 코드는 이미지 바이트 데이터를 이미지 파일경로(imageFilePath의 파일 경로)에 기록해준다.
//            try {
//                Files.write(imageFilePath, requestDto.getUserImage().getBytes());
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//
//        // 빈 값 체크
//        if (teamName == null || teamName.isBlank() ||
//                userName == null || userName.isBlank() ||
//                email == null || email.isBlank() ||
//                password == null || password.isBlank() ||
//                checkPassword == null || checkPassword.isBlank()) {
//            throw new UserCreateException(400, "필수 회원가입 입력정보 중 빈 항목이 있습니다.");
//        }
//
//        // 회원이 팀에 속해있는지 체크
//        Team foundTeam = teamRepository.findByTeamName(teamName).orElseThrow(() -> new UserCreateException(400, "팀이 존재하지 않습니다."));
//
//        // 이메일 중복 체크 (유저레포지토리에 existsByEmail 메서드 추가된 상태에서 작동)
//        if (userRepository.existsByEmail(email)) {
//            throw new UserCreateException(400, "이미 존재하는 아아디입니다.");
//        }
//
//        // 아이디가 이메일이 아닌 경우 : 이메일이 정규식 "문자열"과 같은지를 비교(.matches)
//        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
//        if (!email.matches(emailRegex)) {
//            throw new UserCreateException(400, "아이디는 이메일 형식이어야 합니다.");
//        } // email.equals(emailRegex): 이메일이 정규식 "문자열"과 같은지를 비교
//
//        // 비밀번호 복잡도 체크
//        System.out.println("비밀번호 입력값: [ " + password + " ]");
//        System.out.println("일치 여부: " + password.equals(checkPassword));
//
//        if (!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^\\w\\s])[\\S]{8,20}$")) {
//            throw new UserCreateException(400, "비밀번호는 최소 8자리, 최대 20자리이며, 대소문자, 숫자, 특수문자를 포함해야 합니다.");
//        }
//
//        // 비밀번호 확인 일치여부 체크
//        if (!password.equals(checkPassword)) {
//            throw new UserCreateException(400, "비밀번호와 비밀번호 확인이 일치하지 않습니다.");
//        } // matches()는 정규식 비교 함수입니다. 문자열이 같은지 비교하려면 equals()를 써야 합니다.
//
//        // 비밀번호 암호화
//        String encodedPassword = passwordEncoder.encode(password);
//
//        // 2. 엔티티 생성
//        User newUser = new User(foundTeam, userName, email, encodedPassword, url);
//
//        // 3. 저장
//        userRepository.save(newUser);
//
//        // 4. 응답
//        return new UserCreateResponseDto(200, "회원가입이 정상적으로 완료 되었습니다.");
//
//    }
    public UserCreateResponseDto createUserService(UserCreateRequestDto requestDto) {

        String email = requestDto.getEmail();
        String teamName = requestDto.getTeamName();
        String userName = requestDto.getUserName();
        String password = requestDto.getPassword();
        String checkPassword = requestDto.getCheckPassword();
        MultipartFile userImageFile = requestDto.getUserImage();
        //
        Team foundTeam = teamRepository.findByTeamName(teamName)
                .orElseThrow(() -> new TeamCreateException(400, "팀이 존재하지 않습니다."));

        if (userRepository.existsByEmail(email)) {
            throw new UserCreateException(400, "이미 존재하는 아아디입니다.");
        }

        if (requestDto.getPassword().equals(requestDto.getCheckPassword())==false) {
            throw new PasswordMismatchException("비밀번호가 일치하지 않습니다.");
        }
        if (isValidPassword(requestDto.getPassword())==false) {
            throw new UserCreateException(400, "비밀번호는 영문, 숫자, 특수문자를 포함한 8자 이상이어야 합니다.");
        }
        String encodedPassword = passwordEncoder.encode(requestDto.getPassword());

        // 먼저 imageName이라는 문자열 변수를 만들고, 초기값을 null로 설정합니다.
        // 이 변수는 나중에 이미지 파일 이름을 저장할 용도입니다.
        String imageName = null;
        // 이 조건문은 사용자가 이미지를 업로드했는지 확인하는 코드입니다.
        //userImageFile != null: 업로드한 파일 자체가 아예 없는 경우를 방지.
        //!userImageFile.isEmpty(): 파일이 있지만 비어 있지 않은 경우에만 진행하겠다는 뜻입니다.
        if (userImageFile != null && !userImageFile.isEmpty()) {
            imageName = UUID.randomUUID() + "_" + userImageFile.getOriginalFilename();
        //사용자가 업로드한 파일이 있다면, 이미지 이름을 만들어서 imageName 변수에 저장합니다.
        //UUID.randomUUID() : 유일한 식별자(랜덤 문자열)를 생성합니다. → 중복 방지를 위해 사용.
        //userImageFile.getOriginalFilename() : 사용자가 업로드한 원래 파일 이름을 가져옵니다.
        //이 둘을 이어 붙여서 새로운 고유한 파일명을 만듭니다.
        //예:d4a9c67b-d673-4c33-b177-9305dcb07988_dog.jpg

            // 실제 파일 저장 로직은 필요시 추가
            // 예: Files.copy(userImageFile.getInputStream(), targetPath);
        }
        User newUser = new User(foundTeam, userName, email, encodedPassword, imageName);
        userRepository.save(newUser);

        return new UserCreateResponseDto(200, "회원 가입 성공");
    }

    private boolean isValidPassword(String password) {
        return password.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%^&*()_+=-]).{8,}$");
    }


    /**
     * 회원 조회
     *
     * @param id
     * @return
     */
    public UserInfoResponseDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수 없습니다."));
        return UserInfoResponseDto.from(user, "회원을 조회 하였습니다.");

    }

    /**
     * 회원 수정
     */
    public UserCreateResponseDto updateUserAPI(Long id, UserUpdateRequestDto updateRequest) {

        //1. 변경할 user 객체 찾기
        User userToUpdate = userRepository.findById(id).orElseThrow();

        String confirmPassword = updateRequest.getConfirmPassword();
        String newPassword = updateRequest.getNewPassword();
        String newUserImage = updateRequest.getNewUserImage();

        if (!(newUserImage == null) && !(newUserImage.isBlank())) {
            userToUpdate.changeUserImage(newUserImage);
        }

        if (!passwordEncoder.matches(confirmPassword, userToUpdate.getPassword())) {
            throw new UserCreateException(400, "비밀번호가 일치하지 않습니다.");
        }
        if (!(newPassword == null) && newPassword.equals(userToUpdate.getPassword()) && !newPassword.isBlank()) {
            userToUpdate.changePassword(newPassword);
        }

        userRepository.save(userToUpdate);

        return new UserCreateResponseDto(200, "회원 수정이 성공적으로 이루어졌습니다.");
    }

    /**
     * 회원 삭제
     */
    @Transactional
    public UserCreateResponseDto deleteUserAPI(Long id, String rawPassword) {
        User user = userRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new UserIdNotFoundException());
        // 비밀번호 검증 처리
        if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
            throw new PasswordMismatchException("비밀번호가 일치하지 않습니다.");
        }
        //업무 미할당 처리
        todoRepository.findByUser(user)
                .forEach(todo -> todo.changeAssignee("unassigned"));

        user.delete(); // 실제 삭제가 아닌 deleted = true
        return new UserCreateResponseDto(200, "유저 삭제 처리 완료되었습니다.");
    }
}