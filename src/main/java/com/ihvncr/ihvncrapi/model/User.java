package com.ihvncr.ihvncrapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@JsonIgnoreProperties( { "id" ,"userPassword" , "createDate" ,"updateDate"})
@Table(name="users")
public class User extends BaseClass implements Serializable {

    @Column(name="username", nullable=false, unique=true)
    @NotNull
    private String userName;

    @Column(name="first_name", nullable=false)
    @NotNull
    private String userFirstName;

    @Column(name="last_name", nullable=false)
    @NotNull
    private String userLastName;

    @Column(name="email", nullable=false)
    @NotNull
    private String userEmail;

    @Column(name="password", nullable=false)
    @NotNull
    private String userPassword;


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "USER_ROLE",
            joinColumns = {
                    @JoinColumn(name = "USER_ID")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "ROLE_ID")
            }
    )
    private Set<Role> role;

    @ManyToOne
    private State state;

    @ManyToOne
    private LGA lga;

    @OneToOne
    private Facility facility;
}
