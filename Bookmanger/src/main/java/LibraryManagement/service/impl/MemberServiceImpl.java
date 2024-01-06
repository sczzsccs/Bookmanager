package LibraryManagement.service.impl;

import LibraryManagement.data.Login.LoginUser;
import LibraryManagement.data.dto.MemberDTO;
import LibraryManagement.data.entity.MemberEntity;
import LibraryManagement.data.repository.MemberRepository;
import LibraryManagement.service.MemberService;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service @Qualifier("Member")
public class MemberServiceImpl implements MemberService, UserDetailsService {
    @Autowired private MemberRepository memberRepository;
    private Logger LOGGER = LoggerFactory.getLogger(MemberServiceImpl.class);

    @Override
    public MemberEntity setMember(@NotNull MemberDTO memberDTO) {
        MemberEntity member = memberRepository.save(memberDTO.ToEntity());
        LOGGER.info("[MemberService] - save complete");
        LOGGER.info("[MemberService] - save  member : "+member);

        return member;
    }

    @Override // MemberEntity를 반환
    public MemberEntity getUserId(String UserId) {
        Optional<MemberEntity> optionalMember = memberRepository.findByMemberId(UserId);

        if (optionalMember.isEmpty()) {
            LOGGER.info("[MemberService] -  getEntity Error!");
            LOGGER.info("User Not Found!(사용자를 찾을 수 없습니다.)");
            return null;
        }

        MemberEntity memberEntity = optionalMember.get();
        LOGGER.info("[MemberService] - get MemberId : "+memberEntity.getMemberId());
        return memberEntity;
    }

    @Override // UserDetails를 반환(스프링 시큐리티 세션사용)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final String userId = username; // 뷰 페이지에서 name속성이 username인 값을 받아옴

        LOGGER.info("[MemberService] - certify userId : "+userId);

        Optional<MemberEntity> optionalMember = memberRepository.findByMemberId(userId);
        optionalMember.orElseThrow(() -> {
            LOGGER.info("[MemberService] -  getEntity Error!");
            return new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
        });

        LoginUser loginUser = new LoginUser(optionalMember.get());
        LOGGER.info("[MemberService] - get MemberId : "+loginUser.getUsername());
        return loginUser;
    }
}