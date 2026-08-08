package com.Sudhanshu.Razorpay.merchant.entity;

import com.Sudhanshu.Razorpay.common.Entity.BaseEntity;
import com.Sudhanshu.Razorpay.common.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="app_user",indexes = {
        @Index(name = "idx_app_user_merchant_id", columnList = "merchant_id")})

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Slf4j
@Builder
public class AppUser extends BaseEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column()
    private UUID ID;
    @ManyToOne(fetch =FetchType.LAZY)
    @JoinColumn(name="merchant_id")
    private Merchant merchant;
    @Column(name = "mail",length=50,nullable = false,unique = true)


    private  String email;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;
    private  String passwordHash;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(
                new SimpleGrantedAuthority("ROLE_" + role)
        );

    }

    @Override
    public @Nullable String getPassword() {
        return passwordHash;
    }

    @Override
    public String getUsername() {
        return email;
    }
}

