package com.business.hall.controller;


import com.alibaba.fastjson.JSON;
import com.business.hall.sys.config.BaseConfiguration;
import com.business.hall.helper.Result;
import com.business.hall.constant.upload.FileUploadTypeEnum;
import com.business.hall.constant.upload.ImgBusinessTypeEnum;
import com.business.hall.constant.upload.ImgUploadTableEnum;
import com.business.hall.constant.upload.UploadEditTypeEnum;
import com.business.hall.utils.DateUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.*;

/**
 *
 */
@Controller
@RequestMapping(value = "/api/imgUpload")
public class ImgUploadController {
    @Resource
    private BaseConfiguration baseConfiguration;


    /**
     * 单张图片上传方法
     *
     * @param file
     * @return
     */
    @RequestMapping("/singleImg")
    @ResponseBody
    public Object fileUpload(HttpServletRequest request,
                             HttpServletResponse response,
                             MultipartFile file,
                             Integer businessEnumType,//上传图片的业务类型
                             Integer tableType,//上传表的类型
                             Integer richEditType,//富文本框类型
                             Integer rotate) {//图片旋转
        String originalLogicPath = request.getScheme() //当前链接使用的协议
            + "://" + request.getServerName()//服务器地址
            + ":" + request.getServerPort() //服务器端口号
            + "" + request.getContextPath(); //本项目路径

        System.out.println("request.getContextPath() = " + request.getContextPath());

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        // String strBackUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + httpRequest.getContextPath() + "";

        String logicPath = baseConfiguration.getServerNamePortPath() + request.getContextPath();
        System.out.println("originalLogicPath:" + originalLogicPath);
        System.out.println("logicPath:"+logicPath);

        try {
            if (!file.isEmpty()) {
                try {
                    //要存储的物理地址
                    //可以访问的映射地址
                    //接收的数据的格式

                    String separator = File.separator;

                    //1、上传图片
                    String todayStr = DateUtil.formatToString(new Date(), DateUtil.getDay());

                    String physicsPrefixPath = "";
                    String requestPrefixPath = "";

                    String businessPath = ImgBusinessTypeEnum.getValueByIndex(9);
                    if(ImgBusinessTypeEnum.isContainsIndexKey(businessEnumType)){
                        businessPath = ImgBusinessTypeEnum.getValueByIndex(businessEnumType);
                    }

                    physicsPrefixPath = baseConfiguration.getUploadImgPath() + separator + businessPath.replace("@", separator)
                            + separator + todayStr;//文件存储的绝对路径
                    requestPrefixPath = logicPath + FileUploadTypeEnum.IMG_UPLOAD.getValue() + "/" + businessPath.replace("@", "/")
                            + "/" + todayStr;//文件请求路径

                    Integer realTableType = 999;
                    if(ImgUploadTableEnum.isContainsIndexKey(tableType)){
                        realTableType = tableType;
                    }

                    physicsPrefixPath = physicsPrefixPath + separator + String.valueOf(realTableType);//文件存储的绝对路径
                    requestPrefixPath = requestPrefixPath + "/" + String.valueOf(realTableType);//文件请求路径


                    String fileOriginalName = file.getOriginalFilename();//原始文件名
                    String realFileName = getUUIDFileName(file);//新文件名

                    String requestAllPath = requestPrefixPath + "/" + realFileName;//文件请求全路径

                    File folder = new File(physicsPrefixPath);
                    if (!folder.exists()) folder.mkdirs();


                    String physicsTmpPath = physicsPrefixPath + separator + "tmp";//文件临时存储的绝对路径
                    File tmpFolder = new File(physicsTmpPath);
                    if (!tmpFolder.exists()) tmpFolder.mkdirs();

                    FileUtils.copyInputStreamToFile(file.getInputStream(), new File(physicsPrefixPath, realFileName));

                    /*//临时存储图片
                    FileUtils.copyInputStreamToFile(file.getInputStream(), new File(physicsTmpPath, realFileName));
                    //压缩进行真正保存
                    ImageHelper.reduceBigImg(physicsTmpPath + separator + realFileName, physicsPrefixPath + separator + realFileName, 1000, 1000);
                    new File(physicsTmpPath ,realFileName).delete();

                    if(null!=rotate){
                        //RotateImg.testRotateImg(physicsPrefixPath + separator + realFileName);
                    }*/

                    //FileUtil.delFile(physicsTmpPath + separator + realFileName);

                    System.out.println("uploadFile physicsTmpPath:" + physicsTmpPath);

                    String prefix = fileOriginalName.substring(fileOriginalName.lastIndexOf(".") + 1);
                    Map data = new HashMap();
                    data.put("fileName", fileOriginalName);
                    data.put("filePath", requestAllPath);

                    Map<String, Object> json = new HashMap<>();
                    json.put("message", "uploadSuccess!");
                    json.put("data", data);
                    json.put("code", 200);

                    if(null!=richEditType && richEditType == UploadEditTypeEnum.CLOUD_CLINIC_HIGH_SPEED.getValue()) {//高拍仪上传图片
                        return requestAllPath;
                    }else if(null!=richEditType && richEditType == UploadEditTypeEnum.Wangeditor.getValue()){//wangEditor 富文本框上传图片
                        Map<String, Object> fileJson = new HashMap<>();
                        fileJson.put("errno", 0);
                        List<String> fileList = new ArrayList<String>();
                        fileList.add(requestAllPath);
                        fileJson.put("data", fileList);

                        System.out.println("fileJson:" + JSON.toJSONString(fileJson));
                        return fileJson;
                    }else{
                        return json;
                    }
                } catch (Exception e) {
                    return Result.error("上传失败，原因:" + e.getMessage());
                }
            } else {
                return Result.error("图片为空");
            }
        } catch (Exception e) {
            return Result.error("上传失败，原因:" + e.getMessage());
        }
    }



    /*protected static String getUUIDFileName(MultipartFile file) {
        String orgname = file.getOriginalFilename();
        String prefix = orgname.substring(orgname.lastIndexOf(".") + 1);
        String s = UUID.randomUUID().toString();
        String uuid = s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) + s.substring(19, 23) + s.substring(24);
        return uuid + "." + prefix;
    }*/

    protected static String getUUIDFileName(MultipartFile file) {
        String orgname = file.getOriginalFilename();
        String prefix = orgname.substring(orgname.lastIndexOf(".") + 1);
        String s = UUID.randomUUID().toString();
        String uuid = s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) + s.substring(19, 23) + s.substring(24);
        return uuid + "." + prefix;
    }


}
