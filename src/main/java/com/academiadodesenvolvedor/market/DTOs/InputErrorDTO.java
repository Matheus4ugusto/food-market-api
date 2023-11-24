package com.academiadodesenvolvedor.market.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InputErrorDTO {
    private String field;
    private String message;
}
