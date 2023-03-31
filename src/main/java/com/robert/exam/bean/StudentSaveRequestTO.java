package com.robert.exam.bean;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StudentSaveRequestTO {
    private String id;

    private String name;

    private String lastname;

    private String age;

    private String email;

    private Long timeZoneId;


}
