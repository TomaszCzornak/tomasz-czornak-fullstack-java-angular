package reskilled.mentoring.reskilled.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Data
@Builder
@NoArgsConstructor
public class Job {

    private static final AtomicLong counter = new AtomicLong();
    private Long id;
    private String title;
    private String city;
    private long salary;
    private Currency currency;
    private List<String> skills;

    public Job(Long id,
               String title,
               String city,
               long salary,
               Currency currency,
               List<String> skills) {
        this.id = counter.incrementAndGet();
        this.title = title;
        this.city = city;
        this.salary = salary;
        this.currency = currency;
        this.skills = skills;
    }
}
