package com.digitaldetox.digital_detox.faq.controller;

import com.digitaldetox.digital_detox.faq.dto.FaqCreateRequestDto;
import com.digitaldetox.digital_detox.faq.dto.FaqUpdateRequestDto;
import com.digitaldetox.digital_detox.faq.service.FaqService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/faqs")
@RequiredArgsConstructor
public class AdminFaqController {

    private final FaqService faqService;

    @PostMapping
    public ResponseEntity<Long> createFaq(@Valid @RequestBody FaqCreateRequestDto faqCreateRequestDto) {

        Long faqId = faqService.createFaq(faqCreateRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(faqId);
    }

    @PutMapping("/{faqId}")
    public ResponseEntity<Void> updateFaq(@PathVariable Long faqId,
                                          @Valid @RequestBody FaqUpdateRequestDto faqUpdateRequestDto) {

        faqService.updateFaq(faqId, faqUpdateRequestDto);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{faqId}")
    public ResponseEntity<Void> deleteFaq(@PathVariable Long faqId) {

        faqService.deleteFaq(faqId);

        return ResponseEntity.noContent().build();
    }
}
