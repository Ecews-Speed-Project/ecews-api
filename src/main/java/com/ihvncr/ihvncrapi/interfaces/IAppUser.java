package com.ihvncr.ihvncrapi.interfaces;

import com.ihvncr.ihvncrapi.model.AppUser;

import java.util.List;

public interface IAppUser {
    List<AppUser> findPaginated(int pageNo, int pageSize);
}
