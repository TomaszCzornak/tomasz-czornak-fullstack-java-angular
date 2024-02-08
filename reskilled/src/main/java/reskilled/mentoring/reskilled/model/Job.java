package reskilled.mentoring.reskilled.model;

import jakarta.validation.constraints.*;
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
    @NotBlank(message = "Title cannot be blank")
    private String title;
    @NotBlank(message = "City cannot be blank")
    private String city;
    @Positive(message="Salary should be greater than zero")
    private long salary;
    @NotNull(message = "Currency should not be empty")
    private Currency currency;
    @NotEmpty(message = "list should have at least one element")
    private List<String> skills;

}
