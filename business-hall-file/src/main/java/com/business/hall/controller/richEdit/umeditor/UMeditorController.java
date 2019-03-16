package com.business.hall.controller.richEdit.umeditor;

import com.business.hall.sys.config.BaseConfiguration;
import com.business.hall.controller.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by yangzhou-he on 2017-05-25.
 */
@Controller
@RequestMapping(value = "/api/umeditor")
public class UMeditorController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(UMeditorController.class);

    @Resource
    private BaseConfiguration baseConfiguration;

    /*@RequestMapping(value = "/ueditor")
    public void config(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        String rootPath = request.getSession().getServletContext()
                .getRealPath("/");
        try {
            String exec = new ActionEnter(request, rootPath).exec();
            PrintWriter writer = response.getWriter();
            writer.write(exec);
            writer.flush();
            writer.close();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }*/


    @RequestMapping(value = "/config")
    @ResponseBody
    public Object ueditor(HttpServletRequest request, HttpServletResponse response) {
        String s = "{\"imageActionName\": \"uploadimage\"," +
                "\"imageFieldName\": \"file\"," +
                "\"imageMaxSize\": 2048000," +
                "\"imageAllowFiles\": [\".png\", \".jpg\", \".jpeg\", \".gif\", \".bmp\"]," +
                "\"imageCompressEnable\": true," +
                "\"imageCompressBorder\": 1600," +
                "\"imageInsertAlign\": \"none\"," +
                "\"imageUrlPrefix\": \"\"," +
                "\"imagePathFormat\": \"/ueditor/jsp/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}\"}" ;

        String ss = "{" +
                "    \"imageActionName\": \"uploadimage\"," +
                "    \"imageFieldName\": \"upfile\"," +
                "    \"imageMaxSize\": 2048000," +
                "    \"imageAllowFiles\": [\".png\", \".jpg\", \".jpeg\", \".gif\", \".bmp\"]," +
                "    \"imageCompressEnable\": true, " +
                "    \"imageCompressBorder\": 1600," +
                "    \"imageInsertAlign\": \"none\"," +
                "    \"imageUrlPrefix\": \"\"," +
                "    \"imagePathFormat\": \"/ueditor/jsp/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}\"," +
                "    " +
                "    \"scrawlActionName\": \"uploadscrawl\"," +
                "    \"scrawlFieldName\": \"upfile\"," +
                "    \"scrawlPathFormat\": \"/ueditor/jsp/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}\"," +
                "    \"scrawlMaxSize\": 2048000," +
                "    \"scrawlUrlPrefix\": \"\"," +
                "    \"scrawlInsertAlign\": \"none\"," +
                "" +
                "    \"snapscreenActionName\": \"uploadimage\"," +
                "    \"snapscreenPathFormat\": \"/ueditor/jsp/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}\"," +
                "    \"snapscreenUrlPrefix\": \"\"," +
                "    \"snapscreenInsertAlign\": \"none\"," +
                "" +
                "    \"catcherLocalDomain\": [\"127.0.0.1\", \"localhost\", \"img.baidu.com\"]," +
                "    \"catcherActionName\": \"catchimage\"," +
                "    \"catcherFieldName\": \"source\"," +
                "    \"catcherPathFormat\": \"/ueditor/jsp/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}\"," +
                "    \"catcherUrlPrefix\": \"\"," +
                "    \"catcherMaxSize\": 2048000," +
                "    \"catcherAllowFiles\": [\".png\", \".jpg\", \".jpeg\", \".gif\", \".bmp\"]," +
                "" +
                "    \"videoActionName\": \"uploadvideo\"," +
                "    \"videoFieldName\": \"upfile\"," +
                "    \"videoPathFormat\": \"/ueditor/jsp/upload/video/{yyyy}{mm}{dd}/{time}{rand:6}\"," +
                "    \"videoUrlPrefix\": \"\"," +
                "    \"videoMaxSize\": 102400000," +
                "    \"videoAllowFiles\": [" +
                "        \".flv\", \".swf\", \".mkv\", \".avi\", \".rm\", \".rmvb\", \".mpeg\", \".mpg\"," +
                "        \".ogg\", \".ogv\", \".mov\", \".wmv\", \".mp4\", \".webm\", \".mp3\", \".wav\", \".mid\"], /* 上传视频格式显示 */" +
                "" +
                "    \"fileActionName\": \"uploadfile\"," +
                "    \"fileFieldName\": \"upfile\"," +
                "    \"filePathFormat\": \"/ueditor/jsp/upload/file/{yyyy}{mm}{dd}/{time}{rand:6}\"," +
                "    \"fileUrlPrefix\": \"\"," +
                "    \"fileMaxSize\": 51200000," +
                "    \"fileAllowFiles\": [" +
                "        \".png\", \".jpg\", \".jpeg\", \".gif\", \".bmp\"," +
                "        \".flv\", \".swf\", \".mkv\", \".avi\", \".rm\", \".rmvb\", \".mpeg\", \".mpg\"," +
                "        \".ogg\", \".ogv\", \".mov\", \".wmv\", \".mp4\", \".webm\", \".mp3\", \".wav\", \".mid\"," +
                "        \".rar\", \".zip\", \".tar\", \".gz\", \".7z\", \".bz2\", \".cab\", \".iso\"," +
                "        \".doc\", \".docx\", \".xls\", \".xlsx\", \".ppt\", \".pptx\", \".pdf\", \".txt\", \".md\", \".xml\"" +
                "    ]," +
                "" +
                "    \"imageManagerActionName\": \"listimage\"," +
                "    \"imageManagerListPath\": \"/ueditor/jsp/upload/image/\"," +
                "    \"imageManagerListSize\": 20," +
                "    \"imageManagerUrlPrefix\": \"\"," +
                "    \"imageManagerInsertAlign\": \"none\"," +
                "    \"imageManagerAllowFiles\": [\".png\", \".jpg\", \".jpeg\", \".gif\", \".bmp\"]," +
                "" +
                "    \"fileManagerActionName\": \"listfile\"," +
                "    \"fileManagerListPath\": \"/ueditor/jsp/upload/file/\"," +
                "    \"fileManagerUrlPrefix\": \"\"," +
                "    \"fileManagerListSize\": 20," +
                "    \"fileManagerAllowFiles\": [" +
                "        \".png\", \".jpg\", \".jpeg\", \".gif\", \".bmp\"," +
                "        \".flv\", \".swf\", \".mkv\", \".avi\", \".rm\", \".rmvb\", \".mpeg\", \".mpg\"," +
                "        \".ogg\", \".ogv\", \".mov\", \".wmv\", \".mp4\", \".webm\", \".mp3\", \".wav\", \".mid\"," +
                "        \".rar\", \".zip\", \".tar\", \".gz\", \".7z\", \".bz2\", \".cab\", \".iso\"," +
                "        \".doc\", \".docx\", \".xls\", \".xlsx\", \".ppt\", \".pptx\", \".pdf\", \".txt\", \".md\", \".xml\"" +
                "    ]" +
                "}";

        Map<String, Object> conf = new HashMap<String, Object>();
        conf.put("imageActionName","uploadimage");
        conf.put("imageFieldName","file");
        conf.put("imageMaxSize",2048000);
        List<String> imgList = new ArrayList<>();
        imgList.add(".jpg");
        imgList.add(".png");
        imgList.add(".jpeg");
        imgList.add(".gif");
        conf.put("imageAllowFiles",imgList);
        conf.put("imageCompressEnable",true);
        conf.put("imageCompressBorder",1600);
        conf.put("imageInsertAlign","none");
        conf.put("imageUrlPrefix","");
        conf.put("imagePathFormat","");

        System.out.println("s: " + s);

        return conf;
    }


    @RequestMapping(value = "/imgUpdate")
    @ResponseBody
    public Object imgUpdate(MultipartFile file) {
        if (file.isEmpty()) {
            return "error";
        }

        String realPath = "baseConfiguration.getFemalefilerecord";
        // 获取文件名
        String fileName = file.getOriginalFilename();
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        // 这里我使用随机字符串来重新命名图片
        fileName = Calendar.getInstance().getTimeInMillis() + UUID.randomUUID().toString().substring(0,5) + suffixName;
        // 这里的路径为Nginx的代理路径，这里是/data/images/xxx.png
        File dest = new File(realPath + System.getProperty("file.separator") + fileName);

        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
            //url的值为图片的实际访问地址 这里我用了Nginx代理，访问的路径是http://localhost/xxx.png
            String config = "{\"state\": \"SUCCESS\"," +
                    "\"url\": \"" + "http://localhost:8083" + "aaaaaaaaaaaaaaaaa" + "/" + fileName + "\"," +
                    "\"title\": \"" + fileName + "\"," +
                    "\"original\": \"" + fileName + "\"}";

            Map<String, String> result = new HashMap<>();
            result.put("state", "SUCCESS");
            result.put("url", "http://localhost:8083" + "aaaaaaaaaaaaaaaaa" + "/" + fileName);
            result.put("title", fileName);
            result.put("original", fileName);


            return result;
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "error";
    }











}




