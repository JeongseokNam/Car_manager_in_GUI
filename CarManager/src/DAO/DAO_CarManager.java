package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import DTO.DTO_SeachInfo;
import DTO.DTO_car;
import DTO.DTO_item;
import DTO.DTO_record;
import DTO.DTO_user;

public class DAO_CarManager {
	private Connection conn = null;
	public static DAO_CarManager dao_carmanager = null;
	
	private DAO_CarManager() {
		init();
	}
	public static DAO_CarManager getInstance() {
		if (dao_carmanager==null) {
			dao_carmanager = new DAO_CarManager();
		}
		return dao_carmanager;
	}
	
	private void getConnection() {
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "1111");
			System.out.println("DB접속 성공");
		} catch (SQLException e) {
			System.out.println("DB접속 실패");
			e.printStackTrace();
		}
	}
	private void init() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("클래스 로드 성공");
		} catch (Exception e) {
			System.out.println("클래스 로드 실패");
			e.printStackTrace();
		}
	}

	public void carAdd(DTO_car dtoCar) {
		PreparedStatement ps=null;
		try {
			getConnection();
			String sql = "insert into car values (?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, dtoCar.getCarNum());
			ps.setString(2, dtoCar.getCarType());
			ps.setInt(3, dtoCar.getKm());
			ps.setString(4, dtoCar.getName());
			ps.executeUpdate();
			System.out.println("차량등록되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public ArrayList<DTO_car> setCarList() {
		Statement stat=null;
		ResultSet rs = null;
		ArrayList<DTO_car> carList = new ArrayList<>();
		try {
			getConnection();
			String sql = "select * from car";
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				DTO_car dto = new DTO_car();
				dto.setCarNum(rs.getInt("carnum"));
				dto.setCarType(rs.getString("cartype"));
				dto.setKm(rs.getInt("km"));
				dto.setName(rs.getString("name"));
				carList.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return carList;
	}
	public void carDel(int carnum) {
		PreparedStatement ps = null;
		try {
			getConnection();
			String sql = "delete from car where carnum=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, carnum);
			ps.executeUpdate();
			System.out.println(carnum+"삭제되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	public ArrayList<DTO_item> setItemList() {
		Statement stat=null;
		ResultSet rs = null;
		ArrayList<DTO_item> itemList = new ArrayList<>();
		try {
			getConnection();
			String sql = "select * from item";
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				DTO_item dto = new DTO_item();
				dto.setName(rs.getString("name"));
				dto.setPeriod(rs.getInt("period"));
				itemList.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return itemList;
	}
	public void itemAdd(DTO_item dto) {
		PreparedStatement ps=null;
		try {
			getConnection();
			String sql = "insert into item values (?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, dto.getName());
			ps.setInt(2, dto.getPeriod());
			ps.executeUpdate();
			System.out.println("정비품목등록되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public void itemDel(String itemName) {
		PreparedStatement ps = null;
		try {
			getConnection();
			String sql = "delete from item where name=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, itemName);
			ps.executeUpdate();
			System.out.println(itemName+"삭제되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public ArrayList<DTO_user> setUserList() {
		Statement stat=null;
		ResultSet rs = null;
		ArrayList<DTO_user> userList = new ArrayList<>();
		try {
			getConnection();
			String sql = "select * from caruser";
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				DTO_user dto = new DTO_user();
				dto.setName(rs.getString("name"));
				dto.setTeam(rs.getString("team"));
				dto.setRank(rs.getString("rank"));
				userList.add(dto);
			}
			return userList;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	public void userAdd(DTO_user dto) {
		PreparedStatement ps=null;
		try {
			getConnection();
			String sql = "insert into caruser values (?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, dto.getName());
			ps.setString(2, dto.getTeam());
			ps.setString(3, dto.getRank());
			ps.executeUpdate();
			System.out.println("차량관리자등록되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	public void userDel(String userName) {
		PreparedStatement ps = null;
		try {
			getConnection();
			String sql = "delete from caruser where name=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, userName);
			ps.executeUpdate();
			System.out.println(userName+"삭제되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	public void setCarKM(int carkm, int carnum) {
		PreparedStatement ps = null;
		try {
			getConnection();
			String sql = "update car set km=? where carnum=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, carkm);
			ps.setInt(2, carnum);
			ps.executeUpdate();
			System.out.println("수정되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	public void addRecord(DTO_record dto) {
		PreparedStatement ps = null;
		try {
			getConnection();
			String sql = "insert into record values (?,?,default,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, dto.getCarNum());
			ps.setString(2, dto.getName());
			ps.setInt(3, dto.getKm());
			ps.setInt(4, dto.getPrice());
			ps.executeUpdate();
			System.out.println("정비내역 등록되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public ArrayList<DTO_record> setRecordList() {
		Statement stat=null;
		ResultSet rs = null;
		ArrayList<DTO_record> recordList = new ArrayList<>();
		try {
			getConnection();
			String sql = "select * from record";
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				DTO_record dto = new DTO_record();
				dto.setCarNum(rs.getInt("carnum"));
				dto.setName(rs.getString("name"));
				dto.setDate(rs.getString("r_date"));
				dto.setKm(rs.getInt("km"));
				dto.setPrice(rs.getInt("price"));
				recordList.add(dto);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return recordList;
	}
	public void recordDel(int carnum, String name,int km) {
		PreparedStatement ps = null;
		try {
			getConnection();
			String sql = "delete from record where carnum=? and name=? and km=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, carnum);
			ps.setString(2, name);
			ps.setInt(3, km);
			ps.executeUpdate();
			System.out.println(carnum+"번 "+name+" 정비내역 삭제되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public ArrayList<DTO_record> searchRecord(DTO_record dto1) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<DTO_record> recordList = new ArrayList<>();
		try {
			getConnection();
			String sql = "select * from record where carnum=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, dto1.getCarNum());
			rs = ps.executeQuery();
			while (rs.next()) {
				DTO_record dto = new DTO_record();
				dto.setCarNum(rs.getInt("carnum"));
				dto.setName(rs.getString("name"));
				dto.setDate(rs.getString("r_date"));
				dto.setKm(rs.getInt("km"));
				dto.setPrice(rs.getInt("price"));
				recordList.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return recordList;
		
	}
	public DTO_SeachInfo setSeachList(Integer num, String name) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql=null;
		DTO_SeachInfo dto= new DTO_SeachInfo();
		int recordKm=0;
		int carKm=0;
		int itemKm=0;
		try {
			getConnection() ;
			sql="select * from(select km from record where carnum=?  and name=? order by r_date desc) where rownum=1";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, num);
			ps.setString(2, name);
			rs = ps.executeQuery();
			if (rs.next()) {
				recordKm=rs.getInt("km");
			}
			sql="select km from car where carnum=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, num);
			rs = ps.executeQuery();
			if (rs.next()) {
			carKm=rs.getInt("km");
			}
			sql="select period from item where name=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			rs = ps.executeQuery();
			if (rs.next()) {
				itemKm=rs.getInt("period");
			}
			if (itemKm<=(carKm-recordKm)) {
				dto.setCarNum(num);
				dto.setItemName(name);
				return dto;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
			
		
	}
	
	public ArrayList<String> getItemName() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<String> itemList = new ArrayList<>();
		try {
			getConnection();
			String Sql="select name from item";
			ps=conn.prepareStatement(Sql);
			rs=ps.executeQuery();
			while (rs.next()) {
				itemList.add(rs.getString("name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return itemList;
	}
	public ArrayList<Integer> getCarnum() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Integer> numList = new ArrayList<>();
		try {
			getConnection();
			String Sql="select carnum from car";
			ps=conn.prepareStatement(Sql);
			rs=ps.executeQuery();
			while (rs.next()) {
				numList.add(rs.getInt("carnum"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return numList;
	}

}
