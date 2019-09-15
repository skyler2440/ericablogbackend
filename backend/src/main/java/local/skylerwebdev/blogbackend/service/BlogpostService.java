package local.skylerwebdev.blogbackend.service;

import local.skylerwebdev.blogbackend.model.Blogpost;

import java.util.List;

public interface BlogpostService
{
    List<Blogpost> findAll();

    Blogpost findBlogpostById(long id);

    List<Blogpost> findByUserName ();

    void delete(long id);

    Blogpost save(Blogpost post);

    Blogpost update(Blogpost post, long id);
}
