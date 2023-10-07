package com.ihvncr.ihvncrapi.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="role",uniqueConstraints=@UniqueConstraint(columnNames={"role_name"}))
public class Role extends BaseClass implements Serializable {
    @NotNull
    @Column(name="role_name", nullable=false, unique=true)
    private String roleName;

    private String roleDescription;
}
