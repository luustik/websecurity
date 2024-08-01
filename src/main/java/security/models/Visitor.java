package security.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
public class Visitor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "visitorname")
    @NotEmpty(message = "Поле с именем необходимо заполнить")
    private String visitorname;

    @NotEmpty(message = "Поле с ролью необходимо заполнить")
    @Column(name = "role")
    private String role;

    @Column(name = "purpose")
    private String purpose;

    @NotEmpty(message = "Поле со временем необходимо заполнить")
    @Column(name = "hour")
    private String hour;

    @NotEmpty
    @Column(name = "minute")
    private String minute;

    public Visitor() {
    }

    public Visitor(String visitorname) {
        this.visitorname = visitorname;
    }

    public Visitor(String visitorname, String role, String purpose, String hour, String minute) {
        this.visitorname = visitorname;
        this.role = role;
        this.purpose = purpose;
        this.hour = hour;
        this.minute = minute;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getMinute() {
        return minute;
    }

    public void setMinute(String minute) {
        this.minute = minute;
    }

    public String getVisitorname() {
        return visitorname;
    }

    public void setVisitorname(String visitorname) {
        this.visitorname = visitorname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }
}
