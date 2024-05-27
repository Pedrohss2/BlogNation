package com.BlogNation.br.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "blogs")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_At", nullable = false)
    private Date created_At;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_At", nullable = false)
    private Date updated_At;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author_id;

    @OneToMany(mappedBy = "blog")
    private List<Comment> comments;
    @ManyToMany
    @JoinTable(name = "user_follow_blog",
            joinColumns =  @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "blog_id"))
    private Set<User> followers;


}
