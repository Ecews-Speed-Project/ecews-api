/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ihvncr.ihvncrapi.utils;

import com.ihvncr.ihvncrapi.mongo.model.Container;
import com.ihvncr.ihvncrapi.mongo.model.ObsType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.*;



/**
 *
 * @author MORRISON.I
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class HelperFunctions {
    public static LocalDate convertDate(Date date) {

        Instant instant = date.toInstant();
        ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());

        return zdt.toLocalDate();
    }

    public Optional<ObsType> getDaysOfArv(int obsId, int conceptId, Container container, Date cutOff) {

        return container.getMessageData().getObs()
                .stream()
                .filter(obsType -> obsType.getObsGroupId() == obsId &&
                        obsType.getConceptId() == conceptId &&
                        obsType.getVoided() == 0 &&
                        obsType.getObsDatetime().before(cutOff))
                .max(Comparator.comparing(ObsType::getObsDatetime));
    }

    public static Optional<ObsType> getObsValue(int encounterId, List<ObsType> obs, int valueCoded) {

        return obs
                .stream()
                .filter(obsType -> obsType.getEncounterId() == encounterId &&
                        obsType.getConceptId() == valueCoded &&
                        obsType.getVoided() == 0 )
                .max(Comparator.comparing(ObsType::getObsDatetime));
    }

    public static BigDecimal getValueNumeric(int encounterId, int conceptId, Container patients) {
        Optional<ObsType> obs = patients.getMessageData().getObs()
                .stream().filter(x->x.getEncounterId() == encounterId && x.getConceptId() == conceptId)
                .findFirst();
        if (obs.isPresent()) {
            return obs.get().getValueNumeric();
        }
        return null;
    }



}
