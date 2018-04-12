package by.gsu.epamlab.controller.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;
import by.gsu.epamlab.beans.Constant;
import by.gsu.epamlab.beans.Tasks;
import by.gsu.epamlab.connectionDB.ConnectionSingleton;
import by.gsu.epamlab.controller.ServletUtilite;
import by.gsu.epamlab.utilits.TasksDAOFactory;
import jdk.nashorn.internal.ir.RuntimeNode.Request;

@WebServlet("/upload")
public class UploadDownloadFileServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  public void init(ServletConfig config) throws ServletException {
    ConnectionSingleton.setParameterInDB("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/eeproject?autoReconnect=true&useSSL=false", "root", "root");
    DiskFileItemFactory fileFactory = new DiskFileItemFactory();
    File filesDir = (File) config.getServletContext().getAttribute("FILES_DIR_FILE");
    fileFactory.setRepository(filesDir);
    this.upload = new ServletFileUpload(fileFactory);
  }
  private ServletFileUpload upload;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String fileName = request.getParameter("fileName");
    if(fileName == null || fileName.equals("")){
      throw new ServletException("File Name can't be null or empty");
    }
    File file = new File(request.getServletContext().getAttribute("FILES_DIR")+File.separator+fileName);
    if(!file.exists()){
      throw new ServletException("File doesn't exists on server.");
    }
    System.out.println("File location on server::"+file.getAbsolutePath());
    ServletContext ctx = getServletContext();
    InputStream fis = new FileInputStream(file);
    String mimeType = ctx.getMimeType(file.getAbsolutePath());
    response.setContentType(mimeType != null? mimeType:"application/octet-stream");
    response.setContentLength((int) file.length());
    response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

    ServletOutputStream os = response.getOutputStream();
    byte[] bufferData = new byte[1024];
    int read=0;
    while((read = fis.read(bufferData))!= -1){
      os.write(bufferData, 0, read);
    }
    os.flush();
    os.close();
    fis.close();
    System.out.println("File downloaded at client successfully");
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    System.out.println(request.getParameter("file"));
    if(!ServletFileUpload.isMultipartContent(request)){
      throw new ServletException("Content type is not multipart/form-data");
    }
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    out.write("<html><head></head><body>");
    try {
      List<FileItem> fileItemsList = upload.parseRequest(new ServletRequestContext(request));
      Iterator<FileItem> fileItemsIterator = fileItemsList.iterator();
      String fullFileName = "";
      
      // Write file chunks to the server
      while(fileItemsIterator.hasNext()){
        FileItem fileItem = fileItemsIterator.next();
        if(fileItem == null || fileItem.getName() ==null){
          continue;
        }
        String[] fileElements = fileItem.getName().split("\\.");
        String ext = fileElements[fileElements.length -1];   
        System.out.println(ext);
        String fileName = ServletUtilite.getHashMD5(Integer.valueOf((int)(Math.random()* Integer.MAX_VALUE)).toString());
        if (fullFileName.isEmpty()) {
          fullFileName = request.getServletContext().getAttribute("FILES_DIR") + File.separator + fileName + "." + ext;
        }
        File file = new File(fullFileName);
        fileItem.write(file);
      }

      TasksDAOFactory
      .getTasksDAO("db")
      .setFileURLForTask(getTasksURLParametr(request.getParameter("taskId"), fullFileName));
      response.sendRedirect(Constant.MAIN_PAGE);
    } catch(ParseException e){
      e.printStackTrace();
    } catch (FileUploadException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  private  static  Tasks getTasksURLParametr(String id, String url){
    return new Tasks()
        .setURL(url)
        .setIdtasks(Integer.valueOf(id));

  }
}

