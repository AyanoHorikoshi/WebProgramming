package dao;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.DatatypeConverter;

import model.User;

/**
 * ユーザテーブル用のDao
 * @author takano
 *
 */
public class UserDao {

    /**
     * ログインIDとパスワードに紐づくユーザ情報を返す
     * @param loginId
     * @param password
     * @return
     */
    public User findByLoginInfo(String loginId, String password) {
        Connection conn = null;
        try {
            // データベースへ接続
            conn = DBManager.getConnection();

            // SELECT文を準備
            String sql = "SELECT * FROM user WHERE login_id = ? and password = ?";

             // SELECTを実行し、結果表を取得
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, loginId);
            pStmt.setString(2, convertMD5(password));
            ResultSet rs = pStmt.executeQuery();

             // 主キーに紐づくレコードは1件のみなので、rs.next()は1回だけ行う
            if (!rs.next()) {
                return null;
            }

            String loginIdData = rs.getString("login_id");
            String nameData = rs.getString("name");
            return new User(loginIdData, nameData);

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            // データベース切断
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
    }
    public boolean searchLoginId(String loginId) {
        Connection conn = null;
        try {
            // データベースへ接続
            conn = DBManager.getConnection();

            // SELECT文を準備
            String sql = "SELECT * FROM user WHERE login_id = ?";

             // SELECTを実行し、結果表を取得
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, loginId);
            ResultSet rs = pStmt.executeQuery();

             // 主キーに紐づくレコードは1件のみなので、rs.next()は1回だけ行う
            if (!rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // データベース切断
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    /**
     * 全てのユーザ情報を取得する
     * @return
     */
    public List<User> findAll() {
        Connection conn = null;
        List<User> userList = new ArrayList<User>();

        try {
            // データベースへ接続
            conn = DBManager.getConnection();

            // SELECT文を準備
            // TODO: 未実装：管理者以外を取得するようSQLを変更する
            String sql = "SELECT * FROM user WHERE name not in ('管理者')";

             // SELECTを実行し、結果表を取得
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            // 結果表に格納されたレコードの内容を
            // Userインスタンスに設定し、ArrayListインスタンスに追加
            while (rs.next()) {
                int id = rs.getInt("id");
                String loginId = rs.getString("login_id");
                String name = rs.getString("name");
                Date birthDate = rs.getDate("birth_date");
                String password = rs.getString("password");
                String createDate = rs.getString("create_date");
                String updateDate = rs.getString("update_date");
                User user = new User(id, loginId, name, birthDate, password, createDate, updateDate);

                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            // データベース切断
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
        return userList;
    }

    public List<User> findSearch(String loginId,String name,String birthDate,String birthDate2) {
        Connection conn = null;
        List<User> userList = new ArrayList<User>();

        try {
            // データベースへ接続
            conn = DBManager.getConnection();

            // SELECT文を準備
            // TODO: 未実装：管理者以外を取得するようSQLを変更する
            String sql = "SELECT * FROM user WHERE name not in ('管理者')";

            if(!loginId.equals("")) {
            	sql += " and login_id = '" + loginId + "'";
            }

            if(!name.equals("")) {
            	sql += " and name LIKE '%" + name + "%'";
            }

            if(!birthDate.equals("")) {
            	sql += " and birth_date >= '" + birthDate + "'";
            }

            if(!birthDate2.equals("")) {
            	sql += " and birth_date <= '" + birthDate2 + "'";
            }

             // SELECTを実行し、結果表を取得
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            // 結果表に格納されたレコードの内容を
            // Userインスタンスに設定し、ArrayListインスタンスに追加
            while (rs.next()) {
                int id = rs.getInt("id");
                String loginId1 = rs.getString("login_id");
                String name1 = rs.getString("name");
                Date birthDate1 = rs.getDate("birth_date");
                String password = rs.getString("password");
                String createDate = rs.getString("create_date");
                String updateDate = rs.getString("update_date");
                User user = new User(id, loginId1, name1, birthDate1, password, createDate, updateDate);

                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            // データベース切断
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
        return userList;
    }

    public void insertNewUser(String loginId,String password,String name,String birthDate) {
    	Connection conn = null;

        try {
            // データベースへ接続
            conn = DBManager.getConnection();

            // SELECT文を準備
            String sql = "INSERT INTO user(login_id,name,birth_date,password,create_date,update_date) VALUES(?,?,?,?,now(),now())";

             // SELECTを実行し、結果表を取得
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, loginId);
            pStmt.setString(2, name);
            pStmt.setString(3, birthDate);
            pStmt.setString(4, convertMD5(password));
            int rs = pStmt.executeUpdate();



        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // データベース切断
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
           }
      }
}
     public User userReference(int id) {
    	 Connection conn = null;

         try {
             conn = DBManager.getConnection();
             String sql = "SELECT login_id,name,birth_date,password,create_date,update_date FROM user WHERE id = ?";
             PreparedStatement pStmt = conn.prepareStatement(sql);
             pStmt.setInt(1, id);
             ResultSet rs = pStmt.executeQuery();
             if (!rs.next()) {
            	 return null;
             }
             String loginId = rs.getString("login_id");
             String name = rs.getString("name");
             String password = rs.getString("password");
             Date birthDate = rs.getDate("birth_date");
             String createDate = rs.getString("create_date");
             String updateDate = rs.getString("update_date");
             return new User(id, loginId, name, birthDate, password, createDate, updateDate);

         } catch (SQLException e) {
             e.printStackTrace();
             return null;
         } finally {
             // データベース切断
             if (conn != null) {
                 try {
                     conn.close();
                 } catch (SQLException e) {
                     e.printStackTrace();
                     return null;
                 }
             }
         }
     }
   public void userUpdate(String password,String name,String birthDate,int id) {
        	 Connection conn = null;
             try {
                 conn = DBManager.getConnection();
                 String sql = "UPDATE user SET name= ? ,birth_date= ?, password= ? WHERE id = ?";
                 PreparedStatement pStmt = conn.prepareStatement(sql);
                 pStmt.setString(1, name);
                 pStmt.setString(2, birthDate);
                 pStmt.setString(3, convertMD5(password));
                 pStmt.setInt(4, id);
                 pStmt.executeUpdate();
             } catch (SQLException e) {
                 e.printStackTrace();
             } finally {
                 // データベース切断
                 if (conn != null) {
                     try {
                         conn.close();
                     } catch (SQLException e) {
                         e.printStackTrace();
                     }
                 }
             }
   }
             public User delete(int id) {
            	 Connection conn = null;
                 try {
                     conn = DBManager.getConnection();
                     String sql = "DELETE FROM user WHERE id = ?";
                     PreparedStatement pStmt = conn.prepareStatement(sql);
                     pStmt.setInt(1, id);
                     pStmt.executeUpdate();
                 } catch (SQLException e) {
                     e.printStackTrace();
                 } finally {
                     // データベース切断
                     if (conn != null) {
                         try {
                             conn.close();
                         } catch (SQLException e) {
                             e.printStackTrace();
                         }
                     }
                 }
				return null;
    }
             private String convertMD5(String password) {
            	//ハッシュを生成したい元の文字列
            	 String source = password;
            	 //ハッシュ生成前にバイト配列に置き換える際のCharset
            	 Charset charset = StandardCharsets.UTF_8;
            	 //ハッシュアルゴリズム
            	 String algorithm = "MD5";

            	 //ハッシュ生成処理
            	 byte[] bytes = null;
				try {
					bytes = MessageDigest.getInstance(algorithm).digest(source.getBytes(charset));
				} catch (NoSuchAlgorithmException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
            	 String result = DatatypeConverter.printHexBinary(bytes);
				return result;

             }
}


