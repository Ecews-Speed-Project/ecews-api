package com.ihvncr.ihvncrapi.model.analytics;

import com.ihvncr.ihvncrapi.model.enums.Indicators;
import com.ihvncr.ihvncrapi.model.enums.SearchType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Search{
    public Indicators indicator;
    public Set<String> iPs = new HashSet<>();
    public Set<String> lgas = new HashSet<>();
    public Set<String> states = new HashSet<>();
    public Set<String> facilities = new HashSet<>();
    public Set<String> sex = new HashSet<>();


    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    public LocalDate startDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    public LocalDate endDate;

    public String quarter;
    public Object weight;
    public Object reportType;
    public Object clientStatus;
    public Set<String> ageRange = new HashSet<>();
    public Object viralStatus;
    public String searchType;
    public SearchType displayLevel;
}
