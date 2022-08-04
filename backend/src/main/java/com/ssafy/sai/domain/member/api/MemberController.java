package com.ssafy.sai.domain.member.api;

import com.ssafy.sai.domain.member.domain.Member;
import com.ssafy.sai.domain.member.dto.*;
import com.ssafy.sai.domain.member.dto.request.FindIdRequest;
import com.ssafy.sai.domain.member.dto.request.MemberUpdateRequest;
import com.ssafy.sai.domain.member.dto.response.MemberResponse;
import com.ssafy.sai.domain.member.exception.MemberException;
import com.ssafy.sai.domain.member.exception.MemberExceptionType;
import com.ssafy.sai.domain.member.service.MemberService;
import com.ssafy.sai.global.common.DataResponse;
import com.ssafy.sai.global.common.MessageResponse;
import com.ssafy.sai.global.util.auth.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/members")
@RestController
@CrossOrigin("*")
public class MemberController {

    private final MemberService memberService;

    /**
     * @param id 조회할 교육생의 PK
     * @return 조회한 교육생의 정보
     * @throws Exception 잘못된 접근일 때 예외 발생
     * @메소드 교육생 정보 조회 컨트롤러
     */
    @GetMapping("/member/{id}")
    public ResponseEntity<? extends MessageResponse> findMember(@PathVariable Long id) {
        MemberDto findMember = memberService.findMemberOne(id);
        return ResponseEntity.ok().body(new MessageResponse<>(findMember));
    }

    /**
     * @param id 조회할 컨설턴트의 PK
     * @return 조회한 컨설턴트의 정보
     * @throws Exception 잘못된 접근일 때 예외 발생
     * @메소드 컨설턴트 정보 조회 컨트롤러
     */
    @GetMapping("consultant/{id}")
    public ResponseEntity<? extends MessageResponse> findConsultant(@PathVariable Long id) {
        ConsultantDto findMember = memberService.findConsultantOne(id);
        return ResponseEntity.ok().body(new MessageResponse<>(findMember));
    }

    /**
     * @param id      정보를 수정할 회원의 PK
     * @param request 회원 정보 수정 폼 양식
     * @return 변경한 회원의 이메일과 이름
     * @throws Exception 잘못된 접근, 이미 존재하는 휴대전화 번호로 변겅할 때 예외 발생
     * @메소드 회원 정보 수정 컨트롤러
     */
    @PutMapping("/member/{id}")
    public ResponseEntity<? extends MessageResponse> updateMember(@PathVariable("id") Long id, @RequestBody @Valid MemberUpdateRequest request) {
        MemberResponse findMember = memberService.updateMember(id, request);
        return ResponseEntity.ok().body(new MessageResponse<>(findMember));
    }

    /**
     * @param passwordDto 비밀번호 변경 폼 양식(old password, new password, new password check)
     * @return 변경한 회원의 이메일과 이름
     * @throws Exception 잘못된 접근일 때 예외 발생
     * @메소드 회원 비밀번호 변경 컨트롤러
     */
    @PostMapping("/password")
    public ResponseEntity<? extends MessageResponse> updatePassword(@Valid @RequestBody PasswordDto passwordDto) {
        MemberResponse findMember = memberService.updatePassword(passwordDto);
        return ResponseEntity.ok().body(new MessageResponse<>(findMember));
    }

    @PostMapping
    public ResponseEntity<? extends MessageResponse> findMemberId(@Valid @RequestBody FindIdRequest request) {
        MemberResponse findMember = memberService.findMemberId(request);
        return ResponseEntity.ok().body(new MessageResponse<>(findMember));
    }
}