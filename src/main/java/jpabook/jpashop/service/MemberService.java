package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) // 읽기 동작들은 readOnly = true를 붙이는 것이 좋음
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    // 회원 가입
    @Transactional  // 얘는 readOnly = true 가 들어가지 않음 -> 메소드에 붙은거를 우선함
    public Long join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    // 회원 전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}

/*
    1. Setter 인젝션 -> 필드 인젝션보다 나음 (나중에 값 변경을 할 수 있음)

    @Autowired
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    -> 하지만 나중에 값을 변경한다는 것은 좋지 않음

    2. 그래서 요즘은 아래와 같은 생성자 인젝션을 사용한다.
    */

    /*
    @Autowired -> 하나인 생성자는 스프링이 인젝션을 해준다
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    3. lombok을 이용해서 생성자를 만들어 줄 수도 있음
    @RequiredArgsConstructor 사용
    */

