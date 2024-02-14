package reskilled.mentoring.reskilled.domain.model.dto;

import lombok.Data;
import reskilled.mentoring.reskilled.domain.model.entity.Currency;

import java.util.List;

@Data
public class JobDto {

    private String title;
    private String city;
    private long salary;
    private Currency currency;
    private List<String> skills;
}
