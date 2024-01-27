package reskilled.mentoring.reskilled.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Job {

    private String title;
    private String city;
    private long salary;
    private Currency currency;
    private List<String> skills;
}
