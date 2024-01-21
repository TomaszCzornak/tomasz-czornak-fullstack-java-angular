package reskilled.mentoring.reskilled.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Jobs {

    private String title;
    private String city;
    private String salary;
    private List<String> skills;
}
