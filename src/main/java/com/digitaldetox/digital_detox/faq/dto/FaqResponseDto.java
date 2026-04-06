package com.digitaldetox.digital_detox.faq.dto;

import com.digitaldetox.digital_detox.faq.entity.Faq;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FaqResponseDto {

    private Long faqId;
    private String question;
    private String answer;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static FaqResponseDto fromFaq(Faq faq) {
        return new FaqResponseDto(
                faq.getFaqId(),
                faq.getQuestion(),
                faq.getAnswer(),
                faq.getCreatedAt(),
                faq.getUpdatedAt()
        );
    }
}
