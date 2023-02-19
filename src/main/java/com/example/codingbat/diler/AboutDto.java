package com.example.codingbat.diler;

import com.example.codingbat.entity.Link;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AboutDto {
    private String aboutText;
    private Integer link;
}
