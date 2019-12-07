package ru.dnina.server.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;
    private String name;
    private String phone;

    private String hashPassword;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Token> tokens;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getId().equals(user.getId()) &&
                getLogin().equals(user.getLogin()) &&
                getName().equals(user.getName()) &&
                Objects.equals(getPhone(), user.getPhone()) &&
                getHashPassword().equals(user.getHashPassword()) &&
                getRole() == user.getRole() &&
                getTokens().equals(user.getTokens());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getLogin(), getName(), getPhone(), getHashPassword(), getRole(), getTokens());
    }
}
