package reskilled.mentoring.reskilled.model;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Job {

    @Builder.Default
    private UUID uuid = UUID.randomUUID();
    private String title;
    private String city;
    private long salary;
    private Currency currency;
    private List<String> skills;

}
