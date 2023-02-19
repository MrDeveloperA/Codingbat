package com.example.codingbat.diler;

import com.example.codingbat.entity.Attachment;
import com.example.codingbat.entity.MiscCodeTheme;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MiscCodePracticeDto {
    private Integer miscCodeTheme;
    private Integer attachment;
}
