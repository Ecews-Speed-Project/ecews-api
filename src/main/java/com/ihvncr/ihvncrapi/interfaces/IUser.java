package com.ihvncr.ihvncrapi.interfaces;

import com.ihvncr.ihvncrapi.model.User;

import java.util.List;

public interface IUser {
    List<User> findPaginated(int pageNo, int pageSize);
}
