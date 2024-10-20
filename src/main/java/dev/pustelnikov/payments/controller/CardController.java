package dev.pustelnikov.payments.controller;

import dev.pustelnikov.payments.dto.card.CardRegistrationRequestDto;
import dev.pustelnikov.payments.model.UserRole;
import dev.pustelnikov.payments.service.AccountService;
import dev.pustelnikov.payments.service.CardService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("cards")
public class CardController {

    private final CardService cardService;
    private final AccountService accountService;

    @PostMapping("register")
    public String registerCard(CardRegistrationRequestDto cardRegistrationRequestDto, HttpServletRequest request) {
        if (!(accountService.isAccountBelongsToUser(cardRegistrationRequestDto.getAccountId(), request.getUserPrincipal().getName())
                || request.isUserInRole(UserRole.ROLE_ADMIN.name()))) {
            return "redirect:/users/main";
        }
        cardService.registerCard(cardRegistrationRequestDto);
        return "redirect:/accounts/%d".formatted(cardRegistrationRequestDto.getAccountId());
    }

    @GetMapping("list")
    @PreAuthorize("hasRole('ADMIN')")
    public String getCardList(Model model) {
        model.addAttribute("cards", cardService.getCardList());
        return "template/admin/cards";
    }
}
