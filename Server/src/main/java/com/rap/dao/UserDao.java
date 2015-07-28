package com.rap.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.rap.analysismodels.NewmemberInfo;
import com.rap.analysismodels.OSInfo;
import com.rap.idao.UserIDao;
import com.rap.models.DeviceInfo;
import com.rap.models.UserInfo;

@Repository
public class UserDao implements UserIDao {
	private static final Logger logger = LoggerFactory.getLogger(UserDao.class);

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource ds) {
		dataSource = ds;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		logger.info("Updated DataSource ---> " + ds);
		logger.info("Updated jdbcTemplate ---> " + jdbcTemplate);
	}

	public void create(String project_key, String name) {
		logger.info("UserDao<create> project_key: " + project_key + ", name: " + name);
		jdbcTemplate.update("insert into user (project_key, name) values (?, ?)", new Object[] { project_key, name });
	}

	public void appCountInc(String project_key, String name) {
		isExist(project_key, name);
		jdbcTemplate.update("update user set " + " count = count + 1" + " where project_key = ? AND name = ?",
				new Object[] { project_key, name });
	}

	public void setGCM(String project_key, String name, String gcm_id) {
		isExist(project_key, name);
		jdbcTemplate.update("update user set " + " gcm_id = ?" + " where project_key = ? AND name = ?",
				new Object[] { gcm_id, project_key, name });
	}

	public void setGradeTime(String project_key, String name, int grade_time) {
		isExist(project_key, name);
		jdbcTemplate.update("update user set " + " grade_time = ?" + " where project_key = ? AND name = ?",
				new Object[] { grade_time, project_key, name });
	}

	public void setGradeMoney(String project_key, String name, int grade_money) {
		isExist(project_key, name);
		jdbcTemplate.update("update user set " + " grade_money = ?" + " where project_key = ? AND name = ?",
				new Object[] { grade_money, project_key, name });
	}

	public void setPosition(String project_key, String name, double position_let, double position_lon) {
		isExist(project_key, name);
		jdbcTemplate.update(
				"update user set " + " position_let = ?," + " position_lon = ?" + " where project_key = ? AND name = ?",
				new Object[] { position_let, position_lon, project_key, name });
	}

	public void setOsVersion(String project_key, String name, String os_version) {
		isExist(project_key, name);
		jdbcTemplate.update("update user set " + " os_version = ?" + " where project_key = ? AND name = ?",
				new Object[] { os_version, project_key, name });
	}

	public void setDevice(String project_key, String name, String device_version) {
		isExist(project_key, name);
		jdbcTemplate.update("update user set " + " device_version = ?" + " where project_key = ? AND name = ?",
				new Object[] { device_version, project_key, name });
	}

	public void setAge(String project_key, String name, int age) {
		isExist(project_key, name);
		jdbcTemplate.update("update user set " + " age = ?" + " where project_key = ? AND name = ?",
				new Object[] { age, project_key, name });
	}

	public void setSex(String project_key, String name, int sex) {
		isExist(project_key, name);
		jdbcTemplate.update("update user set " + " sex = ?" + " where project_key = ? AND name = ?",
				new Object[] { sex, project_key, name });
	}

	public void setGCMId(String project_key, String name, String gcm_id) {
		isExist(project_key, name);
		isExist(project_key, name);
		jdbcTemplate.update("update user set " + " gcm_id = ?" + " where project_key = ? AND name = ?",
				new Object[] { gcm_id, project_key, name });
	}

	public void getVirtual_main(String project_key, String name, int money) {
		isExist(project_key, name);
		jdbcTemplate.update(
				"update user set " + " virtual_main = virtual_main + " + money + " where project_key = ? AND name = ?",
				new Object[] { project_key, name });
	}

	public void useVirtual_main(String project_key, String name, int money) {
		isExist(project_key, name);
		jdbcTemplate.update(
				"update user set " + " virtual_main = virtual_main - " + money + " where project_key = ? AND name = ?",
				new Object[] { project_key, name });
	}

	public void getVirtual_sub(String project_key, String name, int money) {
		isExist(project_key, name);
		jdbcTemplate.update(
				"update user set " + " virtual_sub = virtual_sub + " + money + " where project_key = ? AND name = ?",
				new Object[] { project_key, name });
	}

	public void useVirtual_sub(String project_key, String name, int money) {
		isExist(project_key, name);
		jdbcTemplate.update(
				"update user set " + " virtual_sub = virtual_sub - " + money + " where project_key = ? AND name = ?",
				new Object[] { project_key, name });
	}

	public List<UserInfo> select(String project_key) {
		return jdbcTemplate.query("select * from user where project_key = ?", new Object[] { project_key },
				new RowMapper<UserInfo>() {
					public UserInfo mapRow(ResultSet resultSet, int rowNum) throws SQLException {
						return new UserInfo(resultSet.getInt("pk"), resultSet.getString("project_key"),
								resultSet.getString("gcm_id"), resultSet.getInt("grade_time"),
								resultSet.getInt("grade_money"), resultSet.getDouble("position_let"),
								resultSet.getDouble("position_lon"), resultSet.getInt("sex"),
								resultSet.getString("os_version"), resultSet.getString("device_version"),
								resultSet.getInt("age"), resultSet.getInt("count"), resultSet.getInt("virtual_main"),
								resultSet.getInt("virtual_sub"), resultSet.getTimestamp("reg_date"));
					}
				});
	}

	public UserInfo selectUser(String project_key, String name) {
		List<UserInfo> result = jdbcTemplate.query("select * from user where project_key=? AND name=?",
				new Object[] { project_key, name }, new RowMapper<UserInfo>() {
					public UserInfo mapRow(ResultSet resultSet, int rowNum) throws SQLException {
						return new UserInfo(resultSet.getInt("pk"), resultSet.getString("project_key"),
								resultSet.getString("gcm_id"), resultSet.getInt("grade_time"),
								resultSet.getInt("grade_money"), resultSet.getDouble("position_let"),
								resultSet.getDouble("position_lon"), resultSet.getInt("sex"),
								resultSet.getString("os_version"), resultSet.getString("device_version"),
								resultSet.getInt("age"), resultSet.getInt("count"), resultSet.getInt("virtual_main"),
								resultSet.getInt("virtual_sub"), resultSet.getTimestamp("reg_date"));
					}
				});

		if (result.size() == 1) {
			return result.get(0);
		} else if (result.size() > 1) {
			logger.info("UserDao<selectUser>: 결과값 1개 이상");
			return null;
		} else {
			logger.info("UserDao<selectUser>: 유저 없음");
			return null;
		}
	}

	public void deleteAll() {
		jdbcTemplate.update("delete from user");
	}

	public void delete(String project_key) {
		jdbcTemplate.update("delete from user where project_key = ?", new Object[] { project_key });
	}

	private void isExist(String project_key, String name) {
		logger.info("UserDao<isExist>");

		if (selectUser(project_key, name) == null) {
			logger.info("신규 유저");
			create(project_key, name);
		}
	}

	public int countSex(String project_key, int sex) {
		logger.info("count sex");
		List<UserInfo> result = jdbcTemplate.query("select * from user where project_key=? AND sex=?",
				new Object[] { project_key, sex }, new RowMapper<UserInfo>() {
					public UserInfo mapRow(ResultSet resultSet, int rowNum) throws SQLException {
						return new UserInfo(resultSet.getInt("pk"), resultSet.getString("project_key"),
								resultSet.getString("gcm_id"), resultSet.getInt("grade_time"),
								resultSet.getInt("grade_money"), resultSet.getDouble("position_let"),
								resultSet.getDouble("position_lon"), resultSet.getInt("sex"),
								resultSet.getString("os_version"), resultSet.getString("device_version"),
								resultSet.getInt("age"), resultSet.getInt("count"), resultSet.getInt("virtual_main"),
								resultSet.getInt("virtual_sub"), resultSet.getTimestamp("reg_date"));
					}
				});
		return result.size();
	}

	public int countAge(String project_key, String age) {
		logger.info("count sex");
		int floor = 0, ceiling = 0;

		if (age.equals("baby")) {
			floor = 0;
			ceiling = 10;
		} else if (age.equals("ten")) {
			floor = 10;
			ceiling = 20;
		} else if (age.equals("twenty")) {
			floor = 20;
			ceiling = 30;
		} else if (age.equals("thirty")) {
			floor = 30;
			ceiling = 40;
		} else if (age.equals("forty")) {
			floor = 40;
			ceiling = 50;
		} else if (age.equals("old")) {
			floor = 50;
			ceiling = 2147483647;
			List<UserInfo> result = jdbcTemplate.query("select * from user where project_key=? AND age >= ? ",
					new Object[] { project_key, floor }, new RowMapper<UserInfo>() {
						public UserInfo mapRow(ResultSet resultSet, int rowNum) throws SQLException {
							return new UserInfo(resultSet.getInt("pk"), resultSet.getString("project_key"),
									resultSet.getString("gcm_id"), resultSet.getInt("grade_time"),
									resultSet.getInt("grade_money"), resultSet.getDouble("position_let"),
									resultSet.getDouble("position_lon"), resultSet.getInt("sex"),
									resultSet.getString("os_version"), resultSet.getString("device_version"),
									resultSet.getInt("age"), resultSet.getInt("count"),
									resultSet.getInt("virtual_main"), resultSet.getInt("virtual_sub"),
									resultSet.getTimestamp("reg_date"));
						}
					});
			return result.size();
		}

		List<UserInfo> result = jdbcTemplate.query("select * from user where project_key=? AND age >= ? AND age < ?",
				new Object[] { project_key, floor, ceiling }, new RowMapper<UserInfo>() {
					public UserInfo mapRow(ResultSet resultSet, int rowNum) throws SQLException {
						return new UserInfo(resultSet.getInt("pk"), resultSet.getString("project_key"),
								resultSet.getString("gcm_id"), resultSet.getInt("grade_time"),
								resultSet.getInt("grade_money"), resultSet.getDouble("position_let"),
								resultSet.getDouble("position_lon"), resultSet.getInt("sex"),
								resultSet.getString("os_version"), resultSet.getString("device_version"),
								resultSet.getInt("age"), resultSet.getInt("count"), resultSet.getInt("virtual_main"),
								resultSet.getInt("virtual_sub"), resultSet.getTimestamp("reg_date"));
					}
				});
		return result.size();

	}

	public List<DeviceInfo> countDevice(String project_key) {
		logger.info("count device");
		// SELECT DISTINCT email FROM table;

		List<DeviceInfo> Device_version = jdbcTemplate.query(
				"select count(*),device_version from user where project_key=? group by device_version",
				new Object[] { project_key }, new RowMapper<DeviceInfo>() {
					public DeviceInfo mapRow(ResultSet resultSet, int rowNum) throws SQLException {
						return new DeviceInfo(resultSet.getInt("count(*)"), resultSet.getString("device_version"));
					}
				});

		return Device_version;
	}

	public List<OSInfo> countOS(String project_key) {
		logger.info("count os");
		// SELECT DISTINCT email FROM table;

		List<OSInfo> OS_version = jdbcTemplate.query(
				"select count(*),os_version from user where project_key=? group by os_version",
				new Object[] { project_key }, new RowMapper<OSInfo>() {
					public OSInfo mapRow(ResultSet resultSet, int rowNum) throws SQLException {
						return new OSInfo(resultSet.getInt("count(*)"), resultSet.getString("os_version"));
					}
				});

		return OS_version;
	}

	public List<NewmemberInfo> count_new_member(String project_key, String type, Timestamp start) {
		
		logger.info("count new_member");
		// SELECT DISTINCT email FROM table;
		
		List<NewmemberInfo> OPcount = null;
		List<NewmemberInfo> result = new ArrayList<NewmemberInfo>();
		if (type.equals("day")) {
			OPcount = jdbcTemplate.query(
					"select count(*),DATE(reg_date) AS ForDate from user where project_key=? AND reg_date<TIMESTAMP(DATE_ADD(?, INTERVAL 7 day))AND DATE(reg_date)>=DATE(?) GROUP BY DATE(reg_date) ORDER BY ForDate",
					new Object[] { project_key, start,start}, new RowMapper<NewmemberInfo>() {
						public NewmemberInfo mapRow(ResultSet resultSet, int rowNum) throws SQLException {
							return new NewmemberInfo(resultSet.getTimestamp("ForDate"), resultSet.getInt("count(*)"));
						}
					});
		
			int index=0;
			for (int i = 0; i < 7; i++) {			
				if(OPcount.size()>0&&OPcount.get(index).getStart().equals(start)){
					result.add(i,OPcount.get(index));
					if(index<OPcount.size()-1)
					index++;	
				}
				else{
					Timestamp c=new Timestamp(start.getTime());
					NewmemberInfo a = new NewmemberInfo(c, 0);
					result.add(i, a);
				}
				start.setDate(start.getDate()+1);
			}
		}
		
		if (type.equals("month")) {
			OPcount = jdbcTemplate.query(
					"select count(*),DATE(reg_date) AS ForDate from user where project_key=? AND reg_date<TIMESTAMP(DATE_ADD(?, INTERVAL 7 month))AND DATE(reg_date)>=DATE(?) GROUP BY MONTH(reg_date) ORDER BY ForDate",
					new Object[] { project_key, start,start}, new RowMapper<NewmemberInfo>() {
						public NewmemberInfo mapRow(ResultSet resultSet, int rowNum) throws SQLException {
							return new NewmemberInfo(resultSet.getTimestamp("ForDate"), resultSet.getInt("count(*)"));
						}
					});
			for (int i = 0; i < OPcount.size(); i++) {				
				logger.info("q"+"month : "+OPcount.get(i).getCount()+ " count : "+OPcount.get(i).getCount());
			}
			
			int index=0;
			
			for (int i = 0; i < 7; i++) {			
				if(OPcount.size()>0&&OPcount.get(index).getStart().getMonth()==start.getMonth()){
					result.add(i,OPcount.get(index));
					if(index<OPcount.size()-1)
						index++;					
				}
				else{
					Timestamp c=new Timestamp(start.getTime());
					NewmemberInfo a = new NewmemberInfo(c, 0);
					result.add(i, a);
				}
				start.setMonth(start.getMonth()+1);
				//logger.info(""+result.get(i).getCount());
			}
			
			
		}
		
		if (type.equals("year")) {
			OPcount = jdbcTemplate.query(
					"select count(*),DATE(reg_date) AS ForDate from user where project_key=? AND reg_date<TIMESTAMP(DATE_ADD(?, INTERVAL 7 year))AND DATE(reg_date)>=DATE(?) GROUP BY YEAR(reg_date) ORDER BY ForDate",
					new Object[] { project_key, start,start}, new RowMapper<NewmemberInfo>() {
						public NewmemberInfo mapRow(ResultSet resultSet, int rowNum) throws SQLException {
							return new NewmemberInfo(resultSet.getTimestamp("ForDate"), resultSet.getInt("count(*)"));
						}
					});
			for (int i = 0; i < OPcount.size(); i++) {				
				logger.info("q"+OPcount.get(i).getStart().getYear());
			}
			
			int index=0;
			
			for (int i = 0; i < 7; i++) {			
				if(OPcount.size()>0&&OPcount.get(index).getStart().getYear()==start.getYear()){
					result.add(i,OPcount.get(index));
					if(index<OPcount.size()-1)
						index++;					
				}
				else{
					Timestamp c=new Timestamp(start.getTime());
					NewmemberInfo a = new NewmemberInfo(c, 0);
					result.add(i, a);
				}
				start.setYear(start.getYear()+1);
				//logger.info("year"+result.get(i).getCount());
			}/*
			for (int i = 0; i < 7; i++) {		
				logger.info("year : "+result.get(i).getStart());
				}*/
		}


		
		return result;
	}
}
