package sys_practice;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/awss3")
public class AWSS3 extends HttpServlet {

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//文字コード等　基本設定
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		try {
			log("アクセスされました!");
			final AmazonS3 s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.AP_NORTHEAST_1).build();
			List<Bucket> buckets = s3.listBuckets();
			System.out.println("Your {S3} buckets are:");
			for (Bucket b : buckets) {
			    System.out.println("* " + b.getName());
			}
		}catch(Exception e) {
			PrintWriter testPrint = response.getWriter();
	        testPrint.println(e);
	        System.err.println(e);
		}finally {
			response.sendRedirect("http://www.google.com");
		}

	}

}