package local.skylerwebdev.blogbackend.repository;

import local.skylerwebdev.blogbackend.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User, Long>
{
     User findByUsername(String username);
}
