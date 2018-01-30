package com.springr.first.repo;

import com.springr.first.domain.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

//@RepositoryRestResource(exported = false)
public interface UserRepository extends CrudRepository<User, Long> {

    // használható az alap JPQL-es kifejezések
    User findByUserName(String userName);


    // saját query-k

    // SELECT
    @Query(value = "SELECT * FROM users where users.user_name = :userName", nativeQuery = true)
    Iterable<User> selectNative(@Param("userName") String userName);

    @Query(value = "from User u where u.userName= :#{#user.userName}", nativeQuery = false)
    Iterable<User> select(@Param("user") User user);


    // UPDATE
    @Modifying
    @Query(value = "UPDATE users set email = 'native' where user_name = :userName", nativeQuery = true)
    Integer updateNative(@Param("userName") String userName);


    @Modifying
    @Query(value = "UPDATE User set email = 'jpa' where userName = :#{#userP.userName}", nativeQuery = false)
    Integer update(@Param("userP") User user);


    //delete
    @Modifying
    @Query(value = "DELETE User where userName = :#{#userP.userName}", nativeQuery = false)
    void deleteC(@Param("userP") User user);

/*
    @Query(value = "UPDATE Users u set u.role = 'admin' where u.userName = :#{#userP.userName}")
    void makeMeAdmin(@Param("userP") User user);
*/

}