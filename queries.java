package project111;
import java.sql.*;
import java.io.*;
import java.util.Scanner;

import javax.naming.spi.DirStateFactory.Result;

public class queries {
	public static Scanner scan = new Scanner(System.in);

	//1: search all players
	public static void userOpt1(){
		try{
			Connection connect;
			Statement stat;
			connect = DriverManager.getConnection("jdbc:sqlite:/Users/jericosuguitan/eclipse-workspace/project111/ProjDB.db");
			stat = connect.createStatement();

			ResultSet rs = stat.executeQuery(
					"Select * "+
							"From players " +
					"order by plr_teamID, plr_playerID;");
			while(rs.next()){
				String plrTeam = rs.getString("plr_TeamID");
				String plrID = rs.getString("plr_playerID");
				String plrPos = rs.getString("plr_positionID");
				String plrFName = rs.getString("plr_firsName");
				String plrLName = rs.getString("plr_lastName");

				System.out.println(plrTeam + " \t " + plrID + " \t " + plrPos + " \t " + plrFName + plrLName);
			}
			rs.close();
			stat.close();
			connect.close();
			System.out.println();
		}
		catch(Exception e){
			System.err.println(e.getMessage());
			System.out.println("Program Ending...");
			System.exit(0);
		}
	}

	//2a: search players by team
	public static void userOpt2a(int inputType) {
		try{
			Connection connect;
			Statement stat;
			connect = DriverManager.getConnection("jdbc:sqlite:/Users/jericosuguitan/eclipse-workspace/project111/ProjDB.db");
			stat = connect.createStatement();

			ResultSet rs = stat.executeQuery(
					"Select * "+
							"From players " +
							"Where plr_TeamID = '"+ inputType +"'" +
					"order by plr_lastName;");
			while(rs.next()){
				String plrTeam = rs.getString("plr_TeamID");
				String plrID = rs.getString("plr_playerID");
				String plrPos = rs.getString("plr_positionID");
				String plrFName = rs.getString("plr_firsName");
				String plrLName = rs.getString("plr_lastName");

				System.out.println(plrID + "\t" + plrPos + "\t" + plrTeam + "\t" + plrFName + " " + plrLName);
			}
			rs.close();
			stat.close();
			connect.close();
			System.out.println();
		}
		catch(Exception e){
			System.err.println(e.getMessage());
			System.out.println("Program Ending...");
			System.exit(0);
		}
	}

	//2b: search players by position
	public static void userOpt2b(int inputType) {
		try{
			Connection connect;
			Statement stat;
			connect = DriverManager.getConnection("jdbc:sqlite:/Users/jericosuguitan/eclipse-workspace/project111/ProjDB.db");
			stat = connect.createStatement();

			ResultSet rs = stat.executeQuery(
					"Select * "+
							"From players " +
							"Where plr_positionID = '"+ inputType +"'" +
					"order by plr_TeamID, plr_lastName;");
			while(rs.next()){
				String plrTeam = rs.getString("plr_TeamID");
				String plrID = rs.getString("plr_playerID");
				String plrPos = rs.getString("plr_positionID");
				String plrFName = rs.getString("plr_firsName");
				String plrLName = rs.getString("plr_lastName");

				System.out.println(plrID + "\t" + plrPos + "\t" + plrTeam + "\t" + plrFName + " " + plrLName);
			}
			rs.close();
			stat.close();
			connect.close();
			System.out.println();
		}
		catch(Exception e){
			System.err.println(e.getMessage());
			System.out.println("Program Ending...");
			System.exit(0);
		}
	}

	// "3: Search about teams" can search by team name, then by players
	public static void userOpt4(int teamSearch) {
		try{
			Connection connect;
			Statement stat;
			connect = DriverManager.getConnection("jdbc:sqlite:/Users/jericosuguitan/eclipse-workspace/project111/ProjDB.db");
			stat = connect.createStatement();

			ResultSet rs = stat.executeQuery(
					"Select *" +
							"From team inner join players on t_teamID = plr_TeamID" +
							"Where t_teamID = inputType" +
					"order by t_teamName;");

			while(rs.next()){
				String teamID = rs.getString("t_teamID");
				String teamName = rs.getString("t_teamName");
				String venueName = rs.getString("t_venueName");
				String plrID = rs.getString("plr_playerID");
				String plrPos = rs.getString("plr_positionID");
				String plrFName = rs.getString("plr_firsName");
				String plrLName = rs.getString("plr_lastName");

				System.out.println(teamID + " \t " + teamName + " \t " + venueName + " \t " + plrID + " \t " + plrPos + " \t " + plrFName + " \t " + plrLName);
				//System.out.println(plrTeam + " \t " + plrID + " \t " + plrPos + " \t " + plrFName + " \t " + plrLName);
			}

			rs.close();
			stat.close();
			connect.close();
			System.out.println();
		}
		catch(Exception e){
			System.err.println(e.getMessage());
			System.out.println("Program Ending...");
			System.exit(0);
		}
	}

