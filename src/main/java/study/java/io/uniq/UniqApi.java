package study.java.io.uniq;

import com.fasterxml.jackson.databind.deser.DataFormatReaders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author jackie chen
 * @create 2017/10/19
 * @description UniqApi
 */
@Component
public class UniqApi {

    @Value("${api.path}")
    private String API_FILE_PATH;

    Logger logger = LoggerFactory.getLogger(UniqApi.class);

    private String REX_STR = "(http://.*[^\\s])(\\s*\\\"?)";

    private Pattern pattern = Pattern.compile(REX_STR);

    private Matcher matcher;

    List<String> allLine = new ArrayList<>();//所有api接口
    List<String> repeatLine = new ArrayList<>();//重复api接口

    public void uniqApi() {
        try {
            URL a = this.getClass().getResource("/" + API_FILE_PATH);
            //获取文件流
            String filePath = getClass().getResource(API_FILE_PATH).getFile();
            BufferedReader reader = new BufferedReader(new FileReader(filePath));

            String line;
            while ((line = reader.readLine()) != null) {
                //记录重复行
                uniqLine(line);
            }
        } catch (FileNotFoundException e) {
            logger.error("=== uniqApi FileNotFoundException ===", e);
        } catch (IOException e) {
            logger.error("=== uniqApi IOException ===", e);
        }
        printResult(repeatLine, allLine);
    }

    /**
     * 记录重复行
     *
     * @param line
     */
    public void uniqLine(String line) {
        matcher = pattern.matcher(line);
        if (matcher.find()) {
            //模式匹配，记录重复行
            if (allLine.contains(matcher.group(1))) {
                if (!repeatLine.contains(matcher.group(1))) {
                    repeatLine.add(matcher.group(1));
                }
            } else {
                allLine.add(matcher.group(1));
            }
        } else {
            logger.error("=== this line not match ===, line", line);
        }

    }

    private void printResult(List<String> repeatLine, List<String> allLine) {
        System.out.println(repeatLine);
    }
}
