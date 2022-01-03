package hello.core.member;

public class MemberServiceImpl implements MemberService{
    // final일 경우 = or 생성자 를 통해 반드시 값이 할당되어야 한다!
    // 1) 아래 코드는 추상화에도 의존하고 구체화에도 의존하고 있다 (DIP 위반)
    // private final MemberRepository memberRepository = new MemoryMemberRepository();

    // 2) 아래 코드는 추상화에만(MemberRepository라는 인터페이스) 의존하고 있다 (DIP 수행)
    private final MemberRepository memberRepository;

    //AppConfig에서 memberService() 실행시, 아래 생성자 코드가 실행된다
    public MemberServiceImpl(MemberRepository memberRepository) {
        //매개변수로 받은 memberRepository 값을 
        //private final MemberRepository memberRepository; 에 할당한다
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
