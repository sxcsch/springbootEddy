package cn.eddy.restfulInterface;

import cn.eddy.entity.ResponseMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.*;

@Api("测试用例")
@Path("/test")
@RestController
public interface TestController {
    @ApiOperation(
            value = "测试字符串",
            notes = "测试字符串")
    @GET
    @Path("/searchName")
    @Produces("application/json")
    ResponseMessage searchBankName(@PathParam("str") String str);
}
