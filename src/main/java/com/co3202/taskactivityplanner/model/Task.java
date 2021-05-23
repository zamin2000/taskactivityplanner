package com.co3202.taskactivityplanner.model;

import lombok.*;
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Entity(name = "Task")
@Table(name = "Task")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Task {
    @Id
    @GeneratedValue
    @Type(type = "uuid-char")
    @Column(name = "task_id")
    private UUID id;
    @NotEmpty()
    private String name;
    @NotEmpty()
    @Column(length = 65536)
    private String description;
    @NotNull()
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private boolean isDone;
    private String priority;
    private String tag;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    public Task(@NotEmpty() String name, @NotEmpty() String description, @NotNull() LocalDate date, boolean isDone, String priority, String tag, User owner) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.isDone = isDone;
        this.priority = priority;
        this.tag = tag;
        this.owner = owner;
    }

    // Custom method to calculate number of days remaining for a task
    public long daysRemaining(LocalDate date) {
        LocalDate now = LocalDate.now();
        long diff = now.until(date, ChronoUnit.DAYS);
        return diff;
    }

}
