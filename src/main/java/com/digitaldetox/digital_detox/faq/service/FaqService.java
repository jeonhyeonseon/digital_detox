package com.digitaldetox.digital_detox.faq.service;

import com.digitaldetox.digital_detox.faq.dto.FaqCreateRequestDto;
import com.digitaldetox.digital_detox.faq.dto.FaqResponseDto;
import com.digitaldetox.digital_detox.faq.dto.FaqUpdateRequestDto;
import com.digitaldetox.digital_detox.faq.entity.Faq;
import com.digitaldetox.digital_detox.faq.repository.FaqRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class FaqService {

    private final FaqRepository faqRepository;

    public List<FaqResponseDto> getFaqs() {

        return faqRepository.findAll().stream()
                            .map(FaqResponseDto::fromFaq)
                            .toList();
    }


    public FaqResponseDto getFaq(Long faqId) {

        Faq faq = faqRepository.findById(faqId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 FAQ입니다."));

        return FaqResponseDto.fromFaq(faq);
    }

    public Long createFaq(@Valid FaqCreateRequestDto faqCreateRequestDto) {

        Faq faq = Faq.builder()
                .question(faqCreateRequestDto.getQuestion())
                .answer(faqCreateRequestDto.getAnswer())
                .build();

        return faqRepository.save(faq).getFaqId();
    }

    public void updateFaq(Long faqId, @Valid FaqUpdateRequestDto faqUpdateRequestDto) {

        Faq faq = faqRepository.findById(faqId)
                                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 FAQ입니다."));

        faq.update(faqUpdateRequestDto.getQuestion(), faqUpdateRequestDto.getAnswer());
    }

    public void deleteFaq(Long faqId) {
        
        Faq faq = faqRepository.findById(faqId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 FAQ입니다."));

        faqRepository.delete(faq);
    }
}
