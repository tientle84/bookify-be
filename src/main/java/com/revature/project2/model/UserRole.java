package com.revature.project2.model;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="user_role")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userRole_id")
    private int id;
    @Column(name="user_Role")
    private String userRole;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRole userRole1 = (UserRole) o;
        return id == userRole1.id && Objects.equals(userRole, userRole1.userRole);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userRole);
    }
}
