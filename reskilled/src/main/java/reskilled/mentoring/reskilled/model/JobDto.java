package reskilled.mentoring.reskilled.model;

import lombok.Data;

import java.util.List;

@Data
public class JobDto {

    private String title;
    private String city;
    private long salary;
    private Currency currency;
    private List<String> skills;
}
