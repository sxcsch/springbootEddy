package cn.eddy.controller;

import cn.eddy.entity.ResponseMessage;
import cn.eddy.service.CodeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Created by Administrator on 2018/7/23.
 */
@RequestMapping
@RestController
@Api("测试用例")
public class CodeController {
    @Autowired
    private CodeService codeService;

    @GetMapping("/codeStr")
    @ResponseBody
    @ApiOperation(value = "测试字符串", notes = "测试字符串")
    public ResponseMessage str(HttpServletRequest request, HttpServletResponse response) {
        try {
            String str = codeService.getRandomStr((int) (Math.random() * 101 + 1));
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "GET,POST");
            return new ResponseMessage(str).setCode("0");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }


    @GetMapping("/codeStrs")
    public void strs(HttpServletResponse response, HttpServletRequest request) {
        try {
            String str = codeService.getRandomStr((int) (Math.random() * 101 + 1));
            StringBuffer sb = new StringBuffer();
            sb.append("<h1>");
            sb.append(str);
            sb.append("</h1>");
            response.setCharacterEncoding("utf-8");
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "GET,POST");
            response.setContentType("text/html;charset=utf-8");
            String result = sb.toString();
            //前端传过来的回调函数名称
            String callback = request.getParameter("callback");
            //用回调函数名称包裹返回数据，这样，返回数据就作为回调函数的参数传回去了
            result = callback + "(" + result + ")";
            response.getWriter().write(result);
            response.getWriter().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
