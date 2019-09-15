package local.skylerwebdev.blogbackend.repository;

import local.skylerwebdev.blogbackend.model.Blogpost;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BlogpostRepository extends PagingAndSortingRepository<Blogpost, Long>
{
}
