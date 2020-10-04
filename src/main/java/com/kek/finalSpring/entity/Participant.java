package com.kek.finalSpring.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "participant")
public class Participant implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String email;
    private String password;

    @OneToOne(mappedBy = "participant", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
    private ParticipantDetails details;


    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "role", joinColumns = @JoinColumn(name = "id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "participant_conferences",
            joinColumns = { @JoinColumn(name = "participant_id") },
            inverseJoinColumns = { @JoinColumn(name = "conference_id") }
    )
    private Set<Conference> conferences = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
