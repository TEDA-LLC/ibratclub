package com.ibratclub.repository;

import com.ibratclub.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Malikov Azizjon  *  15.01.2023  *  18:17   *  IbratClub
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByUsername(String username);
    Optional<User> findByPhoneAndBot_Id(String phone, Long botId);
    Optional<User> findByPhoneAndCompany_Id(String phone, Long companyId);
    Optional<User> findByEmailAndCompany_Id(String email, Long companyId);

    Page<User> findAllByActiveTrue(Pageable pageable);
    List<User> findAllByBot_Id(Long botId);

//    @Query(nativeQuery = true, value = "select * from users inner join users_roles on users.id = users_roles.users_id where users_roles.roles = :role")
//    User getByRole(String role);
    Optional<User> findByBot_IdAndChatId(Long bot_id, String chatId);
    Optional<User> findByBot_IdAndChatIdAndPhone(Long bot_id, String chatId, String phone);
    Optional<User> findByChatId( String chatId);
}
