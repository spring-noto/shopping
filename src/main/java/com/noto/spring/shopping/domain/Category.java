package com.noto.spring.shopping.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 양방향 매핑 무한루프 해결
 * @JsonManagedReference
 * 참조가 되는 앞부분을 의미하며, 정상적으로 직렬화를 수행한다.
 * Collection Type 에 적용된다.
 * @JsonBackReference
 * 참조가 되는 뒷부분을 의미하며, 직렬화를 수행하지 않는다.
 *
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Category extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    @Column(nullable = false)
    private String name;

    @Column
    private String iconUrl;

    @Column(nullable = false)
    private Boolean activation;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_id")
    @JsonBackReference
    private Category parent;

    @OneToMany(mappedBy = "parent")
    @JsonManagedReference
    private Set<Category> subCategories = new HashSet<>();

}
