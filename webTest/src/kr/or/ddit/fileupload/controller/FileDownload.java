package kr.or.ddit.fileupload.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.fileupload.service.FileinfoServiceImpl;
import kr.or.ddit.fileupload.service.IFileinfoService;
import kr.or.ddit.vo.FileInfoVO;

//파일 다운로드를 처리하는 서블릿
@WebServlet("/fileDownload.do")
public class FileDownload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		// 파라미터로 넘어온 파일 번호를 구한다.
		String strFileNo = request.getParameter("fileno"); // fileList.jsp에 있는 변수명
		int fileno = Integer.parseInt(strFileNo);

		// 파일 번호를 이용하여 DB에서 해당 파일 정보를 가져온다.
		IFileinfoService service = FileinfoServiceImpl.getInstance();

		FileInfoVO fvo = service.getFileinfo(fileno);

		// 업로드된 파일들이 저장될 폴더 설정 (서버 컴퓨터에 있는 폴더)
		// 현재는 내 컴퓨터가 서버/클라이언트가 될 수 있으므로 헷갈림 주의..!!
		String uploadPath = "d:/d_other/uploadFiles";

		// 지정된 폴더가 없으면 새로 만든다.
		File f = new File(uploadPath);
		if (!f.exists()) {
			f.mkdirs();
		}

		response.setCharacterEncoding("utf-8");

		// 다운 받을 파일의 File객체 생성 ==> 실제 저장된 파일명을 이용하여 생성한다.
		File downFile = new File(f, fvo.getSave_file_name());

		if (downFile.exists()) { // 다운 할 파일이 해당 폴더에 존재하면...

			// ContentType설정
			response.setContentType("application/octet-stream; charset=utf-8");

			// Response객체에 content-disposition 헤더 정보를 추가한다.
			// 이 헤더 정보에는 다운된 파일 저장될 파일명을 설정한다.

			String headerKey = "content-disposition";

			// 다운로드할 때 클라이언트에 저장될 파일 이름을 지정한다.
			// ==> 원래의 파일명으로 지정한다.
			//String headerValue = "attachment; filename=\"" + fvo.getOrigin_file_name() + "\";";
			
			//한글이름으로 된 파일 다운받을때 깨지는 현상 해결
			String headerValue =  "attachment; filename*=UTF-8''" 
				+ URLEncoder.encode(fvo.getOrigin_file_name(), "utf-8")
					.replaceAll("\\+", "%20");
			
			response.setHeader(headerKey, headerValue);

			// 자바의 입출력 스트림을 이용해서 서버에 있는 파일을 읽어서 클라이언트로 출력하는 작업을 진행하면 된다.
			BufferedInputStream bin = null;
			BufferedOutputStream bout = null;
			try {
				// 출력용 스트림 객체 생성 (Response객체를 이용한다.)
				bout = new BufferedOutputStream(response.getOutputStream());

				// 파일 입력용 스트림 객체 생성
				bin = new BufferedInputStream(new FileInputStream(downFile));

				byte[] temp = new byte[1024];
				int len = 0;
				while ((len = bin.read(temp)) > 0) {
					bout.write(temp, 0, len);
				}
				bout.flush();

			} catch (Exception e) {
				System.out.println("파일 입출력 오류: " + e.getMessage());
			} finally {
				if (bin != null)
					try {
						bin.close();
					} catch (IOException e) {
					}
				if (bout != null)
					try {
						bout.close();
					} catch (IOException e) {
					}
			}

		} else { // 파일이 없을 경우
			response.setContentType("text/html; charset=utf-8");
			response.getWriter().println("<h3>" + fvo.getOrigin_file_name()+"파일이 존재하지 않습니다.</h3>");

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
