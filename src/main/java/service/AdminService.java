package service;

import entity.Admin;
import entity.Artical;
import entity.ResultData;

import java.util.List;

public interface AdminService {

    ResultData userLogin(Admin admin);
}
