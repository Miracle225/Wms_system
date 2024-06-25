package com.example.wms_system.controllers;

import com.example.wms_system.dto.GoodAcceptanceDto;
import com.example.wms_system.models.AcceptanceItem;
import com.example.wms_system.models.GoodAcceptance;
import com.example.wms_system.services.AcceptanceItemService;
import com.example.wms_system.services.GoodAcceptanceService;
import com.example.wms_system.services.GoodService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/receptions")
public class AcceptanceController {
    private final GoodAcceptanceService acceptanceService;
    private final AcceptanceItemService itemService;
    private final GoodService goodService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public String getAllAcceptances(
            @RequestParam Optional<String> date,
            @RequestParam Optional<String> start,
            @RequestParam Optional<String> end,
            @RequestParam Optional<String> id,
            Model model
    ) {
        List<GoodAcceptance> acceptances = acceptanceService.getAll();
        float sumPrice = 0.0f;
       float sumVolume = 0.0f;
        for (GoodAcceptance acceptance : acceptances) {
            Integer sumQ = itemService.getAllByAcceptanceId(acceptance.getId()).stream().map(AcceptanceItem::getReceivedQuantity).mapToInt(Integer::intValue).sum();
            acceptance.setQuantity(sumQ);
            for (AcceptanceItem item : itemService.getAllByAcceptanceId(acceptance.getId())) {
                Float price = item.getGood().getPrice();
                Double volume = item.getGood().getVolume();
                volume+= item.getReceivedQuantity();
                price *= item.getReceivedQuantity();
                sumPrice += price;
                sumVolume+=volume;
            }
            acceptance.setPrice(sumPrice);
            acceptance.setAcceptsVolume(sumVolume);
            sumPrice = 0.0f;
            sumVolume = 0.0f;
        }

        if (id.isPresent() && !id.get().isBlank()) {
            Long accId = Long.parseLong(id.get());
            acceptances = acceptanceService.getByIdToList(accId);
        }
        if (date.isPresent() && !date.get().isBlank()) {
            Date sqlDate = convertStringToSqlDate(date.get());
            acceptances = acceptanceService.getAllByAcceptanceDate(sqlDate);
        }
        if (start.isPresent() && end.isPresent() && !start.get().isBlank() && !end.get().isBlank()) {
            Date sqlStart = convertStringToSqlDate(start.get());
            Date sqlEnd = convertStringToSqlDate(end.get());
            acceptances = acceptanceService.getAllByDatePeriod(sqlStart, sqlEnd);
        }
        model.addAttribute("acceptances", acceptances);
        return "receptions";

    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public GoodAcceptance getById(@PathVariable Long id) {
        return acceptanceService.getById(id);
    }

    @GetMapping("add")
    public String showAcceptanceForm(Model model) {
        model.addAttribute("acceptance", new GoodAcceptanceDto());
        return "create-reception";

    }

    @PostMapping("/add")
    String createAcceptance(@ModelAttribute @Valid GoodAcceptanceDto acceptanceDto) {
        acceptanceService.createNewAcceptance(acceptanceDto);
        return "redirect:/receptions";
    }

    @GetMapping("/update/{id}")
    public String showUpdateAcceptanceForm(@PathVariable Long id, Model model) {
        GoodAcceptance acceptance = acceptanceService.getById(id);
        model.addAttribute("acceptance", acceptance);
        return "update-reception";

    }

    @PostMapping("/update/{id}")
    String updateAcceptance(@PathVariable Long id, @ModelAttribute @Valid GoodAcceptanceDto acceptanceDto) {
        acceptanceService.updateAcceptance(id, acceptanceDto);
        return "redirect:/receptions";
    }

    @GetMapping("/delete/{id}")
    String deleteAcceptance(@PathVariable Long id) {
        acceptanceService.deleteAcceptanceById(id);
        return "redirect:/receptions";
    }

    public Date convertStringToSqlDate(String dateString) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            java.util.Date utilDate = format.parse(dateString);
            return new java.sql.Date(utilDate.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
