package com.rap.idao;

import java.util.List;

import javax.sql.DataSource;

import com.rap.models.CategorySInfo;

public interface CategorySIDao {

   void setDataSource(DataSource ds);
   void create(String Key, int categoryM_pk, String categoryS);
   
   List<CategorySInfo> select(String key);
   List<CategorySInfo> select(String key, int categoryL_pk);
   List<CategorySInfo> select(String key, String categoryL, String categoryM);
   
   void delete(String key, int categoryM_pk, String categoryS);
   void delete(String key);
}
