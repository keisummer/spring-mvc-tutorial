package hello.servlet.domain.member;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class MemberRepositoryTest {

    MemberRepository repository = MemberRepository.getInstance();

    @AfterEach
    void afterEach() {
        repository.clearStore();
    }

    @Test
    void save() {
        // given
        Member member = new Member("hello", 20);

        // when
        Member savedMember = repository.save(member);

        // then
        Member findMember = repository.findById(savedMember.getId());
        assertThat(savedMember.getId()).isEqualTo(findMember.getId());
    }

    @Test
    void findAll() {
        // given
        Member member1 = new Member("hello1", 20);
        Member member2 = new Member("hello2", 30);

        //when
        repository.save(member1);
        repository.save(member2);

        //then
        assertThat(repository.findAll().size()).isEqualTo(2);
        assertThat(repository.findAll()).contains(member1, member2);
    }
}