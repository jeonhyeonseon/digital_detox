package com.digitaldetox.digital_detox.faq.controller;

import com.digitaldetox.digital_detox.faq.dto.FaqResponseDto;
import com.digitaldetox.digital_detox.faq.service.FaqService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/faqs")
@RequiredArgsConstructor
public class FaqController {

    private final FaqService faqService;

    @GetMapping
    public ResponseEntity<List<FaqResponseDto>> getFaqs() {
        return ResponseEntity.ok(faqService.getFaqs());
    }

    @GetMapping("/{faqId}")
    public ResponseEntity<FaqResponseDto> getFaq(@PathVariable Long faqId) {
        return ResponseEntity.ok(faqService.getFaq(faqId));
    }
}
