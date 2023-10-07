package com.ihvncr.ihvncrapi.service;

import com.blazebit.persistence.CriteriaBuilder;
import com.blazebit.persistence.CriteriaBuilderFactory;
import com.blazebit.persistence.view.EntityViewManager;
import com.ihvncr.ihvncrapi.model.ArtLinelist;
import com.ihvncr.ihvncrapi.model.analytics.*;
import com.ihvncr.ihvncrapi.model.enums.Indicators;
import com.ihvncr.ihvncrapi.model.performanceDashboard.DrugPickup;
import com.ihvncr.ihvncrapi.repository.ArtLineListRepository;
import com.ihvncr.ihvncrapi.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PerformanceDashboardService {

    public final EntityManager em;
    public final CriteriaBuilderFactory cbf;
    public final EntityViewManager evm;
    private final HighChatsService highChatsUtil;

    @Autowired
    ArtLineListRepository artLineListRepository;

    public PerformanceDashboardService(EntityManager em, CriteriaBuilderFactory cbf, EntityViewManager evm, HighChatsService highChatsUtil) {
        this.em = em;
        this.cbf = cbf;
        this.evm = evm;
        this.highChatsUtil = highChatsUtil;
    }

    public List<Map<String, Object>> getDashboardChats() {
        DashboardChats dashboardStats = new DashboardChats();
        List<Map<String, Object>> result = artLineListRepository.generatedrugpickupperformance();
        return result;
    }
}
