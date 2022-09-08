package com.example.envers;

import javax.persistence.Table;
import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import lombok.ToString;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

@Entity
@Getter
@Setter
@Audited
@AuditTable(value="user_details_log")
@Table(name = "user_details")
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper=true)
public class UserDetails extends BaseEntity {

    private String firstName;
    private String lastName;

    private Boolean flag;

    public UserDetails(Integer id, String firstName, String lastName) {
        super();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.active = true;
        this.flag = true;
    }
    // @Version Integer version;
}
