package com.ihvncr.ihvncrapi.model.performanceDashboard;

import com.ihvncr.ihvncrapi.model.BaseClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class DrugPickup  extends BaseClass implements Serializable {
    String state;
    String facilityName;
    long expectedPickup;
    long drugPickup;
}
