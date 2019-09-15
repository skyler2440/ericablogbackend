package local.skylerwebdev.blogbackend.service;

import local.skylerwebdev.blogbackend.model.Blogpost;
import local.skylerwebdev.blogbackend.repository.BlogpostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "todoService")
public class BlogpostServiceImpl implements BlogpostService
{
    @Autowired
    private BlogpostRepository postrepos;
    @Override
    public List<Blogpost> findAll()
    {
        List<Blogpost> postlist = new ArrayList<>();
        postrepos.findAll().iterator().forEachRemaining(postlist::add);
        return postlist;
    }

    @Override
    public Blogpost findBlogpostById(long id)
    {
        return postrepos.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
    }

    @Override
    public List<Blogpost> findByUserName()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<Blogpost> userNameListed = new ArrayList<>();
        postrepos.findAll().iterator().forEachRemaining(userNameListed::add);

        userNameListed.removeIf(t -> t.getUser().getUsername().equalsIgnoreCase(authentication.getName()));
        return userNameListed;
    }
    @Override
    public void delete(long id)
    {

        if(postrepos.findById(id).isPresent())
        {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if(postrepos.findById(id).get().getUser().getUsername().equalsIgnoreCase(authentication.getName()))
            {
                postrepos.deleteById(id);

            }else

            {

                throw new EntityNotFoundException(Long.toString(id) + " " + authentication.getName());
            }
        }else
        {
            throw new EntityNotFoundException(Long.toString(id));
        }

    }
    @Transactional
    @Override
    public Blogpost save(Blogpost post)
    {
        return postrepos.save(post);
    }

    @Override
    public Blogpost update(Blogpost post, long id)
    {
        Blogpost newPost = postrepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
        if(post.getPosttitle() != null)
        {
            newPost.setPosttitle(post.getPosttitle());
        }
        if(post.getPostbody() != null)
        {
            newPost.setPostbody(post.getPostbody());
        }
        if(post.getUser() != null)
        {
            newPost.setUser(newPost.getUser());
        }
        return postrepos.save(newPost);
    }
}
