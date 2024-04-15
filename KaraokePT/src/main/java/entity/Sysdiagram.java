package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "sysdiagrams")
public class Sysdiagram {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "diagram_id", nullable = false)
    private Integer id;

    @Column(name = "principal_id", nullable = false)
    private Integer principalId;

    @Column(name = "version")
    private Integer version;
    @Column(name = "definition")
    private byte[] definition;

/*
    TODO [JPA Buddy] create field to map the 'name' column
     Available actions: Define target Java type | Uncomment as is | Remove column mapping
    @Column(name = "name", columnDefinition = "sysname(0, 0) not null")
    private Object name;
*/
}