	// 4
	//public static void userOpt4(int matchStat, int scoreType){
	public static void userOpt3(int matchStat){
		try{
			Connection connect;
			Statement stat;
			connect = DriverManager.getConnection("jdbc:sqlite:/Users/jericosuguitan/eclipse-workspace/project111/ProjDB.db");
			stat = connect.createStatement();

			if (matchStat == 118){
				System.out.println("Which game would you like to view: 1-4");
				int gameNum = scan.nextInt();

				if (gameNum == 1) {
					ResultSet rs = stat.executeQuery(
							"Select *"+
									"From statistics INNER JOIN matches ON (s_matchID = m_matchID)" +
									"INNER JOIN team t1 on (m_team1ID = t1.t_teamID)" +
									"INNER JOIN team t2 on (m_team2ID = t2.t_teamID)" +
							"WHERE s_matchID = '1181';");
					while(rs.next()){
						String plrID = rs.getString("s_playerID");
						String posID = rs.getString("s_positionID");
						String team = rs.getString("t_teamName");
						String pts = rs.getString("s_PTS");
						String fg = rs.getString("s_FG");
						String fga = rs.getString("s_FGA");
						String fgp = rs.getString("s_FGP");
						String threep = rs.getString("s_3P");
						String threepa = rs.getString("s_3PA");
						String threepp = rs.getString("s_3PP");
						String ft = rs.getString("s_FT");
						String fta = rs.getString("s_FTA");
						String ftp = rs.getString("s_FTP");
						String orb = rs.getString("s_ORB");
						String drb = rs.getString("s_DRB");
						String trb = rs.getString("s_TRB");
						String ast = rs.getString("s_AST");
						String stl = rs.getString("s_STL");
						String blk = rs.getString("s_BLK");
						String tov = rs.getString("s_TOV");
						String pf = rs.getString("s_PF");
						String plusmin = rs.getString("s_plusmin");
						System.out.println(plrID + "\t" +  posID + "\t" +  pts + "\t" +
								fg + "\t" +  fga + "\t" +  fgp + "\t" +  threep + "\t" +  threepa + "\t" +
								threepp + "\t" +  ft + "\t" +  fta + "\t" +  ftp +  "\t" +
								orb + "\t" +  drb + "\t" +  trb + "\t" +  ast + "\t" +  stl + "\t" +
								blk + "\t" +  tov + "\t" +  pf + "\t" + plusmin);
						System.out.println();
					}
				}
				else if (gameNum == 2) {
					ResultSet rs = stat.executeQuery(
							"Select *"+
									"From statistics INNER JOIN matches ON (s_matchID = m_matchID)" +
									"INNER JOIN team t1 on (m_team1ID = t1.t_teamID)" +
									"INNER JOIN team t2 on (m_team2ID = t2.t_teamID)" +
							"WHERE s_matchID = '1182';");
					while(rs.next()){
						String plrID = rs.getString("s_playerID");
						String posID = rs.getString("s_positionID");
						String team = rs.getString("t_teamName");
						String pts = rs.getString("s_PTS");
						String fg = rs.getString("s_FG");
						String fga = rs.getString("s_FGA");
						String fgp = rs.getString("s_FGP");
						String threep = rs.getString("s_3P");
						String threepa = rs.getString("s_3PA");
						String threepp = rs.getString("s_3PP");
						String ft = rs.getString("s_FT");
						String fta = rs.getString("s_FTA");
						String ftp = rs.getString("s_FTP");
						String orb = rs.getString("s_ORB");
						String drb = rs.getString("s_DRB");
						String trb = rs.getString("s_TRB");
						String ast = rs.getString("s_AST");
						String stl = rs.getString("s_STL");
						String blk = rs.getString("s_BLK");
						String tov = rs.getString("s_TOV");
						String pf = rs.getString("s_PF");
						String plusmin = rs.getString("s_plusmin");
						System.out.println(plrID + "\t" +  posID + "\t" +  pts + "\t" +
								fg + "\t" +  fga + "\t" +  fgp + "\t" +  threep + "\t" +  threepa + "\t" +
								threepp + "\t" +  ft + "\t" +  fta + "\t" +  ftp +  "\t" +
								orb + "\t" +  drb + "\t" +  trb + "\t" +  ast + "\t" +  stl + "\t" +
								blk + "\t" +  tov + "\t" +  pf + "\t" + plusmin);
						System.out.println();
					}
				}
				else if (gameNum == 3) {
					ResultSet rs = stat.executeQuery(
							"Select *"+
									"From statistics INNER JOIN matches ON (s_matchID = m_matchID)" +
									"INNER JOIN team t1 on (m_team1ID = t1.t_teamID)" +
									"INNER JOIN team t2 on (m_team2ID = t2.t_teamID)" +
							"WHERE s_matchID = '1183';");
					while(rs.next()){
						String plrID = rs.getString("s_playerID");
						String posID = rs.getString("s_positionID");
						String team = rs.getString("t_teamName");
						String pts = rs.getString("s_PTS");
						String fg = rs.getString("s_FG");
						String fga = rs.getString("s_FGA");
						String fgp = rs.getString("s_FGP");
						String threep = rs.getString("s_3P");
						String threepa = rs.getString("s_3PA");
						String threepp = rs.getString("s_3PP");
						String ft = rs.getString("s_FT");
						String fta = rs.getString("s_FTA");
						String ftp = rs.getString("s_FTP");
						String orb = rs.getString("s_ORB");
						String drb = rs.getString("s_DRB");
						String trb = rs.getString("s_TRB");
						String ast = rs.getString("s_AST");
						String stl = rs.getString("s_STL");
						String blk = rs.getString("s_BLK");
						String tov = rs.getString("s_TOV");
						String pf = rs.getString("s_PF");
						String plusmin = rs.getString("s_plusmin");
						System.out.println(plrID + "\t" +  posID + "\t" +  pts + "\t" +
								fg + "\t" +  fga + "\t" +  fgp + "\t" +  threep + "\t" +  threepa + "\t" +
								threepp + "\t" +  ft + "\t" +  fta + "\t" +  ftp +  "\t" +
								orb + "\t" +  drb + "\t" +  trb + "\t" +  ast + "\t" +  stl + "\t" +
								blk + "\t" +  tov + "\t" +  pf + "\t" + plusmin);
						System.out.println();
					}
				}
				else if (gameNum == 4) {
					ResultSet rs = stat.executeQuery(
							"Select *"+
									"From statistics INNER JOIN matches ON (s_matchID = m_matchID)" +
									"INNER JOIN team t1 on (m_team1ID = t1.t_teamID)" +
									"INNER JOIN team t2 on (m_team2ID = t2.t_teamID)" +
							"WHERE s_matchID = '1184';");
					while(rs.next()){
						String plrID = rs.getString("s_playerID");
						String posID = rs.getString("s_positionID");
						String team = rs.getString("t_teamName");
						String pts = rs.getString("s_PTS");
						String fg = rs.getString("s_FG");
						String fga = rs.getString("s_FGA");
						String fgp = rs.getString("s_FGP");
						String threep = rs.getString("s_3P");
						String threepa = rs.getString("s_3PA");
						String threepp = rs.getString("s_3PP");
						String ft = rs.getString("s_FT");
						String fta = rs.getString("s_FTA");
						String ftp = rs.getString("s_FTP");
						String orb = rs.getString("s_ORB");
						String drb = rs.getString("s_DRB");
						String trb = rs.getString("s_TRB");
						String ast = rs.getString("s_AST");
						String stl = rs.getString("s_STL");
						String blk = rs.getString("s_BLK");
						String tov = rs.getString("s_TOV");
						String pf = rs.getString("s_PF");
						String plusmin = rs.getString("s_plusmin");
						System.out.println(plrID + "\t" +  posID + "\t" +  pts + "\t" +
								fg + "\t" +  fga + "\t" +  fgp + "\t" +  threep + "\t" +  threepa + "\t" +
								threepp + "\t" +  ft + "\t" +  fta + "\t" +  ftp +  "\t" +
								orb + "\t" +  drb + "\t" +  trb + "\t" +  ast + "\t" +  stl + "\t" +
								blk + "\t" +  tov + "\t" +  pf + "\t" + plusmin);
						System.out.println();
					}
				}
			}
			else if (matchStat == 127){
				System.out.println("Which game would you like to view: 1-6");
				int gameNum = scan.nextInt();
				if (gameNum == 1) {
					ResultSet rs = stat.executeQuery(
							"Select *"+
									"From statistics INNER JOIN matches ON (s_matchID = m_matchID)" +
									"INNER JOIN team t1 on (m_team1ID = t1.t_teamID)" +
									"INNER JOIN team t2 on (m_team2ID = t2.t_teamID)" +
							"WHERE s_matchID = '1271';");
					while(rs.next()){
						String plrID = rs.getString("s_playerID");
						String posID = rs.getString("s_positionID");
						String team = rs.getString("t_teamName");
						String pts = rs.getString("s_PTS");
						String fg = rs.getString("s_FG");
						String fga = rs.getString("s_FGA");
						String fgp = rs.getString("s_FGP");
						String threep = rs.getString("s_3P");
						String threepa = rs.getString("s_3PA");
						String threepp = rs.getString("s_3PP");
						String ft = rs.getString("s_FT");
						String fta = rs.getString("s_FTA");
						String ftp = rs.getString("s_FTP");
						String orb = rs.getString("s_ORB");
						String drb = rs.getString("s_DRB");
						String trb = rs.getString("s_TRB");
						String ast = rs.getString("s_AST");
						String stl = rs.getString("s_STL");
						String blk = rs.getString("s_BLK");
						String tov = rs.getString("s_TOV");
						String pf = rs.getString("s_PF");
						String plusmin = rs.getString("s_plusmin");
						System.out.println(plrID + "\t" +  posID + "\t" +  pts + "\t" +
								fg + "\t" +  fga + "\t" +  fgp + "\t" +  threep + "\t" +  threepa + "\t" +
								threepp + "\t" +  ft + "\t" +  fta + "\t" +  ftp +  "\t" +
								orb + "\t" +  drb + "\t" +  trb + "\t" +  ast + "\t" +  stl + "\t" +
								blk + "\t" +  tov + "\t" +  pf + "\t" + plusmin);
						System.out.println();
					}
				}
				else if (gameNum == 2) {
					ResultSet rs = stat.executeQuery(
							"Select *"+
									"From statistics INNER JOIN matches ON (s_matchID = m_matchID)" +
									"INNER JOIN team t1 on (m_team1ID = t1.t_teamID)" +
									"INNER JOIN team t2 on (m_team2ID = t2.t_teamID)" +
							"WHERE s_matchID = '1272';");
					while(rs.next()){
						String plrID = rs.getString("s_playerID");
						String posID = rs.getString("s_positionID");
						String team = rs.getString("t_teamName");
						String pts = rs.getString("s_PTS");
						String fg = rs.getString("s_FG");
						String fga = rs.getString("s_FGA");
						String fgp = rs.getString("s_FGP");
						String threep = rs.getString("s_3P");
						String threepa = rs.getString("s_3PA");
						String threepp = rs.getString("s_3PP");
						String ft = rs.getString("s_FT");
						String fta = rs.getString("s_FTA");
						String ftp = rs.getString("s_FTP");
						String orb = rs.getString("s_ORB");
						String drb = rs.getString("s_DRB");
						String trb = rs.getString("s_TRB");
						String ast = rs.getString("s_AST");
						String stl = rs.getString("s_STL");
						String blk = rs.getString("s_BLK");
						String tov = rs.getString("s_TOV");
						String pf = rs.getString("s_PF");
						String plusmin = rs.getString("s_plusmin");
						System.out.println(plrID + "\t" +  posID + "\t" +  pts + "\t" +
								fg + "\t" +  fga + "\t" +  fgp + "\t" +  threep + "\t" +  threepa + "\t" +
								threepp + "\t" +  ft + "\t" +  fta + "\t" +  ftp +  "\t" +
								orb + "\t" +  drb + "\t" +  trb + "\t" +  ast + "\t" +  stl + "\t" +
								blk + "\t" +  tov + "\t" +  pf + "\t" + plusmin);
						System.out.println();
					}
				}
				else if (gameNum == 3) {
					ResultSet rs = stat.executeQuery(
							"Select *"+
									"From statistics INNER JOIN matches ON (s_matchID = m_matchID)" +
									"INNER JOIN team t1 on (m_team1ID = t1.t_teamID)" +
									"INNER JOIN team t2 on (m_team2ID = t2.t_teamID)" +
							"WHERE s_matchID = '1273';");
					while(rs.next()){
						String plrID = rs.getString("s_playerID");
						String posID = rs.getString("s_positionID");
						String team = rs.getString("t_teamName");
						String pts = rs.getString("s_PTS");
						String fg = rs.getString("s_FG");
						String fga = rs.getString("s_FGA");
						String fgp = rs.getString("s_FGP");
						String threep = rs.getString("s_3P");
						String threepa = rs.getString("s_3PA");
						String threepp = rs.getString("s_3PP");
						String ft = rs.getString("s_FT");
						String fta = rs.getString("s_FTA");
						String ftp = rs.getString("s_FTP");
						String orb = rs.getString("s_ORB");
						String drb = rs.getString("s_DRB");
						String trb = rs.getString("s_TRB");
						String ast = rs.getString("s_AST");
						String stl = rs.getString("s_STL");
						String blk = rs.getString("s_BLK");
						String tov = rs.getString("s_TOV");
						String pf = rs.getString("s_PF");
						String plusmin = rs.getString("s_plusmin");
						System.out.println(plrID + "\t" +  posID + "\t" +  pts + "\t" +
								fg + "\t" +  fga + "\t" +  fgp + "\t" +  threep + "\t" +  threepa + "\t" +
								threepp + "\t" +  ft + "\t" +  fta + "\t" +  ftp +  "\t" +
								orb + "\t" +  drb + "\t" +  trb + "\t" +  ast + "\t" +  stl + "\t" +
								blk + "\t" +  tov + "\t" +  pf + "\t" + plusmin);
						System.out.println();
					}
				}
				else if (gameNum == 4) {
					ResultSet rs = stat.executeQuery(
							"Select *"+
									"From statistics INNER JOIN matches ON (s_matchID = m_matchID)" +
									"INNER JOIN team t1 on (m_team1ID = t1.t_teamID)" +
									"INNER JOIN team t2 on (m_team2ID = t2.t_teamID)" +
							"WHERE s_matchID = '1274';");
					while(rs.next()){
						String plrID = rs.getString("s_playerID");
						String posID = rs.getString("s_positionID");
						String team = rs.getString("t_teamName");
						String pts = rs.getString("s_PTS");
						String fg = rs.getString("s_FG");
						String fga = rs.getString("s_FGA");
						String fgp = rs.getString("s_FGP");
						String threep = rs.getString("s_3P");
						String threepa = rs.getString("s_3PA");
						String threepp = rs.getString("s_3PP");
						String ft = rs.getString("s_FT");
						String fta = rs.getString("s_FTA");
						String ftp = rs.getString("s_FTP");
						String orb = rs.getString("s_ORB");
						String drb = rs.getString("s_DRB");
						String trb = rs.getString("s_TRB");
						String ast = rs.getString("s_AST");
						String stl = rs.getString("s_STL");
						String blk = rs.getString("s_BLK");
						String tov = rs.getString("s_TOV");
						String pf = rs.getString("s_PF");
						String plusmin = rs.getString("s_plusmin");
						System.out.println(plrID + "\t" +  posID + "\t" +  pts + "\t" +
								fg + "\t" +  fga + "\t" +  fgp + "\t" +  threep + "\t" +  threepa + "\t" +
								threepp + "\t" +  ft + "\t" +  fta + "\t" +  ftp +  "\t" +
								orb + "\t" +  drb + "\t" +  trb + "\t" +  ast + "\t" +  stl + "\t" +
								blk + "\t" +  tov + "\t" +  pf + "\t" + plusmin);
						System.out.println();
					}
				}
				else if (gameNum == 5) {
					ResultSet rs = stat.executeQuery(
							"Select *"+
									"From statistics INNER JOIN matches ON (s_matchID = m_matchID)" +
									"INNER JOIN team t1 on (m_team1ID = t1.t_teamID)" +
									"INNER JOIN team t2 on (m_team2ID = t2.t_teamID)" +
							"WHERE s_matchID = '1275';");
					while(rs.next()){
						String plrID = rs.getString("s_playerID");
						String posID = rs.getString("s_positionID");
						String team = rs.getString("t_teamName");
						String pts = rs.getString("s_PTS");
						String fg = rs.getString("s_FG");
						String fga = rs.getString("s_FGA");
						String fgp = rs.getString("s_FGP");
						String threep = rs.getString("s_3P");
						String threepa = rs.getString("s_3PA");
						String threepp = rs.getString("s_3PP");
						String ft = rs.getString("s_FT");
						String fta = rs.getString("s_FTA");
						String ftp = rs.getString("s_FTP");
						String orb = rs.getString("s_ORB");
						String drb = rs.getString("s_DRB");
						String trb = rs.getString("s_TRB");
						String ast = rs.getString("s_AST");
						String stl = rs.getString("s_STL");
						String blk = rs.getString("s_BLK");
						String tov = rs.getString("s_TOV");
						String pf = rs.getString("s_PF");
						String plusmin = rs.getString("s_plusmin");
						System.out.println(plrID + "\t" +  posID + "\t" +  pts + "\t" +
								fg + "\t" +  fga + "\t" +  fgp + "\t" +  threep + "\t" +  threepa + "\t" +
								threepp + "\t" +  ft + "\t" +  fta + "\t" +  ftp +  "\t" +
								orb + "\t" +  drb + "\t" +  trb + "\t" +  ast + "\t" +  stl + "\t" +
								blk + "\t" +  tov + "\t" +  pf + "\t" + plusmin);
						System.out.println();
					}
				}
				else if (gameNum == 6) {
					ResultSet rs = stat.executeQuery(
							"Select *"+
									"From statistics INNER JOIN matches ON (s_matchID = m_matchID)" +
									"INNER JOIN team t1 on (m_team1ID = t1.t_teamID)" +
									"INNER JOIN team t2 on (m_team2ID = t2.t_teamID)" +
							"WHERE s_matchID = '1276';");
					while(rs.next()){
						String plrID = rs.getString("s_playerID");
						String posID = rs.getString("s_positionID");
						String team = rs.getString("t_teamName");
						String pts = rs.getString("s_PTS");
						String fg = rs.getString("s_FG");
						String fga = rs.getString("s_FGA");
						String fgp = rs.getString("s_FGP");
						String threep = rs.getString("s_3P");
						String threepa = rs.getString("s_3PA");
						String threepp = rs.getString("s_3PP");
						String ft = rs.getString("s_FT");
						String fta = rs.getString("s_FTA");
						String ftp = rs.getString("s_FTP");
						String orb = rs.getString("s_ORB");
						String drb = rs.getString("s_DRB");
						String trb = rs.getString("s_TRB");
						String ast = rs.getString("s_AST");
						String stl = rs.getString("s_STL");
						String blk = rs.getString("s_BLK");
						String tov = rs.getString("s_TOV");
						String pf = rs.getString("s_PF");
						String plusmin = rs.getString("s_plusmin");
						System.out.println(plrID + "\t" +  posID + "\t" +  pts + "\t" +
								fg + "\t" +  fga + "\t" +  fgp + "\t" +  threep + "\t" +  threepa + "\t" +
								threepp + "\t" +  ft + "\t" +  fta + "\t" +  ftp +  "\t" +
								orb + "\t" +  drb + "\t" +  trb + "\t" +  ast + "\t" +  stl + "\t" +
								blk + "\t" +  tov + "\t" +  pf + "\t" + plusmin);
						System.out.println();
					}
				}
			}
			else if (matchStat == 136){
				System.out.println("Which game would you like to view: 1-5");
				int gameNum = scan.nextInt();
				if (gameNum == 1) {
					ResultSet rs = stat.executeQuery(
							"Select *"+
									"From statistics INNER JOIN matches ON (s_matchID = m_matchID)" +
									"INNER JOIN team t1 on (m_team1ID = t1.t_teamID)" +
									"INNER JOIN team t2 on (m_team2ID = t2.t_teamID)" +
							"WHERE s_matchID = '1361';");
					while(rs.next()){
						String plrID = rs.getString("s_playerID");
						String posID = rs.getString("s_positionID");
						String team = rs.getString("t_teamName");
						String pts = rs.getString("s_PTS");
						String fg = rs.getString("s_FG");
						String fga = rs.getString("s_FGA");
						String fgp = rs.getString("s_FGP");
						String threep = rs.getString("s_3P");
						String threepa = rs.getString("s_3PA");
						String threepp = rs.getString("s_3PP");
						String ft = rs.getString("s_FT");
						String fta = rs.getString("s_FTA");
						String ftp = rs.getString("s_FTP");
						String orb = rs.getString("s_ORB");
						String drb = rs.getString("s_DRB");
						String trb = rs.getString("s_TRB");
						String ast = rs.getString("s_AST");
						String stl = rs.getString("s_STL");
						String blk = rs.getString("s_BLK");
						String tov = rs.getString("s_TOV");
						String pf = rs.getString("s_PF");
						String plusmin = rs.getString("s_plusmin");
						System.out.println(plrID + "\t" +  posID + "\t" +  pts + "\t" +
								fg + "\t" +  fga + "\t" +  fgp + "\t" +  threep + "\t" +  threepa + "\t" +
								threepp + "\t" +  ft + "\t" +  fta + "\t" +  ftp +  "\t" +
								orb + "\t" +  drb + "\t" +  trb + "\t" +  ast + "\t" +  stl + "\t" +
								blk + "\t" +  tov + "\t" +  pf + "\t" + plusmin);
						System.out.println();
					}
				}
				else if (gameNum == 2) {
					ResultSet rs = stat.executeQuery(
							"Select *"+
									"From statistics INNER JOIN matches ON (s_matchID = m_matchID)" +
									"INNER JOIN team t1 on (m_team1ID = t1.t_teamID)" +
									"INNER JOIN team t2 on (m_team2ID = t2.t_teamID)" +
							"WHERE s_matchID = '1362';");
					while(rs.next()){
						String plrID = rs.getString("s_playerID");
						String posID = rs.getString("s_positionID");
						String team = rs.getString("t_teamName");
						String pts = rs.getString("s_PTS");
						String fg = rs.getString("s_FG");
						String fga = rs.getString("s_FGA");
						String fgp = rs.getString("s_FGP");
						String threep = rs.getString("s_3P");
						String threepa = rs.getString("s_3PA");
						String threepp = rs.getString("s_3PP");
						String ft = rs.getString("s_FT");
						String fta = rs.getString("s_FTA");
						String ftp = rs.getString("s_FTP");
						String orb = rs.getString("s_ORB");
						String drb = rs.getString("s_DRB");
						String trb = rs.getString("s_TRB");
						String ast = rs.getString("s_AST");
						String stl = rs.getString("s_STL");
						String blk = rs.getString("s_BLK");
						String tov = rs.getString("s_TOV");
						String pf = rs.getString("s_PF");
						String plusmin = rs.getString("s_plusmin");
						System.out.println(plrID + "\t" +  posID + "\t" +  pts + "\t" +
								fg + "\t" +  fga + "\t" +  fgp + "\t" +  threep + "\t" +  threepa + "\t" +
								threepp + "\t" +  ft + "\t" +  fta + "\t" +  ftp +  "\t" +
								orb + "\t" +  drb + "\t" +  trb + "\t" +  ast + "\t" +  stl + "\t" +
								blk + "\t" +  tov + "\t" +  pf + "\t" + plusmin);
						System.out.println();
					}
				}
				else if (gameNum == 3) {
					ResultSet rs = stat.executeQuery(
							"Select *"+
									"From statistics INNER JOIN matches ON (s_matchID = m_matchID)" +
									"INNER JOIN team t1 on (m_team1ID = t1.t_teamID)" +
									"INNER JOIN team t2 on (m_team2ID = t2.t_teamID)" +
							"WHERE s_matchID = '1363';");
					while(rs.next()){
						String plrID = rs.getString("s_playerID");
						String posID = rs.getString("s_positionID");
						String team = rs.getString("t_teamName");
						String pts = rs.getString("s_PTS");
						String fg = rs.getString("s_FG");
						String fga = rs.getString("s_FGA");
						String fgp = rs.getString("s_FGP");
						String threep = rs.getString("s_3P");
						String threepa = rs.getString("s_3PA");
						String threepp = rs.getString("s_3PP");
						String ft = rs.getString("s_FT");
						String fta = rs.getString("s_FTA");
						String ftp = rs.getString("s_FTP");
						String orb = rs.getString("s_ORB");
						String drb = rs.getString("s_DRB");
						String trb = rs.getString("s_TRB");
						String ast = rs.getString("s_AST");
						String stl = rs.getString("s_STL");
						String blk = rs.getString("s_BLK");
						String tov = rs.getString("s_TOV");
						String pf = rs.getString("s_PF");
						String plusmin = rs.getString("s_plusmin");
						System.out.println(plrID + "\t" +  posID + "\t" +  pts + "\t" +
								fg + "\t" +  fga + "\t" +  fgp + "\t" +  threep + "\t" +  threepa + "\t" +
								threepp + "\t" +  ft + "\t" +  fta + "\t" +  ftp +  "\t" +
								orb + "\t" +  drb + "\t" +  trb + "\t" +  ast + "\t" +  stl + "\t" +
								blk + "\t" +  tov + "\t" +  pf + "\t" + plusmin);
						System.out.println();
					}
				}
				else if (gameNum == 4) {
					ResultSet rs = stat.executeQuery(
							"Select *"+
									"From statistics INNER JOIN matches ON (s_matchID = m_matchID)" +
									"INNER JOIN team t1 on (m_team1ID = t1.t_teamID)" +
									"INNER JOIN team t2 on (m_team2ID = t2.t_teamID)" +
							"WHERE s_matchID = '1364';");
					while(rs.next()){
						String plrID = rs.getString("s_playerID");
						String posID = rs.getString("s_positionID");
						String team = rs.getString("t_teamName");
						String pts = rs.getString("s_PTS");
						String fg = rs.getString("s_FG");
						String fga = rs.getString("s_FGA");
						String fgp = rs.getString("s_FGP");
						String threep = rs.getString("s_3P");
						String threepa = rs.getString("s_3PA");
						String threepp = rs.getString("s_3PP");
						String ft = rs.getString("s_FT");
						String fta = rs.getString("s_FTA");
						String ftp = rs.getString("s_FTP");
						String orb = rs.getString("s_ORB");
						String drb = rs.getString("s_DRB");
						String trb = rs.getString("s_TRB");
						String ast = rs.getString("s_AST");
						String stl = rs.getString("s_STL");
						String blk = rs.getString("s_BLK");
						String tov = rs.getString("s_TOV");
						String pf = rs.getString("s_PF");
						String plusmin = rs.getString("s_plusmin");
						System.out.println(plrID + "\t" +  posID + "\t" +  pts + "\t" +
								fg + "\t" +  fga + "\t" +  fgp + "\t" +  threep + "\t" +  threepa + "\t" +
								threepp + "\t" +  ft + "\t" +  fta + "\t" +  ftp +  "\t" +
								orb + "\t" +  drb + "\t" +  trb + "\t" +  ast + "\t" +  stl + "\t" +
								blk + "\t" +  tov + "\t" +  pf + "\t" + plusmin);
						System.out.println();
					}
				}
				else if (gameNum == 5) {
					ResultSet rs = stat.executeQuery(
							"Select *"+
									"From statistics INNER JOIN matches ON (s_matchID = m_matchID)" +
									"INNER JOIN team t1 on (m_team1ID = t1.t_teamID)" +
									"INNER JOIN team t2 on (m_team2ID = t2.t_teamID)" +
							"WHERE s_matchID = '1365';");
					while(rs.next()){
						String plrID = rs.getString("s_playerID");
						String posID = rs.getString("s_positionID");
						String team = rs.getString("t_teamName");
						String pts = rs.getString("s_PTS");
						String fg = rs.getString("s_FG");
						String fga = rs.getString("s_FGA");
						String fgp = rs.getString("s_FGP");
						String threep = rs.getString("s_3P");
						String threepa = rs.getString("s_3PA");
						String threepp = rs.getString("s_3PP");
						String ft = rs.getString("s_FT");
						String fta = rs.getString("s_FTA");
						String ftp = rs.getString("s_FTP");
						String orb = rs.getString("s_ORB");
						String drb = rs.getString("s_DRB");
						String trb = rs.getString("s_TRB");
						String ast = rs.getString("s_AST");
						String stl = rs.getString("s_STL");
						String blk = rs.getString("s_BLK");
						String tov = rs.getString("s_TOV");
						String pf = rs.getString("s_PF");
						String plusmin = rs.getString("s_plusmin");
						System.out.println(plrID + "\t" +  posID + "\t" +  pts + "\t" +
								fg + "\t" +  fga + "\t" +  fgp + "\t" +  threep + "\t" +  threepa + "\t" +
								threepp + "\t" +  ft + "\t" +  fta + "\t" +  ftp +  "\t" +
								orb + "\t" +  drb + "\t" +  trb + "\t" +  ast + "\t" +  stl + "\t" +
								blk + "\t" +  tov + "\t" +  pf + "\t" + plusmin);
						System.out.println();
					}
				}
			}
			if (matchStat == 12745){
				System.out.println("Which game would you like to view: 1-7");
				int gameNum = scan.nextInt();
				if (gameNum == 1) {
					ResultSet rs = stat.executeQuery(
							"Select *"+
									"From statistics INNER JOIN matches ON (s_matchID = m_matchID)" +
									"INNER JOIN team t1 on (m_team1ID = t1.t_teamID)" +
									"INNER JOIN team t2 on (m_team2ID = t2.t_teamID)" +
							"WHERE s_matchID = '1451';");
					while(rs.next()){
						String plrID = rs.getString("s_playerID");
						String posID = rs.getString("s_positionID");
						String team = rs.getString("t_teamName");
						String pts = rs.getString("s_PTS");
						String fg = rs.getString("s_FG");
						String fga = rs.getString("s_FGA");
						String fgp = rs.getString("s_FGP");
						String threep = rs.getString("s_3P");
						String threepa = rs.getString("s_3PA");
						String threepp = rs.getString("s_3PP");
						String ft = rs.getString("s_FT");
						String fta = rs.getString("s_FTA");
						String ftp = rs.getString("s_FTP");
						String orb = rs.getString("s_ORB");
						String drb = rs.getString("s_DRB");
						String trb = rs.getString("s_TRB");
						String ast = rs.getString("s_AST");
						String stl = rs.getString("s_STL");
						String blk = rs.getString("s_BLK");
						String tov = rs.getString("s_TOV");
						String pf = rs.getString("s_PF");
						String plusmin = rs.getString("s_plusmin");
						System.out.println(plrID + "\t" +  posID + "\t" +  pts + "\t" +
								fg + "\t" +  fga + "\t" +  fgp + "\t" +  threep + "\t" +  threepa + "\t" +
								threepp + "\t" +  ft + "\t" +  fta + "\t" +  ftp +  "\t" +
								orb + "\t" +  drb + "\t" +  trb + "\t" +  ast + "\t" +  stl + "\t" +
								blk + "\t" +  tov + "\t" +  pf + "\t" + plusmin);
						System.out.println();
					}
				}
				else if (gameNum == 2) {
					ResultSet rs = stat.executeQuery(
							"Select *"+
									"From statistics INNER JOIN matches ON (s_matchID = m_matchID)" +
									"INNER JOIN team t1 on (m_team1ID = t1.t_teamID)" +
									"INNER JOIN team t2 on (m_team2ID = t2.t_teamID)" +
							"WHERE s_matchID = '1452';");
					while(rs.next()){
						String plrID = rs.getString("s_playerID");
						String posID = rs.getString("s_positionID");
						String team = rs.getString("t_teamName");
						String pts = rs.getString("s_PTS");
						String fg = rs.getString("s_FG");
						String fga = rs.getString("s_FGA");
						String fgp = rs.getString("s_FGP");
						String threep = rs.getString("s_3P");
						String threepa = rs.getString("s_3PA");
						String threepp = rs.getString("s_3PP");
						String ft = rs.getString("s_FT");
						String fta = rs.getString("s_FTA");
						String ftp = rs.getString("s_FTP");
						String orb = rs.getString("s_ORB");
						String drb = rs.getString("s_DRB");
						String trb = rs.getString("s_TRB");
						String ast = rs.getString("s_AST");
						String stl = rs.getString("s_STL");
						String blk = rs.getString("s_BLK");
						String tov = rs.getString("s_TOV");
						String pf = rs.getString("s_PF");
						String plusmin = rs.getString("s_plusmin");
						System.out.println(plrID + "\t" +  posID + "\t" +  pts + "\t" +
								fg + "\t" +  fga + "\t" +  fgp + "\t" +  threep + "\t" +  threepa + "\t" +
								threepp + "\t" +  ft + "\t" +  fta + "\t" +  ftp +  "\t" +
								orb + "\t" +  drb + "\t" +  trb + "\t" +  ast + "\t" +  stl + "\t" +
								blk + "\t" +  tov + "\t" +  pf + "\t" + plusmin);
						System.out.println();
					}
				}
				else if (gameNum == 3) {
					ResultSet rs = stat.executeQuery(
							"Select *"+
									"From statistics INNER JOIN matches ON (s_matchID = m_matchID)" +
									"INNER JOIN team t1 on (m_team1ID = t1.t_teamID)" +
									"INNER JOIN team t2 on (m_team2ID = t2.t_teamID)" +
							"WHERE s_matchID = '1453';");
					while(rs.next()){
						String plrID = rs.getString("s_playerID");
						String posID = rs.getString("s_positionID");
						String team = rs.getString("t_teamName");
						String pts = rs.getString("s_PTS");
						String fg = rs.getString("s_FG");
						String fga = rs.getString("s_FGA");
						String fgp = rs.getString("s_FGP");
						String threep = rs.getString("s_3P");
						String threepa = rs.getString("s_3PA");
						String threepp = rs.getString("s_3PP");
						String ft = rs.getString("s_FT");
						String fta = rs.getString("s_FTA");
						String ftp = rs.getString("s_FTP");
						String orb = rs.getString("s_ORB");
						String drb = rs.getString("s_DRB");
						String trb = rs.getString("s_TRB");
						String ast = rs.getString("s_AST");
						String stl = rs.getString("s_STL");
						String blk = rs.getString("s_BLK");
						String tov = rs.getString("s_TOV");
						String pf = rs.getString("s_PF");
						String plusmin = rs.getString("s_plusmin");
						System.out.println(plrID + "\t" +  posID + "\t" +  pts + "\t" +
								fg + "\t" +  fga + "\t" +  fgp + "\t" +  threep + "\t" +  threepa + "\t" +
								threepp + "\t" +  ft + "\t" +  fta + "\t" +  ftp +  "\t" +
								orb + "\t" +  drb + "\t" +  trb + "\t" +  ast + "\t" +  stl + "\t" +
								blk + "\t" +  tov + "\t" +  pf + "\t" + plusmin);
						System.out.println();
					}
				}
				else if (gameNum == 4) {
					ResultSet rs = stat.executeQuery(
							"Select *"+
									"From statistics INNER JOIN matches ON (s_matchID = m_matchID)" +
									"INNER JOIN team t1 on (m_team1ID = t1.t_teamID)" +
									"INNER JOIN team t2 on (m_team2ID = t2.t_teamID)" +
							"WHERE s_matchID = '1454';");
					while(rs.next()){
						String plrID = rs.getString("s_playerID");
						String posID = rs.getString("s_positionID");
						String team = rs.getString("t_teamName");
						String pts = rs.getString("s_PTS");
						String fg = rs.getString("s_FG");
						String fga = rs.getString("s_FGA");
						String fgp = rs.getString("s_FGP");
						String threep = rs.getString("s_3P");
						String threepa = rs.getString("s_3PA");
						String threepp = rs.getString("s_3PP");
						String ft = rs.getString("s_FT");
						String fta = rs.getString("s_FTA");
						String ftp = rs.getString("s_FTP");
						String orb = rs.getString("s_ORB");
						String drb = rs.getString("s_DRB");
						String trb = rs.getString("s_TRB");
						String ast = rs.getString("s_AST");
						String stl = rs.getString("s_STL");
						String blk = rs.getString("s_BLK");
						String tov = rs.getString("s_TOV");
						String pf = rs.getString("s_PF");
						String plusmin = rs.getString("s_plusmin");
						System.out.println(plrID + "\t" +  posID + "\t" +  pts + "\t" +
								fg + "\t" +  fga + "\t" +  fgp + "\t" +  threep + "\t" +  threepa + "\t" +
								threepp + "\t" +  ft + "\t" +  fta + "\t" +  ftp +  "\t" +
								orb + "\t" +  drb + "\t" +  trb + "\t" +  ast + "\t" +  stl + "\t" +
								blk + "\t" +  tov + "\t" +  pf + "\t" + plusmin);
						System.out.println();
					}
				}
				else if (gameNum == 5) {
					ResultSet rs = stat.executeQuery(
							"Select *"+
									"From statistics INNER JOIN matches ON (s_matchID = m_matchID)" +
									"INNER JOIN team t1 on (m_team1ID = t1.t_teamID)" +
									"INNER JOIN team t2 on (m_team2ID = t2.t_teamID)" +
							"WHERE s_matchID = '1455';");
					while(rs.next()){
						String plrID = rs.getString("s_playerID");
						String posID = rs.getString("s_positionID");
						String team = rs.getString("t_teamName");
						String pts = rs.getString("s_PTS");
						String fg = rs.getString("s_FG");
						String fga = rs.getString("s_FGA");
						String fgp = rs.getString("s_FGP");
						String threep = rs.getString("s_3P");
						String threepa = rs.getString("s_3PA");
						String threepp = rs.getString("s_3PP");
						String ft = rs.getString("s_FT");
						String fta = rs.getString("s_FTA");
						String ftp = rs.getString("s_FTP");
						String orb = rs.getString("s_ORB");
						String drb = rs.getString("s_DRB");
						String trb = rs.getString("s_TRB");
						String ast = rs.getString("s_AST");
						String stl = rs.getString("s_STL");
						String blk = rs.getString("s_BLK");
						String tov = rs.getString("s_TOV");
						String pf = rs.getString("s_PF");
						String plusmin = rs.getString("s_plusmin");
						System.out.println(plrID + "\t" +  posID + "\t" +  pts + "\t" +
								fg + "\t" +  fga + "\t" +  fgp + "\t" +  threep + "\t" +  threepa + "\t" +
								threepp + "\t" +  ft + "\t" +  fta + "\t" +  ftp +  "\t" +
								orb + "\t" +  drb + "\t" +  trb + "\t" +  ast + "\t" +  stl + "\t" +
								blk + "\t" +  tov + "\t" +  pf + "\t" + plusmin);
						System.out.println();
					}
				}
				else if (gameNum == 6) {
					ResultSet rs = stat.executeQuery(
							"Select *"+
									"From statistics INNER JOIN matches ON (s_matchID = m_matchID)" +
									"INNER JOIN team t1 on (m_team1ID = t1.t_teamID)" +
									"INNER JOIN team t2 on (m_team2ID = t2.t_teamID)" +
							"WHERE s_matchID = '1456';");
					while(rs.next()){
						String plrID = rs.getString("s_playerID");
						String posID = rs.getString("s_positionID");
						String team = rs.getString("t_teamName");
						String pts = rs.getString("s_PTS");
						String fg = rs.getString("s_FG");
						String fga = rs.getString("s_FGA");
						String fgp = rs.getString("s_FGP");
						String threep = rs.getString("s_3P");
						String threepa = rs.getString("s_3PA");
						String threepp = rs.getString("s_3PP");
						String ft = rs.getString("s_FT");
						String fta = rs.getString("s_FTA");
						String ftp = rs.getString("s_FTP");
						String orb = rs.getString("s_ORB");
						String drb = rs.getString("s_DRB");
						String trb = rs.getString("s_TRB");
						String ast = rs.getString("s_AST");
						String stl = rs.getString("s_STL");
						String blk = rs.getString("s_BLK");
						String tov = rs.getString("s_TOV");
						String pf = rs.getString("s_PF");
						String plusmin = rs.getString("s_plusmin");
						System.out.println(plrID + "\t" +  posID + "\t" +  pts + "\t" +
								fg + "\t" +  fga + "\t" +  fgp + "\t" +  threep + "\t" +  threepa + "\t" +
								threepp + "\t" +  ft + "\t" +  fta + "\t" +  ftp +  "\t" +
								orb + "\t" +  drb + "\t" +  trb + "\t" +  ast + "\t" +  stl + "\t" +
								blk + "\t" +  tov + "\t" +  pf + "\t" + plusmin);
						System.out.println();
					}
				}
				else if (gameNum == 7) {
					ResultSet rs = stat.executeQuery(
							"Select *"+
									"From statistics INNER JOIN matches ON (s_matchID = m_matchID)" +
									"INNER JOIN team t1 on (m_team1ID = t1.t_teamID)" +
									"INNER JOIN team t2 on (m_team2ID = t2.t_teamID)" +
							"WHERE s_matchID = '1457';");
					while(rs.next()){
						String plrID = rs.getString("s_playerID");
						String posID = rs.getString("s_positionID");
						String team = rs.getString("t_teamName");
						String pts = rs.getString("s_PTS");
						String fg = rs.getString("s_FG");
						String fga = rs.getString("s_FGA");
						String fgp = rs.getString("s_FGP");
						String threep = rs.getString("s_3P");
						String threepa = rs.getString("s_3PA");
						String threepp = rs.getString("s_3PP");
						String ft = rs.getString("s_FT");
						String fta = rs.getString("s_FTA");
						String ftp = rs.getString("s_FTP");
						String orb = rs.getString("s_ORB");
						String drb = rs.getString("s_DRB");
						String trb = rs.getString("s_TRB");
						String ast = rs.getString("s_AST");
						String stl = rs.getString("s_STL");
						String blk = rs.getString("s_BLK");
						String tov = rs.getString("s_TOV");
						String pf = rs.getString("s_PF");
						String plusmin = rs.getString("s_plusmin");
						System.out.println(plrID + "\t" +  posID + "\t" +  pts + "\t" +
								fg + "\t" +  fga + "\t" +  fgp + "\t" +  threep + "\t" +  threepa + "\t" +
								threepp + "\t" +  ft + "\t" +  fta + "\t" +  ftp +  "\t" +
								orb + "\t" +  drb + "\t" +  trb + "\t" +  ast + "\t" +  stl + "\t" +
								blk + "\t" +  tov + "\t" +  pf + "\t" + plusmin);
						System.out.println();
					}
				}

			}
			else if (matchStat == 215){
				System.out.println("Which game would you like to view: 1-4");
				int gameNum = scan.nextInt();
				if (gameNum == 1) {
					ResultSet rs = stat.executeQuery(
							"Select *"+
									"From statistics INNER JOIN matches ON (s_matchID = m_matchID)" +
									"INNER JOIN team t1 on (m_team1ID = t1.t_teamID)" +
									"INNER JOIN team t2 on (m_team2ID = t2.t_teamID)" +
							"WHERE s_matchID = '2151';");
					while(rs.next()){
						String plrID = rs.getString("s_playerID");
						String posID = rs.getString("s_positionID");
						String team = rs.getString("t_teamName");
						String pts = rs.getString("s_PTS");
						String fg = rs.getString("s_FG");
						String fga = rs.getString("s_FGA");
						String fgp = rs.getString("s_FGP");
						String threep = rs.getString("s_3P");
						String threepa = rs.getString("s_3PA");
						String threepp = rs.getString("s_3PP");
						String ft = rs.getString("s_FT");
						String fta = rs.getString("s_FTA");
						String ftp = rs.getString("s_FTP");
						String orb = rs.getString("s_ORB");
						String drb = rs.getString("s_DRB");
						String trb = rs.getString("s_TRB");
						String ast = rs.getString("s_AST");
						String stl = rs.getString("s_STL");
						String blk = rs.getString("s_BLK");
						String tov = rs.getString("s_TOV");
						String pf = rs.getString("s_PF");
						String plusmin = rs.getString("s_plusmin");
						System.out.println(plrID + "\t" +  posID + "\t" +  pts + "\t" +
								fg + "\t" +  fga + "\t" +  fgp + "\t" +  threep + "\t" +  threepa + "\t" +
								threepp + "\t" +  ft + "\t" +  fta + "\t" +  ftp +  "\t" +
								orb + "\t" +  drb + "\t" +  trb + "\t" +  ast + "\t" +  stl + "\t" +
								blk + "\t" +  tov + "\t" +  pf + "\t" + plusmin);
						System.out.println();
					}
				}
				else if (gameNum == 2) {
					ResultSet rs = stat.executeQuery(
							"Select *"+
									"From statistics INNER JOIN matches ON (s_matchID = m_matchID)" +
									"INNER JOIN team t1 on (m_team1ID = t1.t_teamID)" +
									"INNER JOIN team t2 on (m_team2ID = t2.t_teamID)" +
							"WHERE s_matchID = '2152';");
					while(rs.next()){
						String plrID = rs.getString("s_playerID");
						String posID = rs.getString("s_positionID");
						String team = rs.getString("t_teamName");
						String pts = rs.getString("s_PTS");
						String fg = rs.getString("s_FG");
						String fga = rs.getString("s_FGA");
						String fgp = rs.getString("s_FGP");
						String threep = rs.getString("s_3P");
						String threepa = rs.getString("s_3PA");
						String threepp = rs.getString("s_3PP");
						String ft = rs.getString("s_FT");
						String fta = rs.getString("s_FTA");
						String ftp = rs.getString("s_FTP");
						String orb = rs.getString("s_ORB");
						String drb = rs.getString("s_DRB");
						String trb = rs.getString("s_TRB");
						String ast = rs.getString("s_AST");
						String stl = rs.getString("s_STL");
						String blk = rs.getString("s_BLK");
						String tov = rs.getString("s_TOV");
						String pf = rs.getString("s_PF");
						String plusmin = rs.getString("s_plusmin");
						System.out.println(plrID + "\t" +  posID + "\t" +  pts + "\t" +
								fg + "\t" +  fga + "\t" +  fgp + "\t" +  threep + "\t" +  threepa + "\t" +
								threepp + "\t" +  ft + "\t" +  fta + "\t" +  ftp +  "\t" +
								orb + "\t" +  drb + "\t" +  trb + "\t" +  ast + "\t" +  stl + "\t" +
								blk + "\t" +  tov + "\t" +  pf + "\t" + plusmin);
						System.out.println();
					}
				}
				else if (gameNum == 3) {
					ResultSet rs = stat.executeQuery(
							"Select *"+
									"From statistics INNER JOIN matches ON (s_matchID = m_matchID)" +
									"INNER JOIN team t1 on (m_team1ID = t1.t_teamID)" +
									"INNER JOIN team t2 on (m_team2ID = t2.t_teamID)" +
							"WHERE s_matchID = '2153';");
					while(rs.next()){
						String plrID = rs.getString("s_playerID");
						String posID = rs.getString("s_positionID");
						String team = rs.getString("t_teamName");
						String pts = rs.getString("s_PTS");
						String fg = rs.getString("s_FG");
						String fga = rs.getString("s_FGA");
						String fgp = rs.getString("s_FGP");
						String threep = rs.getString("s_3P");
						String threepa = rs.getString("s_3PA");
						String threepp = rs.getString("s_3PP");
						String ft = rs.getString("s_FT");
						String fta = rs.getString("s_FTA");
						String ftp = rs.getString("s_FTP");
						String orb = rs.getString("s_ORB");
						String drb = rs.getString("s_DRB");
						String trb = rs.getString("s_TRB");
						String ast = rs.getString("s_AST");
						String stl = rs.getString("s_STL");
						String blk = rs.getString("s_BLK");
						String tov = rs.getString("s_TOV");
						String pf = rs.getString("s_PF");
						String plusmin = rs.getString("s_plusmin");
						System.out.println(plrID + "\t" +  posID + "\t" +  pts + "\t" +
								fg + "\t" +  fga + "\t" +  fgp + "\t" +  threep + "\t" +  threepa + "\t" +
								threepp + "\t" +  ft + "\t" +  fta + "\t" +  ftp +  "\t" +
								orb + "\t" +  drb + "\t" +  trb + "\t" +  ast + "\t" +  stl + "\t" +
								blk + "\t" +  tov + "\t" +  pf + "\t" + plusmin);
						System.out.println();
					}
				}
				else if (gameNum == 4) {
					ResultSet rs = stat.executeQuery(
							"Select *"+
									"From statistics INNER JOIN matches ON (s_matchID = m_matchID)" +
									"INNER JOIN team t1 on (m_team1ID = t1.t_teamID)" +
									"INNER JOIN team t2 on (m_team2ID = t2.t_teamID)" +
							"WHERE s_matchID = '2154';");
					while(rs.next()){
						String plrID = rs.getString("s_playerID");
						String posID = rs.getString("s_positionID");
						String team = rs.getString("t_teamName");
						String pts = rs.getString("s_PTS");
						String fg = rs.getString("s_FG");
						String fga = rs.getString("s_FGA");
						String fgp = rs.getString("s_FGP");
						String threep = rs.getString("s_3P");
						String threepa = rs.getString("s_3PA");
						String threepp = rs.getString("s_3PP");
						String ft = rs.getString("s_FT");
						String fta = rs.getString("s_FTA");
						String ftp = rs.getString("s_FTP");
						String orb = rs.getString("s_ORB");
						String drb = rs.getString("s_DRB");
						String trb = rs.getString("s_TRB");
						String ast = rs.getString("s_AST");
						String stl = rs.getString("s_STL");
						String blk = rs.getString("s_BLK");
						String tov = rs.getString("s_TOV");
						String pf = rs.getString("s_PF");
						String plusmin = rs.getString("s_plusmin");
						System.out.println(plrID + "\t" +  posID + "\t" +  pts + "\t" +
								fg + "\t" +  fga + "\t" +  fgp + "\t" +  threep + "\t" +  threepa + "\t" +
								threepp + "\t" +  ft + "\t" +  fta + "\t" +  ftp +  "\t" +
								orb + "\t" +  drb + "\t" +  trb + "\t" +  ast + "\t" +  stl + "\t" +
								blk + "\t" +  tov + "\t" +  pf + "\t" + plusmin);
						System.out.println();
					}
				}
			}
			if (matchStat == 223){
				System.out.println("Which game would you like to view: 1-6");
				int gameNum = scan.nextInt();
				if (gameNum == 1) {
					ResultSet rs = stat.executeQuery(
							"Select *"+
									"From statistics INNER JOIN matches ON (s_matchID = m_matchID)" +
									"INNER JOIN team t1 on (m_team1ID = t1.t_teamID)" +
									"INNER JOIN team t2 on (m_team2ID = t2.t_teamID)" +
							"WHERE s_matchID = '2231';");
					while(rs.next()){
						String plrID = rs.getString("s_playerID");
						String posID = rs.getString("s_positionID");
						String team = rs.getString("t_teamName");
						String pts = rs.getString("s_PTS");
						String fg = rs.getString("s_FG");
						String fga = rs.getString("s_FGA");
						String fgp = rs.getString("s_FGP");
						String threep = rs.getString("s_3P");
						String threepa = rs.getString("s_3PA");
						String threepp = rs.getString("s_3PP");
						String ft = rs.getString("s_FT");
						String fta = rs.getString("s_FTA");
						String ftp = rs.getString("s_FTP");
						String orb = rs.getString("s_ORB");
						String drb = rs.getString("s_DRB");
						String trb = rs.getString("s_TRB");
						String ast = rs.getString("s_AST");
						String stl = rs.getString("s_STL");
						String blk = rs.getString("s_BLK");
						String tov = rs.getString("s_TOV");
						String pf = rs.getString("s_PF");
						String plusmin = rs.getString("s_plusmin");
						System.out.println(plrID + "\t" +  posID + "\t" +  pts + "\t" +
								fg + "\t" +  fga + "\t" +  fgp + "\t" +  threep + "\t" +  threepa + "\t" +
								threepp + "\t" +  ft + "\t" +  fta + "\t" +  ftp +  "\t" +
								orb + "\t" +  drb + "\t" +  trb + "\t" +  ast + "\t" +  stl + "\t" +
								blk + "\t" +  tov + "\t" +  pf + "\t" + plusmin);
						System.out.println();
					}
				}
				else if (gameNum == 2) {
					ResultSet rs = stat.executeQuery(
							"Select *"+
									"From statistics INNER JOIN matches ON (s_matchID = m_matchID)" +
									"INNER JOIN team t1 on (m_team1ID = t1.t_teamID)" +
									"INNER JOIN team t2 on (m_team2ID = t2.t_teamID)" +
							"WHERE s_matchID = '2232';");
					while(rs.next()){
						String plrID = rs.getString("s_playerID");
						String posID = rs.getString("s_positionID");
						String team = rs.getString("t_teamName");
						String pts = rs.getString("s_PTS");
						String fg = rs.getString("s_FG");
						String fga = rs.getString("s_FGA");
						String fgp = rs.getString("s_FGP");
						String threep = rs.getString("s_3P");
						String threepa = rs.getString("s_3PA");
						String threepp = rs.getString("s_3PP");
						String ft = rs.getString("s_FT");
						String fta = rs.getString("s_FTA");
						String ftp = rs.getString("s_FTP");
						String orb = rs.getString("s_ORB");
						String drb = rs.getString("s_DRB");
						String trb = rs.getString("s_TRB");
						String ast = rs.getString("s_AST");
						String stl = rs.getString("s_STL");
						String blk = rs.getString("s_BLK");
						String tov = rs.getString("s_TOV");
						String pf = rs.getString("s_PF");
						String plusmin = rs.getString("s_plusmin");
						System.out.println(plrID + "\t" +  posID + "\t" +  pts + "\t" +
								fg + "\t" +  fga + "\t" +  fgp + "\t" +  threep + "\t" +  threepa + "\t" +
								threepp + "\t" +  ft + "\t" +  fta + "\t" +  ftp +  "\t" +
								orb + "\t" +  drb + "\t" +  trb + "\t" +  ast + "\t" +  stl + "\t" +
								blk + "\t" +  tov + "\t" +  pf + "\t" + plusmin);
						System.out.println();
					}
				}
				else if (gameNum == 3) {
					ResultSet rs = stat.executeQuery(
							"Select *"+
									"From statistics INNER JOIN matches ON (s_matchID = m_matchID)" +
									"INNER JOIN team t1 on (m_team1ID = t1.t_teamID)" +
									"INNER JOIN team t2 on (m_team2ID = t2.t_teamID)" +
							"WHERE s_matchID = '2233';");
					while(rs.next()){
						String plrID = rs.getString("s_playerID");
						String posID = rs.getString("s_positionID");
						String team = rs.getString("t_teamName");
						String pts = rs.getString("s_PTS");
						String fg = rs.getString("s_FG");
						String fga = rs.getString("s_FGA");
						String fgp = rs.getString("s_FGP");
						String threep = rs.getString("s_3P");
						String threepa = rs.getString("s_3PA");
						String threepp = rs.getString("s_3PP");
						String ft = rs.getString("s_FT");
						String fta = rs.getString("s_FTA");
						String ftp = rs.getString("s_FTP");
						String orb = rs.getString("s_ORB");
						String drb = rs.getString("s_DRB");
						String trb = rs.getString("s_TRB");
						String ast = rs.getString("s_AST");
						String stl = rs.getString("s_STL");
						String blk = rs.getString("s_BLK");
						String tov = rs.getString("s_TOV");
						String pf = rs.getString("s_PF");
						String plusmin = rs.getString("s_plusmin");
						System.out.println(plrID + "\t" +  posID + "\t" +  pts + "\t" +
								fg + "\t" +  fga + "\t" +  fgp + "\t" +  threep + "\t" +  threepa + "\t" +
								threepp + "\t" +  ft + "\t" +  fta + "\t" +  ftp +  "\t" +
								orb + "\t" +  drb + "\t" +  trb + "\t" +  ast + "\t" +  stl + "\t" +
								blk + "\t" +  tov + "\t" +  pf + "\t" + plusmin);
						System.out.println();
					}
				}
				else if (gameNum == 4) {
					ResultSet rs = stat.executeQuery(
							"Select *"+
									"From statistics INNER JOIN matches ON (s_matchID = m_matchID)" +
									"INNER JOIN team t1 on (m_team1ID = t1.t_teamID)" +
									"INNER JOIN team t2 on (m_team2ID = t2.t_teamID)" +
							"WHERE s_matchID = '2234';");
					while(rs.next()){
						String plrID = rs.getString("s_playerID");
						String posID = rs.getString("s_positionID");
						String team = rs.getString("t_teamName");
						String pts = rs.getString("s_PTS");
						String fg = rs.getString("s_FG");
						String fga = rs.getString("s_FGA");
						String fgp = rs.getString("s_FGP");
						String threep = rs.getString("s_3P");
						String threepa = rs.getString("s_3PA");
						String threepp = rs.getString("s_3PP");
						String ft = rs.getString("s_FT");
						String fta = rs.getString("s_FTA");
						String ftp = rs.getString("s_FTP");
						String orb = rs.getString("s_ORB");
						String drb = rs.getString("s_DRB");
						String trb = rs.getString("s_TRB");
						String ast = rs.getString("s_AST");
						String stl = rs.getString("s_STL");
						String blk = rs.getString("s_BLK");
						String tov = rs.getString("s_TOV");
						String pf = rs.getString("s_PF");
						String plusmin = rs.getString("s_plusmin");
						System.out.println(plrID + "\t" +  posID + "\t" +  pts + "\t" +
								fg + "\t" +  fga + "\t" +  fgp + "\t" +  threep + "\t" +  threepa + "\t" +
								threepp + "\t" +  ft + "\t" +  fta + "\t" +  ftp +  "\t" +
								orb + "\t" +  drb + "\t" +  trb + "\t" +  ast + "\t" +  stl + "\t" +
								blk + "\t" +  tov + "\t" +  pf + "\t" + plusmin);
						System.out.println();
					}
				}
				else if (gameNum == 5) {
					ResultSet rs = stat.executeQuery(
							"Select *"+
									"From statistics INNER JOIN matches ON (s_matchID = m_matchID)" +
									"INNER JOIN team t1 on (m_team1ID = t1.t_teamID)" +
									"INNER JOIN team t2 on (m_team2ID = t2.t_teamID)" +
							"WHERE s_matchID = '2235';");
					while(rs.next()){
						String plrID = rs.getString("s_playerID");
						String posID = rs.getString("s_positionID");
						String team = rs.getString("t_teamName");
						String pts = rs.getString("s_PTS");
						String fg = rs.getString("s_FG");
						String fga = rs.getString("s_FGA");
						String fgp = rs.getString("s_FGP");
						String threep = rs.getString("s_3P");
						String threepa = rs.getString("s_3PA");
						String threepp = rs.getString("s_3PP");
						String ft = rs.getString("s_FT");
						String fta = rs.getString("s_FTA");
						String ftp = rs.getString("s_FTP");
						String orb = rs.getString("s_ORB");
						String drb = rs.getString("s_DRB");
						String trb = rs.getString("s_TRB");
						String ast = rs.getString("s_AST");
						String stl = rs.getString("s_STL");
						String blk = rs.getString("s_BLK");
						String tov = rs.getString("s_TOV");
						String pf = rs.getString("s_PF");
						String plusmin = rs.getString("s_plusmin");
						System.out.println(plrID + "\t" +  posID + "\t" +  pts + "\t" +
								fg + "\t" +  fga + "\t" +  fgp + "\t" +  threep + "\t" +  threepa + "\t" +
								threepp + "\t" +  ft + "\t" +  fta + "\t" +  ftp +  "\t" +
								orb + "\t" +  drb + "\t" +  trb + "\t" +  ast + "\t" +  stl + "\t" +
								blk + "\t" +  tov + "\t" +  pf + "\t" + plusmin);
						System.out.println();
					}
				}
				else if (gameNum == 6) {
					ResultSet rs = stat.executeQuery(
							"Select *"+
									"From statistics INNER JOIN matches ON (s_matchID = m_matchID)" +
									"INNER JOIN team t1 on (m_team1ID = t1.t_teamID)" +
									"INNER JOIN team t2 on (m_team2ID = t2.t_teamID)" +
							"WHERE s_matchID = '2236';");
					while(rs.next()){
						String plrID = rs.getString("s_playerID");
						String posID = rs.getString("s_positionID");
						String team = rs.getString("t_teamName");
						String pts = rs.getString("s_PTS");
						String fg = rs.getString("s_FG");
						String fga = rs.getString("s_FGA");
						String fgp = rs.getString("s_FGP");
						String threep = rs.getString("s_3P");
						String threepa = rs.getString("s_3PA");
						String threepp = rs.getString("s_3PP");
						String ft = rs.getString("s_FT");
						String fta = rs.getString("s_FTA");
						String ftp = rs.getString("s_FTP");
						String orb = rs.getString("s_ORB");
						String drb = rs.getString("s_DRB");
						String trb = rs.getString("s_TRB");
						String ast = rs.getString("s_AST");
						String stl = rs.getString("s_STL");
						String blk = rs.getString("s_BLK");
						String tov = rs.getString("s_TOV");
						String pf = rs.getString("s_PF");
						String plusmin = rs.getString("s_plusmin");
						System.out.println(plrID + "\t" +  posID + "\t" +  pts + "\t" +
								fg + "\t" +  fga + "\t" +  fgp + "\t" +  threep + "\t" +  threepa + "\t" +
								threepp + "\t" +  ft + "\t" +  fta + "\t" +  ftp +  "\t" +
								orb + "\t" +  drb + "\t" +  trb + "\t" +  ast + "\t" +  stl + "\t" +
								blk + "\t" +  tov + "\t" +  pf + "\t" + plusmin);
						System.out.println();
					}
				}
			}
			if (matchStat == 312){
				System.out.println("Which game would you like to view: 1-4");
				int gameNum = scan.nextInt();
				if (gameNum == 1) {
					ResultSet rs = stat.executeQuery(
							"Select *"+
									"From statistics INNER JOIN matches ON (s_matchID = m_matchID)" +
									"INNER JOIN team t1 on (m_team1ID = t1.t_teamID)" +
									"INNER JOIN team t2 on (m_team2ID = t2.t_teamID)" +
							"WHERE s_matchID = '3121';");
					while(rs.next()){
						String plrID = rs.getString("s_playerID");
						String posID = rs.getString("s_positionID");
						String team = rs.getString("t_teamName");
						String pts = rs.getString("s_PTS");
						String fg = rs.getString("s_FG");
						String fga = rs.getString("s_FGA");
						String fgp = rs.getString("s_FGP");
						String threep = rs.getString("s_3P");
						String threepa = rs.getString("s_3PA");
						String threepp = rs.getString("s_3PP");
						String ft = rs.getString("s_FT");
						String fta = rs.getString("s_FTA");
						String ftp = rs.getString("s_FTP");
						String orb = rs.getString("s_ORB");
						String drb = rs.getString("s_DRB");
						String trb = rs.getString("s_TRB");
						String ast = rs.getString("s_AST");
						String stl = rs.getString("s_STL");
						String blk = rs.getString("s_BLK");
						String tov = rs.getString("s_TOV");
						String pf = rs.getString("s_PF");
						String plusmin = rs.getString("s_plusmin");
						System.out.println(plrID + "\t" +  posID + "\t" +  pts + "\t" +
								fg + "\t" +  fga + "\t" +  fgp + "\t" +  threep + "\t" +  threepa + "\t" +
								threepp + "\t" +  ft + "\t" +  fta + "\t" +  ftp +  "\t" +
								orb + "\t" +  drb + "\t" +  trb + "\t" +  ast + "\t" +  stl + "\t" +
								blk + "\t" +  tov + "\t" +  pf + "\t" + plusmin);
						System.out.println();
					}
				}
				else if (gameNum == 2) {
					ResultSet rs = stat.executeQuery(
							"Select *"+
									"From statistics INNER JOIN matches ON (s_matchID = m_matchID)" +
									"INNER JOIN team t1 on (m_team1ID = t1.t_teamID)" +
									"INNER JOIN team t2 on (m_team2ID = t2.t_teamID)" +
							"WHERE s_matchID = '3122';");
					while(rs.next()){
						String plrID = rs.getString("s_playerID");
						String posID = rs.getString("s_positionID");
						String team = rs.getString("t_teamName");
						String pts = rs.getString("s_PTS");
						String fg = rs.getString("s_FG");
						String fga = rs.getString("s_FGA");
						String fgp = rs.getString("s_FGP");
						String threep = rs.getString("s_3P");
						String threepa = rs.getString("s_3PA");
						String threepp = rs.getString("s_3PP");
						String ft = rs.getString("s_FT");
						String fta = rs.getString("s_FTA");
						String ftp = rs.getString("s_FTP");
						String orb = rs.getString("s_ORB");
						String drb = rs.getString("s_DRB");
						String trb = rs.getString("s_TRB");
						String ast = rs.getString("s_AST");
						String stl = rs.getString("s_STL");
						String blk = rs.getString("s_BLK");
						String tov = rs.getString("s_TOV");
						String pf = rs.getString("s_PF");
						String plusmin = rs.getString("s_plusmin");
						System.out.println(plrID + "\t" +  posID + "\t" +  pts + "\t" +
								fg + "\t" +  fga + "\t" +  fgp + "\t" +  threep + "\t" +  threepa + "\t" +
								threepp + "\t" +  ft + "\t" +  fta + "\t" +  ftp +  "\t" +
								orb + "\t" +  drb + "\t" +  trb + "\t" +  ast + "\t" +  stl + "\t" +
								blk + "\t" +  tov + "\t" +  pf + "\t" + plusmin);
						System.out.println();
					}
				}
				else if (gameNum == 3) {
					ResultSet rs = stat.executeQuery(
							"Select *"+
									"From statistics INNER JOIN matches ON (s_matchID = m_matchID)" +
									"INNER JOIN team t1 on (m_team1ID = t1.t_teamID)" +
									"INNER JOIN team t2 on (m_team2ID = t2.t_teamID)" +
							"WHERE s_matchID = '3123';");
					while(rs.next()){
						String plrID = rs.getString("s_playerID");
						String posID = rs.getString("s_positionID");
						String team = rs.getString("t_teamName");
						String pts = rs.getString("s_PTS");
						String fg = rs.getString("s_FG");
						String fga = rs.getString("s_FGA");
						String fgp = rs.getString("s_FGP");
						String threep = rs.getString("s_3P");
						String threepa = rs.getString("s_3PA");
						String threepp = rs.getString("s_3PP");
						String ft = rs.getString("s_FT");
						String fta = rs.getString("s_FTA");
						String ftp = rs.getString("s_FTP");
						String orb = rs.getString("s_ORB");
						String drb = rs.getString("s_DRB");
						String trb = rs.getString("s_TRB");
						String ast = rs.getString("s_AST");
						String stl = rs.getString("s_STL");
						String blk = rs.getString("s_BLK");
						String tov = rs.getString("s_TOV");
						String pf = rs.getString("s_PF");
						String plusmin = rs.getString("s_plusmin");
						System.out.println(plrID + "\t" +  posID + "\t" +  pts + "\t" +
								fg + "\t" +  fga + "\t" +  fgp + "\t" +  threep + "\t" +  threepa + "\t" +
								threepp + "\t" +  ft + "\t" +  fta + "\t" +  ftp +  "\t" +
								orb + "\t" +  drb + "\t" +  trb + "\t" +  ast + "\t" +  stl + "\t" +
								blk + "\t" +  tov + "\t" +  pf + "\t" + plusmin);
						System.out.println();
					}
				}
				else if (gameNum == 4) {
					ResultSet rs = stat.executeQuery(
							"Select *"+
									"From statistics INNER JOIN matches ON (s_matchID = m_matchID)" +
									"INNER JOIN team t1 on (m_team1ID = t1.t_teamID)" +
									"INNER JOIN team t2 on (m_team2ID = t2.t_teamID)" +
							"WHERE s_matchID = '3124';");
					while(rs.next()){
						String plrID = rs.getString("s_playerID");
						String posID = rs.getString("s_positionID");
						String team = rs.getString("t_teamName");
						String pts = rs.getString("s_PTS");
						String fg = rs.getString("s_FG");
						String fga = rs.getString("s_FGA");
						String fgp = rs.getString("s_FGP");
						String threep = rs.getString("s_3P");
						String threepa = rs.getString("s_3PA");
						String threepp = rs.getString("s_3PP");
						String ft = rs.getString("s_FT");
						String fta = rs.getString("s_FTA");
						String ftp = rs.getString("s_FTP");
						String orb = rs.getString("s_ORB");
						String drb = rs.getString("s_DRB");
						String trb = rs.getString("s_TRB");
						String ast = rs.getString("s_AST");
						String stl = rs.getString("s_STL");
						String blk = rs.getString("s_BLK");
						String tov = rs.getString("s_TOV");
						String pf = rs.getString("s_PF");
						String plusmin = rs.getString("s_plusmin");
						System.out.println(plrID + "\t" +  posID + "\t" +  pts + "\t" +
								fg + "\t" +  fga + "\t" +  fgp + "\t" +  threep + "\t" +  threepa + "\t" +
								threepp + "\t" +  ft + "\t" +  fta + "\t" +  ftp +  "\t" +
								orb + "\t" +  drb + "\t" +  trb + "\t" +  ast + "\t" +  stl + "\t" +
								blk + "\t" +  tov + "\t" +  pf + "\t" + plusmin);
						System.out.println();
					}
				}
			}
		}

		catch(Exception e){
			System.err.println(e.getMessage());
			System.out.println("Program Ending...");
			System.exit(0);
		}
	}
	public static void adminOpt1(){
		try{
			Connection connect;
			Statement stat;
			connect = DriverManager.getConnection("jdbc:sqlite:/Users/jericosuguitan/eclipse-workspace/project111/ProjDB.db");
			stat = connect.createStatement();
			System.out.println("Connected to database.");

			System.out.println("Player ID");
			double s_playerID = scan.nextDouble();
			System.out.println("Position ID");
			double s_positionID = scan.nextDouble();
			System.out.println("Match ID");
			double s_matchID = scan.nextDouble();
			System.out.println("FG");
			double s_FG = scan.nextDouble();
			System.out.println("FGA");
			double s_FGA = scan.nextDouble();
			System.out.println("FGP");
			double s_FGP = scan.nextDouble();
			System.out.println("3P");
			double s_3P = scan.nextDouble();
			System.out.println("3PA");
			double s_3PA = scan.nextDouble();
			System.out.println("3PP");
			double s_3PP = scan.nextDouble();
			System.out.println("FT");
			double s_FT = scan.nextDouble();
			System.out.println("FTA");
			double s_FTA = scan.nextDouble();
			System.out.println("FTP");
			double s_FTP = scan.nextDouble();
			System.out.println("ORB");
			double s_ORB = scan.nextDouble();
			System.out.println("DRB");
			double s_DRB = scan.nextDouble();
			System.out.println("TRB");
			double s_TRB = scan.nextDouble();
			System.out.println("AST");
			double s_AST = scan.nextDouble();
			System.out.println("STL");
			double s_STL = scan.nextDouble();
			System.out.println("BLK");
			double s_BLK = scan.nextDouble();
			System.out.println("TOV");
			double s_TOV = scan.nextDouble();
			System.out.println("PF");
			double s_PF = scan.nextDouble();
			System.out.println("PTS");
			double s_PTS = scan.nextDouble();
			System.out.println("plusmin");
			double s_plusmin = scan.nextDouble();

			//System.out.println("Values received!");

			//System.out.println("s_playerID = " + s_playerID + " and s_STL = " + s_STL);
			
			
			stat.executeUpdate("INSERT INTO statistics " +
					"VALUES (" + s_playerID + ", " + s_positionID + ", " + s_matchID + ", " + s_FG + ", " + s_FGA  + 
					", " + s_FGP + ", " + s_3P + ", " + s_3PA + ", " + s_3PP + ", " + s_FT + ", " + s_FTA + ", " + s_FTP +
					", " + s_ORB + ", " + s_DRB + ", " + s_TRB + ", " + s_AST + ", " + s_STL + ", " + s_BLK + ", " + s_TOV + 
					", " + s_PF + ", " + s_PTS + ", " + s_plusmin + ");");
			System.out.println("Values inserted into statistics.");

			stat.close();
			//System.out.println("Connected to database.");
			connect.close();
			System.out.println();
		}
		catch(Exception e){
			System.err.println(e.getMessage());
			System.out.println("Program Ending...");
			System.exit(0);
		}
	}

}
