package com.springr.first.domain;

import com.springr.first.service.auth.UserDetailsImpl;
import lombok.Data;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.*;
import java.util.Date;


@Data
@Entity
@Table(name = "myfirstrow")
@NamedQueries({
        @NamedQuery(name = "MyFirstRowEntity.findAll", query = "SELECT u FROM MyFirstRowEntity u"),
        @NamedQuery(name = "MyFirstRowEntity.findByCreator", query = "SELECT u FROM MyFirstRowEntity u WHERE u.creator = :creator"),
})
public class MyFirstRowEntity {

    @Id
    @GeneratedValue
    private Long idGenerated;


    @OneToOne(fetch = FetchType.EAGER)
    private User creator;

    @OneToOne(fetch = FetchType.EAGER)
    private User lastModifier;


    @Column(name = "create_at", insertable = false, nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    // https://www.concretepage.com/java/jpa/jpa-entitylisteners-example-with-callbacks-prepersist-postpersist-postload-preupdate-postupdate-preremove-postremove

    @PrePersist
    protected void onPersist() {
        UserDetailsImpl current = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        creator = current.getUser();
    }


    @PreUpdate
    protected void onUpdate() {
        updated = new Date();
        UserDetailsImpl current = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        lastModifier = current.getUser();
    }


    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;

    private Integer id;
    private String issueId;
    private Integer valami;
    private Integer groupId;
    private String subGroupId;
    private Date date;
    private Long mtid;
    private Integer cg;
    private String segm;
    private String ossnr;
    private Long accmtid;
    private Integer agree;
}
