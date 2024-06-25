package com.example.wms_system.controllers;

import com.example.wms_system.dto.OperationDto;
import com.example.wms_system.enums.OperationType;
import com.example.wms_system.models.Operation;
import com.example.wms_system.services.OperationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/operations")
public class OperationController {
    private final OperationService operationService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    public String getAllOperations(
            @RequestParam Optional<String> id,
            @RequestParam Optional<String> type,
            @RequestParam Optional<String> opDate,
            @RequestParam Optional<String> start,
            @RequestParam Optional<String> end,
            @RequestParam Optional<String> userId,
            Model model
    ) {
        List<Operation> operations = operationService.getAll();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if (id.isPresent() && !id.get().isBlank()) {
            Long opId = Long.parseLong(id.get());
            operations = operationService.getByIdToList(opId);
        }
        if (type.isPresent() && !type.get().equals("ALL")) {
            OperationType opType = OperationType.valueOf(type.get());
            operations = operationService.getAllByOperationType(opType);
        }
        if (opDate.isPresent() && !opDate.get().isBlank()) {
            try {
                Date date = dateFormat.parse(opDate.get());

                // Set time to start of the day for the startDate
                Calendar startCalendar = Calendar.getInstance();
                startCalendar.setTime(date);
                startCalendar.set(Calendar.HOUR_OF_DAY, 0);
                startCalendar.set(Calendar.MINUTE, 0);
                startCalendar.set(Calendar.SECOND, 0);
                startCalendar.set(Calendar.MILLISECOND, 0);
                operations = operationService.getAllByOperationDate(date);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        if (start.isPresent() && end.isPresent() && !start.get().isBlank() && !end.get().isBlank()) {
            try {
                Date startD = dateFormat.parse(start.get());
                Date endD = dateFormat.parse(end.get());
                Calendar startCalendar = Calendar.getInstance();
                startCalendar.setTime(startD);
                startCalendar.set(Calendar.HOUR_OF_DAY, 0);
                startCalendar.set(Calendar.MINUTE, 0);
                startCalendar.set(Calendar.SECOND, 0);
                startCalendar.set(Calendar.MILLISECOND, 0);

                // Set time to end of the day for the endDate
                Calendar endCalendar = Calendar.getInstance();
                endCalendar.setTime(endD);
                endCalendar.set(Calendar.HOUR_OF_DAY, 23);
                endCalendar.set(Calendar.MINUTE, 59);
                endCalendar.set(Calendar.SECOND, 59);
                endCalendar.set(Calendar.MILLISECOND, 999);
                operations = operationService.getAllByOperationDatePeriod(startD, endD);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        if (userId.isPresent() && !userId.get().isBlank()) {
            Long opId = Long.parseLong(userId.get());
            operations = operationService.getAllByUserId(opId);
        }
        model.addAttribute("operations", operations);
        return "operations";
    }

    @GetMapping("/add")
    public String showCreateOperationForm(Model model) {
        model.addAttribute("operation", new OperationDto());
        model.addAttribute("types", OperationType.values());
        return "create-operation";
    }

    @PostMapping("/add")
    public String createOperation(@ModelAttribute @Valid OperationDto operationDto) {
        operationService.createNewOperation(operationDto);
        return "redirect:/operations";
    }

    @GetMapping("/update/{id}")
    public String showUpdateOperationForm(@PathVariable Long id, Model model) {
        Operation operation = operationService.getById(id);
        model.addAttribute("operation", operation);
        model.addAttribute("types", OperationType.values());
        return "update-operation";

    }

    @PostMapping("/update/{id}")
    public String updateOperation(@PathVariable Long id, @ModelAttribute @Valid OperationDto operationDto) {
        operationService.updateOperation(id, operationDto);
        return "redirect:/operations";
    }

    @GetMapping("/delete/{id}")
    public String deleteOperation(@PathVariable Long id) {
        operationService.deleteOperationById(id);
        return "redirect:/operations";
    }
}
