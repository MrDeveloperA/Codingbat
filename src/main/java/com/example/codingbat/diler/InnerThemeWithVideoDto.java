package com.example.codingbat.diler;

import com.example.codingbat.entity.Attachment;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InnerThemeWithVideoDto {
    private String innerThemeWithVideo;
    private Integer attachment;
}
