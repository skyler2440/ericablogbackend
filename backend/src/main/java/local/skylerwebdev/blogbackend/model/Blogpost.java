package local.skylerwebdev.blogbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.CreatedDate;

import static javax.persistence.TemporalType.TIMESTAMP;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "blogpost")
public class Blogpost
{
    @ApiModelProperty(name = "postid", value = "Primary Key for Blog Posts", required = true, example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long postid;
    @ApiModelProperty(name = "posttitle", value = "Title of Blog Post", required = false, example = "Example Title")
    private String posttitle;
    @ApiModelProperty(name = "postdate", value = "Date of Blog Post **Auto Generated**", required = false, example = "MM-dd-yyyy")
    @CreatedDate
    @Temporal(TIMESTAMP)
    private Date postdate;
    @ApiModelProperty(name = "postbody", value = "Body of Blog Post", required = false)
    private String postbody;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid",
                nullable = false)
    @JsonIgnoreProperties({"blogpost", "hibernateLazyInitializer"})
    private User user;

    public Blogpost(String posttitle, Date postdate, String postbody, User user)
    {
        this.posttitle = posttitle;
        this.postdate = postdate;
        this.postbody = postbody;
        this.user = user;
    }

    public Blogpost()
    {
    }

    public long getPostid()
    {
        return postid;
    }

    public void setPostid(long postid)
    {
        this.postid = postid;
    }

    public String getPosttitle()
    {
        return posttitle;
    }

    public void setPosttitle(String posttitle)
    {
        this.posttitle = posttitle;
    }

    public Date getPostdate()
    {
        return postdate;
    }

    public void setPostdate(Date postdate)
    {
        this.postdate = postdate;
    }

    public String getPostbody()
    {
        return postbody;
    }

    public void setPostbody(String postbody)
    {
        this.postbody = postbody;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    @Override
    public String toString()
    {
        return "Blogpost{" +
                "postid=" + postid +
                ", posttitle='" + posttitle + '\'' +
                ", postdate=" + postdate +
                ", postbody='" + postbody + '\'' +
                ", user=" + user +
                '}';
    }
}
