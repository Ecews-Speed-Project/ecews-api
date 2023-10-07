package com.ihvncr.ihvncrapi.interfaces;

import com.ihvncr.ihvncrapi.model.FileBatch;

import java.util.List;

public interface IFileBatch  {
    List<FileBatch> findPaginated(int pageNo, int pageSize);
}
