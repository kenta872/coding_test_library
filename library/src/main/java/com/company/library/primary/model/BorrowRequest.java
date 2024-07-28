package com.company.library.primary.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BorrowRequest {
    private long employeeId;
    @NotEmpty
    private List<Long> bookIdList;
}
