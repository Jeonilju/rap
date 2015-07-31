package com.rap.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.rap.analysismodels.MapInfo;
import com.rap.analysismodels.NewmemberInfo;
import com.rap.analysismodels.OSInfo;
import com.rap.idao.UserIDao;
import com.rap.models.DeviceInfo;
import com.rap.models.UserInfo;

import net.sf.json.JSONObject;

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

	public void setPosition(String project_key, String name, double position_let, double position_lon, String location) {
		isExist(project_key, name);
		jdbcTemplate.update(
				"update user set " + " position_let = ?," + " position_lon = ?, location = ?" + " where project_key = ? AND name = ?",
				new Object[] { position_let, position_lon, location, project_key, name });
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

	public List<NewmemberInfo> count_new_member(String project_key, Timestamp end_date, int during) {
		
		logger.info("count new_member");
		
		java.util.Date date= new java.util.Date();
		Timestamp currentTime = new Timestamp(date.getTime());
		
		if(during == 0){
			// 최근 1주일
			Calendar c = Calendar.getInstance(); 
			c.setTime(date); 
			c.add(Calendar.DATE, -7);
			date = c.getTime();
			
			currentTime = new Timestamp(date.getTime());
		}
		else if (during == 1) {
			// 최근 2주일
			Calendar c = Calendar.getInstance(); 
			c.setTime(date); 
			c.add(Calendar.DATE, -14);
			date = c.getTime();
			
			currentTime = new Timestamp(date.getTime());
		} else if (during == 2) {
			// 최근 3주일
			Calendar c = Calendar.getInstance(); 
			c.setTime(date); 
			c.add(Calendar.DATE, -21);
			date = c.getTime();
			
			currentTime = new Timestamp(date.getTime());
		} else if (during == 3) {
			// 최근 1개월
			Calendar c = Calendar.getInstance(); 
			c.setTime(date); 
			c.add(Calendar.MONTH, -1);
			date = c.getTime();
			
			currentTime = new Timestamp(date.getTime());
		} else if (during == 4) {
			// 최근 3개월
			Calendar c = Calendar.getInstance(); 
			c.setTime(date); 
			c.add(Calendar.MONTH, -3);
			date = c.getTime();
			
			currentTime = new Timestamp(date.getTime());
			
			logger.info("시작 날짜: " + currentTime.toString());
			logger.info("끝 날짜: " + end_date.toString());
			
		} else if (during == 5) {
			// 최근 6개월
			Calendar c = Calendar.getInstance(); 
			c.setTime(date); 
			c.add(Calendar.MONTH, -6);
			date = c.getTime();
			
			currentTime = new Timestamp(date.getTime());
		}
		
		List<NewmemberInfo> result = new ArrayList<NewmemberInfo>();
		
		while(currentTime.compareTo(end_date) <= 0) {
			
			if(during == 0 || during == 1 || during == 2 || during == 3 ){
				
				List<NewmemberInfo> info = jdbcTemplate.query(
						"select * from user where project_key=? AND DATE(reg_date) = DATE(?)",
						new Object[] { project_key, currentTime}, new RowMapper<NewmemberInfo>() {
							public NewmemberInfo mapRow(ResultSet resultSet, int rowNum) throws SQLException {
								return new NewmemberInfo(
										resultSet.getTimestamp("reg_date")
										, 1);
							}
						});
				
				logger.info("currentTime: " + currentTime.toString() + ", size: " + info.size());
				
				//if(info.size() != 0)
					result.add(new NewmemberInfo(currentTime, info.size()));
				
				currentTime.setDate(currentTime.getDate()+1);
			}
			else{
				Timestamp temp = Timestamp.valueOf(currentTime.toString());
				temp.setMonth(temp.getMonth()+1);
				
				currentTime.setDate(1);
				temp.setDate(1);
				
				logger.info("temp: " + temp.toString());
				logger.info("currentTime: " + currentTime.toString());
				
				List<NewmemberInfo> info = jdbcTemplate.query(
						"select * from user where project_key=? AND DATE(reg_date) >= DATE(?) AND DATE(reg_date) <= DATE(?)",
						new Object[] { project_key, currentTime, temp}, new RowMapper<NewmemberInfo>() {
							public NewmemberInfo mapRow(ResultSet resultSet, int rowNum) throws SQLException {
								return new NewmemberInfo(
										resultSet.getTimestamp("reg_date")
										, 1);
							}
						});
				logger.info("temp: " + temp.toString());
				logger.info("currentTime: " + currentTime.toString() + ", size: " + info.size());
				
				result.add(new NewmemberInfo(currentTime, info.size()));
				
				currentTime.setMonth(currentTime.getMonth()+1);
			}
			
		}
		
		return result;
	}
	
	public List<UserInfo> selectPromotionUserlist(String project_key, int grade_time, int grade_money) {
		return jdbcTemplate.query("select * from user where project_key = ? and grade_time = ? and grade_money = ?", new Object[] { project_key, grade_time, grade_money },
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
	
	//TODO 민수
	public List<JSONObject> get_map(String project_key) {
		logger.info("get map");

		List<JSONObject> result=new ArrayList<JSONObject>();
		List<MapInfo> receive = jdbcTemplate.query(
				"select location,count(*) from user where project_key=? group by location",
				new Object[] { project_key }, new RowMapper<MapInfo>() {
					public MapInfo mapRow(ResultSet resultSet, int rowNum) throws SQLException {
						return new MapInfo(resultSet.getString("location"),resultSet.getInt("count(*)"));
					}
				});
		
		for(int i=0;i<receive.size();i++){
			JSONObject newobj=new JSONObject();
			newobj.put("hc-key", receive.get(i).getArea());
			newobj.put("value", receive.get(i).getCount());
			result.add(newobj);
		}
		

		return result;
	}
	
}
