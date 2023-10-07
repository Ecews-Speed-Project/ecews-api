package com.ihvncr.ihvncrapi.configuration;

import com.blazebit.persistence.integration.view.spring.EnableEntityViews;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableEntityViews("com.ihvncr.ihvncrapi.model.entities.views")
public class BlazeEntityViewConfig {
}
