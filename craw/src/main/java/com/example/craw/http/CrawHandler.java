package com.example.craw.http;

import com.example.craw.dto.RequestDTO;
import com.example.craw.dto.ResponseDTO;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @description 
 * @author xiaobo
 * @date 2023/11/29 8:50
 */
@Data
public abstract class CrawHandler {


  public abstract boolean match(CrawEnum crawEnum);

  public void craw(RequestDTO requestDTO) {
      httpRequest(requestDTO);
      convertResponse(requestDTO);
      saveData(requestDTO);
  };

  //http 请求数据
  abstract void httpRequest(RequestDTO requestDTO);

  //相应数据转换
  abstract void convertResponse(RequestDTO requestDTO);

  //转换好的数据存库
  abstract void saveData(RequestDTO requestDTO);
}
