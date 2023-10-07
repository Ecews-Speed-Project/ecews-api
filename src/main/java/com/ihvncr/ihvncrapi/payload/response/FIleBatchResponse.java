package com.ihvncr.ihvncrapi.payload.response;

import com.ihvncr.ihvncrapi.model.FileBatch;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FIleBatchResponse  implements Serializable {
    long uploaded;
    long processed;
    long processing;
    long queued;
    long failed;
    long totalRows;
    Iterable< ?> fileBatch;
}
