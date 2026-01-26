package springCorePrinciples.basic.member;

public interface MemberService {

    void join(final Member member);

    Member findMember(final Long memberId);
}
