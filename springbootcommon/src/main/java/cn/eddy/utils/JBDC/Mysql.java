package cn.eddy.utils.JBDC;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Driver;
import com.mysql.jdbc.Statement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Mysql {
	static String jbdc = "jdbc:mysql://47.93.34.35:3306/eddy";
	static String user = "root";
	static String pass = "sxc594678448";
	
	public static void main(String[] args) {
		String readTxt = readTxt(new File("C:\\Users\\Administrator\\Desktop\\test.txt"),"GBK");
		String[] split = readTxt.split(";;");
		System.err.println(split.length);
		System.err.println(readTxt);
		String sql="";
		try {
			Class.forName("com.mysql.jdbc.Driver") ;   
			java.sql.DriverManager.registerDriver(new Driver());
			Connection conn = (Connection) DriverManager.getConnection(jbdc , user , pass ) ;
			Statement stmt = (Statement) conn.createStatement() ;
//		    PreparedStatement pstmt = conn.prepareStatement(sql) ;   
//		    CallableStatement cstmt = (CallableStatement) conn.prepareCall("{CALL demoSp(? , ?)}") ; 
//		    ResultSet rs = stmt.executeQuery("") ;
		    int i = 0;
		    for (String string : split) {
		    	
		    	sql="insert into test_random(id,str) values(null,'"+string+"')";
		    	PreparedStatement prepareStatement = conn.prepareStatement(sql);
		    	prepareStatement.executeUpdate(sql);
		    	i++;
			}
		    
		    
		    
//		    if(rs != null)
//		     {   // 关闭记录集   
//		        try{   
//		            rs.close() ;   
//		        }catch(SQLException e){   
//		            e.printStackTrace() ;   
//		        }   
//		     }   
		     if(stmt != null)
		     {   // 关闭声明   
		        try{   
		            stmt.close() ;   
		        }catch(SQLException e){   
		            e.printStackTrace() ;   
		        }   
		     }   
		     if(conn != null)
		     {  // 关闭连接对象   
		         try{   
		            conn.close() ;   
		         }catch(SQLException e){   
		            e.printStackTrace() ;   
		         }   
		     } 
		} catch (Exception e){   
			System.out.println("找不到驱动程序类 ，加载驱动失败！");   
			e.printStackTrace() ;   
		} 
		 
	}
	public static String readTxt(File file, String charset){
		//设置默认编码
        if(charset == null){
            charset = "UTF-8";
        }
         
        if(file.isFile() && file.exists()){
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, charset);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                 
                StringBuffer sb = new StringBuffer();
                String text = null;
                while((text = bufferedReader.readLine()) != null){
                    sb.append(text);
                }
                return sb.toString();
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
        return null;
	}
}