package reskilled.mentoring.reskilled.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Job {

    private String title;
    private String city;
    private long salary;
    private Currency currency;
    private List<String> skills;
}
