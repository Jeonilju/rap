package com.rap.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.rap.analysismodels.EdgeInfo;
import com.rap.idao.ActivityIDao;
import com.rap.models.ActivityInfo;
import com.rap.models.BestActivityInfo;

import net.sf.json.JSONObject;

@Repository
public class ActivityDao implements ActivityIDao {

	private static final Logger logger = LoggerFactory.getLogger(ActivityDao.class);

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource ds) {
		dataSource = ds;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		logger.info("Updated DataSource ---> " + ds);
		logger.info("Updated jdbcTemplate ---> " + jdbcTemplate);
	}

	public void create(String project_key, String name, String activity_name) {
		jdbcTemplate.update("insert into activity_log (project_key, name, activity_name) values (?, ?, ?)",
				new Object[] { project_key, name, activity_name });
	}

	public void create(String project_key, String name, String activity_name, String activityb_name) {
		jdbcTemplate.update(
				"insert into activity_log (project_key, name, activity_name, activityb_name) values (?, ?, ?, ?)",
				new Object[] { project_key, name, activity_name, activityb_name });
	}

	public List<ActivityInfo> select(String project_key) {
		return jdbcTemplate.query("select * from activity_log where project_key = ?", new Object[] { project_key },
				new RowMapper<ActivityInfo>() {
					public ActivityInfo mapRow(ResultSet resultSet, int rowNum) throws SQLException {
						return new ActivityInfo(resultSet.getInt("pk"), resultSet.getInt("project_key"),
								resultSet.getString("name"), resultSet.getString("activity_name"),
								resultSet.getString("activityb_name"), resultSet.getTimestamp("reg_date"));
					}
				});
	}

	public void deleteAll() {
		jdbcTemplate.update("delete from activity_log");
	}

	public void delete(String project_key) {
		jdbcTemplate.update("delete from activity_log where project_key = ?", new Object[] { project_key });
	}

	public List<String> selectActivityList(String project_key) {
		List<String> result = jdbcTemplate.query(
				"select activity_name from rap.activity_log where project_key = ? group by activity_name UNION ALL select activityb_name from rap.activity_log where project_key = ? group by activityb_name ORDER BY activity_name ASC;",
				new Object[] { project_key, project_key }, new RowMapper<String>() {
					public String mapRow(ResultSet resultSet, int rowNum) throws SQLException {
						return new String(resultSet.getString("activity_name"));
					}
				});
		return result;

	}

	public List<BestActivityInfo> countBest_activity(String project_key) {

		logger.info("count Best_activity /" + "project_key : " + project_key);
		// SELECT DISTINCT email FROM table;

		List<BestActivityInfo> result = jdbcTemplate.query(
				"select  activity_name,count(*) from activity_log where project_key = ? group by activity_name order by count(*) desc",
				new Object[] { project_key }, new RowMapper<BestActivityInfo>() {
					public BestActivityInfo mapRow(ResultSet resultSet, int rowNum) throws SQLException {
						return new BestActivityInfo(resultSet.getString("activity_name"), resultSet.getInt("count(*)"));
					}
				});
		return result;

	}

	public List<JSONObject> count_activity_path(String project_key) {
		
		logger.info("count activity path");
		
		List<JSONObject> result = new ArrayList<JSONObject>();
		List<String> receive = jdbcTemplate.query(
				"select activity_name from activity_log where project_key=? group by activity_name",
					new Object[] { project_key}, new RowMapper<String>() {
						public String mapRow(ResultSet resultSet, int rowNum) throws SQLException {
							return new String(resultSet.getString("activity_name"));
						}
					});
		for(int i=0;i<receive.size();i++){
			JSONObject newobj= new JSONObject();
		
			List<EdgeInfo> receive2=jdbcTemplate.query(
					 "select activity_name,activityb_name,count(*) from activity_log where project_key=? AND activity_name=?"
					 + " group by activityb_name",
						new Object[] { project_key,receive.get(i)}, new RowMapper<EdgeInfo>() {
							public EdgeInfo mapRow(ResultSet resultSet, int rowNum) throws SQLException {
								return new EdgeInfo(resultSet.getString("activity_name"),
										resultSet.getString("activityb_name"),
										resultSet.getInt("count(*)"));
							}
						});
			List<JSONObject> small = new ArrayList<JSONObject>();
			
			for(int k=0;k<receive2.size();k++){
				JSONObject newobj2= new JSONObject();
				newobj2.put("nodeTo", receive2.get(k).getTo());
				newobj2.put("nodeFrom", receive2.get(k).getFrom());
				JSONObject newobj3= new JSONObject();
				newobj3.put("count", receive2.get(k).getCount().toString());
				newobj2.put("data",newobj3);
				small.add(newobj2);
			}
			newobj.put("adjacencies", small);
			JSONObject newobj4= new JSONObject();	
			newobj4.put("$color", "#83548B");
			newobj4.put("$type", "circle");
			newobj.put("data",newobj4);	
			newobj.put("id", receive.get(i));
			newobj.put("name", receive.get(i));
			result.add(newobj);
		}
		//logger.info(result.toString());
		return result;
	}
}
