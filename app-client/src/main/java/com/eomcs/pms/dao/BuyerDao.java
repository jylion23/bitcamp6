package com.eomcs.pms.dao;

import java.util.List;
import com.eomcs.pms.domain.Buyer;

public interface BuyerDao {
  void insert(Buyer buyer) throws Exception;
  Buyer login(String id, String password) throws Exception;
  List<Buyer> findAll() throws Exception;
  Buyer findById(String id) throws Exception;
  void update(Buyer buyer) throws Exception;
  void delete(String id) throws Exception;
}
