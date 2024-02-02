package shop.mtcoding.blog.User;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "user_tb")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String username;

    @Column(length = 60, nullable = false)
    private String password;
    private String email;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
