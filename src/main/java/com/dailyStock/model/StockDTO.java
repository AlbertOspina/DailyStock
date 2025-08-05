package com.dailyStock.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class StockDTO {
    private Double adjClose;
    private Double adjHigh;
    private Double adjLow;
    private Double adjOpen;
    private Long adjVolume;
    private Double close;

    // Para el campo "date", Jackson (la librería que usa Spring) puede convertir
    // automáticamente el formato "2025-08-05T00:00:00+00:00" a un objeto OffsetDateTime.
    // Es mucho mejor que usar un String.
    private OffsetDateTime date;

    private Double divCash;
    private Double high;
    private Double low;
    private Double open;
    private Double splitFactor;
    private Long volume;
}
