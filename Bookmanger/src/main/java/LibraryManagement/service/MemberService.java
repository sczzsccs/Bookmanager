package LibraryManagement.service;

import LibraryManagement.data.dto.MemberDTO;
import LibraryManagement.data.entity.MemberEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

public interface MemberService {
    public MemberEntity setMember(MemberDTO memberDTO);

    public MemberEntity getUserId(String userId);
}