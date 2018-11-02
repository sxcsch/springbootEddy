package cn.eddy.restfulInterface.restfulImpl;

import cn.eddy.entity.ResponseMessage;
import cn.eddy.restfulInterface.TestController;
import org.springframework.stereotype.Component;


public class TestControllerImpl implements TestController  {

    @Override
    public ResponseMessage searchBankName(String str) {
        return new ResponseMessage("李强银行");
    }
}
